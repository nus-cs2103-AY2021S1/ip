# User Guide
Duke is a desktop app for managing tasks, optimized for use via a Command Line Interface (CLI) while still having the 
benefits of a Graphical User Interface (GUI). If you can type fast, Duke can get your tasks management done faster than 
traditional GUI apps.

## Features 
Usage | Format, Examples
------- | ---------------
**todo** | `todo [description]`<br/>e.g. `todo todo 1`
**deadline** | `deadline [description] /by [yyyy-MM-dd]`<br/>e.g. `deadline deadline 1 /by 2020-09-15`
**event** | `event [description] /at [yyyy-MM-dd h:mm a]`<br/>e.g. `event event 1 /at 2020-09-15 10:51 am`
**done** | `done [index]`<br/>e.g. `done 3`
**find** | `find [keywords]`<br/>e.g. `find eve`
**delete** | `delete [index]`<br/>e.g. `delete 4`
**list** | `list`
**schedule** | `schedule [yyyy-MM-dd]`<br/>e.g. `schedule 2020-09-15`
**bye** | `bye`

Action | Format, Examples
--------|------------------
**Add** | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​` <br> e.g., `add n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 t/friend t/colleague`
**Clear** | `clear`
**Delete** | `delete INDEX`<br> e.g., `delete 3`
**Edit** | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`
**Find** | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`
**List** | `list`
**Help** | `help`
## Usage

### `todo` - Adding todo task

Adds a todo task to the list.

Example of usage: 

`todo [description]`

Input:

`todo todo 1`

Expected outcome:
```
Got it. I've added this task: 
    [T][✘] todo 1
Now you have 9 tasks in the list.
```
### `deadline` - Adding deadline task

Adds a deadline task with a Date to the list.

Example of usage: 

`deadline [description] /by [yyyy-MM-dd]`

Input:

`deadline deadline 1 /by 2020-09-15`

Expected outcome:
```
Got it. I've added this task: 
    [D][✘] deadline 1 (by: Sep 15 2020)
Now you have 8 tasks in the list.
```

### `event` - Adding event task

Adds an event task with a DateTime to the list.

Example of usage: 

`event [description] /at [yyyy-MM-dd h:mm a]`

Input:

`event event 1 /at 2020-09-15 10:32 am`

Expected outcome:

```
Got it. I've added this task: 
    [E][✘] event 1 (at: Sep 15 2020 10:32 am)
Now you have 7 tasks in the list.
```


### `done` - Marking a task as done

Marks a task in the list as done.

Example of usage: 

`done [index]`

Input:

`done 5`

Expected outcome:

```
Nice! I've marked this task as done: 
    [T][✓] todo 1
```


### `find` - Finding tasks

Finds tasks that contain any of the given keywords.

Example of usage: 

`find [keyword]`

Input:

`find todo`

Expected outcome:

```
Here are the matching tasks in your list: 
    1. [T][✘] todo 1
    2. [T][✘] todo 2
```


### `delete` - Deleting a task

Remove a task from the list.

Example of usage: 

`delete [index]`

Input:

`delete 4`

Expected outcome:

```
Noted. I've removed this task: 
    [T][✘] todo 1
Now you have 4 tasks in the list.
```


### `list` - Listing all tasks

Shows a list of all tasks in Duke.

Example of usage: 

`list`

Input:

`list`

Expected outcome:

```
Here are the tasks in your list: 
    1. [T][✘] todo 1
    2. [D][✘] deadline 1 (by: Sep 20 2020)
    3. [E][✘] event 1 (at: Sep 21 2020 7:00 pm)
    4. [T][✓] todo done
```




### `schedule` - Listing all tasks by Date

Shows a list of all tasks that has the specific date in Duke.

Example of usage: 

`schedule [yyyy-MM-dd]`

Input:

`schedule 2020-09-20`

Expected outcome:

```
Here are the schedule on Sep 20 2020: 
  1. [D][✘] exam (by: Sep 20 2020)
```


### `bye` - Exiting the program

Exits the program

Example of usage: 

`bye`
