# User Guide
Duke is a desktop application for **managing tasks, optimized for use
via a Command Line Interface (CLI)** while still having the benefits
of a Graphical User Interface (GUI).

<br />

## Features
### Listing all tasks: `list`
Shows a list of all tasks in the application.

Format: `list`

<br />

### Add a new todo: `todo`
Adds a new todo task.

Format: `todo DESCRIPTION`

Examples:
* `todo borrow book` creates a new todo task with the given
description.

<br />

### Add a new deadline: `deadline`
Adds a new deadline task.

Format: `deadline DESCRIPTION by DD/MM/YYYY HHMM`
* HHMM is in 24 hour e.g. 2359

Examples:
* `deadline project by 02/02/2020 1600` creates a new deadline with
the given description and deadline.

<br />

### Add a new event: `event`
Adds a new event task.

Format: `event DESCRIPTION at DD/MM/YYYY HHMM-HHMM`
* Takes in the start and end time of the event as well.
* HHMM is in 24 hour e.g. 2359

Examples:
* `event movie at 02/02/2020 1600-1800` creates a new event with
the given description, date, start and end timing.

<br />

### Find relevant tasks: `find`
Finds relevant tasks based on the keyword provided.

Format: `find KEYWORD`

Examples:
* `find movie` returns all the relevant tasks with this keyword.

<br />

### Mark task as done: `done`
Marks a task as done.

Format: `done INDEX`
* Marks the task at the specified INDEX as done.
* INDEX must be a positive number and cannot be greater than number
of tasks the user currently has.

Examples:
* `done 2` marks the second task that the user has as completed.

<br />

### Deleting a task: `delete`
Deletes a task.

Format: `delete INDEX`
* Deletes the task at the specified INDEX.
* INDEX must be a positive number and cannot be greater than number
of tasks the user currently has.

Examples:
* `delete 2` deletes the second task that the user has.

<br />

### Exiting the application: `bye`
Exits the application.

Format: `bye`

<br />
