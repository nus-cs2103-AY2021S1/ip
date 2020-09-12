# User Guide

## Features 

### Archive
* Save and load tasks locally into txt file

### Task Management
* Complete tasks
* Create tasks
* Delete tasks
* Mark task as done
* Update tasks

### User Interaction
* Enter command to application
* See chatbot's response to command
* Error message on incorrect input
* Exit the app on exit command 

## View
* View entire list of tasks

### 

## Usage

### `list` - List command

Lists out all the tasks for the user and include details such as completion, time it was created, time it is due. 

Example of usage: 

`list`

Expected outcome:

```
Here are the tasks in your list:

1.[T][D] borrow books
2.[D][-] cs2102 tutorial 1 [created on Sep 4 2020 20:00] (done by Sep 10 2020 20:00)
3.[E][-] dance class [created on Sep 4 2020 20:01] (at: Sep 10 2020 21:00)
```

`outcome`

### `bye` - Exit command

Exits the app

Example of usage: 

`bye`

Expected outcome:

`CYA PAL. Hope to see you again!`

### `todo` - Add Todo Command

Adds a todo task with the given description and current time to the tasklist

Format:
`todo <description>`

Example of usage: 

`todo clean the dishes`

Expected outcome:

```
Got it. I've added this task:
  [T][-] clean the dishes [created on Sep 12 2020 12:19]
Now your have 1 tasks in the list.
```

### `deadline` - Add DeadLine Command

Adds a deadline task with the given description, deadline time and current time to the tasklist.

Format:
`deadline <description> /by <yyyy-MM-dd hh-mm>`

Example of usage: 

`deadline finish homework /by 2020-02-10 23:00 `

Expected outcome:

```
Got it. I've added this task:
    [D][-] finish homework [created on Sep 30 2020 12:36] (by: Feb 10 2020 23:00)
Now your have 2 tasks in the list.
```

### `event` - Add DeadLine Command

Adds a event task with the given description, event time and current time to the tasklist.

Format:
`event <description> /at <yyyy-MM-dd hh-mm>`

Example of usage: 

`event dance class /at 2020-01-10 18:00 `

Expected outcome:
```
Got it. I've added this task:
    [E][-] dance class [created on Sep 30 2020 12:37] (by: Jan 10 2020 18:00)
Now your have 3 tasks in the list.
```

### `delete` - Delete Command

Delete the task using the given index from the tasklist(1-based).

Format:

`delete <index>`

Example of usage: 

`delete 2`

Expected outcome:

```
Noted. I've removed this task:
Now your have 3 in the list.
```

### `done` - Done Command

Mark the task from the given index as done.

Format:

`done <index>`

Example of usage: 

`done 2`

Expected outcome:
```
Nice! I've marked this task as done:
[D][D] finish homework [created on Sep 12 2020 12:38] (by Sep 30 2020)
```

### `update` - Update Command

Update the task from the given index to a new task.

Format:

`update <index> /to <task type and description>`

Example of usage:

`update 2 /to todo sweep the floor`

Expected outcome:
```
Got it. I've updated this task:
  [T][-] sweep the floor [created on Sep 12 2020 13:13]
Now you have 3 tasks in the list
```
