# User Guide

## Features 
Keep track of your tasks (todo, event, and deadline)

### Add Todo
Add a task to do

### Add Event
Add an event with a specified date and time

### Add Deadline
Add a deadline with a specified due date and time

### Mark Task as Done
Mark a task as done

### Delete Task
Delete a task

### Find task
Find tasks based on keyword

## Usage

### `todo <description>` - To add a todo task

Adds the todo task.

Example of usage: 

`todo homework`

Expected outcome:

```
Got it. I've added this task:
[T][X] homework
Now you have 2 tasks in the list
```

### `event <description> /at <date> <time>` - To add an event

Adds the event with the specified date and time.

Example of usage: 

`event webinar /at 20/02/2020 1900`

Expected outcome:

```
Got it. I've added this task:
[E][X] webinar (at: February 20 2020, 19:00)
Now you have 3 tasks in the list
```

### `deadline <description> /by <date> <time>` - To add a deadline

Adds the deadline with the specified due date and time.

Example of usage: 

`deadline lab report /by 22/04/2020 1930`

Expected outcome:

```
Got it. I've added this task:
[D][X] lab report (by: April 22 2020, 19:30)
Now you have 3 tasks in the list
```

### `list` - To view the list of all tasks

Displays list of all tasks along with their indices.

Example of usage: 

`list`

Expected outcome:

```
1. [T][X] homework
2. [E][X] webinar (at: February 20 2020, 19:00)
3. [D][X] lab report (by: April 22 2020, 19:30)
```

### `done <task index>` - To mark a task as done

Marks the task whose index in the task list is specified by `<task index>` as done.

Example of usage: 

`done 1`

Expected outcome:
```
Nice! I've marked this task as done:
[T][:heavy_check_mark:] homework
```

### `delete <task index>` - To delete a task

Deletes the task whose index in the task list is specified by `<task index>`.

Example of usage: 

`delete 1`

Expected outcome:
```
Noted. I've removed this task:
[T][:heavy_check_mark:] homework
Now you have 2 tasks in the list.
```

### `find <keyword>` - To mark the task as done

Finds the tasks containing `<keyword>`.

Example of usage: 

`find web`

Expected outcome:
```
1. [E][X] webinar (at: February 20 2020, 19:00)
```

### `bye` - To exit the Duke app

Closes the Duke app.

Example of usage: 

`bye`

Expected outcome:
The Duke app is closed
