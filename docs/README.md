# User Guide

## Features 

### Create todo, deadline and event tasks
There are 3 types of tasks that you can create: Todo, Deadline and Event tasks. Todo tasks are the simplest, with only a specified name. Deadline and Event tasks are specified with a date. You can create todo, deadline and event tasks to be tracked by Duke.

### Do todo, deadline and event tasks
You can mark a todo, deadline and event task as done.

### Delete todo, deadline and event tasks
You can delete todo, deadline and event tasks.

## Usage

### `todo` - Creates a todo task.

When a todo task is created, it is automatically saved to disk.

Example of usage: 

`todo homework1`

Expected outcome:

```
Got it. I've added this task:
    [T][✘] homework1
Now you have 1 task in the list. 
```

### `deadline` - Creates a deadline task.

Like todo tasks, when a deadline task is created, it is automatically saved to disk. Deadline tasks are specified with a date to complete it by.

Example of usage: 

`deadline homework1 /by 2020-09-23`

Expected outcome:

```
Got it. I've added this task:
    [D][✘] homework1 (by: Sep 23 2020)
Now you have 1 task in the list. 
```

### `event` - Creates a event task.

Like todo tasks, when a event task is created, it is automatically saved to disk. Event tasks are specified with a date to complete it at.

Example of usage: 

`event shopping /at 2020-09-24`

Expected outcome:

```
Got it. I've added this task:
    [E][✘] homework1 (at: Sep 24 2020)
Now you have 1 task in the list. 
```

### `done` - Completes a task.

When you are done with a task, you can mark the task in your list as done, by specifying the index of the task.

Example of usage: 

`done 1`

Expected outcome:

```
Nice! I've marked this task as done:
    [T][✓] homework1 
```

### `delete` - Deletes a task.

You can delete a task from your list of tasks, by specifying the index of the task.

Example of usage: 

`delete 1`

Expected outcome:

```
Noted. I have removed this task:
    [T][✓] homework1 
```

### `find` - Searches for a tasks.

Search for a task according to text specified matching the task name.

Example of usage: 

`find work`

Expected outcome:

```
Here are the matching tasks in your list:
    [T][✓] homework1 
```

### `tag` - Tag a task with a specified text.

You may opt to tag a task with a specified text to keep track of common topics or additional details.

Example of usage: 

`tag 1 urgent`

Expected outcome:

```
I've tagged this task with #urgent:
    [T][✓] homework1 #urgent
```

### `untag` - Untag a task with a specified text.

You may opt to untag a task with a specified text once it is no longer relevant.

Example of usage: 

`untag 1 urgent`

Expected outcome:

```
I've removed the tag from this task: #urgent:
    [T][✓] homework1
```

### `bye` - Exit the app.

Closes the app.

Example of usage: 

`bye`

Expected outcome:

```
Bye! Hope to see you again soon!
```