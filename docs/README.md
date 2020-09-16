# User Guide

![User Interface](Ui.png)

## Features 

### Create Tasks 
Users can create different types of tasks, namely Todos, Events and Deadlines.
The task date can also be noted for task types such as Events and Deadlines.  

### List Tasks 
Users can view a list of all active tasks.

### Mark Tasks as Completed
Users can mark tasks as completed.

### Delete Tasks
Users can remove tasks.

### Find Tasks
Users can search for a task using its name.

### List Today's Tasks
Users can view all tasks that have their date set as the current date.

## Usage

### `todo <task name>` - Creates a new Todo task.

This command creates a new Todo Task, with the task name specified.

Example of usage: 

`todo Exercise`

Expected outcome:

```
Okies! I've added this task~
[T][✘] Exercise
Now you have 1 tasks in the list uwu
```

### `event <event name> /at <event time>` - Creates a new Event task.

This command creates a new Event task, with the specified name and time.
The time should be in YYYY-MM-DD format.

Example of usage: 

`event Project Meeting /at 2020-09-16`

Expected outcome:

```
Okies! I've added this task~
[E][✘] Project Meeting (at: Sep 16 2020, Wednesday)
Now you have 2 tasks in the list uwu
```

### `deadline <deadline name> /by <deadline time>` - Creates a new Deadline task.

This command creates a new Deadline task, with the specified name and time.
The time should be in YYYY-MM-DD format.

Example of usage: 

`deadline Homework /by 2020-09-18`

Expected outcome:

```
Okies! I've added this task~
[D][✘] Homework (by: Sep 18 2020, Friday)
Now you have 3 tasks in the list uwu
```

### `list` - Displays a list of all tasks

This command displays a list of all tasks, from least recently added to most recently added.

Example of usage: 

`list`

Expected outcome:

```
Here are the tasks in your list!!
1. [T][✘] Exercise
2. [E][✘] Project Meeting (at: Sep 16 2020, Wednesday)
3. [D][✘] Homework (by: Sep 18 2020, Friday)
```
### `done <task index>` - Marks a task as completed.

This command marks the task of the specified task index as completed.

Example of usage: 

`done 2`

Expected outcome:

```
Yay! I've marked this task as done :3
[E][✓] Project Meeting (at: Sep 16 2020, Wednesday)
```

### `delete <task index>` - Deletes a task.

This command deletes the task of the specified task index, removing it.

Example of usage: 

`delete 1`

Expected outcome:

```
Got it! I'll remove this task :>
[T][✘] Exercise
Only 2 tasks left!!
```

### `find <search term>` - Searches for tasks based on the search term provided.

This command searches for a task based on the search term provided, matching against
all the different task names. It returns all relevant tasks based on the search term.

Example of usage: 

`find Homework`

Expected outcome:

```
Here are your search results!!
1. [D][✘] Homework (by: Sep 18 2020, Friday)
```

### `today` - Lists all tasks with today's date.

This command displays a list of all tasks that have their date set as the current date.

Example of usage: 

`today`

Expected outcome:

```
Here are today's tasks!!
1. [E][✓] Project Meeting (at: Sep 16 2020, Wednesday)
```

### `bye` - Exits the application.

This command exits and closes the application

Example of usage: 

`bye`

Expected outcome:

The application window will close.