# Duke Chatbot User Guide


1. [**Introduction**](Introduction)
2. [**Quick Start**](Quick Start)
3. [**Features**](Features)
    1. [Listing all tasks: `list`](Listing all tasks: `list`)
    2. [Adding a task: `add`](Adding a task: `add`)
    3. [Marking a task as done: `done`](Marking a task as done: `done`)
    4. [Deleting a task: `delete`](Deleting a task: `delete`)
    5. [Finding tasks by name: `find`](Finding tasks by name: `find`) 
    6. [Exiting the program: `bye`](Exiting the program: `bye`)   
    7. [Saving/loading data](Saving/loading data)   

## Introduction
Welcome to the Duke Chatbot User Guide! Duke Chatbot are for users who prefer to use a desktop application for their task management. Duke Chatbot is optimised for users who prefer to use the Command Line Interface (CLI) while reaping the visual benefits of a Graphical User Interface (GUI).
Duke Chatbot has several features to help you manage your tasks. Sounds interesting? Head over to [**Quick Start**](Quick Start) to get started!



## Quick Start
* Ensure that you have `java 11` installed on your computer.
* Download the latest `duke.jar` [here]().
* Double-click the downloaded `duke.jar` to launch the app!
* The GUI below should appear after a few seconds.

![image](https://github.com/AaronnSeah/ip/blob/master/src/main/resources/images/startUp.png?s=200)
* Now you can explore the features of Duke Chatbot!
* Head over to [**Features**](Features) for detailed explanation of each command.



## Features 

3.1 [Listing all tasks: `list`](Listing all tasks: `list`)

List all of your tasks.

Format: `list`

![image](https://github.com/AaronnSeah/ip/blob/master/src/main/resources/images/list.png)

3.2 [Adding a task: `add`](Adding a task: `add`)

Add a new task.

A newly added task is not done by default.

You can add 3 kinds of tasks:
1. Todo Task
2. Event Task
3. Deadline Task

Adding a Todo Task
* format: `todo <task name>`
![image](https://github.com/AaronnSeah/ip/blob/master/src/main/resources/images/todo.png)

Adding an Event Task
* format: `event <task name> /at <date> <start time>-<end time>`
* format for <date>: DD/MM/YYYY
* format for <start time> and <end time>: HH:MM
* do note the `-` between the <start time> and <end time>        
![image](https://github.com/AaronnSeah/ip/blob/master/src/main/resources/images/event.png)

Adding a Deadline Task
* format: `deadline <task name> /by <date> <time>`
* format for <date>: DD/MM/YYYY
* format for <time>: HH:MM

![image](https://github.com/AaronnSeah/ip/blob/master/src/main/resources/images/deadline.png)

3.3 [Marking a task as done: `done`](Marking a task as done: `done`)

Mark the task to be done.

:warning: You mark a task to be undone.

Format: `done <task number>`

To find the <task number>, use the `list` command.

The <task number> is the number ordering in the list for the task you would like to mark as done.

![image](https://github.com/AaronnSeah/ip/blob/master/src/main/resources/images/delete.png)

3.4 [Deleting a task: `delete`](Deleting a task: `delete`)

Deletes a task from the application.

:warning: You cannot recover a deleted task.

Format: `delete <task number>`

To find the <task number>, use the `list` command.

The <task number> is the number ordering in the list for the task you would like to mark as done.

![image](https://github.com/AaronnSeah/ip/blob/master/src/main/resources/images/done.png)


3.5 [Finding tasks by name: `find`](Finding tasks by name: `find`) 
Find tasks by name.
Format: `find <keyword>`
The order of tasks that appear (if it is non-empty) is as follows:
    1. tasks whose name matches exactly with the search keyword. e.g. "book" and "book"
    2. tasks whose one of the space-delimited name matches exactly with the search keyword. e.g. "book signing" and "book"
    3. tasks whose name matches contains search keyword. e.g. "book signing" and "k s"
    
![image](https://github.com/AaronnSeah/ip/blob/master/src/main/resources/images/find.png)


3.6 [Exiting the program: `bye`](Exiting the program: `bye`) 

Exits the application.

Format: `bye`
  
3.7 [Saving/loading data](Saving/loading data)   

The tasks you have entered will be loaded on the application start up.

Any data change will only be saved by running the `bye` command.


