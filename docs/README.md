# TODO-ke: User Guide

TODO-ke is a simple task management application with a 
command line interface (CLI) and graphical user interface
(GUI).

## Features 
### 1. Adding a Task
#### 1.1 Adding a Todo
Adds a Todo to the task list. 
A Todo is a task with only a description and no date.

**Format**: `todo <description>`

**Examples**: 
- `todo assignment for CS2103`

#### 1.2 Adding a Deadline
Adds a Deadline to the task list. A Deadline is a task
containing a description and a date by which it is meant to be completed.

**Format**: `deadline <description> \by <date in YYYY-MM-DD format>`

**Examples**: 
- `deadline assignment for CS2103 \by 2020-09-19`
- `deadline group project \by 2020-11-01`

#### 1.3 Adding an Event
Adds an Event to the task list. An Event is a task
containing a description and a date at which it is scheduled to occur.

**Format**: `event <description> \at <date in YYYY-MM-DD format>`

**Examples**: 
- `event birth \at 2000-09-23`
- `event moon landing \at 1969-07-20`

### 2. Viewing all Tasks
Displays all tasks currently in the task list.

**Format**: `list`

### 3. Deleting a Task
Deletes a task from the task list.

**Format**: `delete <index of task>`
- The index **must be a positive integer** less than or equal to
the current size of the task list.

**Examples**: 
- `delete 2`

### 4. Searching Task Descriptions
Displays all the tasks whose descriptions contain the search phrase.

**Format**: `find <search phrase>`
- Trailing whitespaces in the search phrase are ignored.

**Examples**: 
- `find cs2103` returns all tasks whose descriptions contain the phrase `cs2103`

### 5. Adding Priorities to Tasks
Adds a priority descriptor to a specific task. Before this step,
the default priority for a task is `UNDEFINED`.

**Format**: `priority <index of task> <priority>`
- The `<priority>` descriptor must be either `high`, `medium` or `low`.
- Adding a priority overrides the previous priority associated with the task.
- The index **must be a positive integer** less than or equal to
the current size of the task list.

**Examples**: 
- `priority 2 high`
- `priority 3 low`

### 6. Saving and Exiting
Saves all the tasks in the list to a local file and exits the program.
The saved tasks will be loaded again when the program is next run.

**Format**: `bye`
