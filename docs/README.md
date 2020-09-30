# User Guide
Duke is a chat bot to help you manage your deadlines and task. It serves as an all in one to-do app! It is optimized for use via a Command Line Interface.

## Quick Start
	1. Download the latest Duke.jar
	2. Double click the file to start the app
    3. Use the text field to type in command
## Features 
Some commands available:
-   `todo`, `deadline`, `event` adds the task type to the todo list
-   `list` lists out all current tasks
-   `delete 3` deletes the 3rd task in the list
-   `help` opens a help window
-   `done 3` marks the 3rd task in the list as done
-   `find book` finds all task with `book` 

More commands are available in the help window

## Key Features Examples
### Feature 1 
Adding of tasks

#### Usage

### `todo`, `deadline`, `event` - Adds the specific task type

Adds a todo, deadline or event task. 

Note that for a deadline, a `/by` followed by the date in the format `YYYY-MM-DD` is required

Note that for an event, a `/at` followed by the date and time in the format `YYYY-MM-DD TT:TT-TT:TT` is required

Example of usage: 

`todo cs2103 quiz`

`deadline cs2103 quiz /by 2020-08-23`

`event cs2103 lecture /at 2020-08-23 16:00-18:00`

Expected outcome:

>Got it. I've added the task:
>
>[T][x] cs2103 quiz

>Got it. I've added the task: 
>
>[T][x] cs2103 quiz (by: Aug 23 2020)

>Got it. I've added the task: 
>
>[T][x] cs2103 lecture (at: Aug 23 2020 16:00-18:00)

### Feature 2
Listing of tasks

#### Usage

### `list` - Lists all current task

List all the current task in the list

Example of usage: 

`list`

Expected outcome:

>Here are the task in your list:
>
>1. [T][x] cs2103 quiz

### Feature 3
Deleting of tasks

#### Usage

### `delete` - deletes a task

Deletes the task specified by the number after the `delete` command

Example of usage: 

`delete 1`

Expected outcome:

>Noted. I've removed this task:
>
>1. [T][x] cs2103 quiz

## Command summary

Action | Format, Examples
--------|------------------
**ToDo** | `todo [TASK_NAME]` e.g.`todo cs2103 quiz`
**Deadline** | `deadline [TASK_NAME] [/by DATE]` e.g.`deadline cs2103 quiz /by 2020-08-23`
**ToDo** | `event [TASK_NAME] [/at DATE] [TIME]` e.g.`event cs2103 lecture /at 2020-08-23 16:00-18:00`
**Delete** | `delete INDEX`<br> e.g., `delete 3`
**Done** | `done INDEX`<br> e.g., `done 3`
**Find** | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find cs2103`
**List** | `list`
**Help** | `help`
