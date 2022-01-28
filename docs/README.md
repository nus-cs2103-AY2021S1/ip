# User Guide

Duke is a **desktop app for managing tasks, optimized**
**use via a Command Line Inteface** (CLI). If you can type
fast, Duke can get your tasks management done faster than
common GUI apps.

## Quick start
 
1. Ensure you have `Java 11` or above installed in your computer.
2. Download the latest `duke.jar` from [here](https://drive.google.com/uc?export=download&id=1WGEDItVpHP4xJtJcWQU993ZyFB7ZHvLI).
3. Copy the file to the folder you want to use as the home
folder for your Duke
4. Double-click the file to start the app.
5. Type command in the command box and press `Enter` to execute it.
    - `help` : shows all available commands with usage and format.
    - `todo Laundy` : add `Laundry` to list of tasks.
    - `deadline Get GF /by 23 Dec 2023` : add `Get GF` by `23 Dec 2023` to list of tasks.
    - `event Exam /at 22 Sept` : add `Exam` at `22 Sept`.
    - `list` : show all existing tasks.
    - `find GF` : show all tasks that contains `GF`.
    - `done 1` : mark the 1st task in the list as done.
    - `delete 2` : delete the 2nd task in the list.
    - `bye` : exit Duke.
6. Refer to the Features below for details of each command

## Features 

### Viewing help: `help`
Shows a message explaining the available commands - format and usage.

### Adding a todo: `todo`
Adds a todo task to the list of tasks.\
Format: `todo <desc>`
- `<desc>` is the description.

Examples:
- `todo cook dinner` Adds a todo task with description: `cook dinner`.


### Adding a deadline: `deadline`
Adds a deadline to the list of tasks.\
Format: `deadline <desc> /by <by>`
- `<desc>` is the description
- `<by>` is the due by

Examples:
- `deadline CS2103 iP /by 15 sep 2020` Adds a deadline with description: `CS2103 iP` that is due by: `15 Sep 2020`.

### Adding an event: `event`
Adds an event to the list of tasks.\
Format: `event <desc> /at <at>`
- `<desc>` is the description
- `<at>` is the time of the event

Examples:
- `event CS2100 Exam /at 22 sep 2020` Adds an event with description: `CS2100 Exam` that is happening on: `22 Sep 2020`

### List out all tasks: `list`
Shows a list of all tasks.
Format: `list`

### Find tasks using keywords: `find`
Shows a list of tasks that contains the `searchword` in the task description.
Format: `find <searchword>`

Examples:
- `find CS2103` Shows a list of tasks that contains `CS2103` in the task description.

### Mark a task as done: `done`
Mark a task with `taskId` as done.
Format: `done <taskId>`

Examples:
- `done 1` Marks the 1st task in the list as done.

### Delete a task: `delete`
Delete a task with `taskId`.
Format: `delete <taskId>`

Examples:
- `delete 1` Deletes the 1st task in the list.

## FAQ
**Q**: How do I transfer my data to another Computer?\
**A**: Move the **data folder** to the **home folder of Duke** in the other Computer.

## Command Summary

| Action           | Format, Examples                                                           |
|------------------|----------------------------------------------------------------------------|
| **Add Todo**     | `todo <desc>` <br> e.g., `todo cook dinner`                                |
| **Add Deadline** | `deadline <desc> /by <by>` <br> e.g., `deadline CS2103 iP /by 15 sep 2020` |
| **Add Event**    | `event <desc> /at <at>` <br>  e.g., `event CS2100 Exam /at 22 sep 2020`    |
| **Done**         | `done <taskId>` <br> e.g., `done 1`                                        |
| **Delete**       | `delete <taskId>` <br> e.g., `delete 1`                                    |
| **Find**         | `find <searchword>` <br> e.g., `find CS2103`                               |
| **List**         | `list`                                                                     |
| **Help**         | `help`                                                                     |