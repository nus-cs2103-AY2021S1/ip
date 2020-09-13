# User Guide

## Features 

### Task
Allows you to create different types of tasks. 

#### Todo
Allows you to create a todo.

#### Deadline
Allows you to create a deadline and specify the date and time.

#### Event
Allows you to create an event amd specify the date and time.

### List
Allows you to list all your tasks in your list.

### Done
Allows you to mark a task as complete.

### Delete
Allows you to delete a task.

### Find
Allows you to search for tasks.

## Usage

### `todo <content> /p <priority>` - Creates a new todo.

Creates a new uncompleted todo with `contents` as the content and `priority` as the priority.

Example of usage: 

`todo finish assignment 1 /p 0`

Expected outcome:

```
Got it. I've added this task:
[T][X](!) finish assignment 1
Now you have 1 task(s) in the list.
```

### `deadline <content> /by <yyyy-MM-dd HH:mm> /p <priority>` - Creates a new todo.

Creates a new uncompleted deadline with `contents` as the content, `yyyy-MM-dd HH:mm` as the due date and `priority` as the priority.

Example of usage: 

`deadline pay for aws bills /by 2020-09-09 12:45 /p 2`

Expected outcome:

```
Got it. I've added this task:
[D][X](!!!) pay for my aws bills (by: 2020-09-09 12:45)
Now you have 2 task(s) in the list.
```

### `event <content> /at <yyyy-MM-dd HH:mm> /p <priority>` - Creates a new event.

Creates a new uncompleted event with `contents` as the content, `yyyy-MM-dd HH:mm` as the event date and `priority` as the priority.

Example of usage: 

`event macritchie run /at 2020-09-10 16:45 /p 1`

Expected outcome:

```
Got it. I've added this task:
[E][X](!!) macritchie run (at: 2020-09-10 16:45)
Now you have 3 task(s) in the list.
```

### `list` - Shows all tasks.

Creates a new uncompleted event with `contents` as the content, `yyyy-MM-dd HH:mm` as the event date and `priority` as the priority.

Example of usage: 

`list`

Expected outcome:

```
Here are the tasks in your list:
1. [T][X](!) finish assignment 1
2. [D][X](!!!) pay for aws bills (by: 2020-09-09 12:45)
3. [E][X](!!) macritchie run (at: 2020-09-10 16:45)
You have 3 task(s) in your list.
```

### `done <taskNumber>` - Completes a specified task.

Completes a task identified by `taskNumber`.

Example of usage: 

`done 1`

Expected outcome:

```
Nice! I've marked this task as done:
[T][Y](!) finish assignment 1
```

### `delete <taskNumber>` - Deletes a specified task.

Deletes a task identified by `taskNumber`.

Example of usage: 

`delete 1`

Expected outcome:

```
Noted. I've removed this task:
[T][Y](!) finish assignment 1
Now you have 2 task(s) in the list
```

### `find <query>` - Finds tasks that matches the query.

Searches for the `query` in each task content and displays all tasks that matches the `query` (Case-sensitive).

Example of usage: 

`find pay`

Expected outcome:

```
Here are the matching tasks in your list:
1. [D][X](!!!) pay for aws bills (by: 2020-09-09 12:45)
```