# User Guide
https://bchenghi.github.io/ip/

## About
```
 ____        _        
|  _ \ _   _| | _____ 
| | | | | | | |/ / _ \
| |_| | |_| |   <  __/
|____/ \__,_|_|\_\___|
Hello! I'm Duke, your friendly task manager.
How can I help you today?
```

## Download
Download the jar file from releases.

## Features 

### Stores your tasks 
Duke allows you to store tasks and search for them in the future.

### Multiple task types
Choose from Todo, Deadline and Event task types.

### Undo and Redo
Duke has undo and redo features to correct mistakes easily.

### Done or not done
Duke displays a tick or cross to easily notify the user if the task was done or not.

### Search for your tasks
Duke allow searching for multiple keywords, making searching for tasks easy.
## Usage

### `todo` - Adds a Todo task

Will add a Todo task to Duke.

Example of usage: 

`todo buy groceries`

Expected outcome:

`Got it! I've added this task:`\
`[T][X] buy groceries`\
`Now you have 1 tasks in your list.`

### `deadline` - Adds a Deadline task

Will add a Deadline task with a date and time to Duke.

Example of usage: 

`deadline do assignment /by 22/12/2020 1600`

Expected outcome:

`Got it! I've added this task:`\
`[D][X] do assignment (by: Dec 22 2020 16:00)`\
`Now you have 1 tasks in your list.`

### `event` - Adds a Event task

Will add a Event task with a date and time to Duke.

Example of usage: 

`event attend Mary's Birthday /at 8/12/2020 1200`

Expected outcome:

`Got it! I've added this task:`\
`[E][X] attend Mary's Birthday (at: Dec 8 2020 12:00)`\
`Now you have 2 tasks in your list.`

### `list` - Lists all tasks in Duke

Expected outcome:

`Here are the tasks in your list:`\
`1. [D][X] do assignment (by: Dec 22 2020 16:00)`\
`2. [E][X] attend Mary's Birthday (at: Dec 8 2020 12:00)`

### `delete` - Deletes a task in Duke

Deletes the task at the given index. Index can be seen from `list`.

Example of usage: 

`delete 1`

Expected outcome:

`Noted. I've removed this task.`\
`[D][X] do assignment (by: Dec 22 2020 16:00)`\
`Now you have 1 tasks in your list`

### `find` - Finds all tasks in Duke with the matching keywords

Finds all tasks that contain the keywords specified.

Example of usage: 

`find assignment CS2100`

Expected outcome:

`Here are the tasks with "assignment", "CS2100" in your list:`\
`1. [D][X] do CS2100 assignment 1 (by: Dec 22 2020 16:00)`\
`2. [D][X] do CS2100 assignment 2 (by: Jan 21 2020 11:00)`

### `done` - Sets a task as done

Sets the task at the given index as done.

Example of usage:

`done 2`

Expected outcome:

`Nice! I've marked this task as done:`\
`[T][✓] get groceries`

### `undo` - Undoes most recent command

Undoes the most recent command since opening Duke.

Expected outcome:

`Successfully undid task!`\
or\
`Oops! No more tasks to undo.`

### `redo` - Redoes the most recent undone command

Redoes the most recent undone command since opening Duke.

Expected outcome:

`Successfully redid task!`\
or\
`Oops! No more tasks to redo.`

### `date` - Lists all event and deadline tasks with the date

Lists all events that happen on the date and deadline tasks that is due on the date

`date 12/2/2020`

Expected outcome:

`There are no tasks with the date: 2020-02-12`\
or\
`Here are the tasks with the date: 2020-02-12`\
`1. [D][X] do CS2100 assignment 1 (by: Feb 12 2020 16:00)`

### `dateAndTime` - Lists all event and deadline tasks with the date and time

Lists all event and deadline tasks with the specified date and time.

`dateAndTime 21/2/2020 1600`

Expected outcome:

`There are no tasks with the date: 2020-02-12 and time: 16:00`\
or\
`Here are the tasks with the date: 2020-02-12 and time: 16:00`\
`1. [D][X] do CS2100 assignment 1 (by: Feb 12 2020 16:00)`

### `bye` - Exits Duke
