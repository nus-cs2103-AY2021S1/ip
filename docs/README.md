# User Guide

Welcome to the Duke created by Yongyan to help you mange your tasks.

## Content
* [Introduction](#introduction)
* [About](#about)
* [Getting Started](#getting-started)
* [Features List](#features-list)
* [Usage](#usage)
* [Credits](#credits)

## About
Author: Chen Yongyan

GitHub: https://github.com/Raymond0212/ip

Version: v0.2

Release Date: 16/09/2020

## Getting Started

1. Download and install Java JDK 11.
2. Double click the `Duke.jar` or run `java -jar Duke.jar`

## Features List
All the commands are case-sensitive.
* Add tasks: `todo`, `deadline`, `event`
* List all tasks: `list`
* Mark a task as done: `done`
* Delete a task: `delete`
* Archive a task: `archive`
* Find tasks by a keyword e.g. `find`
* Exit and save the changed data e.g. `bye`

![Ui](Ui.png)

## Usage

### `todo`
You can add a TODO task.

Format: `todo DESCRIPTION`

### `event`
You can add a EVENT task. The EVENT is a task with a time.

Format: `event TASK_CONTENT /at TIME /to TIME`

You are allowed to use time format below: `dd/MM/yyyy HH:mm, dd.MM.yyyy HH:MM, ddLLL,yyyy HH:mm, ddLLLyyyy HH:mm, yyyy-MM-dd HH:mm`

### `deadline`
You can add a DEADLINE task. The DEADLINE is a task with a deadline.

Format: `deadline TASK_CONTENT /by TIME`

You are allowed to use time format below: `dd/MM/yyyy HH:mm, dd.MM.yyyy HH:MM, ddLLL,yyyy HH:mm, ddLLLyyyy HH:mm, yyyy-MM-dd HH:mm`

### `list`
You can list all the tasks.

Format: `list`

### `done`
You can mark a task as DONE.

Format: `done INDEX`

Note: You should give a valid task index.

### `delete`
You can delete a task.

Format: `delete INDEX`

Note: You should give a valid task index.

### `find`
You can find a task by the keyword. It executes rough search for tasks based on description and tag.

Format: `find KEYWORD`

### `bye`
The Duke will be closed and any change of tasks will be stored.

Format: `bye`

## Credits
Packages used:
* JavaFX
* JUnit
