# User Guide
Duke is a desktop dog to help you manage tasks in the form of todos, deadlines and events. It is optimised for use via Command Line Interface (CLI). If you can type fast, Duke can help you record your tasks faster than with a traditional GUI.

## Features 
[Add a new Todo](#todo)
### Viewing help: `help`
Displays features and the commands needed to use them.
#### Usage
Format: `/help`
* Instructions will be displayed in the following format - GENERAL DESCRIPTION: input needed.

#Todo
### Add a new ToDo: `todo`
Adds a new ToDo task to the list.
#### Usage
Format: `/todo TASK`
* Adds a ToDo with TASK as the text.
* The ToDo will be marked as undone (`[Not barked yet]`).
* TASK cannot be empty.
* Text before the "/" will be ignored.
* Will be displayed as `TASK`.

Examples:
* `/todo Implement better GUI for CS2103t iP`
* `/todo Head to the gym`

### Add a new Event: `at`
Adds a new Event task to the list.
#### Usage
Format:`TASK /at START_DATETIME END_DATETIME`
* Adds a Event with TASK as text, and START_DATETIME and END_DATETIME as the start and end of said event.
* The Event ToDo will be marked as undone (`[Not barked yet]`)
* TASK cannot be empty.
* START_DATETIME and END_DATETIME have to be in the format **`yyyy-MM-dd HHmm`** where HHmm refers to time in **24-hour** format.
* Will be displayed as `TASK (at: START_DATETIME to END_DATETIME)` with START_DATETIME and END_DATETIME in the format of `dd-MM-yyyy HHmm`.

Examples:
* `Night cycling /at 2020-09-19 0200 2020-09-19 0800`
* `Arts Council meeting /at 2020-09-15 2100 2020-09-16 0030`

### Add a new Deadline: `by`
Adds a new Deadline task to the list.
#### Usage
Format: `TASK /by DUE_DATETIME`
* Adds a Deadline with TASK as the text, and DUE_DATETIME as the due date of the task.
* The Deadline will be marked as undone (`[Not barked yet]`).
* TASK cannot be empty.
* DUE_DATETIME has to be in the format **`yyyy-MM-dd HHmm`** where HHmm refers to time in **24-hour** format.
* Will be displayed as `TASK (by DUE_DATETIME)`, with DUE_DATETIME in the format of `DAY dd-MM-yyyy HHmm`.

Examples:
* `Finish User Guide /by 2020-18-09 2230`
* `Write essay /by 2020-12-09 2359`

### List out current tasks: `list`
Lists out tasks currently in the app.
#### Usage
Format: `/list`
* DONE_STATUS refers to whether a task is done (`Barked`) or undone (`Not barked yet`).
* Todos will be displayed as `INDEX [T] [DONE_STATUS] TASK`.
* Events will be displayed as `INDEX [E] [DONE_STATUS] TASK (at: START_DATETIME to END_DATETIME)`.
* Deadlines will be displayed as `INDEX [D] [DONE_STATUS] TASK (by: DUE_DATETIME)`.

### Search for a Task using a keyword: `find`
Displayes all tasks containing a specific keyword.
#### Usage
Format: `/find KEYWORD`
* Searches lists of tasks and displays any task that contains KEYWORD.
* KEYWORD cannot be empty.
* KEYWORD is not case-specific.
* " " can be a KEYWORD.

Examples:
* `/find submit`
* `/find Arts`

### Delete a Task by index: `delete`
Deletes a task by its number within the list.
#### Usage
Format: `/delete INDEX`
* Deletes a task by its number within the list.
* INDEX has to be the number of an existing task.

Examples:
* `/delete 1` - this would delete the first task on the list.
* `/delete 3` - this would delete the third task on the list.

### Delete a Task by index: `delete`
Deletes a task by its number within the list.
#### Usage
Format: `/delete INDEX`
* Deletes a task by its number within the list.
* INDEX has to be the number of an existing task.

Examples:
* `/delete 1` - this would delete the first task on the list.
* `/delete 3` - this would delete the third task on the list.

### Mark a task as done by index: `done`
Marks a task as done by its number within the list.
#### Usage
Format: `/done INDEX`
* Marks a task as done (`[Barked]`) by its number within the list.
* INDEX has to be the number of an existing task.

Examples:
* `/done 1` - this would mark the first task on the list as done.
* `/done 3` - this would mark the third task on the list as done.

### Update a task by index: `update`
Updates the display text of a task within the list.
#### Usage
Format: `/update INDEX NEW_TEXT`
* Updates task INDEX on the list to display NEW_TEXT.
* Does not change Task type or status.

Examples:
* `/update 1 Submit photos to National Geographic`
* `/udpate 3 Do CS2103t Continuous Integration`

### Save list and close the app: `bye`
Closes the app and saves the list.
#### Usage
Format: `bye`
* Closes the app and saves the list in the save directory as the app.
* If closed without this command, changes to the list will **not** be saved.
