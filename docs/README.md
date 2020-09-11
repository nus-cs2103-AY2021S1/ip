# User Guide for Nite

## WHAT is Nite?
Nite is a task tracking cat-bot, dark as the night, and easy to use.

## HOW do you use Nite?
Prerequisites: JDK 11

Download the v0.2 JAR release, and store it in a folder.
Double click on the executable jar file to wake Nite up.

## Features 
* [Add tasks](#add-tasks)
* [Mark tasks as done](#mark-tasks-as-done)
* [List tasks](#list-tasks)
* [Find tasks with keyword](#find-tasks-with-keyword)
* [Sort list of tasks](#sort-list-of-tasks)
* [Delete tasks](#delete-tasks)

### Add tasks
Add 3 different types of tasks, such as Todo, Deadline, and Event.

#### Usage

##### 1. `todo <description>` - Add a Todo task

Adds a Todo task, which is a generic non-time-sensitive task.

Example of usage: 

`todo feed my cat`

Expected outcome:

`outcome`

##### 2. `deadline <description> /by YYYY-MM-DD HHmm` - Add a Deadline task

Adds a Deadline task, which is task to be completed by a certain time.

Example of usage: 

`deadline book a vet visit /by 2020-10-03 2359`

Expected outcome:

`outcome`

##### 3. `event <description> /at YYYY-MM-DD HHmm to YYYY-MM-DD HHmm` - Add an Event task

Adds an Event task, which is task occuring in a certain time window.

Example of usage: 

`event catwalk parade /at 2020-12-25 1400 to 2020-12-25 1600`

Expected outcome:

`outcome`

## Mark tasks as done
## List tasks
## Find tasks with keyword
## Sort list of tasks
## Delete tasks
