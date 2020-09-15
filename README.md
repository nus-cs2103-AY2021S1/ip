# User Guide

Duke Task Tracker is a **desktop java application for managing your tasks, designed to be used via its Command Line Interface** (CLI) while still having the advantages of a Graphical User Interface (GUI). If you can type fast, Duke Task Tracker can get your task management done faster than traditional GUI applications.

* [Features](#features)
  * [Adding a basic task : **`todo`**](#adding-a-basic-task--todo)
  * [Adding a task with a deadline : **`deadline`**](#adding-a-task-with-a-deadline--deadline)
  * [Adding a task with a date : **`event`**](#adding-a-task-with-a-date--event)
  * [Listing all tasks : **`list`**](#listing-all-tasks--list)
  * [Setting a task as done  : **`done`**](#setting-a-task-as-done--done)
  * [Deleting a task : **`delete`**](#deleting-a-task--delete)
  * [Finding a task : **`find`**](#finding-a-task--find)
  * [Exiting the program :  **`bye`**](#exiting-the-program--bye)
  * [Saving the data ](#saving-the-data)
  
----------------------

## Features

- words in `<....>` are the user input.
- items that have `/` before them are keywords for that command

### Adding a basic task : `todo`

Adds a task that only has a task description and priority to the list of tasks.

Format: `todo \<priority> \<task description>`

Examples:

Input:
```
todo LOW Buy bread
```
Expected Outcome:
```
Affirmative. I've added this task:
[T][✘] {LOW} Buy bread
Now you have 1 tasks in the list.
```

Input:
```
todo MEDIUM Write reflection
```
Expected Outcome:
```
Affirmative. I've added this task:
[T][✘] {MEDIUM} Write reflection
Now you have 2 tasks in the list.
```

Notes:

- `\<priority>` can be any of the following: `LOW`, `MEDIUM` or `HIGH` 

### Adding a task with a deadline : `deadline`

Adds a task that has a priority and a deadline associate with it.

Format: `deadline \<priority> \<task description> /by \<deadline date description>`

Examples:

Input:
```
deadline HIGH Submit report /by 2020-06-05
```
Expected Outcome:
```
Affirmative. I've added this task:
[D][✘] {HIGH} Submit report (by: Jun 05 2020)
Now you have 3 tasks in the list.
```

Input:
```
deadline LOW Complete essay /by Tomorrow
```
Expected Outcome:
```
Affirmative. I've added this task:
[D][✘] {LOW} Complete essay (by: Tomorrow)
Now you have 4 tasks in the list.
```

Notes:

- `\<priority>` can be any of the following: `LOW`, `MEDIUM` or `HIGH` 
- `\<deadline date description>` can either be text or a date in YYYY-MM-DD format.

### Adding a task with a date : `event`

Adds a task that has an event date and time associated with it.

Format: `event \<priority> \<task description> /at \<event date-time description>`

Examples:

Input:
```
event HIGH House party /at 2020-09-18
```
Expected Outcome:
```
Affirmative. I've added this task:
[E][✘] {HIGH} Complete essay (at: Sep 18 2020)
Now you have 5 tasks in the list.
```

Input:
```
event LOW Take out the trash /at Tonight 10pm
```
Expected Outcome:
```
Affirmative. I've added this task:
[E][✘] {LOW} Take out the trash (at: Tonight 10pm)
Now you have 6 tasks in the list.
```

Notes:

- `\<priority>` can be any of the following: `LOW`, `MEDIUM` or `HIGH` 
- `\<event date-time description>` can either be text or a date in YYYY-MM-DD format.

### Listing all tasks : `list`

Shows a list of all tasks currently in the task list.

Format: `list`

Expected Outcome:
```
Here are your tasks for today:
1.[T][✘] {LOW} Buy bread
2.[T][✘] {MEDIUM} Write reflection
3.[D][✘] {HIGH} Submit report (by: null)
4.[D][✘] {LOW} Complete essay (by: Tomorrow)
5.[E][✘] {HIGH} House party (at: null)
6.[E][✘] {LOW} Take out the trash (at: Tonight 10pm)
```

### Setting a task as done  : `done`

Marks an existing task as done.

Format: `done \<task number>`

Examples:

Input:
```
done 1
```
Expected Outcome:
```
Good job! I've marked this task as done:
[T][✓] {LOW} Buy bread
```

Input:
```
done 2
```
Expected Outcome:
```
Good job! I've marked this task as done:
[T][✓] {MEDIUM} Write reflection
```


Notes:

- `\<task number>` is the number that appears on the left of a task when the list of tasks is displayed using `list`.

###  Deleting a task : `delete`

Deletes a task from the task list.

Format: `delete \<task number>`

Examples:

Input:
```
delete 2
```
Expected Outcome:
```
Of course sir. I have removed this task:
  [T][✓] {MEDIUM} Write reflection
Now you have 5 tasks in the list.
```

Input:
```
delete 4
```
Expected Outcome:
```
Of course sir. I have removed this task:
  [E][✘] {HIGH} House party (at: null)
Now you have 4 tasks in the list.
```

Notes:

- `\<task number>` is the number that appears on the left of a task when the list of tasks is displayed using `list`.

### Finding a task : `find`

Finds a task in the list that matches the keywords

Format: `find \<search keyword>`

Examples:

Input:
```
find bread
```
Expected outcome:
```
Here are your tasks for today:
1.[T][✓] {LOW} Buy bread
```

Input:
```
find report
```
Expected Outcome:
```
Here are your tasks for today:
1.[D][✘] {HIGH} Submit report (by: null)
```

### Exiting the program :  `bye`

Exits the programme

Format: `bye`

Expected outcome:
```
Guess its time for us to part ways
Thanks for the memories
:`(
```

### Saving the data

Duke Task Tracker automatically saves data in the local storage every time a change is made.

----------------------

## Command Summary

|          Action          |                          Format                          |
| :----------------------: | :------------------------------------------------------: |
|           Help           |                         `--help`                         |
|      Add Basic Task      |                `todo <task description>`                 |
|  Add Task with Deadline  | `deadline <task description> /by <deadline description>` |
| Add Task with Event Date | `event <task description> /at <event date description>`  |
|           List           |                          `list`                          |
|           Done           |                   `done <task number>`                   |
|          Delete          |                  `delete <task number>`                  |
|          Remind          |                  `remind <task number>`                  |
|          Search          |           `search <keyword1 keyword2 etc...>`            |
|        getEvents         |                    `getEvents <date>`                    |
|       getReminders       |                      `getReminders`                      |
|           Exit           |                          `bye`                           |
