# User Guide

Mushy is a **Personal Assistant Chatbot for keeping track 
of tasks, optimized for use via a Command Line Interface** 
(CLI) while still having the benefits of a Graphical User 
Interface (GUI). If you can type fast, Mushy can get your 
tasks management done faster than traditional GUI apps.

- [Quick start](#quick-start)
- [Features](#features)
    * [Viewing help: `help`](#viewing-help-help)
    * [Adding a todo task: `todo`](#adding-a-todo-task-todo)
    * [Adding a deadline task: `deadline`](#adding-a-deadline-task-deadline)
    * [Adding an event task: `event`](#adding-an-event-task-event)
    * [Listing all tasks: `list`](#listing-all-tasks-list)
    * [Marking a task as done: `done`](#marking-a-task-as-done-done)
    * [Locating tasks by name: `find`](#locating-tasks-by-name-find)
    * [Deleting a task: `delete`](#deleting-a-task-delete)
    * [Exiting the program: `bye`](#exiting-the-program-bye)
    * [Saving the data](#saving-the-data)
- [FAQ](#faq)
- [Command summary](#command-summary)

---

## Quick start
1. Ensure you have Java `11` or above installed in your Computer.
2. Download the latest `Mushy.jar` from [here](https://github.com/GabrielTeo/ip/releases/tag/A-Release)
3. Copy the file to the folder you want to use as the *home folder* for your Mushy program.
4. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds.  

![welcome page](/docs/welcome.png)  

5. Type the command in the command box and press Enter to execute it. 
e.g. typing `help` and pressing Enter will display a list of commands.  
Some example commands you can try:
   * **`list`** : Lists all tasks.
   * **`todo`**`meet friend` : 
   Adds a todo task named `meet friend` to the task list.
   * **`delete`**`3` : Deletes the 3rd task shown in the current list.
   * **`bye`** : Exits the app.
6. Refer to the [Features](#Features) below for details of each command.

---

## Features
> ℹ️  **Notes about the command format:**
> - Words in `UPPER_CASE` are the parameters to be supplied by the user.  
> e.g. in `todo DESCRIPTION`, `DESCRIPTION` is a parameter which can be used as `todo meet friend`.
> - Items in square brackets are optional.  
> e.g. `deadline DESCRIPTION /by DATE [TIME]` can be used as `deadline book report /by 15/9/2020` or as `deadline book report /by 15/9/2020 2359`
> - Items with `...` after them can be used one or multiple times.  
> e.g. `find KEYWORD...` can be used as `find read` or `find read book`.
> - Order of parameters must be fixed.  
> e.g. if the command specifies `event DESCRIPTION /at PERIOD`, `event /at PERIOD DESCRIPTION` is **not** acceptable.

### Viewing help: `help`
Shows a message displaying all the commands.  

![help page](/docs/help.png)  

Format: `help`  

Example of usage:  
`help`

### Adding a todo task: `todo`
Adds a todo task to the list of tasks.  
Format: `todo DESCRIPTION`  

Example of usage:  
`todo read book`  
 
Expected outcome:  
`Got it. I've added this task:`  
`[T][✘] read book`  
`Now you have 3 tasks in the list.`  

![todo page](/docs/todo.png)

### Adding a deadline task: `deadline`
Adds a deadline task to the list of tasks.  
Format: `deadline DESCRIPTION /by DATE [TIME]`
 
> ⚠️  `DATE [TIME]` parameters have to be specified in the following format:

Format | Example
------ | -------
dd/MM/yyyy | 15/9/2020
dd/MM/yyyy HHmm | 15/9/2020 2359
 
Example of usage:  
`deadline CS2103T tutorial /by 13/9/2020 2359`  

Expected outcome:  
`Got it. I've added this task:`  
`[D][✘] CS2103T tutorial (by: Sep 13 2020 1159 PM`  
`Now you have 4 tasks in the list.`

### Adding an event task: `event`
Adds an event task to the list of tasks.  
Format: `event DESCRIPTION /at PERIOD`  

Example of usage:  
`event project meeting /at Aug 6th 2-4pm`  

Expected outcome:  
`Got it. I've added this task:`  
`[E][✘] project meeting (at: Aug 6th 2-4pm)`  
`Now you have 5 tasks in the list.`

### Listing all tasks: `list`
Shows a list of all tasks.  
Format: `list`  

Example of usage:  
`list`  
 
Expected outcome:  
`Here are the tasks in your list:`  
`1. [T][✓] borrow book`  
`2. [T][✓] read book`  
`3. [D][✘] return book (by: Jun 6 2020)`  
`4. [E][✘] project meeting (at: Aug 6th 2-4pm)`  

![list page](/docs/list.png)

### Marking a task as done: `done`
Marks a task as done.  
Format: `done INDEX`
- Marks the task at the specified `INDEX` as done.
- The index refers to the index number shown in the displayed task list.
- The index **must be a positive integer** 1, 2, 3, ...
 
Example of usage:  
`done 4`  

Expected outcome:  
`Nice! I've marked this task as done:`  
`[✓] project meeting`  

![done page](/docs/done.png)

### Locating tasks by name: `find`
Finds tasks whose description contain any of the given keywords.  
Format: `find KEYWORD...`
- The search is case-insensitive. e.g. `book` will match `Book`
- The order of the keywords does not matter. e.g. `read book` will match `book read`
- Only the task description is searched.
- Only full words will be matched. e.g. `boo` will not match `book`
- Only tasks matching all keywords will be returned.
 
Example of usage:  
`find book`  
 
Expected outcome:  
`Here are the matching tasks in your list:`  
`1. [T][✓] borrow book`  
`2. [T][✓] read book`  
`3. [D][✘] return book (by: Jun 6 2020)`  

![find page](/docs/find.png)

### Deleting a task: `delete`
Deletes the specified task.  
Format: `delete INDEX`
- Deletes the task at the specified `INDEX`.
- The index refers to the index number shown in the displayed task list.
- The index **must be a positive integer** 1, 2, 3, ...
 
Example of usage:  
`delete 1`  
 
Expected outcome:  
`Noted. I've removed this task:`  
`[T][✓] borrow book`  
`Now you have 7 tasks in the list.`  

![delete page](/docs/delete.png)

### Exiting the program: `bye`
Exits the program.  
Format: `bye`

### Saving the data
Mushy data are saved in the hard disk automatically after any command that changes the data.  
There is no need to save manually.

---

## FAQ
**Q**: How do I transfer my data to another computer?  
**A**: Install the app in the other computer and overwrite the empty 
data file it creates with the file that contains the data of your 
previous Mushy home folder.

---

## Command summary
Action | Format | Examples
------ | ------ | --------
**Help** | `help`
**Todo** | `todo DESCRIPTION` | `todo read book`
**Deadline** | `deadline DESCRIPTION /by DATE [TIME]` | `deadline CS2103T tutorial /by 13/9/2020 2359`
**Event** | `event DESCRIPTION /at PERIOD` | `event project meeting /at Aug 6th 2-4pm`
**List** | `list`
**Done** | `done INDEX` | `done 4`
**Find** | `find KEYWORD...` | `find book`
**Delete** | `delete INDEX` | `delete 1`
**Bye** | `bye`
