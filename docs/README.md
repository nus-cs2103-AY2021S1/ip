# BikiniBot User Guide

BikiniBot, short for Bikini Bottom, is a desktop app for creating and managing a todo list. 
The style of this app is inspired by the cartoon Spongebob. 

## Feature list

Listed below are the list of features that the BikiniBot supports.

1. View the list of commands available: `help`
1. Add a todo task: `todo`
1. Add a deadline task: `deadline`
1. Add an event task: `event`
1. List all the tasks: `list`
1. Find tasks using keywords: `find`
1. Mark a task done: `done`
1. Delete a task: `delete`
1. Update an existing task: `update`
1. Exit the application: `bye`
1. Saving the data

## Features


### View the list of commands available: `help`
Displays the list of commands that can be used in the BikiniBot.

Format: help

Example usage:
```
help
```
Expected output:
```
I don't know nothin' about other commands but here are the list of commands I understand!
help: displays the list of commands available

list: displays the list of tasks you have

find *keyword*: displays the tasks with that keyword
eg find karate

todo *task description*: adds a task without any date/time attached to it
eg todo scold spongebob

deadline *task description* /by *date+time*: adds a task that needs to be done before a specific date and time 
(date and time to be written in yyyy-mm-dd HHMM format)
eg deadline build spaceship /by 2019-10-15 2359

event *task description* /at *date+time*: adds a task that starts at a specific time and ends at a specific time
(date and time to be written in yyyy-mm-dd HHMM format)
eg event karate competition /at 2019-10-15 1200

done *task number*: marks the task with that number as done
eg done 1

delete *task number*: deletes the task with that number from the list
eg delete 1

update *task number* /name *task name*: updates the name of the task with that number from the list
update 1 /name help spongebob

update *task number* /date *task date*: (only for deadline or event tasks!) updates the date and time of the task with that number from the list
update 1 /date 2020-02-20 1200

bye: ends the session
```

### Add a todo task: `todo`
Adds a todo task to the list.

Format: todo \*task description\*

Example usage:
```
todo scold spongebob
```
Expected output:
```
Ain't no problem! I'm addin' this task:
[T][✘] scold spongebob
Now you have 1 tasks in your list!
```

### Add a deadline task: `deadline`
Adds a deadline task to the list, with a date and time attached to it.

Format: deadline \*task description* /by \*yyyy-mm-dd HHMM*

Example usage:
```
deadline build spaceship /by 2019-10-15 2359
```
Expected output:
```
Ain't no problem! I'm addin' this task:
[D][✘] build spaceship (by: Tue, 15 Oct 2019, 23:59)
Now you have 2 tasks in your list!
```

### Add an event task: `event`
Adds an event task to the list, with a date and time attached to it.

Format: event \*task description* /at \*yyyy-mm-dd HHMM*

Example usage:
```
event karate competition /at 2019-10-15 1200
```
Expected output:
```
Ain't no problem! I'm addin' this task:
[E][✘] karate competition (at: Tue, 15 Oct 2019, 12:00)
Now you have 3 tasks in your list!
```

### List all the tasks: `list`
Displays the list of tasks the user has.

Format: list

Example usage:
```
list
```
Expected output:
```
Here yer go! These are all your tasks!
1. [T][✘] scold spongebob
2. [D][✘] build spaceship (by: Tue, 15 Oct 2019, 23:59)
3. [E][✘] karate competition (at: Tue, 15 Oct 2019, 12:00)
```

### Find tasks using keywords: `find`
Finds the tasks in the list with the matching keyword specified.

Format: find \*keyword*

Example usage:
```
find karate
```
Expected output:
```
Ain't no problem! I have found the matchin' tasks in your list:
1. [E][✘] karate competition (at: Tue, 15 Oct 2019, 12:00)
```

### Mark a task done: `done`
Marks a task in the list as done.

Format: done \*task number*

Example usage:
```
done 1
```
Expected output:
```
YEEEEE-HAW!!! You've completed this task!
[T][✓] scold spongebob
```

### Delete a task: `delete`
Deletes a task from the list.

Format: delete \*task number*

Example usage:
```
delete 1
```
Expected output:
```
Got it! I'm removin' this task!
[T][✓] scold spongebob
Now you have 2 tasks in your list!
```

### Update an existing task: `update`
Updates an existing task by either changing the task description or 
the date and time attached to the task.

Format 1 (updating task description): update \*task number* /name \*task name*

Example usage 1:
```
update 1 /name destroy spaceship
```
Expected output 1:
```
Got it! I'm updatin' this task to:
[D][✘] destroy spaceship (by: Tue, 15 Oct 2019, 23:59)
```

Format 2 (updating date and time): update *task number* /date *task date*

Example usage 2:
```
update 2 /date 2020-02-20 1200
```
Expected output 2:
```
Got it! I'm updatin' this task to:
[E][✘] karate competition (at: Thu, 20 Feb 2020, 12:00)
```
### Exit the application: `bye`
Exits the application.

Format: bye

Example usage:
```
bye
```
Expected output:
```
You're leavin' already?!??! Well, see you again! BYEEE!!!
```

### Saving the data
BikiniBot saves the user's data in the hard disk automatically after any command that changes the data. 
There is no need to save manually. When the user restarts the application, the list of tasks from the previous
use will still be able to be displayed.  