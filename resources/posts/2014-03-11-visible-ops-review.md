---
title: "The Visible Ops Handbook" Review
template: default.clj
tags: is3500blog
---

# "The Visible Ops Handbook" Review

My boss was kind enough to lend me his copy of [The Visible Ops Handbook](http://www.amazon.com/The-Visible-Ops-Handbook-Implementing/dp/0975568612/)
to read over Spring Break. I'd seen the book recommended many times, so when
I saw the distinctive cover on his desk I had to ask about it. My initial reaction
was that it was much thinner than I ever thought it would be, I was used to big
compsci books and this is certainly not like those. However, short as it may be,
there's definitely a lot of really great information packed in there. The book is only
about 80 pages, so I was able to finish it on the flight to Indiana for Spring Break.
The handbook contains a series of four steps detailing how you can transform your
organization into a higher performing one. The four steps are:

1. Stabalize the Patient (Modify First Response)
2. Catch and Release (Find Fragile Artifacts)
3. Establish Repeatable Build Library
4. Enable Continuous Improvement

## Stabalize the Patient

This step mainly concerns with stabalizing the organization's infrastructure ("the patient").
The biggest point they hit upon is that 80% of outages are caused by the organization themselves.
To rememdy this, they focus on change management and control. Tripwire (software which detects
changes to specified files) was heavily mentioned here, though that is because one of the
authors (Gene Kim) founded Tripwire. They of course mention that alternatives exist,
but none are discussed as in-depth (relatively speaking, as in, named) as Tripwire.

## Catch and Release

This step is a response to a fragile infrastructure, that is, infrastructure that has been
created and configured by hand, meaning it is not able to repeatably created and destroyed
identically. An inventory must be done of all assets, configurations, and services to find
the those which have the lowest successful change rate, the highest MTTR, and the highest
downtime costs. Once these assets have been enumerated, the authors suggest that they be
"frozen", meaning no changes are applied to them without good reason. Now
that the infrastructure has been inventoried, the organization can focus on creating
a provsioning process such that they have identical, repeatable infrastructure that
can be easily replicated, which nicely leads into the next step.

## Establish Repeatable Build Library

This step focuses on implementing an effective release management process by creating
repeatable builds for the most important assets, with the end goal being to make
it cheaper to rebuild than repair a given server (or, as my boss likes to put it,
treating infrastructure like cattle, when one gets sick, just take it out back and
shoot it and bring the next one in). 

## Enable Continuous Improvement

The previous steps have focused on building a tight loop of "Release=>Control=>Resolution".
This step focuses on implementing metrics surrounding those process areas, because, as
H. James Harrington said:

> Measurement is the first step that leads to control and eventually to improvement. If
> you can't measure something, you can't understand it. If you can't understand it, you
> can't control it. If you can't control it, you can't improve it.

[Source](http://www.goodreads.com/quotes/632992-measurement-is-the-first-step-that-leads-to-control-and)

## Conclusion

I found the handbook to be a fast, concise read. I believe that any Operations Engineer/
System Administrator/etc should read it as it contains many valuable insights into increasing
your organizations performance. Highly recommended.
