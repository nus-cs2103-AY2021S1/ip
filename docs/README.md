# User Guide

## Features 

### Create, Read, Complete, Delete Tasks
There are three types of tasks: ToDo, Deadline and Event.
You will be able to create tasks, view the current list of tasks, complete or delete specific or multiple tasks.

### Find tasks
You may search for tasks which contains any string of your choice.

### Local Data Storage
Your task list will be saved locally and loaded in whenever you open the application.

### Graphical User Interface (GUI)
The application is user-friendly and easy to use as it runs on a GUI. 

## Usage

### Syntax:

`[parameter]` denotes a parameter. Parameters are required.

`(arguments)` denotes optional arguments. The command will run 
with or without this part of the input.

### `todo [task description]` - Add a To-do type task.

Adds a ToDo type task with a description to your task list. 

Example usage: 

`todo test`

Expected outcome:

```
>> Task has been added:
>> [T][✘] test
>> You now have 1 tasks in the list
```

### `deadline [task description] /by [deadline] (time) ` - Add a Deadline type task.

Adds a Deadline type task with a description and deadline to your task list. 
Deadline may be a date of the form YYYY-MM-DD, and may include a time in 24 hour format of HH:MM.

Example usage: 

`deadline project /by 2020-09-29 18:00`

Expected outcome:

```
>> Task has been added:
>> [D][✘] project (by: Sep 29 2020 6:00 PM)
>> You now have 2 tasks in the list
```

### `event [task description] /by [event date] (time)` - Add an Event type task.

Adds an Event type task with a description and event date to your task list. 
Event time may be a date of the form YYYY-MM-DD, and may include a time in 24 hour format of HH:MM.

Example usage: 

`event concert /at 2020-10-10`

Expected outcome:

```
>> Task has been added:
>> [E][✘] concert (at: Oct 10 2020)
>> You now have 3 tasks in the list
```

### `done [task number]` - Marks a task as done.

Indicates that the corresponding task in the list is completed.

Example usage: 

`done 2`

Expected outcome:

```
>> Task completed: [D][✓] project (by: Sep 29 2020 6:00 PM)
```

### `delete [task number]` - Deletes a task.

Removes the corresponding task number from the task list.

Example usage: 

`delete 1`

Sample outcome:

```
>> Task has been removed.
>> [T][✘] test
>> You now have 2 tasks in the list
```

### `list` - Displays your current Task List.

Displays all tasks, along with their type, date and completion status.

Sample outcome:

```
>> Here are the tasks in your list:
>> 1.[D][✓] project (by: Sep 29 2020 6:00 PM)
>> 2.[E][✘] concert (at: Oct 10 2020) 
```

### `find [key string]` - Finds tasks.

Displays all tasks which contain the key string in the task description.

Example usage: 

`find project`

Sample outcome:

```
>> Here are the matching tasks in your list:
>> 1.[D][✘] project (by: Sep 29 2020 6:00 PM)
>> 2.[T][✓] group project
```

### `clear` - Resets your current Task List.

Remove all tasks from your current task list.

Example usage:

`clear`

Sample outcome:

```
>> Task List has been cleared.
```

### `bye` - Exits duke.

Closes duke, and saves your task list to the hard drive. It will be automatically loaded the next time Duke is opened.