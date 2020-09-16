# User Guide

![GUI](Ui.png)

Tickbot is a CLI-based, easy-to-use task manager.

## Features 

### Manage tasks

You can check your task list, add or delete a task, mark a task as completed, and add tags for a tasks.

### Save the task list

Your task list is automatically saved to your computer, so no need to worry about data loss!

### Both console interface and GUI

Tickbot is shipped with both console interface and GUI. Start up the application with no command line arguments to use GUI; start up with a command line argument `--cli` to use the console interface.

## Usage

### `deadline` - Add a deadline task

Add a deadline task to your task list.

Usage: `deadline <content> /by <yyyy-MM-dd[ HH:mm[:ss]]>`

### `event` - Add a event task

Add a event task to your task list.

Usage: `event <content> /at <yyyy-MM-dd[ HH:mm[:ss]]>`

### `todo` - Add a TO-DO task

Add a TO-DO task to your task list.

Usage: `todo <content>`

### `list` - list all tasks

Show all your tasks in the task list.

Usage: `list`

### `done` - mark a task as completed

Mark a task with given index in the task list is completed.

Usage: `done <index>`

### `delete` - delete a task

Remove a task with given index from your task list.

Usage: `delete <index>`

### `tag` - add tags to a task

Add tags to a task with given index in the task list.

Usage: `tag <index> [<tag> ...]`

### `help` - show help for commands

Show the help message for commands

Usage:
-   `help` to show all available commands
-   `help <command>` to show help messages for a certain command

### `bye` - exit the program

Exit the program.

Usage: `bye`