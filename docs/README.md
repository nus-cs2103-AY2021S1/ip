# User Guide
Duke is a **desktop app for managing tasks, optimised for use via a command line interface** (CLI) 
while still having the benefits of a Graphical User Interface (GUI).
If you can type fast, Duke can manage and organise your tasks faster than traditional GUI apps.

- [Quick start](#quick-start)
- [Features](#features)
    1. [Adding a todo: `todo`](#adding-a-todo--todo)
    2. [Adding a deadline: `deadline`](#adding-a-deadline--deadline)
    3. [Adding an event: `event`](#adding-an-event--event)
    4. [Listing all tasks: `list`](#listing-all-tasks--list)
    5. [Searching for a task: `find`](#finding-tasks-by-keyword--find)
    6. [Deleting a task: `delete`](#deleting-a-task--delete)
    7. [Marking a task as done: `done`](#completing-a-task--done)
    8. [Updating a task: `update`](#updating-a-task--update)
    9. [Updating a task's description: `updateDesc`](#updating-the-description-of-a-task--updatedesc)
    10. [Updating a Deadline/Event's time/date: `updateTime`](#updating-the-timedate-of-deadlineevent--updatetime) 
    11. [Exiting the program: `bye`](#exiting-the-program)
    12. [Saving the data](#saving-the-data)
- [Command summary](#command-summary)


## Quick start
1. Ensure that you have Java `11` or above installed on your computer.
2. Download the latest Duke Jar from [here](https://github.com/chiabs/ip/releases/tag/A-Release).
3. Copy the file to the folder you want to use as the *home folder* for your Duke program.
4. Double click the file to start the app for Windows, or run `java -jar duke.jar` for Mac.
You should see the below GUI after a few seconds:
![landing page](/docs/Ui.png)
5. Type the command in the command box and press Enter to execute it.

Some example commands you can try:
  - `todo study cs2103t`: Adds a todo `study cs2103t` to the list of tasks.
  - `list`: Lists all tasks.
  - `done 8`: Marks the eight task in the list as done.
  - `bye`: Exits the app
6. Refer to the [Features](#Features) below for details of each command.


## Features 
> ℹ️  **Notes about the command format:**
> - Words in `UPPER_CASE` are the parameters to be supplied by the user.  
> eg. in `todo DESCRIPTION`, `DESCRIPTION` is a parameter which can be used as `todo study`.
> - Items with `...` after them can be used multiple times but at least once, separated by a comma.  
> eg `find KEYWORD...` can be used as `find study` or `find study cs2103t`.
> - Parameters must be in the order specified.
> - Command is case-insensitive. 

#### Adding a todo : `todo`
Adds a ToDo. A ToDo is a task with only a description.

Format: `todo d/DESCRIPTION`

Examples:
* `toDo d/read a book`
* `toDo d/study for CS2103T`

#### Adding a Deadline : `deadline`
Adds a Deadline. A Deadline is a task with a description and the deadline by 
which the task must be completed.

Format: `deadline d/DESCRIPTION /by t/TIME_DATE`

> ⚠️  For `TIME_DATE` parameters in both deadline and event
> features, Duke is accepts all formats including text, Duke is
> also able to recognize the date format YYYY-MM-DD and display it as 
> dd-MMM-yyyy. 

Examples:
* `deadline d/CS2103T Submission /by t/2021-12-26`
* `deadline d/CS2100 homework /by t/tmr`

> 💡  Tip: If you uncertain, you can key the date as unknown, and update it later on!

#### Adding an Event : `event`
Adds an Event. An Event is a task with a description and the time/day when the 
event is occurring.

Format: `event d/DESCRIPTION /at t/TIME_DATE`

> ℹ️  Refer to [Adding a deadline: `deadline`](#adding-a-deadline--deadline)
> to find out about date formats.

Examples:
* `event d/CS2103T Lecture /at t/2021-12-26`
* `event d/CS2100 Tutorial /at t/tmr`

#### Listing all Tasks : `list`
Shows a list of all Tasks stored.

Format: `list`

#### Finding Tasks by keyword : `find` 
Finds Tasks whose descriptions contain the all given keywords.

Format: `find k/KEYWORD`

- Keywords are case-sensitive.
- The order of the keywords matters, that is, `read book` or 
`book read` will show different results.
- Only the task description is searched.
- Partial words cannot be matched (eg `boo` will not match `book`);
- Only tasks matching all keywords will be returned.

Examples:
* `find k/CS2100` 

Expected outcome:  
&nbsp; `Here are the matching tasks in your list:`  

&nbsp;&nbsp; `1. [D][✘] CS2100 Assignment 1 (by 12 Sep 2020 2:00 PM)`

&nbsp;&nbsp; `2. [E][✘] Finals for CS2100 (at 15 Oct 2020 2:00 PM)`

#### Deleting a Task : `delete`
Deletes the specified Task at the index.

Format: `delete i/INDEX`

> ℹ️  `INDEX` refers to the index of the task
> shown in the displayed person list. 

Examples:
* `delete i/5` deletes the 5th task in Duke.
* `delete i/2` deletes the 2nd task in Duke.

### Completing a Task : `done`
Marks a task as done.

Format: `done i/INDEX` 

> ℹ️  `INDEX` refers to the index of the task
> shown in the displayed person list. 

Example of usage:  
- `done 1` marks the 1st task in Duke as done.

#### Updating a Task : `update`
Updates a Task at the specified index and completely overwrites the Task to be updated.

Format: `update i/INDEX t/TASK`

> ℹ️  `TASK` refers to the updated task, and
> its format should be one of the Tasks formats.
> Refer to 
> - [Adding a todo: `todo`](#adding-a-todo--todo), 
> - [Adding a deadline: `deadline`](#adding-a-deadline--deadline),
> - [Adding an event: `event`](#adding-an-event--event)

Examples:
* `update i/1 t/event CS2103 Lecture /at t/2021-12-05`

Expected Outcome: 

`[E][✘] Finals for CS2100 (at 15 Oct 2020)`

&nbsp;`updated to`

`[E][✘] CS2103 Lecture (at 05 Dec 2020)`

#### Updating the time/date of Deadline/Event : `updateTime`
Updates the date/time of a Deadline/Event at the specified index.

Format: `updateTime i/INDEX t/TIME_DATE`

Examples:
* `updateTime i/1 t/2021-12-05`

Expected Outcome:

`[E][✘] Finals for CS2100 (at 15 Oct 2020)`

&nbsp;`updated to`

`[E][✘] Finals for CS2100 (at 05 Dec 2020)`

#### Updating the description of a Task : `updateDesc`
Updates the description of a Task at the specified index.

Format: `updateDesc i/INDEX d/DESCRIPTION`

Examples:
* `updateDesc i/5 d/study`

Expected Outcome:

`[E][✘] Finals for CS2100 (at 15 Oct 2020)`

&nbsp;`updated to`

`[E][✘] study (at 15 Oct 2020)`

#### Exiting the program
Exits the program.

Format: `Bye`

#### Saving the data
Duke data are saved in the hard disk automatically after any command that changes the data.
There is no need to save manually.

> 💡  Data is stored in */duke.txt* relative to the 
> *home folder*.

## Command summary
Action | Format | Examples
------ | ------ | --------
`todo` | `todo DESCRIPTION` | `todo read book`
`deadline` | `deadline DESCRIPTION /by TIME_DATE` | `deadline assignment /by 2021-05-12`
`event` | `event DESCRIPTION /at TIME_DATE` | `event meeting /at tmr`
`list` | `list` 
`find` | `find KEYWORDS` | `find read`, `find read book`
`done` | `done INDEX` | `done 2`
`delete` | `delete INDEX` | `delete 1`
`update` | `update INDEX TASK` | `update 1 todo read book` 
`updateTime` | `updateTime INDEX TIME_DATE` | `updateTime 2 2020-12-01`
`updateDesc` | `updateDesc INDEX DESCRIPTION` | `updateDesc 5 study`
`bye` | `bye`