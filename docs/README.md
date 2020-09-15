# Juke User Guide

Welcome to the **Juke Chatbot** User Guide!\
Juke is an extremely convenient personal task tracker.\
It is suited for fast typists, with commands input via a Command line Interface (CLI), 
with the added visual benefits of a Graphical User Interface (GUI).

## Quick Start
1. Ensure you have Java 11 installed on your computer.
1. Download the latest version from [here](https://github.com/VaishakAnand/ip/releases)
1. Copy the downloaded file to the folder you want to use as the *home folder* for Duke.
1. Double click the file to start the app.
1. Type commands in the text field at the bottom and click the *Send* button to execute.
The list of commands can be found in the feature list below.

## Features 

### `todo` - Adding a Todo task.

Adds a Todo task to list of current tasks.

Format: `todo TASK_DESCRIPTION`  

Example of usage: `todo Boil eggs`

Expected outcome: *Boil eggs* Todo added.  

### `event` - Adding an Event task.

Adds an Event task to list of current tasks, with an Event date (YYYY-MM-DD).

Format: `event TASK_DESCRIPTION /at EVENT_DATE`  

Example of usage: `event NUS Computing Day /at 2020-09-06`

Expected outcome: *NUS Computing Day on 6 September 2020* Event added.  

### `deadline` - Adding a Deadline task.

Adds a Deadline task to list of current tasks, with a Deadline date (YYYY-MM-DD).

Format: `deadline TASK_DESCRIPTION /by DEADLINE_DATE`  

Example of usage: `deadline CS2103T Homework /by 2020-09-15`

Expected outcome: *CS2103T Homework by 15 September 2020* Deadline added.  


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
bye | `bye` | `bye`

