# User Guide
Meow the meme lord is a chat bot to help you manage your tasks and deadlines. It is a to-do list in a command interface form

## Quick Start
	1. Download the latest Meow the meme lord.jar
	2. Double click the file to start the app
    3. Use the text field to type in command
## Features 
Available commands:
-   `todo`, `deadline`, `event` Adds the specific task type to the list
-   `list` Lists out all current tasks
-   `delete 1` Deletes the 1st task in the list
-   `done 4` Marks the 4th task in the list as done
-   `find book` finds all task with `book` in its description


## Key Features Examples
### Feature 1 
Adding of tasks

#### Usage

### `todo`, `deadline`, `event` - Adds the specific task type

Adds a todo, deadline or event task. 

Note that for a deadline, a `/by` followed by the date in the `dd/MM/yyyy HHmm` format

Note that for an event, a `/at` followed by the date and time in the format `dd/MM/yyyy HHmm` format

Example of usage: 

`todo feed my cat`

`deadline return library book /by 20/9/2020 0000`

`event Birthday party /at 20/9/2020 0000`

Expected output:

>Got it. I've added the task:
>
>[T][x] feed my cat

>Got it. I've added the task: 
>
>[D][x] return library book (by: 00:00 20 Sep 2020)

>Got it. I've added the task: 
>
>[E][x] Birthday party (at: 00:00 20 Sep 2020)

### Feature 2
Listing of tasks

#### Usage

### `list` - Lists all current task

List all the current task in the list

Example of usage: 

`list`

Expected output:

>Meow list
>
>1. [T][x] feed my cat

### Feature 3
Deleting of tasks

#### Usage

### `delete` - deletes a task

Deletes the task specified by the number after the `delete` command

Example of usage: 

`delete 1`

Expected output:

>Noted. I've removed this task:
>
>1. [T][x] feed my cat