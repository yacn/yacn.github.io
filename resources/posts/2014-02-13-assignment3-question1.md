---
title: Assignment 3 Question 1
tags: assignment3
template: default.clj
---

# Assignment 3 Question 1

Using decision tables to model business rules is an easy way
to obtain visibility of all the possible states of your system.

For example, consider the following business rules:

> The sales and marketing team at Willard's has started to implement
> a VIP program in which different levels of status can be achieved
> depending on annual spending amounts. Depending on the level different
> discount vouchers are issued monthly. Specifically, they would like to
> have two levels of VIP status: Silver and Gold. Gold members receive a
> $100 voucher once they spend a over $2,000, while Silver members get a
> $50 voucher. If they spend less than $2,000 they get a voucher for half
> the amount. However, they must spend at least $500. 

You could model the above as a decision table like so:

![Decision Table](http://ccs.neu.edu/home/iboehman/dt.png)
