# User Guide

## Features 

#### List tasks
Lists all tasks the user has entered

#### Add tasks
Add a new todo, deadline, or event

#### Delete tasks
Deletes an existing task

#### Complete tasks
Mark an existing task as completed

#### Find tasks
Search tasks by their message or date 

## Usage

### `todo`

Adds a todo task

Example of usage: 

`todo Sleep`

Expected outcome:

```
Got it. I've added this task:
[T][✗] Sleep
Now you have 2 tasks in the list.
```

### `event`

Adds an event task

Example of usage: 

`event Birthday /at 2021-04-23`

Expected outcome:

```
Got it. I've added this task:
[E][✓] Birthday (at: Apr 23 2021)
Now you have 3 tasks in the list.
```

### `deadline`

Adds a deadline task

Example of usage: 

`deadline Assignment /at 2020-09-23 18:00`

Expected outcome:

```
Got it. I've added this task:
[D][✗] Assignment (by: Sep 23 2020 18:00)
Now you have 4 tasks in the list.
```

### `list`

Lists all tasks

Example of usage:
 
 `list`

Expected outcome:

```
1. [T][✗] Sleep
2. [E][✗] Birthday (at: Apr 23 2021)
3. [D][✗] Assignment (by: Sep 23 2020 18:00)
```

### `find`

Find tasks by name or date

Example of usage:
 
`find i`

Expected outcome:

```
1. [E][✗] Birthday (at: Apr 23 2021)
2. [D][✗] Assignment (by: Sep 23 2020 18:00)
```

### `done`

Marks a task as completed

Example of usage: 

`done 1`

Expected outcome:

```
Nice! I've marked this task as done:
[T][✓] Sleep
```

### `delete`

Marks a task as completed

Example of usage: 

`delete 3`

Expected outcome:

```
Noted. I've removed this task:
[D][✗] Assignment (by: Sep 23 2020 18:00)
```