# Duke User Guide

Duke is a **desktop app for managing tasks, optimized for use via a Command Line Interface** (CLI)
while still having the benefits of a Graphical User Interface (GUI). If you can type fast, Duke
can get task management done faster than traditional GUI apps.

Duke can be used for managing three types of tasks:
- Todo
  - Todos are tasks that only have a description.
- Deadline
  - Deadlines are tasks that have both a description and a date indicating when the deadline is due.
- Event
  - Events are tasks that have both a description and a date indicating when the event occurs.

## Quick start
1. Ensure that you have Java 11 or above installed in your Computer.

2. Download the latest `duke.jar` from [here](https://github.com/CalistaIo/ip/releases/tag/v0.2).

3. Copy the file to the folder you want to use as the *home folder* for your Duke app.

4. Double-click the file to start the app. The GUI similar to the one below should appear in a few seconds.

![Ui](Ui.png)

5. Type the command in the command box and click on the Send button to execute it.

Some example commands you can try:

  - `list`: Lists all tasks.
  
  - `todo borrow book`: Adds the Todo task `borrow book` to the task list.
  
  - `delete all`: Deletes all tasks.
  
6. When the Duke app starts up, a `data` folder will be automatically created in the home folder,
if it does not yet exist. Tasks will be saved in a `tasks.txt` file within this folder so that they
can be retrieved the next time that the Duke app is used.
  
7. Refer to the Features section below for details of each command.

## Features

**Notes about the command format:**

- Items with `...` after them can be used at least one time.
e.g. `find TASK_NUMBER...` can be used as `find 1`, `find 1 2`, etc. `find` is not allowed.

### Viewing tasks: `list`

Shows a list of tasks currently stored in the `data` folder.

Format: `list`

### Adding a Todo: `todo`

Adds a todo to the task list.

Format: `todo TODO`

1. There must only be a **description** provided for each todo.

Examples:

* `todo borrow book`
* `todo go to the library`

### Adding a Deadline: `deadline`

Adds a deadline to the task list.

Format: `deadline DEADLINE /by DATE`

1. There must be a **description** provided for each deadline.
2. There must be a **date** provided for each deadline.
3. The date must either be in **YYYY-MM-DD** or **YYYY-MM-DD HHMM** format.

Examples:

* `deadline finish homework /by 2020-10-10`
* `deadline return library book /by 2020-12-02 2359`

### Adding an Event: `event`

Adds an event to the task list.

Format: `event EVENT /at DATE`

1. There must be a **description** provided for each event.
2. There must be a **date** provided for each event.
3. The date must either be in **YYYY-MM-DD** or **YYYY-MM-DD HHMM** format.

Examples:

* `event attend concert /at 2020-09-28 1600`
* `event project meeting /at 2020-09-15`

### Deleting specific tasks: `delete`

Deletes specific tasks from the task list.

Format: `delete TASK_NUMBER...`

* At least one task number must be specified.
* Task numbers specified refer to the indices of the tasks in the task list.
* The index of a particular task can be found by entering the command `list`.
* The task numbers do not have to be specified in any particular order.

Examples:

* `delete 1`
* `delete 3 1 2`

### Deleting all tasks: `delete all`

Deletes all the tasks from the task list.

Format: `delete all`

### Marking tasks as done: `done`

Marks a specific task as done.

Format: `done TASK_NUMBER`

* There must be exactly one task number specified.
* The task number specified refers to the index of the task in the task list.
* The index of a particular task can be found by entering the command `list`.

Examples:

* `done 1`
* `done 10`

### Finding tasks: `find`

Finds tasks in the task list using a specific keyword.

Format: `find KEYWORD`

* There must be exactly one keyword specified.
* All tasks that match the keyword will be shown.

Examples:

* `find homework`
* `find by`








