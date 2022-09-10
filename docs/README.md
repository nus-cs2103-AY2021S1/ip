# Dutch Task Tracker

Dutch Task Tracker is a task tracking application (as the name implies). It was made for the iP part of CS2103 at NUS. 
With this application, one can track different kinds of tasks and their deadlines. It was named after the character "Dutch van der Linde" from 
the video game "Red Dead Redemption 2".

## User Guide
### Adding tasks
There are 3 kinds of tasks that can be added. The command syntax is also shown below.
1. Todo: todo **taskDescription**
2. Events: event **taskDescription** /at **date** 
3. Deadlines: deadline **taskDescription** /by **date**
  
Take note that the **date** has to be in YYYY-MM-DD format.
   
The application will then return a confirmation message if the task was added successfully, and an error message if some part of the command was wrong.

### Listing tasks
The syntax for listing all currently stored tasks: list

The application will then list all currently stored tasks, as well as its index number.

### Deleting tasks
The syntax for deletion is as follows: delete **taskIndex**
   
The **taskIndex** refers the number attached to the task when "list" is used.

### Tagging tasks
The syntax for tagging is as follows: tag **taskIndex** **tagDescription**

The **taskIndex** refers the number attached to the task when "list" is used.

The **tagDescription** should be a string with not spaces within.

### Find tasks
The syntax for searching is as follows: find **searchTerm**

The application will then list all the tasks that contain the search term fully. For dates, the input has be in DD-MMM-YYYY format.
The search is not case-sensitive.

### Saving and exiting
The syntax for exiting is as follows: bye

This will save your current task list into a .txt file and exit the applicaiton.
