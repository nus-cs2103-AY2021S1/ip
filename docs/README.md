# User Guide
ByteMe is a desktop scheduling app to help you manage your daily tasks. It is optimized for use via a Command Line Interface (CLI).

## Quick Start
1. Ensure you have Java 11 or higher version installed on your computer.
2. Download release `V0.2` of `duke.jar`.
3. Start `duke.jar`.

## Features 
1. Adding a todo task `todo`
2. Adding an event `event`
3. Adding a deadline `deadline`
4. Listing all tasks `list`
5. Marking a task as done `done`
6. Deleting a task `delete`
7. Finding a task `find`
8. Finding free slots `free time`
9. Exiting programme `bye`

## Usage

### `todo` - Adding a new todo task

Add a new todo task into your task list.

Example of usage: 

`todo finish lab`

### `event` - Adding a new event with a specific time

Add a new event with a specific time into your task list.

Example of usage: 

`event project meeting /at 2020-09-13 19:00`

### `deadline` - Adding a new deadline with a specific time

Add a new new deadline with a specific time into your task list.

Example of usage: 

`deadline assignment /by 2020-09-13 19:00`

### `list` - Listing all the tasks.

Listing all the tasks..

Example of usage: 

`list`

### `done` - Marking a task at a specific index as done

Mark a task at a specific index in your task list as done.

Example of usage: 

`done 4`

### `delete` - Deleting a task at a specific index as done

Delete a task at a specific index in your task list.

Example of usage: 

`delete 4`

### `find` - Finding tasks by keywords

Find tasks by keywords in the list.

Example of usage: 

`find meeting`

### `free time` - Finding free time slots on a specific date

Find free time slots on a specific date.

Example of usage: 

`free time 2020-09-13`

### `bye` - Exiting the programme.

Exit ByteMe.

Example of usage: 

`bye`