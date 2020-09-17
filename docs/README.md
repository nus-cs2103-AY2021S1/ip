# User Guide

## Features 

### Task Management
Duke provides a wide range of operations to manage tasks, including the addition, deletion, marking, searching, 
archiving and viewing your task list. History data storage is supported, so tasks are accessible whenever Duke is
invoked. Exploiting task features of Duke may help organize your numerous tasks.

### CLI with GUI
Most interactions with Duke are conducted via command line instructions listed below, which can guarantee a high-level
efficiency once you are familiar with it. A graphic user interface is included for better users' readability of 
commands and responses.

## Usage

### `todo` - create a todo

Creates a new task of type "todo" in the task list.

Example of usage: 

`todo Learn Java`

Expected outcome:

```
Got it. I've added the task:
[T][✗] Learn Java
Now you have 1 tasks in the list.
```

### `deadline` | `event` - create a deadline or an event

Creates a new task of type "deadline" or "event" in the task list with an attached time. The time attached in 
`deadline` and `event` commands should be indicated following `/by` and `/at` keyword respectively. If the time 
satisfies format `yyyy-MM-dd` then it will be converted into format `Month dd yyyy`.

Example of usage: 

`event Group meeting /at Monday`

`deadline Return book /by 2020-12-31`

Expected outcome:
```
Got it. I've added the task:
[E][✗] Group meeting (at: Monday)
Now you have 2 tasks in the list.

Got it. I've added the task:
[D][✗] Return book (by: DECEMBER 31 2020)
Now you have 3 tasks in the list.
```

### `list` - view all tasks

Lists out all tasks that are added in the task list.

Example of usage: 

`list`

Expected outcome:

```
1. [T][✗] Learn Java 
2. [E][✗] Group meeting (at: Monday) 
3. [D][✗] Return book (by: DECEMBER 31 2020)
```

### `done` - mark a task as done

Marks a task from the list as "done" using its index from the `list` command.

Example of usage: 

`done 2`

Expected outcome:

```
Well done! I've marked this as done:
[E][✓] Group meeting (at: Monday)
```

### `delete` - delete a task

Deletes a task from the list using its index from the `list` command.

Example of usage: 

`delete 3`

Expected outcome:

```
Noted! I've removed this task:
[D][✗] Return book (by: DECEMBER 31 2020)
Now you have 2 tasks in the list.
```

### `find` - find tasks with keyword

Finds all tasks that contain a given keyword. The keyword may appear in the name, type and time of a task. 

Example of usage: 

`find t`

Expected outcome:

```
Here are the matching tasks in your list:
1. [T][✗] Learn Java 
2. [E][✓] Group meeting (at: Monday) 
```

### `archive` - archive a task

Archives a task from the list using its index from the `list` command. The archived tasks are not accessible from the 
task list, but still are visible via the `list archived` command.

Example of usage: 

`archive 1`

Expected outcome:

```
Noted! I've archived this task:
[T][✗] Learn Java
You can always use <list archived> command to show archived tasks :)
```

### `list archived` - view all archived tasks

Lists out all tasks that have been archived.

Example of usage: 

`list archived`

Expected outcome:

`1. [T][✗] Learn Java`

### error messages - respond to unexpected results

Invalid commands or parameters and unexpected exceptions (such as file accessing) may cause Duke to 
display an error message to the user.

Tips: please be reminded that commands are case-sensitive.

Example of error-arsing commands: 

`delete 9999` (index out of bound)

`deadline TEST /by` (empty time parameter)

`ARCHIVE 1` (wrong spelling of command)

Expected outcome:

`Sorry, I do not know what that means :(` (invalid commands)

`Oops! Something went wrong :(` (failed file accessing)