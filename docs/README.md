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
Use `bye` to exit the programme.

## Usage

#### `list`

Example of usage: 

`list`

Expected outcome:

`Here are your tasks: ...` If no such tasks exists it will return `No tasks found.`

### Commands related to searching for tasks

#### `find`

Example of usage: 

`find (keyword1 keyword2...)`

Expected outcome:

All tasks that contain all the keywords. If no such tasks exists it will return `No tasks found.`

### Commands related to changing a task's status

#### `done`

Example of usage: 

`done INDEX` 

Index must be within range.

Expected outcome:

`Nice! I've marked this task as done: [TASK IN QUESTION]` 

If the specified index is not within range, the response will be `OOPS! Command was not executed! Please choose an index that is within range.`

#### `delete`

Example of usage: 

`delete INDEX` 

Index must be within range.

Expected outcome:

`Nice! I've removed this task: [TASK IN QUESTION] Now you have XYZ tasks in the list.` 

If the specified index is not within range, the response will be `OOPS! Command was not executed! Please choose an index that is within range.`

### Commands related to task creation

You can add a tag to any tag by simply writing a `#` followed by the word.

Example: `do #work today` contains the tag `work`.
#### `todo`

Creates a todo task.

Example of usage: 

`todo DESCRIPTION` Description cannot be empty and must start exactly 1 space after `todo`.

Expected outcome:

`Got it. I've added this task:[TASK IN QUESTION] Now you have XYZ tasks in the list.`

If description is empty or has erroneous spacing, the response will be: `OOPS! Task was not added! Please start your description exactly 1 space after the task type. Type 'help' to see the appropriate format.`

#### `event`

Creates an event task.

Example of usage: 

`event DESCRIPTION /at TIMING` Description cannot be empty and must start exactly 1 space after `event`. Timing must be in `yyyy-MM-dd` format.

Expected outcome:

`Got it. I've added this task: [TASK IN QUESTION] Now you have XYZ tasks in the list.`

If description is empty or has erroneous spacing, the response will be: `OOPS! Task was not added! Please start your description exactly 1 space after the task type. Type 'help' to see the appropriate format.`

if the timing is in invalid format, the response will be: `"OOPS! Task was not added! Please use the yyyy-MM-dd format for your date.`

#### `deadline`

Creates a deadline task.

Example of usage: 

`deadline DESCRIPTION /by TIMING` Description cannot be empty and must start exactly 1 space after `deadline`. Timing must be in `yyyy-MM-dd` format.

Expected outcome:

`Got it. I've added this task: [TASK IN QUESTION] Now you have XYZ tasks in the list.`

If description is empty or has erroneous spacing, the response will be: `OOPS! Task was not added! Please start your description exactly 1 space after the task type. Type 'help' to see the appropriate format.`

If the timing is in invalid format, the response will be: `"OOPS! Task was not added! Please use the yyyy-MM-dd format for your date.`

### Commands related to getting help

#### `help`

Example of usage: 

`help`

Expected outcome:

`Don't panic! I am here. Here are some useful commands: ...`

### Commands related to exiting Duke

### `bye`

Example of usage: 

`bye`

Expected outcome:

Window closes.