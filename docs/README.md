# User Guide

## What is Duke?
Duke, is a Personal Assistant Chatbot 
that helps you to keep track of various things,  
optimized for use via a Command Line Interface (CLI) 
while still having the benefits of a Graphical User Interface (GUI). 
The name 'Duke' was inspired by Duke, the Java Mascot.

![Duke Logo](../src/main/resources/images/javaduke.jpg)

## Quick Start 

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `addressbook.jar` from [here](https://github.com/se-edu/addressbook-level3/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your AddressBook.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](./Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`list`** : Lists all contacts.

   * **`add`**`n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01` : Adds a contact named `John Doe` to the Address Book.

   * **`delete`**`3` : Deletes the 3rd contact shown in the current list.

   * **`clear`** : Deletes all contacts.

   * **`exit`** : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features 

### Lists all tasks: `list` 

Lists all the tasks that is saved in the device.
 
### Adds *todo* tasks: `todo` 

Save a todo tasks into the list.

Format: `todo DESCRIPTION`

Example of usage: 

`todo read book`

Expected outcome:

`Got it!...`

### Adds *deadline* tasks: `deadline` 

Save a deadline tasks into the list.

Format: `deadline DESCRIPTION /by DATE/MONTH/YEAR`

### Adds *event* tasks: `event` 

Save an event tasks into the list.

Format: `event DESCRIPTION /at DATE/MONTH/YEAR`

### Delete a tasks: `delete` 

Delete a task in the list by given index.

Format: `delete INDEX`

### Find keyword: `find` 

Find all matching task by given description.

Format: `find DESCRIPTION`

### Mark a task as done: `done` 

Mark a task as done by its index.

Format: `done INDEX`

### Sort the task list : `sort` 

Sort the task list by date or by description.

Format: `sort by date` / `sort by description`
