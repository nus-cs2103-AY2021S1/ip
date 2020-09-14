# User Guide

### Introduction

**GeNiaaz Personal Assistant** is a simple desktop application to manage tasks in your everyday life.

It functions in a GUI (Graphical User Interface) to give a fast typer the freedom to manage tasks

quickly and efficiently.


## How to read this guide 

Commands are written in italics, and the text in the < .. > field is user dependent.

Appropriate user input is preceded by 
- [x] `Sample CORRECT task command`

Incorrect user input is preceded by
- [ ] `Sample WRONG task command`

Example: `delete <task number>`  represent a delete in front of the task number ( eg `3,4` etc ). 


## Summary of Task Commands

Action by user | Command to input 
------------ | -------------
Add Todo task | `todo <task title>`
Add Deadline task | `deadline \<task title> /by <YYYY/MM/DD> <hh:mm>`
Add Event task | `event <task title> /at <YYYY/MM/DD> <hh:mm>`
Delete task | `delete <task number>`
List all task | `list`
Mark task as Done | `done <task number>`
Find term in tasks | `find <search term>`
Terminate application | `bye`



## Usage

### Add Task: `todo` `deadline` `event`

There are 3 types of tasks to add, which are described below

#### Add Todo Task

Adds a todo task into the list with the task's title

User input: **todo \<task title>**

Sample todo task: 
- [x] `todo get 6 packs`
- [x] `todo upgrade pc to Ryzen 3900x`


- [ ] `todo `

 #### Add Deadline Task
 
 Adds a Deadline task into the list with the task's title and deadline date / time 
 
 User input: **deadline \<task title> /by \<YYYY/MM/DD> \<hh:mm>**
 
 Sample deadline task: 
 - [x] `deadline finish 6 packs shortcut /by 2432/02/12 12:43`
 - [x] `deadline finish Apple 1 /by 1984/09/01 10:09`
 
 
 - [ ] `deadline finish by 2020/09/12 10:09` Make sure to include the '/' preceding by
 - [ ] `deadline finish by 2020-04-12 10:09` Write correct date formats
 
 
 #### Add Event Task

Adds a Event task into the list with the task's title and date / time

User input: **event \<task title> /at \<YYYY/MM/DD> \<hh:mm>**

Sample event task: 
- [x] `event GOT season 9 /at 2020/12/09 23:59`
- [x] `event Halo Infinite release /at 2021/10/23 10:00`


- [ ] `event /at 2020/12/02 12:32 Cyberpunk` Be sure to get the order correct

### Delete Task: `delete` 

Deletes a task of the specified number

User input: **delete \<task number>**

Sample delete task: 
- [x] `delete 2`
- [x] `delete 3`


- [ ] `delete` Make sure to add a task number
- [ ] `delete 0` Be sure to input a valid task number

### List all Task: `list` 

List all active tasks

User input: **list**

Sample listing tasks: 
- [x] `list`


- [ ] `list 1` 
- [ ] `list it right now` 

It's simple, just enter "list"

### Done Task: `done` 

Marks a task as 'done' to signify finishing a task

User input: **done \<task number>**

Sample delete task: 
- [x] `done 1`
- [x] `done 3`


- [ ] `done 0` make sure to enter a valid task number
- [ ] `done` make sure to enter a task number after done

### Find in Task: `find` 

Looks for a term / tag across all tasks and returns a list of tasks containing them

User input: **find \<search term>**

Sample delete task: 
- [x] `find milk`
- [x] `find #abs #6pack` searches for tasks with those tags


- [ ] `find ` make sure to add a term after find to search for

### Tag Tasks: `tag` 

Tag tasks with multiple terms 

User input: **tag \<task number> #\<tag name> **

Sample delete task: 
- [x] `tag 4 #arm #nvidia`
- [x] `tag 2 #abs` 


- [ ] `tag 2` make sure to add a tag to add to a task

### Bye to exit: `bye` 

Exits the application when "bye" is entered, and displays a goodbye message

User input: **bye**

Sample delete task: 
- [x] `bye`


- [ ] `bye 1` Make sure there is no text after bye to correctly execute
