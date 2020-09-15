# Duke user guide

This is Duke, an application that helps you to manage your life. It can store tasks, deadlines, and remember what you need.

Duke is a CLI (command line interface) application. You will type your commands and Duke will listen!

* [List of all features](#features)
    * [Adding a todo task : **`add todo`**](#adding-todo)
    * [Adding a deadline : **`add deadline`**](#adding-deadline)
    * [Adding an event : **`add event`**](#adding-event)
    * [Listing tasks : **`list`**](#listing-tasks)
    * [Mark done tasks  : **`done`**](#marking-as-done) 
    * [Finding tasks with keywords : **`find`**](#finding)
    * [Deleting a task : **`delete`**](#deleting) 
    * [Quit the app :  **`bye`**](#bye)

## Features 

### Features 

### Adding a task : **`add todo`**
Adds a todo task.
Format: `add todo <description>`  
* Note: You cannot add two tasks with the same \<description>

Examples:  
- **`add todo brew Coffee`**. Adds a todo task noted as "brew Coffee" 


### Adding a deadline : **`add deadline`**
Adds a deadline with a specific date  
Format: `add deadline <description> /by YYYY-MM-DD`  
* Note: You cannot add two deadlines with the same \<description> and due dates.  

Examples:  
- **`add deadline IP release /by 2020-09-18`**

### Adding an event: **`add event`**
Adds a new event
Format: `add event <description> /at <date/place>`  
* Note: You cannot add two deadlines with the same \<description> and \<date/place. 

Examples:  
- **`event IOI 2020 /at NUS`**
- **`event LOL Worlds /at 2020-10-03`**

### Listing all current tasks : **`list`**
Shows a list of all current tasks.
Format: `list`



### Deleting a task : **`delete`**
Deletes a task. 
Format: `delete <index>`  
* \<index> counts from 1 to number of current tasks. It matches with the shown list.

Examples:
- `delete 1`

### Marking a task as done: **`done`**
Marks a task as done.  
Format: `done <index>`

* \<index> counts from 1 to number of current tasks. It matches with the shown list.

Examples:
- `done 1`

### Finding tasks with keywords : **`find`**
Find all the tasks that contains the given keyword.
Format: `find <keyword>`

Examples:
* `find IOI`. Duke will show all the tasks with IOI as a sub-sequence in their descriptions.


### Quitting Duke: `bye`

Duke will shut down and save all the data.


## Command Summary
|          Action          |                          Format                          |
| :----------------------: | :------------------------------------------------------: |
|Adding a todo task|`add todo <description>`|
|Adding a deadline|`add deadline <description> /by <deadline>`|
|Adding an event|`add event <description> /at <date/place>`|
|Listing tasks|`list`|
|Deleting a task|`delete <task index>`|
|Marking a task as done|`done <task index>`|
|Finding tasks with keywords|`find <querystring>`|
|Quitting Duke|`bye`|

