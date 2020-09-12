# Duke User Guide

- [Introduction](#introduction)
- [Quick Start](#quick-start)
- [Features](#features)

  1. [Add a `Deadline` task](#1-add-a-deadline-task)
  2. [Add an `Event` task](#2-add-an-event-task)
  3. [Add a `Todo` task](#3-add-a-todo-task)
  4. [Delete a `Task`](#4-delete-a-task)
  5. [Exit Duke application](#5-exit-duke-application)
  6. [Find `Task`](#6-find-task)
  7. [List `Task`](#7-list-task)
  8. [Mark a `Task` as done](#8-mark-a-task-as-done)
  9. [Prioritise a `Task`](#9-prioritise-a-task)

- [Command Summary](#command-summary)

## Introduction

Duke application is an **offline task management tool that organizes the task list** and saves the task data in the local computer's storage.

<img src="Ui/../Ui.png" width="320" height="497"/>

## Quick Start

1. Ensure you have Java `11` or above installed in your computer.

2. Download the latest release `duke.jar` from the [GitHub repo](https://github.com/teekoksiang/ip/releases).

3. Open the finder/file explorer and navigate to the directory where `duke.jar` is stored.

4. Double-click `duke.jar` to start the application and the GUI below will appear. <br>
   <img src="welcome.png" width="320" height="497"/>
5. Type the command in the input box and press Enter or click the Send button to execute it.

6. Refer to [Features](#features) section to learn all the commands.

## Features

> Notes about the command format:
>
> - Words in `UPPER_CASE` are the parameters to be supplied by the user.

### 1. Add a `Deadline` task

Add a `Deadline` task and save it to the `./data/tasks.txt` file.

Usage

`deadline TASK_NAME /by DATE` - add a `Deadline` task

Enter the command to add a `Deadline` task and save it to the `./data/tasks.txt` file. The `DATE` must follow `yyyy-mm-dd` format.

Example of usage:

`deadline CS2103T iP /by 2020-09-18`

Expected outcome:

```console
Got it. I've added this task:
    [D][✗][MEDIUM] CS2103T iP (by: Sep 18 2020)
Now you have 1 tasks in the list.
```

### 2. Add an `Event` task

Add an `Event` task and save it to the `./data/tasks.txt` file.

### Usage

### `event TASK_NAME /at TIME` - add an `Event` task

Enter the command to add an `Event` task and save it to the `./data/tasks.txt` file.

Example of usage:

`event CS2103T iP /at Sep 18, 2359`

Expected outcome:

```console
Got it. I've added this task:
    [E][✗][MEDIUM] CS2103T iP (at Sep 18, 2359)
Now you have 1 tasks in the list.
```

### 3. Add a `Todo` task

Add a `Todo` task and save it to the `./data/tasks.txt` file.

### Usage

### `todo TASK_NAME` - add a `Todo` task

Enter the command to add a `Todo` task and save it to the `./data/tasks.txt` file.

Example of usage:

`todo CS2103T iP`

Expected outcome:

```console
Got it. I've added this task:
    [T][✗][MEDIUM] CS2103T iP
Now you have 1 tasks in the list.
```

### 4. Delete a `Task`

Delete a `Task` and update `./data/tasks.txt` file.

### Usage

### `delete TASK_NUMBER` - delete a `Task`

Enter the command to delete a `Task` and update `./data/tasks.txt` file.

Example of usage:

`delete 1`

Expected outcome:

1. If there is task associated with the number `1`:

```console
Noted. I have removed this task:
    [T][✗][MEDIUM] CS2103T iP
Now you have 1 tasks in the list.
```

2. If there is no task associated with the number `1`:

```console
The task number is not found
```

### 5. Exit Duke application

Exit Duke application.

### Usage

### `bye` - exit application

Enter the command to exit and close Duke application.

Example of usage:

`bye`

Expected outcome:

```console
Bye. See you again soon!
```

The application will be closed.

### 6. Find `Task`

Find the tasks based on the query keyword.

### Usage

### `find KEYWORD` - find the tasks

Enter the command to search for the tasks and display the found tasks.

Example of usage:

`find CS2103T`

Expected outcome:

1. If there is task associated with the keyword `CS2103T`:

```console
Here are the matching tasks in your list:
    1.[T][✗][MEDIUM] CS2103T iP
```

2. If there is no task associated with the keyword `CS2103T`:

```console
No task found...
```

### 7. List `Task`

List all the saved tasks from `./data/tasks.txt` file.

### Usage

### `list` - list all the saved tasks

Enter the command to display all the tasks.

Example of usage:

`list`

Expected outcome:

1. If there is task:

```console
Here are the tasks in your list:
    1.[T][✗][MEDIUM] CS2103T iP
```

2. If there is no task:

```console
No task added yet...
```

### 8. Mark a `Task` as done

Mark a `Task` as done and update `./data/tasks.txt` file.

### Usage

### `done TASK_NUMBER` - mark the `Task` as done

Enter the command to mark the `Task` as done and update `./data/tasks.txt` file.

Example of usage:

`done 1`

Expected outcome:

1. If there is task associated with the number `1`:

```console
Nice! I have marked this task as done:
    [T][✓][MEDIUM] CS2103T iP
```

2. If there is no task associated with the number `1`:

```console
The task number is not found
```

### 9. Prioritise a `Task`

Mark a `Task` priority and update `./data/tasks.txt` file.

### Usage

### `priority TASK_NUMBER PRIORITY_LEVEL` - mark the `Task` priority

Enter the command to mark the `Task` priority and update `./data/tasks.txt` file.
Priority level:

- `1`: low
- `2`: medium (default)
- `3`: high

Example of usage:

`priority 1 3`

Expected outcome:

1. If there is task associated with the number `1`:

```console
Nice! I have updated the task priority: HIGH
    [T][✓][HIGH] CS2103T iP
```

2. If there is no task associated with the number `1`:

```console
The task number is not found
```

## Command Summary

| Action   | Format, Example                                                              |
| -------- | ---------------------------------------------------------------------------- |
| Bye      | `bye`                                                                        |
| Deadline | `deadline TASK_NAME /by DATE` <br> e.g. `deadline CS2103T iP /by 2020-09-18` |
| Delete   | `delete TASK_NUMBER` <br> e.g. `delete 1`                                    |
| Done     | `done TASK_NUMBER` <br> e.g. `done 1`                                        |
| Event    | `event TASK_NAME /at TIME` <br> e.g. `event CS2103T iP /at Sep 19, 2359`     |
| Find     | `find KEYWORD` <br> e.g. `find CS2103T`                                      |
| List     | `list`                                                                       |
| Priority | `priority TASK_NUMBER PRIORITY_LEVEL` <br> e.g. `priority 1 3`               |
| Todo     | `todo TASK_NUMBER` <br> e.g. `todo 1`                                        |
