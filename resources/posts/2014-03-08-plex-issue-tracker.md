---
title: Plex Issue Tracker and Custom Icon Font
tags: plex media.server personal font.awesome vector.graphics
template: default.clj
---

# Plex Issue Tracker

I've been hosting a Plex Media Server and sharing it with friends for almost
a year now (started once I moved off campus and my internet allowed it) and in
that time there's been a few bumps in the road, but overall it has been running
quite smoothly. There's been the occasional glitch, e.g the Plex Media Scanner
stops running (meaning no new shows are detected) necessitating a restart of the
Plex service or the Plex service itself stops working and needs a restart. These
issues are minor and take minimal effort to fix as I simply need to RDP in and
restart the service. However, there's the occasional issue which requires a bit
more manual intervention, such as shows having improper metadata. Since I'm not
the only user of the server, there's actually a number of shows present that I
do not keep up with at all. What ends up happening is I'll be hanging out with
one of my friends and they'll mention some issue they're having with Plex. I then
make a mental note to remember to fix it when I return home. Being the human I am,
I don't have perfect memory, which means things sometimes slip through the cracks.

With that in mind, I decided to create a place to file support requests for GUNTER,
my Plex server. I created a GitHub repository [gunter-issues](https://github.com/yacn/gunter-issues)
to hold any support requests / issues related to Plex or GUNTER. Using the free service
[waffle.io](https://waffle.io), I was able to turn that repo into an [open and online
agile/kanban board](https://waffle.io/yacn/gunter-issues). Now, people can file issues
with the knowledge that if I forget about it, it's right there on the board. It is my
hope that by introducing this, GUNTER/Plex can only improve.

### Plex Info Page and Icon Fonts

While integrating the issue tracker into this site, I wanted to add a link to the a page
containing information and resources about Plex. Since it was going to be in the upper-right
corner, I wanted it to fit in with the [Font Awesome](fontawesome.github.io/Font-Awesome/)
icons I was already using. I came up with the idea of putting together a version of the Plex
Media Center logo in the style of the Font Awesome icons.

I started by finding the PMS logo and then modified its coloring to fit in with the Font Awesome
icons. I ended up with a PNG with transparency, but then realized that this wouldn't scale well 
if I wanted to use it at a bigger size. I decided at that point to convert it to an SVG.
Unfortunately while the software I used to modify the image ([Pixelmator](http://www.pixelmator.com/))
supports creating vector-like graphics, it does not support exporting to SVGs.
However, I was able to use a piece of software called [Vector Magic](http://vectormagic.com/desktop)
to take the PSD exported by Pixelmator and feed it in and with about 5 clicks, I had an SVG!

I then spent quite a bit of time futzing around with the CSS sizing and positioning, trying to
get the SVG to appear, but nothing seemed to work. Frustrated and out of ideas, I googled 
"create own font awesome icons svg" and stumbled upon this awesome RubyGem called
[Font Custom](http://fontcustom.com), which takes a directory of SVG files and converts them
to a CSS file that you can include on your sites. With this in hand, I was able to convert my
SVG into a font and then it was a simple matter of adding an item to the navigation list like
all the other links! In the end, I think it looks and feels like a native Font Awesome icon
and was well worth the effort to get it going.
