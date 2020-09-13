# User Guide

Duke Task Tracker is a **desktop application for managing your tasks, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, Duke Task Tracker can get your task management done faster than traditional GUI applications. 

* [Features](#features)
  * [Viewing help : **`--help`**](#viewing-help-----help)
  * [Adding a basic task : **`todo`**](#adding-a-basic-task--todo)
  * [Adding a task with a deadline : **`deadline`**](#adding-a-task-with-a-deadline--deadline)
  * [Adding a task with a date : **`event`**](#adding-a-task-with-a-date--event)
  * [Listing all tasks : **`list`**](#listing-all-tasks--list)
  * [Setting a task as done  : **`done`**](#setting-a-task-as-done--done)
  * [Deleting a task : **`delete`**](#deleting-a-task--delete)
  * [Searching for tasks : **`search`**](#searching-for-tasks--search)
  * [Adding a task to reminders : **`remind`**](#adding-a-task-to-reminders--remind)
  * [Retrieving events on a date : **`getEvents`**](#retrieving-events-on-a-date--getevents)
  * [Retrieving reminders : **`getReminders`**](#retrieving-reminders--getreminders)
  * [Exiting the program :  **`bye`**](#exiting-the-program--bye)
  * [Saving the data ](#saving-the-data)

----------------------------

## Features

### Features

- words in `<....>` are the user input.
- items that have `/` before them are keywords for that command

### Viewing help : `-- help`

Shows a message explaining what commands Duke Task Tracker accepts and what the command formats are.

Format : --help

### Adding a basic task : `todo`

Adds a task that only has a task description to the list of tasks.

Format: todo \<task description>

Examples: 

- todo A new Task 
- todo CS2100 Tutorial 4

### Adding a task with a deadline : `deadline`

Adds a task that has a deadline associated with it.

Format: deadline \<task description> /by \<deadline description>

Examples:

- deadline new task /by 2020-06-05
- deadline new deadline /by Tomorrow

Notes:

- \<deadline description> can either be text or a date in YYYY-MM-DD format.

### Adding a task with a date : `event`

Adds a task that has an event date associated with it.

Format: event \<task description> /at \<event date description>

Examples:

- event new task /at 2020-09-18
- event new event /at Tonight 8pm

Notes:

- \<event date description> can either be text or a date in YYYY-MM-DD format.

### Listing all tasks : `list`

Shows a list of all tasks currently in the task list.

Format: list

### Setting a task as done : `done`

Edits an existing task and marks it as done.

Format : done \<task number>

Examples:

- done 1
- done 3

Notes:

- \<task number> is the number that appears on the left of a task when the list of tasks is displayed.

### Deleting a task : `delete`

Deletes an existing task from the task list.

Format: delete \<task number>

Examples:

- delete 2
- delete 4

Notes:

- \<task number> is the number that appears on the left of a task when the list of tasks is displayed.

### Searching for tasks : `search`

Searches for tasks in the list that match the keywords.

Format: search \<keyword1 keyword2 etc...>

Examples:

- search CS2103T
- search homework tutorial

Notes:

- search takes in multiple keywords as arguments and tasks are matched to each keyword individually.

### Adding a task to reminders : `remind`

Adds a task to the list of reminders

Format: remind \<task number>

Examples:

- remind 3
- remind 5

Notes:

- \<task number> is the number that appears on the left of a task when the list of tasks is displayed.

### Retrieving events on a date : `getEvents`

Retrieves all tasks that take place or have deadlines on the corresponding date.

Format: getEvents \<date>

Examples:

- getEvents 2020-06-05
- getEvents 2020-09-18

Notes:

- \<date> must be a valid date in YYYY-MM-DD format.

### Retrieving reminders : `getReminders`

Retrieves all reminders.

Format: getReminders

### Exiting the program : `bye`

Exits the program.

Format: bye

### Saving the data 

Duke Task Tracker automatically saves data in the hard disk every time a change is made. 

----------------------

## Command Summary

|          Action          |                          Format                          |
| :----------------------: | :------------------------------------------------------: |
|           Help           |                         `--help`                         |
|      Add Basic Task      |                `todo <task description>`                 |
|  Add Task with Deadline  | `deadline <task description> /by <deadline description>` |
| Add Task with Event Date | `event <task description> /at <event date description>`  |
|           List           |                          `list`                          |
|           Done           |                   `done <task number>`                   |
|          Delete          |                  `delete <task number>`                  |
|          Remind          |                  `remind <task number>`                  |
|          Search          |           `search <keyword1 keyword2 etc...>`            |
|        getEvents         |                    `getEvents <date>`                    |
|       getReminders       |                      `getReminders`                      |
|           Exit           |                          `bye`                           |