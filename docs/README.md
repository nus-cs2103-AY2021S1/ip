# User Guide for Tachikoma

- [User Guide for Tachikoma](#user-guide-for-tachikoma)
  * [Features](#features)
    + [Add tasks](#add-tasks)
    + [List all tasks](#list-all-tasks)
    + [Mark as done](#mark-as-done)
    + [Delete tasks](#delete-tasks)
    + [Undo previous commands](#undo-previous-commands)
    + [Save data permanently](#save-data-permanently)
  * [Usage](#usage)
    + [`list` - List all tasks](#-list----list-all-tasks)
    + [`todo` - Add a Todo task](#-todo----add-a-todo-task)
    + [`deadline` - Add a Deadline task](#-deadline----add-a-deadline-task)
    + [`event` - Add a Event task](#-event----add-a-event-task)
    + [`done` - Mark a task as Done](#-done----mark-a-task-as-done)
    + [`delete` - Delete a task](#-delete----delete-a-task)
    + [`find` - Find tasks using keywords](#-find----find-tasks-using-keywords)
    + [`undo` - Undo previous operations](#-undo----undo-previous-operations)
    + [`bye` - Quit the app](#-bye----quit-the-app)
    
## Features 

### Add tasks
Tachikoma can add three types of tasks with their description to your task list:
* `Todo`: a general type of task without an associated date
* `Deadline`: tasks with duedates
* `Event`: tasks with dates specifying when the events take place.

### List all tasks

Tachikoma can show your task list, which contains the tasks you have added before.

### Mark as done

Tachikoma can mark a task as done, after which the task will be displayed with a checkmark in front of its description.

### Delete tasks

Tachikoma can delete a task from the task list if you feel that it is no longer necessary to have it on the list.

### Undo previous commands

In case you accidentally delete an important task, Tachikoma get you covered! You can use undo command to: 

* recover tasks that you have accidentally deleted
* uncheck the task that you have just marked as done
* remove the newly added task if you realized that the information of it is incorrect.

### Save data permanently

Tachikoma will automatically store the task list to a local txt file after each operations. Therefore, next time you open Tachikoma, all tasks that you have added previously will be reloaded from the disk.

## Usage

### `list` - List all tasks

Show all the tasks you have previously entered with their:

1. unique index
2. type (`Todo`, `Deadline`, `Event`)
3. status (checked or not), 
4. description
5. time (if the task is an deadline or event)

Example of usage: 

`list`

Expected outcome:

```
Here are the tasks in your list:
 1. [D][✓] CS3230 Assignment (by: Sep 19 2020)
 2. [E][✘] tP meeting (at: Sep 18 2020)
 3. [T][✘] wash clothes
```

### `todo` - Add a Todo task

Add a new todo task with the given description to the task list.

Format:

`todo [description]`

Example of usage: 

`todo wash clothes`

Expected outcome:

```
Got it. I've added this task:
	[T][✘] wash clothes
Now you have 4 tasks in the list.
```

### `deadline` - Add a Deadline task

Add a new deadline task with the given description and duedate to the task list.

Format:

`deadline [description] /by [YYYY-MM-dd]`

Example of usage: 

`deadline CS3230 Assignment /by 2020-09-19`

Expected outcome:

```
Got it. I've added this task:
    [D][✘] CS3230 Assignment (by: Sep 19 2020)
Now you have 4 tasks in the list.
```

### `event` - Add a Event task

Add a new event task with the given description and date to the task list.

Format:

`event [description] /at [YYYY-MM-dd]`

Example of usage: 

`event tP meeting /at 2020-09-18`

Expected outcome:

```
Got it. I've added this task:
    [E][✘] tP meeting (at: Sep 18 2020)
Now you have 4 tasks in the list.
```

### `done` - Mark a task as Done

Mark the task with the given index as done.

Format:

`done [index]`  (note that the `index` must lies between 1 and n (inclusive), where n is the total number of tasks)

Example of usage: 

`done 3`

Expected outcome:

```
Nice! I've marked this task as done:
    [T][✓] wash clothes
```

### `delete` - Delete a task

Remove the task with the given index from the task list

Format:

`delete [index]` (note that the `index` must lies between 1 and n (inclusive), where n is the total number of tasks)

Example of usage: 

`delete 3`

Expected outcome:

```
Noted. I've removed this task:
    [T][✓] wash clothes
Now you have 2 tasks in the list.
```

### `find` - Find tasks using keywords

Find all tasks whose descriptions contains the given keywords

Format:

`find [keywords]`

Example of usage: 

`find meeting`

Expected outcome:

```
Here are the matching tasks in your list:
 1. [E][✘] tP meeting (at: Sep 18 2020)
 2. [T][✘] create zoom link for tP meeting
```

### `undo` - Undo previous operations

Undo previous commands, specifically:

* re-add tasks that you have just deleted
* uncheck the task that you have just marked as done
* remove the newly added task.

Example of usage: 

`undo`

Expected outcome 1:

```
OK! I've re-added the task you just deleted:
    [T][✓] create zoom link for tP meeting
Now you have 3 tasks in the list.
```

Expected outcome 2:

```
OK! I've unchecked the task:
    [T][✘] create zoom link for tP meeting
```

Expected outcome 3:

```
OK! I've removed the task you just added now:
    [T][✘] create zoom link for tP meeting
Now you have 2 tasks in the list.
```

### `bye` - Quit the app

Exit the app.

Example of usage: 

`bye`

Expected outcome:

`Bye. Hope to see you again soon!`

