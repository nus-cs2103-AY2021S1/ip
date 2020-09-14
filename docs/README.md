# User Guide of C-3PO
C-3PO is a droid for human cyborg relations.

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

Describe action and its outcome.

Example of usage: 

`keyword (optional arguments)`

Expected outcome:

`outcome`

### `todo` - add a todo task

### `deadline` - add a deadline task

### `event` - add an event task

### `done` - mark a task as done

### `delete` - delete a task

### `find` - find tasks by keywords

### `sort` - sort tasks by kind, name or date

### `bye` - save and exit
