# User Guide

## Features 
* Add a task to do: `todo`
* Add a task with a deadline: `deadline`
* Add an event: `event`
* Remove a task: `remove`
* Mark task as done: `done`
* Find task by keyword: `find`
* List tasks: `list`
* Exit the program: `bye`


### Add a task to do: todo
Adds a `todo` with the specified description to the task list.

Format: `todo TASK`

Example: `todo walk the dog`

Expected Outcome:
``` 
Got it. I've added this task:
     [T][x] walk the dog
Now you have 3 tasks in the list
```

### Add a task with a deadline: deadline
Adds a `deadline` with the specified description to the task list.

Format: `deadline TASK /by DATE`
* DATE format is in **YYYY-MM-DD**

Example: deadline complete individual project /by 2020-09-18`

Expected Outcome:
``` 
Got it. I've added this task:
    [D][x] complete individual project (Sep 18 2020)
 Now you have 4 tasks in the list
```

### Add an event: event
Adds an `event` with the specified description to the task list.

Format: `event TASK /at DATE`
* DATE format is in **YYYY-MM-DD**

Example: `event music festival /at 2020-09-20`

Expected Outcome:
``` 
Got it. I've added this task:
        [E][x] music festival (Sep 20 2020)
    Now you have 5 tasks in the list
```
### Remove a task: remove
Removes a task from the task list.

Format: `remove INDEX`
* Removes the task at the specified index
* The index refers to the index number of the task as shown in the list
* The index **must be a positive integer** 1,2,3

Example: `remove 2`

Expected Outcome:
``` 
Noted. I've removed this task:
       [T][x] read book
    Now you have 4 tasks in the list. 
```

### Mark task as done: done
Marks a task as done in the task list.

Format: `done INDEX`
* Marks the task at the specified index as done (using a tick)
*  The index refers to the index number of the task as shown in the list
* The index **must be a positive integer** 1,2,3

Example: `done 2`

Expected Outcome:
``` 
Nice! I've marked this task as done:
        [/] walk the dog
```

### Find task by keyword: find
Finds a task in the task list by keyword.

Format: `find KEYWORD`

Example: `find music`

Expected Outcome:
``` 
Tasks Found:
    5. [E][x] music festival (Sep 20 2020)
```

### List tasks: list
Lists the current task list.

Format: `list`

Expected Outcome:
```  
Here are the tasks in your list:
    1. [T][x] complete project 
```

### Exit the program: bye
Exits the program.

Format: `bye`

Expected Outcome:
`Bye. Hope to see you again soon!`

### Saving data
Task List data is automatically saved in the harddisk after any command that changes its data.
There is no need to save manually
















