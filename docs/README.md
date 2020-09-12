# User Guide

## Features 

### Feature 1: Adding todos 
Add a todo to the task list. A todo is a task that does not have a date attached to it.

### Feature 2: Adding deadlines

Add a deadline to the task list. A deadline is a task that has a date attached to it. 

### Feature 3 Adding events

Add an event to the task list. A event is a task that has a date attached to it. 

### Feature 4: View all tasks in the task list

Ability to view all the tasks entered by the user into the task list.

### Feature 5: Mark a task as done.

User is able to check off a task as 'done'.

### Feature 6: Delete tasks

User is able to delete a task from the task list.

### Feature 7: Find tasks using keyword

User is able to find all tasks in the task list that contain a certain keyword.

## Usage

### `todo` - Adds a todo into the task list

Describe action and its outcome.

The string entered by the user after this keyword is the description of the todo. A todo with the description entered will be added into the task list.

Example of usage: 

`todo do something`

Expected outcome:

```
Got it. I've added this task:
	[T][X] do something
Now you have 1 tasks in the list.
```



### `deadline` - Adds a deadline into the task list

The string entered by the user after this keyword is the description of the deadline. The date, entered in the format YYYY-MM-DD, is entered after `/by`.   A deadline with the description and date specified will be added into the task list.

Example of usage:

`deadline do something /by 2020-12-12`

Expected outcome:

```
Got it. I've added this task:
	[D][X] do something (by: Dec 12 2020)
Now you have 1 tasks in the list.
```



###`event` - Adds an event into the task list

The string entered by the user after this keyword is the description of the deadline. The date, entered in the format YYYY-MM-DD, is entered after `/at`.  This new task is marked as undone. An event with the description and date specified will be added into the task list.

Example of usage:

`event do something /at 2020-12-12`

Expected outcome:

```
Got it. I've added this task:
	[E][X] do something (at: Dec 12 2020)
Now you have 1 tasks in the list.
```





### `list` - Lists out all the tasks currently in the task list

After this keyword is entered, all the tasks that are currently in the task list will be listed out.

Example of usage: 

```
todo buy lunch
event do something /at 2020-12-12
deadline do work /by 2020-11-11
list
```

Expected outcome:

```
Here are the tasks in your list:
1. [T][X] buy lunch
2. [E][X] do something (at: Dec 12 2020)
3. [D][X] do work (by: Nov 11 2020)
```



### `done` - Marks the specified task as done

The integer entered by the user after this keyword will specify which task to mark as done.

Example of usage:

Suppose a todo 'buy lunch' is the first task on the list.

`done 1`

Expected outcome:

```
Nice! I've marked this task as done:
	[T][✔] buy lunch
```



### `delete` - Deletes the specified task

Deletes the task with the task number that is specified by the user after the keyword.

Example of usage:

Suppose a todo 'buy lunch' is the firsy task on the list.

`delete 1`

Expected outcome:

```
Noted. I've removed this task:
	[T][✔] buy lunch
Now you have 0 tasks in the list.
```



### `find` - Searches for related tasks

The string entered by the user after this keyword is the phrase to be searched. The tasks from the task list that contain this phrase will be listed out.

Example of usage:

Suppose this is the current list of tasks:

```
Here are the tasks in your list:
1. [T][X] buy eggs
2. [T][X] buy bread
3. [D][X] finish work (by: Dec 12 2020)
```

User input: `find buy`

Expected outcome:

```
Here are the matching tasks in your list:
1. [T][X] buy eggs
2. [T][X] buy bread
```



