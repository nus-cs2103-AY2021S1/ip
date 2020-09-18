# User Guide

* Add a new *Task*
  * *Todo*
  * *Event*
  * *Deadline*
* Remove task
* List all tasks
* Mark a task as done
* Find a particular task

### Add a new *Todo*

##### `Todo TASK_NAME` 

Adds a new Todo Task called `TASK_NAME`

Example of usage: 

`Todo CS2103 Assignment`

### Add a new *Event*

##### `event TASK_NAME /at TIME` 

Adds a new Event Task called `TASK_NAME` that starts at `TIME`

Example of usage: 

`event team meeting /at 2020-12-31`

### Add a new *Deadline*

##### `deadline TASK_NAME /by TIME` 

Adds a new Deadline Task called `TASK_NAME` whose deadline is at `TIME`

Example of usage: 

`deadline team project /by 2020-12-31`

### List all tasks
##### `list` 

Lists all the tasks, including those that are done.


### Remove a task
##### `delete TASK_INDEX` 

Removes the task at index given. Index starts at 1 for the first task and increases by 1.
To find out the index of a particular task, refer to `list`.

Example of usage: 

`delete 1`


### Mark a task as done
##### `done TASK_INDEX` 

Sets the task at index given as done. Index starts at 1 for the first task and increases by 1.
To find out the index of a particular task, refer to `list`.

Example of usage: 

`done 1`

### Find a task 
##### `find TASK_NAME` 
Find the task whose name starts with `TASK_NAME` or any of its sub-words

Example of usage: 

`find cook curry` will match any task with `cook` or `curry`



