# Duke User Guide

1. **Introduction**

2. **Quick Start**

3. **Features**

    1. Adding a todo: `todo`
    
    2. Adding a deadline: `deadline`
    
    3. Adding an event: `event`
    
    4. Listing all tasks: `list`
    
    5. Marking a task as done: `done`
    
    6. Deleting a task: `delete`
    
    7. Finding a task: `find`
    
    8. Undo a command: `undo`
    
    9. Exiting the program: `bye`
    
4. **Command Summary**

----------------------------------------------------
    
### 1. Introduction
 
Duke is a chatbot for managing tasks, optimized for use with a 
Graphical User Interface (GUI)

### 2. Quick Start
1. Ensure you have Java 11 or above installed in your computer
2. Download the latest duke.jar from [here](https://github.com/IlyaRin/ip/releases/tag/A-Release)
3. Double click the file to start the app.
4. Type the command in the input box and press Enter to execute it

### 3. Features
 
#### Adding a todo: `todo`
Adds a todo to the task list

Format: `todo DESCRIPTION`

Examples:
* `todo buy bread`
* `todo eat`




#### Adding a deadline: `deadline`
Adds a deadline to the task list

Format: `deadline DESCRIPTION /by YYYY-MM-DD`

Examples:
* `deadline do tutorial /by 2020-09-15`
* `deadline watch lecture /by 2020-09-18`




#### Adding an event: `event`
Adds an event to the task list

Format: `event Description /at YYYY-MM-DD`

Examples
* `event ice skating /at 2020-09-17`
* `event watch movie /at 2020-09-22`




#### Listing all tasks: `list`
Displays all tasks in the list

Format: `list`




#### Marking a task as done: `done`
Sets a task in the list as done

Format: `done INDEX`

* Marks the task at the specified `INDEX` as done
* Index must be a positive integer 1, 2...

Examples: 
* `done 3`
* `done 13`




#### Deleting a task: `delete`
Deletes a task from the list

Format `delete INDEX`

* Deletes the task at the specified `INDEX`
* Index must be a positive integer 1, 2...

Examples:
* `delete 4`
* `delete 10`




#### Finding a task: `find`
Find tasks in the list based on description

Format `find DESCRIPTION`

* Partial `DESCRIPTION` can be given to locate tasks

Examples:
* `find eat`
* `find ea`




#### Undo a command: `undo`
Reverses the latest command that changes the task list

Format `undo`

* Tasks that change the list are
    1. `todo`
    2. `deadline`
    3. `event`
    4. `done`
    5. `delete`



#### Exiting the program: `bye`
Exits the program

Format: `bye`


### 4. Command Summary 

Action | Format
-------| ------------------
todo | `todo DESCRIPTION`
deadline | `deadline DESCRIPTION /by YYYY-MM-DD`
event | `event DESCRIPTION /at YYYY-MM-DD`
list | `list`
done | `done INDEX`
delete | `delete INDEX`
find | `find DESCRIPTION`
undo | `undo`
bye | `bye`

