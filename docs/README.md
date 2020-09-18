# Duke-it Task Manager User Guide

Duke-it is a desktop application for managing tasks by typing commands, while also having a Graphical User Interface (GUI).

<p align="center">
  <img src="Ui.png" width="50%" height="50%" alt="">
</p>

* [Features](#features)
* [Feature Details](#feature-details)
* [Quick start](#quick-start)

## Features
1. [**Bye**](#1-bye) - exits the application
1. [**Deadline**](#2-deadline) - creates a new deadline task
1. [**Delete**](#3-delete) - deletes a task
1. [**Done**](#4-done) - marks a task as complete
1. [**Event**](#5-event) - creates a new event task
1. [**Find**](#6-find) - finds task containing given keywords
1. [**Help**](#7-help) - lists all available commands
1. [**List**](#8-list) - lists all tasks
1. [**Todo**](#9-todo) - creates a new todo task
1. [**Sort**](#10-sort) - sorts the task list by oldest date first

## Feature Details

### 1. Bye

Exits the application.

**Usage**

### `bye` 

Example of usage: 

```
bye
```

Expected outcome:

```
Bye. Hope to see you again soon!
``` 
and subsequently the app closes.

---

### 2. Deadline

Creates a new deadline task with the associated due date. The specified date must follow the format `dd-MM-yyyy HHmm`.

**Usage**

### `deadline <DESCRIPTION> /by <DATE>` 

Example of usage: 

```
deadline Math homework /by 20-09-2020 2359
```

Expected outcome:

```
I've added this task:
[D][✘] Math homework (by: 20 Sep 2020 23:59)
Now you have 1 tasks in the list
```

---

### 3. Delete

Deletes a task at an index. The specified index must be in the range of the task list size.

**Usage**

### `delete <INDEX>` 

Example of usage: 

```
delete 1
```

Expected outcome:

```
Noted. I've removed this task:
[D][✘] Math homework (by: 20 Sep 2020 23:59)
Now you have 4 tasks in the list.
```

---

### 4. Done

Marks a task at an index as completed. The specified index must be in the range of the task list size.

**Usage**

### `done <INDEX>` 

Example of usage: 

```
done 4
```

Expected outcome:

```
Nice! I've marked this task as done:
[T][✓] Buy groceries
```

---

### 5. Event

Creates a new event task with the associated event date. The specified date must follow the format `dd-MM-yyyy HHmm`.

**Usage**

### `event <DESCRIPTION> /at <DATE>` 

Example of usage: 

```
event Birthday /at 06-05-2020 0000
```

Expected outcome:

```
I've added this task:
[E][✘] Birthday (at: 06 May 2020 00:00)
Now you have 5 tasks in the list.
```

---

### 6. Find

Finds and displays tasks containing given keywords. Keywords is case sensitive. Multiple keywords can be specified using ~ as a separator. 

**Usage**

### `find <KEYWORDS>` 

Example of usage: 

```
find Math ~ Clean ~ Medicine
```

Expected outcome:

```
Here are the tasks in your list that contain
"Math"
"Clean"
"Medicine"

1. [E][✓] Math exam (at: 20 Nov 2020 19:00)
2. [T][✘] Clean room
```

---

### 7. Help

Displays all available commands.

**Usage**

### `help` 

Example of usage: 

```
help
```

Expected outcome:

```
Here are all the available commands:
bye
deadline <description> /by <date>
delete <task index>
done <task index>
event <description> /at <date>
find <keywords separated by ~>
help
list
todo <description>
sort
```

---

### 8. List

List all tasks.

**Usage**

### `list` 

Example of usage: 

```
list
```

Expected outcome:

```
1.[E][✓] Math exam (at: 20 Nov 2020 19:00)
2.[T][✘] Clean room
3.[D][✘] Homework (by: 14 Sep 2020 23:59)
4.[T][✘] Buy groceries
5.[E][✘] Birthday (at: 06 May 2020 00:00)
```

---

### 9. Todo

Creates a new todo task without any specified date.

**Usage**

### `todo <DESCRIPTION>` 

Example of usage: 

```
todo Buy groceries
```

Expected outcome:

```
I've added this task:
[T][✘] Buy groceries
Now you have 6 tasks in the list.
```

---

### 10. Sort

Sorts the task list by earliest date first. If the task has no date (todo tasks), they are placed at the end.

**Usage**

### `sort` 

Example of usage: 

```
list
sort
list
```

Expected outcome:

```
1.[E][✓] Math exam (at: 20 Nov 2020 19:00)
2.[T][✘] Clean room
3.[D][✘] Homework (by: 14 Sep 2020 23:59)
4.[T][✘] Buy groceries
5.[E][✘] Birthday (at: 06 May 2020 00:00)

Your task list has been sorted!

1.[E][✘] Birthday (at: 06 May 2020 00:00)
2.[D][✘] Homework (by: 14 Sep 2020 23:59)
3.[E][✓] Math exam (at: 20 Nov 2020 19:00)
4.[T][✘] Clean room
5.[T][✘] Buy groceries
```

---


## Quick Start
1. Ensure you have [Java ```11```](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html) or above installed on your computer.
2. Download the latest [`duke.jar`](https://github.com/aizatazhar/ip/releases).
3. Copy the file to the folder you wish to use as the home folder.
4. Double-click the file to start the app
5. Type your command into the box and press enter to execute it.
