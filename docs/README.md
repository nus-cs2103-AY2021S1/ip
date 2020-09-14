# User Guide
Duke *DuiDui* is a desktop app for **managing tasks**, 
optimized for use via a Command Line Interface (CLI). 
* [Quick start](#quick-start)
* [Features](#features)
    * [Adding a todo task `todo`](#adding-a-todo-task-todo)
    * [Adding a deadline `deadline`](#adding-a-deadline-deadline)
    * [Adding an event `event`](#adding-an-event-event)
    * [Marking a task as done `done`](#marking-a-task-as-done-done)
    * [Finding the specific tasks `find`](#finding-the-specific-tasks-find)
    * [Listing all the tasks `list`](#listing-all-the-tasks-list)
    * [Deleting a specific task `delete`](#deleting-a-specific-task-delete)
    * [Exiting the system `bye`](#exiting-the-system-bye)

### Quick start 
1. Ensure you have Java `11` or above installed in your computer.
2. Download the latest `duke.jar` from here.
3. Copy the file to the folder you want to use as the 
home folder for your Duke.
4. Double-click the file to start the app. The GUI similar to 
the below should appear in a few seconds. 
[![Enter page]](https://github.com/YangYue128-helen/ip/blob/master/docs/UiImage/EnterPage.png)

### Features 
* Words in `UPPER_CASE` are the parameters to be supplied by the user.  
e.g. `todo DESCRIPTION`, `DESCRIPTION` is a parameter which can be used as `todo read book`.
* The valid input of time is `YYYY-MM-DD HH:SS`
### Adding a todo task `todo` 
Adds a todo task to the task list. You can also use command: `TODO`

Format: `todo DESCRIPTION`

Examples:
* `todo have lunch`
* `TODO sleep at 10`

### Adding a deadline `deadline`
Adds a deadline to the task list. You can also use commands:
`Deadline`, `ddl`, `DDL`

Format: `deadline DESCRIPTION /by YYYY-MM-DD HH:SS`

Examples:
* `deadline op reflection /by 2020-10-12 19:00`
* `ddl ps1 /by 2020-12-12 23:59`

### Adding an event `event`
Adds an event to the task list. You can also use commands:
`Event`, `EVENT`, `E`

Format: `event DESCRIPTION /at YYYY-MM-DD HH:SS`

Examples:
* `event concert /at 2020-10-123 12:00`
* `E speech /at 2020-10-23 19:00`

### Marking a task as done `done`
Marks the task in the list with index `i` as done. You can also use commands:
`Done`, `finish`, `complete`

Format: `done i`

Examples:
* `done 1`
* `finish 5`

### Finding the specific tasks `find`
Finds the tasks contains `KEYWORD` in the task list. You can also use commands:
`FIND`, `fnd`

Format: `find KEYWORD`

Examples:
* `find ps`
* `fnd assignment`

### Listing all the tasks `list`
Lists all the tasks in the task list. You can also use command:
`lst`

Format: `list`

### Deleting a specific task `delete`
Deletes the task in the list with index `i`. You can also use commands:
`Delete`, `clear`, `clr`

Format: `delete i`

Examples:
* `delete 1`
* `clear 2`

### Exiting the system `bye`
Exits Duke DuiDui. You can also use commands:
`quit`, `exit`

Format: `bye`

