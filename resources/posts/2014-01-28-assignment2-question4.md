---
title: Assignment 2 Question 4
template: default.clj
tags: assignment2
---

# Assignment 2 Question 4

## Question 4

>A conversation with several managers in the order processing department has revealed that
>about 1 out of every 30 orders for conference phone setups is unusual. How many previous
>orders should you inspect if you want to have a 95% level of certainty that you covered
>all variations? What criteria would you use to select these orders? Be sure to show your
>work.

### Solution

* Probability of unusual order (p) = 1/30
* Acceptable level of error (a) = 1-.95 <==> .05
* Certaintiy level (z-score, z) = 1.96

Sample Size Formula: p(1-p)((z/a)^2)

Substituting above values: (1/30)(29/30)((1.96/.05)^2) = 49.513955556

From the above calculation, you should inspect 50 orders, ensuring that they were
randomly selected for inspection.
