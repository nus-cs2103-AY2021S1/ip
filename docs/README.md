# User Guide
## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `duke.jar` from [here](https://github.com/ZoroarkDarkrai/ip/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your Duke.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds.<br>
   ![Ui](./Ui.png)

1. Type the command in the command box and press Enter to execute it. 

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

**Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add /n NAME`, `NAME` is a parameter which can be used as `add /n John Doe`.
* `DATE` should be in `YYYY-MM-DD` format.


### Listing all tasks : `list`

List all the tasks the user has.

Format: `list`


### Adding a to do task: `todo`

Adds a task of type to do to the task list.

Format: `todo DESCRIPTION`

Examples:
* `todo do laundry`

### Adding an event task: `event`

Adds a task of type event to the task list.

Format: `event DESCRIPTION /at DATE`

Examples:
* `event team meeting /at 2020-05-06`

### Adding a deadline task: `deadline`

Adds a task of type deadline to the task list.

Format: `deadline DESCRIPTION /by DATE`

Examples:
* `deadline art project /by 2021-03-15`


### Updating a task description: `update`

Edits an existing task description in the Duke.

Format: `update description INDEX DESCRIPTION`

Examples:
*  `update description 1 sleep`

### Updating a task date: `update`

Edits an existing task date in the Duke.

Format: `update date INDEX DESCRIPTION`

* Only applicable for tasks of type event or deadline.

Examples:
*  `update date 1 2022-08-09`

### Locating tasks by description: `find`

Finds tasks whose descriptions contain the given keyword.

Format: `find KEYWORD`

* The search is case-sensitive. e.g `hans` will not match `Hans`

Examples:
* `find play`

### Marking a task as done : `done`

Marks as done the specified task from the Duke.

Format: `done INDEX`

Examples:
* `done 2`

### Deleting a task : `delete`

Deletes the specified task from the Duke.

Format: `delete INDEX`

* Deletes the person at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd task in the Duke


### Exiting the program : `bye`

Exits the program.

Format: `bye`

### Saving the data

Duke data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.