# User Guide

Mushy is a **Personal Assistant Chatbot for keeping track 
of tasks, optimized for use via a Command Line Interface** 
(CLI) while still having the benefits of a Graphical User 
Interface (GUI). If you can type fast, Mushy can get your 
tasks management done faster than traditional GUI apps.

- [Quick start](#quick-start)
- [Features](#features)
    * [Viewing help: `help`](#viewing-help)
    * [Adding a todo task: `todo`](#adding-a-todo-task-todo)
    * [Adding a deadline task: `deadline`](#adding-a-deadline-task-deadline)
    * [Adding an event task: `event`](#adding-an-event-task-event)
    * [Listing all tasks: `list`](#listing-all-tasks-list)
    * [Locating tasks by name: `find`](#locating-tasks-by-name-find)
    * [Marking a task as done: `done`](#marking-a-task-as-done-done)
    * [Deleting a task: `delete`](#deleting-a-task-delete)
    * [Exiting the program: `bye`](#exiting-the-program-bye)
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

### Adding a todo task: `todo`
Adds a todo task to the list of tasks.  
Format: `todo DESCRIPTION`

Example of usage:
* `todo read book`  
 
Expected outcome:  
`Got it. I've added this task:`  
`[T][✘] read book`  
`Now you have 3 tasks in the list.`

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

## Usage

### `Keyword` - Describe action

Describe action and its outcome.

Example of usage: 

`keyword (optional arguments)`

Expected outcome:

`outcome`
