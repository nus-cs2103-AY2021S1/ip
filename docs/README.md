# Duke User Guide
Duke is a personal chatbot who can help you keep track of your tasks.

![Product Screenshot](Ui.png)

## Table of Contents
- [Requirements](#requirements)
- [Features](#features)
- [Usage](#usage)

## Requirements
- Java 11

## Features

### Duke supports multiple types of tasks
You can keep track of a todo task, a task with deadline, or an event with a specified time.

### Search tasks by keyword
You can search tasks containing the keyword(s) you query.

### No need to use mouse or trackpad
You can easily use the features using keyboard, hence improves efficiency.

### Auto-save
Duke will save the changes in your tasks automatically.

## Usage

### `todo <description>` - To add a todo task

Adds the todo task.

Example of usage: 

`todo homework`

Expected outcome:

```
Got it. I've added this task:
[T][✘] homework
Now you have 2 tasks in the list
```

***

### `event <description> /at <date> <time>` - To add an event

Adds the event with the specified date and time.

Example of usage: 

`event webinar /at 20/02/2020 1900`

Expected outcome:

```
Got it. I've added this task:
[E][✘] webinar (at: February 20 2020, 19:00)
Now you have 3 tasks in the list
```

***

### `deadline <description> /by <date> <time>` - To add a deadline

Adds the deadline with the specified due date and time.

Example of usage: 

`deadline lab report /by 22/04/2020 1930`

Expected outcome:

```
Got it. I've added this task:
[D][✘] lab report (by: April 22 2020, 19:30)
Now you have 3 tasks in the list
```

***

### `list` - To view the list of all tasks

Displays list of all tasks along with their indices.

Example of usage: 

`list`

Expected outcome:

```
1. [T][✘] homework
2. [E][✘] webinar (at: February 20 2020, 19:00)
3. [D][✘] lab report (by: April 22 2020, 19:30)
```

***

### `done <task index>` - To mark a task as done

Marks the task whose index in the task list is specified by `<task index>` as done.

Example of usage: 

`done 1`

Expected outcome:
```
Nice! I've marked this task as done:
[T][✓] homework
```

***

### `delete <task index>` - To delete a task

Deletes the task whose index in the task list is specified by `<task index>`.

Example of usage: 

`delete 1`

Expected outcome:
```
Noted. I've removed this task:
[T][✓] homework
Now you have 2 tasks in the list.
```

***

### `find <keyword>` - To mark the task as done

Finds the tasks containing `<keyword>`.

Example of usage: 

`find web`

Expected outcome:
```
1. [E][✘] webinar (at: February 20 2020, 19:00)
```

***

### `bye` - To exit the Duke app

Closes the Duke app.

Example of usage: 

`bye`

Expected outcome:
The Duke app is closed
