# Duck User Guide

Duck is a **desktop app for managing tasks, optimized for use via a Command Line Interface** (CLI) 
while still having the benefits of a Graphical User Interface (GUI). If you can type fast, 
Duck helps you to manage your tasks faster than traditional GUI apps.

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

2. Copy the `duck.jar` file to the folder you want to use as the _home folder_ for your Duck.

3. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. 
   ![Duke](Ui.png)

4. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

### Terminating the program: `bye`

Exits the program.

Format: `bye`

### Adding a Deadline task: `deadline`

Adds a new Deadline task to the task manager. A Deadline task is a task with a description and a deadline.

If the deadline is in any of the formats: `'yyyy-MM-dd HHmm', 'yyyy-MM-d HHmm', 'dd/MM/yyyy HHmm', 'dd/M/yyyy HHmm', 'd/MM/yyyy HHmm', 
'd/M/yyyy HHmm', 'dd-MM-yyyy HHmm"', 'dd-M-yyyy HHmm', 'd-MM-yyyy HHmm', 'd-M-yyyy HHmm', 'yyyy-MM-dd', 'yyyy-MM-d', 'dd/MM/yyyy', 
'd/MM/yyyy', 'dd/M/yyyy', 'd/M/yyyy', 'dd-MM-yyyy', 'dd-M-yyyy', 'd-MM-yyyy' and 'd-M-yyyy'`, it will be represented as 
`'MMM dd yyyy, h:mm a' or 'MMM dd yyyy'`.

Format: `deadline TASK_DESCRIPTION /by DEADLINE`

Example of usage:

`deadline Tuck Ducks in Bed /by 19/7/2020 0000`

Expected outcome:
```
Quack! I have added: 
[D][X] Tuck Ducks in Bed (by: Jul 19 2020, 12:00 AM)
My duck senses tell me you have 6 tasks in the list.

```

### Deleting a task: `delete` 

Deletes the task at `TASK_NUMBER` corresponding to the the task from the `list` command.

Format: `delete TASK_NUMBER`

Example of usage:

`delete 3`

Expected outcome:

```
Quack! I have deleted this task: 
[T][X] Feed Ducks
My duck senses tell me you have 5 tasks in the list.
```

### Marking a task as done: `done`

Marks the task at `TASK_NUMBER` corresponding to the the task from the `list` command as done.

Format: `done TASK_NUMBER`

Example of usage:

`done 3`

Expected outcome:

```
Quack! I have marked this task as done:
[E][✓] Play With Ducks (at: Jul 05 2020, 12:00 PM)
```

### Adding an Event task: `event` 

Adds a new Event task to the task manager. An Event task is a task with a description and a event time/date.
If the event time/date is in any of the formats: `'yyyy-MM-dd HHmm', 'yyyy-MM-d HHmm', 'dd/MM/yyyy HHmm', 'dd/M/yyyy HHmm', 'd/MM/yyyy HHmm', 
'd/M/yyyy HHmm', 'dd-MM-yyyy HHmm"', 'dd-M-yyyy HHmm', 'd-MM-yyyy HHmm', 'd-M-yyyy HHmm', 'yyyy-MM-dd', 'yyyy-MM-d', 'dd/MM/yyyy', 
'd/MM/yyyy', 'dd/M/yyyy', 'd/M/yyyy', 'dd-MM-yyyy', 'dd-M-yyyy', 'd-MM-yyyy' and 'd-M-yyyy'`, it will be represented as 
`'MMM dd yyyy, h:mm a' or 'MMM dd yyyy'`.

Format: `event TASK_DESCRIPTION /at EVENT_TIME_DATE`

Example of usage:

`event Play With Ducks /at 5/7/2020 1200`

Expected outcome:

```
Quack! I have added: 
[E][X] Play With Ducks (at: Jul 05 2020, 12:00 PM)
My duck senses tell me you have 7 tasks in the list.
```

### Finding tasks based on keywords: `find`

Finds all tasks with task descriptions containing any of the provided keywords. The search is case-insensitive.

Format: `find KEYWORD(S)`

Example of usage:

`find duck paradise`

Expected outcome:

```
Quack! Here are the tasks in your list that match:
1. [E][✓] Feed Ducks (at: Jul 05 2020, 12:00 PM)
2. [D][X] Tuck Ducks in Bed (by: Jul 19 2020, 12:00 AM)
3. [E][✓] Play With Ducks (at: Jul 05 2020, 12:00 PM)
```

### Listing all tasks: `list`

Shows a list of all tasks in the task manager.

Format: `list`

Example of usage:

`list`

Expected outcome:

```
Quack! Here are the tasks in your list:
1. [E][✓] Feed Ducks (at: Jul 05 2020, 12:00 PM)
2. [D][X] Tuck Ducks in Bed (by: Jul 19 2020, 12:00 AM)
3. [T][X] Complete User Guide
4. [D][X] CS2100 Assignment 1 (by: Sep 18 2020, 11:59 PM)
```

### Adding a ToDo task: `todo` 

Adds a new ToDo task to the task manager. A ToDo task is a task with a description.

Format: `todo TASK_DESCRIPTION`

Example of usage:

`todo Feed Ducks`

Expected outcome:

```
Quack! I have added: 
[T][X] Feed Ducks
My duck senses tell me you have 5 tasks in the list.
```

### Saving the data

Task data are saved in the hard disk automatically in after any command that changes the data. There is no need to save manually.

--------------------------------------------------------------------------------------------------------------------
--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Description
--------|------------------
**Bye** | Exits the program
**Deadline** | Adds a new Deadline task 
**Delete** | Deletes a task
**Done** | Marks a task as done
**Event** | Adds a new Event task 
**Find** | Finds tasks based on task description
**List** | Lists all tasks
**ToDo** | Adds a new ToDo task 
