# Duck User Guide

Duck is a _Fall Guys_ themed Task Management Application
that keeps track of the things that you need to do.
It supports attaching dates to tasks, so you can keep
track of deadlines or events and even sort them by date
to keep your priorities straight!

![Duck Screenshot](./Ui.png)

## Features

Duck supports the following features:

1. [Creating a task](#Creating-a-task)

   - [Creating Todos](#Todos)

   - [Creating Deadlines](#Deadlines)

   - [Creating Events](#Events)

1. [Viewing your tasks](#Viewing-your-tasks)

1. [Finding a task](#Finding-a-task)

1. [Sorting tasks by date](#Sorting-tasks-by-date)

1. [Completing a task](#Completing-a-task)

1. [Deleting a task](#Deleting-a-task)

1. [Exiting the program](#Exiting-the-program)

### Creating a task

Tasks in Duck are categorized into three types,
**Todos**, **Deadlines** and **Events**. All tasks take in
a description and can be marked as completed.

### Todos

### `todo DESCRIPTION` - Creates a todo task with the description

A Todo is a simple task that allows you to provide
a description of a task.

`DESCRIPTION` - String

Example of usage:

`todo read a book`

Expected outcome:

Todo is created

### Deadlines

### `deadline DESCRIPTION /by DATE` - Creates a deadline task with the description and date specified

A Deadline is another variation of a task that allows you to provide
an additional date field which represents the date this task must be
completed by.

`DESCRIPTION` - String
`DATE` - String in format "YYYY-MM-DD"

Example of usage:

`deadline return book /by 2020-09-09`

Expected outcome:

Deadline is created with a date field

### Events

### `event DESCRIPTION /at DATE` - Creates an event task with the description and date specified

An Event is another variation of a task that allows you to provide
an additional date field which represents the date this task must be
completed by. The distinction with Deadline is only semantic and can be
used interchangeably.

`DESCRIPTION` - String
`DATE` - String in format "YYYY-MM-DD"

Example of usage:

`event team meeting /at 2020-09-09`

Expected outcome:

Event is created with a date field

### Viewing your tasks

### `list` - Lists all created tasks so far

This command allows you to view the statuses of all your created
tasks so far. This also provides the indexing of your tasks required
for other commands like `delete` and `done`.

Example of usage:

`list`

Expected outcome:

Tasks are listed with their index, "done" status, description and
date if present.

### Finding a task

### `find SUBSTRING` - Lists tasks that contain SUBSTRING

This command allows you to filter your tasks based on the substring provided.

SUBSTRING - Tasks containing this substring will be returned

Example of usage:

`find book`

Expected outcome:

Tasks with the substring `book` in their description will be returned.

### Sorting tasks by date

### `due [/by | /at DATE]` - Sorts tasks by their upcoming date

This command allows you to view your tasks in the order of their dates. Tasks
without dates like Todos will not be shown.

[DATE] - Optional field if provided, will not return tasks equal to or greater
than this date.

Example of usage:

`due`

Expected outcome:

Returns tasks in ascending dates

`due /by 2020-09-19`

Expected outcome:

Returns tasks in ascending dates up until 19 September 2020

### Completing a task

### `done INDEX` - Mark the task at INDEX as done

The tasks specified by INDEX will be marked as done and a checkmark
will appear next to the task.

INDEX - Index of the task as obtained by `list`

Example of usage:

`done 1`

Expected outcome:

Tasks with the index `1` as shown in `list` will be marked as done

### Deleting a task

### `delete INDEX` - Deletes the task at INDEX

The tasks specified by INDEX will be deleted.

INDEX - Index of the task as obtained by `list`

Example of usage:

`delete 1`

Expected outcome:

Tasks with the index `1` as shown in `list` will be deleted

### Exiting the program

### `bye` - Gracefully exits the program

The program will exit and conduct a final save of the tasks. Tasks
will also be automatically saved on change and this is merely a
precaution.

Example of usage:

`bye`

Expected outcome:

Program exits gracefully.
