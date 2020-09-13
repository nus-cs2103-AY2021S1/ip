# User Guide

## Features 
* Add a task to do: todo
* Add a task with a deadline: deadline
* Add an event: event
* Remove a task: remove
* Mark task as done: done
* Find task by keyword: find
* List tasks: list
* Exit the program: bye


### Add a task to do: todo
Adds a todo-task to the task list.

Format: todo TASK

Examples: 
* todo walk the dog
* todo read a book

### Add a task with a deadline: deadline
Adds a deadline-task to the task list.

Format: deadline TASK /by DATE
* DATE format is in **YYYY-MM-DD**

Examples:
* deadline complete individual project /by 2020-09-18
* deadline return books /by 2020-09-20

### Add an event: event
Adds an event-task to the task list.

Format: event TASK /at DATE
* DATE format is in **YYYY-MM-DD**

Examples:
* event music festival /at 2020-09-20
* event project meeting /at 2020-10-01

### Remove a task: remove
Removes a task from the task list.

Format: remove INDEX
* Removes the task at the specified index
* The index refers to the index number of the task as shown in the list
* The index **must be a positive integer** 1,2,3

Example:
* remove 2 

### Mark task as done: done
Marks a task as done in the task list.

Format: done INDEX
* Marks the task at the specified index as done (using a tick)
*  The index refers to the index number of the task as shown in the list
* The index **must be a positive integer** 1,2,3

Example:
* done 2

### Find task by keyword: find
Finds a task in the task list by keyword.

Format: find KEYWORD

### List tasks: list
Lists the current task list.

Format: list

### Exit the program: bye
Exits the program.

Format: bye

### Saving data
Task List data is automatically saved in the harddisk after any command that changes its data.
There is no need to save manually
















