# User Guide
Duke is a PC chatbot application designed to manage user's tasks.

It can be accessed via a Command Line Interface (CLI) or a Graphical User Interface (GUI)

## Features 
Items in curly braces are compulsory field to be given by the user (e.g. `{KEYWORD}`)

Table of commands

|No. | Command | Description |
| --- | --- | ---|
|1 | list | list all tasks|
|2 | find | find tasks that matches keyword |
|3 | todo | add a todo task |
|4 | event | add a event task |
|5 | deadline | add a deadline task |
|6 | done | mark a task as done |
|7 | delete | deletes a task |
|8 | schedule | outputs tasks before given date |
|9 | bye | exits the app |

### 1. List all tasks : ````list````
This command shows all the tasks that is in the tasks list.

Task number can be seen from the output.

#### Usage

### `list`

List all tasks in tasks list with numbering.

Example of usage: 

`list`

Expected outcome:
```
1. [D][✓]submitHW (by: May 5 2020 6:00 PM)
2. [E][✓]eat (at: May 6 2020 5:00 PM)
3. [T][✘]sleep
4. [T][✘]run
````
### 2. find tasks that matches keyword : ````find````
This command allows users to find task from the task list.

#### Usage

### `find {keyword}`

Search for the tasks in the tasks list using the keyword provided.

Tasks that contains the keyword will be returned.

Example of usage: 

`find a`

Expected outcome:
````
Here are the matching tasks in your list:
1. [E][✓]eat (at: May 6 2020 5:00 PM)
2. [E][✘]marathon (at: Sep 9 2020 6:00 PM)
````
### 3. Adds a todo task : ````todo````
This command adds a todo task to the task list.

The todo task has only description.

#### Usage

### `todo {todo_description}`

Adds a todo task to the task list with the description provided.

Outputs a confirmation of added todo and shows the total 
number of tasks in the tasks list after addition.

Example of usage: 

`todo run`

Expected outcome:
````
Got it. I've added this task:
[T][✘]run
Now you have 10 tasks in the list.
````
### 4. Adds a event task : ````event````
This command adds a event task to the task list.

The event task has a description and a date and time.

#### Usage

### `event {event_description} /at {dd/MM/yyyy HHmm}`

Adds a event task to the task list with the description and date time provided.

Outputs a confirmation of added event and shows the total 
number of tasks in the tasks list after addition.

Date and time format must be in **dd/MM/yyyy HHmm**

Example of usage: 

`event Attend John's birthday /at 15/09/2020 1630`

Expected outcome:
````
Got it. I've added this task:
[E][✘]Attend John's birthday (at: Sep 15 2020 4:30 PM)
Now you have 10 tasks in the list.
````
### 5. Adds a deadline task : ````deadline````
This command adds a deadline task to the task list.

The deadline task has a description and a date and time.

#### Usage

### `event {event_description} /by {dd/MM/yyyy HHmm}` 

Adds a deadline task to the task list with the description and date time provided.

Outputs a confirmation of added deadline and shows the total 
number of tasks in the tasks list after addition.

Date and time format must be in **dd/MM/yyyy HHmm**

Example of usage: 

`deadline submit cs2103 ip /by 15/09/2020 1630`

Expected outcome:
````
Got it. I've added this task:
[D][✘]submit cs2103 ip (by: Sep 15 2020 4:30 PM)
Now you have 10 tasks in the list.
````
### 6. Mark a task as done : ````done````
This command marks a task as done (✓) based on the numbering in the list.

#### Usage

### `done {Task_Number}` 

Mark a task with the corresponding task number done.

Done task will has a tick (✓) while task that are unfinished has a cross (✘).

Example of usage: 
````
Suppose our list has the following numbering
1. [D][✓]submitHW (by: May 5 2020 6:00 PM)
2. [E][✓]eat (at: May 6 2020 5:00 PM)
3. [T][✘]sleep
4. [T][✘]run
````
`done 3`

Expected outcome:
````
Nice! I've marked this task as done:
3. [T][✓]sleep
````
### 7. Deletes a task : ````delete````
This command deletes a task based on the numbering in the list.

#### Usage

### `delete {Task_Number}` 

Delete a task with the corresponding task number done.

Example of usage: 
````
Suppose our list has the following numbering
1. [D][✓]submitHW (by: May 5 2020 6:00 PM)
2. [E][✓]eat (at: May 6 2020 5:00 PM)
3. [T][✓]sleep
4. [T][✘]run
````
`delete 3`

Expected outcome:
````
Noted. I've removed this task:
[T][✓]sleep
Now you have 3 tasks in the list.
````
### 8. Scheduling : ````schedule````
This command gives the schedule based on tasks in task list.

#### Usage

### `schedule before {dd/MM/yyyy HHmm}` 

Finds tasks with date time and outputs tasks that are before the 
given date time.

Example of usage: 
````
Suppose we have the following task
1. [D][✓]submitHW (by: May 5 2020 6:00 PM)
2. [E][✓]eat (at: May 6 2020 5:00 PM)
````
`schedule before 06/05/2020 1600`

Expected outcome:
````
1. [D][✓]submitHW (by: May 5 2020 6:00 PM)
````
### 9. Exit : ````bye````
Exit the application

#### Usage

### `bye` - exit application




