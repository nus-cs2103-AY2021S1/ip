# User Guide

## Features 

### A) Add Task
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

Expected Outcome:

`I have deleted this task for you: {task}`

### Find Task
Find all tasks with matching keyword given

### `Find`

Example of usage: 

`find math`

Expected outcome:

`Here are the matching tasks: 1. {task1} 2. {tasks2}`

### Finish Task
To mark task as finished

### `Done` 

Mark the task at the given index as done with a tick

Example of usage: 

`done 1`

Expected outcome:

`I have marked this task as done: {task1 [✓] }`

### View Schedule
To view the task on the given date

### `View` 

The bot will filter and show the list of task with the given date

Example of usage: 

`view 2020-09-30`

Expected outcome:

`Here are the tasks on 30th September 2020: {task1}`

### Edit Task
To modify task description

### `Edit` 

The bot will retrieve the task at the given index and edit the description

Example of usage: 

`edit 1 English`

Expected outcome:

`I have changed the description for you: {newTaskName}`

### B) Exit Emily Bot
To exit the application

### `Bye` 

The program will close

Example of usage: 

`bye`

Expected outcome:

`Program will close`
