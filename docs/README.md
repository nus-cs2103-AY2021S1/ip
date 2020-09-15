# User Guide

![Duke](Ui.png)

Welcome to Duke, the task manager application for command line interface (CLI) lovers!

## Features

### Manage Your Tasks

To best cater to your task management needs, Duke supports the creation of three different types of tasks:
- Events
- ToDos
- Deadlines

## Usage

### `alias` - Defines an alias for a command

Associates an alias with a command.

Example of usage: 

`alias bye b`

Expected outcome:

```
Alias 'b' successfully added for command 'bye'.
```

### `bye` - Terminates the program

Exits the program gracefully.

Example of usage:

`bye`

Expected outcome:

Program exits

### `deadline` - Adds a deadline task

Adds a new deadline task to the task manager.
A deadline task is a task with a description and a due date.

Example of usage:

`deadline User Guide /by 15/9/2020 2359`

Expected outcome:

```
Got it. I've added this task:
  [D][✘] User Guide (by: Sep 15 2020, 11:59 PM)
Now you have 4 tasks in the list.
```

### `delete` - Deletes a task

Deletes the task at the specified index from the task manager.

Example of usage:

`delete 3`

Expected outcome:

```
Noted. I've removed this task:
  [D][✘] User Guide (by: Sep 15 2020, 11:59 PM)
Now you have 3 tasks in the list.
```

### `done` - Marks a task as done

Marks the task at the specified index in the task manager as done.

Example of usage:

`done 3`

Expected outcome:

```
Nice! I've marked this task as done:
  [D][✓] User Guide (by: Sep 15 2020, 11:59 PM)
```

### `event` - Adds an event task

Adds a new event task to the task manager.
An event task is a task with a description and a date/time.

Example of usage:

`event CS2103T Lecture /at 18/9/2020 1600`

Expected outcome:

```
Got it. I've added this task:
  [E][✘] CS2103T Lecture (at: Sep 18 2020, 04:00 PM)
Now you have 4 tasks in the list.
```

### `find` - Finds tasks based on keywords

Searches for tasks in the task manager which match the provided keywords.

Example of usage:

`find 2103 guide`

Expected outcome:

```
Here are the matching tasks in your list:
1. [D][✓] User Guide (by: Sep 15 2020, 11:59 PM)
2. [E][✘] CS2103T Lecture (at: Sep 18 2020, 04:00 PM)
```

### `help` - Displays the list of commands

Displays the list of available commands along with a brief description.

Example of usage:

`help`

Expected outcome:

```
Commands:
- alias
Defines an alias for a command
- bye
Terminates the program
- deadline
Adds a deadline task to the task manager
- delete
Deletes a task from the task manager
- done
Marks a task as done
- event
Adds an event task to the task manager
- find
Searches for tasks in the task manager which match the provided keywords
- help
Displays a list of all commands along with a brief description
- list
Lists all tasks in the task manager
- overdue
Lists all overdue tasks in the task manager
- todo
Adds a todo task to the task manager
- upcoming
Lists all upcoming tasks in the task manager
```

### `language` - Sets the language of Duke

Sets the language which Duke will respond in.
Currently, the following languages are supported:
- English (en)
- Chinese (zh)

Example of usage:

`language zh`

Expected outcome:

```
语言切换为中文！
```

### `list` - Lists all tasks

Lists all tasks in the task manager.

Example of usage:

`list`

Expected outcome:

```
Here are the tasks in your list:
1. [D][✘] Add GUI to iP (by: Sep 1 2020, 11:59 PM)
2. [E][✓] Project meeting (at: Sep 5 2020, 10:00 PM)
3. [D][✓] User Guide (by: Sep 15 2020, 11:59 PM)
4. [E][✘] CS2103T Lecture (at: Sep 18 2020, 04:00 PM)
```

### `overdue` - Lists all overdue tasks

Lists all overdue tasks in the task manager.

Example of usage:

`overdue`

Expected outcome:

```
Here are your overdue tasks:
1. [D][✘] Add GUI to iP (by: Sep 1 2020, 11:59 PM)
```

### `todo` - Adds a todo task

Adds a new todo task to the task manager.
A todo task is a task with only a description.

Example of usage:

`todo CS2103T Quiz 6`

Expected outcome:

```
Got it. I've added this task:
  [T][✘] CS2103T Quiz 6
Now you have 5 tasks in the list.
```

### `upcoming` - Lists all upcoming tasks

Lists all upcoming tasks in the task manager.

Example of usage:

`upcoming`

Expected outcome:

```
Here are your upcoming tasks:
1. [E][✘] CS2103T Lecture (at: Sep 18 2020, 04:00 PM)
```
