# Lana del Taco User Guide

## Application UI

![Image of LanaDelTaco](https://gabztcr.github.io/ip/Ui.png)

## Features 

### Add new task
Add new ToDo, Deadline or Event for Lana to track.

### View tasks
See all the tasks that are currently tracked by Lana.

### Edit the completion status of task(s)
Mark task(s) as done once you've completed them.

### Delete task(s)
Delete task(s) you no longer want to track.

### Find task(s)
Find all tracked task(s) matching your keyword.

## Usage

### `todo DESCRIPTION` - Add ToDo

Adds a new ToDo task with a description.

Example of usage: 

`todo write sad poetry`

Expected outcome:

`Nice! I've added the new task for you! Now you have 1 task in the list.`

### `deadline DESCRIPTION /by YYYY-MM-DD HH:MM` - Add Deadline

Adds a new Deadline task with a description and deadline.

Example of usage: 

`deadline write love song /by 2020-12-24 23:59`

Expected outcome:

`Nice! I've added the new task for you! Now you have 2 tasks in the list.`

### `event DESCRIPTION /at YYYY-MM-DD HH:MM to YYYY-MM-DD HH:MM` - Add Event

Adds a new Event task with a description and event duration.

Example of usage: 

`event attend Golden Globes /at 2021-01-24 19:00 to 2021-01-24 22:30`

Expected outcome:

`Nice! I've added the new task for you! Now you have 3 tasks in the list.`

### `list` - List all tasks

Lists all tasks to view them.

Example of usage: 

`list`

Expected outcome:

```
1. [T][✗] write sad poetry
2. [D][✗] write love song (by: Thu, 24 Dec 2020, 23:59)
3. [E][✗] attend Golden Globes (at: Sun, 24 Jan 2021, 19:00 to Sun, 24 Jan 2021, 22:30)
```

### `done TASKNUMBER(s)` - Mark task(s) as done

Marks the task(s) done and changes the according symbol(s) from a cross to a tick.

Example of usage: 

`done 1,3`

Expected outcome:

`Nice! I've marked done the task(s) for you!`


### `delete TASKNUMBER(s)` - Delete task(s)

Deletes the task(s) Lana does not have to track anymore.

Example of usage: 

`delete 3`

Expected outcome:

`Nice! I've deleted the task(s) for you! Now you have 2 tasks in the list.`

### `find KEYWORD` - Find task(s)

Find task(s) based on keyword.

Example of usage: 

`find poetry`

Expected outcome:

`1. [T][✓] write sad poetry`




