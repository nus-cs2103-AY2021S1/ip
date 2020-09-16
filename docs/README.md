# Bob User Guide

## Features
1. `todo`: Add todo tasks to your list.
2. `deadline`: Add tasks with a deadline to your list.
3. `event`: Add event tasks to your list.
4. `list`: Displays all tasks currently in your list
5. `find`: Searches and displays all tasks in your list that contains your specified keyword.
6. `done`: Mark specified task as done.
7. `delete`: Delete specified task from your list.
8. `exit`: Exits and closes the program.

## Usage

### 1. `todo`
Adds a todo task with a description to your list.

**Format:** `todo <description>` 

*Example command*: `todo homework`


*Expected outcome*:
```
Noted! I have added the following task to your list:
[T][✘] homework
You now have 1 task(s) in your list

Successfully saved task list to file :)
```

### 2. `deadline`
Adds a task with a description and a deadline date to your list. Deadline date should be of the form **yyyy-MM-dd HH:mm**

**Format:** `deadline <description> /by <deadline_date>` 

*Example command*: `deadline quiz /by 2020-01-01 18:00`


*Expected outcome*:
```
Noted! I have added the following task to your list:
[D][✘] quiz (by: 1 Jan 2020, Wednesday 06:00 PM)
You now have 2 task(s) in your list

Successfully saved task list to file :)
```

### 3. `event`
Adds a task with a description and an event date to your list. Event date should be of the form **yyyy-MM-dd HH:mm**

**Format:** `event <description> /at <event_date>` 

*Example command*: `event party /at 2020-12-25 20:00`


*Expected outcome*:
```
Noted! I have added the following task to your list:
[E][✘] party (at: 25 Dec 2020, Friday 08:00 PM)
You now have 3 task(s) in your list

Successfully saved task list to file :)
```


### 4. `list`
Displays all tasks currently in your list. If your list does not contain any tasks, Bob will notify you

**Format:** `list` 

#### If the list is not empty:
*Example command*: `list`


*Expected outcome*:
```
Your current task list is as follows:
1.[T][✘] homework
2.[D][✘] quiz (by: 1 Jan 2020, Wednesday 06:00 PM)
3.[E][✘] party (at: 25 Dec 2020, Friday 08:00 PM)

Successfully saved task list to file :)
```

#### If the list is empty:
*Example command*: `list`


*Expected outcome*:
```
List is empty :(
```

### 5. `find`
Searches and displays all tasks in your list that contains your specified keyword. If no task in your list contains your specified keyword, Bob will notify you.

**Format:** `find <keyword>` 

#### If there are tasks that fit your keyword:
*Example command*: `find 2020`


*Expected outcome*:
```
Here are the tasks that matched with '2020':
1.[D][✘] quiz (by: 1 Jan 2020, Wednesday 06:00 PM)
2.[E][✘] party (at: 25 Dec 2020, Friday 08:00 PM)
```

#### If there are no tasks that fit your keyword:
*Example command*: `find hey`


*Expected outcome*:
```
List is empty :(
```

### 6. `done`
Marks the task with the list index you specified as done with a tick. List index should be a numerical number.

**Format:** `done <list_index>` 

#### If the list index you specified exists:
*Example command*: `done 1`


*Expected outcome*:
```
Good job completing this task! I've marked this task as done:
[T][✓] homework
Keep up the good work :)
```

#### If the list index you specifed does not exist:
*Example command*: `done 5`


*Expected outcome*:
```
OOPS! Task 5 does not exist.
Please make sure task index is correct.
```

#### If the list index you specifed is not a numerical number:
*Example command*: `done two`


*Expected outcome*:
```
OOPS! Please enter a numerical number :)
```

### 7. `delete`
Deletes the task with the list index you specified from the list. List index should be a numerical number.

**Format:** `done <list_index>` 

#### If the list index you specified exists:
*Example command*: `delete 1`


*Expected outcome*:
```
Noted! I have deleted the following task from your list:
[T][✓] homework
You now have 2 task(s) in your list

Successfully saved task list to file :)
```

#### If the list index you specifed does not exist:
*Example command*: `delete 5`


*Expected outcome*:
```
OOPS! Task 5 does not exist.
Please make sure task index is correct.
```

#### If the list index you specifed is not a numerical number:
*Example command*: `delete two`


*Expected outcome*:
```
OOPS! Please enter a numerical number :)
```

### 8. `exit`
Exits and closes the program. Bob will say goobye before the program closes.

**Format:** `exit` 

*Example command*: `exit`


*Expected outcome*:
```
Goodbye! Have a nice day :D
```
