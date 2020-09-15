# User Guide

![Image of chatbot](https://github.com/kimberlyohq/ip/blob/master/docs/Ui.png)

## Features 

### Feature 1 - Adding a task
You can add a new task to the To Do List.

### Feature 2 - Adding priority to task
You can add a priority to the task. 

### Feature 3 - Deleting a task
You can delete a task from the To Do List.

### Feature 4 - Listing tasks
You can list all your tasks in the To Do list. 

### Feature 5 - Finding tasks
You can find all tasks matching a keyword.

### Feature 6 - Marking tasks as completed
You can mark a task as done once you have completed it. 


## Usage

### `todo` - Adds a ToDo Task

Example of usage: 

`todo Read a book`

Expected outcome:

```
Got it! I have added this task: 
[T][✘] Read a book
Now you have 4 tasks in the list.
```

### `deadline` - Adds a Deadline Task

Example of usage: 

`deadline Read a book /by 15-09-2020 12:00`

Expected outcome:

```
Got it! I have added this task: 
[D][✘] Read a book (by: 15 Sep 2020, 12:00 PM )
Now you have 4 tasks in the list.
```
### `event` - Adds a Event Task

Example of usage: 

`event CS2103T tutorial /at 15-09-2020 12:00`

Expected outcome:

```
Got it! I have added this task: 
[E][✘] CS2103T tutorial (by: 15 Sep 2020, 12:00 PM )
Now you have 4 tasks in the list.
```

### `priority /add` - Adds a Priority to Task

Types of priority: HIGH, MEDIUM, LOW

Format: priority /add [index] [priority]

Example of usage: 

`priority /add 1 HIGH`

Expected outcome:

```
Noted. I've add priority to this task
[T][✘] Read book Priority: HIGH
```

### `priority /update` - Update Priority of Task

Types of priority: HIGH, MEDIUM, LOW

Format: priority /update [index] [priority]

Example of usage: 

`priority /update 1 HIGH`

Expected outcome:

```
Noted. I've update priority to this task
[T][✘] Read book Priority: HIGH
```

### `priority /delete` - Delete Priority of Task

Types of priority: HIGH, MEDIUM, LOW

Format: priority /delete [index]

Example of usage: 

`priority /delete 1`

Expected outcome:

```
Noted. I've delete priority to this task
[T][✘] Read book
```

### `list` - List all the tasks

Example of usage: 

`list`

Expected outcome:

```
Here are the tasks in your list: 
1. [T][✘] Read a book
2. [T][✘] Return book
```

### `find` - Find all the tasks matching keyword
Format: find [keyword]

Example of usage: 

`find read`

Expected outcome:

```
Here are the matching tasks in your list: 
1. [T][✘] Read a book
```

### `done` - Mark task as done 

Format: done [index]

Example of usage: 

`done 1`

Expected outcome:

```
Nice! I have marked this task as done:
1. [T][✓] Read a book
```

### `delete` - Delete a task

Format: delete [index]

Example of usage: 

`delete 1`

Expected outcome:

```
Noted! I have removed this task:
1. [T][✓] Read a book
```

### `bye` - Exit the chatbot

Example of usage: 

`bye`

Expected outcome:

```
Bye! Hope to see you again soon!
```
