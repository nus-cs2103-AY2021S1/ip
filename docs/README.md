# User Guide

## Features

**Notes about the command format:**  

* Words in ```UPPER_CASE``` are the parameters to be supplied by the user.  
e.g. in ```todo DESCRIPTION```, ```DESCRIPTION``` is a parameter which can be used as ```todo read book```.

* Items in square brackets are optional.  
e.g. ```/by DATE [TIME]``` can be used as ```/by 01/01/2020 0800``` or as ```/by 01/01/2020```.

### 1. Adding a todo task: ```todo```

Adds a todo task to the task list.

Format: ```todo DESCRIPTION```

Example of usage:  
```todo read book```

Expected outcome:  
```
¡Está bien! I've added this task:
    [T][✘][4] read book
Now you have 1 task in the list
```

### 2. Adding a deadline task: ```deadline```

Adds a deadline task to the task list.
The date follows the ```dd/MM/yyyy``` format while the time follows the ```HHmm``` format.
The date must be present.

Format: ```deadline DESCRIPTION /by DATE [TIME]```

Example of usage:  
```deadline return book /by 01/01/2020 1200```

Expected Outcome:  
```
¡Está bien! I've added this task:
    [D][✘][4] return book (by: Wed, 1 January 2020, 12:00pm)
Now you have 5 tasks in the list
```

### 3. Adding an event task: ```event```

Adds an event task to the task list.
The date follows the ```dd/MM/yyyy``` format while the time follows the ```HHmm``` format.

Format 1: ```event DESCRIPTION /at START_DATE-END_DATE START_TIME-END_TIME```

Format 2: ```event DESCRIPTION /at START_DATE START_TIME-END_TIME```

Example of usage:  
```event project meeting /at 01/01/2020 1400-1800```

Expected outcome:  
```
¡Está bien! I've added this task:
    [E][✘][4] project meeting (at: Wed, 1 January 2020, 2:00pm - 6:00pm)
Now you have 4 tasks in the list
```

### 4. Listing all tasks: ```list```

Shows a list of all tasks in the task list.

Format: ```list```

Example of usage:  
```list```

Expected outcome:  
```
Here are the tasks in your list:
1.[T][✓][4] read book
2.[D][✘][1] return book (by: Sat, 19 September 2020, 10:00am)
3.[E][✘][3] project meeting (at: Sun, 20 December 2020, 2:00am - 2:00pm)
4.[T][✓][2] join sports club
```

### 5. Deleting a task: ```delete```

Deletes a specified task from the task list.

Format: ```delete INDEX```  
* Deletes the task at the specified ```INDEX```.
* The index must be a positive integer 1, 2, 3, ...  

Example of usage:  
```delete 5```

Expected outcome:  
```
Entiendo! I've removed this task:
    [D][✘][4] return book (by: Wed, 1 January 2020, 12:00pm)
Now you have 4 tasks in the list
```

### 6. Marking a task as done: ```done```

Marks a specified task as done.

Format: ```done INDEX```  
* Marks a task as done at the specified ```INDEX```.
* The index must be a positive integer 1, 2, 3, ...  

Example of usage:  
```done 2```

Expected outcome:
```
¡Fantástico! I've marked this task as done:
    [D][✓][1] return book (by: Sat, 19 September 2020, 10:00am)
```

### 7. Searching for a task: ```find```

Find tasks whose description contains the given keyword.

Format: ```find KEYWORD```  
* The search is case-sensitive, e.g. ```book``` will not match ```BOOK```.
* Only the description is searched.
* Keyword can be matched partailly, e.g. ```boo``` will match ```book```.

Example of usage:  
```find book```

Expected outcome:  
```
Here are the matching tasks in your list:
1.[T][✓][4] read book
2.[D][✘][1] return book (by: Sat, 19 September 2020, 10:00am)
```

### 8. Prioritizing a task: ```priority```

Prioritizes a specified task given the priority number. 

The priority number ranges from 1 to 4:
1. Urgent (most important)
2. High
3. Medium
4. Low (least important)

By default, tasks that will be added to the task list will all have the priority number 4 initially.

Format: ```priority INDEX PRIORITY_NUMBER```  
* ```PRIORITY_NUMBER``` can only take positive integers ranging from 1 to 4.
* The index must be a positive integer 1, 2, 3, ...  

Example of usage:  
`priority 1 2`

Expected outcome:  
```
Genial! I've changed the priority of this task:
    [T][✓][2] read book
```

### 9. Exiting the program: ```bye```

Exits the program.

Format: ```bye```

### 10. Saving the data

Task List data are saved automatically after any command that changes the data. There is no need to save manually.

---

### Command Summary
Action | Format
---|---
Add todo | ```todo DESCRIPTION```
Add deadline | ```deadline DESCRIPTION /by DATE [TIME]```
Add event | <ul><li>```event DESCRIPTION /at START_DATE-END_DATE START_TIME-END_TIME```</li><li>```event DESCRIPTION /at START_DATE START_TIME-END_TIME```</li></ul>
List | ```list```
Delete | ```delete INDEX```
Done | ```done INDEX```
Find | ```find KEYWORD```
Priority | ```priority INDEX PRIORITY_NUMBER```
Bye | ```bye```