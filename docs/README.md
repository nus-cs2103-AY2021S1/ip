# RichBot User Guide

RichBot is a desktop app for managing tasks, optimized for use via
a Command Line Interface(CLI) while still having the benefits of
Graphical User Interface (GUI). This app can help you to manage
your tasks tidier and efficiently.

## Table of Content

* [Features](#features)
* [Usage](#usage)
    * [Showing help](#help---showing-list-of-commands)
    * [Adding todo task](#todo---adding-a-task)
    * [Adding deadline task](#deadline---adding-a-task)
    * [Adding event task](#event---adding-a-task)
    * [Showing task list](#list---showing-list-of-tasks)
    * [Finding task](#find---finding-tasks)
    * [Marking task as done](#done---marking-tasks-as-done)
    * [Deleting task](#delete---deleting-task)
    * [Marking all tasks to done](#done-all---marking-all-undone-task)
    * [Deleting all tasks](#delete-all---deleting-all-tasks)
    * [Showing task after date](#show-after---showing-tasks-after-some-date)
    * [Showing task before date](#show-before---showing-tasks-before-some-date)
    * [Exiting the program](#exit---exiting-the-program)
    
## Features 

### Viewing help
Shows the list of commands to you.
### Create task
Allows you to create 3 types of task, including todo task,
deadline task, and event task.
### Delete task
Allows you to delete unwanted task.
### Mark task as done
Allows you to mark your done as done.
### Show list of tasks
Allows you to see what task exists.
### Show task after or before some date
Allows you to see task before or after specified date. Note
that only deadline and event task that will be shown.

## Usage

### `help` - Showing list of commands.

Shows list of commands that can be executed in this app.

Format: `--help`

Example of usage: 

`--help`

Expected outcome:

```
COMMAND  |   FORMAT
 - deadline  |   deadline <DEADLINE_NAME> /by <yyyy-MM-dd> <HH:mm>
 - delete  |   delete <TASK_NUMBER>
 - delete all  |   delete all
 - done  |   done <TASK_NUMBER>
 - done all  |   done all
 - event  |   event <EVENT_NAME> /at <yyyy-MM-dd> <HH:mm>
 - show after  |   show after <yyyy-MM-dd>
 - show before  |   show before <yyyy-MM-dd>
 - todo  |   todo <TASK_NAME>
```
***
### `todo` - Adding a task.

Adds a todo type task to the list with the specified task
description. 

Format: `todo <description>`

* Adds a todo task with specified `<description>` to the list of tasks. 
`<description>` must be specified.

Example of usage: 

`todo borrow book`

Expected outcome:
```
Got it. I've added this task :
[T][X] borrow book
Now you have 3 tasks in the list
```
***
### `deadline` - Adding a task.

Adds a deadline type task to the list with the specified task
description and date time. 

Format: `deadline <description> /by <yyyy-MM-dd> <HH:mm>`

* Adds a deadline task with specified `<description>` and 
`date time` to the list of tasks. `<description>` must be specified.
* `date time` must be specified and in the format of 
`<yyyy-MM-dd> <HH :mm>`. Example :`2020-08-08 18:00` (Note that
the time are written in 24 hr format.)

Example of usage: 

`deadline CS2103T IP /by 2020-09-09 12:00`

Expected outcome:
```
Got it. I've added this task :
[D][X] CS2103T IP (by: Sep 9 2020, 12 PM)
Now you have 4 tasks in the list
```
***

### `event` - Adding a task.

Adds a event type task to the list with the specified task
description and date time. 

Format: `event <description> /by <yyyy-MM-dd> <HH:mm>`

* Adds a event task with specified `<description>` and 
`date time` to the list of tasks. `<description>` must be specified.
* `date time` must be specified and in the format of 
`<yyyy-MM-dd> <HH :mm>`. Example :`2020-08-08 18:00` (Note that
the time are written in 24 hr format.)

Example of usage: 

`event CS2103T IP /by 2020-09-09 12:00`

Expected outcome:
```
Got it. I've added this task :
[E][X] CS2103T IP (by: Sep 9 2020, 12 PM)
Now you have 5 tasks in the list
```
***

### `list` - Showing list of tasks.

Shows list of tasks. 

Format: `list`

Example of usage: 

`list`

Expected outcome:
```
Here are the tasks in your list:
1. [T][X] borrow book
2. [E][X] dancing (by: Jun 6 2020, 7 PM)
3. [T][X] borrow book
4. [D][X] CS2103T IP (by: Sep 9 2020, 12 PM)
5. [E][X] CS2103T IP (by: Sep 9 2020, 12 PM)
```
***

### `find` - Finding tasks

Finds list of tasks from existing keyword.

Format: `find <keyword>`

Example of usage: 

`find book`

Expected outcome:
```
 Here are the matching tasks in your list:
      1. [T][X] borrow book
      2. [T][X] borrow book
```
***

### `done` - Marking tasks as done.

Marks task as done. Specifies task by using the numbering
from the list.

Format: `done <index_number>`

* `<index_number>` must be specified.

Example of usage: 

`done 1`

Expected outcome:
```
Nice! I've marked this task as done:
[T][O] borrow book
```
***

### `delete` - Deleting task.

Deletes task from the list. Specifies the potential tasks by using
numbering from the list.

Format: `delete <index_number>`

* `<index_number>` must be specified.

Example of usage: 

`delete 1`

Expected outcome:
```
Noted. I've removed this task:
[T][O] borrow book
Now you have 4 tasks in the list
```
***

### `done all` - Marking all undone task.

Marks all undone task as done.

Format: `done all`

Example of usage: 

`done all`

Expected outcome:
```
Here are the tasks in your list:
      1. [E][O] dancing (by: Jun 6 2020, 7 PM)
      2. [T][O] borrow book
      3. [D][O] CS2103T IP (by: Sep 9 2020, 12 PM)
      4. [E][O] CS2103T IP (by: Sep 9 2020, 12 PM)
```
***

### `delete all` - Deleting all tasks.

Deletes all tasks.

Format: `delete all`

Example of usage: 

`delete all`

Expected outcome:
```
All of your task has been removed!
```
***

### `show after` - Showing tasks after some date.

Shows list of tasks after the specified date.

Format: `show after <yyyy-MM-dd>`

*  `date` must be specified and in the format of 
`<yyyy-MM-dd>`. Example: `2020-08-09`

Example of usage: 

`show after 2020-06-09`

Expected outcome:
```
1. [D][X] CS2103T (by: Sep 9 2020, 12 PM)
2. [E][X] dance club (by: Aug 9 2020, 6 PM)
3. [D][X] CS2103T TP (by: Jul 31 2020, 12 AM)
```
***

### `show before` - Showing tasks before some date.

Shows list of tasks before the specified date.

Format: `show before <yyyy-MM-dd>`

*  `date` must be specified and in the format of 
`<yyyy-MM-dd>`. Example: `2020-08-09`

Example of usage: 

`show before 2020-09-09`

Expected outcome:
```
1. [E][X] dance club (by: Aug 9 2020, 6 PM)
2. [D][X] CS2103T TP (by: Jul 31 2020, 12 AM)
```
***

### `exit` - Exiting the program

Exits the program

Format: `bye`

Example of usage: 

`bye`

Expected outcome:
```
Bye! Hope to see you soon
```
(Program terminate)