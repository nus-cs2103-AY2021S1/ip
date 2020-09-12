# User Guide

## Features 

### Manage tasks
Add and remove tasks easily, allowing you to keep track of the tasks you need to do.

### Search
Easily search for certain tasks using the search function, by entering specific keywords.

### Check off tasks
Once a task is done, check it off to mark it as done.

## Usage
The following lists all available commands.

### `LIST` - Lists all tasks.

This function shows all the tasks you have added.

Example of usage: 

`list`

Expected outcome:

`1. [T][X] Return books`

`2. [T][O] Do homework`

### `DONE [TASK NUMBER]` - Marks a task as done.

This function marks a given task as done.

Example of usage: 

`done 1`

Expected outcome:

`Nice! I've marked this task as done: [T][O] Return books`

### `DELETE [TASK NUMBER]` - Deletes a task.

This function deletes a given task.

Example of usage: 

`delete 2`

Expected outcome:

`Noted. I have removed this task: [T][O] Do homework`

### `FIND [QUERY]` - Searches for a task.

This function searches for tasks given a search query.

Example of usage: 

`find homework`

Expected outcome:

`Here are the matching tasks in your list: 1. [T][O] Do homework`

### `TODO [DESCRIPTION]` - Adds a todo task.

This function adds a simple todo task with no additional details.

Example of usage: 

`todo meet John`

Expected outcome:

`Got it. I've added this task: [T][X] meet John`

### `DEADLINE [DESCRIPTION] /by [YYYY-MM-DD]` - Adds a deadline.

This function adds a deadline task with due date.

Example of usage: 

`deadline write essay /by 2020-12-09`

Expected outcome:

`Got it. I've added this task: [D][X] write essay (by: Dec 9 2020)`

### `EVENT [DESCRIPTION] /at [YYYY-MM-DD]` - Adds an event.

This function adds an event task with its date.

Example of usage: 

`event project meeting /at 2020-12-09`

Expected outcome:

`Got it. I've added this task: [E][X] project meeting (at: Dec 9 2020)`

### `HELP` - Shows all keywords and descriptions.

This function brings up this help page within the app.

### `BYE` - Exits the app.

This function saves all data and exits.

