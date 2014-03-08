---
title: Migrating from dvm to boot2docker
tags: docker personal dvm osx boot2docker troubleshooting
template: default.clj
---

# Migrating from `dvm` to `boot2docker`

## Preconditions

About two weeks ago, I wrote about how I got [dvm](https://fnichol.github.io/dvm/)
working with [Docker](https://www.docker.io) using a specified network range for
the `docker0` bridge. A quick overview of dvm, for the uninitiated: It's a Bash
script wrapped around a `Vagrantfile` which uses a Vagrant box based off the
[boot2docker](http://boot2docker.io) image. The original boot2docker script itself
is simply a Bash script wrapped around the [VBoxManage](http://www.virtualbox.org/manual/ch08.html)
command. There's an [on-going effort](https://github.com/boot2docker/boot2docker-cli)
to rewrite the boot2docker script in [Go](https://golang.org), but it is currently
experimental. Anyways, dvm uses Vagrant to interact with VirtualBox, where Vagrant
itself is a wrapper around interacting with VirtualBox. Since it uses Vagrant, it
needs [a Vagrant Box](https://github.com/mitchellh/boot2docker-vagrant-box/) to create
the VM, meaning that each time a new boot2docker image is released, a new Vagrant Box
must also be created before dvm can use the latest version.

You've probably already guessed why I'm migrating away, there's just simply too many
moving pieces that can each become a bottleneck. dvm can bottleneck with bugfixes while
I can also be bottlenecked by waiting for new Vagrant Boxes to be released. To eliminate this,
I have decided to move away from using dvm to using the original boot2docker (I'm actually using
boot2docker-cli, the Go port of the tool, but more on that later).

## Moving previous work

The downside to this is that I needed to find a way to perform the same customizations I was using
with dvm to change the network range of the `docker0` interface. With dvm, adding local customizations
is as simple as editing the provisioning script which is run wheneve the VM is created. Using
boot2docker itself wasn't as straight forward. As boot2docker is run entirely from memory,
a persistence disk must be attached to the VM to provide a place to save things so they won't be
deleted. While dvm handled this in the `Vagrantfile`, the boot2docker script actually creates it
for you as well and mounts it to `/mnt/sda1`. To add local customizations, you can place what you
want to do into a script titled `bootlocal.sh` and place that inside `/var/lib/boot2docker` (which
is symlinked to `/mnt/sda1/var/lib/boot2docker`).

The tasks I needed to take care of in this script were to:

1. Install bridge utils if not present
2. Modify Docker's init script to add the `--bip` option
3. Create a folder on the persistence disk for use as a mount point for SSHFS

To accomplish 1 and 2, I was able to re-use most of the work done in dvm's `Vagrantfile`. For 3, it
was simply a matter of creating a folder. Once I had written the script, however, I realized I needed
to get it on the VM disk before it'd be useful! I could have simply re-typed it while SSH'd into the
VM, but that's not very DRY and I'll likely need to add more files to the VM at a later point. Luckily,
the boot2docker maintainers [provide a
workaround](https://github.com/boot2docker/boot2docker/blob/master/doc/WORKAROUNDS.md#folder-sharing)
using [FUSE for OS X and SSHFS](https://osxfuse.github.io), so I was able to mount a folder from my
host laptop on the VM with minimal fuss.

The one issue I ran into was with the command used to mount the folder. In the steps given, they have
you create a file `b2d-passwd` containing the password for the docker user on the VM. Then, when you
go to mount the folder on the VM, they have you run:

    sshfs docker@localhost:/mnt/sda1/myapp ~/myapp -oping_diskarb,volname=b2d-myapp -p 2022 -o reconnect -o UserKnownHostsFile=/dev/null -o password_stdin < ~/.boot2docker/b2d-passwd

where `/mnt/sda1/myapp` is the mount-point on the VM and `~/myapp` is the folder on the host being shared
with the VM. The part that I had issue with was `-o password_stdin < ~/.boot2docker/b2d-passwd`. No matter
what I tried, I always got a timeout error while waiting for the prompt. When I removed that part of the
command, the reason for this became immediately obvious: I was being asked `yes/no` about the VM's SSH key,
meaning the prompt would never show! As of now, I haven't found a workaround, it is easy enough to type
`yes` and `tcuser` when I want to mount a folder.

## Bumps in the road

Once that was working, it was trivial to move the `bootlocal.sh` script from the shared folder to its
place in `/var/lib/boot2docker`. Then I simply logged out, stopped the VM, and copied the VMDK
to a safe place. Now when I want to create a new boot2docker VM, I just need to copy the VMDK into place
(`~/.boot2docker`) before running `boot2docker init`. Once that completes, I have a fresh VM with all
my local customizations. The next logical step for this would be to create a customized ISO that includes
the `bootlocal.sh` to remove the need to remember to copy the VMDK back into place when creating a new VM.

However, this is where the issues have started springing up. When I tried to run Test Kitchen through the
new boot2docker VM, I ran into the issue where
[the VM hangs waiting for the container to be ready](https://github.com/portertech/kitchen-docker/issues/24).
Reading through that thread it seemed like any issues with the software itself (Test Kitchen, kitchen-docker,
boot2docker, docker itself, etc) should have been fixed in the versions I am using (Test Kitchen 1.2.1,
kitchen-docker installed from GitHub, boot2docker 0.6.0, docker 0.8.1). This issue seemed familiar though,
and upon further digging I narrowed it down to routes getting crossed and confused. Aha! This was why I moved
to dvm in the first place. dvm is slightly different from the default boot2docker because it creates a
[host-only network](http://www.virtualbox.org/manual/ch06.html#network_hostonly) to run Docker on. This
solves the issue of criss-crossed routes, so I set out to find if this feature existed in boot2docker.

In my research, I found this [hostonly interface-related](https://github.com/boot2docker/boot2docker/pull/198)
pull request. At the time of this writing, the pull request is still open, so this feature hasn't made its
way into the main boot2docker. Fortunately though, at the bottom of the pull request the discussion gets
moved to [this pull request](https://github.com/boot2docker/boot2docker-cli/pull/42) on the new
`boot2docker-cli` repo. That request was merged, so the feature in some form exists. This is why I am
as of now using the `boot2docker-cli` tool instead of the normal one.

## What's left to do

Once that was all figured out, my next move was to try to get test kitchen and kitchen-docker working
together with this new boot2docker VM. I re-created my VMDK w/ the `bootlocal.sh` script and tried
to run `kitchen test`. Unfortunately, this fails right after pulling in the ubuntu images. Looking
through the logs, it seems like the containers are unable to access the Internet. Also, the boot2docker-cli
creates a new hostonly interface for each VM it creates, but it also never deletes them after the VMs
are deleted. This also leads to issues with criss-crossed routes as packets are being sent to the wrong
hostonly interface. Lastly, I seem to be unable to run `boot2docker-cli ssh` or `boot2docker ssh` to
log into the VMs while I'm connected to my work's VPN. This is strange since I was able to use both
of those commands just fine while at work, but as soon as I turned my VPN off, they both started
working. I suspect it is also an issue of criss-crossed routes. 

In summary, the following work is left to do before the migration from dvm to boot2docker is complete:

1. Figure out why containers cannot reach the internet
2. Modify `boot2docker-cli init/delete` to keep track of the interfaces it creates so they can be deleted
with the VM.
3. Figure out why I'm unable to SSH into the VM while logged into my work's VPN.

I've started reading about Go so that I can contribute patches, so hopefully I should be able to make some
progress on number 2 before too long. It seems to be a simple matter of creating a map of the
interfaces created to the VMs their attached to, but there's all of Go to grapple with first. It's going
to be a busy upcoming week too, as the 2014 Northeast CCDC is this Friday, Saturday, and Sunday at UNH.
Fun fun fun, so much to do, so little time.
