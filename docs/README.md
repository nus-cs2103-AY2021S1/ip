# MilkMocha Task Tracker

MilkMocha is a desktop application for managing tasks, optimized for use via a Command Line Interface (CLI) while 
still having the benefits of a Graphical User Interface (GUI). If you can type fast, MilkMocha can get your task
management done faster than traditional GUI apps.

## Quick start

1. Ensure you have `Java 11` or above installed in your computer.
2. Download the latest `milkmocha.jar`.
3. Copy the file to the folder you want to use as the home folder for your MilkMocha.
4. Double-click the file to start the app.
5. Type the command in the command box and press Enter to execute. 
6. Refer to `Features` below for details of each command.

## Features

#### Viewing help: `help`

Shows a message explaining all the commands with examples.  
Format: `help`  

#### Listing all task: `list`

Shows all of your current task in the list.  
Format: `list`  

#### Adding a todo task: `todo`

Adds a 'todo' task into the list.  
  
Format: `todo <task name>`  
Examples:
  - `todo learn cooking`
  - `todo buy milk`

#### Adding a deadline task: `deadline`

Adds a 'deadline' task into the list.  
  
Format: `deadline <task name> /by <date> <time>`  
Examples:
  - `deadline CS2103T project /by 2020-08-26 23:59`
  - `deadline Google Internship Application /by 2020-09-31 22:00`

#### Adding an event task: `event`

Adds a 'event' task into the list.  
  
Format: `event <task name> /at <date>`  
Examples:
  - `event Company's Retreat /at 2020-01-29`
  - `event Google Day /at 2020-09-09`

#### Marking a task as done: `done`

Marks a task in the list as done.  
  
Format: `done <number>`  
Examples:
  - `done 2`
  - `done 1`

#### Deleting a task: `delete`

Deletes a task from the list.  
  
Format: `delete <number>`  
Examples:
  - `delete 2`
  - `delete 1`

#### Checking incomplete task on a date: `check`

Checks what task(s) has/have not been completed on a specified date.  
  
Format: `check <date>`  
Examples:
  - `check 2020-08-08`
  - `check 2020-10-28`

#### Finding task using keyword: `find`

Finds any task(s) matching a specified keyword.  
  
Format: `find <keyword>`  
Examples:
  - `find homework`
  - `find project` 
  
## FAQ

Q: How do I transfer my data to another Computer?  
 > Install the app in the other computer and copy over the text file `todolist.txt` and paste it in
 > the home folder of MilkMocha.

