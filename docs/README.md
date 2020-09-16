# User Guide

Welcome to the Duke user guide. 

Duke is a desktop app that helps you keep 
track of your tasks and schedule.

## Quick Start

1. Ensure that you have Java 11 or above installed.
2. Download the latest `duke.jar` from [here]().
3. Copy the file to the folder you want to use as the home folder for your Duke.
4. Start the jar file from a terminal using `java -jar duke.jar`. The GUI should appear in a few seconds.
5. Type in a command in the command box to execute it. Type help to see all the commands available.
6. Refer to features below for details of each command.

Alternatively, you can double-click on the `jar` file at step 4 to start Duke. 

## Usage

> Note:
> 
> Words in `UPPER_CASE` are the parameters to be supplied by the user. 
> e.g. in `todo NAME`, `NAME` is a parameter which can be used as `event abc /at 2020-02-02`.

### Add a Todo Task: `todo`

Creates a new non-dated todo item.

Usage: `todo NAME`

Example of usage: `todo fold clothes`

Expected outcome: Creates a `todo` task with name `fold clothes`

### Add an Event Task: `event`

Creates a new dated event item.

Usage: `event NAME /at DATE`

Example of usage: `event meetup /at 2020-10-10`

Expected outcome: Creates an `event` task with name `meetup` at date `Oct 10 2020`

### Add a Deadline Task: `todo`

Creates a new dated deadline item.

Usage: `deadline NAME /by DATE`

Example of usage: `deadline homework /by 2020-09-09`

Expected outcome: Creates a `deadline` task with name `homework` at date `Sep 09 2020`

### View Tasks List: `list`

Shows all tasks in a numbered list, sorted by date created. Also gives the count of pending tasks.

### Mark a Task as done: `done`

Marks the task at a given index as done. 

Usage: `done INDEX`

Example of usage: `done 2`

Expected outcome: Marks task number `2` as `done`

### Delete a Task: `delete`

Deletes the task at a given index. Also updates the count of pending tasks as necessary.

Usage: `delete INDEX`

Example of usage: `delete 3`

Expected outcome: Deletes task number `3` as `done`. If task `3` was not done yet, pending count decreases.

### Find a Task: `find`

Finds all tasks that contain a given name.

Usage: `find NAME`

Example of usage: `find meet`

Expected outcome: Shows the tasks `meet with friends` and `team meeting`

### Update a Task: `update`

Changes a detail of the task at a given index. Possible detail changes include: `name`, `date` and `undo`.

#### a. Change name: `name`

Usage: `update INDEX name NEW`

Example of usage: `update 1 name wash dishes`

Expected outcome: Changes the name of task number `1` to `wash dishes`

#### b. Change date: `date`

Usage: `update INDEX date NEW`

Example of usage: `update 2 date 2020-08-08`

Expected outcome: Changes the date of task number `2` to `Aug 8 2020`

#### c. Change status: `undo`

Usage: `update INDEX undo`

Example of usage: `update 3 undo`

Expected outcome: Changes the status of task `3` to pending.

### Help for Commands: `help`

Lists down all possible commands with explanation.

### Exit Duke: `bye`

Saves your task lists and exits the app after a short message