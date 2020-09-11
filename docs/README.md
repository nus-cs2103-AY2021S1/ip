# User Guide

## Features 

### Create, Read, Search, Delete Tasks
You will be able to create tasks, view the list of your
tasks, search your list of tasks, and delete tasks from
the list. There are 3 supported types of tasks: To-dos,
Deadlines and Events.

### Toggle Completion of Tasks
You will be able to mark a task as completed, and this
will be reflected in the list of tasks.

### Local Data Storage
Your task list will be saved locally and loaded in
whenever you open the application.

### Graphical User Interface (GUI)
The application runs on a GUI, making usage more intuitive.

### Tagging
You will be able to tag your tasks with words or
phrases. Tasks can have multiple tags. These tags
are responsive to searches.

## Usage

### Syntax:

`[parameter]` denotes a parameter. Parameters are required.

`(arguments)` denotes optional arguments. The command will run 
with or without this part of the input.

### `todo [task name] (/tags tag1, tag2...)` - Add a To-do type task.

Adds a To-do type task with a name to your task list. If
you provide tags, the task added will be tagged.

Example usage: 

`todo Task 1`

Sample outcome:

```
>> Added the task:
>> [T][✘] Task 1
>> You now have 5 tasks to do!
>> Tasks saved!
```

### `deadline [task name] /by [deadline] (/tags tag1, tag2...)` - Add a Deadline type task.

Adds a Deadline type task with a name and deadline to
your task list. Deadline date format must be of the
form YYYY-MM-DD. If you provide tags, the task added
will be tagged.

Example usage: 

`deadline Task 2 /by 2020-09-18 /tags Important, CS2103`

Sample outcome:

```
>> Added the task:
>> [D][✘] Task 2 #Important #CS2103 (by: Sep 18 2020)
>> You now have 6 tasks to do!
>> Tasks saved!
```

### `event [task name] /at [date] (/tags tag1, tag2...)` - Add an Event type task.

Adds an Event type task with a name and date to
your task list. Date format must be of the
form YYYY-MM-DD. If you provide tags, the task 
added will be tagged.

Example usage: 

`event Task 3 /at 2020-09-19 /tags CS2101`

Sample outcome:

```
>> Added the task:
>> [E][✘] Task 3 #CS2101 (at: Sep 19 2020)
>> You now have 7 tasks to do!
>> Tasks saved!
```

### `done [task number]` - Marks a task as done.

Marks the task corresponding to the task number as done.

Example usage: 

`done 7`

Sample outcome:

```
>> Yay! The following task is marked as done:
>> [E][✓] Task 3 #CS2101 (at: Sep 19 2020)
>> Tasks saved!
```

### `delete [task number]` - Deletes a task.

Removes the task corresponding to the task number from
the task list.

Example usage: 

`delete 7`

Sample outcome:

```
>> I've eradicated the task:
>> [E][✓] Task 3 #CS2101 (at: Sep 19 2020)
>> You now have 6 tasks to do!
>> Tasks saved!
```

### `find [key string]` - Finds tasks.

Provides a list of all tasks which contain the key
string in either its name or tags.

Example usage: 

`find CS2103`

Sample outcome:

```
>> Your matching tasks:
>> 1. [T][✓] CS2103 Week 5
>> 2. [D][✘] Task 2 #Important #CS2103 (by: Sep 18 2020)
```

### `list` - Displays your list of tasks.

Displays your list of tasks along with their corresponding
dates and completion status.

Sample outcome:

```
[T][✓] Task 1
[D][✘] Task 2 (by: Sep 18 2020)
```

### `bye` - Exits the program.

Closes the program. Your task list will be automatically
loaded into the program the next time it is started.
