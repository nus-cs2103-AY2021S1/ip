# User Guide

## Introduction
Duke is a Task Manager application which helps you to organize and set up your tasks, events and deadlines.

## Features 
Overview of available features:  
    • `list`  
    • `todo`  
    • `deadline`  
    • `event`  
    • `delete`  
    • `done`  
    • `find`  
    • `sort`  
    • `bye`  
### Feature 1: View the task list
View all the tasks in your current task list: `list` 

Format: `list`  

Example: `list`  

Expected example outcome:  
```
Quack! Your list is here:
    1.[T][✓] play dote (Priority: low)
    2.[T][✘] plan for date (Priority: high)
    3.[D][✘] CS2100 assignment (by: Sep 20 2020) (Priority: low)
    4.[T][✘] laundry (Priority: low)
```

### Feature 2: Add a todo task
Add a todo task to the task list: `todo`  

Format: `todo [description]`  

Example: `todo do CS2103T iP increment`  

Expected example outcome:
```
Quack Quack Quack. I've added this task: 
    [T][✘] do CS2103T increment (Priority: low)
Master, you now have 5 items in the list! Quack! Quack!
```

### Feature 2: Add a deadline task
Add a deadline task to the task list: `deadline`  

Format: `deadline [description] /by [date]` or `deadline [description] /by [date] [time]`  

Example: `deadline CS2100 assignment /by 2020-09-18 23:59`  

Remarks:
	• 

Expected example outcome:  
```
Quack Quack Quack. I've added this task: 
    [D][✘] CS2100 assignment  (by: Sep 18 2020 23:59) (Priority: low)
Master, you now have 6 items in the list! Quack! Quack!
```


Description of feature.

## Usage

### `Keyword` - Describe action

Describe action and its outcome.

Example of usage: 

`keyword (optional arguments)`

Expected outcome:

`outcome`
