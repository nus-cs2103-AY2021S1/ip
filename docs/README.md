# User Guide of C-3PO
C-3PO is a droid for human cyborg relations.

<img src="Ui.png" alt="Screenshot" width="250">

- [User Guide of C-3PO](#user-guide-of-c-3po)
  * [Features](#features)
    + [Add tasks - todo, events and deadlines](#add-tasks---todo--events-and-deadlines)
    + [Date processing](#date-processing)
    + [Mark tasks as done](#mark-tasks-as-done)
    + [List all tasks](#list-all-tasks)
    + [Delete tasks](#delete-tasks)
    + [Sort tasks by different properties](#sort-tasks-by-different-properties)
    + [Save data into local storage](#save-data-into-local-storage)
  * [Usage](#usage)
    + [`list` - list all tasks](#-list----list-all-tasks)
    + [`todo` - add a todo task](#-todo----add-a-todo-task)
    + [`deadline` - add a deadline task](#-deadline----add-a-deadline-task)
    + [`event` - add an event task](#-event----add-an-event-task)
    + [`done` - mark a task as done](#-done----mark-a-task-as-done)
    + [`delete` - delete a task](#-delete----delete-a-task)
    + [`find` - find tasks by keywords](#-find----find-tasks-by-keywords)
    + [`sort` - sort tasks by kind, name or date](#-sort----sort-tasks-by-kind--name-or-date)
    + [`bye` - save and exit](#-bye----save-and-exit)

## Features 

### Add tasks - todo, events and deadlines
C-3PO can add different types of tasks to your task list, including:
* `Todo` tasks that have a description for the task;
* `Event` tasks that have a description, and a date when the events happen.
* `Deadline` tasks that have a description and a due date.

### Date processing
C-3PO can understand date representation of the format `YYYY-MM-DD` and 
compare multiple dates based on this feature. For date and time which cannot
be understood by C-3PO, a raw string is used to store the date and time.

### Mark tasks as done
C-3PO can mark tasks as done and display the task status in a list.

### List all tasks
C-3PO is able to list all existing tasks.

### Delete tasks
C-3PO can delete existing tasks from the task list.

### Sort tasks by different properties
C-3PO can sort tasks by:
* Name: in alphabetical order, from A to Z;
* Kind: `Deadline` comes before `Event` before `Todo`;
* Date: later understandable dates come before earlier dates, followed by string
dates (`Deadline` before `Event`), and finally followed by `Todo` tasks.

### Save data into local storage
C-3PO will store the data into local storage automatically after each
operation. Every time when C-3PO starts, it automatically reads the local
storage and loads previous data from it.

## Usage

### `list` - list all tasks

`list` command can list all the added tasks stored in the local database.

```
list
```

Alias for `list`: `ls`.

### `todo` - add a todo task

`todo` command can add a new todo task to the task list.

```
todo DESCRIPTION
```

Alias for `todo`: `td`, `t`.

### `deadline` - add a deadline task

`deadline` command add a new deadline task to the task list.

```
deadline DESCRIPTION /by DATE
```

Alias for `deadline`: `ddl`, `d`.

`DATE` can be a string or in the format of `YYYY-MM-DD`. For the latter format, C-3PO will be able to understand the due date, while for the former format, C-3PO will directly save the date as a raw string.

### `event` - add an event task

`event` command add a new deadline task to the task list.

```
event DESCRIPTION /at DATE
```

Alias for `event`: `e`.

`DATE` can be a string or in the format of `YYYY-MM-DD`. For the latter format, C-3PO will be able to understand the date, while for the former format, C-3PO will directly save the date as a raw string.

### `done` - mark a task as done

`done` command marks a task as done.

```
done INDEX
```

Alias for `done`: `finish`.

For a non-empty task list with `n` tasks, ` INDEX` must be in the range of 1 to `n` (inclusive).

### `delete` - delete a task

`delete` command deletes a task from the list.

```
delete INDEX
```

Alias for `delete`: `dl`, `rm`.

For a non-empty task list with `n` tasks, ` INDEX` must be in the range of 1 to `n` (inclusive).

### `find` - find tasks by keywords

`find` command searches for tasks with a specified keyword and prints a list of all searched tasks.

```
find KEYWORDS
```

Alias for `find`: `f`, `search`, `s`.

### `sort` - sort tasks by kind, name or date

`sort` command sorts the task list by their kinds, names or dates.

Alias for `sort`: `s`.

```
sort /by COMPARATOR
```

`COMPARATOR` can only be `date`, `name` or `kind`.

### `bye` - save and exit

`bye` command shuts down the chat bot and exits in two seconds.

```
bye
```

Alias for `bye`: `exit`.


