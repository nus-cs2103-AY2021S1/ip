# User Guide
DukeBunny is a **desktop application for managing tasks, optimized for use via a Command Line Interface (CLI)** while still 
having the visual benefits of a Graphical User Interface (GUI). If you need help in **keeping track of things to do**, 
DukeBunny offers several useful features with a touch of posh attitude to it.  
## Getting Started
1. Ensure you have Java `11` or above installed in your Computer.
2. Download the latest `DukeBunny.jar` from here.
3. Copy the downloaded jar file to the folder you want to use as the home folder for your DukeBunny.
4. Double-click the file to start the app. The GUI below should appear in a few seconds.\

![DukeBunnyWelcomeSS](/docs/DukeBunnyWelcomeSS.png)
5. Enter commands in the command box and press Enter to execute it. (Please refer to command list)\

![DukeBunnyCommandSS](/docs/DukeBunnyCommandSS.png)

## Features
Words in `UPPER_CASE` are the parameters that are supplied by the user.\
e.g. in `todo DESCRIPTION`, `DESCRIPTION` is a parameter which can be used as `todo Buy bread`.
### Adding a todo task - **`todo`**
Adds a todo task to the task list. A todo is a task with only a description.

Format: `todo DESCRIPTION`

Example:
`todo Buy bread`

Expected outcome:
```javascript
No worries, the following task has been added to your list:
 -------------------------------------------------------------
[T][✘] Buy bread
 -------------------------------------------------------------
Marvellous! Now you have 1 tasks in your list!
```
### Adding a deadline task - **`deadline`**
Adds a deadline task to the task list. A deadline is a task with a description and a date and time.
The date and time is entered after a `/by` follow up command. The format of the date and time is YYYY-MM-DD hhmm.

Format: `deadline DESCRIPTION /by DATE TIME`

Example:
`deadline Physics Project /by 2020-12-04 1800`

Expected outcome:
```javascript
No worries, the following task has been added to your list:
-------------------------------------------------------------
[D][✘] Physics Project (by: 4 December 2020 06:00 PM)
-------------------------------------------------------------
Marvellous! Now you have 2 tasks in your list!
```
### Adding an event task - **`event`**
Adds an event task to the task list. An event is a task with a description, a date, start time and end time.
The date and time is entered after a `/at` follow up command. The format of the date, start and end time 
is YYYY-MM-DD hhmm-hhmm.

Format: `event DESCRIPTION /at DATE START_TIME-END_TIME`

Example:
`event Family Christmas party /at 2020-12-25 1800-2300`

Expected outcome:
```javascript
No worries, the following task has been added to your list:
-------------------------------------------------------------
[E][✘] Familiy Christmas party (at: 25 December 2020 06:00 PM to 11:00 PM)
-------------------------------------------------------------
Marvellous! Now you have 3 tasks in your list!
```
### Viewing all tasks - **`list`**
Views all tasks that are in the task list.

Format: `list`

Example:
```javascript
todo Buy bread
deadline Physics Project /by 2020-12-04 1800
event Family Christmas party /at 2020-12-25 1800-2300
list
```

Expected outcome:
```javascript
Here are the tasks in your list:
-------------------------------------------------------------
1.[T][✘] Buy bread
2.[D][✘] Physics Project (by: 4 December 2020 06:00 PM)
3.[E][✘] Family Christmas party (at: 25 December 2020 06:00 PM to 11:00 PM)
```
### Marking a task as done - **`done`**
Marks a task in the task list as done based on a specified index. The index refers to the index number shown
in the task list displayed when `list` is called. 

Format: `done INDEX`

Example:
```javascript
list
done 1
```

Expected outcome:
```javascript
Here are the tasks in your list:
-------------------------------------------------------------
1.[T][✘] Buy bread
2.[D][✘] Physics Project (by: 4 December 2020 06:00 PM)
3.[E][✘] Family Christmas party (at: 25 December 2020 06:00 PM to 11:00 PM)
```

When `delete 1` is entered.
```javascript
Splendid! I've marked the following task as done:
-------------------------------------------------------------
  [✓] Buy bread
```
### Deleting a task - **`delete`**
Deletes a task from the task list based on a specified index. The index refers to the index number shown
in the task list displayed when `list` is entered. 

Format: `delete INDEX`

Example:
```javascript
list
delete 1
```

Expected outcome:

When `list` is entered.
```javascript
Here are the tasks in your list:
-------------------------------------------------------------
1.[T][✓] Buy bread
2.[D][✘] Physics Project (by: 4 December 2020 06:00 PM)
3.[E][✘] Family Christmas party (at: 25 December 2020 06:00 PM to 11:00 PM)
```

When `delete 1` is entered.
```javascript
No worries, the following task has been deleted from your list:
-------------------------------------------------------------
  [✓] Buy bread
-------------------------------------------------------------
Marvellous! Now you have 2 tasks in your list!
```
### Finding tasks via keyword - **`find`**
Finds tasks that contains a keyword, casing of keyword does not matter. Multiple words are not acceptable.

Format: `find KEYWORD`

Example:
`find project`

Expected outcome:
```javascript
Splendid! Here are the tasks in your list that matches 'project':
-------------------------------------------------------------
1.[D][✘] Physics Project (by: 4 December 2020 06:00 PM)
```
### Finding tasks via date and time - **`finddt`**
Finds tasks that matches the date. Only applicable to deadline and event tasks.

Format: `finddt DATE`

Example:
`finddt 2020-12-25`

Expected outcome:
```javascript
Splendid! Here are the tasks in your list that matches '2020-12-25':
-------------------------------------------------------------
1.[E][✘] Family Christmas party (at: 25 December 2020 06:00 PM to 11:00 PM)
```
### Sorting task list alphabetically - **`sort`**
Sorts the task list lexicographically. 

Format: `sort`

### Sorting task list based on date and time - **`sortdt`**
Sorts the task list in increasing order based on date and time. Event tasks will be sorted based on their start 
time and todo tasks will be sorted to the bottom on the task list.

Format: `sortdt`
### Exiting the application - **`bye`**
Closes and exits DukeBunny.

Format: `bye`
### Saving task list
DukeBunny data is automatically saved into the TaskList.txt file after any command that changes the data is executed.
DukeBunny will always draw the data of the task list from the TaskList.txt file.

## Command Summary
Action | Format/Examples
------------ | -------------
Add Todo | `todo DESCRIPTION` <br> e.g. `todo Buy bread`
Add deadline | `deadline DESCRIPTION /by DATE TIME` <br> e.g. `deadline Physics Project /by 2020-12-04 1800` 
Add event | `event DESCRIPTION /AT DATE START_TIME-END_TIME` <br> e.g. `event Family Christmas party /at 2020-12-25 1800-2300`
View list | `list`
Mark done | `done INDEX` <br> e.g. `done 1`
Delete | `delete INDEX` <br> e.g. `delete 1`
Find tasks (keyword)  | `find KEYWORD` <br> e.g. `find project`
Find tasks (date) | `finddt DATE` <br> e.g. `finddt 2020-12-25`
Sort alphabetically | `sort`
Sort based on date and time | `sortdt`
Exit | `bye`