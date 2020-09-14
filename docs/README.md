# User Guide

## Features
### [Listing all tasks: `list`](#list)
### [Adding a todo task: `todo`](#todo)
### [Adding a deadline task: `deadline`](#deadline)
### [Adding an event task: `event`](#event)
### [Deleting a task by index: `delete`](#delete)
### [Locating tasks by name: `find`](#find)
### [Marking a task as completed by index: `done`](#done)
### [Displaying the statistics of recent activities: `stats`](#stats)
### [Exiting the program: `bye`](#bye)

## Usage

### <a id="list"></a> Listing all tasks: `list`

Shows a list of all tasks in Dude.

Format: `list`
* Shows a list of all tasks in Dude and their respective indexes.

### <a id="todo"></a> Adding a todo task: `list`

Adds a todo task to Dude.

Format: `todo NAME`
* Adds a todo task with the given `NAME`.

Examples:
* `todo eat`
* `todo sleep`

### <a id="deadline"></a> Adding a deadline task: `deadline`

Adds a deadline task to Dude.

Format: `deadline NAME /by DD/MM/YYYY HHMM`
* Adds a deadline with the given `NAME`.
* The day and month can be single digits (Example: `1/5/2020`).

Examples:
* `deadline lose weight /by 01/01/2021 0000`
* `deadline celebrate christmas /by 25/12/2020 0000`

### <a id="event"></a> Adding an event task: `event`

Adds an event task to Dude.

Format: `event NAME /at DD/MM/YYYY HHMM`
* Adds an event with the given `NAME`.
* The day and month can be single digits (Example: `1/5/2020`).

Examples:
* `event new year celebration /at 01/01/2021 0000`
* `event exams /at 01/02/2021 0800`

### <a id="delete"></a> Deleting a task by index: `delete`

Deletes the specific task from Dude.

Format: `delete INDEX`
* Deletes the task at the specified `INDEX`.
* The index of a task can be found by using the [`list`](#list) command.

Examples:
* `delete 1`
* `delete 2`

### <a id="find"></a> Locating tasks by name: `find`

Finds tasks whose names contains any of the given keywords in Dude.

Format: `find KEYWORD`
* Finds tasks whose names contains `KEYWORD`.

Examples:
* `find homework`
* `find meaning in life`

### <a id="done"></a> Marking a task as completed by index: `done`

Marks the specific task as completed in Dude.

Format: `done INDEX`
* Marks the task at the specified `INDEX` as completed.
* The index of a task can be found by using the [`list`](#list) command.

Examples:
* `done 1`
* `done 2`

### <a id="stats"></a> Displaying the statistics of recent activities: `stats`

Displays the number of tasks completed in the past 7 days.

Format: `stats`

### <a id="bye"></a> Exiting the program: `bye`

Exits the program.

Format: `bye`
