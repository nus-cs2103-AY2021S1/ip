# User Guide

## Features 

### Add Todo Task 
Adds a new todo task with the specified description to the user’s list.

### Add Deadline Task
Adds a deadline task with the specified description and due date to the user’s list.

### Add Event Task
Adds an event task with the specified description and event date to the user’s list.

### Finish Task
Marks the task at the specified index in the user’s list as done.

### Delete Task
Deletes the task at the specified index in the user’s list.

### List Task
Lists out all the tasks in the user’s list in the same order as they were entered. 

### Find Task
Lists out all the tasks that match user’s keyword even if the keyword is incomplete.

### Exit Program
Exits the program.
 
## Usage

### Add Todo Task: `todo`
Adds a new todo task.

Format: `todo <description>`

Example of usage: 

* `todo read book` adds the todo task with description `read book` to   the user’s list.

### Add Deadline Task: `deadline`
Adds a new deadline task.

Format: `deadline <description> /by <YYYY-MM-DD>`

Example of usage:

* `deadline history essay /by 2020-10-12` adds the deadline task with description `history essay` and due date `2020-10-12` to the user’s list.

### Add Event Task: `event`
Adds a new event task.

Format: `event <description> /at <YYYY-MM-DD>`

Example of usage:

* `event project meeting /at 2020-11-15` adds the event task with description `project meeting` and event date `2020-11-15` to the user’s list.

### Finish Task: `done`
Marks the task as done.

Format: `done <index>`

Example of usage:

* `done 2` marks the second task in the user’s list as done.

### Delete Task: `delete`
Deletes the task from the user’s list.

Format: `delete <index>`

Example of usage:

* `delete 2` deletes the second task in the user’s list.

### List Task: `list`
Lists out all the tasks in the user’s list.

Format: `list`

Example of usage:

* `list` shows all the tasks in the list.

### Find Task: `find`
Lists out all the tasks that match user’s keyword.

Format: `find <description>`

Example of usage: 

* `find book` and `find boo` lists out tasks that has description containing `book` and `boo` respectively.

### Exit Program: `bye`
Exits the program.

Format: `bye`

Example of usage:

* `bye` exits the program.

