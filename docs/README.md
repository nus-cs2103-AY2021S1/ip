# User Guide

## Introduction
Duke is a **Command Line Interface (CLI) optimized** Task Manager application which helps you to organize and set up your to-do tasks, events and deadlines.

## Quick Start  
  1. Ensure you have Java `11` or above installed in your Computer.
  2. Download the latest `duke-v0.2.jar` from [here](https://github.com/ducbinh2611/ip/releases/tag/A-Release).
  3. Copy the file to the folder you want to use as the home folder for your Duke.
  4. Double-click `duke-v0.2.jar` to start the application. The GUI should appear with a greeting  
    • If you encounter `duke.txt file does not exist` error. Close the application and run it again, the error will be fixed.

## Features 
Overview of available features:  
  * `list`  
  * `todo`  
  * `deadline`  
  * `event`  
  * `delete`  
  * `done`  
  * `find`  
  * `sort`  
  * `bye` 

### Feature 1: Viewing the task list
Views all the tasks in your current task list: `list` 

Format: `list`  

Example: `list`  

Expected example outcome:  
```
Quack! Your list is here:
    1.[T][✓] play dote (Priority: low)
    2.[T][✘] plan for date (Priority: high)
    3.[D][✘] CS2100 assignment (by: Sep 20 2020) (Priority: low)
    4.[T][✘] laundry (Priority: low)
```

### Feature 2: Adding a todo task
Adds a todo task to the task list: `todo`  

Format: `todo [description] /priority [priority]`  

Remarks:  
  * `[description]` can have spaces.  
  * `[description]` is case-sensitive.  
  * `[priority]` can take one of `high`, `medium` or `low`.  
  * `[priority]` field is optional. If it is not indicated, priority is set to `low`.  

Example: `todo do CS2103T iP increment`  

Expected example outcome:
```
Quack Quack Quack. I've added this task: 
    [T][✘] do CS2103T increment (Priority: low)
Master, you now have 5 items in the list! Quack! Quack!
```

### Feature 3: Adding a deadline task
Adds a deadline task to the task list: `deadline`  

Format: `deadline [description] /by [date] /priority [priority]` or `deadline [description] /by [date] [time] /priority [priority]`  

Remarks:  
  * `[description]` can have spaces.  
  * `[description]` is case-sensitive.
  * `[time]` field is optional.  
  * `[date]` `[time]` must follow this format YYYY-MM-DD HH:mm.  
  * `[priority]` can take one of `high`, `medium` or `low`.  
  * `[priority]` field is optional. If it is not indicated, priority is set to `low`.  

Example: `deadline CS2100 assignment /by 2020-09-18 23:59`  

Expected example outcome:  
```
Quack Quack Quack. I've added this task: 
    [D][✘] CS2100 assignment  (by: Sep 18 2020 23:59) (Priority: low)
Master, you now have 6 items in the list! Quack! Quack!
```

### Feature 4: Adding an event task
Adds an event task to the task list: `event`  

Format: `event [description] /at [date] /priority [priority]` or `event [description] /at [date] [time] /priority [priority]`  

Remarks:  
  * `[description]` can have spaces.  
  * `[description]` is case-sensitive.
  * `[time]` field is optional.  
  * `[date]` `[time]` must follow this format YYYY-MM-DD HH:mm.  
  * `[priority]` can take one of `high`, `medium` or `low`.  
  * `[priority]` field is optional. If it is not indicated, priority is set to `low`.  

Example: `event The International 10 /at 2021-08-15 /priority medium`  

Expected example outcome:  
```
Quack Quack Quack. I've added this task: 
    [E][✘] The International 10  (at: Aug 15 2021) (Priority: medium)
Master, you now have 7 items in the list! Quack! Quack!
```

### Feature 5: Deleting a task in the task list  
Deletes a given task from your task list: `delete`  

Format: `delete [index]`  

Remarks:  
  * `[index]` in the index of the task appeared in the task list.  
	
Example: `delete 6`  

Expected example outcome:
```
Quackkk. I've removed this task for you: 
    [D][✘] CS2100 assignment  (by: Sep 18 2020 23:59) (Priority: low)
Master, you now have 6 items in the list! Quack! Quack!
```

### Feature 6: Marking a task as done
Marks a given task as done: `done`  

Format: `done [index]`  

Remarks:  
  * `[index]` in the index of the task appeared in the task list.  
	
Example: `done 2`  

Expected example outcome:  
```
QUACKK! I've marked this task as done:
    [T][✓] plan for date  (Priority: high)
```

Note that the corresponding task is changed from:  
```
[T][✘] plan for date  (Priority: high)
```
to
```
[T][✓] plan for date  (Priority: high)
```

### Feature 7: Finding tasks
Finds tasks such that their description contains a given keyword: `find`  

Format: `find [keyword]`  

Remarks:  
  * `keyword` is case sensitive. For instance `CS2100` will only match `CS2100`, and will not match `cs2100`.  
  * `keyword` can contain only one complete word.  
	
Example: `find play`  

Expected example outcome:  
```
Quack Quack! Here are the matching tasks in your list:
    1.[T][✓] play dote (Priority: low)
```

### Feature 8: Sorting the task list  
Sorts the task list according to either alphabetical order or priority order: `sort`  

Format: `sort` or `sort priority`

Example: `sort`  

Expected example outcome:
```
Quack! Here is your task list sorted alphabetically:
    1.[D][✘] CS2100 assignment  (by: Sep 18 2020 23:59) (Priority: low)
    2.[E][✘] The International 10  (at: Aug 15 2021) (Priority: medium)
    3.[T][✘] do CS2103T increment (Priority: low)
    4.[T][✘] laundry (Priority: low)
    5.[T][✓] plan for date  (Priority: high)
    6.[T][✓] play dote (Priority: low)
```

Example: `sort priority`  

Expected example outcome:  
```
Quack! Here is your task list sorted based on priority:
    1.[T][✓] plan for date  (Priority: high)
    2.[E][✘] The International 10  (at: Aug 15 2021) (Priority: medium)
    3.[T][✓] play dote (Priority: low)
    4.[T][✘] laundry (Priority: low)
    5.[T][✘] do CS2103T increment (Priority: low)
    6.[D][✘] CS2100 assignment  (by: Sep 18 2020 23:59) (Priority: low)
```

### Feature 9: Exiting the program  
Exits the program: `bye`  

Format: `bye`  

Example: `bye`  

Expected outcome: The program is closed.

