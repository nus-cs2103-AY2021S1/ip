# User Guide

## Product Screenshot

![title](Ui.png)

## Features

### Add tasks

Add any task(s) to your todo list, whether it is a regular todo, an event with a time or a deadline. Helps you keep track of your pending work.

### Mark tasks as done

Mark any task as done, or finished. Updates the todo list with the task(s) you've finished!

### Remove tasks

Remove any task(s) from your todo list, in case you don't need to do them anymore.

### List all tasks

List all the current tasks (done or pending) for the user

### View all tasks on a single day

List all the current tasks (done or pending) _on a particular day_. Lets you view your daily schedule!

### Search for a task

Find a task based on a particular keyword. Lets you view related tasks.

### Add Notes

Add snippets of text that you need to remember.

### Remove Notes

Remove notes that you don't need anymore.

### Persistent Storage

Stores all of your past todos, events, deadlines or notes - so that they don't get wiped out after you use the application once.

## Usage

### `todo` - Add todo

This adds a regular todo task (denoted by "T") to the list, along with a description of the todo. Initially, the todo is marked as "not done." Can be aliased to `t`.
Example of usage:

`todo read book`

Expected outcome:

```
Got it. I've added this task:
    [T][X] read book.
 Now you have 1 task(s) in the list.
```

### `event` - Add event

This adds an event task (denoted by "E") to the list, along with a description of the event as well as an ISO date preceded by `/at`. Initially, the todo is marked as "not done." Can be aliased to `e`.
Example of usage:

`event Friday Hacks /at 2020-10-15`

Expected outcome:

```
Got it. I've added this task:
    [E][X] Friday Hacks.
 Now you have 2 task(s) in the list.
```

### `deadline` - Add deadline

This adds an deadline task (denoted by "D") to the list, along with a description of the deadline as well as an ISO date preceded by `/by`. Initially, the todo is marked as "not done." Can be aliased to `d`.
Example of usage:

`deadline return book /by 2020-10-18`

Expected outcome:

```
Got it. I've added this task:
    [D][X] return book
 Now you have 3 task(s) in the list.
```

### `done` - Mark task as done

This marks a pending task (denoted by its 1-based index) as done. Informs the user if a task has already been marked as done, or if the task index supplied does not exist.  
Example of usage:

`done 1`

Expected outcome:

```
Nice, I've marked this task as done:
    [T][✓] read book
```

### `delete` - Delete a task

This deletes a task(denoted by its 1-based index) as done. Informs the user if the task index supplied does not exist. Can be aliased to `-d`.
Example of usage: .

`delete 1`

Expected outcome:

```
The following task has been removed successfully
    [T][✓] read book
Now you have 2 task(s) in the list.
```

### `list` - List all tasks

Lists all tasks (along with their pending or done status) currently in the todo-list, itemized by a number (1-based). Can be aliased to `l`.
Example of usage:

`list`

Expected outcome:

```
[E][X] Friday Hacks (at: Oct 15 2020)
[D][X] return book  (at: Oct 17 2020)
```

### `view` - List all tasks on a particular day

Lists all tasks (along with their pending or done status) currently in the todo-list, if they are on a particular date supplied by the user. Can be aliased to `v`.
Example of usage:

`view 2020-15-2020`

Expected outcome:

```
Here are the tasks on this day in your list:
1. [E][X] Friday Hacks (at: Oct 15 2020)
```

### `find` - Find all tasks given keywords

Lists all tasks (along with their pending or done status) currently in the todo-list, if their descriptions match the keywords supplied. Can be aliased to `f`.
Example of usage:

`find book`

Expected outcome:

```
Here are the matching tasks in your list:
1. [T][✓] read book
2. [D][✓] return book (by: Oct 17 2020)
```

### `bye` - Shows exit message

Shows exit message to the user.

Example of usage:

`bye`

Expected outcome:

```
Bye. Hope to see you again soon!
```
