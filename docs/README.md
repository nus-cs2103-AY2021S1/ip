# Jarvis User Guide

## Features 

### List tasks
List all tasks that have been saved.

### Find tasks

Search existing tasks using a keyword

### Add tasks

The following types of tasks can be added

1. Todo
2. Event
3. Deadline

### Mark tasks as done

Tasks are initially added as incomplete and can be marked as done.

### Delete tasks

Individual tasks can be deleted.

### Auto-save and load

Tasks are automatically saved and restored when closing and opening the application.

Users can specify a specific file to load and save to

## Usage

### `list` - List all tasks

All tasks currently saved will be returned by Jarvis

Example of usage: `list`

### `find <keyword>` -  Find relevant tasks

Tasks containing `keyword` will be returned by Jarvis

Example of usage: `find CS2103` - All tasks containing "CS2103" in their name will be listed

### `todo <name>` - **Add a todo task**

A todo task of name `name` will be added

Example of usage: `todo CS2103 TP` - adds a todo task named "CS2103 TP" to the list of tasks

### `event <name> /at <yyyy-mm-dd>` - **Add an event task**

An event task of name `name` happening at `<yyyy-mm-dd>` will be added

Example of usage: `event CS2103 Exam /at <1999-12-01>` - adds an event task named "CS2103 Exam" happening on 1st December 1999 to the list of tasks

### `deadline <name> /by <yyyy-mm-dd>` - **Add a deadline task**

A deadline task of name `name` due by `<yyyy-mm-dd>` will be added

Example of usage: `deadline CS2103 IP Submission /at <2025-10-29>` - adds a deadline task named "CS2103 IP Submission" due on 29th October 2025 to the list of tasks

### `done <task index>` - **Mark a task as done**

The task at the index indicated by the user will be marked as done.

Example of usage: `done 2`

### `delete <task index>` - **Delete a task**

The task at the index indicated by the user will be deleted.

Example of usage: `delete 7`

### `load` - **Choose a file to load/save to**

Jarvis prompts the user for a .txt file to load from and save tasks to

Example of usage: `load`

### `bye` - **Quit Jarvis** 

Jarvis exits

Example of usage: `bye`