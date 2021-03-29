# User Guide

Duke is a desktop app for managing tasks, optimized for use via a Command Line Interface (CLI). A rudimentary GUI is implemented for ease of use. 
Duke is great for fast typists who can quickly get used to the syntax.

## Features 

### Task Management
Tasks of three forms can be handled by Duke: To-Dos, Events and Deadlines.
Tasks can be added, deleted, marked as done, searched for and listed.

### Task Reminder
Duke can be used to remind you of tasks due today

### Save and Load
Saving is automatic when Duke is properly closed (i.e. using the `bye` command). Loading is automatic when starting up Duke. A new save file will be generated if it cannot be found.

## Usage

### `about` - Displays information about Duke.

Shows a message containing information about the Duke application.

Format: `about`

### `bye` - Exits Duke.

Duke sends farewell wishes before closing. Saving occurs after this command.

Format: `bye`

### `deadline` - Adds a deadline for Duke to keep track of.

Adds a task with the given deadline for Duke to manage.
 - Deadline will start off not marked as done.
 - If a date (and optionally, time) is provided in the 24h date and time format `dd/mm/yy hh:mm`, it will be recognised by Duke. Otherwise, a description will be stored instead.
 - A deadline must be provided or an error will be displayed instead.

Format: `deadline NAME /by DEADLINE`

Example of usage:
 - `deadline do homework /by 1/10/20 23:59` will add a new deadline to do homework by 1st October 2020 11:50pm
 - `deadline do homework /by 12:00` will add a new deadline to do homework by 12:00, which is stored as a description, not a time construct (and therefore will not work with `remind`).
 - `deadline apply for internship /by yesterday` will add a new deadline to apply for internship by yesterday, also stored as a description.

### `delete` - Deletes a task from Duke's system.

Deletes a task that is stored within Duke.
 - A valid, 1-based index must be provided, or an error will be displayed instead.

Format: `delete INDEX`

Example of usage:
 - `delete 3` will delete the third task in the list.
 
### `done` - Marks a task in Duke's system as done.

Marks a task that is stored within Duke as done.
 - A valid, 1-based index must be provided, or an error will be displayed instead.

Format: `done INDEX`

Example of usage:
 - `done 2` will mark the second task on the list as done.
 
### `event` - Adds an event for Duke to keep track of.

Adds an event at a specific date or time for Duke to manage.
 - Event will start off not marked as done.
 - If a date (and optionally, time) is provided in the 24h date and time format `dd/mm/yy hh:mm`, it will be recognised by Duke. Otherwise, a description will be stored instead.
 - The event's time must be provided or an error will be displayed instead.

Format: `event NAME /at TIMEPOINT`

Example of usage:
 - `event watch concert /at 1/10/20 21:00` will add a new event to watch the concert at 1st October 2020 9pm.
 - `event play basketball /at 3pm` will add a new event to play basketball at 3pm, which is stored as a description, not a time construct (and therefore will not work with `remind`).
 - `event Bob's party /at later` will add a new event about Bob's party later, with "later" also stored as a description. 
 
### `find` - Finds a task in Duke's system.

Finds tasks given a part of its name. All tasks fitting the given search phrase will be displayed.
 - A search string must be provided or an error will be given.
 - The serial number associated with the returned tasks is not the order it is in the list. Use `list` to check the list index.

Format: `find SUBSTRING`

Example of usage:
 - `find sub` will show a task named `submit homework` or `eat sub`.
 - `find clean floor` will show a task named `clean floor clutter` but not `clean dirt floor` nor `sweep floor until clean`.
 - `find June` will show a task named `Meet June for lunch` but not a deadline expiring in June. Note that `find` does not search through additional information. It only searches through names.
 
### `list` - Displays all tasks.

List down all tasks stored within Duke and their relevant information (state of doneness, deadline/time if any).

Format: `list`

### `remind` - Displays reminder about deadlines due today.

Lists all deadlines stored within Duke that expire at the day of command input.

Format: `remind`

### `todo` - Adds a todo for Duke to keep track of.

Adds a task to be done for Duke to manage
 - To-do will start off not marked as done.

Format: `todo NAME`

Example of usage:
 - `todo submit homework` will store a to-do task to submit homework