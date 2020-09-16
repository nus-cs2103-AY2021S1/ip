# Duke User Guide

Duke is a desktop app for managing tasks, optimized for use via a Command
Line Interface (CLI) while still having the benefits of a Graphical User
Interface (GUI). If you can type fast, Duke can get your tasks managed faster
than traditional GUI apps.

## Quick start
1. Ensure that you have Java 11 or above installed in your Computer.

2. Download the latest duke.jar from [here](https://github.com/CalistaIo/ip).

3. Copy the file to the folder you want to use as the home folder for your Duke application.

4. Double-click the file to start the app. The GUI similar to the one below should appear
in a few seconds.

![Duke](Screenshot (279).png)

## Features

### Viewing tasks: `list`

Shows a list of tasks currently stored in the hard disk.

Format: `list`

### Adding a Todo: `todo`

Adds a todo task to the task list.

Format: `todo TODO`

:books: There must only be a task **description** provided for each todo.

Examples:

* `todo borrow book`
* `todo go to the library`

### Adding a Deadline: `deadline`

Adds a deadline to the task list.

Format: `deadline DEADLINE /by DATE`

:clock130: There must be a **date** provided for each deadline.
:books: There must be a task **description** provided for each deadline.
:bulb: The date must either be in **YYYY-MM-DD** or **YYYY-MM-DD HHMM** format.

Examples:

* 'deadline finish homework /by 2020-10-10`
* `deadline return library book /by 2020-12-02 2359`

### Adding an Event: `event`

Adds an event to the task list.

Format: `event EVENT /at DATE`

:clock130: There must be a **date** provided for each event.
:books: There must be a task **description** provided for each event.
:bulb: The date must either be in **YYYY-MM-DD** or **YYYY-MM-DD HHMM** format.


Examples:

* `event attend concert /at 2020-09-28 1600`
* `event project meeting /at 2020-09-15 1800`

### Deleting specified tasks: `delete`

Deletes specified tasks from the task list.

Format: `delete [TASK_NUMBER]...`

* The task numbers specified refer to the indices of the tasks as shown in the task list.
* The index of a task can be found by entering the command `list`.
* The task numbers do not have to be specified in any particular order.

Examples:

* `delete 1`
* `delete 3 1 2`

### Deleting all tasks: `delete all`

Deletes all the tasks from the task list.

Format: `delete all`

### Marking tasks as done: `done`

Marks specified tasks as done.

Format: `done TASK_NUMBER`

* There must be exactly one task number specified.
* The task number specified refer to the index of the task as shown in the task list.
* The index of a task can be found by entering the command `list`.
* Task numbers specified must be valid i.e. they refer to actual tasks.

Examples:

`done 1`
`done 10`

### Finding tasks: `find`

Finds tasks in the task list using a specified keyword.

Format: `find KEYWORD`

* There must be exactly one keyword specified.
* All tasks that match the keyword will be shown.

Examples:

* `find homework`
* `find by`








