# Alexa User Guide

Alexa is a chatbot to help people manage their tasks easily. 
You can take note and update it via GUI that responds to your
command line instruction.

## Table of Contents

- [Requirements](#requirements)
- [Features](#features)
    - [Interactive user interface](#interactive-user-interface)
    - [View your tasks by date](#view-your-tasks-by-date)
    - [Find your tasks](#find-your-tasks)
    - [Update your tasks](#update-your-tasks)
- [Usage](#usage)
    - [Add a new task](#add-a-new-task)
    - [Marks task as done](#marks-task-as-done)
    - [Deletes a task in the list](#deletes-a-task-in-the-list)
    - [Find tasks](#find-tasks)
    - [List tasks](#list-tasks)
    - [View all tasks by date](#view-all-tasks-by-date)
    - [Update a task](#update-a-task)

## Requirements

- Java 11 or later

## Features 

### Interactive user interface

Alexa chatbot uses a GUI that gives you interactive feedback for every command
that you write. It responds as if you are chatting with a real person that
always available to help you manage your tasks.

![Product Screenshot](Ui.png)

### View your tasks by date

By default, Alexa stores your task by creation date. However, you can also filter to see which tasks
do you have for the day

### Differentiates your tasks

There are several types of tasks that are supported by Alexa chatbot to help
you classify different types of task that you have.

### Find your tasks

You can ask Alexa to find task that contain a certain keyword in your command.

### Update your tasks

If you have a change of plan, there is no need to delete and create a new task, 
simply update the current task with a new deadline 

## Usage

### Add a new task

Add a new task into the list of tasks that are stored by Alexa chatbot for you.

**Usage example:** 
- `todo [description]`
- `deadline [description] /by [yyyy-mm-dd]`
- `event [description] /at [yyyy-mm-dd]`

**Example of usage:**
- `todo watch movie` <br>
Adds a new todo task with description `watch movie`.

- `deadline homework /by 2020-09-19` <br>
Adds a new deadline task with description `homework`.
Time description will be automatically formatted to 
`Sep 19, 2020`.

- `event music concert /at 2020-09-19` <br>
Adds a new event task with description `music concert`.
Time description will be automatically formatted to 
`Sep 19, 2020`.

***

### Marks task as done

Marks a particular task as done in the list of tasks.

**Usage example:**
`done [taskNumber]`

**Example of usage:**
- `done 2` <br>
Mark task with id `2` as done.

***

### Deletes a task in the list

Removes a certain task from the list of tasks.

**Usage example:**
`delete [taskNumber]`

**Example of usage:**
- `delete 2`
Removes task with id `2`.

***

### Find tasks

Finds all the tasks that contain a certain keyword in its description.

**Usage example:**
`find [keyword]`

**Example of usage:**
- `find homework`
Find all tasks that contain keyword `homework` in its description.

***

### List tasks

Lists all the tasks that are currently stored by the Alexa chat bot.

**Usage example:**
- `list`


**Example of usage:**
- `list`
By default, this will lists all tasks based on their creation time.

***

### View all tasks by date

View all tasks to be done on the given date

**Usage example:**
- `viewall yyyy-mm-dd`

**Example of usage:**
- `viewall 2020-09-19` <br>
This command will list all tasks on Sep 19, 2020

***

### Update a task

Update task description or date

**Usage example:**
- `update <taskNumber> [/d <description>] [/t <date>]`
- `update <tasknumber> [/t <date>] [/d <description>]`
- `update <taskNumber> [/t <date>]`
- `update <taskNumber> [/d <description>]`

**Example of usage:**
- `update 2 /t 2020-09-20` <br>
This command will update task 2 date to fall on September 20, 2020
- `update 2 /d borrow book` <br>
This command will update task 2 description to borrow book
- `update 2 /t 2020-09-20 /d borrow book` <br>
This command will update task 2 date to fall on September 20, 2020 and description to borrow book

### Exit Alexa

Exit Alexa chatbot

**Usage example:**
- `bye` <br>
This will show Alexa's goodbye message and close the application
