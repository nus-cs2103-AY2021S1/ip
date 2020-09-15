# User Guide

## Features 

### Feature 1 
Add tasks with specified deadline time

## Usage
You will always remember the deadline for every task

### `deadline` - Set a task with a specific deadline

Example of usage: 

`deadline cs2103 project /by 2020-09-20 2359`

Expected outcome:

`Got it. I've added this task: [D][X] cs2103 project (by: Sep 20 2020 2359)`

##

### Feature 2 
Add tasks with specified start time

## Usage
You will always remember the starting time of a certain event

### `event` - Set a task with a specific starting time

Example of usage: 

`event practice session /at 2020-09-20 2359`

Expected outcome:

`Got it. I've added this task: [E][X] cs2103 project (at: Sep 20 2020 2359)`

##

### Feature 3 
Add things to do on that day

## Usage
You will always remember what do you need to do

### `todo` - Set a task

Example of usage: 

`todo sleep`

Expected outcome:

`Got it. I've added this task: [T][X] sleep`

##

### Feature 4 
List out all your current task

## Usage
You will always remember what are the things you need to do

### `list` - Give a list of your current tasks

Example of usage: 

`list`

Expected outcome:

`Here are the tasks in your list: 1. [T][X] sleep`


##

### Feature 5 
Set your specified task to done

## Usage
You will be able to set your task to done

### `done` - Set a task to be done

Example of usage: 

Here are the tasks in your list: 
1. [T][X] sleep

`done 1`

Expected outcome:

`Nice! I've marked this task as done: [T][V] sleep`

##

### Feature 6 
Set your specified task to be deleted

## Usage
You will be able to delete your specified task

### `delete` - Delete a specified task

Example of usage: 

Here are the tasks in your list: 
1. [T][V] sleep

`delete 1`

Expected outcome:

`Noted. I've removed this task: 
   [T][V] sleep
 Now you have 2 tasks in the list.`

##

### Feature 7 
To end your conversation with the bot

## Usage
You can save your current task list to data base

### `bye` - Save all tasks in your current task list to database

Example of usage: 

`bye`

Expected outcome:

`Bye. Hope to see you again soon!`
 
 ##
 
 ### Feature 8 
 To look for tasks that are related to your desired keyword
 
 ## Usage
 You will be able to look after some tasks that are related to you desired keyword
 
 ### `find` - Find some tasks that are related to a keyword
 
 Example of usage: 
 
 Here are the tasks in your list:
 1. [D][X] cs2103 project (by: Jun 26 2020 2359)
 2. [T][X] sleep
 3. [D][X] cs2103 tut (by: Sep 26 2020 2359)
 
 `find cs2103`
 
 Expected outcome:
 
 `Here are the matching tasks in your list:
  1. [D][X] cs2103 project (by: Jun 26 2020 2359)
  2. [D][X] cs2103 tut (by: Sep 26 2020 2359)`
