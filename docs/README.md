# User Guide

![](Ui.png)

## Features 
Duke is a simple application to help you manage your tasks. The tasks can be categorized into one of the following types:
* Todos
* Events (and their starting times)
* Deadlines (and their due times)

Duke also supports marking tasks as done, deleting, and finding tasks that match a keyword. Your data will be stored locally and automatically loaded whenever you start the app.

## Usage
**Note:** Times can be either of the following formats:
* `yy-M-dd H:mm`
    * E.g.: `20-09-21 05:45` or `20-9-21 5:45`
* `yyyy-MM-dd'T'HH:mm`
    * E.g.: `2020-09-21T05:45` 

### `event <description> [/p <position>] /at <time>` 
Add an event that will happen at a specified time to a position in the task list.
If argument `/p` is either unspecified or is invalid, the task will be added to the back of the list by default.

Example: 
`event IOI day 2 /p 1 /at 20-9-19 17:00`

Outcome example:
```
Got it. I've added this task:
[E][X] IOI day 2 (at: 2020-09-19T17:00)
Now you have 4 tasks in the list.
```

### `deadline <description> [/p <position>] /by <time>` 
Add a deadline that dues at a specified time to a position in the task list.
If argument `/p` is either unspecified or is invalid, the task will be added to the back of the list by default.

Example: 
`deadline CS2030 iP /by 2020-09-18T23:59`

Outcome example:
```
Got it. I've added this task:
[D][X] CS2030 iP (by: 2020-09-18T23:59
Now you have 3 tasks in the list.
```

### `todo <description> [/p <position>]` 
Add a todo to a position in the task list.
If argument `/p` is either unspecified or is invalid, the task will be added to the back of the list by default.

Example: 
`todo do 5 chess puzzles /p 2`

Outcome example:
```
Got it. I've added this task:
[T][X] do 5 chess puzzles
Now you have 3 tasks in the list.
```

### `done <task_id>` 
Mark a task with the specified task_id as done

Example: 
`done 1`

Outcome example:
```
Nice! I've marked this task as done:
[E][O] IOI day 2 (at: 2020-09-19T17:00)
```

### `list` 
List all tasks currently present in the task list.

Example: 
`list`

Outcome example: 
```
Here are the tasks in your list:
1. [E][O] IOI day 2 (at: 2020-09-19T17:00)
2. [T][O] do 5 chess puzzles
3. [D][X] CS2030 iP (by: 2020-09-18T23:59)
4. [E][X] Worlds 2020 (at: 2020-10-04T16:00)
```

### `delete <task_id>` 
Delete the task that has the specified task_id

Example: 
`delete 3`

Outcome example:
```
Noted. I've removed this task:
[D][X] CS2030 iP (by: 2020-09-18T23:59)
```

### `find <keyword>` 
Find all tasks whose description match the specified keyword.

Example: 
`find Worlds`

Outcome example: 
```
Here are the matching tasks in your list:
1. [E][X] Worlds 2020 (at: 2020-10-04T16:00)
```

### `bye`
Save the data and close the application.