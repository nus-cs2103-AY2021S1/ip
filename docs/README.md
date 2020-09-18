# User Guide

## What is Duke?
Duke, is a Personal Assistant Chatbot 
that helps you to keep track of various things,  
optimized for use via a Command Line Interface (CLI) 
while still having the benefits of a Graphical User Interface (GUI). 
The name 'Duke' was inspired by Duke, the Java Mascot.

![Duke Logo](./javaduke.jpg)

## Quick Start 

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `duke.jar` from [here](https://github.com/tanwayne890/ip/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your Duke.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](./Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`list`** and pressing Enter will show the tasks list.<br>
   Some example commands you can try:

   * **`list`** : Lists all tasks.

   * **`todo`**`read book` : Adds a todo task named `read book` to the tasks list.
   
   * **`deadline`**`return book` : Adds a deadline task named `return book` to the tasks list.
   
   * **`event`**`borrow book` : Adds a event task named `borrow book` to the tasks list.

   * **`delete`**`3` : Deletes the 3rd task shown in the current list.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features 

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `todo DESCRIPTION`, `DESCRIPTION` is a parameter which can be used as `todo read book`.

* `First word` of a command belongs to a feature type. If the `first word` of the input is not recognised by Duke,<br>
  Duke will not execute the given command.
  
* Command cannot include sensitive symbols. For example, `|`, `(`, `)`, `[` and `]`.

</div>

### Lists all tasks: `list` 

Lists all the tasks that is saved in the device.
 
### Adds *todo* tasks: `todo` 

Save a todo tasks into the list.

Format: `todo DESCRIPTION`

* `DESCRIPTION` cannot be blank.

Example of usage: 

`todo read book`

### Adds *deadline* tasks: `deadline` 

Save a deadline tasks into the list.

Format: `deadline DESCRIPTION /by DATE/MONTH/YEAR`

* `DESCRIPTION /by DATE/MONTH/YEAR` cannot be blank.

Example of usage:

`deadline CS2103T User guide /by 21/07/2020`

### Adds *event* tasks: `event` 

Save an event tasks into the list.

Format: `event DESCRIPTION /at DATE/MONTH/YEAR HOUR:MINUTES`

* `DESCRIPTION /at DATE/MONTH/YEAR HOUR:MINUTES` cannot be blank.

Example of usage:

`event Google interview /at 15/06/2020 12:00`

### Delete a tasks: `delete` 

Delete a task in the list by given index.

Format: `delete INDEX`

* `INDEX` cannot exceed the number of things in the task list.

Example of usage:

`delete 1`

### Find keyword: `find` 

Find all matching task by given description.

Format: `find DESCRIPTION`

Example of usage:

`find book`

### Mark a task as done: `done` 

Mark a task as done by its index.

Format: `done INDEX`

* `INDEX` cannot exceed the number of things in the task list.

Example of usage:

`done 1`

### Sort the task list : `sort` 

Sort the task list by date or by description.

Format: `sort by date` / `sort by description`

Example of usage:

`sort by date` or `sort by description`

### Saving the data
Duke's data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.
