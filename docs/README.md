# User Guide

Welcome to PandaBot's User Guide. PandaBot is a desktop application for managing
the tasks that you have.

This guide aims to get you familiar with the features of PandaBot.

## Features 

### Exit the PandaBot Application: `bye`
Exits the program.

Format: `bye`

### List all tasks recorded: `list`
Shows a list of all the tasks that you have given.

Format: `list`

### Help page: `help`
Presents a help page that contains all the available commands that you can use.

Format: `help`

### Delete a task: `delete`
Deletes a specified task, according to its task number in the task list.

Format: `delete NUMBER`, where NUMBER refers to its task number in the task list
* `NUMBER` should be a positive integer.

Example Use: `delete 1`

### Complete a task: `done`
Marks a specified task, according to its task number in the task list, as done.

Format: `done NUMBER`, where NUMBER refers to its task number in the task list
* `NUMBER` should be a positive integer.

Example Use: `done 1`

### Find a list of tasks using a keyword: `find`
Presents a list of tasks with description that contains the keyword.

Format: `find KEYWORD`
* `KEYWORD` refers to the keyword used to find tasks with matching description.

Example Use: `find CS2103T`

### Add a Deadline task: `deadline`
Adds a Deadline task to the list of tasks. These are tasks that have to be done 
by a certain time.

Format: `deadline DESCRIPTION /by TIME`
* `DESCRIPTION` refers to the description of the Deadline task.
* `TIME` refers to the due date of the Deadline task.
* `TIME` can be formatted as *dd/MM/yyyy HHmm*.
* If you prefer not to use a formatted `TIME`, any type of input for `TIME` is possible.

Example Use: 
* `deadline CS2103T iP /by 18/09/2000 2359`
* `deadline CS2100 assignment /by Friday`

### Add a DoAfter task: `do`
Adds a DoAfter task to the list of tasks. These are tasks that have to done after 
a certain time or task.

Format: `do DESCRIPTION /after` `TIME` or `TASK`
* `DESCRIPTION` refers to the description of the DoAfter task.
* `TIME/TASK` can refer to the time, or the task, that the DoAfter task should be done after.
* If `TIME` is used, it can be formatted as *dd/MM/yyyy HHmm*.
* If you prefer not to use a formatted `TIME`, any type of input for `TIME` is possible.

Example Use: 
* `do CS2103T tP assignment /after CS2103T iP assignments`
* `do CS2103T quiz /after 18/09/2000 1200`

### Add an Event task: `event`
Adds an Event task to the list of tasks. These are tasks that occur at a certain time.

Format: `event DESCRIPTION /at TIME`
* `DESCRIPTION` refers to the description of the Event task.
* `TIME` refers to the due date of the Event task.
* `TIME` can be formatted as *dd/MM/yyyy HHmm*.
* If you prefer not to use a formatted `TIME`, any type of input for `TIME` is possible.

Example Use: 
* `event Webinar /at 19/09/2000 1430`
* `event Online Concert /at Friday 2pm`

### Add a ToDo task: `todo`
Adds a ToDo task to the list of tasks. These are tasks that are not time-based.

Format: `todo DESCRIPTION`
* `DESCRIPTION` refers to the description of the ToDo task.

Example Use: `todo Revision for CS2100 midterms`

## Note:
Formatted `TIME` in the form of *dd/MM/yyyy HHmm* will be displayed in a new format after processing
by PandaBot. If `TIME` is not formatted as such, PandaBot will just record the given input as is.
