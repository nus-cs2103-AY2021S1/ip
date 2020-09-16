# User Guide

Milk is a **desktop app for managing tasks, optimised for use via a command line interface** (CLI)
while still having the benefits of a Graphical User Interface (GUI).
If you can type fast, Milk can manage your tasks faster than traditional GUI apps.

1. [Quick start](#quick-start)
1. [Features](#features)
    1. [Adding a todo: `todo`](#adding-a-todo-todo)
    1. [Adding a deadline: `deadline`](#adding-a-deadline-deadline)
    1. [Adding an event: `event`](#adding-an-event-event)
    1. [Listing all tasks: `list`](#listing-all-tasks-list)
    1. [Marking a task as done: `done`](#marking-a-task-as-done-done)
    1. [Deleting a task: `delete`](#deleting-a-task-delete)
    1. [Finding tasks: `find`](#finding-tasks-find)
    1. [Finding deadlines/events occuring on a specific date: `date`](#finding-deadlinesevents-occuring-on-a-specific-date-date)
    1. [Obtaining statistics about managed tasks: `stats`](#obtaining-statistics-about-managed-tasks-stats)
    1. [Exiting the program: `bye`](#exiting-the-program-bye)
1. [FAQ](#faq)
1. [Command summary](#command-summary)
1. [Troubleshooting](#troubleshooting)

---

## Quick start

1. Ensure that you have Java `11` or above installed in your Computer.
1. Download the latest `Milk.jar` from [here](https://github.com/successs404/ip/releases/tag/A-Release).
1. Copy the file to the folder you want to use as the *home folder* for your Milk.
1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds.
Note how Milk greets you with a welcome message.

   <img src="https://user-images.githubusercontent.com/65069982/93287632-90bfb400-f80c-11ea-85f1-7bf18d20bc34.png" height="300">
   
1. Type the command in the command box and press Enter to execute it. <br>
Some example commands you can try:
    * `todo read book`: Adds a todo with `read book` description to the list of tasks.
    * `list`: Lists all tasks.
    * `done 1`: Marks the 1st task shown in the list of tasks as done.
    * `delete 3`: Deletes the 3rd task shown in the list of tasks.
    * `bye`: Exits the app.
1. Refer to the [Features](#features) below for details of each command.

---

## Features

> ℹ️ **Notes about the command format:**
>* Commands are case-sensitive.
>* Words in `UPPER_CASE` are the parameters to be supplied by the user. <br>
>e.g. in `todo DESCRIPTION`, `DESCRIPTION` is a parameter which can be used as `todo read book`
>* Items in square brackets are optional. <br>
>e.g. `find KEYWORD [MORE_KEYWORDS]` can be used as `find read` or as `find read book`
>* The order of parameters is fixed.

### Adding a todo: `todo`

Adds a todo to the list of tasks.

Format: `todo DESCRIPTION`

Examples:
* `todo read book`
* `todo swim`

### Adding a deadline: `deadline`

Adds a deadline to the list of tasks.

Format: `deadline DESCRIPTION /by DATE`

> ⚠ `DATE` should be in **YYYY-MM-DD** format.

Examples:
* `deadline return book /by 2020-06-06`
* `deadline assignment /by 2020-09-18`

### Adding an event: `event`

Adds an event to the list of tasks.

Format: `event DESCRIPTION /at DATE`

Examples:
* `event project meeting /at 2020-08-06`
* `event concert /at 2020-10-10`

### Listing all tasks: `list`

Shows the list of tasks that are being managed.

Format: `list`

### Marking a task as done: `done`

Marks a task from the list of tasks as done.

Format: `done INDEX`
* Marks the task at the specified `INDEX`. The index refers to the index number shown in the list of tasks.
The index **must be a positive integer** 1, 2, 3, ...

Examples:
* `done 1` marks the 1st task in a list that contains at least 1 task as done.
* `done 3` marks the last task in a list that contains 3 tasks as done.

### Deleting a task: `delete`

Deletes an existing task from the list of tasks.

Format: `delete INDEX`
* Deletes the task at the specified `INDEX`. The index refers to the index number shown in the list of tasks.
The index **must be a positive integer** 1, 2, 3, ...

Examples:
* `delete 1` deletes the 1st task from a list that contains at least 1 task.
* `delete 3` deletes the last task from a list that contains 3 tasks.

### Finding tasks: `find`

Find tasks which name contains any of the given keywords.

Format: `find KEYWORD [MORE_KEYWORDS]`
* The search is case-sensitive. e.g. `Book` will not match `book`
* The order of the keywords matter. e.g. `book read` will not match `read book`
* Partial words can be matched. e.g. `b` will match `book`
* Tasks matching at least one keyword will be returned. e.g. `book` will return `book`, `read book`

Examples:
* `find s` returns `swim`, `swimming`
* `find book` returns `read book`, `return book`

<img src="https://user-images.githubusercontent.com/65069982/93290752-024f3080-f814-11ea-8562-0a2a2f83f209.png" height="150">

### Finding deadlines/events occuring on a specific date: `date`

Find deadlines/events that occur on the given date.

Format: `date DATE`

Example:
* `date 2020-06-06`

<img src="https://user-images.githubusercontent.com/65069982/93291787-a043fa80-f816-11ea-82e8-6d21e1f92d21.png" height="150">

### Obtaining statistics about managed tasks: `stats`

Shows the statistics of the tasks managed.

Format: `stats`
* The number of tasks that you have completed in this session will be shown. A session starts when the application is launched and ends when the application is closed.
* The number of uncompleted tasks in your list of tasks will be shown **only if** you still have uncompleted tasks.

Example: `stats`

<img src="https://user-images.githubusercontent.com/65069982/93291952-fa44c000-f816-11ea-9b4d-ac55a11c0590.png" height="150">

### Exiting the program: `bye`

Exits the program.

> ℹ️The application closes **3 seconds** after the command is called.

Format: `bye`

---

## FAQ

**Q:** How do I transfer by data to another Computer?

**A:** Install the app in the other computer and overwrite the empty data file it creates
with the file that contains the data of your previous Milk home folder.

---

## Command summary

**Action** | **Format, Examples**
---|---
**Add** | `todo DESCRIPTION` e.g. `todo read book` <br> `deadline DESCRIPTION /by DATE` e.g. `deadline return book /by 2020-06-06` <br> `event DESCRIPTION /at DATE` e.g. `event project meeting /at 2020-08-06`
**List** | `list`
**Done**  | `done INDEX` e.g. `done 1`
**Delete** | `delete INDEX` e.g. `delete 3`
**Find** | `find KEYWORD` e.g. `find book`
**Date** | `date DATE` e.g. `date 2020-06-06`
**Statistics** | `stats`
**Exit** | `bye`

---

## Troubleshooting

When interacting with Milk, some errors may occur and Milk would change her expression as shown:

<img src="https://user-images.githubusercontent.com/65069982/93308849-63d3c700-f835-11ea-8efe-1e786f744603.png" height="100">

The following table displays the error messages that you may face and how you can solve the errors:

Message | What to do
---|---
Harh? The description of a task cannot be empty. | Include the `DESCRIPTION` parameter when adding a task.
Harh? The task does not have a date/time attached. | Include the `DATE` parameter when adding a deadline/event.
Harh? The format of the date given is invalid. | Modify your `DATE` parameter into YYYY-MM-DD format.
Harh? There isn't a task index inputted. | Include the `INDEX` parameter when marking tasks as done or when deleting tasks.
Harh? This task index does not exist in your list. | Modify your `INDEX` parameter to an index that is found in your list.
Harh? I don't know what that means. | Use a valid Milk command.
