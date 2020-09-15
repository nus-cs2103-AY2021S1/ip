# User Guide

## Features 
* Add Todo (a task with no deadline or date): `todo`
* Add Deadline (a task with deadline): `deadline`
* Add Event (an item with date): `event`
* Delete a task: `delete`
* Mark a task as done: `done`
* Undo the last command: `undo`
* Find tasks containing a certain text: `find`
* List all tasks: `list`
* Exit the program: `bye`

### Add Todo: `todo`
Adds a Todo to the task list.

Format: `todo TASK_NAME`

Example of usage: `todo wake up`

Expected outcome:

`Got it. I've added this task:
    [T][X] wake up
Now you have 2 tasks in the list`

### Add Deadline: `deadline`
Adds a Deadline to the task list.

Format: `deadline TASK_NAME /by YYYY-MM-DD`

Example of usage: `deadline wake up /by 2020-10-23`

Expected outcome:

`Got it. I've added this task:
    [D][X] wake up (by: Oct 23 2020)
Now you have 3 tasks in the list`

### Add Event: `event`
Adds a Event to the task list.

Format: `event EVENT_NAME /at YYYY-MM-DD`

Example of usage: `event sleep /at 2020-10-24`

Expected outcome:

`Got it. I've added this task:
    [E][X] sleep (at: Oct 24 2020)
Now you have 4 tasks in the list`

### Delete a task: `delete`
Deletes a Task from the task list.

Format: `delete TASK_INDEX`

Example of usage: `delete 3`

Expected outcome:

`Noted. I've removed this task:
    [E][X] sleep (at: Oct 24 2020)
Now you have 3 tasks in the list`

### Mark a task as done: `done`
Marks a Task from the task list as done.

Format: `done TASK_INDEX`

Example of usage: `done 2`

Expected outcome:

`Nice! I've marked this task as done:
    [D][✓] wake up (by: Oct 23 2020)`

### Undo the last command: `undo`
Undos the last command.

Format: `undo`

Example of usage: `undo`

Expected outcome:

`Got it. I've marked this task as undone:
    [D][X] wake up (by: Oct 23 2020)`    
    
### Find tasks containing a certain text: `find`
Finds all tasks containing the text.

Format: `find TEXT`

Example of usage: `find wake`

Expected outcome:

`Here are the matching tasks in your list:
    1. [T][X] wake up
    2. [D][X] wake up (by: Oct 23 2020)` 

### List all tasks: `list`
Lists all the tasks in the list.

Format: `list`

Example of usage: `list`

Expected outcome:

`Here are the tasks in your list:
    1. [T][X] first task
    2. [T][X] wake up
    3. [D][X] wake up (by: Oct 23 2020)` 

### Exit the program: `bye`
Exits the program.

Format: `bye`

Example of usage: `bye`

Expected outcome:

`Bye. Hope to see you again soon!` 
