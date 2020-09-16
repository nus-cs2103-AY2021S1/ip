# Fei User Guide

## Introduction
Fei is a Task Manager application, it uses Command Line Interface(CLI). 
It can help people (who prefers typing to voice/mouse command) to manage their tasks more efficiently.

## User interface
![UI](Ui.png)

## Features 

* Add a task to task list:
    1. **`todo`**: add a *todo* task to the list.
    2. **`deadline`**: add a *deadline* task to the list.
    3. **`event`**: add an *event* task to the list.

* List all tasks in the task list: 
     **`list`**

* Mark a task as done: 
    **`done`**

* Delete a task:
    **`delete`**

* Find all tasks that matches the given keyword:
    **`find`**
    
* Show an overall statistics of undone tasks:
    **`stats`**
    
* Provide In-app instruction for all the commands:
    **`help`**

* Exit the application:
    **`bye`**, **`exit`**

## Usage

### Add task commands

### 1. `todo DESCRIPTION` - Add *todo* task to the list

Example of usage: 

* `todo finish ip`

Expected outcome:
```
I've added this task:
[T][✘] finish ip
Now you have 1 tasks in the list.
```

### 2. `deadline DESCRIPTION /by TIME` - Add *deadline* task to the list

Example of usage: 

1.`deadline ip final version /by 2020-09-18`

Expected outcome:
```
I've added this task: 
  [D][✘] ip final version (by: Sep 18 2020)
Now you have 2 tasks in the list.
```


Example of usage: 

2.`deadline ip final version /by tonight`

Expected outcome:
```
Sorry, I can't parse the time format of your input.
Try in this format: yyyy-mm-dd (e.g. 2020-01-01).
I've added this task: 
  [D][✘] ip final version (by: tonight)
Now you have 2 tasks in the list.
```

### 3.`event DESCRIPTION /by TIME` - Add *event* task to the list

Example of usage: 

* `event tp meeting /at 21:00`

Expected outcome:
```
I've added this task: 
  [E][✘] tp meeting (at: 21:00)
Now you have 3 tasks in the list.
```
### List Command

### `list` - List all the tasks in the task list.

Example of usage: 

* `list`

Expected outcome:
```
Here are the tasks in your list:
  1. [T][✘] finish ip
  2. [D][✘] ip final version (by: Sep 18 2020)
  3. [E][✘] tp meeting (at: 21:00)
```

### Done Command
### `done INDEX` - Mark a task with the given INDEX as done, the index must be in the range of your list index.
Example of usage: 

* `done 3`

Expected outcome:
```
Nice! I've marked this task as done:
[E][✓] tp meeting (at: 21:00)
```

### Delete Command
### `delete INDEX` - Delete a task with the given INDEX, the index must be in the range of your list index.
Example of usage: 

* `delete 3`

Expected outcome:
```
Noted. I've removed this task:
 [E][✓] tp meeting (at: 21:00)
Now you have 2 tasks in the list.
```

### Find Command
### `find KEYWORD` - Find all tasks that matches the given keyword.
Example of usage: 

* `find ip`

Expected outcome:
```
Here are the matching tasks in your list:
  1. [T][✘] finish ip
  2. [D][✘] ip final version (by: Sep 18 2020)
```

### Statistic Command
### `stats` - Show an overall statistics of undone tasks.
Example of usage: 

* `stats`

Expected outcome:
```
You now have 1 out of 2 tasks marked as done.
There are still 1 task(s) left.
```

### Help Command
### `help` - Provide In-app instruction for all the commands.
Example of usage: 

* `help`

Expected outcome:
```
The following commands are supported:
 1. todo [task description]
 2. deadline [task description] /by [time]
 3. event [task description] /at [time]
 4. list
 5. delete [index]
 6. done [index]
 7. find [keywords]
 8. stats
 9. help
 10. exit
You can replace '[xxx]' with your on input.
```

### Exit Command
### `bye`, `exit` - Show goodbye message, and exit the application.
Example of usage: 

* `bye`
* `exit`

Expected outcome:
```
GoodBye, baby.
Hope to see you again soon!
```


