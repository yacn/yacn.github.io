---
title: Requirements Elicitation Experience: piperun
template: default.clj
tags: is3500blog
---

# Requirements Elicitation Experience: piperun

Near the end of my first co-op at [BitSight](https://www.bitsighttech.com/), my
manager had one last project for me: create a tool to generate the necessary
configurations to aggregate data from an arbitrary date range. With this in mind,
the first thing I set about doing was gathering all the expected functionality from
the people I knew who would end up using it the most (namely, the QA and Data Science
departments). I didn't realize it at the time, but I was performing requirement elicitation.
The main method I used was brainstorming through a Google Doc which consisted of various
user stories ("As a BitSight engineer ...") for the various features requested.

Once I'd gathered enough requirements, I started putting together the MVP (minimum viable
product), focusing most of my energy on generating the necessary datemask for the given
date range. Unfortunately, I made a rookie mistake of rushing myself. I had heard a handful
of times that the tool was needed because people were wanting to start using it, so I made
the decision to write it in Ruby, the language I consider myself most proficient in. This was
a mistake because my manager had specified the non-functional requirement that the tool must
be written in Python as it would be maintained by the Engineering team and Python is the
lingua franca around here. Luckily, most of my effort had been concentrated on developing the
algorithm for datemask generation and it was trivial to port the work from Ruby to Python.
Additionally, by writing my tests first (particularly for the datemask generation), it gave
me peace of mind that the tool still performed as expected. As it turns out, this saved my
skin! When testing out the tool with one of our Hadoop clusters, I found out I was generating
the datemasks in the incorrect format (it was expecting ranges such as `[0-9]` instead of
bracket expansion ranges `{0..9}`). I converted the tests to the new format, verified they
all now failed, and then refactored the code to the new format, and verified all the tests
passed! All said and done, the process took 5 minutes and definitely helped cement in my
mind the importance of a thorough test suite.

In the end, the tool was released and continues to be used to this day!
