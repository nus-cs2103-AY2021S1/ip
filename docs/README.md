# Duke User Guide

A simple task manager to help you manage the tasks in your life.

## Features 

### Prioritise Tasks
Tag tasks to their priority levels to know what's important to you - high, medium and low

### Search for Tasks
Search for tasks by keywords or dates

### Manage Different Kinds of Tasks
Manage different kinds of tasks depending on what you need - ToDos, Deadlines, Events

## Getting Started

Download latest release from https://github.com/chrystalquek/ip/releases
Double click on the jar file and the application page will appear.

If you want to use the Command Line Interface, git clone https://github.com/chrystalquek/ip. From the terminal, navigate to the directory of the project and run the Duke class.
 Duke will start responding to what you type in the terminal.

## Usage

### `todo` - Add a ToDo

Adds a ToDo to a list of Tasks.

Example of usage: 

`todo (description)`

`todo (description) /priority (priority level)`


Expected outcome:

```
Got it. I've added this task:
[T][✘] todo (priority: medium)
Now you have 3 tasks in the list.
```


### `deadline` - Add a Deadline

Adds a Deadline to a list of Tasks.

Example of usage: 

`deadline (description) /by (date)`

`deadline (description) /by (date) /priority (priority level)`


Expected outcome:

```
Got it. I've added this task:
[D][✘] deadline (by: Mar 4 2020)
Now you have 3 tasks in the list.
```

### `event` - Add a Event

Adds a Event to a list of Tasks.

Example of usage: 

`event (description) /at (date)`

`event (description) /at (date) /priority (priority level)`


Expected outcome:

```
Got it. I've added this task:
[E][✘] event (by: Mar 4 2020) (priority: low)
Now you have 3 tasks in the list.
```



### `list` - See all tasks

Displays all tasks.

Example of usage: 

`list`

Expected outcome:

```
Here are the tasks in your list:
1. [E][✘] fb hackathon (at: Oct 4 2020)
2. [D][✘] cs2103T iP (by: Sep 18 2020) (priority: high)
```


### `tasks due on` - Search tasks by date

Displays tasks with date on a certain date.

Example of usage: 

`tasks due on (date)`

Expected outcome:

```
Here are the tasks in your list that occur on Oct 4 2020:
1. [E][✘] fb hackathon (at: Oct 4 2020)
```


### `find` - Search tasks by keyword

Displays tasks which contain a certain keyword.

Example of usage: 

`find (keyword)`

Expected outcome:

Displays tasks which contain a certain keyword.

```
Here are the matching tasks in your list:
1. [E][✘] fb hackathon (at: Oct 4 2020)
```

### `done` - Complete a task

If task is yet to be completed, task will be marked as done.

If task has already been marked as done, you will be prompted that the task is already completed.

Example of usage: 

`done (index in task list)`

Expected outcome:

```
Nice! I've marked this task as done:
[T][✓] todo (priority: medium)
```


### `delete` - Delete a task

If index is valid, task will be removed from the task list.

If index is invalid, you will be prompted that the index provided is out of range.

Example of usage: 

`delete (index in task list)`

Expected outcome:

```
Noted. I've removed this task:
[T][✓] todo (priority: medium)
Now you have 3 tasks in the list.
```


### `bye` - Exit Duke

Exits the program.

Example of usage: 

`bye`

Expected outcome:

Exits the program and closes the window.

