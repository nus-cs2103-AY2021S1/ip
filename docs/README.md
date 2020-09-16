# User Guide

## Features: A summary of Jarvis's commands

### Add a Task
Add task that is to be completed into Jarvis

### Delete a Task 
Delete a completed task

### Find a Task
Look up an existing task

### Indicate a finished Task
Nark completed task as finished

### Recurring task
Set up tasks that are to be repeated weekly


## Usage: Getting started

### `todo` - Add a task

Adds a task into Jarvis.

Example of usage: 

`todo the dishes`

Expected outcome:

`added: [T][x]the dishes
0 finished tasks in the list
1 unfinished tasks in the list
----------------------------------`

### `deadline` - Add a task with a specific deadline

Adds a task into Jarvis.

Example of usage: 

`deadline my IP /by 2/12/2019 1800`

Expected outcome:

`added: [D][x]my IP -----2019-12-02
0 finished tasks in the list
2 unfinished tasks in the list
----------------------------------`

### `done` - mark a task as done

task will be shown as a tick when marked done

Example of usage: 

`done 1`

Expected outcome: 

`Nice I've marked this tasks as done
[D][√] my IP ------2019-12-02
----------------------------------`

### `remove` - remove a task permanently

remove a task regardless of recurrence or not. Specify index of the task to be removed.

Example of usage: 

`remove 1`

Expected outcome: 

`removed:[T][x] the dishes
0 finished tasks in the list
1 unfinished tasks in the list
----------------------------------`

### `weekly` - Add a task with a specific deadline. This task repeats weekly

Adds a task into Jarvis that repeats weekly

Example of usage: 

`weekly my IP /by 2/12/2019 1800`

Expected outcome:

`added: [W][x]my IP -----2019-12-02
0 finished tasks in the list
1 unfinished tasks in the list
----------------------------------`

### `delete` - remove a recurring task

Used to delete a recurring weekly task for the week. For permanent deletion, use remove as shown above

Example of usage: 

`delete 1`

Expected outcome: 

`removed:[D][x]my IP -----2019-12-02 
added recurring:[D][x]my IP -----2019-12-02 
----------------------------------`

### `help` - show the commands 

show commands at a glance

Example of usage: 

`help`

Expected outcome: 

`--These are Jarvis Functions-- 
help
list
todo <taskName>
done <index>
deadline/weekly <taskName> /by 2/12/2019 1800
find <taskName>
delete <weeklyTaskIndex>
remove <taskToRemovePermanently>
----------------------------------`

 