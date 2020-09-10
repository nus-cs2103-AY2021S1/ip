# User Guide

## Features 

### Add task (e.g. Todo, deadline, event)

Asks the chat bot to add a new task to the list 
and save it to disk.
You are expected to specify a date for deadlines 
and events.

### List task
Asks the chat bot to list out your tasks.

### Delete task
Instructs the chat bot to delete the task which 
you do not want or have completed.

### Mark complete
Instructs the chat bot to mark a task as done.

### Find by date
Asks the chat bot to return you all tasks on 
a given date, to help you quickly find out what 
needs to be done on a particular day.

### Find by keyword
Asks the chat bot to return you a list of tasks
that contains certain keywords.

### Sort tasks
Asks the chat bot to return you a list of tasks
sorted by earliest or latest date.


## Usage

### `list` - Show All Tasks

Describe action and its outcome.

Example of usage: 

`list`

Expected outcome:

```
1. [T][✔] Read book
```

### `todo <description>` - Adds a Todo

Describe action and its outcome.

Example of usage: 

`todo Walk the dog`

Expected outcome:

```
outcome
```

### `deadline <description> /by <date>` - Adds a Deadline

Describe action and its outcome.

Example of usage: 

`deadline Submit assignment /by 2020-09-10`

Expected outcome:

```
outcome
```

### `event <description> /at <date>` - Adds an Event

Describe action and its outcome.

Example of usage: 

`event Attend sister's wedding /at 2020-10-10 `

Expected outcome:

```
outcome
```

### `done <index>` - Mark Complete

Describe action and its outcome.

Example of usage: 

`done 1`

Expected outcome:

```
outcome
```

### `delete <index>` - Delete a Task

Describe action and its outcome.

Example of usage: 

`delete 1`

Expected outcome:

```
outcome
```

### `date <date>` - Find by Date

Describe action and its outcome.

Example of usage: 

`date 2020-10-10`

Expected outcome:

```
outcome
```

### `find <keyword>` - Find by Keyword

Describe action and its outcome.

Example of usage: 

`find Submit`

Expected outcome:

```
1. [D][✗] Submit assignment (by: Sep 10 2020)
```

### `sort /by <descriptor>` - Sort tasks

Allows you to sort tasks by latest or earliest date, and returns you
a list of tasks in that order.

Example of usage: 

`sort /by latest`

Expected outcome:

```
outcome
```

### `bye` - Exit

Describe action and its outcome.

Example of usage: 

`delete 1`

Expected outcome:

```
outcome
```
