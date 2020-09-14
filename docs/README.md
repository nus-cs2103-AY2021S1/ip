# User Guide
Duke is a chatbot that manages your tasks. You can use Duke to organize tasks that you need to do, deadlines and events .
## Features 

### Quickstart
1. Make sure you have Java 11 or above installed in your computer
1. Download the latest `duke.jar`.
1. Run the JAR file using the command `java -jar duke.jar` in the directory that the JAR file is in.
1. You can now use the program by typing in commands (described below).

## Usage

### `todo` - Adding a todo

Adds a task that you need to do in your to do list in Duke.

Format: `todo DESCRIPTION [#TAG]`

Examples of usage: 

* `todo do homework`
* `todo do homework #school`

### `deadline` - Adding a deadline

Adds a deadline into your to do list in Duke.

Format: `deadline DESCRIPTION [#TAG] /by DATETIME` where `DATETIME` is in the format `YYYY-MM-DD HHMM`, and time is optional

Examples of usage: 

* `deadline individual project /by 2020-09-17 2359`
* `deadline individual project /by 2020-09-17`
* `deadline individual project #cs2103T /by 2020-09-17 2359`

### `event` - Adding an event

Adds an event into your to do list in Duke.

Format: `event DESCRIPTION [#TAG] /at DATETIME` where `DATETIME` is in the format `YYYY-MM-DD HHMM`, and time is optional

Examples of usage: 

* `event mum’s birthday /at 2020-09-17 1800`
* `event mum’s birthday /at 2020-09-17`
* `event mum’s birthday #family /at 2020-09-17 1800`

### `done` - Mark a task as done

Marks a task in your to do list as done.

Format: `done TASKNUMBER`

Example of usage: `done 2`

### `delete` - Delete a task

Deletes a task from your to do list.

Format: `delete TASKNUMBER`

Example of usage: `delete 2`

### `find` - Find a task

Finds a task in your to do list according to a keyword input from the user.

Format: `find KEYWORD`

Example of usage: `find piano`

### `list` - List all tasks

Displays all items in your to do list.

Example of usage: `list`

### `bye` - Exit

Exits the program.

Example of usage: `bye`
