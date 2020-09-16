# Elon User Guide
Elon is a desktop application for managing tasks, such as to-dos, events,
and deadlines. The user interacts with the system in a graphical user
interface that is designed to look like a chat-bot.

## Features 
To interact with Elon, commands are type in a text field and submitted
with the send button or simply pressing 'Enter' on they keyboard.
Commands have a general format: [command] [description]

### Adding a Task: `todo` `event` `deadline`
A task consists of a description and optionally, a date and/or time
if the task is an event or deadline. Elon will attempt to parse the
date and/or time provided for events and deadlines.

Format:
* To-do: `todo [description]`
* Event: `event [description] /at [date]`
* Deadline: `deadline [description] /by [date]`

Examples:
* `todo Sweep the floor`
* `event School concert /at 20-01-2021 1200`
* `deadline Coding project /by this Friday 5pm`

### Listing All Tasks: `list`
Displays all the current tasks stored in Elon.

Format: `list`

### Mark a task as done: `done`
Given the task's number, Elon will mark the specified
task as done with a tick. The task number is based on 
the ordering when `list` is called.

Format: `done [task number]`

Example: `done 2`

### Delete an existing task: `delete`
Given the task's number, Elon will delete the specified
task. The task number is based on the ordering when `list` is called.

Format: `delete [task number]`

Example: `delete 5`

### Find a task `find`
Display existing task(s) which match the user's keyword(s).
The searching process is case-insensitive.
Format: `find [keyword 1] [keyword 2] (optional) ...`

Examples:
* `find homework`
* `find school concert`

### Save and exit Elon: `bye`
Saves any tasks in Elon and prepares exiting of the program.
Enter any other command after `bye` to exit Elon.

Format: `bye`

### Get help `help`
Displays this list of commands in Elon.

Format: `help`
 
For specific help to a particular command, type the command after `help`:

Examples:
* `help list`
* `help event`
