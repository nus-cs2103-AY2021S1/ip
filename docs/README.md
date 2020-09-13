# User Guide

Star Bot is a **personal assistant chatbot app that keeps track of various tasks.** It stores a list of tasks that you
can interact with and manipulate via its Graphical User Interface (GUI).

All tasks are represented in the following format:

`[S][Done?] task description`
* `[S]` - the task symbol
* `[Done?]` - a tick/cross representing if the task has been completed
* `task description` - description of the task as entered by the user

## Features

List of features:
* Add a task
  * Add a ToDo task
  * Add an Event task
  * Add a Deadline task
* Delete a task
* Mark a task as completed
* Show your list of tasks
* Find a task
* Exit the program

Note:
* Words in `UPPER_CASE` are the parameters to be supplied by the user. <br />
e.g. in `todo TASK`, `TASK` is a parameter to be supplied by the user, for example: `todo homework`
* Command words are case-insensitive
<br />
<br />

### Add a task

##### Add a ToDo `[T]` task: `todo` / `td` / `t`

Adds a ToDo task to the task list.

ToDo tasks are the most generic type of tasks that need not be done by a particular date or time, nor at any particular
place.

Format: `todo TASK`

Example of usage: `todo make breakfast`

Expected outcome:
```
Got it. I've added this task:
[T][X] make breakfast
Now you have 1 task in the list.
```
<br />
<br />

##### Add an Event `[E]` task: `event` / `ev` / `e`

Adds an Event task to the task list.

Event tasks are tasks that occur at a particular place.

Format: `event TASK /at PLACE`

Example of usage: `event party /at my house`

Expected outcome:
```
Got it. I've added this task:
[E][X] party (at: my house)
Now you have 2 tasks in the list.
```
<br />
<br />

##### Add a Deadline `[D]` task: `deadline` / `d`

Adds a Deadline task to the task list.

Deadline tasks are tasks that need to be done by a particular date and time.

Format: `deadline TASK /by YYYY-MM-DD hhmm`
* `YYYY` - year
* `MM` - month
* `DD` - day
* `hh` - hour (in 24-hour format)
* `mm` - minute

Example of usage: `deadline send email /by 2020-11-23 2359`

Expected outcome:
```
Got it. I've added this task:
[D][X] send email (by: 23 Nov 2020 @ 11:59pm)
Now you have 3 tasks in the list.
```
<br />
<br />

### Delete a task: `delete` / `del`

Deletes a task in the task list.

Format: `delete TASK_NUMBER`, where `TASK_NUMBER` is the index of the task in the task list.

Example of usage: `delete 3`

Expected outcome:
```
Noted. I've removed this task:
[D][X] send email (by: 23 Nov 2020 @ 11:59pm)
Now you have 2 tasks in the list.
```
<br />
<br />

### Mark a task as completed: `done` / `dn`

Marks a task in the task list as completed.

Format: `done TASK_NUMBER`, where `TASK_NUMBER` is the index of the task in the task list.

Example of usage: `done 1`

Expected outcome:
```
Nice! I've marked this task as done:
[T][✓] make breakfast
```
<br />
<br />

### Show your list of tasks: `list` / `ls` / `l`

Shows all the tasks in your task list.

Format / example of usage: `list`

Expected outcome:
```
Here are the tasks in your list:
1.[T][✓] make breakfast
2.[E][X] party (at: my house)
```
<br />
<br />

### Find tasks: `find` / `f`

Finds all the tasks in your task list that contain a specified word(s).

Format: `find SEARCH_WORDS`

Example of usage: `find present`

Expected outcome:
```
Here are the tasks in your list with the word(s) "present":
1.[T][✓] buy presents
2.[E][X] give presentation (at: school)
3.[T][X] revise present tense French words
```
<br />
<br />

### Exit the program: `bye` / `b`

Exits the program.

Format / example of usage: `bye`

Expected outcome:
```
Goodbye, see you again soon! :)
```
<br />
<br />

## Command Summary

Action | Command | Shortcuts | Format, Examples
-------|---------|----------|-----------------
Add a ToDo task | `todo` | `td` / `t` | `todo TASK`<br />e.g.`todo make breakfast`
Add an Event task | `event` | `ev` / `e` | `event TASK /at PLACE`<br />e.g.`event party /at my house`
Add a Deadline task | `deadline` | `d` | `deadline TASK /by YYYY-MM-DD hhmm`<br />e.g.`deadline send email /by 2020-11-23 2359`
Delete a task | `delete` | `del` | `delete TASK_NUMBER`<br />e.g.`delete 3`
Mark a task as completed | `done` | `dn` | `done TASK_NUMBER`<br />e.g.`done 1`
Show your list of tasks | `list` | `ls` / `l`
Find tasks | `find` | `f` | `find SEARCH_WORDS`<br />e.g.`find cook`
Exit the program | `bye` | `b`
