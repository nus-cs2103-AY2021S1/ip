# Duke - User Guide

## Introduction

A greenfield Individual project created for CS2103T-software engineering, Duke is a simple and easy-to-talk chatbot that promises to keep track of all kinds of tasks and supports task tracking with a variety of useful features as well.

## Types of Tasks
- Todo: Tasks that have no timing attached to it
- Deadlines: Tasks that are required to be done by a specific date & time
- Events: Tasks that require the user's attendance/participation, with a specific date & time

## Features 
1. **Task addition**: Adds the various types of tasks into Duke's track-list.
    1. `todo`: add a task of **Todo** type
    2. `deadline`: add a task of **Deadline** type
    3. `event`: add a task of **Event** type
2. `delete`: delete a specific task
3. `find`: queries the task(s) containing the specific word
4. `list`: displays all tasks in a list format
5. `done`: marks a task as done
6. `tag`: tags a specific with the desired hashtag
7. `bye`: shutdown the application    
    
### Feature details

### Adding a **todo** task: `todo` 

Adds a task of todo type into Duke's tracklist.

#### Format
`todo <activity>`

A todo task will be added with the given activity.

#### Usage

Example of usage:

`todo read book`

Expected outcome:
```
Got it. I've added this task:
   [T][✘] read book 
Now you have 1 tasks in the list.
```
### Adding a **deadline** task: `deadline` 

Adds a task of deadline type into Duke's tracklist.

#### Format
`deadline <activity> /by <Year-Month-Day Hour:Minute>`

A deadline task will be added with the given activity,  date & time.

#### Usage

Example of usage:

`deadline finish 2103iP /by 2020-09-15 21:00`

Expected outcome:
```
Got it. I've added this task:
   [D][✘] finish 2103iP (by: 2020-09-15 21:00) 
Now you have 1 tasks in the list.
```
### Adding an **event** task: `event` 

Adds a task of event type into Duke's tracklist.

#### Format
`event <activity> /at <Year-Month-Day Hour:Minute>`

An event task will be added with the given activity,  date & time.

#### Usage

Example of usage:

`event attend session /at 2020-11-10 15:00`

Expected outcome:
```
Got it. I've added this task:
   [E][✘] attend session (at: 2020-11-10 15:00) 
Now you have 1 tasks in the list.
```
### Deleting a task: `delete` 

Delete a specific task of any type from Duke's tracklist.

#### Format
`delete <index>`

A task with the specified index will be removed.

#### Usage

Example of usage:

`delete 2`

Expected outcome:
```
Noted. I've removed this task:
  [D][✘] return book (by: 2017-10-10 05:00) 
Now you have 2 tasks in the list.
```

### Finding a task: `find` 

Returns tasks of any type from Duke's tracklist based on the keyword given.

#### Format
`find <keyword>`

All tasks whose activity description contains the specified index will be returned.

#### Usage

Example of usage:

`find finish`

Expected outcome:
```
1. [D][✘] finish 2103iP (by: Sep 15 2020, 9:00 pm) 
2. [T][✘] finish assignment 
```

### Displaying all current tasks: `list` 

Returns all tasks in Duke's tracklist.

#### Format
`list`

All tasks in Duke's tracklist will be displayed.

#### Usage

Example of usage:

`list`

Expected outcome:
```
1. [D][✘] finish 2103iP (by: Sep 15 2020, 9:00 pm) 
2. [T][✘] finish assignment
3. [E][✘] attend session (at: 2020-11-10 15:00)  
```
### Completing a task: `done` 

Marks a task as done in Duke's tracklist.

#### Format
`done <index>`

A task with the specified index will be marked as done using ✓.

#### Usage

Example of usage:

`done 9`

Expected outcome:
```
Nice! I have marked this task as done: 
  [D][✓] finish 2103iP (by: 2020-09-15 21:00) 
```

### Tagging a task: `tag` 

Appends a given hashtag onto a specific task in Duke's tracklist.

#### Format
`tag <index> <hashtag>`

A task with the specified index will have the given hashtag.

#### Usage

Example of usage:

`tag 11 #urgent`

Expected outcome:
```
Noted. I've tagged this task as:
  [E][✓] project meeting (at: 2017-12-11 13:00)  #urgent 
```

### Leaving the application: `bye` 

The Duke chat will stop taking in commands and proceeds to exit immediately.

#### Format
`bye`

The application will be exited immediately upon the command.

#### Usage

Example of usage:

`bye`

Expected outcome:
```
Bye. Hope to see you again soon!
```

## Command summary

Action | Format, Examples
--------|------------------
**Todo** | `todo <activity>` <br> e.g., `todo buy book`
**Deadline** | `deadline <activity> /by <Year-Month-Day Hour:Minute>` <br> e.g., `deadline finish 2103iP /by 2020-09-15 21:00`
**Event** | `event <activity> /at <Year-Month-Day Hour:Minute>` <br> e.g., `event attend session /at 2020-11-10 15:00`
**Delete** | `delete <index>`<br> e.g., `delete 3` 
**Find** | `find <keyword>`<br> e.g., `find book`
**List** | `list`
**Done** | `done <index>` <br> e.g., `done 5`
**Tag** | `tag <index> <#hashtag>` <br> e.g., `tag 10 #cool`
**Bye** | `bye` 