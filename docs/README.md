# CS Task Manager
Proudly presented by the mighty Gilth Sebert.

## What is this thingy?
A task manager for all your CS work. Friendly GUI partnered with CLI as main form of interaction.

Made for them fast fingers.

![screenshot](Ui.png)



## What can it do?

### Add task
Add a new task to your list. Supported tasks: `Todo`, `Event`, `Deadline`

### Mark as done
Marks a task as done.

### List
Lists all your current tasks.

### Delete
Deletes a task that's in the list.

### Sort
Sorts your tasks chronologically.

### Find
Finds tasks using a given keyword



## How do I use it?

### `todo` - Add a new todo task

Adds a new todo task to the list

Format: `todo <name of task item>`

Example: `todo CS2103T project`

Outcome: `new todo added`

### `deadline` - Add a new deadline task

Adds a new deadline task to the list

Format: `deadline <name of task item> /at <date>`

Example: `deadline oral presentation /at 2020-01-02`

Outcome: `new deadline added`

Note: date must be in `YYYY-MM-DD` format, else an error message is prompted.

### `event` - Add a new event task

Adds a new event task to the list

Format: `event <name of task item> /by <date>`

Example: `event modrekt /by 2020-01-02`

Outcome: `new event added`

Note: date must be in `YYYY-MM-DD` format, else an error message is prompted.

### `list` - Lists all current tasks

Displays all current tasks in the list.

Format: `list`, expects no other arguments.

Example: `list`

Outcome: displays a list of all tasks

### `delete` - Deletes a selected task

Deletes a selected task from the list.

Format: `delete <item index>`

Example: `delete 1`

Outcome: `task at index 1 deleted`

Note: expects valid integer index, else an error message is prompted.

### `done` - Marks a selected task as done

Marks a selected task as done.

Format: `done <item index>`

Example: `done 2`

Outcome: `task at index 1 marked as done`

Note: expects valid integer index, else an error message is prompted.

### `sort` - Sorts tasks chronologically

Sorts all tasks chronologically

Format: `sort`, expects no other arguments.

Example: `sort`

Outcome: displays a list of tasks sorted chronologically

### `find` - Finds tasks using given keyword

Finds and lists tasks with given keyword

Format: `find <keyword>`

Example: `find homework`

Outcome: displays a list of tasks that contain the string "homework", or prompt with no results`

### `bye` - Quits application

Saves data locally and quits application

Format: `bye`, expects no other arguments

Example: `bye`

Outcome: saves data in a local text file and quits application


## How to get this?

Simply head to the releases page and find the latest release with a binary file.

Download it and voila!

