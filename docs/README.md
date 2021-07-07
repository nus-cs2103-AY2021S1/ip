# iPbot User Guide

![Screenshot](Ui.png)

## Features 
### `todo` - Creates a new Todo task

Adds a new Todo task. A Todo task only has a description attached.

Usage: `todo <Task Description>`

Expected output: An acknowledgement that iPbot has added the task.

### `event` - Creates a new Event task

Adds a new Event task. An Event task has both a description and an event time.

Usage: `event <Task Description> /at <Event Time>`

Expected output: An acknowledgement that iPbot has added the task.

### `deadline` - Creates a new Deadline task

Adds a new Deadline task. A Deadline task has both a description and a time due.

Usage: `deadline <Task Description> /by <Time Due>`

Expected output: An acknowledgement that iPbot has added the task.

### `list` - Lists all tasks

Retrieves all tasks in the task list and displays them.
Note that iPbot always displays the latest task list on the right.

Usage: `list`

Expected output: The list of tasks

### `done` - Marks a task as done

Marks the task with a given ID as done. The task ID refers to the numbering in the task list.
If the task was already marked as done, iPbot will report that in its output. 

Usage: `done <Task ID>`

Expected output: An acknowledgement that the task was completed.

### `bye` - Exits from iPbot

Immediately closes iPbot.

Usage: `bye`

Expected output: The application exits.

### `load` - Loads commands from a file

Silently loads a script of commands from an external file.
This can be the saved data file from another instance of iPbot.

Usage: `load <File Location>`

Expected output: The application exits.

### `help` - Displays help message

Displays a list of available commands.
Note that iPbot always displays this command list on the left.

Usage: `help`

Expected output: A list of available commands.
