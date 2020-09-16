# Duke Chatbot User Guide


1. [**Introduction**](#Introduction)
2. [**Quick Start**](#Quick-Start)
3. [**Features**](#Features)

    3.1. [Listing all tasks: `list`](#1-Listing-all-tasks-list)
    
    3.2. [Adding a task](#2-Adding-a-task)
    
    3.3. [Marking a task as done: `done`](#3-Marking-a-task-as-done-done)
    
    3.4. [Deleting a task: `delete`](#4-Deleting-a-task-delete)
    
    3.5. [Finding tasks by name: `find`](#5-Finding-tasks-by-name-find) 
    
    3.6. [Exiting the program: `bye`](#6-Exiting-the-program-bye)   
    
    3.7. [Saving/loading data](#7-Savingloading-data)   
<br><br>

## Introduction
Welcome to the Duke Chatbot User Guide! Duke Chatbot are for users who prefer to use a desktop application for their task management. Duke Chatbot is optimised for users who prefer to use the Command Line Interface (CLI) while reaping the visual benefits of a Graphical User Interface (GUI).
Duke Chatbot has several features to help you manage your tasks. Sounds interesting? Head over to [**Quick Start**](#Quick-Start) to get started!
<br><br>


## Quick Start
* Ensure that you have `java 11` installed on your computer.
* Download the latest `duke.jar` [here](https://github.com/AaronnSeah/ip/releases).
* Double-click the downloaded `duke.jar` to launch the app!
* The GUI below should appear after a few seconds.

<img src="https://github.com/AaronnSeah/ip/blob/master/src/main/resources/images/startUp.png" width="400">

* Now you can explore the features of Duke Chatbot!
* Head over to [**Features**](#Features) for detailed explanation of each command.
<br><br>


## Features 

### 1. Listing all tasks: `list`

List all of your tasks.

Format: `list`

<img src="https://github.com/AaronnSeah/ip/blob/master/src/main/resources/images/list.png" width="400">
<br><br>

### 2. Adding a task

Add a new task to your task management application.

A newly added task is not done by default.

You can add 3 kinds of tasks:
1. **Todo Task**
2. **Event Task**
3. **Deadline Task**
<br><br>

* **Adding a Todo Task**
    * format: `todo <task name>`
    
<img src="https://github.com/AaronnSeah/ip/blob/master/src/main/resources/images/todo.png">
<br><br>

* **Adding an Event Task**
    * Format: `event <task name> /at <date> <start time>-<end time>`
    * Format for `<date>`: DD/MM/YYYY
    * Format for `<start time>` and `<end time>`: HH:MM
    * Note the `-` between the `<start time>` and `<end time>`   
         
<img src="https://github.com/AaronnSeah/ip/blob/master/src/main/resources/images/event.png">
<br><br>

* **Adding a Deadline Task**
    * Format: `deadline <task name> /by <date> <time>`
    * Format for `<date>`: DD/MM/YYYY
    * Format for `<time>`: HH:MM

<img src="https://github.com/AaronnSeah/ip/blob/master/src/main/resources/images/deadline.png">
<br><br>

### 3. Marking a task as done: `done`

Mark your task as completed.

:warning: You cannot mark a task to be undone.

Format: `done <task number>`

To find the `<task number>`, use the `list` command.

The `<task number>` is the number ordering in the list for the task you would like to mark as done.

<img src="https://github.com/AaronnSeah/ip/blob/master/src/main/resources/images/done.png">
<br><br>

### 4. Deleting a task: `delete`

Deletes your task from the application.

:warning: You cannot recover a deleted task.

Format: `delete <task number>`

To find the `<task number>`, use the `list` command.

The `<task number>` is the number ordering in the list for the task you would like to mark as done.

<img src="https://github.com/AaronnSeah/ip/blob/master/src/main/resources/images/delete.png">
<br><br>

### 5. Finding tasks by name: `find`
Find your tasks by name.

Format: `find <keyword>`

The order of tasks that appear (if it is non-empty) is as follows:
1. tasks whose name matches exactly with the search keyword. e.g. "book" and "book" respectively.

2. tasks whose one of the space-delimited name matches exactly with the search keyword. e.g. "book signing" and "book" respectively.

3. tasks whose name matches contains search keyword. e.g. "book signing" and "k s" respectively.

<img src="https://github.com/AaronnSeah/ip/blob/master/src/main/resources/images/find.png">
<br><br>

### 6. Exiting the program: `bye`

Exits the application.

Format: `bye`
<br><br>

### 7. Saving/loading data

The tasks you have entered will be loaded on the application start up.

Any data change will only be saved by running the `bye` command.


