# Duke User Guide

Duke is a **desktop app for organising your todo tasks, deadlines and events via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI).

## Quick Start
1. Ensure you have Java 11 or above installed in your Computer.
2. Download the latest duke.jar from [here](https://github.com/m0nggh/ip/releases/tag/v0.3).
3. Copy the file to the folder you want to use as the _home folder_ for your Duke.
4. Double-click the file to start the app.
5. Type the command in the command box and press Enter to execute it. e.g. typing **`list`** and pressing Enter will display your tasks. The GUI similar to the below should appear within a second. <br><br>
![GUI](https://github.com/m0nggh/ip/blob/master/docs/Ui.png)

## Features & Usage

Below are a list of features and their respective commands:

### `help` - displays all available commands

This command shows a list of commands available in the Duke program.

### `list` - displays all current tasks

This command shows a list of all the tasks in the tasklist. No tasks will be displayed if empty.

Example of usage: 

`list`

Expected outcome:

`Duke: Here are the tasks in your list:` <br>
`1.[T][✗] eat dinner` <br>
`2.[T][✓] watch show` <br>

### `done` - marks task as completed

This command checks off the task as completed in the tasklist. **A valid task number has to go right after the command.**

Format:

`done [task number]`

Example of usage: 

`done 1`

Expected outcome:

`Duke: Nice! I've marked this task as done:` <br>
`[T][✓] eat dinner` <br>

### `todo` - adds a todo task

This command adds a todo task to the tasklist. **A todo task only requires a simple description right after the command.** The updated number of tasks will be displayed as well.

Format:

`todo [details]`

Example of usage: 

`todo sleep`

Expected outcome:

`Duke: Got it. I've added this task:` <br>
`[T][✗] sleep` <br>
`Now you have 3 tasks in the list.` <br>

### `deadline` - adds a deadline

This command adds a deadline to the tasklist. **A deadline requires a simple description and a valid date/time right after the command.** The updated number of tasks will be displayed as well.

Format:

`deadline [details] /by [YYYY-MM-DD HH:MM]`

Example of usage: 

`deadline return book /by 2020-01-01 12:30`

Expected outcome:

`Duke: Got it. I've added this task:` <br>
`[D][✗] return book (by: Jan 1 2020 12:30)` <br>
`Now you have 3 tasks in the list.` <br>

### `event` - adds an event

This command adds an event to the tasklist. **An event requires a simple description and a valid date/time right after the command.** The updated number of tasks will be displayed as well.

Format:

`event [details] /at [YYYY-MM-DD HH:MM]`

Example of usage: 

`event project meeting /at 2020-01-02 14:30`

Expected outcome:

`Duke: Got it. I've added this task:` <br>
`[E][✗] project meeting (at: Jan 2 2020 14:30)` <br>
`Now you have 3 tasks in the list.` <br>

### `delete` - removes task

This command removes the task from the tasklist. **A valid task number has to go right after the command.** The updated number of tasks will be displayed as well.

Format:

`delete [task number]`

Example of usage: 

`delete 1`

Expected outcome:

`Duke: Noted. I've removed this task:` <br>
`[T][✓] eat dinner` <br>
`Now you have 2 tasks in the list.` <br>

### `find` - searches for tasks

This command searches for all tasks from the tasklist by the relevant keywords. **A keyword has to go right after the command.** The matched tasks would be displayed as a list.

Format:

`find [keyword]`

Example of usage: 

`find dinner`

Expected outcome:

`Duke: Here are the matching tasks in your list:` <br>
`1.[T][✓] eat dinner` <br>

### `undo` - reverts the latest command

This command reverts for the latest command that modifies the tasklist. **Modifying commands: todo, deadline, event, done, delete.** The updated number of tasks will be displayed as well.

Format:

`undo`

Example of usage: 

`undo` after deleting task

Expected outcome:

`Duke:` <br>
`UNDO EXECUTED:` <br>
`I've recovered this task:` <br>
`[T][✓] eat dinner` <br>
`Now you have 2 tasks in the list.` <br>

### `bye` - exits the program

This command saves the data and exits the program.

## Command Summary
|Action|Format/Examples|
|--|--|
|**help** | `help` |
|**list**  | `list` |
|**done**  | `done [task number]` <br> e.g. `done 1`|
|**todo**  | `todo [details]` <br> e.g. `todo sleep` |
|**deadline**  | `deadline [details] /by [YYYY-MM-DD HH:MM]` <br> e.g. `deadline return book /by 2020-01-01 12:30` |
|**event**  | `event [details] /at [YYYY-MM-DD HH:MM]` <br> e.g. `event project meeting /at 2020-01-02 14:30` |
|**delete**  | `delete [task number]` <br> e.g. `delete 1` |
|**find**  | `find [keyword]` <br> e.g. `find dinner` |
|**undo**  | `undo` |
|**bye**  | `bye` |

## Acknowledgements
GUI code was referenced from https://se-education.org/guides/tutorials/javaFx.html. <br>
GUI image was taken from https://onlyvectorbackgrounds.com/ambient-light-background-day-blue/.
