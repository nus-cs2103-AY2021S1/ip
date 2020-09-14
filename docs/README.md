# KirbyBot 🌟

## Description
Kirbybot is a bot that helps to manage tasks. You can add, edit and delete tasks.
![Ui](/Ui.png)

## Table of content
- [KirbyBot 🌟](#kirbybot---)
  * [Description](#description)
  * [Table of content](#table-of-content)
  * [Installation](#installation)
  * [Usage via java](#usage-via-java)
  * [Features of the application](#features-of-the-application)
    + [1. Add Tasks (Deadline, Todo, Events type)](#1-add-tasks--deadline--todo--events-type-)
    + [2. Delete Tasks](#2-delete-tasks)
    + [3. List all Tasks](#3-list-all-tasks)
    + [4. Complete Tasks](#4-complete-tasks)
    + [5. Update description of Tasks](#5-update-description-of-tasks)
    + [6. Storage feature](#6-storage-feature)
  * [Editing the code (Pull requests)](#editing-the-code--pull-requests-)
    + [To run the application](#to-run-the-application)
  * [Contributing](#contributing)
  * [Credits](#credits)

## Installation

Download the java application under release [v2.0](hhttps://github.com/Jillzyt/ip/releases/tag/v0.2) to start your journey on task
 management.

## Usage via java
Run the java application. 

## Features of the application
### 1. Add Tasks (Deadline, Todo, Events type)
Add todo to the task list.  
Command: todo {DESCRIPTION}  
Example: todo revise homework 
<img src="/docs/images/todo.png" height="300" width="500">

Add an event to the task list.  
Command: event {DESCRIPTION} /at {DUEDATE} {TIME}  
Example: event mark homework /at 2019-02-18 18:00  
<img src="/docs/images/event.png" height="300" width="200">

Add a deadline to the task list.  
Command: deadline {DESCRIPTION} /by {DUEDATE} {TIME}  
Example: deadline do homework /by 2019-02-18 18:00  
<img src="/docs/images/deadline.png" height="300" width="200">

### 2. Delete Tasks
Delete the tasks identified by the index number used in the task list.  
Command: delete {INDEX}  
Example: delete 1  
<img src="/docs/images/delete.png" height="300" width="200">

### 3. List all Tasks
List all the tasks in the task list.  
Command: list  
Example: list  
<img src="/docs/images/list.png" height="300" width="200">

### 4. Complete Tasks
Mark a task done in the task list.  
Command: done {INDEX}  
Example: done 2  
<img src="/docs/images/done.png" height="300" width="200">

### 5. Update description of Tasks
Update the description of a task in the task list.  
Command: update {INDEX}  
Example: update 2 do homework  
<img src="/docs/images/update.png" height="300" width="200">

### 6. Storage feature
The file of your tasks will be saved in the directory ./data/duke.txt.

## Editing the code (Pull requests)
### To run the application
Run the file Launcher.java or run the file gradlew.bat / gradle.sh base on your OS.  
Ensure Java 11 SDK is installed. 

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.

## Credits
Base code from 
- https://github.com/nus-cs2103-AY2021S1/ip

Reference snippets of code from 
- https://github.com/se-edu/addressbook-level2

<small><i><a href='http://ecotrust-canada.github.io/markdown-toc/'>Table of contents generated with markdown-toc</a></i></small>
