# User Guide

## Features 

### Add Task
To add tasks to list

1. ### `Todo` 
        Add a task with description
2. ### `Deadline`
        Add a task with description and date
3. ### `Event`
        Add a task with description, date and time


Example of usage: 

- `todo {name of task}`
- `deadline {name of task /by {date}}`
- `todo {name of task /at {date} {time}}`

Expected outcome:

`I have added this task for you {task}`

### Delete Task
To delete tasks in the list

### `Delete` 

Delete a task by its given index

Example of usage: 

`delete 1`

Expected outcome:

`I have deleted this task for you: {task}`

### Find Task
Find all tasks with matching keyword given

### `Find`

Example of usage: 

`find (math)`

Expected outcome:

`Here are the matching tasks: 1. {task1} 2. {tasks2}`

### Finish Task
To mark task as finished

### `Done` 

Describe action and its outcome.

Example of usage: 

`done 1`

Expected outcome:

`I have marked this task as done: {task1}`

### View Schedule
To view the task on the given date

### `View` 

Describe action and its outcome.

Example of usage: 

`view 2020-09-30`

Expected outcome:

`Here are the tasks on 30th September 2020: {task1}`

### Edit Task
To modify task description

### `Edit` 

Describe action and its outcome.

Example of usage: 

`edit 1 English`

Expected outcome:

`I have changed the description for you: {newTaskName}`
