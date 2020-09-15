# User Guide

Duke is a **desktop app for managing tasks, optimised for use via a command line interface** (CLI)
while still having the benefits of a Graphical User Interface (GUI).
If you can type fast, Duke can manage and organise your tasks faster than traditional GUI apps. The
chat interface also gives users a more personal feeling.

- [Quick start](#quick-start)
- [Features](#features)
    1. [Adding a todo: `todo`](#adding-a-todo-todo)
    2.  [Adding a deadline: `deadline`](#adding-a-deadline-deadline)
    3. [Adding an event: `event`](#adding-an-event-event)
    4. [Confirming a date/time of an event: `confirm`](#confirming-a-datetime-of-an-event-confirm)
    5. [Listing all tasks: `list`](#listing-all-tasks-list)
    6. [Listing today's tasks: `today`](#listing-todays-task-today)
    7. [Searching for a task: `find`](#searching-for-a-task-find)
    8. [Marking a task as done: `done`](#marking-a-task-as-done-done)
    9. [Deleting a task: `delete`](#deleting-a-task-delete)
    10. [Exiting the program: `bye`](#exiting-the-program-bye)
    11. [Saving the data](#saving-the-data)
- [Command summary](#command-summary)

---
    
## Quick start
1. Ensure that you have Java `11` or above installed on your computer.
2. Download the latest Duke Jar from [here](https://github.com/dianneloh9/ip/releases/tag/A-Release).
3. Copy the file to the folder you want to use as the *home folder* for your Duke program.
4. Double click the file to start the app for Windows, or run `java -jar duke.jar` for Mac.
You should see the below GUI after a few seconds:
![landing page](/docs/landing.png)
5. Type the command in the command box and press Enter to execute it.

Some example commands you can try:
  - `todo read book`: Adds a todo `read book` to the list of tasks.
  - `list`: Lists all tasks.
  - `done 1`: Marks the first task in the list as done.
  - `delete 1`: Deletes the first task shown in the list.
  - `bye`: Exits the app
6. Refer to the [Features](#Features) below for details of each command.

---

## Features 
> ℹ️  **Notes about the command format:**
> - Words in `UPPER_CASE` are the parameters to be supplied by the user.  
> eg. in `todo DESCRIPTION`, `DESCRIPTION` is a parameter which can be used as `todo read book`.
> - Items in square brackets are optional.  
> eg `event DESCRIPTION /at DATE [TIME]` can be
> used as `event meeting /at 9/10/2020` or `event meeting /at 9/10/2020 2:00 PM`
> - Items with `...` after them can be used multiple times but at least once, separated by a comma.  
> eg `find KEYWORD...` can be used as `find read` or `find read book`.
> - Parameters must be in the order specified.
> - Command is case-insensitive. 

### Adding a todo: `todo`
Adds a todo to the list of tasks.  
Format: `todo DESCRIPTION`

Example of usage:  
`todo read book`  

Expected outcome:  
&nbsp; `Got it. I've added this task:`  
&nbsp;&nbsp; `[T][✘] read book`  
&nbsp; `Now you have 2 tasks in the list.`

### Adding a deadline: `deadline`
Adds a deadline to the list of tasks.  
Format: `deadline DESCRIPTION /by DATE [TIME]`

> ⚠️  For `DATE [TIME]` parameters in both deadline and event
> features, only the following formats are accepted: 

- Date formats:  

Format | Example
------ | -------
dd/mm/yyyy | 9/10/2020
dd MMM yyyy | 9 Oct 2020
dd MMMM yyyy | 9 October 2020
dd-mm-yyyy | 9-10-2020
yyyy-mm-dd | 2020-10-9

- Time formats:

Format | Example
------ | -------
hh:mm A | 3:30 PM
HH:mm | 15:30
Hmm | 1530

> 💡  Tip: It is not necessary to add a time.

Example of usage:  
`deadline finish CS2103 User Guide /by 9/10/2020 2:00 PM`  

Expected outcome:  
&nbsp; `Got it. I've added this task:`  
&nbsp;&nbsp; `[D][✘] finish CS2103 User Guide (by 9 Oct 2020 2:00 PM)`  
&nbsp; `Now you have 3 tasks in the list.`

> 👍  All dates and time are displayed as `dd MMM yyyy h:mm A` (as above)
> for easy reading.

### Adding an event: `event`
Adds an event to the list of tasks.  
Format: `event DESCRIPTION /at (DATE [TIME])... `

> 💡  `...` indicates that `DATE [TIME]` can be repeated
> multiple times (but at least once), separated by a comma.
> Add multiple timings if you want to store a tentative schedule
> to be confirmed later.

> ℹ️  Refer to [Adding a deadline: `deadline`](#adding-a-deadline-deadline)
> to find out the accepted date and time formats.

Example of usage:  
`event meeting /at 9 Oct 2020, 10/10/2020 4:30 PM`  

Expected outcome:  
&nbsp; `Got it. I've added this task:`  
&nbsp;&nbsp; `[E][✘] meeting (at [9 Oct 2020, 10 Oct 2020 4:30 PM])`  
&nbsp; `Now you have 2 tasks in the list.`

### Confirming a date/time of an event: `confirm`
Confirms the timing of an event with a tentative schedule (multiple timings).  
Format: `confirm TASK_INDEX DATE_TIME_INDEX`  

Example of usage:  
Before command, suppose there was originally an event
`1. [E][✘] meeting (at [9 Oct 2020, 10 Oct 2020 4:30 PM])`
and you want to confirm the timing `10 Oct 2020 4:30 PM`, which 
is the second timing.  
Command: `confirm 1 2`

Expected outcome:
&nbsp; `Nice! I've confirmed the date for this event`  
&nbsp;&nbsp; `[E][✘] meeting at 10 Oct 2020 4:30 PM)`

### Listing all tasks: `list`
Shows a list of all tasks.  
Format: `list`

Example of usage:  
`list`  

Expected outcome:  
&nbsp; `Here are the tasks in your list:`  
&nbsp;&nbsp; `1. [T][✘] read book`  
&nbsp;&nbsp; `2. [D][✘] finish CS2103 User Guide (by 9 Oct 2020 2:00 PM)`  
&nbsp;&nbsp; `3. [E][✘] meeting (at [9 Oct 2020, 10 Oct 2020 4:30 PM])`  
&nbsp; `Now you have 3 tasks in the list.`

### Listing today's task: `today`
Shows a list of all events and deadlines that are happening today.  
Format: `today`

Example of usage:  
`today`

Expected outcome:  
&nbsp; `Here are the tasks today:`  
&nbsp;&nbsp; `1. [D][✘] finish CS2103 User Guide (by 9 Oct 2020 2:00 PM)`  

> ⚠️  Note that events with tentative schedules (more than one timing specified)
> that have not been confirmed will not be displayed.

### Searching for a task: `find`
Finds tasks whose description matches all the keywords.  
Format: `find KEYWORD...`

- Keywords are case-sensitive.
- The order of the keywords does not matter, that is, `read book` or 
`book read` will show the same result.
- Only the task description is searched.
- Partial words can be matched (eg `boo` will match `book`);
- Only tasks matching all keywords will be returned.

Examples:  
- `find finish Guide`  

Expected outcome:  
&nbsp; `Here are the matching tasks in your list:`  
&nbsp;&nbsp; `1. [D][✘] finish CS2103 User Guide (by 9 Oct 2020 2:00 PM)`

### Marking a task as done: `done`
Marks a task as done.  
Format: `done INDEX` 

> ℹ️  `INDEX` refers to the index of the task
> as displayed when executing `list`. 

Example of usage:  
`done 1`  

Expected outcome:  
&nbsp; `Nice! I've marked this task as done:`  
&nbsp;&nbsp; `[T][✓] read book`  

### Deleting a task: `delete`
Deletes a task.  
Format: `delete INDEX` 

> ℹ️  `INDEX` refers to the index of the task
> as displayed when executing `list`. 

Example of usage:  
`delete 1`  

Expected outcome:  
&nbsp; `Noted. I've removed this task:`  
&nbsp;&nbsp; `[T][✓] read book`  

### Exiting the program: `bye`
Exits the program.  
Format: `bye`

### Saving the data
All tasks are saved in the hard drive automatically after every
command. There is no need to save manually.

> 💡  Data is stored in */data/duke.txt* relative to the 
> *home folder*.

___

## Command summary
Action | Format | Examples
------ | ------ | --------
`todo` | `todo DESCRIPTION` | `todo read book`
`deadline` | `deadline DESCRIPTION /at DATE [TIME]` | `deadline study /by 9/10/2020`
`event` | `event DESCRIPTION /at (DATE [TIME])...` | `event meeting /at 9/10/2020 2:00 PM, 10 Oct 2020 1530`
`confirm` | `confirm INDEX` | `confirm 1`
`list` | `list` 
`today` | `today`
`find` | `find KEYWORDS...` | `find read`, `find read book`
`done` | `done INDEX` | `done 2`
`delete` | `delete INDEX` | `delete 1`
`bye` | `bye`