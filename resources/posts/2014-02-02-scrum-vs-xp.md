---
title: Agile Methodologies: Scrum vs Extreme Programming
template: default.clj
tags: is3500blog
---

# Agile Methodologies: Scrum vs Extreme Programming

Over the past few weeks, I've seen and read a number of articles detailing
"Why Agile Has Failed", "Agile Is Dead", etc. One thing I'd noticed while
reading one of the articles was that Extreme Programming (XP) tended to
crop up in discussions surrounding these articles on Hacker News. I'd heard
the term before and had briefly looked into it before (i.e. reading its Wikipedia
article), but I didn't know what exactly it entailed or how it compared to
Scrum, which I am famililar with through working at BitSight. I decided it was
time to figure out what exactly XP was, so I set off to Martin Fowler's
[Bliki](http://martinfowler.com/bliki/) and started reading the essay
[The New Methodology](http://martinfowler.com/articles/newMethodology.html).

## Extreme Programming

XP has been around for quite a while. It has its roots in the Smalltalk community,
where it was being formed and thought up during the late 1980's. However, it wasn't
until the Chrysler Comprehensive Compensation project (a project to replace the company's
aging COBOL payroll systems) that the practice started to gain any sort of traction.
In fact, it wasn't until around 1997 that the term "Extreme Programming" even came into
use. In the end, the C3 project was eventually canceled, but the ball had been set rolling
and XP had enough inertia from the trail previously blazed to continue on as a respected
methodology.

Anyways, that's enough history. XP is based on 5 values (Communication, Simplicity, Feedback,
Courage, and Respect), which form the basis for its collection of principles, which then
form the basis for its collection of practices. The 5 values represent the fundamental knowledge
and understanding underpinning this approach to software development. The practices are then
concrete, implementable, day-to-day versions of these values. The principles bridge the large
gap between the values and practices. Each of these parts is necessary, as practices without
values serve no purpose at all while values without practices are very ambiguous and hard to apply
as there are so many ways to accomplish it. One thing that is strongly emphasised throughout
development is testing. It doesn't strictly suggest test-driven development, but doing so is highly
recommended.

## Scrum

Scrum also has its roots in the late 1980's and early 1990's in the object-oriented programming
community. However, Scrum concentrates on the project management aspect of software development,
defining thing such as "sprints" (30 day development iterations) and daily standups (short meetings
focusing on what was accomplished yesterday and what is hoped to be accomplished today). There's not
much emphasis on the engineering practices. In fact, many people combine Scrum's project management
with XP's engineering practices, as XP's management practices are quite similar to Scrum's.

## Conclusion

In the end, I discovered that Extreme Programming and Scrum, while different, are still two different
agile methodologies, meaning they do share a lot in common. In fact, they synergize quite well, leading
many people to choose Scrum for project management while using the engineering practices detailed by
XP.

