# Milk User Guide

## Table of contents

1. [Introduction](#introduction)
1. [Getting Started](#gettingStarted)
1. [Features](#features)
    1. [Adding a todo: `todo`](#todo)
    1. [Adding a deadline: `deadline`](#deadline)
    1. [Adding an event: `event`](#event)
    1. [Listing all tasks: `list`](#list)
    1. [Marking a task as done: `done`](#done)
    1. [Deleting a task: `delete`](#delete)
    1. [Finding tasks: `find`](#find)
    1. [Finding deadlines/events occuring on a specific date: `date`](#date)
    1. [Obtaining statistics about managed tasks: `stats`](#stats)
    1. [Exiting the program: `bye`](#bye)
1. [Command Summary](#summary)
1. [Troubleshooting](#troubleshooting)

## Introduction

Milk is a desktop application that manages your todos, deadlines and events.
It comes with Command Line Interface (CLI) and Graphical User Interface (GUI).

This user guide provides documentation on the installation process to get you started. It also describes the features of the application.

## Getting Started

Follow these instructions to launch the application:
1. Ensure that you have Java `11` or above installed in your computer.
1. Download the latest version of `Milk.jar` here.
1. To launch the application, you can either:
    * Double-click `Milk.jar`, or
    * Open your computer's Command Prompt and run `java -jar "<PATH OF Milk.jar>"`
1. You should see this after successful launch of the application:
   
   <img src="https://user-images.githubusercontent.com/65069982/93287632-90bfb400-f80c-11ea-85f1-7bf18d20bc34.png" height="300">
   
When the application is first launched, a `data` text file will be created:
* If you launched the application by double-clicking the jar file, the `data` file will be created inside the folder `Milk.jar` is currently in.
* If you used the Command Prompt method instead, the `data` file will be created inside `C:\Users\Username`.

## Features

Note the following:
* Commands are case-sensitive.
* The parameters that the user inputs are represented in `UPPER_CASE`.
* `DATE` should be in **YYYY-MM-DD** format.
* The `data` file stores the list of tasks that Milk is managing. It would be accessed across sessions, which starts when the application is launched and ends when the application is closed.

### Adding a todo: `todo`

You can add a todo for Milk to manage. A todo is a task that does not have any date attached to it.

Format: `todo DESCRIPTION`

Example usage: `todo read book`

Expected outcome:

<img src="https://user-images.githubusercontent.com/65069982/93289770-c6b36700-f811-11ea-867a-bdec143659ca.png" height="150">

### Adding a deadline: `deadline`

You can add a deadline for Milk to manage. A deadline is a task that needs to be done before a specific date.

Format: `deadline DESCRIPTION /by DATE`

Example usage: `deadline return book /by 2020-06-06`

Expected outcome:

<img src="https://user-images.githubusercontent.com/65069982/93290043-6bce3f80-f812-11ea-9e2a-5e9d3d61c85a.png" height="150">

### Adding an event: `event`

You can add an event for Milk to manage. An event is a task that occurs at a specific date.

Format: `event DESCRIPTION /at DATE`

Example usage: `event project meeting /at 2020-08-06`

Expected outcome:

<img src="https://user-images.githubusercontent.com/65069982/93290189-c2d41480-f812-11ea-81b7-8e0ae4f1f863.png" height="150">

### Listing all tasks: `list`

You can ask Milk to display the list of tasks that you have added. Tasks that have been added and not deleted in previous sessions would be displayed in the list as well.

Format: `list`

Example usage: `list`

Expected outcome:

<img src="https://user-images.githubusercontent.com/65069982/93290356-18a8bc80-f813-11ea-9762-a22740315c05.png" height="150">

### Marking a task as done: `done`

You can mark a task as done after completing the task.

Format: `done INDEX`

Example usage: `done 1`

Expected outcome:

<img src="https://user-images.githubusercontent.com/65069982/93290501-75a47280-f813-11ea-8a56-d69e3e181bce.png" height="150">

### Deleting a task: `delete`

You can delete tasks from the list of tasks.

Format: `delete INDEX`

Example usage: `delete 3`

Expected outcome:

<img src="https://user-images.githubusercontent.com/65069982/93292269-c1591b00-f817-11ea-9c17-bdc5bd43599a.png" height="150">

### Finding tasks: `find`

You can find tasks from the list of tasks by inputting keyword(s). The keywords are case-sensitive.

Format: `find KEYWORD`

Example usage: `find book`

Expected outcome:

<img src="https://user-images.githubusercontent.com/65069982/93290752-024f3080-f814-11ea-8562-0a2a2f83f209.png" height="150">

### Finding deadlines/events occuring on a specific date: `date`

You can find deadlines/events that occur on the inputted date from the list of tasks.

Format: `date DATE`

Example usage: `date 2020-06-06`

Expected outcome:

<img src="https://user-images.githubusercontent.com/65069982/93291787-a043fa80-f816-11ea-82e8-6d21e1f92d21.png" height="150">

### Obtaining statistics about managed tasks: `stats`

You can check how many tasks you have completed in this session. Milk will also tell you the number of uncompleted tasks in your list of tasks, only if you still have uncompleted tasks.

Format: `stats`

Example usage: `stats`

Expected outcome:

<img src="https://user-images.githubusercontent.com/65069982/93291952-fa44c000-f816-11ea-9b4d-ac55a11c0590.png" height="150">

### Exiting the program: `bye`

You can exit the application. The application closes **3 seconds** after the command is called.

Format: `bye`

Example usage: `bye`

Expected outcome:

<img src="https://user-images.githubusercontent.com/65069982/93292151-6fb09080-f817-11ea-9e45-6ec2b9e66069.png" height="150">

The application closes after 3 seconds.

## Command Summary

The following table summarizes the commands of the application:

Action | Format
---|---
Add todo | `todo DESCRIPTION`
Add deadline | `deadline DESCRIPTION /by DATE`
Add event | `event DESCRIPTION /at DATE`
List tasks | `list`
Mark task as done  | `done INDEX`
Delete a task | `delete INDEX`
Find tasks | `find KEYWORD`
Find deadlines/events | `date DATE`
Obtain statistics | `stats`
Exit | `bye`

## Troubleshooting

When interacting with Milk, some errors may occur and Milk would change her expression accordingly as shown:

<img src="https://user-images.githubusercontent.com/65069982/93308849-63d3c700-f835-11ea-8efe-1e786f744603.png" height="100">

The following table displays the error messages that you may face:

Message | What to do
---|---
Harh? The description of a task cannot be empty. | Include the `DESCRIPTION` parameter when adding a task.
Harh? The task does not have a date/time attached. | Include the `DATE` parameter when adding a deadline/event.
Harh? The format of the date given is invalid. | Modify your `DATE` parameter into YYYY-MM-DD format.
Harh? There isn't a task index inputted. | Include the `INDEX` parameter when marking tasks as done or when deleting tasks.
Harh? This task index does not exist in your list. | Modify your `INDEX` parameter to an index that is found in your list.
Harh? I don't know what that means. | Use a valid Milk command.



