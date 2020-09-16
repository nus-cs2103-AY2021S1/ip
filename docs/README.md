# User Guide

## Features 

### Task CRUD
Able to store and manage your list of tasks locally on your computer!.

## Usage

### `help` - Display the help command

Displays the list of commands that Duke understands.

Example of usage: 

`help`

Expected outcome:

```
Here are the list of commands you can use:
help, list, save, bye, todo 'TASK', deadline 'TASK' /by 'dd/MM/yyyy HH:mm', event 'TASK' /at 'dd/MM/yyyy HH:mm', done 'n', delete 'n' 
```

### `list` - Display the list

Displays the list of the user's tasks available.

Example of usage: 

`list`

Expected outcome:

```
You have no tasks left! Good job my child!
```

```
 Here is the task in your list:
 1.[T][N] dab
```



### `save` - Manually saves

Manually saves the current state of the list of the user's tasks.

Example of usage: 

`save`

Expected outcome:

```
Saving...
Saved successfully!
```

### `bye` - Display the goodbye message

Saves the list of the user's tasks and displays the goodbye message. It is safe to exit the program after this command.

Example of usage: 

`bye`

Expected outcome:

```
 Bye. Hope to see you again soon!
 You can now safely close the app!
```

### `todo` - Create a new todo task

Creates a new todo task with the description.

Example of usage: 

`todo 'TASK'`

Expected outcome:

```
 Got it. I've added this task:
  [T][N] dab
 Now you have 1 task in the list.
```

### `deadline` - Create a new deadline task

Creates a new deadline task with the description and the date due by.

Example of usage: 

`deadline 'TASK' /by 'dd/MM/yyyy HH:mm'`

Expected outcome:

```
 Got it. I've added this task:
  [D][N] dab (by: Nov 11 1111 11:11)
 Now you have 1 task in the list.
```

### `event` - Create a new event task

Creates a new event task with the description and the date due at.

Example of usage: 

`event 'TASK' /at 'dd/MM/yyyy HH:mm'`

Expected outcome:

```
 Got it. I've added this task:
  [E][N] dab (at: Nov 11 1111 11:11)
 Now you have 1 task in the list.
```

### `done` - Marks a task done

Marks the task at position 'n' of the list of the user's tasks as done.

Example of usage: 

`done 'n'`

Expected outcome:

```
Great job! I'll mark 'dab' as done for you. ^^
```

### `delete` - Delete a task

Deletes the task at position 'n' of the list of the user's tasks.

Example of usage: 

`delete 'n'`

Expected outcome:

```
Noted. I've removed this task:
 [T][N] dab
Now you have 1 task in the list.
```



