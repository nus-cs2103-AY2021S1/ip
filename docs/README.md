# User Guide

## Features 

### CRUD Operations

Able to Create, Read, Update and Delete Tasks

## Usage

##### `todo [String]` - Add a todo task

Creates a new todo task

Example of usage: 

`todo borrow book`

Expected outcome:

```
Got it. I've added this task:
[T][X] read book
Now you have 1 tasks in the list.
```

##### `event [String] /at YYYY-MM-DD HHMM` - Add an event task

Creates a new event task on a particular date and time

Example of usage: 

`event borrow book /at 2019-04-18 1400`

Expected outcome:

```
Got it. I've added this task:
[E][X] borrow book (at: Apr 18 2019, 2.00pm)
Now you have 2 tasks in the list.
```

##### `deadline [String] /by YYYY-MM-DD HHMM` - Add a deadline task

Creates a new deadline task on a particular date and time

Example of usage: 

`deadline return book /at 2019-04-26 1800`

Expected outcome:

```
Got it. I've added this task:
[D][X] return book (at: Apr 26 2019, 6.00pm)
Now you have 3 tasks in the list.
```

##### `list` - Lists all tasks

Lists all the saved tasks

Example of usage: 

`list`

Expected outcome:

```
1.[T][X] read book
2.[E][X] borrow book (at: Apr 18 2019, 2.00pm)
3.[D][X] return book (at: Apr 26 2019, 6.00pm)
```

##### `done [Integer]` - Complete a task

Completes a task according to the task number provided

Example of usage: 

`done 1`

Expected outcome:

```
Nice! I've marked this task as done:
[T][✓] read book
```

##### `delete [Integer]` - Deletes a task

Deletes a task according to the task number provided

Example of usage: 

`delete 1`

Expected outcome:

```
Noted. I've removed this task:
[T][✓] read book
Now you have 2 tasks in the list.
```



