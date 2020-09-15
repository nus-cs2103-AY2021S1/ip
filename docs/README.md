# Duke - User Guide

1. [Introduction](#1-introduction)
1. [Quick Start](#2-quick-start)
1. [Features](#3-features)
    1. [Listing all tasks: `list`](#31-listing-all-tasks-list)
    1. [Finding tasks: `find`](#32-finding-tasks-find)
    1. [Adding a ToDo task: `todo`](#33-adding-a-todo-task-todo)
    1. [Adding a Deadline task: `deadline`](##34-adding-a-deadline-task-deadline)
    1. [Adding an Event task: `event`](#35-adding-an-event-task-event)
    1. [Adding a Do After task: `doafter`](#36-adding-a-do-after-task-doafter)
    1. [Marking a task as done: `done`](#37-marking-a-task-as-done-done)
    1. [Deleting a task: `delete`](#38-deleting-a-task-delete)
    1. [Exiting the app: `bye`](#39-exiting-the-app-bye)

## 1. Introduction

Duke is a desktop app for managing tasks. It is catered towards individuals who enjoy working with a Command Line Interface (CLI). This app is especially useful for fast typists.

## 2. Quick Start

1. Ensure you have Java `11` or above installed.
2. Download the latest duke.jar here (insert link later).
3. Copy the file to a folder you wish to use as your home folder.
4. Double-click the file to start the app. The GUI should appear in a few seconds. Shown below is an example with some user commands and the app's responses.

![Ui image](/docs/Ui.png)

5. Type the command in the text box and press `Enter`.
6. Refer to [Section 3, "Features"](#3-features) for details of each command.

## 3. Features

### 3.1 Listing all tasks: `list`

Shows a list of all the tasks added.

Format / Example of usage:
`list`

### 3.2 Finding tasks: `find`

Returns with task(s) that contain the given keyword.

Format:
`find [keyword]`

Example of usage:
`find test`

### 3.3 Adding a ToDo task: `todo`

Adds a new todo task to the current list of tasks.

Format:
`todo [taskname]`

Example of usage:
`todo read lecture notes`

### 3.4 Adding a Deadline task: `deadline`

Adds a new deadline task to the current list of tasks.

Format:
`deadline [taskname] /by [yyyy-mm-dd]`

Example of usage:
`deadline return book /by 2020-10-13`

### 3.5 Adding an Event task: `event`

Adds a new event task to the current list of tasks.

Format:
`event [taskname] /at [date, can be in any format]`

Example of usage:
`event 2103 meeting /at 15 sep, 8pm`

### 3.6 Adding a Do After task: `doafter`

Adds a new do after task to the current list of tasks.

Format:
`doafter [taskname] /after [date, can be in any format]`

Example of usage:
`doafter study for test /after lecture tmr`

### 3.7 Marking a task as done: `done`

Changes the completion status of a task to be done.

Format:
`done [task number]`

Example of usage:
`done 5`

### 3.8 Deleting a task: `delete`

Deletes a task from the list.

Format:
`delete [task number]`

Example of usage:
`delete 4`

### 3.9 Exiting the app: `bye`

Exits the app.

Format / Example of usage:
`bye`
