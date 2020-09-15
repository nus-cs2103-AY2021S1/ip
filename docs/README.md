# User Guide

## Features 

### Feature 1 - Add Task
Add a task.  
The task can be:
1. todo
2. event
3. deadline
### Feature 2 - Mark Task as Done
### Feature 3 - Delete Task
### Feature 4 - Find task 
Find a task by a given keyword.
### Feature 5 - List tasks

## Usage

### `todo` - Adds a todo task

Adds a todo task to the list of tasks.

Example of usage: 

`todo assignment`

Expected outcome:

`Got it. I've added this task:`\
`[T][X] assignment`\
` Now you have 3 tasks in the list`

### `event` - Adds an event task

Adds an event task to the list of tasks. Date specified must be in <b>YYYY-MM-DD</b> format.

Example of usage:

`event lesson /at 2020-09-15`

Expected outcome:

`Got it. I've added this task:`\
`[E][X] lesson (at: Sep 15 2020)`\
` Now you have 3 tasks in the list`

### `deadline` - Adds a deadline task

Adds a deadline task to the list of tasks. Date specified must be in <b>YYYY-MM-DD</b> format.

Example of usage:

`deadline project /by 2020-09-16`

Expected outcome:

`Got it. I've added this task:`\
`[D][✗] project (at: Sep 16 2020)`\
` Now you have 3 tasks in the list`

### `list` - Lists all tasks

Lists all tasks. Each task is given an index starting from 1.

Example of usage:

`list`

Expected outcome:

`Here are the tasks in your list:`\
`1. [T][✗] assignment`\
`2. [E][✗] lesson (at: Sep 15 2020) `\
`3. [D][✗] project (at: Sep 16 2020)`

### `done` - Mark task as done

Mark a task specified by the index as done. Index is obtained through `list`.

Example of usage:

`done 1`

Expected outcome:

`Nice I've marked this task as done:`\
`1. [T][✓] assignment`

### `delete` - Delete a task

Deletes a task specified by the index. Index is obtained through `list`.

Example of usage:

`delete 1`

Expected outcome:

`Noted. I've removed this task:`\
`[T][✓] assignment`\
`Now you have 2 tasks in the list`

### `find` - Find a task

Finds a task specified by the given keyword. The search is case insensitive.

Example of usage:

`find project`

Expected outcome:

`Here are the matching tasks in your list:`\
`2. [D][✗] project (by: Sep 16 2020)`

### `help` - Displays help page for commands

Displays help page for an individual command or all commands.

Example of usage:

`help todo`\
`help`

Excepted outcome for `help todo`:\
`todo command`\
&nbsp;&nbsp;&nbsp;&nbsp;`Create a todo task.`\
&nbsp;&nbsp;&nbsp;&nbsp;`Format: todo <task name>`\
&nbsp;&nbsp;&nbsp;&nbsp;`Eg. todo homework1`

