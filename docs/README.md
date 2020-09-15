# User Guide for Incredible Bot

*Keane Chan Jun Yu | A0205678W*

<img src="Ui.png" width="100%"/>

Welcome to Incredible Bot, a task manager app which manages your todos, events and deadlines!

## Content Page
- [Features](## Features)
    - [Todo](### 1. Todo)
    - [Event](### 2. Event)
    - [Deadline] (### 3. Deadline)
    - [Additional Features] (### To assist you with managing your tasks are:)

## Features

In the Incredible Bot, there types of tasks can be created:

### 1. Todo

The simplest task in the whole app! Simply input a description and this task will be added to the bot.

### 2. Event

Events are used for scheduling purposes, allowing you to tag a specific time frame to attend to a task. 
Time frames used can be flexible as you want it too! e.g. event eat /at Tampines

### 3. Deadline

Last but not least, we have deadlines, tasks with fixed due dates. Deadlines can be used to mark tasks with
fixed deadlines.
 
### To assist you with managing your tasks are:
 - `list` - Display the list of tasks
 - `sort` - Sorts the tasks according to completion status and type
 - `done` - Complete a task
 - `delete` - Delete a task
 - `find` - Finds all tasks which matches the query word

## Usage

### Notes about the format of commands:
- Words in `lower_case` are fixed inputs.
- Words in `UPPER_CASE` are input supplied by the user. e.g. `todo DESCRIPTION`, DESCRIPTION is refers to the input
supplied by the user.


### `todo` - adds a todo

A todo task will be added to your list of tasks.

Format: `todo DESCRIPTION`

Example of usage:

`todo Watch Lecture`

Expected outcome:

```
Got it. I've added this task:
    [T][✘] Watch Lecture
Now you have 1 tasks in the list.
```

### `event` - adds an event

An event task will be added to your list of tasks.

Format: `event DESCRIPTION /at TIMEFRAME`

Example of usage:

`event Team meeting /at 2-4pm`

Expected outcome:

```
Got it. I've added this task:
    [E][✘] Team meeting (at: 2-4pm)
Now you have 2 tasks in the list.
```

### `deadline` - adds a deadline

A deadline task will be added to your list of tasks.

Format: `deadline DESCRIPTION /by TIMEFORMAT`

Example of usage:

`deadline Submit homework /by 13-9-20 1420`

Expected outcome:

```
Got it. I've added this task:
    [D][✘] Submit homework (by: 13 Sep 2020 @ 2.20 PM)
Now you have 3 tasks in the list.
```

#### More about deadlines
A deadline task has a unique feature unlike the other two tasks. You can input in dates/times and order deadlines
accordingly!

Accepted time formats:
- d-M-yy HHmm
    - 23-12-19 2230
- d-M-yy
    - e.g. 23-12-19
    - Time will be taken as the current time
- HHmm 
    - e.g. 2230
    - Date will be taken as today's date

### `list` - shows all tasks

Displays all tasks in the list.

Example of usage:

`list`

Expected outcome:

```
Here are the tasks in your list.
1. [T][✘] Watch Lecture
2. [E][✘] Team meeting (at: 2-4pm)
3. [D][✘] Submit homework (by: 13 Sep 2020 @ 2.20 PM)
```

### `sort` - sorts the list of tasks

Sorts the list of tasks with the following standards:
- Incomplete tasks have higher priority (higher up in the list)
- Tasks are sorted by type in order of:
    - Deadlines with the earliest due dates
    - Events in the order they were created
    - Todos in the order they were created

Example of usage:

`sort`

Expected outcome:

```
Your list has been sorted!
Type in 'list' to see the new ordering.
```

If you type in `list`,

```
Here are the tasks in your list.
1. [D][✘] Submit homework (by: 13 Sep 2020 @ 2.20 PM)
2. [E][✘] Team meeting (at: 2-4pm)
3. [T][✘] Watch Lecture
```

### `done` - marks a task as done

A task will be marked as done.

Format: `done NUM`

Example of usage:

`done 1`

Expected outcome:

```
Nice! I've marked this task as done:
    [T][✓] Watch Lecture
```

### `delete` - deletes a task

A task will be deleted.

Format: `delete NUM`

Example of usage:

`delete 1`

Expected outcome:

```
Noted. I've removed this task:
    [T][✓] Watch Lecture
Now you have 2 tasks in the list.
```

### `find` - finds the matching tasks in the list

Finds the list of matching tasks to the query word after find.

Format: `find DESCRIPTION`

Example of usage:

`find meeting`

Expected outcome:

```
Here are the matching tasks in your list:
1. [E][✘] Team meeting (at: 2-4pm)
Now you have 2 tasks in the list.
```

### `help` - Displays the list of commands available

Incredible Bot will display the list of commands that he knows.

Example of usage:

`help`

Expected outcome:

```
Here are the available commands that I know:
1. todo _ (e.g. todo 3)
2. deadline 'task name' /by 'due date' (e.g. deadline Exercise /by 23-8-20)
3. event 'task name' /at 'start time - end time' (e.g. meeting /at Sunday 2pm - 4pm)
4. list
5. done _ (e.g. done 4)
6. delete _ (e.g. delete 4)
7. find '   ' (e.g. find book)
8. sort
9. bye
```

### `bye` - Quits the program

Closes the GUI window.

Example of usage:

`bye`

Expected outcome:

```
Bye! Hope to see you again soon! 
```


## Acknowledgements

### External packages Used
- [JUnit by JUnit Team](https://github.com/junit-team/junit5/) - For testing classes and methods in Incredible Bot
- [JavaFX by OpenJDK](https://github.com/openjdk/jfx) - For creating Graphical User Interface.

### Images Used
- [User image](https://www.pngitem.com/middle/hbxJbTo_the-incredibles-2-mrs-incredible-png-by-metropolis/)
- [Duke image](https://www.pngitem.com/middle/xwTbRJ_incredibles-2-mr-incredible-png-clipart-png-download/)
