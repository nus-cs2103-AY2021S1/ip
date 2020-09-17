# User Guide
Rock is a **desktop application for managing and scheduling your tasks,
optimized for use via a Command Line Interface (CLI)** while still having
the benefits of a Graphical User Interface (GUI). It is a convenient platform
for you to keep track of all your upcoming tasks like deadlines and events.

## Features 
Notes about command format:
* Words in `UPPER_CASE` are the parameters supplied by you.
* Example: event `YOUR_EVENT` /at `TIME_OF_THE_EVENT`

### Adding a to-do task: `todo`
Adds to the list: a to-do task that needs to be done.

Format: `todo DESCRIPTION`

Example: `todo buy a new laptop`

### Adding an event task: `event`
Adds to the list: an event task that happens at a certain time.

Format: `event DESCRIPTION /at TIME`

Example: `event CS2103T lecture /at Friday`

### Adding a deadline task: `deadline`
Adds to the list: a deadline task that needs to be done by a certain deadline.

Format: `deadline DESCRIPTION /by TIME`

Example: `deadline CS2103T IP /by Friday`

### Adding a do-with-in task: `dowithin`
Adds to the list: a do-with-in task that needs to be done within a certain period.

Format: `dowithin DESCRIPTION /between START_TIME /and END_TIME`

Example: `dowithin CS2103T Quiz /between Friday /and next Monday`

### Listing all tasks: `list`
Shows a list of all tasks.

Format: `list`

### Deleting a task: `delete`
Deletes the specified task from the list.

Format: `delete INDEX`

* Deletes the task at the specified `INDEX`.
* The index refers to the index number shown in the displayed task list.
* The index **must be a positive integer** 1, 2, 3, …

Example:
* Optional: `list` to show the current tasks list.
* `delete 2` deletes the 2nd task in the list.

### Locating tasks by description: `find`
Finds tasks whose descriptions contain the given keyword.

Format: `find KEYWORD`
* The search is case-sensitive.
* Only the description is searched. e.g `Friday` will not match task created by `deadline CS2103T IP /by Friday`.

Example: 'find CS2103T'
* returns a list of matched tasks.
* e.g tasks created by `dowithin CS2103T Quiz /between Friday /and next Monday`
and `deadline CS2103T IP /by Friday`.

### Marking a task as done: `done`
Marks the specified task in the list as done.

Format: `done INDEX`

* Mark the task at the specified `INDEX`.
* The index refers to the index number shown in the displayed task list.
* The index **must be a positive integer** 1, 2, 3, …

Example: 
* Optional: `list` to show the current tasks list.
* `done 2` marks the 2nd task in the list as done.

### Exiting the program: `bye`
Exits the program.

Format: `bye`

### Saving the data
Rock tasks list data are saved in the hard disk automatically after any command that
changes the data.

There is no need to save manually.

## Command summary
* `todo DESCRIPTION`
* `event DESCRIPTION /at TIME`
* `deadline DESCRIPTION /by TIME`
* `dowithin DESCRIPTION /between START_TIME /and END_TIME`
* `list`
* `delete INDEX`
* `find KEYWORD`
* `done INDEX`
* `bye`