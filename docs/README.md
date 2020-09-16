# Nugget User Guide

Nugget is a task manager bot with a dog theme. 

## Features
### 1. Adding Tasks
#### a) Add a Todo
Command: `todo {task details}`

Example: `todo wash rice`

Expect output: 

    I've added this task: 
    [T][✘] wash rice
    Now you have 1 task(s) in your list.
    
#### b) Add an Event
Command: `event {event details} /at {date in YYYY-MM-DD}`

Example: `event birthday /at 2020-06-29`

Expect output:

    I've added this task: 
    [E][✘] birthday (at: Jun 29 2020)
    Now you have 2 task(s) in your list.
    
#### c) Add a Deadline
Command: `deadline {deadline details} /by {date in YYYY-MM-DD`

Example: `deadline iP submission /by 2020-09-18`

Expect output:
    
    I've added this task: 
    [D][✘] iP submission (by: Sep 18 2020)
    Now you have 3 task(s) in your list.
    
### 2. Add priority - Adds a priority level to a specified task
Add the "/p" tag behind a task if you want to add a priority level to it
Command: {task} /p 1

Example: `event graduation /at 2021-07-01 /p 1`

Expect output:

    I've added this task: 
    [E][✘] graduation (at: Jul 1 2021) (priority: 1)
    Now you have 4 task(s) in your list.

### 3. Help - View the list of commands available
Command: `help`

Expect output:

    "Here are the list of commands you can type:
     1. list - Shows the complete list of tasks you have
     2. find <keyword> - Shows tasks in your task list that match the keyword
     3. done <task number> - Marks the task as completed
     4. delete <task number> - Deletes the task from your list
     5. todo <task>- Adds a todo task
     6. event <event details> /at <date in YYYY-MM-DD> - Adds an event
     7. deadline <deadline details> /by <date in YYYY-MM-DD> -  Adds a deadline
     
     Optional: Adding a '/p <priority level>' tag at the end of a task command
     adds a priority level to your task!\n The default priority level is 0.
     
### 4. List tasks - View all the tasks in your task list in decreasing priority
Command: `list`

Expect output:

    Here is your to-do list:
    1. [E][✘] graduation (at: Jul 1 2021) (priority: 1)
    2. [T][✘] wash rice
    3. [E][✘] birthday (at: Jun 29 2020)
    4. [D][✘] iP submission (by: Sep 18 2020)
    
### 5. Find task - View all the tasks in your list that contain the keyword
Command: `find {keyword}`

Example: `find rice`

Expect output:

    Here are the matching tasks in your list:
    1.[T][✘] wash rice
    
### 6. Complete task - Mark a task as completed
Command: `done {task number}`

Example: `done 2`

Expect output:

    You've completed this task:
    [T][✓] wash rice
    
### 7. Delete task - Deletes a task from your task list
Command: `delete {task number}`

Example: `delete 4`

Expect output:

    Ok, this task has been kicked off your to-do list:
    [D][✘] iP submission (by: Sep 18 2020)
    Now you have 3 task(s) in your list.