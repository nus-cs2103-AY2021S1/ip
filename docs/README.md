# User Guide

## User Interface

![title](Ui.png)

## Features

### Create Tasks

Create any task(s), whether they are todo tasks, events or even deadlines, to help you keep track of your schedule.

### Mark Tasks As Completed

Mark any task(s) that you have finished as completed so that you can update your schedule.

### Delete Tasks

Delete any task(s) which you might not need to complete anymore.

### List Tasks

List all task(s), whether completed or pending, for your information.

### Sort Tasks

Sort task(s) by alphabetical order (and chronological order if applicable) so that you can have an ordered representation of the tasks.

### Search Tasks

Find task(s) that match a certain keyword for easy access to tasks.

### Storage

Stores all your information in one place so that you have access to them the next time you run the application.


## Usage

### `todo` - Adds a todo task

The `todo` command allows users to create new todo tasks (labelled "T") that do not have a set date.

Example of usage:
`todo buy bread`

Expected outcome:
```
Got it. I've added this task:
    [T][✗] buy bread
Now you have 4 task(s) in the list.
```

### `event` - Adds an event

The `event` command allows users to create new event tasks (labelled "E") that have an associated start date and time. 

Example of usage:
`event midterm exam /at 2020-05-31 1200`

Expected outcome:
```
Got it. I've added this task:
    [E][✗] midterm exam (at: 31-05-2020 12:00PM)
Now you have 5 task(s) in the list.
```

### `deadline` - Adds a deadline

The `deadline` command allows users to create new tasks (labelled "D") that have an associated deadline (both date and time).

Example of usage:
`deadline submit draft /by 2020-09-17 1400`

Expected outcome:
```
Got it. I've added this task:
    [D][✗] submit draft (by: 17-09-2020 2:00PM)
Now you have 6 task(s) in the list.
```

### `done` - Mark a task as completed

The `done` command allows users to mark existing an uncompleted task as completed. 

Example of usage:
`done 4`

Expected outcome:
```
Nice! I've marked this task as done:
    [T][✓] buy bread
```

### `delete` - Delete a task

The `delete` command allows users to delete an existing task.

Example of usage:
`delete 3`

Expected outcome:
```
Noted. I've removed this task:
    [D][✗] CS assignment (by: 04-08-2020 11:59PM)
Now you have 5 task(s) in the list.
```

### `list` - List all tasks

The `list` command allows users to view all their current tasks (both pending and completed) in a list format.

Example of usage:
`list`

Expected outcome:
```
There are currently 5 tasks in your list.
1. [T][✓] return book
2. [E][✓] study session (at: 02-05-2020 6:00PM)
3. [T][✓] buy bread
4. [E][✗] midterm exam (at: 31-05-2020 12:00PM)
5. [D][✗] submit draft (by: 17-09-2020 2:00PM)
```

### `sort` - sort tasks

The `sort` command allows users to sort the tasks in alphabetical and chronological order (the latter where applicable).

Example of usage:
`sort`

Expected outcome:
```
Your list has been sorted.
There are currently 5 tasks in your list.
1. [T][✓] buy bread
2. [E][✗] midterm exam (at: 31-05-2020 12:00PM)
3. [T][✓] return book
4. [E][✓] study session (at: 02-05-2020 6:00PM)
5. [D][✗] submit draft (by: 17-09-2020 2:00PM)
```

### `find` - find tasks based on keyword

The `find` command allows users to see list of existing tasks that are matched by a keyword.

Example of usage:
`find bread`

Expected Outcome:
```
Here are the task(s) that match the keyword given
1. [T][✓] buy bread
```

### `bye` - exit the bot

The `bye` command causes the bot to exit, closing the window in the process.

Example of usage:
`bye`

Expected outcome:
```
Bye. Hope to see you again soon!
```

