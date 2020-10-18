# N Chat Bot User Guide

![UiWindow](Ui.png)

## Features

N Chat Bot is an application that helps you maintain your todo tasks.

## Usage

### 1. `list` - List out all current tasks

N Chat Bot will return you a list of all tasks you currently have stored in N Chat bot, regardless of its status.

Format: `list`

Example: 
```
list
```

Expected Outcome:
```
Here are the tasks in your list:
1. [T][✗] return books
2. [T][✓] borrow books
```

### 2. `todo` - Add a todo task

Add a todo task with the given description in N Chat Bot. The task will be added before the first task with status 
done. 

Format: `todo DESCRIPTION`

Example: 
```
todo do assignment 1
```

Expected Outcome:
```
You have added [T][✗] do assignment 1!
Now you have these tasks:
    1. [T][✗] return books
    2. [T][✗] do assignment 1
    3. [T][✓] borrow books
```

### 3. `deadline` - Add a Deadline Task

Add a deadline task with the given description and due date.

Format: `deadline DESCRIPTION /by YYYY-MM-DD`

Example: 
```
deadline programming assignment /by 2020-11-24
```

Expected Outcome:
```
You have added [D][✗] programming assignment (by: Nov 24 2020)!
Now you have these tasks:
    1. [T][✗] return books
    2. [T][✗] do assignment 1
    3. [D][✗] programming assignment (by: Nov 24 2020)
    4. [T][✓] borrow books
```

### 4. `event` - Add an event task

Add an event task with the given description and due date.

Format: `event DESCRIPTION /at YYYY-MM-DD`

Example: 
```
event birthday party /at 2020-10-13
```

Expected Outcome:
```
You have added E][✗] birthday party (at: Oct 13 2020)!
Now you have these tasks:
    1. [T][✗] return books
    2. [T][✗] do assignment 1
    3. [D][✗] programming assignment (by: Nov 24 2020)
    4. [E][✗] birthday party (at: Oct 13 2020)
    5. [T][✓] borrow books
```


### 5. `done` - Mark a task as done

Mark the task at the specified index as done. The marked task will be moved to the last of the list. <br>
After the task is marked as done, N Chat Bot will display all tasks with status incomplete. 

Format: `done INDEX` where `INDEX` refer to the index of the previous displayed list of tasks.

Example:<br>
Previously displayed list:
```
You have added E][✗] birthday party (at: Oct 13 2020)!
Now you have these tasks:
    1. [T][✗] return books
    2. [T][✗] do assignment 1
    3. [D][✗] programming assignment (by: Nov 24 2020)
    4. [E][✗] birthday party (at: Oct 13 2020)
    5. [T][✓] borrow books
```
1st Command:
```
done 2
```
Expected Outcome (with displayed list for the next command):
```
You have finished do assignment 1!
Move on to the next one:
    1. [T][✗] return books
    2. [D][✗] programming assignment (by: Nov 24 2020)
    3. [E][✗] birthday party (at: Oct 13 2020)
```
2nd Command:
```
done 2
```
Expected Outcome:
```
You have finished programming assignment 1!
Move on to the next one:
    1. [T][✗] return books
    2. [E][✗] birthday party (at: Oct 13 2020)
```

### 6. `delete` - Delete a task

Delete the task with the specified index. <br>
After the task is deleted, N Chat Bot will display all the tasks stored in N Chat Bot. 

Format: `delete INDEX` where `INDEX` refer to the index of the previous displayed list of tasks.

Example:<br>
Previously displayed list:
```
Here are the tasks in your list:
    1. [T][✗] return books
    2. [E][✗] birthday party (at: Oct 13 2020)
    3. [T][✓] borrow books
    4. [T][✓] do assignment 1
    5. [D][✓] programming assignment (by: Nov 24 2020)
```
1st Command:
```
delete 4
```
Expected Outcome (with displayed list for the next command):
```
do assignment 1 deleted.
Check out other tasks:
    1. [T][✗] return books
    2. [E][✗] birthday party (at: Oct 13 2020)
    3. [T][✓] borrow books
    4. [D][✓] programming assignment (by: Nov 24 2020)
```
2nd Command:
```
done 4
```
Expected Outcome:
```
programming assignment deleted.
Check out other tasks:
    1. [T][✗] return books
    2. [E][✗] birthday party (at: Oct 13 2020)
    3. [T][✓] borrow books
```

### 7. `find` - Find a task by description

Find tasks containing the search phrase in the description. 

Format: `find SEARCH_PHRASE`

Example:
```
find return
```

Expected Outcome:
```
You hvae these tasks containing "return":
    1. [T][✗] return books
```

### 8. `on` - Find a task on a specific date

Find tasks with the specified date.

Format: `on DATE`

Example:
```
on 2020-10-13
```

Expected Outcome:
```
You hvae these tasks on this date:
    1. [E][✗] birthday party (at: Oct 13 2020)
```

### 9. `snooze` - Postpone a task to a later date

Postpone a task to the specified new date. <br>
After the task is postponed, N Chat Bot will display all the tasks stored in N Chat Bot. 

Format: `snooze INDEX NEW_DATE` where `INDEX` refer to the index of the previous displayed list of tasks.

Example:<br>
Previously displayed list:
```
programming assignment deleted.
Check out other tasks:
    1. [T][✗] return books
    2. [E][✗] birthday party (at: Oct 13 2020)
    3. [T][✓] borrow books
```

Command

```
snooze 2 2020-12-23
```

Expected Outcome:
```
birthday party is snoozed to new date: 2020-12-23
    1. [T][✗] return books
    2. [E][✗] birthday party (at: Dec 23 2020)
    3. [T][✓] borrow books
```

### 10. `bye` - Exit the application

Exit the application. 

Format: `bye`

Example:
```
bye
```

Expected Outcome: Application exits.


## Author

Li Gangwei
