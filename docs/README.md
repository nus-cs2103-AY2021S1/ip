# User Guide
Kaizen is a simple todo desktop app that you can use to keep track of your tasks via the Command Line Interface (CLI).

## Quick start
1. Ensure that you have `java 11` and above installed on your desktop.
2. Copy the `duke.jar` to your designated folder on your desktop.
3. Open your terminal and navigate to the designated folder containing `duke.jar`.
4. Run `java -jar duke.jar` to start the program.

## Features 
1. Adding tasks to the program
2. Listing tasks
3. Marking tasks as done
4. Finding tasks
5. Deleting tasks
6. Clearing of all tasks at one go
7. Exiting the Program
8. Storing and Retrieving tasks

## Feature Details
### 1. Adding tasks
There are three types of tasks that the user can enter into the program:
1. Todo
2. Deadline
3. Event

#### `todo` - Adding a Todo task
Format: `todo TODO_DESCRIPTION`  
Adds a Todo task to the program.  
The description of the todo task is written in the TODO_DESCRIPTION.  
TODO_DESCRIPTION cannot be left blank.  

**Example of Usage:**
```
todo buy wasabi for dinner
```

**Expected Outcome:**
```
Hai! I have added this task to your list:
[TODO] [X] buy wasabi for dinner
You now have 1 tasks in your list. Gambatte!
```  

#### `deadline` - Adding a Deadline task
Format: `deadline DEADLINE_DESCRIPTION /by DEADLINE_ENDDATE DEADLINE_ENDTIME`  
Adds a Deadline task to the program.  
The description of the deadline task is written in the DEADLINE_DESCRIPTION.
The Deadline date is written in the DEADLINE_ENDDATE. DEADLINE_ENDDATE must be written in YYYY-MM-DD format.  
The Deadline time is written in the DEADLINE_END TIME. DEADLINE_ENDTIME must be written in HH:MM format.  
All of DEADLINE_DESCRIPTION, DEADLINE_ENDDATE and DEADLINE_ENDTIME cannot be left blank.  

**Example of Usage:**
```
deadline submit report on a Studio Ghibli film /by 2020-12-15 23:59
```

**Expected Outcome:**
```
Hai! I have added this task to your list:
[DEADLINE] [X] submit report on a Studio Ghibli film (by: 15 December 2020 11:59pm)  
You now have 2 tasks in your list. Gambatte!
```  

#### `event` - Adding an Event task
Format: `event EVENT_DESCRIPTION /at EVENT_AT`  
Adds an event task to the program.
The description of the event task is written in the EVENT_DESCRIPTION.  
The details of where and when the event is held is written in the EVENT_AT.  

**Example of Usage:**
```
event Go watch Olympics /at Tokyo 2021
```

**Expected Outcome:**
```
Hai! I have added this task to your list:
[EVENT] [X] event Go watch Olympics (at: Tokyo 2021)
You now have 3 tasks in your list. Gambatte!
```  
<br><br/>
### 2. `list` or `ls` - Listing tasks
Format: `list` or `ls`
Lists all tasks currently stored in the program.  
**Example of Usage:**
```
list
```

**Expected Outcome:**
```
Here are your tasks!
1. [TODO] [X] buy wasabi for dinner
2. [DEADLINE] [X] submit report on a Studio Ghibli film (by: 15 December 2020 11:59pm)  
3. [EVENT] [X] event Go watch Olympics (at: Tokyo 2021)
```  
<br><br/>
### 3. `done` - Marking tasks as done
Format: `done TASK_INDEX`  
The designated task number is entered in TASK_INDEX
Marks a designated task as done, turning the [X] into a [✓].  
**Example of Usage:**
```
done 1
```

**Expected Outcome:**
```
Sugoi! This task is done!
[TODO] [✓] buy wasabi for dinner
```  
<br><br/>
### 4. `find` - Finding tasks
Format: `find KEYWORD`  
The keyword of the task is entered in KEYWORD.  
The program will return all tasks with descriptions partially or fully matching KEYWORD.  
**Example of Usage:**
```
find film
```

**Expected Outcome:**
```
Here are all the matching tasks in your list:
2. [DEADLINE] [X] submit report on a Studio Ghibli film (by: 15 December 2020 11:59pm)
```  
<br><br/>
### 5. `delete` - Deleting tasks
Format: `delete TASK_INDEX`  
The designated task number is entered in TASK_INDEX 
The program will delete the corresponding task  
**Example of Usage:**
```
delete 1
```

**Expected Outcome:**
```
Hai! This task has been deleted!  
[TODO] [✓] buy wasabi for dinner
You now have 2 tasks in your list. Gambatte!
```  
<br><br/>
### 6. `clear` - Clearing of all tasks at one go
Format: `clear`  
Program will wipe out all current tasks  
**Example of Usage:**
```
clear
```

**Expected Outcome:**
```
All your tasks have been removed!
```  
<br><br/>
### 6. `bye` - Exiting the program
**Example of Usage:**
```
bye
```

**Expected Outcome:**
Exits the program
<br><br/>
### 7. Storing of tasks  
The data in the program persists even after you close the program.  
When you re-open the program again, the tasks from the previous run will be loaded onto the program again.  
