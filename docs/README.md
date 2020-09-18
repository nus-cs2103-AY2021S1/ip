# User Guide

Duke is a **desktop app for tracking down todos, deadlines and events for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, Duke can get your tasks management done faster than traditional GUI apps.

* [Quick Start](#quick-start)
* [Features](#features)
	* [Adding a task](#adding-a-task)
		* [Adding a todo: `todo`](#adding-a-todo-todo)
		* [Adding a deadline: `deadline`](#adding-a-deadline-deadline)
		* [Adding an event: `event`](#adding-an-event-event)
	* [Marking a task as done: `done`](#marking-a-task-as-done-done)
	* [Listing all tasks: `list`](#listing-all-tasks-list)
	* [Locating a task by name: `find`](#locating-a-task-by-name-find)
	* [Displaying all tasks on a specific date: `tasks on`](#displaying-all-tasks-on-a-specific-date-tasks-on)
	* [Archiving a task: `archive`](#archiving-a-task-archive)
	* [Unarchiving a task: `unarchive`](#unarchiving-a-task-unarchive)
	* [Deleting a task: `delete`](#deleting-a-task-delete)
	* [Exiting the program: `bye`](#exiting-the-program-bye)
* [Command Summary](#command-summary)

## Quick Start
1. Ensure you have Java `11` or above installed in your Computer.
2. Download the latest Duke.jar from [here](https://github.com/FH-30/ip/releases/tag/v0.3).
3. Copy the file to the folder you want to use as the home folder for your AddressBook.
4. Double-click the file to start the app. The GUI similar to the one below should appear in a few seconds. **If not able to, you can open terminal in that directory and run: `java -jar <filename>`**

<div align = "center">
  <img src="https://github.com/FH-30/ip/blob/master/docs/Ui.png"/>
</div>

5. Type the command in the command box and press Enter or click Send to execute it. e.g. typing `list` and pressing Enter or clicking Send will list all your current tasks.
Some example commands you can try:
	* `todo wash car` : Adds a task called wash car into your list.
	* `done 1`: Marks the 1st task in the current list.
	* `delete 1`: Deletes the 1st task in the current list.
	* `bye`: Exits the app.
6. Refer to the Features below for details of each command.

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `todo TASK`, `TASK` is a parameter which can be used as `todo wash car`.

* Spacing before and after the command and input will automatically be ignored.<br>
 e.g. the command `[some spacing] todo [some spacing] CS2103T project [some spacing]` will be interpreted as `todo CS2103T project`.

</div> 

### Adding a task

#### Adding a todo: `todo`

Adds a task to the list.

Format: `todo TASK`

Examples:
* `todo wash car`
* `todo clean house`

#### Adding a deadline: `deadline`

Adds a deadline to the list.

Format: `deadline TASK /by dd/mm/yyyy hh:mm`

Examples:
* deadline return book /by 22/08/2020 18:00
* deadline finish project /by 25/08/2020 20:00

#### Adding an event: `event`

Adds an event to the list.

Format: `event TASK /at dd/mm/yyyy hh:mm`

Examples:
* event attend seminar /at 23/09/2020 13:00
* event project meeting /at 26/09/2020 20:00

### Marking a task as done: `done`

Marks a task in the list as done.

Format `done INDEX`

Examples:
* `done 1`
* `done 2`

### Listing all tasks: `list`

Shows a list of all Tasks in the list.

Format: `list`

Additional tags:
* `-a`: Includes archived tasks

Examples:
* `list`
* `list -a`


###  Locating a task by name: `find`

Finds tasks whose description contains the given keyword.

Format: `find KEYWORD`

Examples:
* `find project`
* `find wedding`

### Displaying all tasks on a specific date: `tasks on`

Displays all tasks that occur on the given date.

Format: `tasks on dd/mm/yyyy`

Examples:
* `tasks on 15/09/2020`
* `tasks on 27/08/2020`

### Archiving a task: `archive`

Archives a task in the list.

Format: `archive INDEX`

Examples:
* `archive 1`
* `archive 2`

### Unarchiving a task: `unarchive`

Unarchives a task in the archived list.

Format: `unarchive INDEX`

Examples:
* `unarchive 1`
* `unarchive 2`

### Deleting a task: `delete`

Deletes a task in the list.

Format: `delete INDEX`

Examples:
	
* `delete 1`
* `delete 2`

### Exiting the program: `bye`

Exits the program.

Format: `bye`

### Saving the data

Duke data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Command summary

Action | Format
-------|-------
todo | `todo TASK`
deadline | `deadline TASK /by dd/mm/yyyy hh:mm`
event | `event TASK /at dd/mm/yyyy hh:mm`
done | `done INDEX`
list | `list` or `list -a` to include archived tasks
find | `find KEYWORD`
tasks on | `tasks on dd/mm/yyyy`
archive | `archive INDEX`
unarchive | `unarchive INDEX`
delete | `delete INDEX`
bye | `bye`