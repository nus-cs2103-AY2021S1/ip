# User Guide

Duke Tracker is a **desktop app for managing tasks, optimized for use via a Command Line Interface** (CLI) while still having the advantages of a Graphical User Interface (GUI). If you can type fast, Duke Tracker can help your task management done faster than traditional GUI applications. 

* [Features](#features)
    * [Listing all tasks : **`list`**](#listing-all-tasks--list)
    * [Adding a task : **`todo`**](#adding-a-task--todo)
    * [Adding a task (with a deadline) : **`deadline`**](#adding-a-task-with-a-deadline--deadline)
    * [Adding a task (with a specified date) : **`event`**](#adding-a-task-with-a-specified-date--event)
    * [Deleting a task : **`delete`**](#deleting-a-task--delete) 
    * [Marking a task as done  : **`done`**](#marking-a-task-as-done--done) 
    * [Finding tasks : **`find`**](#finding-tasks--find)
    * [Exiting the application :  **`bye`**](#exiting-the-application--bye)
    * [Saving data](#saving-data)

## Features 
- Words in `<....>` are the user input.
- Items that have `/` before them are keywords for that command

### Listing all tasks : **`list`**
Shows a list of tasks in the current task list.  
Format: `list`

### Adding a task : **`todo`**
Adds a new todo task.  
Format: `todo <TASK_DESCRIPTION>`  
* Tasks with duplicated \<TASK_DESCRIPTION> are not allowed.  

Examples:  
- **`todo CS2103T Quiz 5`** Creates a todo task with description "CS2103T Quiz 5"  

### Adding a task (with a deadline) : **`deadline`**
Adds a new task with a deadline.  
Format: `deadline <TASK_DESCRIPTION> /by <DEADLINE_DESCRIPTION>`  
* \<DEADLINE_DESCRIPTION> can either be a string or a date in YYYY-MM-DD format.  
* Tasks with duplicated \<TASK_DESCRIPTION> are not allowed.  

Examples:  
- **`deadline return book /by this Sunday`**
- **`deadline CS2100 Assignment 1 /by 2020-09-18`**

### Adding a task (with a specified date) : **`event`**
Adds a new task with a specified date.  
Format: `event <TASK_DESCRIPTION> /by <DATE_DESCRIPTION>`  
* \<DATE_DESCRIPTION> can either be a string or a date in YYYY-MM-DD format.  
* Tasks with duplicated \<TASK_DESCRIPTION> are not allowed. 

Examples:  
- **`event return book /at this Sunday`**
- **`event CS2100 Assignment 1 /at 2020-09-18`**

### Deleting a task : **`delete`**
Deletes a task at the specific index from the task list.  
Format: `delete <TASK_INDEX>`  
- \<TASK_INDEX> is the index that appears on the left of a task when the task list is shown.  

Examples:
- `delete 3` Deletes task at index 3
- `delete 6` Deletes task at index 6

### Marking a task as done : **`done`**
Marks a task at the specific index from the task list as finished/done.  
Format: `done <TASK_INDEX>`
- \<TASK_INDEX> is the index that appears on the left of a task when the task list is shown.  

Examples:
- `done 2` Marks task at index 2 as done/finished
- `done 5` Marks task at index 5 as done/finished

### Finding tasks : **`find`**
Finds all the tasks in the task list that match the given querystring.  
Format: `find <QUERY_STRING>`
* Returns tasks of which \<TASK_DESCRIPTION> contains \<QUERY_STRING> as a substring.  
* \<QUERY_STRING> is case-insensitive.  

Examples:
* `find CS2100 Assignment` Shows a list of tasks of which \<TASK_DESCRIPTION> contains "CS2100 Assignment" as substring

### Exiting the application : `bye`

Exits and closes the application.  

###  Saving data
Duke Tracker automatically saves data into hard disk (.txt file) everytime any changes are made.


