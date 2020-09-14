# User Guide

## Overview
Duke is an interactive chat-bot that helps to organize your tasks through a to-do list.

<img src="https://raw.githubusercontent.com/eugene3231/ip/master/docs/Ui.png" width="400">

## Quick Start Guide
Prerequisites:

* Java JDK 11
* Ensure that your `JAVA_HOME` is set to the correct JDK 11 location

Starting Duke:
  1. [Download](https://github.com/eugene3231/ip/releases) Duke.jar and runduke.bat.
  2. Start the .bat file with Duke.jar in the same directory.
  3. The GUI should appear and you can start talking to Duke!
  

## Features 

### Add tasks
* Creates a task of type listed below and add it to the task list.
  - Deadline
  - Event
  - Todo

### Delete tasks
* Removes a task from the task list.

### Show all tasks
* Displays all tasks in the task list.

### Clear all tasks
* Removes all tasks in the task list.

### Find a task
* Finds a task based on a given keyword.

### Mark task as completed
* Completes a task in the task list.

### Undo 
* Undos the previous user command.


## Usage

### 1. `todo` - Add new todo
Add a new todo task with its given description. </br>

Example of usage: 

`todo Walk the dog`

Expected outcome:

> Yes! I have successfully added: </br>   
    [T][✘] Walk the dog

### 2. `deadline` - Add new deadline

Example of usage: 

`deadline [description] /by [time]`

Expected outcome:

`outcome`

### 3. `event` - Add new event

Describe action and its outcome.

Example of usage: 

`event [description] /at [time]`

Expected outcome:

`outcome`

### 4. `delete` - Delete a task

Deletes a task by specifing its number in the task list.

Example of usage: 

`delete 3`

Expected outcome:

`outcome`

### 5. `list` - List all tasks

Displays all tasks currently in the list.

Example of usage: 

`list

Expected outcome:

`outcome`

### 6. `clear` - Clear all tasks

Deletes all tasks in the list.

Example of usage: 

`clear`

Expected outcome:

`outcome`

### 7. `find` - Find a task by keyword

Find all tasks matching the keyword either fully or partially.

Example of usage: 

`find assignment`

Expected outcome:

`outcome`

### 8. `done` - Completes a task

Mark a task as done by specifing its number in the task list.

Example of usage: 

`done 1`

Expected outcome:

`outcome`

### 9. `undo` - Undo the previous command

Example of usage: 

`undo`

Expected outcome:

`outcome`

### 10. `help` - Displays help guide for commands

Example of usage: 
* Displays help for all commands

  `help`

* Display help for a specific command

  `help deadline`

`keyword (optional arguments)`

Expected outcome:

`outcome`
