# User Guide

**Clara** is a personal task manager. 

There are three types of tasks that **Clara** supports.
1. Todo: a task that has no time
2. Deadline: a task that should be done by a specific time
3. Event: a task that happens at a specific time

![Duke](./docs/Ui.png)


## Features
Description of feature.

### Add a todo 
Adds a todo to **Clara**'s list.

Command: `todo <title>`

Example: `todo exercise`

### Add an event 
Adds an event to **Clara**'s list. 

Command: `event <title> /at <y-M-d or y-M-d HH:mm>`

Example: `event mom's birthday /at 2020-01-01`

### Add a deadline 
Adds a deadline to **Clara**'s list. 

Command: `deadline <title> /by <y-M-d or y-M-d HH:mm>`

Example: `deadline peer review /at 2020-12-31`

### Mark a task as done
Mark a task in **Clara**'s list as done. 

Command: `done <index of task in list>`

Example: `done 1`

### Edit a task's description
Edit the description of a task in **Clara**'s list. 

Command: `edit <index of task in list> <new description>`

Example: `edit 1 run 5 km`

### Find tasks by keyword
Find tasks that contain a specified keyword

Command: `find <keyword>`

Example: `find run 5 km`

### Delete a task
Delete a task in **Clara**'s list. 

Command: `delete <index of task in list>`

Example: `delete 3`

### Display task list
Display all tasks in **Clara**'s list. 

Command: `list`

Example: `list`

