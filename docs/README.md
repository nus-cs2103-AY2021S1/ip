# Duke User Guide

Duke is a desktop app for managing tasks, deadlines and other events. It's named after the Java mascot _Duke_, and the UI developed with the use of JavaFX. Given below are instructions on how to use it.

## Quick Start

1. Ensure you have Java 11 or above installed in your Computer.
2. Download the latest `duke.jar` from [here](https://github.com/JunCheng98/ip/releases/tag/v0.2).
3. Copy the file to the folder you want to use as the home folder for your Duke.
4. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data, as well as a help list that pops up when you intialise the app.
![Image of sample data](https://i.imgur.com/w9PKpPy.png)
5. Type the command in the command box and press Enter to execute it. e.g. typing `help` and pressing Enter will open the help window. More commands can be found in the list below.

## Features

The letter inside the brackets next to each command name is the shortcut for that command; type those instead for faster execution!

### Show instruction list: `help`(h)

Shows a message in the app with the list of available commands for you to use.

Format: `help`

### Show all current tasks: `list`(l)

Shows all tasks listed in data/duke.txt.

Format: `list`

### Exit the app: `bye`(b)

Closes the application.

Format: `bye`

### Complete a task: `done`(o)

Marks a task with the indicated INDEX as complete. The INDEX **must be a positive number** and corresponds to the index number shown in the displayed task list. Command will fail if INDEX exceeds the number of tasks in the list.

Format: `done INDEX`

Example: `done 3`

### Delete a task: `delete`(x)

Deletes a task with the indicated INDEX. The INDEX **must be a positive number** and corresponds to the index number shown in the displayed task list. Command will fail if INDEX exceeds the number of tasks in the list.

Format: `delete INDEX`

Example: `delete 5`

### Check for tasks on a specific date: `check`(c)

Lists all tasks with the indicated DATE. The DATE **must be in YYYY-MM-DD format**. If no tasks occur on the DATE, the command will return an empty list.

Format: `check DATE`

Example: `check 2020-09-01`

### Add a todo task: `todo`(t)

Adds a todo task to the task list with a provided DESC. The DESC **should not be empty**, otherwise the command will fail. DESC can contain more than one word.

Format: `todo DESC`

Example: `todo finish this user guide`

### Add a deadline task: `deadline`(d)

Adds a deadline task to the task list with a provided DESC and DATE. The DESC **should not be empty** and the DATE **must be in YYYY-MM-DD format**, otherwise the command will fail. DESC can contain more than one word.

Format: `deadline DESC /by DATE`

Example: `deadline update iP increments /by 2020-09-16`

### Add an event task: `event`(e)

Adds an event task to the task list with a provided DESC and DATE. The DESC **should not be empty** and the DATE **must be in YYYY-MM-DD format**, otherwise the command will fail. DESC can contain more than one word.

Format: `event DESC /at DATE`

Example: `event CS2103T Tutorial /at 2020-09-16`

### Find tasks with a common keyword: `find`(f)

Lists all tasks with the indicated KEYWORD. The KEYWORD can be longer than one word. If no tasks contain the KEYWORD, the command will return an empty list.

Format: `find KEYWORD`

Example: `find Tutorial`

## Command Summary

Action   | Format/Examples
---------| -------------
help | `help`
list | `list`
bye | `bye`
done | `done INDEX`<br>e.g. `done 3`
delete | `delete INDEX`<br>e.g. `delete 2`
check | `check DATE`<br>e.g. `check 2020-07-02`
todo | `todo DESC`<br>e.g. `todo new stuff`
deadline | `deadline DESC /by DATE`<br>e.g. `deadline this thing /by 2020-09-09`
event | `event DESC /at DATE`<br>e.g. `event something else /at 2020-12-12`
find | `find KEYWORD`<br>e.g. `find something`
