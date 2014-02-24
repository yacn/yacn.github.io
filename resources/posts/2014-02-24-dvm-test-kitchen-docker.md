---
title: Fixing dvm with Test Kitchen
tags: docker test-kitchen dvm
template: default.clj
---

# Fixing dvm with Test Kitchen

## Finding dvm

For the past few weeks at work, I've been conducting research on the side with the goal of setting up a 
continuous integration environment for our Chef cookbooks. I've decided to use [Test Kitchen](https://kitchen.ci)
with the [kitchen-docker](https://github.com/portertech/kitchen-docker) driver as the base
for our integration testing framework (of which the specifics are out of the scope of this post).

Now that I'd chosen our stack, it was time to get kitchen-docker, Test Kitchen, and Docker working
together. I needed a solution which would allow me to work with Docker locally on OS X. Luckily,
there are two related projects allowing me to do just that, [dvm](https://github.com/fnichol/dvm)
and [boot2docker](https://github.com/boot2docker/boot2docker) (dvm is actually just a wrapper around
Vagrant using a [boot2docker box](https://github.com/mitchellh/boot2docker-vagrant-box)).

## Bumpy start

The first issue I ran into was Docker choosing a network range which conflicted with the network
range our work runs in. I'd actually run into this issue before, several months ago when I was
first testing out Docker. At the time, there was no immediate workaround, so put it in the back of
my mind for later. Docker has since been updated to attempt to be smart about choosing the network
range by looking for any conflicting networks. However, this issue still affects us because Docker
is being run through a VM, meaning it will not detect the conflicting network.

A more recent addition to Docker is the `--bip=""` option, which you can use to specify a CIDR range
for the `docker0` bridge that Docker creates. However, to use this new feature with dvm, I was going
to need to make some modifications to support it.

## Modifying dvm

The way dvm works is by allowing you to set configuration options in a file `~/.dvm/dvm.conf`. Those
settings are environment variables which are exported when creating the boot2docker VM. To add support
for `--bip`, I added the configuration directive `DOCKER0_CIDR` to the config file. I then modified
the `Vagrantfile` to use the new argument if specified. I saved my changes, crossed my fingers, and
typed `dvm up`. To my dismay, passing in that option didn't seem to affect anything!

## (Re)building the bridge

After some more reading, I learned that if the `docker0` bridge has already been created, the
IP range assigned to it will not be affected by the `--bip` option. To use the option, you need to
first make sure the `docker0` bridge does not exist when starting Docker, meaning that if it does
exist, it will need to be deleted.

Deleting a bridge seems simple enough, you just need to run `brctl delbr nameofbridge`. Unfortunately,
the `brctl` utility is not included in the release of [Tiny Core Linux](http://www.tinycorelinux.net/)
used by boot2docker. I now set off to find a bridge-utils package I could install on the distro. Fortunately
I didn't need to dig for too long, there was a link to such a package in
[one of the docs](https://github.com/boot2docker/boot2docker/blob/master/doc/FAQ.md) for boot2docker.

Now all I needed to do was update the `Vagrantfile` to check whether or not the `DOCKER0_CIDR` option
was set, and if so, setup the arguments, stop the currently running Docker daemon, install the bridge
utils, and remove the `docker0` bridge. I ran `dvm up` and low and behold, it worked!

By this time I'm feeling pretty good, I've gotten much farther than ever before. I run `kitchen create`
to create a container and, you guessed it, it worked! Running `kitchen converge` also worked as expected,
which is great as now my container can access hosts within our local network as well as the internet at
large! Satisfied with myself, I ran `kitchen destroy` to delete the containers. Oh no, it doesn't work!

## One last jam

Unfortunately I ran into a jam (damn you Chris Christie!), running `kitchen destroy` throws an exception!
Looking at the message returned, it seems to be an issue related with trying to stop and remove the
container, something appears to be holding a lock over it:

    |ruby-1.9.3-p448| isaacs-air in ~/git/yacn/dvm/tmp
    ± |fix/unable-to-stop-delete-containers ✗| → kitchen destroy
    -----> Starting Kitchen (v1.2.1)
    -----> Destroying <default-ubuntu>...
    368ccd5979763ce38c072d9751417ba457827146548d7a04676b3a5795c806ca
    >>>>>> ------Exception-------
    >>>>>> Class: Kitchen::ActionFailed
    >>>>>> Message: Failed to complete #destroy action: [Expected process to exit with [0], but received '1'
    ---- Begin output of docker -H tcp://192.168.42.43:4243 rm 368ccd5979763ce38c072d9751417ba457827146548d7a04676b3a5795c806ca ----
    STDOUT:
    STDERR: Error: container_delete: Cannot destroy container 368ccd5979763ce38c072d9751417ba457827146548d7a04676b3a5795c806ca: Driver aufs failed to remove root filesystem 368ccd5979763ce38c072d9751417ba457827146548d7a04676b3a5795c806ca: rename /var/lib/docker/aufs/mnt/368ccd5979763ce38c072d9751417ba457827146548d7a04676b3a5795c806ca /var/lib/docker/aufs/mnt/368ccd5979763ce38c072d9751417ba457827146548d7a04676b3a5795c806ca-removing: device or resource busy
    2014/02/24 14:07:29 Error: failed to remove one or more containers
    ---- End output of docker -H tcp://192.168.42.43:4243 rm 368ccd5979763ce38c072d9751417ba457827146548d7a04676b3a5795c806ca ----
    Ran docker -H tcp://192.168.42.43:4243 rm 368ccd5979763ce38c072d9751417ba457827146548d7a04676b3a5795c806ca returned 1]
    >>>>>> ----------------------
    >>>>>> Please see .kitchen/logs/kitchen.log for more details
    >>>>>> Also try running `kitchen diagnose --all` for configuration

Yikes, that doesn't seem good. Well, lets see if the container is still there. I logged into the `dvm`
machine via `dvm ssh` and ran `docker ps`

    |ruby-1.9.3-p448| isaacs-air in ~/git/yacn/dvm/tmp
    ± |fix/unable-to-stop-delete-containers ✗| → dvm ssh
                            ##        .
                      ## ## ##       ==
                   ## ## ## ##      ===
               /""""""""""""""""\___/ ===
          ~~~ {~~ ~~~~ ~~~ ~~~~ ~~ ~ /  ===- ~~~
               \______ o          __/
                 \    \        __/
                  \____\______/
     _                 _   ____     _            _
    | |__   ___   ___ | |_|___ \ __| | ___   ___| | _____ _ __
    | '_ \ / _ \ / _ \| __| __) / _` |/ _ \ / __| |/ / _ \ '__|
    | |_) | (_) | (_) | |_ / __/ (_| | (_) | (__|   <  __/ |
    |_.__/ \___/ \___/ \__|_____\__,_|\___/ \___|_|\_\___|_|
    boot2docker: 0.5.4
    docker@boot2docker:~$ docker ps
    CONTAINER ID        IMAGE               COMMAND             CREATED             STATUS              PORTS               NAMES
    docker@boot2docker:~$ exit

Hmm, so it looks like the container was actually stopped. Researching this issue led me to
[this](https://github.com/dotcloud/docker/issues/2714#issuecomment-34408878)
thread. The issue seems to be related to using a symlink where Docker expects its runtime root to be.
By default, this is `/var/lib/docker`, but in boot2docker, this has been symlinked to 
`/mnt/sda/var/lib/docker`. This causes Docker to be unable to stop or remove any of the containers
it creates, as it doesn't seem to follow the symlink. 

The remedy is to use the `-g` option to set the path to use as the root of Docker's runtime to the
directory the symlink points to. Looking at the file in boot2docker where the Docker daemon is
started ([/usr/local/etc/init.d/docker](https://github.com/boot2docker/boot2docker/blob/master/rootfs/rootfs/usr/local/etc/init.d/docker#L18)),
it seems like the `-g` option should already be provided. All the more curious and needs further
investigation.

## A `sed` to far

The culprit, it turns out, is the `Vagrantfile` used by dvm! In the [provisioning script](https://github.com/fnichol/dvm/blob/master/Vagrantfile#L89)
used to restart the Docker daemon with the args provided in `DOCKER_ARGS`, there's a `sed` command which
looks like:

    sed -i -e 's|docker -d .* $EXPOSE_ALL|docker -d #{args}|' $INITD

where: 

* `$INITD` is `/usr/local/etc/init.d/docker`
* `$EXPOSE_ALL` is a variable set in that file
* `#{args}` is the string interpolation of the `args` defined in `DOCKER_ARGS`.

This command is only run if you modify either `args` or `DOCKER_ARGS`, which is how I believe
it slipped through the cracks.

What happens is the part of the line in `/usr/local/etc/init.d/docker` that is specifying the `-g`
option is wiped out when the new `args` are added to the command. There were a few options to
solving this:

1. Update the README to state that if you modify `DOCKER_ARGS`, you must make sure to include
the `-g` option
2. Update the `Vagrantfile` to check if `DOCKER_ARGS` is modified, and if so, to check that it
includes the `-g` option, adding it if it does not.
3. Update the `sed` command to save the `-g` option and use a backreference to it.

I chose to implement option 3 as it seemed the simplest and most transparent way to accomplish
the goal.

## One last run

With the changes in place, it was time to test them out again. I created a new `dvm` VM with the
updated `Vagrantfile` and a `dvm.conf` specifying `DOCKER0_CIDR`. Once that was created, I checked
to make sure the `docker0` bridge had the new IP range. So far, so good, so I ran `kitchen create`
and watched as Test Kitchen pulled in the Ubuntu repo and created a container for me. Still looking
good, but all of this did work before. I enter `kitchen destroy` for the final test and...

    |ruby-1.9.3-p448| isaacs-air in ~/git/yacn/dvm/tmp
    ± |fix/unable-to-stop-delete-containers ✗| → kitchen destroy
    -----> Starting Kitchen (v1.2.1)
    -----> Destroying <default-ubuntu>...
    2118a87d5cf2f5d6bc502a3f086b4ab828e6c3023624e2da9e8590eae043ec1f
    2118a87d5cf2f5d6bc502a3f086b4ab828e6c3023624e2da9e8590eae043ec1f
    Finished destroying <default-ubuntu> (0m0.14s).
    -----> Kitchen is finished. (0m0.20s)
     
    |ruby-1.9.3-p448| isaacs-air in ~/git/yacn/dvm/tmp
    ± |fix/unable-to-stop-delete-containers ✗| → kitchen list
    Instance Driver Provisioner Last Action
    default-ubuntu Docker ChefZero <Not Created>

WooHoo! It works! 

## Conclusion

To summarize, I needed to update dvm to allow me to set the IP range of the `docker0` bridge.
This required me to install bridge-utils for `brctl` to remove the previously created `docker0`
bridge before my new IP range could take effect. Once that was implemented, I discovered a bug
in the provisioning script for dvm causing which prevented stopping or deleting containers. Both
fixes are currently open as pull requests:

* [sed fix](https://github.com/fnichol/dvm/pull/21)
* [Setting docker0 CIDR range](https://github.com/fnichol/dvm/pull/22)
