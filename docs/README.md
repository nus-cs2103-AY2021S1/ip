# User Guide
This is a simple user guide to Duke, a personal chat-bot to manage your task-list.
## Features 
 There are three types of tasks in Duke: `Todo` `Deadline` and `Event` 
All tasks have features **command** and **isDone** (indicating whether this task is completed or not).
    - `Deadline` and `Event` have an additional feature **time**.
    - Tasks can added, deleted, and marked as done.

## Usage

### `todo COMMAND` - Adds a task that is to be done.

Duke will add a task of type ToDo into your list.

Example of usage: 

`todo read book`

Expected outcome:

`Got it. I've added this task:
 [T][✘]read book
 Now you have 1 tasks in the list.`


### `event COMMAND /at DATETIME` - Adds a task that is to be done in a certain date and time.

Duke will add a task of type Event into your list.

Example of usage: 

`event meeting /at 2020-01-01 1600`

Expected outcome:

`Got it. I've added this task:
[E][✘]meeting(by: Jan 1 2020 16:00)
Now you have 2 tasks in the list.`


### `deadline COMMAND /by DATETIME` - Adds a task that is to be done by a certain date and time.

Duke will add a task of type Deadline into your list.

Example of usage: 

`event return book /by 2000-01-01 1600`

Expected outcome:

`Got it. I've added this task:
[D][✘]return book(by: Jan 1 2000 16:00)
Now you have 3 tasks in the list.`


### `done INDEX` - Marks task as done.
Duke will mark the task indicate by INDEX as done and update the field in your task list.

Example of usage: 

`done 1`

Expected outcome:

`Nice! I've marked this task as done:
 [T][✓]read book`


### `delete INDEX` - Deletes a task from the list.
Duke will delete the task indicate by INDEX and update your task list.

Example of usage: 

`delete 2`

Expected outcome:

`Noted. I've removed this task:
 [E][✘]meeting(by: Jan 1 2020 16:00)`


### `list` - Views your current list of tasks.

Duke will show the detailed list of your current tasks.

Example of usage: 

`list`

Expected outcome:

`[T][✓]read book
 [D][✘]return book(by: Jan 1 2000 16:00)`

### `stats` - Views the statistics of your task list.

Duke will show the total number of tasks, the number of task of each type, and the number of completed tasks in your list.

Example of usage: 

`stats`

Expected outcome:

`You have 2 tasks in total, including 0 events, 1 deadlines, and 1 todos.
 You have completed 1 tasks.`


### `find KEYWORD` - Finds tasks that matches the given keyword.

Duke will show all tasks whose command includes the given KEYWORD.

Example of usage: 

`find read`

Expected outcome:

`Here are the matching tasks in your list:
1.[T][✓]read book`


### `bye` - Exits Duke.

Duke will close the dialogue box and exit.

Example of usage: 

`bye`

Expected outcome:

`Bye. Hope to see you again soon!`