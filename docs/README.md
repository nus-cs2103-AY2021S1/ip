# Shao Kiat's Duke User Guide

## Features 
##### Add Todo task
Add a todo task.
#### Add Deadlines 
Add an upcoming deadline.
#### Add Events
Add an upcoming event.
#### View all tasks
Allows you to view all tasks that you have added to Duke.
#### Delete task
Delete a task that was added to Duke.
#### Mark tasks as done
Mark a task as done that was added to Duke.
#### Search for tasks
Search for tasks with keywords.
#### Exit program
Exit Duke and saves data into tasks.txt file.

## Features Summary
| Command | Feature Descriptions| Example                            |
|:--------|:--------------------|:-----------------------------------|
| todo    |  Add a todo task    | todo complete ip                   |
| deadline|  Add a deadline     | deadline ip /by 2020-09-18 23:59   |
| event   |  Add an event       | event meeting /at 2020-10-10 10:10 |
| list    |  View all tasks     | list                               |
| delete  |  Delete a task      | delete 1                           |
| done    |  Mark a task as done| done 2                             |
| find    |  Search for tasks   | find ip                            |
| bye     |  Exit program       | bye                                |

## Usage

### `todo` - Add a new Todo task

Add a new todo task to Duke task list.

Format: `todo (task)`

Example of usage: 

`todo ip`

Expected outcome:

![todo](./image/todo.png)

### `deadline` - Add a new Deadline

Add a new deadline to Duke task list.

Format: `deadline (task) /by (time)`

Example of usage: 

`deadline ip /by 2020-09-18 23:59`

Expected outcome:

![deadline](./image/deadline.png)

### `event` - Add a new Event

Add a new event to Duke task list.

Format: `event (task) /by (time)`

Example of usage: 

`event meeting /at 2020-10-10 10:10`

Expected outcome:

![event](./image/event.png)

### `list` - View all tasks

Display all tasks in Duke's task list.

Format: `list`

Example of usage: 

`list`

Expected outcome:

![list](./image/list.png)

### `delete` - Delete a task

Delete a task that was added to Duke.

Format: `delete (task index)`

Example of usage: 

`delete 1`

Expected outcome:

![delete](./image/delete.png)

### `done` - Mark a task as done

Mark a task as done that was added to Duke.

Format: `done (task index)`

Example of usage: 

`done 2`

Expected outcome:

![done](./image/done.png)

### `find` - Search for tasks

Search for tasks with keywords.

Format: `find (keywords)`

Example of usage: 

`find ip`

Expected outcome:

![find](./image/find.png)

### `bye` - Save and exit

Exit Duke and saves data into tasks.txt file.

Format: `bye`

Example of usage: 

`bye`

Expected outcome:

![bye](./image/bye.png)
