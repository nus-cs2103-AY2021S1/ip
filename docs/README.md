# User Guide
Duke is a minimalistic task manager CLI app. It can add 3 different types of task which are todo, event and deadline. You can set a date for deadline and event tasks. You are also able to mark each task as complete or remove them from the list. This app serves to help you keep track of things in your life.
## Features 

### `todo <description>`
Add a todo task to the list of tasks.

Example of usage:

`todo buy fish`

Expected outcome:

```
Got it. I've added this task:
[T][✘] buy fish
Now you have 3 tasks in the list.
```
### `deadline <description> /by <date>`
Add a deadline task to the list of tasks. <br />
Note: `<date>` must be of the format `YYYY-MM-DD`

Example of usage:

`deadline buy fish /by 2018-05-05`

Expected outcome:

```
Got it. I've added this task:
[D][] buy fish (by: May 5 2018)
Now you have 7 tasks in the list.
```

### `event <description> /at <date>`
Add an event task to the list.<br />
Note: `<date>` must be in the format `YYYY-MM-DD`

Example of usage:

`event fly kite /at 2019-05-05`

Expected outcome:

```
Got it. I've added this task:
[E][] fly kite (at: May 5 2019)
Now you have 9 tasks in the list.
```

### `list`
Shows all the tasks stored in the list.

Expected usage:

`list`

Expected outcome:

```
Here are the tasks in your list:
1. [T][] buy books
2. [T][] fly kite
3. [E][] run 5km (by: Dec 2 2019)
```

### `done <task number>`
Mark a task as done.<br />
Note: `<task number>` must be within the number of tasks in the list.

Example of usage:

`done 2`

Expected outcome:

```
This task has been marked as done:
[T][] fly kite
```

### `delete <task number>`
Remove a task from the list.<br />
Note: `<task number>` must be within the number of tasks in the list.

Example of usage:

`delete 1`

Expected outcome:

```
This task has been removed:
[T][] buy books
Now you have 4 tasks left in the list.
```

### `find <keyword>`
Searches the list of tasks to find tasks matching the keyword.

Example of usage:
` find book`

Expected outcome:
```
Here are the matching tasks in your list:
1. [T] buy books
2. [E] return books to dad (at: May 12 2017)
```
###  `bye`
Terminates the app and closes the chat window.
