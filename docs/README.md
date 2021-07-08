# KirbyBot 🌟

## Description
Kirbybot is a bot that helps to manage tasks. You can add, edit and delete tasks.
<img src="Ui.png" height="700" width="500">


## Table of content
- [KirbyBot 🌟](#kirbybot---)
  * [Description](#description)
  * [Table of content](#table-of-content)
  * [Installation](#installation)
  * [Usage via java](#usage-via-java)
  * [Features of the application](#features-of-the-application)
    + [1. Add Tasks](#1-add-tasks)
    + [2. Delete Tasks](#2-delete-tasks)
    + [3. List all Tasks](#3-list-all-tasks)
    + [4. Complete Tasks](#4-complete-tasks)
    + [5. Update description of Tasks](#5-update-description-of-tasks)
    + [6. Storage feature](#6-storage-feature)
  * [Pull Request](#pull-request)
    + [To run the application](#to-run-the-application)
  * [Contributing](#contributing)
  * [Credits](#credits)

## Installation

Download the java application under release [v0.2](https://github.com/Jillzyt/ip/releases/tag/v0.2) to start your journey on task
 management.

## Usage via java
Run the java application. 

## Features of the application
### 1. Add Tasks
Add <b>todo</b> to the task list.  
Command: todo {DESCRIPTION}  
Example: todo revise homework  
<img src="images/todo.png" height="300" width="500">


Add an <b>event</b> to the task list.  
Command: event {DESCRIPTION} /at {DUEDATE} {TIME}  
Example: event mark homework /at 2019-02-18 18:00  
<img src="images/event.png" height="300" width="500">


Add a <b>deadline</b> to the task list.  
Command: deadline {DESCRIPTION} /by {DUEDATE} {TIME}  
Example: deadline do homework /by 2019-02-18 18:00  
<img src="images/deadline.png" height="300" width="500">

### 2. Delete Tasks
Delete the tasks identified by the index number used in the task list.  
Command: delete {INDEX}  
Example: delete 1  
<img src="images/delete.png" height="300" width="500">

### 3. List all Tasks
List all the tasks in the task list.  
Command: list  
Example: list  
<img src="images/list.png" height="300" width="500">

### 4. Complete Tasks
Mark a task done in the task list.  
Command: done {INDEX}  
Example: done 2  
<img src="images/done.png" height="300" width="500">

### 5. Update description of Tasks
Update the description of a task in the task list.  
Command: update {INDEX}  
Example: update 2 do homework  
<img src="images/update.png" height="300" width="500">

### 6. Storage feature
The summary of your task list will be saved in the directory ./data/duke.txt.

## Pull request
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

Images:
https://t.me/addstickers/Kanna_hibiki
https://t.me/addstickers/PinkPotato

<small><i><a href='http://ecotrust-canada.github.io/markdown-toc/'>Table of contents generated with markdown-toc</a></i></small>
