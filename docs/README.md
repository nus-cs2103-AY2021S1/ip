# User Guide

Duke is a **desktop app for managing tasks, optimised for use via a command line interface** (CLI)
while still having the benefits of a Graphical User Interface (GUI).
If you can type fast, Duke can manage and organise your tasks faster than traditional GUI apps. The
chat interface also gives users a more personal feeling.

- [Quick start](#quick-start)
- [Features](#features)
    1. [Adding a todo: `todo`](#adding-a-todo-todo)
    2. [Adding a deadline: `deadline`](#adding-a-deadline-deadline)
    3. [Adding an event: `event`](#adding-an-event-event)
    4. [Tagging a task: `tag`](#tagging-a-task-tag)
    5. [Listing all tasks: `list`](#listing-all-tasks-list)
    6. [Listing today's tasks: `today`](#listing-todays-task-today)
    7. [Listing uncompleted tasks: `uncompleted](#listing-uncompleted-tasks-uncompleted)
    8. [Listing completed tasks: `completed`](#listing-completed-tasks-completed)
    9. [Listing overdue tasks: `overdue](#listing-overdue-tasks-overdue)
    10. [Searching for a task: `find`](#searching-for-a-task-find)
    11. [Marking a task as done: `done`](#marking-a-task-as-done-done)
    12. [Deleting a task: `delete`](#deleting-a-task-delete)
    13. [Exiting the program: `bye`](#exiting-the-program-bye)
    14. [Providing a list of possible commands: `help`](#providing-a-list-of-possible-commands-help)
    15. [Saving the data](#saving-the-data)
- [Command summary](#command-summary)

---
    
## Quick start
1. Ensure that you have Java `11` or above installed on your computer.
2. Download the latest Duke Jar from here.
3. Copy the file to the folder you want to use as the *home folder* for your Duke program.
4. Double click the file to start the app for Windows, or run `java -jar duke.jar` for Mac.
5. Type the command in the command box and press Enter to execute it.

Some example commands you can try:
  - `list`: Lists all current existing tasks.
  - `todo run 5KM`: Adds a todo task `run 5KM` to the list of tasks.
  - `today`: Lists down all time based tasks which has a datetime of the current day.
  - `delete 1`: Deletes the first task shown in the list.
  - `done 3`: Marks the third task in the list as done.
  - `bye`: Exits the app
6. Refer to the [Features](#Features) below for details of each command.

---

## Features 
> ℹ️  **Notes about the command format:**
> - Words in `UPPER_CASE` are the parameters to be supplied by the user.  
> eg. in `todo DESCRIPTION`, `DESCRIPTION` is a parameter which can be used as `todo run 5KM`.
> - Parameters must be in the order specified.
> - Command is case-insensitive. 

### Adding a todo: `todo`
Adds a todo task to the list of tasks.  

Format: `todo DESCRIPTION`

Example of usage:  
- `todo run 5KM`  
- `todo read book`

### Adding a deadline: `deadline`
Adds a task which has a deadline to the list of tasks.  

Format: `deadline DESCRIPTION /by DATETIME`

> ⚠️  For `DATE TIME` parameters in both deadline and event
> features, only the following formats are accepted: 

- Date formats:  

Format | Example
------ | -------
d/M/yyyy | 9/10/2020
d MMM yyyy | 9 Oct 2020
d MMMM yyyy | 9 October 2020
d-M-yyyy| 9-10-2020
d/M/yyyy | 2020-10-9

- Time formats:

Format | Example
------ | -------
H:mm | 3:30 
h:mm a | 3:15 PM
h:mma | 3:15PM
Hmm | 2359
HH:mm | 15:30
Hmm | 1530
ha | 9PM

Example of usage:  
`deadline return book /by 15/09/2020 2359`  

> 👍  All dates and time are displayed as `dd MMM yyyy h:mm A`

### Adding an event: `event`
Adds an event to the list of tasks.  

Format: `event DESCRIPTION /at DATETIME`

> ℹ️  Refer to [Adding a deadline: `deadline`](#adding-a-deadline-deadline)
> to find out the accepted date and time formats.

Example of usage:  
`event meeting /at 9 Oct 2020, 10/10/2020 4:30 PM`  

### Tagging a task: `tag`
Sets a tag to a task item.

Format: `tag INDEX #TAGNAME`

Example of usage:
- `tag 1 #sports`
- `tag 2 #CS2103T`

### Listing all tasks: `list`
Shows a list of all existing tasks.  

Format: `list`

### Listing today's task: `today`
Shows a list of all time-based tasks, such as event and deadline tasks, 
that are happening today.  

Format: `today`

### Listing uncompleted tasks: `uncompleted`
Shows a list of all tasks that have not been completed yet.

Format: `uncompleted`.

### Listing completed tasks: `completed`
Shows a list of all tasks that have already been completed yet.

Format: `completed`.

### Listing overdue tasks: `overdue`
Shows a list of all tasks that are overdue and have not been completed.

Format: `overdue`.

### Searching for a task: `find`
Finds tasks whose description matches the keyword.  

Format: `find KEYWORD`

- Keywords are case-sensitive.
- Only the task description is searched.
- Only tasks matching all keywords will be returned.

Examples:  
- `find book`  

### Marking a task as done: `done`
Marks a task as completed.  

Format: `done INDEX` 

> ℹ️  `INDEX` refers to the index of the task
> as displayed when executing `list`. 

Example of usage:  
`done 1`   

### Deleting a task: `delete`
Deletes a task from the list of tasks.  

Format: `delete INDEX` 

> ℹ️  `INDEX` refers to the index of the task
> as displayed when executing `list`. 

Example of usage:  
`delete 1`  

### Providing a list of possible commands: `help`
Provides a list of possible commands that the user can use within the application.

Format: `help`

### Exiting the program: `bye`
Exits the program.  

Format: `bye`

### Saving the data
All tasks are saved in the hard drive automatically after every
command and once the program has terminated as well. 
There is no need to save manually.

> 💡  Data is stored in */data/duke.txt* relative to the 
> *home folder*.

___

## Command summary
Action | Format | Examples
------ | ------ | --------
`todo` | `todo DESCRIPTION` | `todo read book`
`deadline` | `deadline DESCRIPTION /at DATE TIME` | `deadline return book /by 15/9/2020 2359`
`event` | `event DESCRIPTION /at (DATE TIME` | `event meeting /at 15/9/2020 1100`
`tag` | `tag INDEX #TAGNAME` | `tag 1 #sports`
`list` | `list` 
`today` | `today`
`uncompleted` | `uncompleted`
`completed` | `completed`
`overdue` | `overdue`
`find` | `find KEYWORDS` | `find book`
`done` | `done INDEX` | `done 3`
`delete` | `delete INDEX` | `delete 2`
`bye` | `bye`
`help` | `help`
