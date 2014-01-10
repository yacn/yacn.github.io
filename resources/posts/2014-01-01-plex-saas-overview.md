---
template: default.clj
title: Automated Media Server Overview
tags: plex media.server personal
---
## Automated Media Server Overview

In this inagural post, I will give a general overview of how I structured
the infrastructure for my media server. First, a brief overview of my infrastructure.
The systems below marked with `VM` are virtual machines hosted on an ESXi box:

* Windows Server 2012 `VM`
    - Runs Plex Media Server and serves DNS requests for the network
* Ubuntu 12.04 `VM`
    - Runs Sick Beard, CouchPotato, and r(u)torrent
* Synology DS413j
    - This is my NAS, holding 4x 3TB drives formatted with Synology Hybrid Raid
      for 1 drive redundancy

### System Overviews
#### Windows Server 2012 `VM`

This box runs Plex Media Server (PMS), which organizes and fetches metadata for
all of my media automatically. I chose Windows Server to host this service since
at the time, PMS for Linux was beta software and there was no Silverlight support
for Linux, meaning I would not be able to run the Netflix channel. This may have
changed since then, but I have not looked into it recently. Now, PMS for Linux
is out of beta and the Netflix channel has been broken for ages, which I never
used anyways because my Roku has a native channel for Netflix. Because of these
developments, I am looking into switching back to an all Ubuntu setup as I am a
Linux guy at heart, and it will make my future plans easier to implement.

Additionally, this VM is running the Windows DNS server. I decied to use this
over a traditional solution like BIND or dnsmasq mainly due to ease of use. Once
I migrate PMS to Ubuntu, I will setup BIND or something similar as there would
be no point in running an entire Windows VM just for DNS.

#### Ubuntu 12.04 `VM`

This box takes care of the grunt work of the operation. It runs rtorrent,
rutorrent, Sick Beard, CouchPotato, and Subsonic. This medly of software takes
care of downloading (r(u)torrent), renaming and moving (Sick Beard and CouchPotato),
and serving music (Subsonic).

[Sick Beard](http://sickbeard.com) is a Python webapp built on CherryPy which
searches for and manages all of the TV shows I watch.
[CouchPotato](https://couchpota.to) is a similar webapp also written in Python
and based on Tornado, but for movies instead of TV.

[rTorrent](http://libtorrent.rakshasa.no) is an ncurses based torrent client I
run as a daemon in the background using GNU screen.
[ruTorrent](https://code.google.com/p/rutorrent/) is a web-frontend to rTorrent
built in PHP that can be extended via plugins. I use several, such as
[Ratio](https://code.google.com/p/rutorrent/wiki/PluginRatio),
[AutoTools](https://code.google.com/p/rutorrent/wiki/PluginAutotools), and
[Scheduler](https://code.google.com/p/rutorrent/wiki/PluginScheduler) to enhance
and improve my workflow. The workflow itself is a tad complex, so it will be
discussed in a later post.

[Subsonic](http://subsonic.org) is a free web-based media streamer which allows
me to stream my music collection from anywhere.

#### Synology DS413j

All of the systems listed above are accessing, using, and manipulating a lot of
data, but none of it, except for software-related files (such as the SQLite dbs
used by Plex, Sick Beard, and CouchPotato), is actually stored on those systems.
Each of them are mounting shared folders which are located on this NAS. I have
Synology Hybrid Raid setup to allow me to add bigger drives incrementally in the
as well as giving myself a 1-drive redundancy safety net. Using the software on
the Synology itself, I am able to share these folders over my network via NFS,
CIFS, and AFP.

### Plans for the Future

* Migrate to PMS on Ubuntu
* Migrate DNS server to BIND or something similar
* Implement Chef to automate provisioning of these systems
* Move from ESXi to OpenStack
* Implement Sensu or Riemann to monitor the above servers and apps
