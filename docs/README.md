# Duke User Guide

## Features 

### Adding Tasks
Add Tasks of a specified type (Todo/ Deadline/ Event) to the Task List.

### Deleting Tasks
Delete a specified Task from the Task List.

### Finding Tasks
Using a specified keyword, find matching tasks in the Task List.

### Marking Tasks as Recurring
By including the recurring keyword when adding tasks, marks the task as recurring weekly.
## Usage

- ### `bye` - Exits the Application
Exits the Duke application.

Example of usage: 

`bye`

Expected outcome:

`Alright then cya again soon`

- ### `todo DESCRIPTION` - Adds a Todo
Adds a Todo Task to the Task List with the given description.

Example of usage:
 
`todo Take out the trash`

Expected outcome:

```
Got it! I've added this task:
 [T][✘] Take out the trash
Now you have 2 tasks in the list!
```

- ### `recurring` - marks the current Task as recurring when adding Tasks.
The recurring keyword marks the Deadline/Todo Task as weekly Recurring.
when the recurring Task is marked as done, it will automatically be scheduled one week later.

Example of usage:

`deadline Watch webcasts recurring /by 2020-09-20 1900`

Expected outcome:

```
Got it! I've added this task:
 [D][✘] Watch webcasts (by: 2020-09-20 1900) [Recurring Weekly]
Now you have 1 tasks in the list!
```

- ### `deadline DESCRIPTION /by YYYY-MM-DD TTTT` - Adds a Deadline
Adds a deadline to the Task List with the specified description and DateTime.
To mark as recurring, add "recurring" after the description.

Example of usage:

`deadline Finish tutorials /by 2020-09-20 1200`

Expected outcome:

```
Got it! I've added this task:
 [D][✘] Finish tutorials (by: 2020-09-20 1200)
Now you have 1 tasks in the list!
```

- ### `event DESCRIPTION /on YYYY-MM-DD TTTT` - Adds an Event
Adds an Event to the Task List with the specified description and DateTime.
To mark as recurring, add "recurring" after the description.

Example of usage:

`event Birthday party /on 2020-09-20 1200`

Expected outcome:

```
Got it! I've added this task:
 [E][✘] Birthday party (on: 2020-09-20 1200)
Now you have 1 tasks in the list!
```

- ### `list` - Shows the current list of Tasks
Displays the current Task List.

Example of usage:

`list`

Expected outcome:

```
1. [T][✘] Take out the trash
2. [D][✘] Finish tutorials (by: 2020-09-20 1200)
3. [E][✘] Birthday party (on: 2020-09-20 1200)
```

- ### `find KEYWORD` - find Tasks with KEYWORD
Displays the Tasks with descriptions that match the specified keyword.

Example of usage:

`find Birthday`

Expected outcome:

```
Here's what I found:
1. [E][✘] Birthday party (on: 2020-09-20 1200)
```

- ### `delete TASKINDEX` - deletes the specified Task
Deletes the Task at the corresponding index.

Example of usage:

`delete 1`

Expected outcome:

```
Noted! I've removed this task:
[T][✘] Take out the trash
Now you have 0 tasks in the list!
```

- ### `done TASKINDEX` - Marks the specified task as done
Marks the Task at the corresponding index as done.

Example of usage:

`done 1`

Expected outcome:

```
Nice! I've marked this task as done:
[T][✓] Cook breakfast
```
