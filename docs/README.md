---
title: User Guide
---

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `duke.jar` from [here](https://github.com/).

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will show all the commands and how to use them.<br>
   Some example commands you can try:

   * **`list`** : Lists all tasks.

   * **`todo`**`read book` : Adds a `todo` with a description of `read book` to the Task List.

   * **`delete`**`3` : Deletes the 3rd task shown in the current list.

   * **`done 3`** : Marks the 3rd task shown in the current list as done.

   * **`exit`** : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `todo DESC`, `DESC` is a parameter which can be used as `todo read book`.

* Items in square brackets are optional.<br>
  e.g `find KEYWORD [MORE_KEYWORDS]` can be used as `find book` or as `find book clubs`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[MORE_KEYWORDS]…​` can be used as ` ` (i.e. 0 times), `book`, `book clubs` etc.

</div>

| Feature name          | Description                                                               | Format
|:---                   | :---                                                                      | :---
|View help              | Show all the commands the user can used                                   | `help`
|Add todo               | Adds a new todo task with the given `DESCRIPTION`                         | `todo DESC`
|Add deadline           | Adds a new deadline task with the given `DESCRIPTION`, `DATE` and `TIME`  | `deadline DESC /by DD/MM/YYYY HHmm`
|Add event              | Adds a new event task with the given `DESCRIPTION`, `DATE` and `TIME`     | `event DESC /at DD/MM/YYYY HHmm`
|Mark task as done      | Mark the specific task index, `INDEX` from the current list as done       | `done INDEX`
|Delete task            | Deletes the specified task `INDEX` from the current list                  | `delete INDEX`
|Find tasks             | Finds and shows all tasks with `KEYWORD` in `DESCRIPTION`                 | `find KEYWORD [MORE_KEYWORDS]...`
|List all tasks         | Lists all the tasks and show their relevant information                   | `list`
|Show statistics        | Shows the number of tasks completed against the total number of tasks     | `stat`
|Exit the application   | Terminates and closes the application                                     | `exit`

## Usage

### Viewing help: `help`

Shows a message explaining how to use each command.

Example of usage: `help`

Expected outcome:

```
--- Commands ---
todo, deadline, event, done, delete, find, list, stat, exit

todo :
Adds a todo to the task list.
  Parameters: DESC
  Example: todo read book

deadline :
Adds a deadline to the task list.
  Parameter: DESC /by DATE TIME
  Example: deadline return book /by 23/09/2020 1800

...
```

### Adding a todo : `todo`

Adds a todo to the task list.

Format: `todo DESC`

Example of usage: `todo read book`

Expected outcome:

```
Got it. I've added this task:
    [T][X] read book
Now you have 7 tasks in the list.
```

### Adding a deadline : `deadline`

Adds a deadline to the task list.

Format: `deadline DESC /by DD/MM/YYYY HHmm`

Example of usage: `deadline return book /by 23/09/2020 1800`

Expected outcome:

```
Got it. I've added this task:
    [D][X] return book (by: Wed, 23 Sep 2020 06:00 PM)
Now you have 8 tasks in the list.
```

### Adding an event : `event`

Adds an event to the task list.

Format: `event DESC /at DATE TIME`

Example of usage: `event project meeting /at 05/09/2020 1430`

Expected outcome:

```
Got it. I've added this task:
    [E][X] project meeting (at: Sat, 05 Sep 2020 02:30 PM)
Now you have 7 tasks in the list.
```

### Done a task : `done`

Mark the specific task from the current task list as done.

Format: `done INDEX`
*   Marks the task at the specified `INDEX` as done.
*   The index refers to the index number shown in the displayed task list.
*   The index **must be a positive integer** 1, 2, 3, …​

Example of usage: `done 7`

Expected outcome:

```
Nice! I've mark this task as done:
    [E][O] project meeting (at: Sat, 05 Sep 2020 02:30 PM)
```

### Deleting a task : `delete`

Deletes the specified task from the current task list.

Format: `delete INDEX`

* Deletes the task at the specified `INDEX`.
* The index refers to the index number shown in the displayed task list.
* The index **must be a positive integer** 1, 2, 3, …​

Example of usage: `delete 2`

Expected outcome:

```
Got it. I've deleted this task:
    [E][O] project meeting (at: Sat, 05 Sep 2020 02:30 PM)
Now you have 8 tasks in the list.
```

### Locating tasks by descriptions: `find`

Finds tasks whose descriptions contain any of the given keywords.

Format: `find KEYWORD [MORE_KEYWORDS]...`

* The search is case-sensitive. e.g `read` will not match `Read`
* The order of the keywords does not matter. e.g. `Read Book` will match `Book Read`
* Only the description is searched.
* Only full words will be matched e.g. `Book` will not match `Books`
* Tasks matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `read club` will return `read book`, `join sports club`

Examples of usage:
* `find book` returns `read book` and `return book`
* `find book club` returns `read book`, `join sports club`

Expected outcome:
```
1. [T][O] read book
2. [D][X] return book
Stormy have found 2 task(s) related to your keyword(s).
```

### Listing all tasks : `list`

Shows a list of all the tasks in the task list.

Format: `list`


### Viewing statistics : `stat`

Displays the number of tasks that have been completed.

Format: `stat`

Expected outcome:
```
You have completed 4 out of 8 tasks. Keep it up!!
```

### Exiting the program : `exit`

Exits the program.

Format: `exit`

