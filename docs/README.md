# Duke User Guide

## Features 

### Create
Create 3 different types of tasks: Todos, Events, and Deadlines.

### View
See a list of your tasks, sorted by name or date.

### Manage
Check off tasks when you are done, or even delete them from the list.


## Usage

### `todo` - Create a Todo

Example of usage: 

`todo read book`

Expected outcome:

```
Got it. I've added this task:
[T][N] read book
Now you have 1 task in the list.
```

### `deadline` - Create a Deadline

Example of usage: 

`deadline return book /by 2020-10-15`

Expected outcome:

```
Got it. I've added this task:
[D][N] return book (by: Oct 15 2020)
Now you have 2 tasks in the list.
```

### `event` - Create an Event

Example of usage: 

`event project meeting /at 2020-11-11`

Expected outcome:

```
Got it. I've added this task:
[E][N] project meeting (at: Nov 11 2020)
Now you have 3 tasks in the list.
```

### `list` - List out Tasks

Example of usage: 

`list`

Expected outcome:

```
Here are the tasks in your list:
1.[T][N] read book
2.[D][N] return book (by: Oct 15 2020)
3.[E][N] project meeting (at: Nov 11 2020)
```

### `done` - Mark Task as Done

Example of usage: 

`done 1`

Expected outcome:

```
Nice! I've marked this task as done:
[T][Y] read book
```

### `delete` - Deletes task

Example of usage: 

`delete 1`

Expected outcome:

```
Noted. I've removed this task: 
[T][Y] read book
Now you have 2 tasks in the list.
```

### `bye` - Close Duke

Example of usage: 

`bye`

Expected outcome:

```
bye
```