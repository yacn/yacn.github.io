---
title: "Dia" Review
template: default.clj
tags: is3500blog
---

# "Dia" Review

## Overview

[Dia](https://wiki.gnome.org/Apps/Dia/) (pronounced "dee-a"), is FOSS tool used for
general-purpose diagramming which runs on all major platforms (\*nix, Windows, Mac OS X).

## Finding Dia

I found Dia when searching for a diagramming tool that I could run locally on my own
machine instead of needing internet access to use one of the various SaaS diagramming
tools, such as [Creatly](https://creately.com) or [Lucidchart](https://www.lucidchart.com).
Additionally, I wanted something that was free. This was both out of cheapness on my part
(I don't often diagram things so it seems like a waste of money to pay for something I won't
use) and due to frustration with the freemium SaaS offerings mentioned. The frustration stems
from various features of the services only being available to members who pay, such as the
ability to export your diagrams in a variety of formats or a limiting the set of shapes and objects
you can use in your diagrams and still be able to export it. Dia satisfies all of these requirements
and I have been using it for all of my diagramming needs since the end of February.

## User Experience

Dia's UX, compared to the UX offered by tools like Crealy, Lucidchart, Gliffy, or OmniGraffle,
is very rough. The application itself uses [GTK+](https://en.wikipedia.org/wiki/GTK%2B) to manage
it's UI, which means the developers only need to write their UI for GTK+, which then handles displaying
the UI on the variety of platforms it runs on. The downside to this approach is that the interface doesn't
look native on any platform and doesn't integrate well with any (save for \*nix platforms). The slight upside
is that users only need to learn one interface, as it will be the same on any platform your application is
used on.

Anyways, as I use OS X, the UX was affected even more. While \*nix and Windows use the Control key to
anchor most of their keyboard shortcuts, OS X uses the Command key. Dia/GTK does not take this into
consideration, meaning I need to use the Control key for all keyboard shortcuts, which undermines all
the muscle memory developed for standard keyboard shortcuts. Fortunately, it is fairly simple for me to get
into the groove of using the Control key as I've used Windows for most of my life, but the initial
context-switch can be fairly jarring.

## Worth It?

The question you must be asking yourself now is, "Does Dia overcome it's rough UX or should I stick with
my current tool of choice?". The answer to this question is "It depends". If you are going to be doing
a lot of charting, I would definitely recommend going with a more commercial solution as it will be
better polished and offer more features. However, if you just need a tool to create the occasional
UML diagram for a class/homework assignment/one-off project/etc, I highly recommend Dia. The ability
to work on my diagrams while not tethered to an internet connection while also being able to export
my diagrams in any format I desire in addition to being completely [libre](https://en.wikipedia.org/wiki/Free_software)
outweighs the jarring UX for me as I feel the above features make it worth it.
