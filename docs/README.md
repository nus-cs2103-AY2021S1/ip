# User Guide

## Duke

![GitHub Logo](Ui.png)

**Duke** is an application that can help you to *manage tasks*. It supports *saving*, *editing*, *deleting* and 
*displaying* **tasks** using simple **commands**.

### Quick Start

**Prerequisites: Java 11 or later installed.**

1. **Download** the latest version of Duke `(a jar file)` from the [website](https://github.com/Ma-Yueran/ip/releases).
1. **Copy** the jar file to the directory you want to use as the *home directory* for the application.
1. `Double click` the jar file or enter `java -jar duke.jar` in console to run the program.
1. The window shown above should appear.
1. *Well done!* Now you can start using Duke!

## Features 

### Creating a new ToDo task: `todo`

Format: `todo <task description>`

Example: `todo read book`

Expected outcome: *Todo* task created **if there is no duplications**.

### Creating a new Event task: `event`

Format: `event <task description> /at <time>`

Example: `event have dinner /at 18:00`

Expected outcome: *Event* task created **if there is no duplications**.

### Creating a new Deadline task: `deadline`

Format: `deadline <task description> /by <time>`

Example: `deadline return book /by next Firday`

Expected outcome: *Deadline* task created **if there is no duplications**.

### Listing current tasks: `list`

Format: `list`

Example: `list`

Expected outcome: A list of current tasks shown.

### Deleting a task using an index: `delete`

Format: `delete <task index>`

Example: `delete 1`

Expected outcome: The task with the given index is deleted.

### Tagging a task: `tag`

Format: `tag <task index> <tag>`

Example: `tag 2 This is a tag...`

Expected outcome: The task with the given index is tagged with the input tag string.

### Removing the tag of a task: `untag`

Format: `untag <task index>`

Example: `untag 2`

Expected outcome: The tag of  the task with the given index is removed.

### Marking a task as done: `done`

Format: `done <task index>`

Example: `done 3`

Expected outcome: The task with the given index is marked as done.

### Finding tasks using a keyword: `find`

(The *keyword* does not have to be a word)

Format: `find <keyword>`

Example: `find re`

Expected outcome: All tasks that contains the keyword in their description are shown.

### Showing all available commands: `help`

Format: `help`

Example: `help`

Expected outcome: Descriptions and formats of all commands are shown.

### Exiting the application: `bye`

Format: `bye`

Example: `bye`

Expected outcome: All current tasks are saved, and the application ends.

### Showing the table of current tasks

Method: Click `View` -> `Task List...`

Expected outcome: A window containing the table of tasks pops up.

### Showing the table of all commands

Method: Click `Help` -> `Commands...`

Expected outcome: A window containing the table of all commands pops up.

## Saving Task Data

**3 Ways of Saving Task Data in Duke**

* Click `File` -> `Save`
* Choose `Yes` in Exit window.

![GitHub Logo](ExitWindow.png)
* Enter command `bye` to exit the application.