# User Guide

# Features 
- Ability to add and delete tasks
- Ability to update added tasks
- Ability to list all tasks, or view the ones you want
- Ability to load a list of tasks from a local save file
- Dark UI so you don't get blinded :)

## Add tasks
Add 3 types of tasks, To Do, Event, and Deadline, with date (optional).

### Usage

### `todo` - adds a To Do task
### `event` - adds an Event task
### `deadline` - adds a Deadline task

Usage: 

`<todo/event/deadline> <name of task> <date>`

Example:

`todo homework 2020-09-08`

Expected outcome:

`Task added:`

`[T][X] Homework @ Sep 08 2020`

`You now have 1 task(s)."`

## Complete tasks
Mark added tasks as complete.

### Usage

### `done` - marks task at selected index as complete

Usage: 

`<done> <task index>`

Example:

`done 1`

Expected outcome:

`Marked task 1 as complete`

`[T][✓] Homework @ Sep 09 2020`

## Update tasks 
Change the name or date of tasks.

### Usage

### `update` - updates name or date at selected index

Usage: 

`<update> <task index> <name/date> <information to update>`

Example:

`update 1 date 2020-09-08`

Expected outcome:

`Updated task:`

`[T][X] Homework @ Sep 08 2020`

## Delete tasks
Delete added tasks

### Usage

### `delete` - deletes task at selected index

Usage: 

`<delete> <task index>`

Example:

`delete 1`

Expected outcome:

`Deleted task:`

`[T][X] Homework @ Sep 09 2020`

`There are now 0 task(s) remaining.`

## List tasks
Lists out all tasks

### Usage

### `list` - displays all stored tasks

Usage: 

`<list>`

Example:

`list`

Expected outcome:

`Here's your tasks`

`1.[T][X] Homework @ Sep 08 2020`

`2.[T][X] Housework @ Sep 09 2020`

`3.[T][X] Woodwork @ Sep 08 2020`

`4.[T][X] Bodywork @ Sep 09 2020`

## Find tasks
Lists all tasks with names that match given input

### Usage

### `find` - displays all stored tasks

Usage: 

`<find> <name to search for>`

Example:

`find od`

Expected outcome:

`Here's your matching tasks:`

`3.[T][X] Woodwork @ Sep 08 2020`

`4.[T][X] Bodywork @ Sep 09 2020`

## Find due tasks
Lists all tasks with due dates that match given input

### Usage

### `due` - displays all stored tasks

Usage: 

`<due> <date>`

Example:

`due 2020-09-08`

Expected outcome:

`These tasks are due:`

`[T][X] Homework @ Sep 08 2020`

`[T][X] Woodwork @ Sep 08 2020`

## Exit program
Exits the program

### Usage

### `bye` - displays all stored tasks

Usage: 

`<bye>`

Example:

`bye`

Expected outcome:

Program exits
