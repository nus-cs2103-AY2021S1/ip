# User Guide

**Notes on format:**
* Words in `UPPER_CASE` are parameters supplied by the user.
e.g. in `todo DESCRIPTION`, `DESCRIPTION` can be used as `todo buy groceries`.
* `...` Means multiple input is allowed.
## Features 

### 1. Add a Todo Task: `todo`
Adds todo task to the task list.

Format: `todo DESCRIPTION`

Example: `todo Pick up groceries`

### 2. Add a Deadline Task: `deadline`
Adds task with deadline to the task list.

Format: `deadline DESCRIPTION /by DATE_TIME`

Example: `deadline submit quiz /by 2020-20-20 2020`

### 3. Add an Event Task: `event`
Adds an event task to the task list.

Format: `event DESCRIPTION /at DATE_TIME`

Example: `event Hair for hope /at 2020-20-20 2020`

### 4. List all task: `list`
List all current task in the task list.

Format" `list`

### 5. Finding task: `find` 
List all task that contains the keyword.

Format: `find KEYWORD`

### 6. Deleting a task: `delete`
Deletes Task at the given index.

Format: `delete INTEGER_VALUE...`

Example: `delete 1 2 3 4`

### 7. Marking task as Done: `done`
Mark task as done at the given index.

Format: `done INTEGER_VALUE...`

Example: `done 1 2 3 4`

### 8. Exit the program: `bye`
Exit the program and saves the list of tasks.

Format: `bye`

## Command summary

* `todo`     `DESCRIPTION`
* `deadline` `DESCRIPTION /by DATE_TIME`
* `event`    `DESCRIPTION /at DATE_TIME`
* `delete`   `INTEGER_VALUE...`
* `done`     `INTEGER_VALUE...`
* `find`     `KEYWORD`
* `list` 
* `bye`