---
title: Test-driven Development Experiences
template: default.clj
tags: is3500blog
---

# Test-driven Development Experiences

I first became aware of [test-driven development](https://en.wikipedia.org/wiki/Test_driven_development)
while taking Fundamentals of Computer Science (colloquially known as "Fundies") at Northeastern
University. Fundies used [Racket](http://racket-lang.org) along with several 
[Domain-specific languages](https://en.wikipedia.org/wiki/Domain_specific_language) (such as Beginner
Student Language, Intermediate Student Language, etc) to teach the building blocks for all of our future
classes. One of the first things that was instilled into us was "test-first development", meaning
that we should be writing our tests before we write our code. This concept was introduced to us
via `(check-expect ...)`, which allowed us to _check_ that the output of a given function
with the given parameters matches what we _expect_ it to. Unfortunately for myself, I saw
this as an additional burden on top of the homework assignments and would usually save
writing all the tests until after I had already completed everything else. This pattern
continued with me to Fundies II, where we learned more fundamentals along with the
basics of object-oriented programming, this time using Java instead of Racket. We used
their special libraries to continue writing tests in the style of `(check-expect ...)`
(though with a more Java-like syntax), though towards the end of the semester we switched
over to writing [JUnit](https://www.junit.org)-style tests. It wasn't until my first co-op
at [BitSight](https://www.bitsighttech.com/) did I realize just how powerful of a concept
test-driven development really is. One of my first tasks was to test an internal tool using
[RSpec](http://rspec.info), meaning I had to go out and research all the tooling surrounding
as well as the process of writing "specs". This experience has stuck with me to this day.
The benefits of this are numerous (e.g. ease of refactoring), but the biggest one in my eyes
(through my Ops glasses) is that with a full featured test suite (which TDD helps to supply by
default as you write your tests while writing your code), you can iterate at a much faster
rate as you're able to continuously integrate features into your application as they're completed
because you can run the integration tests to verify that the new feature (or bug fix, etc) does
not break anything else. That is the true power in my eyes.
