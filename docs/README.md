# User Guide

## Features 
- delete a task: delete
- add a todo: todo
- add a deadline: deadline
- add an event: event
- mark task as done: done
- find a task: find
- display all tasks: list
- sort all tasks: sort
- exit duke application: bye

## Usage
### Add Tasks  
Adds a task into the list.

### Command: `todo` 

Adds a todo into the list.

Example of usage: 

`todo homework`

Expected outcome:

`Got it I've added this task:`

`[T][X] homework`

`Now you have 1 tasks in the list.`

### Command: `deadline` 

Adds a deadline into the list.

Example of usage: 

`deadline assignment /by 2020-03-04`

Expected outcome:

`Got it I've added this task:`

`[D][X] assignment (by: Mar 4 2020)`

`Now you have 2 tasks in the list.`

### Command: `event` 

Adds an event into the list.

Example of usage: 

`event meeting /at 2020-01-01`

Expected outcome:

`Got it I've added this task:`

`[E][X] meeting (at: Jan 1 2020)`

`Now you have 3 tasks in the list.`

### Delete Tasks  
Deletes a task from the list.
### Command: `delete` 

Example of usage: 

`delete 2`

Expected outcome:

`Noted. I've removed this task:`

`[D][X] assignment (by: Mar 4 2020)`

`Now you have 2 tasks in the list.`

### Find Tasks  
Finds the tasks from the list which match the user input.

### Command: `find` 

Example of usage: 

`find meeting`

Expected outcome:

`Here are the matching tasks in your list:`

`[E][X] meeting (at: Jan 1 2020)`

### Sort Tasks  
Sorts all the tasks in the list according to date.

### Command: `sort` 

Example of usage: 

`sort`

Expected outcome:

`Your list is now sorted!`

`Here are the tasks in your list:`

`[E][X] meeting (at: Jan 1 2020)`

`[E][X] project pitch (at: Feb 2 2020)`

### List Tasks  
List all the tasks in the task list.

### Command: `list` 

Example of usage: 

`list`

Expected outcome:

`Here are the tasks in your list:`

`[T][X] homework`

`[E][X] meeting (at: Jan 1 2020)`

### Mark Tasks As Done
Marks a task in the task list as done.

### Command: `done` 

Example of usage: 

`done 2`

Expected outcome:

`Nice! I've marked this task as done:`

`[E][✓] meeting (at: Jan 1 2020)`

### Exit Duke Application
Exits the application.

### Command: `bye` 

Example of usage: 

`bye`