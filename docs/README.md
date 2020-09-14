# User Guide

## Features 

### List out all Tasks
Use `list` to view all tasks.

### Search for specific keywords
Use `find KEYWORD` to list all tasks that contain the specified keyword.

### Complete a Task
Use `done INDEX` to complete the task at the specified index.

### Delete a Task
Use `delete INDEX` to delete the task at the specified index.

### Get Help
Use `help` for Duke to give you some instructions and tips in case you have forgotten any commands.

### Exiting Duke
Use `bye` to save your data and exit the programme.

## Usage

### `list`

Example of usage: 

`list`

Expected outcome:

`Here are your tasks: ...` If no such tasks exists it will return `None`.

### `find`

Example of usage: 

`find (keyword1 keyword2...)`

Expected outcome:

All tasks that contain all the keywords. If no such tasks exists it will return `None`.

### `done`

Example of usage: 

`done INDEX` 

Index must be within range.

Expected outcome:

`Nice! I've marked this task as done: [TASK IN QUESTION]` 

If the specified index is not within range, the response will be `duke.exception.InvalidIndexException: OOPS!!! The index you have 
chosen is out of bounds`

### `delete`

Example of usage: 

`delete INDEX` 

Index must be within range.

Expected outcome:

`Nice! I've removed this task: [TASK IN QUESTION] Now you have XYZ tasks in the list.` 

If the specified index is not within range, the response will be `duke.exception.InvalidIndexException: OOPS!!! The index you have 
chosen is out of bounds`

### `todo`

Creates a todo task.

Example of usage: 

`todo DESCRIPTION` Description cannot be empty. It must contain at least 1 nonspace character.

Expected outcome:

`Got it. I've added this task:[TASK IN QUESTION] Now you have XYZ tasks in the list.`

If description is invalid, the response will be: `duke.exception.InvalidDescriptionException: OOPS!!! The description of a task cannot be empty`

### `event`

Creates an event task.

Example of usage: 

`event DESCRIPTION /at TIMING` Description and timing cannot be empty. They must contain at least 1 nonspace character.

Expected outcome:

`Got it. I've added this task: [TASK IN QUESTION] Now you have XYZ tasks in the list.`

If description or timing is invalid, the response will be: `duke.exception.InvalidDescriptionException: OOPS!!! The description of a task cannot be empty`

### `deadline`

Creates an event task.

Example of usage: 

`event DESCRIPTION /by TIMING` Description and timing cannot be empty. They must contain at least 1 nonspace character.

Expected outcome:

`Got it. I've added this task: [TASK IN QUESTION] Now you have XYZ tasks in the list.`

If description or timing is invalid, the response will be: `duke.exception.InvalidDescriptionException: OOPS!!! The description of a task cannot be empty`

### `help`

Example of usage: 

`help`

Expected outcome:

`Don't panic! I am here. Here are some useful commands: ...`

### `bye`

Example of usage: 

`bye`

Expected outcome:

Window closes.