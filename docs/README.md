# User Guide

## Features

### Adding a Todo 
Adds a todo to the list of tasks.

### Adding a Deadline
Adds a deadline task to the list of tasks.

### Adding an Event
Adds an event task to the list of tasks.

### Viewing all Tasks
Shows entire list of tasks.

### Searching for particular tasks with a keyword
Shows only tasks which contain the keyword entered.

### Marking a task as done
Marks the specified task as done.

### Deleting a task
Deleted the specifed task.

### Detects duplicate tasks
Detects duplicate tasks being added.

### Exiting the app
Exits with a goodbye message.

## Usage

Notes about the command format:
* Words in UPPER_CASE are the parameters to be supplied by the user.
e.g. in todo TASK, TASK is a parameter which can be used as todo homework

### `todo TASK` - Adding a todo

This command adds a todo with the description specified in TASK.

Example of usage: 

`todo homework`

Expected outcome:

```
Got it. I've added this task:
[T][✘] homework
Now you have TOTAL_TASKS tasks in the list.
```

### `deadline TASK /by DATE` - Adding a deadline

This command adds a deadline with the description specified in TASK and the date specified in DATE.
The date must be specified in the format YYYY-MM-DD.

Example of usage: 

`deadline bake bread /by 2020-10-14`

Expected outcome:

```
Got it. I've added this task:
[D][✘] bake bread (by: Oct 14 2020)
Now you have TOTAL_TASKS tasks in the list.
```

### `event TASK /at DATE` - Adding an event

This command adds an event with the description specified in TASK and the date specified in DATE.
The date must be specified in the format YYYY-MM-DD.

Example of usage: 

`event hiking trip /at 2020-11-15`

Expected outcome:

```
Got it. I've added this task:
[E][✘] hiking trip (at: Nov 15 2020)
Now you have TOTAL_TASKS tasks in the list.
```

### `list` - Viewing all tasks

This command lists all added tasks.

Example of usage: 

`list`

Expected outcome:

```
Here are the tasks in your list:
1. [T][✘] homework
2. [D][✘] bake bread (by: Oct 14 2020)
3. [E][✘] hiking trip (at: Nov 15 2020)
```

### `find KEYWORD` - Viewing particular tasks containing a keyword

This command lists only tasks containing a keyword.

Example of usage: 

`find bread`

Expected outcome:

```
Here are the matching tasks in your list:
1. [D][✘] bake bread (by: Oct 14 2020)
```
### `done TASK_NUMBER` - Marking a particular task as done

This command marks a particular task as done.

Example of usage: 

`done 3`

Expected outcome:

```
Nice! I've marked this task as done:
[E][✓] hiking trip (at: Nov 15 2020)
Those who are crazy enough to think that they can change the world are the ones who usually do. Dream big!

```

### `delete TASK_NUMBER` - Deletes a particular task

This command deletes a particular task.

Example of usage: 

`delete 1`

Expected outcome:

```
Noted. I've removed this task:
[T][✘] homework
Now you have TOTAL_TASKS tasks in the list.
```

### Detecting a duplicate task

Duplicate tasks cannot be added.

Example of usage: 

`deadline bake bread /by 2020-10-14`

Expected outcome:

```
This task has already been added to your list!
```

### `bye` - Exiting the program

This command saves all updates to the task list and then exits the program.

Example of usage: 

`bye`
