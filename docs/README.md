# Spongebot Patty Flipper User Guide

Spongebot Patty Flipper is a JavaFX application used to **managing tasks, optimized for use via a Command Line Interface (CLI)**. If you can type fast, Spongebot Patty Flipper can get your tasks managed faster than traditional GUI apps.

## Quick Start

1. Ensure you have Java 11 or above installed in your Computer.
2. Download the latest spongebot.jar from here.
3. Copy the file to the folder you want to use as the home folder for your Spongebot Patty Flipper.
4. Double-click the file to start the app. The GUI should appear in a few seconds.
5. Commands that can be executed in the command box and other important words are highlighted in `light blue`. e.g. type help and press Enter will open the help window. Some example commands you can try:
   - `list` : Lists all tasks in Spongebot Patty Flipper.
   - `todo Example` : Adds a Task of type Todo with the name "Example".
   - `exit` : Exits the app.

## Notes about the command format:

* Words in UPPER_CASE are parameters to be supplied by the user. E.g. in `todo TODO_NAME`, TODO_NAME is the parameter in this command
* Parameters must be in a fixed order

## Features:

### Adding a todo: `todo` 

Adds a Todo task to Spongebot Patty Flipper.

Format: `todo TODO_NAME`

Example:

* `todo Task 1` : Adds a Task of type Todo with the name "Task 1".

### Adding a deadline: `deadline`

Adds a Deadline task to Spongebot Patty Flipper.

Format: `deadline DEADLINE_NAME /by DATE_AND_TIME`

* DATE_AND_TIME format is in DD-MM-YYYY HHMM

Example:

* `deadline Deadline 1 /by 20-12-2000 1200` : Adds a Task of type Deadline with name "Deadline 1" on the 20 December 2000 at 12pm

### Adding an event: `event`

Adds an Event task to Spongebot Patty Flipper.

Format:  `event EVENT_NAME /at DATE_AND_TIME`

* DATE_AND_TIME format is in DD-MM-YYYY HHMM

Example:

* `event Event 1 /on 20-12-2000 1200` : Adds a Task of type Event with name "Event" on the 20 December 2000 at 12pm

### Listing all tasks: `List`

Shows a list of all tasks in Spongebot Patty Flipper.

Format: `list`



### Marking a task as done: `done`

Marks a specified task from Spongebot Patty Flipper as done.

Format: `done INDEX`

* Marks the task at the specified INDEX as done
* The index refers to the index number shown on the displayed task list
* The index **must be a positive integer** 1, 2, 3, ...

Example:

* `done 1` : Marks the 1st task in the list as done.

### Deleting a task: `Delete`

Deletes a specified task from Spongebot Patty Flipper.

Format: `delete INDEX`

* Deletes the task at the specified INDEX
* The index refers to the index number shown on the displayed task list
* The index **must be a positive integer** 1, 2, 3, ...

Example:

* `delete 1` : Deletes the 1st task in the results of the list command.



### Find function: `find`

Find tasks by TASK_NAME in Spongebot Patty Flipper.

Format: `find PHRASE`

* The search is case-sensitive e.g. `find book` will match `borrow book` but not `Borrow Book`

Example:

* `find Deadline`: Returns a list of results of with that contain word "Deadline"

### View Schedule: `schedule`

Find tasks that fall on a specific date in Spongebot Patty Flipper.

Format: `schedule /on DATE

* DATE_AND_TIME format is in DD-MM-YYYY

Example:

* `schedule /on 11-11-2020` returns all Tasks that fall on 11 November 2020

### Closing Application: `bye`

Closes Spongebot Patty Flipper.

# 

## FAQ:

Q: How do I start the application?

A: In the command prompt/terminal: java -jar spongebot.jar

# 

## Author

Marcus Tan Zhong Hong
