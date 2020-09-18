# User Guide

Duke is a desktop-based Graphical User Inface (GUI) application designed at helping you keep track of your day to day tasks. It has a wide variety of features that can help you manage and organize your tasks. 
As Duke is optimized for people who can type at high speeds, it has the potential to be quicker than other traditional applications depending on your typing speed.
  
Download the latest Duke Jar from [here](https://github.com/hoperawr/ip/releases/tag/A-Release).  

![screenshot](/docs/Ui.png)

- [Quick start](#quick-start)
- [Features](#features)
    1. [View all commands: `help`](#view-all-commands-help)
    1. [Add a todo: `todo`](#add-a-todo-todo)
    1. [Add a deadline: `deadline`](#add-a-deadline-deadline)
    1. [Add an event: `event`](#add-an-event-event)
    1. [List all tasks: `list`](#list-all-tasks-list)
    1. [Mark a task as done: `done`](#mark-a-task-as-done-done)
    1. [Delete a task: `delete`](#delete-a-task-delete)
    1. [Find a task: `find`](#find-a-task-find)
    1. [Reschedule a task: `reschedule`](#reschedule-a-task-reschedule)
    1. [Snooze a task: `snooze`](#snooze-a-task-snooze)
    1. [Exit the program: `bye`](#exit-the-program-bye)
- [Command table](#command-table)

## Features 

### View all commands `help`
Shows all the commands available.
Format: `help`

Example of usage:  
`help`  

Expected outcome:  
`Welcome to duke!`  
`These are the list of commands and [ ] indicates user input while { } indicates variables`  
`- [todo {name}] to add a new todo`  
`- [deadline {name} /by {dd/mm/yyyy HHmm}] to add a new deadline`  
`- [event {name} /at {dd/mm/yyy HHmm-HHmm}] to add a new event`  
`- [delete {index}] to delete a task at the index specified`  
`- [delete all] to delete all tasks`  
`- [done {index}] to mark a task as done`  
`- [list] to view all tasks in the list`  
`- [find {word}] to find task that contains a certain word`  
`- [snooze {index}] to push back a deadline or event by an hour`  
`- [reschedule {index} {hours}] to push back a deadline or event by a number of hours`  
`- [help] to print list of commands`  
`- [bye] to exit the application`  

### Add a todo: `todo`
Adds a todo to the list of tasks.  
Format: `todo [DESCRIPTION]`

Example of usage:  
`todo laundry` 

Expected outcome:  
`added: [T][✘] laundry`    
`Now you have 1 tasks in the list.`

### Add a deadline: `deadline`
Adds a deadline to the list of tasks.  
Format: `deadline [NAME] /by [DATE] [TIME]`

Example of usage:  
`deadline assignment /by 25/12/2020 2359` 

Expected outcome:  
`added this task: [D][✘] assignment (by 25 Dec 2020 23:59)`    
`Now you have 2 tasks in the list.`

### Add an event: `event`
Adds an event to the list of tasks.  
Format: `event [NAME] /at [DATE] [TIME]-[TIME]`

Example of usage:  
`event tutorial /at 18/09/2020 1100-1200` 

Expected outcome:  
`added this task: [E][✘] tutorial (at 18 Sep 2020 11:00-12:00)`    
`Now you have 3 tasks in the list.`

### List all tasks: `list`
Shows all tasks.
Format: `list`

Example of usage:  
`list`

Expected outcome:  
`1. [T][✘] laundry`  
`2. [D][✘] assignment (by 25 Dec 2020 23:59)`  
`3. [E][✘] tutorial (at 18 Sep 2020 11:00-12:00)`  

### Mark a task as done: `done`
Marks a task as done.
Format: `done [INDEX]`

Example of usage:  
`done 1` 

Expected outcome:  
`Nice! I've marked this task as done:`  
`[T][✓] laundry`  
`Now you have 3 tasks in the list.`

### Delete a task: `delete`
Delete a task or all tasks.
Format: `delete [INDEX]`
Format: `delete all`

Example of usage:  
`delete 2` 

Expected outcome:  
`Noted. I've removed this task:`  
`[D][✘] assignment (by 25 Dec 2020 23:59)`  
`Now you have 2 tasks in the list.`

### Find a task: `find`
Finds all the task that has a certain word.
Format: `find [WORD]`

Example of usage:  
`find tutorial` 

Expected outcome:  
`Here are the matching tasks in your list:`  
`2. [E][✘] tutorial (at 18 Sep 2020 11:00-12:00)`  

### Reschedule a task: `reschedule`
Reschedule deadlines and events.
Format: `reschedule [INDEX] [DATE] [TIME]`
Format: `reschedule [INDEX] [DATE] [TIME]-[TIME]`

Example of usage:  
`reschedule 2 21/9/2020 1200-1300` 

Expected outcome:  
`The following task has been rescheduled to:`  
`[E][✘] tutorial (at 21 Sep 2020 12:00-13:00)`  

### Snooze a task: `snooze`
Push a deadline or event back by a certain number of hours.
Format: `snooze [INDEX] [HOURS]`

Example of usage:  
`snooze 2 1` 

Expected outcome:  
`The following task has been rescheduled to:`  
`[E][✘] tutorial (at 21 Sep 2020 13:00-14:00)`  

### Exit the program: `bye`
Closes the application
Format: `bye`

Example of usage:  
`bye` 

Expected outcome:  
`Bye. Hope to see you again soon!`

## Command Table
Command | Format | Example
------ | ------ | --------
`help` | `help` | `help`
`todo` | `todo [NAME]` | `todo read book`
`deadline` | `deadline [NAME] /at [DATE] [TIME]` | `deadline study /by 9/9/2020 2359`
`event` | `event [NAME] /at [DATE] [TIME]-[TIME]` | `event meeting /at 9/10/2020 2:00 PM, 10 Oct 2020 1530`
`list` | `list` |  `list`
`done` | `done [INDEX]` | `done 2`
`delete` | `delete [INDEX]` | `delete 1`
`delete` | `delete all` | `delete all`
`find` | `find [WORD]` | `find read`
`reschedule` | `reschedule [INDEX] [DATE] [TIME]` | `reschedule 3 21/9/2020 1300`
`reschedule` | `reschedule [INDEX] [DATE] [TIME]-[TIME]` | `reschedule 2 21/9/2020 1200-1300`
`snooze` | `snooze [INDEX] [HOURS]` | `snooze 2 1`
`bye` | `bye` | `bye`