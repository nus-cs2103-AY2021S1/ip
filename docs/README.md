# User Guide

## Features 

### Archive 
* Archive information of tasks locally in resource file

### User interaction
* Key in command and send to the application
* See application response in the app
* See tips when the input command is invalid
* Exit the app using command

### View
* View the entire list of all tasks
* View tasks happening on a designated time
* View tasks containing certain keywords

### Task management
* Create
* Mark as done
* Reschedule and recur
* Delete

## Usage

### `list` - List Command

List out all the tasks with their index, type, status (done or not), description, and time.

Example of usage: 

`list`

Expected outcome:

```
Here are the tasks in your list:

1.[T][✘] borrow books
2.[D][✓] cs2102 tutorial 1 (by: Sep 4 2020 repeat every 1 days)
3.[T][✓] return books
4.[D][✘] cs2101 email (by: Sep 14 2020)
5.[E][✘] cs2103 project meeting (at: 2020-09-30 12:00/2020-10-01 12:00)
6.[E][✓] cs2102 project meeting (at: 07:00 PM   Oct 1 2020 repeat every 3 days)
```

### `bye` - Bye Command

Exit the app.

Example of usage: 

`bye`

### `todo` - Add todo Command

Add a todo task with given description.

Format:

`todo <description>`

Example of usage: 

`todo sample task`

Expected outcome:

```
Got it. I've added this task:
[T][✘] sample task
Now your have 7 tasks in the list.
```

### `deadline` - Add deadline Command

Add a deadline task with given description and date.

Format:

`deadline <description> /by <yyyy-MM-dd>`

Example of usage: 

`deadline sample deadline /by 2020-09-30`

Expected outcome:

```
Got it. I've added this task:
[D][✘] sample deadline (by: Sep 30 2020)
Now your have 8 tasks in the list.
```

### `event` - Add event Command

Add an event task with given description and date time.
Multiple tentative slots can be added to the event.

Format:

`event <description> /at <yyyy-MM-dd HH:mm>[/other tentative slots/...]`

Example of usage 1: 

`event sample event /at 2020-10-01 12:00`

Expected outcome 1:

```
Got it. I've added this task:
[E][✘] sample event (at: 12:00 PM   Oct 1 2020)
Now your have 9 tasks in the list.
```

Example of usage 2: 

`event sample event 2 /at 2020-10-01 12:00/2020-10-02 13:00`

Expected outcome 2:

```
Got it. I've added this task:
[E][✘] sample event 2 (at: 2020-10-01 12:00/2020-10-02 13:00)
Now your have 10 tasks in the list.
```

### `delete` - Delete Command

Delete the task of the given index.

Format:

`delete <index>`

Example of usage: 

`delete 7`

Expected outcome:

```
Noted. I've removed this task:
[T][✘] sample task
Now your have 9 tasks.
```

### `done` - Done Command

Mark the task of the given index as done.

Format:

`done <index>`

Example of usage: 

`done 7`

Expected outcome:

```
Nice! I've marked this task as done:
[D][✓] sample deadline (by Sep 30 2020)
```

### `happen on/before/after` - Happen Date Filter Command

Filter tasks according to the happening time (todo tasks are always excluded).

Format:

`happen <on/before/after> <today or yyyy-MM-dd>`

Example of usage 1: 

`happen on 2020-09-30`

Expected outcome 1:

```
Here are the tasks happening on Sep 30 2020 in your list:

2.[D][✓] cs2102 tutorial 1 (by: Sep 4 2020 repeat every 1 days)
7.[D][✓] sample deadline (by Sep 30 2020)
```

Example of usage 2:

`happen before today`

Expected outcome 2:

```
Here are the tasks happening before today in your list:

2.[D][✓] cs2102 tutorial 1 (by: Sep 4 2020 repeat every 1 days)
```

### `happen in` - Happen In Filter Command

Filter tasks that are happening within n days.

Format:

`happen in <n> days`

Example of usage: 

`happen in 5 days`

Expected outcome:

```
Here are the tasks happening in 5 days in your list:

2.[D][✓] cs2102 tutorial 1 (by: Sep 4 2020 repeat every 1 days)
4.[D][✘] cs2101 email (by: Sep 14 2020)
```

### `happen between` - Happen Between Filter Command

Filter tasks that are happening between two dates.

Format:

`happen between <start date yyyy-MM-dd> <end date yyyy-MM-dd>`

Example of usage: 

`happen between 2020-09-02 2020-09-18`

Expected outcome:

```
Here are the tasks happening between Sep 2 2020 and Sep 18 2020  in your list:

2.[D][✓] cs2102 tutorial 1 (by: Sep 4 2020 repeat every 1 days)
4.[D][✘] cs2101 email (by: Sep 14 2020)
```

### `find` - Find Filter Command

Filter tasks that contains the given substring.
The end date should not occur before the start date.

Format:

`find <substring>`

Example of usage: 

`find cs`

Expected outcome:

```
Here are the tasks containing 'cs' in your list:

2.[D][✓] cs2102 tutorial 1 (by: Sep 4 2020 repeat every 1 days)
4.[D][✘] cs2101 email (by: Sep 14 2020)
5.[E][✘] cs2103 project meeting (at: 2020-09-30 12:00/2020-10-01 12:00)
6.[E][✓] cs2102 project meeting (at: 07:00 PM   Oct 1 2020 repeat every 3 days)
```

### `fix` - Fix Slot Command

Fix a slot for events that have tentative slots. 
The date time to fix should be one of the tentative slots.

Format:

`fix <task index> <the date time to fix>`

Example of usage: 

`fix 9 2020-10-02 13:00`

Expected outcome:

```
Nice! I've fixed the slot for the event:
[E][✘] sample event 2 (at: 01:00 PM   Oct 2 2020)
```

### `snooze` - Snooze Command

Snooze a task to a later time.
The time should be later than the original, and unfixed events are not allowed to snooze.

Format:

`snooze <task index> to <the new date time or date>`

Example of usage: 

`snooze 7 to 2020-10-03`

Expected outcome:

```
Noted. I've postponed this task:
7.[D][✓] sample deadline (by Oct 3 2020)
from 2020-09-30 to 2020-10-03
```

### `repeat` - Repeat Command

Repeat a task regularly.

Format:

`repeat <task index> <daily/weekly/every n days>`

Example of usage 1: 

`repeat 7 weekly`

Expected outcome 1:

```
Noted. I've set this task to repeat every 7 days:
7.[D][✓] sample deadline (by Oct 3 2020 repeat every 7 days)
```

Example of usage 2: 

`repeat 7 every 3 days`

Expected outcome 2:

```
Noted. I've set this task to repeat every 3 days:
7.[D][✓] sample deadline (by Oct 3 2020 repeat every 3 days)
```

## Trouble shooting
If you are sure that you have some tasks in your list but they don't show up,
please check the resource file located at src/main/resources/tasks.txt to see if the input format of tasks are correct.