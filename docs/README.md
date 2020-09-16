# User Guide

![Claude Screenshot](/docs/Ui.png)

Claude is a chatbot written in `Java 11` that can be used as a personal task manager. 
The current version supports tracking of todos, events, and deadlines. 

## Quick Start
1. Ensure you have Java 11 installed on your computer.
2. Downloaded the latest `ip.jar`.
3. Copy the file to the folder you want to use as the _home folder_ for Claude.
4. Start Claude by running `java -jar ip.jar` in a terminal application of your choice.
5. Type the commands in the box at the bottom of the screen, and pres `Enter` or hit the Send button.

Enjoy!

## Features 

> Note on command format:
> * Words in `UPPER_CASE` denote parameters to be supplied by the user.


### Adding a Deadline: `deadline`
Adds a deadline-type task to the existing task list.

Format: `dateline DESCRIPTION /by DATE`
* `DESCRIPTION` should contain the description of the event to be added.
* `DATE` should contain the deadline of the task to be added, in a `YYYY-MM-DD` format.

Example:
* `dateline CS2103 iP Final Submission /by 2020-09-18`


### Adding an Event: `event`
Adds an event-type task to the existing task list.

Format: `event DESCRIPTION /at DATE`
* `DESCRIPTION` should contain the description of the event to be added.
* `DATE` should contain the date of the event to be added, in a `YYYY-MM-DD` format.

Examples:
* `event CS2103 Tutorial /on 2020-09-17`
* `event CS2103 Lecture /on 2020-09-18`


### Adding a Todo: `todo`
Adds a todo-type task to the existing task list.

Format: `todo DESCRIPTION`
* `DESCRIPTION` should contain the description of the task to be added.

Example:
* `todo Catch Pokemon`


### Listing tasks: `list`
Shows all the tasks on existing task list.

Format: `list`


### Finding a task: `find`
Searches the existing list of tasks for tasks that contain the input search string.

Format: `find SEARCH_STRING`
* `SEARCH_STRING` should contain the word or series of words to search for.

Example:
* `find CS2103`


### Marking a task as done: `done`
Marks the task at the specified index as done.

Format: `done INDEX`
* `INDEX` refers to the index of task as shown by the `list` command.
* `INDEX` must be a positive integer.

Example:
* `done 42`


### Deleting a task: `delete`
Deletes the task at the specified index.

Format: `delete INDEX`
* `INDEX` refers to the index of task as shown by the `list` command.
* `INDEX` must be a positive integer.

Example:
* `delete 666`


### Exiting the application: `bye`
Exits the application.

Format: `bye`
