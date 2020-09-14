# KirbyBot 🌟

Kirbybot is a bot that helps to manage tasks.
![Ui](/Ui.png)

## Installation

Download the java application under release [v2.0](hhttps://github.com/Jillzyt/ip/releases/tag/v0.2) to start your journey on task
 management.

## Usage via java
Run the java application. 

## Features of the application
### 1. Add Tasks (Deadline, Todo, Events type)
Add todo to the task list.  
Command: todo {DESCRIPTION}  
Example: todo revise homework  
![todo](/images/todo.png)

Add an event to the task list.  
Command: event {DESCRIPTION} /at {DUEDATE} {TIME}  
Example: event mark homework /at 2019-02-18 18:00  
![event](/images/event.png)

Add a deadline to the task list.  
Command: deadline {DESCRIPTION} /by {DUEDATE} {TIME}  
Example: deadline do homework /by 2019-02-18 18:00  
![deadline](/images/deadline.png)

### 2. Delete Tasks
Delete the tasks identified by the index number used in the task list.  
Command: delete {INDEX}  
Example: delete 1  
![delete](/images/delete.png)

### 3. List all Tasks
List all the tasks in the task list.  
Command: list  
Example: list  
![list](/images/list.png)

### 4. Complete Tasks
Mark a task done in the task list.  
Command: done {INDEX}  
Example: done 2  
![done](/images/done.png)

### 5. Update description of Tasks
Update the description of a task in the task list.  
Command: update {INDEX}  
Example: update 2 do homework  
![update](/images/update.png)

### 6. Storage feature
The file of your tasks will be saved in the directory ./data/duke.txt.


## Editing the code (Pull requests)
### To run the application
Run the file Launcher.java or run the file gradlew.bat / gradle.sh base on your OS.  
Ensure Java 11 SDK is installed. 


## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.

