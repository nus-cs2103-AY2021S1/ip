# User Guide

Butler is a **desktop app for managing tasks, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, Butler can manage your tasks faster than traditional GUI apps.
This user guide format is heavily referenced from https://se-education.org/addressbook-level3/UserGuide.html

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `butler.jar` from [here](https://github.com/hopinxian/ip/releases).

1. Copy the file to the folder you want to use as the _home folder_ for Butler.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note that the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box at the bottom of the screen and press Enter to execute it. e.g. typing **`list`** and pressing Enter will give you a current list of tasks.<br>
   Some example commands you can try:

   * **`list`** : Lists all tasks.

   * **`deadline`**`submit math assignment /by 2020-09-24` : Adds a deadline task to `submit math assignment` by `24 September 2020` to the task list.

   * **`delete`**`3` : Deletes the 3rd task shown in the current list.

   * **`undo`**`1` : Undo the most recent change to the task list.

   * **`bye`** : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

**Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `todo SUMMARY`, `SUMMARY` is a parameter which can be used as `todo Water the gardens`.

* Items with `…`​ after them can be used multiple times, excluding zero times.<br>
  e.g. `INDEX…​` can be used as `1`, `2 3 4` etc.

### Adding a todo task: `todo`

Adds a todo task to the task list.

Format: `todo SUMMARY`

Examples:
* `todo Water the garden`
* `todo Walk the dog`

### Adding a deadline task: `deadline`

Adds a deadline task to the task list. Dates are to be given in YYYY-MM-DD format.

Format: `deadline SUMMARY /by DEADLINE`

Examples:
* `deadline Submit math assignment /by 2020-12-12`
* `deadline Send in resume and cover letter /by 2020-10-31`

### Adding an event task: `event`

Adds an event task to the task list. Dates are to be given in YYYY-MM-DD format.

Format: `deadline SUMMARY /at STARTDATE ENDDATE`

Examples:
* `event Science convention at Expo /at 2020-12-12 2020-12-15`
* `event Orientation camp at NUS /at 2020-10-31 2020-11-01`

### Listing all tasks : `list`

Shows a list of all tasks in the task list.

Format: `list`

### Locating tasks by summary: `find`

Finds tasks whose summaries contains the given keyword.

Format: `find KEYWORD`

* The search is case-sensitive. e.g `submit` will not match `Submit`
* The order of words within the keyword matters. e.g. `math assignment` will not match `assignment math`
* Only the summary is searched.
* Tasks whose summaries contain keyword completely will be returned.
  e.g. `submit mathematics` will return `submit mathematics assignment` but not `submit math`

Examples:
* `find assignment` returns `math assignment` and `english assignment`
* `find math assignment` returns `math assignment`, `submit math assignment`
  
### Deleting a task : `delete`

Deletes the specified task from the task list.

Format: `delete INDEX`

* Deletes the person at the specified `INDEX`.
* The index refers to the index number shown in the displayed task list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `delete 2` deletes the 2nd task in the task list.

### Completing a task : `done`

Marks the specified task as complete.

Format: `done INDEX...`

* Multiple tasks can be marked as complete in the same command.<br> e.g. `done 2 3 5` marks tasks 2, 3 and 5 as complete. 

Examples:
* `done 1` marks the 1st task as complete.

### Undo a command : `undo`

Undo the specified number of changes to the task list.

Format: `undo INDEX`

* Undoes INDEX number of changes to the task list.
* Commands that can be undone are `todo`, `deadline`, `event`, `delete`, `done`.
* The index **must be a positive integer** 1, 2, 3, …​
* If the previous command is `done 1 2`, `undo 1`, marks both tasks 1 and 2 as incomplete.

Examples: 
* `undo 2` undoes the recent 2 changes to the task list.

### Exiting the program : `bye`

Exits the program.

Format: `bye`

### Saving the data

Butler data are saved in the hard disk automatically after every command. There is no need to save manually.

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: From the data folder inside the same folder as your program, copy `tasks.txt` and put it into `data/tasks.txt` of the new location.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**todo** | `todo SUMMARY` <br> e.g., `todo Water the garden`
**deadline** | `deadline SUMMARY /by DEADLINE` <br> e.g., `deadline Submit math assignment /by 2020-12-12`
**event** | `deadline SUMMARY /at STARTDATE ENDDATE`<br> e.g., `event Orientation camp at NUS /at 2020-10-31 2020-11-01`
**delete** | `delete INDEX` <br> e.g., `delete 3`
**done** | `done INDEX...` <br> e.g., `done 2 4 5`
**undo** | `undo INDEX` <br> e.g.,`undo 3`
**find** | `find KEYWORD`<br> e.g., `find assignment`
**list** | `list`
**bye** | `bye`