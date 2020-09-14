# User Guide
Download the latest jar file from releases and run the program to start the chatbot up.


![Image of Duke](./Ui.png)

## Features 

### Add task 
Adds a todo/event/deadline to the task list.

### Update task status
Updates the status of whether a task is completed or not.
### Delete task 
Removes a task from the list.
### Find task 
Finds a task that matches the keyword specified.
### Prioritise task
Tags a task with a priority of high/mid/low.
### List all tasks 
List all tasks the user has sorted by priority.
## Usage

### `todo (description)` - Add a todo to the list

Add a `todo` to with the specified description to the task list.

Example of usage: 

`todo read book`

Expected outcome:

```
Got it I've added this task:
[MID][T][✘] read book 
You now have 1 task(s) in the list.
```
<br/>

### `deadline (description, time)` - Add a deadline to the list

Add a `deadline` with the specified `description` and `time` to the task list. `time` in "/by MM-DD-YYYY HH:MM" format.

Example of usage: 

`deadline read book /by 12-12-2020 23:59`

Expected outcome:
```
Got it I've added this task:
[MID][D][✘] read book (By: Dec 12 2020 23:59)
You now have 1 task(s) in the list.
```
<br/>

### `event (description, time)` - Add an event to the list

Add an `event` with the specified `description` and `time` to the task list. `time` in "/at MM-DD-YYYY HH:MM" format. 

Example of usage: 

`event read book /at 12-12-2020 23:59`

Expected outcome:
```
Got it I've added this task:
[MID][E][✘] read book (At: Dec 12 2020 23:59)
You now have 1 task(s) in the list.
```
<br/>

### `done (index)` - Update the done status of a task.

Update the done status of a task at the specified `index`

Example of usage: 

`done 1`

Expected outcome:
```
Nice! I've marked this task as done:
[MID][T][✘] read book
```
<br/>

### `delete (index)` - Delete a task from the task list.

Delete the task at the specified `index`

Example of usage: 

`delete 1`

Expected outcome:
```
Noted! I've removed this task:
[MID][T][✘] read book
You now have 0 task(s) in the list.
```
<br/>

### `find (keyword)` - Find all tasks that match the `keyword`.

Displays a list of tasks that match the specified `keyword`

Example of usage: 

`find book` on
1. [MID][T][✘] borrow book
2. [MID][T][✘] read book
3. [MID][T][✘] eat dinner

Expected outcome:
```
Here are your matching tasks in your list:
1. [MID][T][✘] borrow book
2. [MID][T][✘] read book
```
<br/>

### `high/mid/low (index)` - Tag a task with a priority

Tag a task at the specified `index` with the priority `high`, `mid` or `low`

Example of usage: 

`high 3` on
1. [MID][T][✘] borrow book
2. [MID][T][✘] read book
3. [MID][T][✘] eat dinner

Expected outcome:
```
The priority of this task has been updated:
[HIGH][T][✘] eat dinner
```
<br/>

### `list` - Display all tasks in the task list

List all tasks in the task list sorted by priority

Example of usage: 

`list` 

Expected outcome:
```
These are your tasks Oppa!!!
1. [HIGH][T][✘] eat dinner
2. [MID][T][✘] read book
3. [LOW][T][✘] watch drama
```