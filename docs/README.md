# User Guide
Duke is a **chatbot for managing your tasks via a Command Line Interface (CLI)** while still having the benefits of a 
Graphical User Interface (GUI). It is fun and exciting, and makes managing tasks less of a chore.

## Features 
1. Adding a Task
1. Finding a Task
1. Mark a Task as done
1. Deletes a Task
1. Snooze a Task
1. Lists all the tasks
1. Exit the Program


## Adding a Task: `todo`, `event`, or `deadline`
Adds a todo, event, or deadline task to the task list.

Format: `todo TASK` / `event TASK /at DATE` / `deadline TASK /by DATE`

Words in upper case are parameters. 

DATE is in the following format: YYYY-MM-DD

Examples:
* `todo Sleep`
* `event Birthday /at 2020-01-02 `
* `deadline Submit homework /by 2020-01-02`
 

## Finding a Task: `find`
Finds a task with the given keyword.

Format: `find KEYWORD`

KEYWORD is a parameter.

Example: `find sleep`

## Mark a Task as Done: `done`
Marks a task as done.

Format: `done TASKNUMBER`

TASKNUMBER is the id of the task that you would want to mark as done.

Example: `done 1`

## Deletes a Task: `delete`
Delete a task from the task list.

Format: `delete TASKNUMBER`

TASKNUMBER is the id of the task that you would want to delete.

Example: `delete 1`

## Snooze a task: `snooze`

Snooze a task, and change the date of the `event` or `deadline` (could be earlier or later).

Format: `snooze TASKNUMBER DATE`

TASKNUMBER is the id of the task that you would want to snooze.

DATE is the new desired date of the task.

## Lists all the tasks: `list`

Lists all the tasks in the list.

Format: `list`

## Exit and save the task list: `bye`

Exists and save all the current tasks to a txt file in the hard drive.

Format: `bye`