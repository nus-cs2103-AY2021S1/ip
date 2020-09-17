# Juke User Guide

Welcome to the **Juke Chatbot** User Guide!\
Juke is an extremely convenient personal task tracker.\
It is suited for fast typists, with commands input via a Command line Interface (CLI), 
with the added visual benefits of a Graphical User Interface (GUI).

* [Quick start](#quick-start)
* [Features](#features)  
  * [Adding a Todo: `todo`](#todo---adding-a-todo-task)
  * [Adding a Event: `event`](#event---adding-an-event-task)
  * [Adding a Deadline: `deadline`](#deadline---adding-a-deadline-task)
  * [Viewing all tasks: `list`](#list---listing-all-tasks)
  * [Finding a task: `find`](#find---finding-a-task)
  * [Completing a task: `done`](#done---marking-a-task-as-done)
  * [Deleting a task: `delete`](#delete---deleting-a-task)
  * [Updating a task: `update`](#update---updates-a-task)
  * [Exiting the program: `bye`](#bye---stopping-chatbot)
* [Command Summary](#command-summary)

## Quick Start
1. Ensure you have Java 11 installed on your computer.
1. Download the latest version from [here](https://github.com/VaishakAnand/ip/releases)
1. Copy the downloaded file to the folder you want to use as the *home folder* for Duke.
1. Double click the file to start the app.
1. Type commands in the text field at the bottom and click the *Send* button to execute.
The list of commands can be found in the feature list below.

## Features 

Parameters in square brackets, e.g. `[/date DATE]` are optional parameters.  
Parameters without square brackets are required.

### `todo` - Adding a Todo task.

Adds a Todo task to list of current tasks.

Format: `todo TASK_DESCRIPTION`  

Example of usage: `todo Boil eggs`

Expected outcome: *Boil eggs* Todo added.  

### `event` - Adding an Event task.

Adds an Event task to list of current tasks, with an Event date (YYYY-MM-DD).

Format: `event TASK_DESCRIPTION /at EVENT_DATE`  

Example of usage: `event NUS Computing Day /at 2020-09-06`

Expected outcome: *NUS Computing Day on Sep 6 2020* Event added.  

### `deadline` - Adding a Deadline task.

Adds a Deadline task to list of current tasks, with a Deadline date (YYYY-MM-DD).

Format: `deadline TASK_DESCRIPTION /by DEADLINE_DATE`  

Example of usage: `deadline CS2103T Homework /by 2020-09-15`

Expected outcome: *CS2103T Homework by Sep 15 2020* Deadline added.  


### `list` - Listing all tasks.

Shows a list of all tasks.

Format: `list`  

### `find` - Finding a task.

Finds all the tasks that contains the given keyword in Task Description.

Format: `find KEYWORD`  

Example of usage: `find eggs`

Expected outcome: All tasks containing keyword *eggs*.  

### `done` - Marking a task as done.

Marks a task from list of tasks with given list index of task, as done.  
Index has to be a *positive* integer.

Format: `done INDEX`  

Example of usage: `done 4`

Expected outcome: Task at list index *4* marked as done.  

### `delete` - Deleting a task.

Deletes a task from list of tasks with given list index of task.  
Index has to be a *positive* integer.

Format: `delete INDEX`  

Example of usage: `delete 4`

Expected outcome: Task at list index *4* deleted.    

### `update` - Updates a task.

Updates a task from list of tasks with given list index of task.  
Index has to be a *positive* integer.  
Can update task description, or date, or both.

Format: `update /number INDEX [/description DESCRIPTION] [/date DATE]`

Example of usage: `update /number 2 /description eat eggs /date 2020-04-19`

Expected outcome: Task at list index *4* has description updated to *eat eggs*
and date updated to *Apr 19 2020*

### `bye` - Stopping chatbot.

Saves all tasks to disk and stops chatbot.

Format: `bye`  
 
## Command Summary
 
Action | Format | Example
---|---|---
Todo | `todo TASK_DESCRIPTION` | `todo Boil eggs`
Event | `event TASK_DESCRIPTION /at EVENT_DATE` | `event NUS Computing Day /at 2020-09-06`
Deadline | `deadline TASK_DESCRIPTION /by DEADLINE_DATE` | `deadline CS2103T Homework /by 2020-09-15`
list | `list` | `list`
find | `find KEYWORD` | `find pasta`
done | `done INDEX` | `done 3`
delete | `delete INDEX` | `delete 1`
update | `update /number INDEX [/description DESCRIPTION] [/date DATE]` | `update /number 2 /description eat eggs /date 2020-04-19`
bye | `bye` | `bye`

