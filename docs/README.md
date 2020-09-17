# DukeBot User Guide 

## User Interface
![UI](Ui.png)

## Features 

### Task Manager
DukeBot is a personal task manager. It allows users to add, delete, search and tag tasks.

### Usage

### 1. `bye` - Exits application

Exits DukeBot application.

Example of usage: 


bye


Expected outcome:


Bye! Hope to see you again soon!


### 2. `deadline DESCRIPTION /by DATETIME` - Adds a deadline

Adds a Deadline Task to task list.

Example of usage: 


deadline return book /by 2019-01-02


Expected outcome:


Got it! I've added this task:
[D][X] return book on 2019-01-02
Now you have 1 task in the list.


### 3. `delete TASKID` - Deletes a task

Deletes a task from task list.

Example of usage: 


delete 1


Expected outcome:


Noted. I've removed this task:
[D][V] hello world on 2019-02-03
Now you have 0 tasks in the list!


### 4. `done TASKID` - Marks a task as done

Marks a task as done in task list.

Example of usage: 


done 1


Expected outcome:


Nice! I've marked this task as done:
[D][V] hello world on 2020-09-19


### 5. `event DESCRIPTION /at DATETIME` - Adds an event

Adds an Event Task to task list.

Example of usage: 


event hello world /at 01/01/2019 1800


Expected outcome:


Got it! I've added this task:
[E][X] hello world on 2019-01-01
Now you have 1 task in the list!


### 6. `find TASKID` - Searches tasks

Searches tasks by keyword in the task list.

Example of usage: 


find world


Expected outcome:


Here are the tasks in your list:
1.[E][X] hello world on 2019-01-01


### 7. `list` - Lists tasks

Lists all tasks in the task list.

Example of usage: 


list


Expected outcome:


Here are the tasks in your list:
1.[E][X] hello world on 2019-01-01


### 8. `todo DESCRIPTION` - Adds a Todo Task

Adds a Todo Task to task list.

Example of usage: 


todo hello world


Expected outcome:


Got it! I've added this task:
[T][X] hello world
Now you have 2 tasks in the list!


### 8. `update TASKID TASKTYPE NEWDESCRIPTION /by NEWDATE` - Adds a tag to a task

Adds a tag to a task in the task list.

Example of usage: 


update 1 deadline GER Quiz 3 /by 2019-02-01


Expected outcome:

```
Here are the tasks in your list:
1. [D][V] Ger Quiz 3 on 2019-02-01
2. [D][X] return book on 2019-01-02
3. [E][X] hello world on 2019-01-01