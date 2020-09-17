# Elon User Guide
Elon is a desktop application for managing tasks, such as to-dos, events,
and deadlines. The user interacts with the system in a graphical user
interface that is designed to look like a chat-bot.

## Features 
To interact with Elon, commands are type in a text field and submitted
with the send button or simply pressing 'Enter' on they keyboard.
Commands have a general format: [command] [description]

## Quick Start
1. Ensure you have Java 11 or higher installed on your system.
1. Download `elon.jar` from this repository and run it with `java -jar elon.jar`.
1. You should see the introduction screen appear:\
![Elon introduction screen](Ui_Intro_Screen.png)

## Features
* [Adding a task: `todo`, `event`, `deadline`](#adding-a-task-todo-event-deadline)
* [Listing all tasks: `list`](#listing-all-tasks-list)
* [Mark a task as done: `done`](#mark-a-task-as-done-done)
* [Delete an existing task: `delete`](#delete-an-existing-task-delete)
* [Find a task `find`](#find-a-task-find)
* [Save and exit Elon: `bye`](#save-and-exit-elon-bye)
* [Get help `help`](#get-help-help)

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

Expected Outcomes:
```$xslt
Got it, I've added this todo: [T][X] Sweep the floor
   Now you have 1 tasks in the list.
Got it, I've added this event: [E][X] School concert (at: JANUARY 20 2021 12:00AM)
   Now you have 2 tasks in the list.
Got it, I've added this deadline: [D][X] Coding project (by: this Friday 5pm)
   Now you have 3 tasks in the list.
```

### Listing All Tasks: `list`
Displays all the current tasks stored in Elon.

Format: `list`

Expected Outcome:
```$xslt
Here are the tasks in your list:
    1. [T][X] Sweep the floorw`
    2. [E][X] School concert (at: JANUARY 20 2021 12:00AM)
    3. [D][X] Coding project (by: this Friday 5pm)`
```

### Mark a task as done: `done`
Given the task's number, Elon will mark the specified
task as done with a tick. The task number is based on 
the ordering when `list` is called.

Format: `done [task number]`

Example: `done 2`

Expected Outcome:
```$xslt
Nice! I've marked this task as done:
[E][✔] School concert (at: JANUARY 20 2021 12:00AM)
```

### Delete an existing task: `delete`
Given the task's number, Elon will delete the specified
task. The task number is based on the ordering when `list` is called.

Format: `delete [task number]`

Example: `delete 3`

Expected Outcome:
```$xslt
Noted. I have removed this task:
[D][X] Coding project (by: this Friday 5pm)
Now you have 2 tasks in the list.
```

### Find a task `find`
Display existing task(s) which match the user's keyword(s).
The searching process is case-insensitive.
Format: `find [keyword 1] [keyword 2] (optional) ...`

Example:
* `find school concert`

Expected Outcome:
```$xslt
Here are the matching tasks in your list:
1. [E][✔] School concert (at: JANUARY 20 2021 12:00AM)
```

### Save and exit Elon: `bye`
Saves any tasks in Elon and prepares exiting of the program.
Enter any other command after `bye` to exit Elon.

Format: `bye`

Expected Outcome:
```
Saved your list. Enter new command to exit...
```

### Get help `help`
Displays this list of commands in Elon.

Format: `help`
 
For specific help to a particular command, type the command after `help`:

Example:
* `help event`

Expected outcome:
```$xslt
event: Add an event task.
Usage: event [description] /at [date]
Date can be in the following formats:
dd/MM/yyyy HHmm, yyyy/MM/dd HHmm, MM/dd/yyyy HHmm,
HHmm dd/MM/yyyy, HHmm yyyy/MM/dd, HHmm MM/dd/yyyy,
dd-MM-yyyy HHmm, yyyy-MM-dd HHmm, MM/dd/yyyy HHmm,
HHmm dd-MM-yyyy, HHmm yyyy-MM-dd, HHmm MM-dd-yyyy.
It is fine to omit the hours and minutes. If your
input does not match the date time format, then\
Duke will simply enter the date as whatever you
input as [date].
```