# Intrubot User Guide

## What is Intrubot
Intrubot is a **desktop based chat application** for keeping track of your tasks.
You can interact via a Command Line Interface view responses via a Graphical
User Interface (GUI).

--------------------------------------------------------------------------------------------------------------------
## Table of Contents
- [Getting Started](#getting-started)
- [About Intrubot's Features](#features)
    - [Add tasks](#add-tasks)
    - [List items](#list-items)
    - [Find items](#find-items)
    - [Mark tasks](#mark-tasks)
    - [Clear items](#clear-items)
    - [No Duplicates](#no-duplicates)
    - [Add Trivia](#add-trivia)
    - [Exit](#exit)
- [Usage](#usage)
    - [todo](#todo)
    - [deadline](#deadline)
    - [event](#event)
    - [trivia](#trivia)
    - [list](#list)
    - [clear](#clear)
    - [find](#find)
    - [done](#done)
    - [delete](#delete)
    - [bye](#bye)

## Getting Started 
* Ensure you have Java 11 or above installed in your Computer.
* Download the latest `.jar` from [here](https://github.com/orzymandias/ip/releases/tag/A-Release).
* Windows OS:Double-click the file to start the app.
* MacOS/*nix: Navigate to directory containing jar file and run `java -jar duke.jar`

![Intrubot_Screenshot](Ui.png)

## Features 

#### Add Tasks 
* Save 3 kinds of tasks to your task list: todo, deadline, event. 

#### Add trivia
* Learnt a fun fact? Save the question and answer to Intrubot.

#### List Items 
* Lists all the tasks and trivia you have saved.

#### Find Items 
* Search for tasks and trivia in your list.

#### Mark Tasks 
* Mark tasks as done when you have completed them.

#### Clear Items
* Want to start on a fresh slate? Intrubot can clear all your items in your list.

#### No Duplicates
* Ensure you never save redundant tasks with a friendly reminder 
when you try to add a task that already exists.

#### Exit
* Done using Intrubot? Simply exit! Your data will be waiting for you when you come back.

## Usage

### `todo` 

Adds a todo to the task list. Todos only have a description and status.

Format:
`todo <description>`

Example of usage: 

`todo buy watermelons`

Expected outcome:
```
Got it. I've added this task:
    [T][✘] work
Now you have 5 tasks in the list
```

### `deadline` 

Adds a deadline to the task list. Deadlines store a description, completion status and date of the deadline.

Format:
`deadline <description> /by <YYYY-MM-DD>`

Example of usage: 

`deadline submit homework /by 2020-11-27`

Expected outcome:
```
Got it. I've added this task:
    [D][✘] submit homework (by:Nov 27 2020) 
Now you have 6 tasks in the list
```

### `event` 

Adds a event to the list. Events store a description, completion status and date of the event.

Format:
`event <description> /at <YYYY-MM-DD>`

Example of usage: 

`event flight to korea /at 2020-06-17`

Expected outcome:
```
Got it. I've added this task:
    [E][✘] flight to korea (at:Jun 17 2020) 
Now you have 3 tasks in the list
```

### `trivia` 

Adds a trivia to the list. Trivia contains a question and an answer.

Format:
`trivia <question> /ans <ans>`

Example of usage: 

`trivia how many legs does a ladybug have /ans 6 legs`

Expected outcome:
```
Got it. I've added this trivia:
    [Q] how many legs does a ladybug have
    [A] 6 legs
Now you have 4 items in the list
```
### `list` 

List all the item stored to intrubot.

Format:
`list`

Expected outcome:
```
1. [D][✘] do homework (by: Dec 12 2020)
2. [D][✘] submit homework (by: Nov 27 2020)
3. [E][✘] flight to korea (at: Jun 17 2020)
```

### `clear` 

clear all the item stored to intrubot.

Format:
`clear`

Expected outcome:
```
Task list cleared
```

### `find` 

Find items in the list with matching description.

Format:
`find <String>`

Example of usage: 

`find to`

Expected outcome:
```
HAI. Here are matching tasks:
1. [E][✘] flight to korea (at: Jun 17 2020)
2. [T][✓] go to supermarket
```


### `done` 

Marks a tasks as complete by specifying its index.

Format:
`done <index>`

Example of usage: 

`done 1`

Expected outcome:
```
Nice! I've marked this task as done:
[D][✓] do homework (by: Dec 12 2020)
```

### `delete` 

Delete an item by specifying its index.

Format:
`delete <index>`

Example of usage: 

`delete 1`

Expected outcome:
```
HAI. I've deleted this task:
    [D][✓] do homework (by: Dec 12 2020)
Now you have 3 tasks in the list)

```
### `bye` 

Quit using intrubot.

Format:
`bye`

Expected outcome:
```
SAYONARA!
```
Good bye message is shown and program will close.
