_Ikurabowl_ is a **desktop app for tasks and todos, optimised for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, it can get your task management done faster than traditional GUI apps.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `ikurabowl.jar` from [here](https://github.com/zhiayang/ip/releases) and save it to the folder you want to use as the _home folder_ for _ikurabowl_.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](./Ui.png)

1. Type the command in the command box and press Enter to execute it. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Items in angled brackets (eg: `<description>`) are the inputs from the user.
* Items in square brackets (eg: `[date]`) are optional inputs from the user.
* Command parameters are provided after the relevant specifier (eg. `todo /desc <description>`)
* All arguments can contain whitespace; the argument for that parameter ends either at the end of the input, or when the next parameter delimiter (ie. `/foo`) is encountered.
* The date format is `YYYY-MM-DD` in all instances.

</div>

## Types of Tasks

### General

In general, all tasks have a title and a description; the description is optional, and
if not given will simply be empty.

Each task also has one piece of state --- whether or not it is completed.

### Todo

A Todo is the most basic type of task; it has no additional state, containing only a
title and an (optional) description.


### Deadline

A Deadline is a Todo task, except it has a date component.


### Event

An Event is functionally the same as a Deadline, in that it also has a date component.







## Commands

### Adding a Todo: `todo`

Creates a new Todo task.

Format: `todo <title> [/desc <description>]​`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
If the description is not specified, then it is empty.
</div>

Examples:
* `todo Buy 700 eggs /desc required to bake a huge cake.`
* `todo Buy 30 cartons of milk`


### Adding an Event: `event`

Creates a new Event; note that the date is mandatory.

Format: `event <title> /at <date> [/desc <description>​]`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
The date and description can come in any order, as long as the date is present.
</div>

Examples:
* `event Massive birthday party /at 2020-12-31`
* `event Clean up the mess /desc I'm sure there will be a lot /at 2021-01-01`



### Adding an Deadline: `deadline`

Creates a new Deadline; note that the date is mandatory.

Format: `deadline <title> /by <date> [/desc <description>​]`

Examples:
* `deadline Bake the massive cake /by 2020-12-29`
* `deadline Invite people /desc I'm sure there will be a lot /by 2020-11-18`


### Listing all tasks : `list`

Shows a list of all the tasks that are tracked.

Format: `list`


### Deleting all tasks : `reset`

Deletes all tasks from the list. Note that this is irreversible!

Format: `reset`



### Completing a task : `done`

Mark a task as done. This takes the task number, which is the same one displayed by the `list` command. Note that the number of the first task is 1.

Format: `done <task number>`

Example:
* `done 3`


### Deleting a task : `delete`

Delete a task. This takes the task number, similar to `done`.

Format: `delete <task number>`

Example:
* `delete 3`



### Editing a task : `edit`

Edits an existing task.

Format: `edit <task number> [/title <new title>] [/desc <new description>] [/date <new date>]`

* Edits the task with the given `<task number>`, which is the same number displayed by `list`.
* At least one of the optional fields must be provided, but they can be in any order.
* Existing values will be updated to the input values.
* It is an error to edit the date of a Todo (which does not have a date).

Examples:
*  `edit 3 /title I changed my mind /date 2021-12-31` changes the title and date of the 3rd task.



### Searching for a task: `find`

Finds persons whose names contain *any* of the given keywords.

Format: `find <keyword> [<keyword>]...`

* The search is case-insensitive. e.g `owo` will match `OWO`
* The order of the keywords does not matter. e.g. `uwu owo` will match `OWO UWU`
* Both the title and the description of the task are searched.
* Only full words will be matched e.g. `AYAYA` will not match `AYAYAS`
* Persons matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `owo uwu` will return `monkaS owo`, `monkaMEGA uwu`

Examples:
* `find eggs` returns `Buy eggs` and `Dispose of Eggs`

### Exiting the program : `quit`

Exits the program.

Format: `quit`

### Saving the data

The list of tasks and their state is saved automatically after every command, so there is no need to save it manually.
