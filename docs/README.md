# User Guide

## Features 

### Adding a task
Adds either a todo, event or deadline to the task list.

### Marking a task as done
Marks a task as completed.

### Finding a task
Finds a task within the list of tasks by a keyword.

### Listing all tasks
Lists all tasks in the task list.

### Viewing help
Displays a summary of commands.

### View upcoming tasks
Lists upcoming deadlines and events in the coming week.

## Usage

### `help` - Viewing help

Displays a summary of the commands and how to use them.

Format:
`help`

### `todo` - Adding a todo

Adds a todo to the task list.

Format:
`todo <description>`

Example: 

`todo read book`
### `event` - Adding an event

Adds an event to the task list.

Format:
`event <description> /at <date in YYYY-MM-DD format>`

Example: 

`event Hack&Roll 2020 /at 2020-05-20`

### `deadline` - Adding a deadline

Adds a deadline to the task list.

Format:
`deadline <description> /by <date in YYYY-MM-DD format>`

Example: 

`deadline return library book /by 2020-05-20`

### `list` - Listing all tasks

Shows a list of all tasks in the task list.

Format:
`list`

### `done` - Marking a task as complete

Marks a specified task in the task list as completed.

Format:
`done <index of task>`
* \<index of tasks> refers to the index number shown in the displayed task list.
* The index must be a __positive integer__ and lesser than or equal to the total size of the task list.

Example: 

`done 1`

### `Reminder` - Viewing upcoming events

Displays a list of upcoming events and deadlines within the next week.

Format:
`reminder`

### `bye` - Exiting the program

Exits the program.

Format:
`bye`