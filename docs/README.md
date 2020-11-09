# User Guide

Duke is a **desktop app for managing your tasks, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Grahpical User Interface (GUI). If you can type fast, Duke can improve your efficiency in managing your tasks.

* [Quick Start](#quick-start)
* [Features](#features)
  * [Adding a ToDo task: `todo`](#adding-a-todo-task-todo)
  * [Adding a Deadline task: `deadline`](#adding-a-deadline-task-deadline)
  * [Adding a Event task: `event`](#adding-a-event-task-event)
  * [Adding a Recurring task: `recurring`](#adding-a-recurring-task-recurring)
  * [Listing all tasks: `list`](#listing-all-tasks-todo)
  * [Marking task as done: `done`](#marking-task-as-done-done)
  * [Marking task as not done: `undo`](#marking-task-as-not-done-undo)
  * [Finding a task: `find`](#finding-a-task-find)
  * [Deleting a task: `delete`](#deleting-a-task-delete)
  * [Exiting the program: `bye`](#exiting-the-program-bye)
 * [Saving the data](#saving-the-data)
 * [Command Summary](#command-summary)

## Quick Start
  1. Ensure that you have Java `11` or above installed in your Computer.
  1. Download the latest Duke.jar from here.
  1. Copy the file to the folder you want to use as the *home folder* for your Duke app.
  1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.
  1. Type the command in the command box and press Enter to execute it.
  1. Refer to the [Features](#features) below for details for each command.
  
# Features

### Adding a ToDo task: `todo`  

Adds a todo task to the list of the tasks.
Format: `todo [TASK]`

Example of usage: 
  * `todo` return book
  * `todo` read book
  
  
### Adding a Deadline task: `deadline`  

Adds a task with a dealine to the list of the tasks.
Format: `deadline [TASK] /by [DD-MM-YYYY HHMM]`
        `deadline [TASK] /by [YYYY-MM-DD HHMM]`
  * DD is day, MM is month, YYYY is year, HH is hour in 24H format, MM is minutes
  * No other combination of date is allowed except for the mentioned.

Example of usage: 
  * `deadline` return book /by 4-6-2020 1800
  * `deadline` read book /by 2020-6-4 1800
  
  
### Adding a Event task: `event`  

Adds a event task to the list of the tasks.
Format: `event [TASK] /at [TIME]`

Example of usage: 
  * `event` return book /by sunday
  * `event` read book /by wednesday


### Adding a Recurring task: `recurring`  

Adds a recurring task to the list of the tasks. Task will undo itself automatically with the time and frequency given.
Format: `recurring [FREQUENCY] [DESCRIPTION] [TIME]`
  * Frequency can only be `daily`, `weekly`, `monthly`.
  * `TIME` must be in 24 hours
  
Example of usage: 
  * `recurring` daily return book 1600 
  * `recurring` weekly return book 1600 
  * `recurring` monthly return book 1600 
  
  
### Listing all tasks: `list`

Shows a list of all the tasks.

Format: `list`
  
Example of usage: 
  * `list`
  
  
### Marking task as done: `done`

Marks the task as done.

Format: `done [TASK NUMBER]`
 * `TASK NUMBER` must be an integer.
  
Example of usage: 
  * `done` 1 
  
  
### Marking task as not done: `undo`

Marks the task as not done.

Format: `undo [TASK NUMBER]`
 * `TASK NUMBER` must be an integer.
  
Example of usage: 
  * `undo` 1 


### Finding a task: `find`

Finds a task in the tasklist.

Format: `find [TASK NAME]`
  
Example of usage: 
  * `find` book 
  
  
### Deleting a task: `delete`

Deletes a task in the tasklist.

Format: `delete [TASK NUMBER]`
   * `TASK NUMBER` must be an integer.

Example of usage: 
  * `delete` 1 
  
  
### Exiting the program: `bye`

Exits the program

Format: `bye`

Example of usage: 
  * `bye`
  
  
### Saving the data

Task data are saved in the hard disk automatically when the command `bye` is inputted.


## Command summary

**Action** | **Format**
------------ | -------------
**todo** | `todo [TASK]`
**deadline** | `deadline [TASK] /by [DD-MM-YYYY HHMM]` `deadline [TASK] /by [YYYY-MM-DD HHMM]`
**event** | `event [TASK] /at [TIME]`
**recurring** | `recurring [FREQUENCY] [DESCRIPTION] [TIME]`
**list** | `list`
**done** | `done [TASK NUMBER]`
**undo** | `undo [TASK NUMBER]`
**find** | `find [TASK NAME]`
**delete** | `delete [TASK NUMBER]`
**bye** | `bye`
