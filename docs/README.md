# User Guide

![](Ui.png)

Straw bot is a desktop app for managing tasks, optimized for use via a Command Line Interface.
Graphical User Interface (GUI) is added for better user accessibility.

The 3 types of tasks supported are Todo, Event, and Deadline task. 
1. Todo task has no date and time included.
2. Event task has a date and time which roughly indicate when the task should be done.
3. Deadline task has a date and time which state when the task should be done by.

## Quick Start

1. Ensure you have Java 11 or above installed in your Computer.

2. Download the latest .jar file from this repository release.

3. Copy the file to the folder you want to use as the home folder for your Straw bot.

4. Launch the jar file to start the app.

5. Type the command in the comand box and press Enter to execute it. For more commands, refer to the next section.

## Features 
Words in UPPER_CASE are the parameters to be supplied by the user.
Items in curly brackets are date formats.


### List: `list`

Shows a list of all the user's tasks.


### Exit: `bye`

Terminates the programme.


### Add a Todo task: `todo TASK_NAME`

Add a task with the name TASK_NAME.

For example,

`todo Task 1`


### Mark a task as Done: `done INDEX`

Mark a task at index INDEX as Done (INDEX starts at 1).

For example,

`done 1`


### Add an Event task: `event EVENT_NAME /at {yyyyMMddHHmm}`
or `event EVENT_NAME /at {yyyyMMdd HHmm}`
or `event EVENT_NAME /at {HH:mm ddMMyy}`
or `event EVENT_NAME /at {yyyy-MM-dd HH:mm}`

Add an event with the name EVENT_NAME. It accepts the 4 different formats of date.

For example,

`event run up /at 2020-12-01 14:30`


### Add a Deadline task: `deadline DEADLINE_NAME /by {yyyyMMddHHmm}`
or `deadline DEADLINE_NAME /by {yyyyMMdd HHmm}`
or `deadline DEADLINE_NAME /by {HH:mm ddMMyy}`
or `deadline DEADLINE_NAME /by {yyyy-MM-dd HH:mm}`

Add a deadline with the name DEADLINE_NAME. It accepts the 4 different formats of date.

For example,

`deadline run around /by 20201201 1430`


### Find: `find NAME`

Shows all tasks whose name has the substring NAME. Note: It is case sensitive.

For example, in a list of 2 items todo AB and todo CD,

`find A` gives the output of a list with todo AB in it.


### Delete: `delete INDEX`

Delete a task at index INDEX (INDEX starts at 1).

For example, 
`delete 1`
