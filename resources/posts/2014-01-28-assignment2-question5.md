---
title: Assignment 2 Question 5
tags: assignment2
template: default.clj
---

# Assignment 2 Question 5

[Mind map for this post](http://ccs.neu.edu/home/iboehman/oomm.png)

## Offshore Outsourcing of Software Development: Does It Really Reduce Software Development Costs?

My initial reaction to such a headline is [Betteridge's law of headlines](https://en.wikipedia.org/wiki/Betteridge%27s_law_of_headlines),
that is:

> Any headline which ends in a question mark can be answered by the word *no*

However, instead of simply dismissing the question, I decided to think it through a little
more.

## The Good

The only benefit I can think of for offshore outsourcing is how much cheaper it is than paying
the salaries of software developers, or even the price of an onshore solution. However, I would
always look into both of those alternatives first before thinking about looking into offshore
outsourcing, and only if it were my last resort would I choose it. It just strikes me as
"wrong", which is completely and totally subjective, but is due to my bias as a software developer.

The only use case I can think of would be if I were a brand new startup with limited capital and I
needed to get some [yak shaving](http://projects.csail.mit.edu/gsb/old-archive/gsb-archive/gsb2000-02-11.html)
done to help me reach a target date. Even then, I would be hesitant.

### The Bad

One of the many reasons against offshore outsourcing is that since it is offshore (I mean, it's in
the name), and since you're outsourcing (and thus, are exporting your work to a country with cheaper
labor) there is a very high likelyhood that the native language of the chosen country is not English.

Now, the degree to which this is detrimental can vary a bit. For example, if you were to say, outsource
to an Eastern European country, their native language may not be English, but it is more likely that they
will have a professional working proficiency of the language. This is not necessarily the case if you were
to outsource to a place such as India.

Another thing to take into consideration is that you will eventually have to maintain the code you're
outsourcing, unless you're simply outsourcing your entire product. At that point, is it even your product
anymore? Ideas are are a dime a dozen, it's the implementation that's difficult and reaps the most rewards.

If you are going to maintain the code later on, you'll be paying some developer to go through and clean
up the code and add features. This could take considerably more time and effort depending on the quality of
the code base that the developer has to work with, and you'll have to cross your fingers that things such as
variables, functions, classes, etcetera are named in English and not in the native tongue of the outsourced
developer that originally wrote it.

## The Ugly

Finally, we have the "ugly" reasons against offshore outsourcing. For one, there's always the question of the
time difference. Like was stated before when discussing language barriers, it all depends on which country you
end up using. For example, if you're located in San Francisco (UTC -5:00) and you choose to use a company located
in Mumbai, India (UTC +5:30), the time difference will be 10.5 hours. This means that you'll have to 
have any real-time discussions, via telephone, Skype, etc, either very early in the morning or very late in the
evening. If you choose a company located in Bucharest, Romania (UTC +2:00), the time difference will be "just"
8 hours, which is better, but still not anywhere close to being ideal for real-time communication. Which leads
me to my point, any sort of non-trivial time difference will significantly slow development when any sort of
clarification of requirements (or clarification of anything really) is required as you will either need to
communicate via a slower method such as email, or you will need to wake up very early or stay very late to
communicate in real-time. Both of these add significant delays to your project which add up over time to non-trivial
costs of time, resources, and money.

One last "ugly" concern of offshore outsourcing is that you really need to be certain to pay careful attention
to the company's reputation when selecting one for outsourcing and you should be certain that the code you
receive is free of vulnerabilities and back-doors. If your company does not have the domain knowledge necessary 
to do so, you will need to hire yet *another* company to audit the code for you. Now you could simply choose to
not care about your users' security, but does that really sound like a way to win and keep customers? I for one
would be very hesitant about supporting such a company.

## Conclusion

In the end, I believe that the potential savings posed by offshore outsourcing are not truly savings, but are
simply deferring the cost of development to a later point in time. Now, depending on the resources of a company,
this could be an advantageous move, say if you were a startup with limited resources that just needs a little
push to get going. However, if you have the resources to outsource the project onshore or even to hire developers,
I believe there are few good reasons to choose offshore outsourcing over the other alternatives which have been
presented.

In conclusion, Betteridge's law was right once again:

> Any headline which ends in a question mark can be answered by the word *no*
