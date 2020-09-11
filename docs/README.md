# User Guide

## Description
Duke is an assistant that helps you track your daily tasks.

## Features 
These are the features offered by Duke:
1. Add a task
2. Delete a task
3. Marking a particular task as done upon completion
5. Display the entire list of tasks

## Usage

### `todo` - Add a todo task

Example of usage: 

`todo read book`

Expected outcome:

```
Got it, I've added this task:

[T][X] read book

Now you have have 1 tasks in the list.
```

### `deadline` - Add a deadline task

Example of usage: 

`deadline MA2104 assignment /by 2020-04-29`

Expected outcome:

```
Got it, I've added this task:

[D][X] MA2104 assignment (by: April 29 2020)

Now you have have 1 tasks in the list.
```

### `event` - Add an event task

Example of usage: 

`event wedding /at 2020-04-29`

Expected outcome:

```
Got it, I've added this task:

[E][X] wedding (at: April 29 2020)

Now you have have 1 tasks in the list.
```

### `after` - Add an after task

Example of usage: 

`after run /after nap`

Expected outcome:

```
Got it, I've added this task:

[A][X] run (after: nap)

Now you have have 1 tasks in the list.
```

### `list` - List all tasks

Example of usage: 

`list`

Expected outcome:

```
Here are the tasks in your list:
    1. [T][X] read book
    2. [D][X] MA2104 assignment (by: April 29 2020)
    3. [E][X] wedding (at: April 29 2020)
    4. [A][X] run (after: nap)
```

### `done` - Mark a task as done

Example of usage: 

`done 1`

Expected outcome:

```
Nice! I've marked this task as done:
    [T][✓] read book
    Now you have 3 tasks in the list.
```

### `delete` - Delete a task

Example of usage: 

`delete 1`

Expected outcome:

```
Noted. I've removed this task:
    [T][X] read book
    Now you have 3 tasks in the list.
```