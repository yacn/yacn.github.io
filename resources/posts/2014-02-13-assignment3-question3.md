---
title: Assignment 3 Question 3
template: default.clj
tags: assignment3
---

# Assignment 3 Question 3

## As-Is Use Case Model of Blackboard LMS

### Actors

* Instructor
* Student

### Use Cases

1. Instructor grades assignment
2. Student submits assignment
3. Student views grades
4. Instructor posts class materials
5. Student takes quiz

### UML Use Case Diagram

![Use Case Diagram](http://ccs.neu.edu/home/iboehman/a3ucm.png)

### "Submit Assignment" Use Case Narrative from "System As-Is" Perspective

#### Pre-Conditions

* Student must be logged in
* Student has navigated to the course page
* Assignment is open
* Student has not submitted before or assignment allows multiple submissions

1. *The use case starts* when the student clicks the "Assignment"
navigational link.
2. Student clicks name of assignment {V1, V2}
3. Student adds completed assignment to submission {V3, V4}
4. Student adds any comments for submission
5. Student clicks submit

#### Variations

* V1: Assignment is closed
    1. System prompts student with message saying assignment is not active
    2. Student may now navigate away from the page
* V2: Student has submitted previously and multiple submissions not allowed
    1. System prompts student that they have already submitted for this assignment
    2. Student may now navigate away from the page
* V3: Student submits text submission
    1. Student clicks "Write Submission"
    2. Student writes in the presented box
* V4: Student attaches file for submission
    1. Student clicks "Browse My Computer"
    2. Student selects the correct file(s) in the pop-up window and clicks "OK"

### Use Case Rankings

|           Use Case           | Importance to Users | Architectural Impact | Frequence of Use |
| ---------------------------- | ------------------- | -------------------- | ---------------- |
| Instructor Grades Assignment |          13         |           2          |      13          |
| Student Submits Assignment   |          13         |           2          |      13          |
| Student Views Grades         |          13         |           5          |      13          |
| Instructor Posts Materials   |          8          |           5          |       5          |
| Student Takes Quiz           |          3          |           8          |       5          |
