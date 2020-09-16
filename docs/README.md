# User Guide

* Features
    * [Adding a ToDo task: `todo`](#todo)
    * [Adding a Deadline task: `deadline`](#deadline)
    * [Adding an Event task: `event`](#event)
    * [Listing all task: `list`](#list)
    * [Locating a task by name: `find`](#find)
    * [Deleting a task: `delete`](#delete)
    * [Complete a task: `done`](#done)
    * [Exiting the program: `bye`](#bye)

---
## Features 

> Notes about the command format:
> * Words in `UPPER_CASE` are the parameters to be supplied by the user.  
> e.g in `todo DESCRIPTION`, `DESCRIPTION` is a parameter which can be used as `todo Submit work`.

### Adding a ToDo task: `todo` <a name="todo"></a>
Adds a ToDo task to Dude Bot.
Format: `todo DESCRIPTION`

### Adding a Deadline task: `deadline` <a name="deadline"></a>
Adds a Deadline task to Dude Bot.
Format: `deadline DESCRIPTION /by DATE`

### Adding an Event task: `event`: todo <a name="event"></a>
Adds an Event task to Dude Bot.
Format: `event DESCRIPTION /at DATE`

### Listing all task: `list` todo <a name="list"></a>
Shows a list of all tasks in Dude Bot.
Format: `list`

### Locating a task by name: `find` todo <a name="find"></a>
Finds tasks whose descriptions contains the given keyword.
Format: `find KEYWORD`
* The search is case-insensitive. e.g `teSt` will match `Test`

### Deleting a task: `delete` todo <a name="delete"></a>
Deletes the specified task from Dude Bot.
Format: `delete INDEX`

### Complete a task: `done` todo <a name="done"></a>
Marks the specified task as complete in Dude Bot.
Format: `done INDEX`

### Exiting the program: `bye` <a name="bye"></a>
Exits the program.

Format: `exit`

## Usage

### `Keyword` - Describe action

Describe action and its outcome.

Example of usage: 

`keyword (optional arguments)`

Expected outcome:

`outcome`
