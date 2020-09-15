# User Guide

## Features 

### Adding a Task: todo, deadline or event
Adds a todo, deadline or event task to the list.

### Deleting a Task: delete
Deletes a task from the list.

### Completing a task: done
Changes status of task to 'done' when it is completed.

### Finding a keyword: find
Searches for tasks in the list that match the keyword.

### Prioritising tasks: prioritise
Changes the priority of the task to high, medium or low priority. 

### Exiting the program: bye
Saves the current tasks in the list and exits the program. 

## Usage

### `todo` - Adds a todo task

Adds a todo task to the list

Format: 
`todo TASK_NAME`

Example of usage: 

`todo zoom workout`

Expected outcome:

`Got it. I've added this task:`

`[T][✗][3] zoom workout`

`Now you have # tasks in the list`

### `deadline` - Adds a deadline task

Adds a deadline task to the list

Format: 
`deadline TASK_NAME /by YYYY-MM-DD`

Example of usage: 

`deadline cs assignment /by 2020-08-12`

Expected outcome:

`Got it. I've added this task:`

`[D][✗][3] cs assignment (by: Aug 12 2020)`

`Now you have # tasks in the list`

### `event` - Adds an event task

Adds an event task to the list

Format: 
`event TASK_NAME /at YYYY-MM-DD HH:MM`

Example of usage: 

`event concert /at 2020-10-11 19:00`

Expected outcome:

`Got it. I've added this task:`

`[E][✗][3] concert (at: Oct 11 2020, 19:00)`

`Now you have # tasks in the list`

### `delete` - Deletes a task

Deletes a task from the list

Format: 
`delete TASK_NUMBER`

Example of usage: 

`delete 1`

Expected outcome:

`Noted. I've removed this task:`

`[T][✗][3] zoom workout`

`Now you have # tasks in the list`

### `done` - Marks task as done

Marks a task as done when it is completed

Format: 
`done TASK_NUMBER`

Example of usage: 

`done 1`

Expected outcome:

`Nice! I've marked this task as done:`

`[T][✓][3] zoom workout`

### `priority` - Changes the priority of a task

1. `Indicates priority levels`
 2. `Changes the priority of the task to high (1), medium (2) or low (3)`

Format: 

1. `priority`
2. `priority TASK_NUMBER PRIORITY`


Example of usage: 

1. `priority`
2. `priority 1 2` 

Expected outcome:

1. `Priority levels:`

    `HIGH - 1`

    `MEDIUM - 2`

    `LOW - 3`


2. `Noted! I've changed the priority of this task to: MEDIUM`

    `[T][✓][2] zoom workout`

### `find` - Finds a task

Finds all the tasks that match the keyword

Format: 
`find KEYWORD`

Example of usage: 

`find workout`

Expected outcome:

`Here are the matching tasks in your list`

`1. [T][✓][3] zoom workout`

`2. [D][✗][1] workout with sister`

### `bye` - Exits the program

Saves the tasks in the list and exits the program

Format: 
`bye`


Expected outcome:

`bye bye!`



