# User Guide

## Features 

### Record todo task 
Records an ordinary task with the name specified.
### Record deadline
Records a task with the name specified, together with the date and time of the deadline.
### Record event
Records an upcoming event with the name, date, and time specified.
### Delete task
Deletes the corresponding task.
### Mark as done
Marks the corresponding task as done.
### List tasks
Lists down all tasks.
### Find tasks
Find tasks based on the query.
### Add Alias
Adds an alias for an existing command. The user can then use the alias in place of the original command.
### Exit
Exits the application.

## Usage

### `todo <name>` - Record todo task 

Saves the task with the given name into memory and show a confirmation message.

Example of usage: 

`todo tidy room`

Expected outcome:

A new todo 'tidy room' is saved.

### `deadline <name> /by <dd-mm-yy> <hh:mm>` - Record deadline

Saves the given task with the name, date, and time specified into memory and show a confirmation message.

Example of usage: 

`deadline assignment /by 12-09-20 20:00`

Expected outcome:

A new deadline 'assignment' is saved, due at 12th September 2020 at 8pm.

### `event <name> /by <dd-mm-yy> <hh:mm>` - Record event.

Saves the given task with the name, date and time specified into memory and show a confirmation message.

Example of usage: 

`event meeting /at 12-09-20 20:00`

Expected outcome:

A new event 'meeting' is saved for 12th September 2020 at 8pm.

### `delete <id>` - Delete task.

Deletes the task that corresponds to the \<id>th item on the task list from memory and show a confirmation message.

Example of usage: 

`delete 3`

Expected outcome:

Deletes the third task in the saved task list.

### `done <id>` - Marks as done.

Marks the task that corresponds to the \<id>th item on the task list as done and show a confirmation message.

Example of usage: 

`done 3`

Expected outcome:

Marks as done the third task in the saved task list.

### `list` - List tasks.

shows all tasks that are currently stored in memory on the GUI with their relevant attributes.

Example of usage: 

`list`

Expected outcome:

shows all tasks on the screen.

### `find <query>` - Find tasks

Shows all tasks that contains the query.

Example of usage: 

`find math`

Expected outcome:

Shows all tasks with the word math in it. eg. "math homework", "maths assignment", "drop math".

### `addalias <alias> <original command>` - Add Alias

Saves an alias for an existing command into memory and shows a confirmation message.

Example of usage: 

`addalias f find`

Expected outcome:

The alias f is now bound to find.

### `bye` - Exit

Exits the application.

Example of usage: 

`bye`

Expected outcome:

The application will close.