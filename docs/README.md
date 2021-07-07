# User Guide
###### Developed by Stefan

## Features

### 1. Add Todo: `todo`
- Adds a `Todo` task to the task list.
- Usage: `todo TASKNAME`

#### Example:
`todo CS2103 Lecture`

#### Expected Outcome:
Adds a todo "CS2103 Lecture" to the task list.

### 2. Add Deadline: `deadline`
- Adds a `Deadline` task to the task list.
- Usage: `deadline TASKNAME /by TIME`

#### Example:
- `deadline CS2103 iP /by 2020/09/28 2359`

#### Expected Outcome:
Adds a deadline "CS2103 iP" which is due by "2020/09/28 23:59" to the task list.

### 3. Add Event: `event`
- Adds an `Event` task to the task list.
- Usage: `event TASKNAME /at TIME`

#### Example:
- `event Gym /at Sunday 7pm-9pm`

#### Expected Outcome:
Adds an event "Gym" which is happening at "Sunday 7pm - 9pm" to the task list. 

### 4. List All Tasks: `list`
- Prints out the current task list.
- Usage: `list`

#### Expected Outcome:
The information of respective tasks in the list will be printed.

### 5. Mark Tasks as Done: `done`
- Sets a task status to `done`
- Usage: `done NUMBER`

#### Example:
- `done 5`

#### Expected Outcome:
Task 5 in the task list will be marked as done.

### 6. Delete Task: `delete`
- Deletes a task from the list.
- Usage: `delete NUMBER`

#### Example:
- `delete 10`

#### Expected Outcome:
Task 10 in the task list will be deleted.

### 7. Find Tasks
- Displays a list of tasks containing a keyword (not case-sensitive).
- Usage: `find KEYWORD`

#### Example:
- `find Gym`

#### Expected Outcome:
All the tasks that are named "Gym" will be listed.

### 8. Undo: `undo`
- Undo the previous command
- Usage: `undo`

#### Example:
- `delete 3` `undo`

#### Expected Outcome:
Task 3 in the task list will first be deleted then recover from the deletion.

### 9. Bye: `bye`
- Exits the application.
- Usage: `bye`

#### Expected Outcome:
Duke will say goodbye to you.

