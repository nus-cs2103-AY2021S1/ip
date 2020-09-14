# Hisoka User Guide

![GitHub Logo](./docs/Ui.png)

## Features

Hisoka is a task manager that allows you to add, delete, list, find and update tasks.

Tasks are categorised into 3 different types: todos, deadlines and events.

### Commands

#### 1. `todo DESCRIPTION` - Add todo

Add a todo to the task list.

Example of usage: 

`todo homework`

Expected outcome:

<pre><code>Got it. I've added this task:
    [T][✘] homework
Now you have 1 task in the list!
</code></pre>

#### 2. `deadline DESCRIPTION /by YYYY-MM-DD hh:mm` - Add deadline

Add a deadline to the task list.

hh:mm field is optional.

Example of usage: 

`deadline homework /by 2020-09-14 20:00`

Expected outcome:

<pre><code>Got it. I've added this task:
    [D][✘] homework (by: Sep 14 2020 08:00 PM)
Now you have 2 tasks in the list!
</code></pre>

#### 3. `event DESCRIPTION /at YYYY-MM-DD hh:mm-hh:mm` - Add event

Add an event to the task list.

hh:mm fields are optional.

Example of usage: 

`event meeting /at 2020-09-14 20:00-21:00`

Expected outcome:

<pre><code>Got it. I've added this task:
    [E][✘] meeting (at: Sep 14 2020 08:00 PM-09:00 PM)
Now you have 3 tasks in the list!
</code></pre>

#### 4. `list YYYY-MM-DD` - List tasks

List tasks on a specified date, if a date was specified by the user.
Otherwise, list all tasks in the task list.

YYYY-MM-DD field is optional.

Example of usage: 

`list`

Expected outcome:

<pre><code>Here are the tasks in your list:
    1.[T][✘] homework
    2.[D][✘] homework (by: Sep 14 2020 08:00 PM)
    3.[E][✘] meeting (at: Sep 14 2020 08:00 PM-09:00 PM)
</code></pre>

Example of usage: 

`list 2020-09-14`

Expected outcome:

<pre><code>Here are your tasks on Sep 14 2020:
    1.[D][✘] homework (by: Sep 14 2020 08:00 PM)
    2.[E][✘] meeting (at: Sep 14 2020 08:00 PM-09:00 PM)
</code></pre>

#### 5. `delete TASKINDEX` - Delete task

Delete task at specified index in the task list.

Example of usage: 

`delete 2`

Expected outcome:

<pre><code>Noted. I've removed this task:
    [D][✘] homework (by: Sep 14 2020 08:00 PM)
</code></pre>


#### 6. `done TASKINDEX` - Set task to done

Set task at specified index in the task list to done.

Example of usage: 

`done 2`

Expected outcome:

<pre><code>Nice! I've marked this task as done:
    [E][✓] meeting (at: Sep 14 2020 08:00 PM-09:00 PM)
</code></pre>


#### 7. `update desc TASKINDEX DESCRIPTION` - Update task description

Update description of task at specified index.

Example of usage: 

`update desc 1 math homework`

Expected outcome:

<pre><code>Got it. I've updated task 1 to:
    [T][✘] math homework
</code></pre>

#### 8. `update date TASKINDEX YYYY-MM-DD` - Update task date

Update date of task at specified index.

Example of usage: 

`update date 2 2020-09-15`

Expected outcome:

<pre><code>Got it. I've updated task 1 to:
    [E][✓] meeting (at: Sep 15 2020 08:00 PM-09:00 PM)
</code></pre>

#### 9. `update time TASKINDEX hh:mm-hh:mm` - Update task time

Update time of task at specified index.

2nd hh:mm field is optional.

Example of usage: 

`update time 2 22:00-23:00`

Expected outcome:

<pre><code>Got it. I've updated task 1 to:
    [E][✓] meeting (at: Sep 15 2020 10:00 PM-11:00 PM)
</code></pre>


#### 10. `find KEYWORD` - List tasks containing specified keyword

List tasks containing specified keyword. (non case sensitive matching)

Example of usage: 

`find homework`

Expected outcome:

<pre><code>Here are your tasks containing homework:
    [T][✘] math homework
</code></pre>


#### 11. `bye` - Close application

Send goodbye message and close application.

Example of usage: 

`bye`

Expected outcome:

`Goodbye! Hope to see you again soon :-)`
