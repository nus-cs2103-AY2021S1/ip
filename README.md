# Lan Zhan, the Duke User Guide

[![Generic badge](https://img.shields.io/badge/Download-duke.jar-green>.svg)](https://github.com/Sruthisarav/ip/releases/download/A-Release/duke.jar)

## Introduction
Lan Zhan, the Duke, is a personal assistant chatbot that helps a person keep 
track of their tasks, deadlines and events. 

Created by: Sruthi 

## Quick Start
1. Ensure you have Java 11 or above installed in your machine.
2. Go to the directory containing the jar file you have just downloaded
3. Execute the command `java -jar duke.java` in your terminal

## User Interface
![UI](Ui.png)

## Features 

### Add Tasks
Allows users to add 3 different kinds of tasks: 
- Todo: Only consists of the description of task 
- Event: Consists of description of task and the date and time it's held at 
- Deadline: Consists of description of task and the date and time it's due at 

### Update Tasks
Allow users to: 
- Complete the specific task they have finished
- Delete a specific task that they no longer want to see in their list

### Commands for Task List
Allow users to:
- Undo their previous command if they have made a mistake
- View the whole list of tasks currently in their Task List
    
### Search for tasks in the task list
Allow users to find for tasks in the list based on keywords.

### Extra Command
Just a fun additional command for the theme of this Duke Program, The Untamed where w

### Exit from Duke Programme
Allow users to exit from Lan Zhan's Duke Programme once they are done.

## Usage

### `todo DESCRIPTION` - Create Todo Task

Creates a Todo Task which is then added to the Task List

Example of usage: 

`todo User Guide for Duke Program`

Expected outcome:

```  
  [T][X] User Guide for Duke Program
  New todo item added to the list!
  There are now 1 todo items in the list
```

### `event DESCRIPTION /at DD/MM/YYYY 2359` - Create Event Task

Creates an Event Task that has a date and time the event is being held at, which is then added to the Task List

Example of usage: 

`event CS2101 presentation /at 14/09/2020 1600`

Expected outcome:

```
  [E][X] CS2101 presentation (at: Sep 14 2020 4.00pm)
  New todo item added to the list!
  There are now 2 todo items in the list
```

### `deadline DESCRIPTION /by DD/MM/YYYY 2359` - Create Deadline Task

Creates a Deadline Task that has a date and time the deadline is due at, which is then added to the Task List

Example of usage: 

`deadline CS3243 Project 1 /by 16/09/2020 2359`

Expected outcome:

```
  [D][X] CS3243 Project 1 (at: Sep 16 2020 11.59pm)
  New todo item added to the list!
  There are now 3 todo items in the list
```

### `done TASKID` - Complete Task

Marks a task as complete based on the index given.

Example of usage: 

`done 2`

Expected outcome:

```
     \\(^o^)/ *.*.* \\(^o^)/
  Yay! This task has been completed:
  [E][O] CS2101 presentation (at: Sep 14 2020 4.00pm)
```

### `delete TASKID` - Deletes Task

Deletes a task in the Task List based on the index given

Example of usage: 

`delete 4`

Expected outcome:

```
  Noted. This task has now been removed from the list:
    [D][X] CS3243 Project 1 (at: Sep 16 2020 11.59pm)
  There are now 2 todo items in the list
```

### `undo` - Undo an action

This command undoes the previous command given by the user

Example of usage: 

`undo`

Expected outcome:

```
  Noted. This task has now been added back to the list:
    [D][X] CS3243 Project 1 (at: Sep 16 2020 11.59pm)
  There are now 3 todo items in the list
```

### `list` - Lists all tasks

Lists all the tasks in the Task List

Example of usage: 

`list`

Expected outcome:

```
  The tasks in your Todo List:
    1. [T][X] User Guide for Duke Program
    2. [E][O] CS2101 presentation (at: Sep 14 2020 4.00pm)
    3. [D][X] CS3243 Project 1 (at: Sep 16 2020 11.59pm)
```

### `find KEYWORDS` - Find Tasks

Find tasks based on the keywords given. This feature is case-sensitive.

Example of usage: 

`find presentation`

Expected outcome:

```
  Here are the matching tasks in your list:
    1. [E][O] CS2101 presentation (at: Sep 14 2020 4.00pm)
```

### `LanZhan` - Extra Command

A command created just for fun to follow The Untamed theme of this Duke Program.

Example of usage: 

`LanZhan`

Expected outcome:

```
Wei Ying,

Why am I sitting here having such a useless conversation with you?

Go away!
```

### `bye` - Exit Duke

Exits from Lan Zhan, the Duke Program.

Example of usage: 

`bye`

Expected outcome:

```
            *(^v^)
  Bye. Hope to see you again soon!
```
