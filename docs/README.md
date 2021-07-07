# Duke User Guide

## Introduction
Duke is a personal task manager. It is a simple application that help you keep track of your tasks.

## User interface
![UI](Ui.png)

## Features 

1. Add tasks : add a task to task list.
    1. **`todo`**: adds a *todo* task to the list.
    2. **`deadline`**: adds a *deadline* task to the list.
    3. **`event`**: adds an *event* task to the list.
2.  **`list`**: lists all tasks in the list.
3. **`done`**: marks a task as done.
4. **`delete`**: deletes a task.
5.  **`find`**: finds all the task that matches the given keyword.
6.  **`tag`**: tags a task with the given description.
7.  **`bye`**: exit the application.

## Usage

### 1a `todo DESCRIPTION` - Adds a *todo* task.

Adds a *todo* task to the list.

Example of usage: 
```
todo borrow book
```

Expected outcome:

```
Got it. I've added this task:
[T][✘] return book
Now you have 1 tasks in the list.
```

### 1b `deadline DESCRIPTION /by yyyy-mm-dd` - Adds a *deadline* task.

Adds a *deadline* task to the list.

Example of usage: 
```
deadline return book /by 2020-09-22
```

Expected outcome:

```
Got it. I've added this task: 
  [D][✘] return book (by: Sep 22 2020)
Now you have 1 tasks in the list.
```

### 1c `event DESCRIPTION /at yyyy-mm-dd` - Adds an *event* task.

Adds an *event* task to the list.

Example of usage: 
```
event buy book /at 2020-09-23
```

Expected outcome:

```
Got it. I've added this task: 
  [E][✘] buy book (at: Sep 23 2020)
Now you have 1 tasks in the list.

```

### 2 `list` - Lists all tasks.

Lists all tasks in the task list.

Example of usage: 
```
list
```

Expected outcome:

```
Here are the tasks in your list:
  1. [D][✘] return book (by: Sep 22 2020)
  2. [T][✘] borrow book
```

### 3 `done INDEX` - Marks task as done.

Marks the task at INDEX position in the list as done.

Example of usage: 
```
done 2
```

Expected outcome:

```
Nice! I've marked this task as done: 
  [T][✓] borrow book
```

### 4 `delete INDEX` - Deletes a task.

Deletes the task at INDEX position in the list.

Example of usage: 
```
delete 2
```

Expected outcome:

```
Noted. I've removed this task:
  [T][✓] borrow book
Now you have 1 tasks in the list.
```

### 5 `find KEYWORD` - Finds task matching the keyword.

Find all the task that matches the given keyword.

Example of usage: 
```
find book
```

Expected outcome:

```
Here are the matching tasks in your list:
  1. [D][✘] return book (by: Sep 22 2020)
  2. [T][✓] borrow book
```

### 6 `tag INDEX #DESCRIPTION` - Tags task.

Tags the task at INDEX position in the list with `#DESCRIPTION`.

Example of usage: 
```
tag 2 # from library
```

Expected outcome:

```
Got it! I've tagged this task as: #from library
```

### 6 `bye` - Exits application.

Exits the Duke application.

Example of usage: 
```
bye
```

Expected outcome:

```
Bye. Hope to see you again soon!
```




