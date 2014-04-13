---
title: "The Corruption of Agile" Response
template: default.clj
tags: is3500blog
---

# "The Corruption of Agile" Response

[The Corruption of Agile](http://www.drdobbs.com/architecture-and-design/the-corruption-of-agile/240166698)
is (yet another) article detailing how the agile movement has deviated from a set of values to a set of
practices, with a pseudo-religious tone surrounding said practices. The article argues that this shift from
values to practices was heavily influenced by the vast number of "Agile consultants", who took the set
of values stated in the Agile manifesto and translated them into rote programming practices, which the
article argues loses the original values. In the end, the article argues that whether or not a company
can be called agile depends on the culture of said company and not the practices the company uses. Now,
I do agree with this point, as I have heard of many companies simply wanting to "buy agile", i.e. pay
for a consultant to come in, run a few seminars, take their check, and leave. Tada, now we're agile!
Now lets keep doing everything the same way as before, but now we're "agile". Right? Wrong. The article's
conclusion is simply a prettier way of saying "if you want to call yourself agile, you need to put up or
shut up".

One point that struct a chord with me while reading was the author's constant comparison of TDD to some
sort of religious movement. While they do have a point when they're refering to the fanatics, i.e. those who
see any other way of doing things as incorrect, I don't believe that this should discount the entire
methodology. In a [follow up](http://www.drdobbs.com/architecture-and-design/addressing-the-corruption-of-agile/240166890),
the author admits that the test coverage provided by TDD is valuable, though they argue that the same benefit
could be reaped if tests are written after the fact. I do not disagree with this statement, the most important
part of test-driven development is the resulting tests that you use to validate your code works correctly. However,
I do believe that by following TDD practices writing tests is easier, simply because you're writing your tests
while also writing your code, meaning you have the idea of what you want this function to do already in your
head. When writing tests first, I've found it much easier to understand the full scope of the problem being
worked on, which often leads to me implementing a better solution overall. In the end, as long as the test
coverages are equal, it really doesn't matter how you get there, as long as you get there, I just find TDD
makes it easier to get there.
