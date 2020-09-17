# User Guide

![](Ui.png)

Straw bot is a desktop app for managing todo tasks, optimized for use via a Command Line Interface.
Graphical User Interface (GUI) is added for better user accessibility.

## Quick Start

1. Ensure you have Java 11 or above installed in your Computer.

2. Download the latest addressbook.jar from this repository release.

3. Copy the file to the folder you want to use as the home folder for your Straw bot.

4. Launch the jar file to start the app.

5. Type the command in the comand box and press Enter to execute it. For more commands, refer to the next section.

## Features 
Words in UPPER_CASE are the parameters to be supplied by the user.
Items in curly brackets are date formats.

### List: `list`

Shows a list of all the user's tasks.

### Exit: `bye`

Terminates the programme

### Add a todo task: `todo TASK_NAME`

Add a task with the name TASK_NAME.

### Mark a task as Done: `done INDEX`

Item at INDEX is marked as done (Index starts at 1)

### Add an Event: `event EVENT_NAME /at {yyyyMMddHHmm}`
or `event EVENT_NAME /at {yyyyMMdd HHmm}`
or `event EVENT_NAME /at {HH:mm ddMMyy}`
or `event EVENT_NAME /at {yyyy-MM-dd HH:mm}`

Add an event with the name EVENT_NAME. It accepts the 4 different formats of date.

### Add a Deadline: `deadline DEADLINE_NAME /by {yyyyMMddHHmm}`
or `event DEADLINE_NAME /by {yyyyMMdd HHmm}`
or `event DEADLINE_NAME /by {HH:mm ddMMyy}`
or `event DEADLINE_NAME /by {yyyy-MM-dd HH:mm}`

Add a deadline with the name DEADLINE_NAME. It accepts the 4 different formats of date.

### Find: `find NAME`

Shows all tasks whose name has the substring NAME.

### Delete: `delete INDEX`

Delete task at index INDEX (Index starts at 1)
