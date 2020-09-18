# DukeLUL User Guide

DukeLUL is a desktop app for managing things that you need to get done. 
Chat with an incredibly annoying LULCAT and get so irritated that you'll
complete your tasks in record time and close the app ASAP. 

* Quick Start
* Features
    * Viewing help: `help`
    * Adding a todo task: `todo`
    * Adding a task with a deadline: `deadline`
    * Adding an event task: `event`
    * Listing all tasks: `list`
    * Finding tasks: `find`
    * Marking a task as done: `done`
    * Deleting a task: `delete`
    * Updating a task: `update`
    * Exiting the program: `bye`
    * Saving the data
* FAQ
* Command summary  
<br />
---

## Quick Start

1. Ensure you have Java 11 or above installed on your computer.

2. Download the latest `DukeLUL.jar` from [here](https://github.com/kaitlynng/ip/releases/tag/v0.1).

3. Copy the file to the folder you want to use as the home folder of your DukeLUL app.

4. Double-click the file to start the app. The GUI similar to the one below should appear in a few seconds.

5. Chat with the LULCAT by typing into the chat box and pressing Enter. Typing `help` and pressing Enter will bring up 
a list of commands you can try.

6. Refer to the Features below for details of available commands.
 

## Features 

This section describes a list of commands that you can use when chatting with LULCAT. 

| :memo: Take note!                                                                                     |
|:------------------------------------------------------------------------------------------------------|
| Words in `UPPER_CASE` are parameters to be supplied by you.                                           |
| * e.g. In `todo TASK_DESCRIPTION`, `TASK_DESCRIPTION` is a parameter which you can supply, like so: `todo Homework` |
|                                                                                                       |
| Items in square brackets are optional.                                                                |
| * e.g. `list [\by DATE_TIME]` can be used as `list` or `list \by 2012-02-02`                          |


### Viewing help: `help`

Displays all the possible commands for the app.

Format: `help`

### Adding a todo task: `todo`

Adds a todo task to the tasklist
Format: `todo TASK_DESCRIPTION`
Examples: 
* `todo Finish homework`
* `todo Clean the house`

### Adding a task with a deadline: `deadline`

Adds a task to the tasklist with a deadline.

Format: `deadline TASK_DESCRIPTION \by DATE_TIME`
* Note that the `DATE_TIME`has to be entered with a `YYYY-MM-dd` or `YYYY-MM-ddThh:mm:ss` format to be
  recognised as a date / datetime by the app. Otherwise, no filtering operations 
  can be performed on it.

Examples: 
* `deadline Project 3 \by 2020-10-05`
* `deadline Buy grocevires \by 2020-09-16T11:02`

### Adding an event task: `event`

Adds an event to the tasklist happening at a given date/time. 

Format: `event EVENT_DESCRIPTION \at DATE_TIME`
* Note that the `DATE_TIME`has to be entered with a `YYYY-MM-dd` or `YYYY-MM-ddThh:mm:ss` format to be
  recognised as a date / datetime by the app. Otherwise, no filtering operations 
  can be performed on it.

Examples: 
* `event Night cycling \at 2020-09-19T02:00`
* `event Dad's birthday \at 2020-10-25`

### Listing all tasks: `list`

Shows a list of all tasks in the tasklist. 

Format: `list [\by DATE_TIME]`
* You can optionally specify a `DATE_TIME`to list all the deadlines and events that are due / happening before the given datetime.
The `DATE_TIME` specified has to be of the format `YYYY-MM-dd` or `YYYY-MM-ddThh:mm:ss`
to be valid.

Examples: 
* `list \by 2020-09-19T02:00`
* `list \by 2020-10-25`

### Finding tasks: `find`

Finds tasks that contains the given keyphrase. 

Format: `find KEYPHRASE`
* The search is not case-sensitive. e.g. `Cats` will match `cats`
* Only the description of tasks / events is searched.
* Only the whole phrase would be matched. e.g. `Happy` or `days` will not match `Happy days`

Examples: 
* `find birthday` returns `John's birthday` and `Weiming's birthday`

### Marking a task as done: `done`

Marks a task in the current tasklist as done.

Format: `done TASK_INDEX`
* `TASK_INDEX` refers to the index of the task you wish to mark as done in the tasklist.
To find the index of the task you would like to mark as done, use the `list` command.
 
Examples:
* `done 3` marks the task at index 3 as done

### Deleting a task: `delete`

Deletes a task from the current tasklist.

Format: `delete TASK_INDEX`
* `TASK_INDEX` refers to the index of the task you wish to delete in the tasklist.
To find the index of the task you would like to delete, use the `list` command.

Examples: 
* `delete 3` deletes the task at index 3 in the tasklist

### Updating a task: `update`

Updates the description or datetime of a task currently in the tasklist.

Format: `update TASK_INDEX [\date] NEW_VALUE`
* `TASK_INDEX` refers to the index of the task you wish to update in the tasklist.
  To find the index of the task you would like to update, use the `list` command.
* If `\date` is specified, the date of the task will be updated to `NEW_VALUE`, otherwise
the description of the task will be updated to `NEW_VALUE`. If the task at the given index
does not have a specified datetime, an error will be thrown.

Examples:
* `update 3 Chinese homework` updates the description of the task at index 3 to "Chinese homework"
* `update 3 \date 2020-09-02T12:00:05` updates the datetime of the task at index 3 to "Sep 2 2020, 12:00:05 PM"

### Exiting the program: `bye`

Exits the program.

Format: `bye`

### Saving the data

DukeLUL automatically saves the current tasklist to the hard drive after any command that 
modifies the tasklist. There is no need to save manually.
<br/>

---

## FAQ

**Q:** How can I inform the developers of bugs in the program?

**A:** You can open a new issue in this project's Github repository. These issues will be 
reviewed on a regular basis. Click [here](https://docs.github.com/en/enterprise/2.15/user/articles/creating-an-issue) 
to find out how to create a Github issue.

---

## Command Summary

