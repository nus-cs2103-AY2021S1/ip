# User Guide

## Features 

### Creating a ToDo task : `todo`

Creates a new ToDo task.

Format: `todo TASK_DESCRIPTION`

Example of usage: 
* `todo read book`

Expected outcome:
* `[T][✘] read book`

### Creating a Deadline task: `deadline`

Creates a new Deadline task.

Format: `deadline TASK_DESCRIPTION /by DATE TIME`
* `DATE` is in DD/MM/YYYY format.
* `TIME` is in HH:MM (24 hour clock) format.

Example of usage:
* `deadline iP project /by 16/08/2020 23:59`

Expected outcome:
* `[D][✘] iP project (by: 16 Aug 2020 11:59 PM)`

### Creating an Event task: `event`

Creates a new Event task.

Format: `event TASK_DESCRIPTION /by DATE START_TIME-END_TIME`
* `DATE` is in DD/MM/YYYY format.
* `START_TIME` and `END_TIME` is in HH:MM (24 hour clock) format.

Example of usage:
* `event project meeting /at 14/08/2020 21:00-22:00`

Expected outcome:
* `[E][✘] project meeting (at: 14 Aug 2020 9:00 PM - 10:00 PM)`

### Listing all tasks: `list`
Lists all tasks in the task manager.

Format: `list`

### Marking a task done: `done`
Marks a task done.

Format: `done TASK_NUMBER`
* Task indexed at TASK_NUMBER will be marked as done.

Example of usage: 
* `done 1`

Expected outcome:
* `[T][✓] read book`

### Deleting a task: `delete`
Deletes a task.

Format: `delete TASK_NUMBER`
* Task indexed at TASK_NUMBER will be deleted.

Example of usage: 
* delete 1

Expected outcome:
* `[D][✘] return book (by: 12 Dec 2020 11:59 PM)` is deleted.

### Finding a task: 'find'
Finds a task.

Format: `find KEYWORD`
* All tasks that contain KEYWORD will be displayed.

Example of usage:
* `find book`

Expected outcome:
* `[T][✓] read book` and `[D][✘] return book (by: 12 Dec 2020 11:59 PM)` will be returned.

### Listing all commands: `help`
Lists all commands of the task manager.

Format: `help`

### Exiting the application: `bye`
Exits the task manager.

Format `bye`