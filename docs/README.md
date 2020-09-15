# User Guide

Welcome to the Duke created by Xia Liyi to help you mange your tasks.

## Content
* [Introduction](#Introduction)
* [About](#About)
* [Getting Started](#Getting Started)
* [Features List](#Features List)
* [Usage](#Usage)
* [Credits](#Credits)

## Introduction
This is mainly a task management application supported by JavaFX. It is an individual project in CS2103/T. It is also the gift I prepare for the girl I love.

## About
Author: Xia Liyi

GitHub: https://github.com/XIA-LIYI/ip

Version: v0.2

Release Date: 15 September 2020

## Getting Started
1. Download and install Java JDK 11.
2. Mac / Linux / Windows are supported.
3. Double click the Duke or run `java -jar Duke.jar`
4. Log in with default user e.g. key in `login admin password`

## Features List
All the commands are case-sensitive.
* Log in to start the app e.g. `login`
* Add a user e.g. `adduser`
* Add a task e.g. `todo`, `deadline`, `event`
* List all the tasks e.g. `list`
* Mark a task as done e.g. `done`
* Delete a task e.g. `delete`
* Set a tag for a task e.g. `tag`
* Find tasks by a keyword e.g. `find`
* Get a random reply e.g. `love`
* Exit and save the changed data e.g. `bye`

## Usage
### `login`
You need to log in to use the Duke.

Format: `login USRNAME PASSOWRD`

Note: The username and password of default user is "admin" and "password".

### `adduser`
You can add a new user.

Format: `adduser USERNAME PASSWORD NICKNAME`

### `todo`
You can add a TODO task.

Format: `todo DESCRIPTION`

### `event`
You can add a EVENT task. The EVENT is a task with a time.

Format: `event DESCRIPTION /at TIME`

Note: The format of the `TIME` is either `yyyy/mm/dd HH:mm` or `yyyy/mm/dd`. 

### `deadline`
You can add a DEADLINE task. The DEADLINE is a task with a deadline.

Format: `deadline DESCRIPTION /by TIME`

Note: The format of the `TIME` is either `yyyy/mm/dd HH:mm` or `yyyy/mm/dd`. 

### `list`
You can list all the tasks.

Format: `list`

### `done`
You can mark a task as DONE.

Format: `done TASKINDEX`

Note: You should give a valid task index. You are recommended that you run `list` first.

### `delete`
You can delete a task.

Format: `delete TASKINDEX`

Note: You should give a valid task index. You are recommended that you run `list` first.

### `tag`
You can set a tag for a task. The tag is optional for tasks.

Format: `tag TASKINDEX TAGCONTENT`

### `find`
You can find a task by the keyword. It executes rough search for tasks based on description and tag.

Format: `find KEYWORD`

### `love`
You can get a random message to make you happy. You can try once a day.

Format: `love`

### `bye`
The Duke will be closed and any change of tasks will be stored.

Format: `bye`

## Credits
Packages used:
* JavaFX
* JUnit
* java-json
