---
title: Assignment 1 Question 6
tags: is3500blog assignment1
template: default.clj
---

# Implementation Strategies for Shopping Assistant

I propose for the Shopping Assistant an iterative implementation
style as it would be extremely helpful to have a short feedback
loop between iterations of the app. Features could be implemented
incrementally and ensure that they function as intended before
moving on to the next incremental feature.

I would propose something either cloud-based, meaning HTML5 and
JavaScript, or something client/server based. The benefit of
cloud-based is the cost of porting to other platforms, or rather,
that there is no cost. Since HTML5 and JavaScript support are
standard on mobile devices now, it means theres only one codebase
to maintain for the app.

The benefit of client/server based is that the app would be a
native app on the target platform, which can lead to a much more
cohesive user experience. The downside is that if you want to
support more than one platform, you must maintain separate codebases
for each.
