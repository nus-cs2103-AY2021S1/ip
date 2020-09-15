# User Interface
![GitHub Logo](/Ui.png)
Format: ![Alt Text](url)
# User Guide
The duke bot is a desktop app for managing task, optimized for use via a Command Line Interface (CLI). If you can type fast, duke bot can track your tasks faster than traditional GUI apps. 
## Table of contents
* [Quick Start](#quick-start)
* [Features](#features)
    * [Add a todo task: `todo`](#add-a-todo-task-todo)
    * [Add an event task: `event`](#add-an-event-task-event)
    * [Add a deadline task: `deadline`](#add-a-deadline-task-deadline)
    * [Mark a task as done:`done`](#mark-a-task-as-done-done)
    * [Delete a task:`delete`](#delete-a-task-delete)
    * [Get upcoming tasks:`reminder`](#get-upcoming-tasks-reminder)
    * [Find task that matches a keyword:`find`](#find-task-that-matches-a-keyword-find)
    * [List all task:`list`](#list-all-task-list)
    * [Viewing help:`help`](#viewing-help-help)
    * [Exit the application:`bye`](#exit-the-application-bye)
* [FAQ](#faq)
* [Command Summary](#command-summary)

## Quick Start
1. Ensure you have Java 11 or above installed in your Computer.

2. Download the latest duke.jar from here.

3. Copy the file to the folder you want to use as the home folder for your duke bot.

4. Double-click the file to start the app.

5. Type the command in the command box and press Enter to execute it. e.g. typing help and pressing Enter will show the list of available commands.
## Features

The following section will showcase the list of available commands and its usage.

### Add a todo task: `todo`

Adds a todo task to your task list.

Format: `todo <NUMBER>`

Example:  
`todo CS2101 Homework`

Outcome:   
```
 Got it. I've added this task:
 [T][?] CS2101 Homework 
 Now you have 5 tasks in the list.
```

### Add an event task: `event`

Adds an event task to your task list.

Format: `event <task> /at <YYYY-MM-DD HH:MM>`

Example:  
`event CS2101 Homework /at 2020-09-16 12:00`

Outcome:   
```
 Got it. I've added this task:
 [E][?] CS2101 Homework (at: Sep 16 2020 12:00)
 Now you have 6 tasks in the list.
```

### Add a deadline task: `deadline`

Adds a deadline task to your task list.

Format: `deadline <task> /at <YYYY-MM-DD HH:MM>`

Example:  
`deadline CS2101 Homework /at 2020-09-16 12:00`

Outcome:   
```
 Got it. I've added this task:
 [D][?] CS2101 Homework (at: Sep 16 2020 12:00)
 Now you have 6 tasks in the list.
```

### Mark a task as done: `done`

Marks the task at the input position as done.

Format: `done <number>`

Example:  
`done 1`

Outcome:   
```
 Nice! I've marked this task as done:
 [T][?] CS2101 Homework
```

### Delete a task: `delete`

Delete the task at the input number position.

Format: `delete <number>`

Example:  
`delete 5`

Outcome:   
```
 Noted. I've removed this task:
 [T][?] CS2101 Homework
 Now you have 5 tasks in the list.
```

### Get upcoming tasks: `reminder`

Shows the list of task within the next input number of days.

Format: `reminder <number>`

Example:  
`reminder 2`

Outcome:   
```
 Here is a list of task that is due within 2 days:
 1. [E][?] CS2101 Homework (at: Sep 16 2020 12:00)
```

### Find task that matches a keyword: `find`

Finds and shows a list of commands which contains the input keyword.

Format: `find <keyword>`

Example:  
`find CS2101`

Outcome:   
```
 There are 2 matching tasks in your list:
 1. [T][?] CS2101 Homework
 2. [E][?] CS2101 Homework (at: Sep 16 2020 12:00)
```

### List all task: `list`

Displays the list of items in your task list.

Format: `list`

Example:  
`list`

Outcome:   
```
 Here are the tasks in your list:
 1. [T][?] do CS2103T
 2. [T][?] read Es2660
 3. [E][?] read book (at: Dec 21 2020 12:12)
 4. [T][?] CS2101 Homework
 5. [E][?] CS2101 Homework (at: Sep 16 2020 12:00)
```

### Viewing help: `help`

Displays the list of available commands in duke.

Format: `help`

Example:  
`help`

Outcome:   
```
 Here is the list of available commands:
 todo <task> : Adds a todo task to your task list.
 event <task> /at <YYYY-MM-DD HH:MM> : Adds a event with a date to your task list.
 deadline <task> /at <YYYY-MM-DD HH:MM> : Adds a deadline task with a date to your task list.
 done <number> : Marks the task at the input position as done
 delete <number> : Delete the task at the input number position.
 reminder <number> : Shows the list of task within the next input number of days.
 find <keyword> : Finds and shows a list of commands which contains the input keyword.
 list : Displays the list of items in your task list.
 help : Displays the list of available commands in duke.
 bye : Exits the duke bot.
```

### Exit the application: `bye`

Exits the duke bot.

Format: `bye`

Example:  
`bye`

Outcome:   
```
 Bye. Hope to see you again soon!
```

## FAQ
Q: How do I transfer my data to another Computer?  
A: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous duke home folder.

## Command Summary
| Action  | Format/ example |
| ------------- | ------------- |
| todo  | `todo <NUMBER>` <br> eg., `todo CS2101 Homework`  |
| event  | `event <task> /at <YYYY-MM-DD HH:MM>` <br> eg., `event CS2101 Homework /at 2020-09-16 12:00`  |
| deadline  | `deadline <task> /at <YYYY-MM-DD HH:MM>` <br> eg., `deadline CS2101 Homework /at 2020-09-16 12:00`  |
| done  | `done <NUMBER>` <br> eg., `done 3`  |
| delete | `delete <NUMBER>` <br> eg., `delete 2`  |
| reminder  | `reminder <NUMBER>` <br> eg., `reminder 2`  |
| find  | `find <keyword>` <br> eg., `find cs2101`  |
| list  | `list` |
| help  | `help`|
| bye  | `bye`  |
