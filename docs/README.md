# User Guide

## Features 

### Feature 1 
Get the list of available commands

## Usage

### `help` - Get the list of available commands

Type help in the chat, and a list of available commands will be shown.

Example of usage: 

`help`

Expected outcome:

`1. list: Lists the tasks in the task list`

`2. todo description: Add a task to do with the description`

`...`

### Feature 2 
Get the list of tasks in the task list

## Usage

### `list [time]` - Get the list of available commands

Type list in the chat, and a list of tasks in the task list will be shown.

Example of usage: 

`list`

Expected outcome:


1. [E] <input type="checkbox" checked> borrow book (at: 12 7 2014)
2. [T] <input type="checkbox" checked> read book
3. [D] <input type="checkbox" checked> return book (by: 12 8 2014)

Example of usage: 

`list 2014-12-07`

Expected outcome:


1. [E] <input type="checkbox" checked> read book (at: 12 7 2014)

### Feature 3 
Add a task to do.

## Usage

### `todo description` - Add a task to do in the task list

Type todo and description in the chat, and the task with the description will be added to the task list.

Example of usage: 

`todo description`

Expected outcome:

The task with the description will be added to the task list.

### Feature 4
Add a deadline by a certain time point.

## Usage

### `deadline description /by yyyy-MM-dd` - Add a deadline in the task list

Type deadline, description, and the time of the deadline in the chat, and the deadline with 
the description and time will be added to the task list.

Example of usage: 

`deadline description /by 2014-12-07`

Expected outcome:

The deadline with the description and time will be added to the task list.


### Feature 5
Add an event at a certain time point.

## Usage

### `event description /at yyyy-MM-dd` - Add an event in the task list

Type event, description, and the time of the event in the chat, and the event with 
the description and time will be added to the task list.

Example of usage: 

`event description /at 2014-12-07`

Expected outcome:

The event with the description and time will be added to the task list.

### Feature 6
Add an event at a certain time point.

## Usage

### `periodTask description /start yyyy-MM-dd /end yyyy-MM-dd` - Add a period task in the task list

Type periodTask, description, the start time of the period task, and the end time of the period task in 
the chat, and the period task with the description, start time, and end time will be added to the task list.

Example of usage: 

`period description /start 2014-12-07 /end 2014-12-08`

Expected outcome:

The period task with the description, start time, and end time will be added to the task list.

### Feature 7
Search for a task with the description that contains a key in 
the task list.

## Usage

### `find key` - Search for the tasks with the key in the task list.

Type find and the key, and the list of tasks with the description that contains 
the key in the task list will be shown. 

Example of usage: 

`find book`

Expected outcome:

1. [E] <input type="checkbox" checked> borrow book (at: 12 7 2014)
2. [T] <input type="checkbox" checked> read book
3. [D] <input type="checkbox" checked> return book (by: 12 8 2014)

### Feature 8
Remove a task from the task list.

## Usage

### `delete number` - Remove the corresponding taks in the task list.

Type delete and the number, and the task corresponding to 
the number will be removed from the task list.

Example of usage: 

`delete 3`

Expected outcome:

1. [E] <input type="checkbox" checked> borrow book (at: 12 7 2014)
2. [T] <input type="checkbox" checked> read book

### Feature 9
Make a task in the task list marked as completed.

## Usage

### `done number` - Make a task in the task list marked as completed.

Type done and the number, and the task corresponding to 
the number will be marked as completed. 

Example of usage: 

`done 1`

Expected outcome:

1. [E] <input type="checkbox" checked> borrow book (at: 12 7 2014)
2. [T] <input type="checkbox" checked> read book
3. [D] <input type="checkbox" checked> return book (by: 12 8 2014)

### Feature 10
Exit the process

## Usage

### `bye` - Exit the process.

Type bye, and the process will no longer continue. 

Example of usage: 

`bye`

Expected outcome:

The process will no longer continue.