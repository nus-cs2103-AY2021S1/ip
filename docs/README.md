# User Guide
Duke is a task manager which makes use of Graphical User Interface(GUI). Instructions on how to use it are below:

* Features
    * Adding a new Todo task
    * Adding a new Deadline task
    * Adding a new Event task
    * Deleting a task
    * Finding a task
    * Marking a task as Done
    * Saving tasks
    * Exit

## Features 

### Listing all Tasks `list`
Shows all tasks in the tasklist.

Format: `list`

### Adding a Todo task `todo`
Adds a Todo task with its description to the tasklist.

Format: `todo DESCRIPTION`

Examples: 

* `todo read book`
* `todo do homework`

### Adding a Deadline Task `deadline`
Adds a Deadline task with its description and deadline to the tasklist.

Format: `deadline DESCRIPTION /by DD-MM-YYYY HHMM`

Examples:

* `deadline homework 1 /by 15-09-2020 2359`
* `deadline group project /by 18-09-2020 1800`

### Adding an Event Task `event`
Adds an Event task with its description and date time to the tasklist.

Format: `event DESCRIPTION /at DD-MM-YYYY HHMM`

Examples: 

* `event birthday party /at 12-12-2020 1200`
* `event dinner date /at 13-10-2020 1900`

### Deleting a Task `delete`
Deletes the specified task from the list.

Format: `delete INDEX`
* Deletes the task at the specified INDEX.
* The index refers to the index number in the current list of tasks.
* The index number must be a **positive integer**.

Examples:

* `delete 3` will delete the third task on the tasklist.

### Finding a Task `find`
Finds a list of tasks which contain the description to be found.

Format: `find DESCRIPTION`

Examples:
* `find borrow book` will find the task that contains the description borrow book.

### Marking a task as Done `done`
Marks the specified task as done in the `list`

Format: `done INDEX`
* Marks the task at the specified INDEX as done.
* The index refers to the index number in the current list of tasks.
* The index number must be a **positive integer**.

Examples:
* `done 2` marks the second task in the list as done.

### Saving the tasks
Tasks after saved in the data folder automatically after each user command.

### Exit `bye`
Exits the task manager.

Format: `bye`

## Command Summary 

Index | Action | Format, examples |
| ------------ | ------------ | ------------- |
1 | List all tasks | `list` |
2 | Add a Todo task | `todo TASK_NAME`, e.g.,`todo read books` |
3 | Add a Deadline task | `deadline TASK_NAME /by YYYY-MM-DD`, e.g.,  `deadline group project /by 18-09-2020 1800` |
4 | Add an Event task | `event TASK_NAME /at YYYY-MM-DD`, e.g., `event birthday party /at 12-12-2020 1200` |                 
5 | Mark a task as done | `done INDEX`, e.g., `done 3` |
6 | Delete a task | `delete INDEX`, e.g., `delete 2` |
7 | Find tasks matching the keyword | `find KEYWORD`, e.g., `find borrow book` |
8 | Exit | `bye`
