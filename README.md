# User Guide

<img src="./docs/Ui.png" width="350" />

* [Features](#features)
  * [Task](#task)
    * [Add Task](#add-task)
      * [Add Todo](#add-todo)
      * [Add Event](#add-event)
      * [Add Deadline](#add-deadline)
    * [List All Tasks](#list-all-tasks)
    * [Mark Task as Done](#mark-task-as-done)
    * [Delete Task](#delete-task)
    * [Find Tasks](#find-tasks)
      * [Find Tasks by Keyword](#find-tasks-by-keyword)
      * [Find Tasks by Date](#find-tasks-by-date)
      * [Find Tasks with Today's Date](#find-tasks-with-todays-date)
  * [Exit](#exit)
  
## Features

### Task

A task's status is completed(✓) or uncompleted(✘).

#### Add Task

##### Add Todo

Adds a todo.
A todo is a task without any date or time attached to it.

Format: `todo DESCRIPTION`

* Adds a new todo with specified DESCRIPTION to the list.

Example of usage:

`todo todo`

Expected outcome:

```console
I've added this task:
[T][✘] todo
Now you have 1 task in the list.
```

##### Add Event

Adds an event.
An event is a task with a date, start time and end time attached to it.

Format: `event DESCRIPTION /at DATE START_TIME END_TIME`

* Adds a new event with specified DESCRIPTION, DATE, START_TIME and END_TIME to the list.
* The start time is inputted before the end time.
* The format of date is YYYY-MM-DD. (YYYY: year, MM: month, DD: day)
* The format of time is HH:MMA. (HH: hour, MM: minute, A: AM or PM)

Example of usage:

`event event /at 2020-10-23 08:30PM 10:12PM`

Expected outcome:

```console
I've added this task:
[E][✘] event (at: 23 Oct 2020, start: 8:30PM, end: 10:12PM)
Now you have 1 task in the list.
```

##### Add Deadline

Adds a deadline.
A deadline is a task with a date attached to it.
A deadline needs to be done before the date.

Format: `deadline DESCRIPTION /by DATE`

* Adds a new deadline with specified DESCRIPTION and DATE to the list.
* The format of date is YYYY-MM-DD. (YYYY: year, MM: month, DD: day)

Example of usage:

`deadline deadline /by 2020-10-23`

Expected outcome:

```console
I've added this task:
[D][✘] deadline (by: 23 Oct 2020)
Now you have 1 task in the list.
```

#### List All Tasks

List all tasks in the list.

Format: `list`

Example of usage:

```console
todo todo
event event /at 2020-10-23 08:30PM 10:12PM
deadline deadline /by 2020-10-22
list
```

Expected outcome:

```console
...
Here are the tasks in your list:
1. [T][✘] todo
2. [E][✘] event (at: 23 Oct 2020, start: 8:30PM, end: 10:12PM)
3. [D][✘] deadline (by: 23 Oct 2020)
```

#### Mark Task as Done

Mark a task as done, the status of the task changes from uncompleted(✘) to completed(✓).

Format: `done TASK_NUMBER`

* Mark a task with TASK_NUMBER as done.

Example of usage:

```console
todo todo
done 1
```

Expected outcome:

```console
...
One less thing on your plate! I've marked this task as done:
[T][✓] todo
```

#### Delete Task

Deletes a task from the list.

Format: `delete TASK_NUMBER`

* Deletes a task with TASK_NUMBER.

Example of usage:

```console
todo todo
delete 1
```

Expected outcome:

```console
...
I've removed this task:
[T][✘] todo
Now you have 0 task in the list.
```

#### Find Tasks

##### Find Tasks by Keyword

Find tasks from the list with description that matches the keyword inputted.

Format: `find KEYWORD`

Example of usage:

```console
todo e
event event /at 2020-10-23 08:30PM 10:12PM
deadline deadline /by 2020-10-22
find e
```

Expected outcome:

```console
...
Here are the tasks that I've found matching e:
1. [T][✘] e
2. [E][✘] event (at: 23 Oct 2020, start: 8:30PM, end: 10:12PM)
3. [D][✘] deadline (by: 23 Oct 2020)
```

##### Find Tasks by Date

Find tasks from the list with date that matches the date inputted.

Format: `date DATE`

* The format of date is YYYY-MM-DD. (YYYY: year, MM: month, DD: day)

Example of usage:

```console
event event /at 2020-10-23 08:30PM 10:12PM
deadline deadline /by 2020-10-23
date 2020-10-23
```

Expected outcome:

```console
...
Here are the tasks that I've found on 23 Oct 2020:
1. [E][✘] event (at: 23 Oct 2020, start: 8:30PM, end: 10:12PM)
2. [D][✘] deadline (by: 23 Oct 2020)
```

##### Find Tasks with Today's Date

Find tasks from the list with date that matches the today's date.

Format: `date DATE`

* The format of date is YYYY-MM-DD. (YYYY: year, MM: month, DD: day)

Example of usage:

```console
event event /at 2020-10-23 08:30PM 10:12PM
deadline deadline /by 2020-10-23
today
```

Expected outcome:

```console
...
Here are today's tasks:
1. [E][✘] event (at: 23 Oct 2020, start: 8:30PM, end: 10:12PM)
2. [D][✘] deadline (by: 23 Oct 2020)
```

### Exit

Exits the application.

Format: `bye`

Example of usage:

`bye`

Expected outcome:

```console
Bye. See you again!
```
