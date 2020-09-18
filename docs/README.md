* Table of Contents
{:toc}

Duke is a **desktop app for managing todos, deadlines and events, optimised for use via a Command Line Interface (CLI)**

![](Ui.png)

# User Guide

## Features 

### Adding a todo: `todo`

Adds a todo to Duke. The priority must be one of NONE (default), LOW, MEDIUM or HIGH.

Format: `todo [name] [#tags] [!priority]`

Examples:

* `todo Foobar`
* `todo Foobar #with #tags !high`

### Adding a deadline: `deadline`

Adds a deadline to Duke. The priority must be one of NONE (default), LOW, MEDIUM or HIGH.
If the time is not specified, it will default to midnight.
The date and time specified must be in one of the following formats:

**Date:**

* `26/8`
* `26/08`
* `26/08/20`
* `26/08/2020`

**Time (optional):**

* `1:19`
* `1:19 PM`

Format: `deadline [name] [/by date] [#tags] [!priority]`

Examples:
* `deadline Foobar /by 26/8`
* `deadline Foobar /by 26/8 1:19 PM #with #tags !high`

### Adding an event: `event`

Adds an event to Duke. The priority must be one of NONE (default), LOW, MEDIUM or HIGH.
If the time is not specified, it will default to midnight.
The date and time specified must be in one of the following formats:

**Date:**

* `26/8`
* `26/08`
* `26/08/20`
* `26/08/2020`

**Time (optional):**

* `1:19`
* `1:19 PM`

Format: `event [name] [/at date] [#tags] [!priority]`

Examples:
* `event Foobar /at 26/8`
* `event Foobar /at 26/8 1:19 PM #with #tags !high`

### Do task: `done`

Marks a task as done. The task number refers to the position of the task on the list.

Format: `done [task number]`

### Delete task: `delete`

Deletes a task. The task number refers to the position of the task on the list.

Format: `delete [task number]`

### List all tasks: `list`

Lists all tasks in the list.

Format: `list`

### Find tasks by keyword: `find`

Finds all tasks with the specified keywords. The search is case-sensitive and matches tasks with all the specified keywords.

Format: `find [keywords]`

Examples:

* `find some tasks`

### Find tasks with priority: `prioritised`

Finds all tasks with the specified priority. The priority must be one of NONE, LOW, MEDIUM or HIGH.

Format: `prioritised [priority]`

Examples:

* `prioritised high`

### Find tasks with tags: `tagged`

Finds all tasks with the specified tags. Only tasks with all the specified tags are returned.

Format: `tagged [tags]`

Examples:

* `tagged with tags`

### Find tasks due on a date: `due`

Finds all tasks due on the specified date. The date must be in one of the following formats:

**Date:**

* `26/8`
* `26/08`
* `26/08/20`
* `26/08/2020`

Format: `due [date]`

Examples:

* `due 26/8`

### Exit: `bye`

Exits Duke.

Format: `bye`
