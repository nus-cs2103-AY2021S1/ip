# User Guide

Duke is a task manager with a Graphical User Interface(GUI). Given below are instructions on how to use it.
* 1. Quick Start
* 2. Features
    * 2.1 Adding a ToDo task `todo`
    * 2.2 Adding a Deadline task `deadline`
    * 2.3 Adding an Event task `event`
    * 2.4 Listing all tasks `list`
    * 2.5 Marking a task as done `done`
    * 2.6 Deleting a task `delete`
    * 2.7 Finding tasks that match a keyword `find`
    * 2.8 Saving the data
    * 2.9 Exiting the program `bye`
    * 2.10 Displaying the help message `help`
* 3. Usage
    * 3.1 `todo` - Add a ToDo task
    * 3.2 `deadline` - Add a Deadline task
    * 3.3 `event` - Add an Event task
    * 3.4 `list` - List all tasks
    * 3.5 `done` - Mark a task as done
    * 3.6 `delete` - Delete a task
    * 3.7 `find` - Find tasks matching a keyword
    * 3.8 `bye` - Exit the program
    * 3.9 `help` - Display the help message
* 4. Command summary
* 5. Acknowledgements

## 1. Quick Start

 1. Ensure that you have Java `11` or above installed in your computer.
 
 2. Download the latest `duke.jar` from [here](https://github.com/WM71811/ip/releases/tag/v0.3).
 
 3. Place `duke.jar` into an empty folder.
 
 4. Open duke.jar by running the command `java -jar duke.jar`. The Graphic User Interface(GUI) should appear in a few 
 seconds. Note that a data folder would be created upon opening duke.jar file. 
 
 5. Type the command in the text box and press Send to execute it.
    Some example commands you can try:
    * `list`: Lists all Tasks.
    * `todo do assignment`: Adds a ToDo task named do assignment.
    * `event meeting /at 2020-09-20`: Adds an Event task named project with the date on Sep 20 2020.
    * `deadline project /by 2020-10-10`: Adds a Deadline task named project with the time due on Oct 10 2020.
    * `done 2`: Marks the second task shown in the current list as done.
    * `delete 3`: Deletes the third task shown in the current list.
    * `find meeting`: Finds tasks that has a name that contains the word meeting and lists these tasks.
    * `bye`: exits the program.
6. Refer to Features below for details of each command.
![Figure of Dodo](Ui.png)
##### Figure 1. Screenshot of Duke

## 2. Features 
### 2.1 Adding a ToDo Task `todo`
Adds a ToDo task with its task name appended.

Format: `todo TASK_NAME`

Examples:
* `todo do readings`
* `todo finish homework`

### 2.2 Adding a Deadline Task `deadline`
Adds a Deadline task with its task name and time appended.

Format: `deadline TASK_NAME /by YYYY-MM-DD`

Examples:
* `deadline finish report /by 2020-09-21`
* `deadline write paper /by 2020-10-12`

### 2.3 Adding an Event Task `event`
Adds an Event task with its task name and time appended.

Format: `event TASK_NAME /at YYYY-MM-DD`

Examples:
* `event workshop /at 2020-09-23`
* `event project meeting /by 2020-10-15`

### 2.4 Listing all tasks `list`
Shows a list of all tasks.

Format: `list`

### 2.5 Marking a task as done `done`
Marks the specified Task in the list as done.

Format: `done INDEX`
* Marks the task at the specified INDEX as done. 
*The index refers to the index number shown in the current list of tasks. 
*The index **must be a positive integer** 1, 2, 3, …

Examples:
* `done 1` marks the first task in the list as done.
* `done 3` marks the third task in the list as done.

### 2.6 Deleting a task `delete`
Deletes the specified Task from the list.

Format: `delete INDEX`

* Deletes the task at the specified INDEX. 
* The index refers to the index number shown in the current list of tasks. 
* The index **must be a positive integer** 1, 2, 3, …

Examples:
* `delete 2` deletes the second task from the list.
* `delete 4` deletes the fourth task from the list.

### 2.7 Finding tasks that match a keyword `find`
Finds the tasks with their names matching the keyword 
and returns these tasks as a list.

Format: `find KEYWORD`

Examples:
* `find homework` returns a list of tasks that has name 
matching the word homework.
* `find project` returns a list of tasks that has name 
matching the word project.

### 2.8 Saving the data
Duke's data are saved in the data folder automatically 
after any user command that changes the data.

### 2.9 Exit the program `bye`
Exits the program.

Format: `bye`

### 2.10 Displaying the help message `help`
Displays the help message, which is a list showing the valid formats 
of user commands.

Format: `help`

## 3. Usage

### 3.1 `todo` - Add a ToDo task
Creates a ToDo task when the user input is in the right format, 
namely `todo TASK_NAME`, and adds the task into the list.

Example of usage: `todo play the violin`

Expected outcome:
```
Noted! The task below is added into the list:
[T][✘] play the violin
There are 3 tasks in total in your list.
```

### 3.2 `deadline` - Add a Deadline task
Creates a Deadline task when the user input is in the right format, 
namely `deadline TASK_NAME /by YYYY-MM-DD`, and adds the task into the list.

Example of usage: `deadline do homework /by 2020-09-26`

Expected outcome:
```
Noted! The task below is added into the list:
[D][✘] do homework (by: Sep 26 2020)
There are 4 tasks in total in your list.
```

### 3.3 `event` - Add an Event task
Creates an Event task when the user input is in the right format, 
namely `event TASK_NAME /at YYYY-MM-DD`, and adds the task into the list.

Example of usage: `event research workshop /at 2020-10-12`

Expected outcome:
```
Noted! The task below is added into the list:
[E][✘] research workshop (at: Oct 12 2020)
There are 5 tasks in total in your list.
```

### 3.4 `list` - List all tasks
Displays the list of tasks saved by Duke.

Example of usage: `list`

Expected outcome:
```
Here are the tasks in the list:
1. [T][✘] play the violin
2. [D][✘] do homework (by: Sep 26 2020)
3. [E][✘] research workshop (at: Oct 12 2020)
4. [T][✓] draw the diagram
There are 4 tasks in total in your list.
```

### 3.5 `done` - Mark a task as done
Marks a specific task as done, when the user input is in the right format,
namely `done INDEX`, INDEX being the index number of that task in the current 
list.

Example of usage: `done 1`

Expected outcome:
```
Great! The task below is marked as done:
   [T][✓] play the violin
```

## 3.6 `delete` - Delete a task
Deletes a specific task, when the user input is in the right format,
namely `delete INDEX`, INDEX being the index number of that task in the current 
list.

Example of usage: `delete 1`

Expected outcome:
```
Okay. The task below is deleted from your list:
   [T][✓] play the violin
Now there are 3 tasks in total in your list.
```

## 3.7 `find` - Find tasks matching a keyword
Finds the tasks with names that matches the keyword, when the user input is in the right format,
namely `find KEYWORD`, and returns a list consisting of these tasks.
 
Example of usage: `find do`

Expected outcome:
```
Here are the task or tasks that matches the keyword:
1. [D][✘] do homework (by: Sep 26 2020)
```

## 3.8 `bye` - Exit the program
The program will exit when the command `bye` is entered.

Example of usage: `bye`

Expected outcome:
```
Bye! I look forward to meeting you next time!
```
The program exits.

## 3.9 `help` - Display the help message
Displays the help message.

Example of usage: `help`

Expected outcome:
```
Hi, here is a list of commands that I can recognize:
1. 'todo TASK_NAME': this adds a ToDo task.
2. 'deadline TASK_NAME /by YYYY-MM-DD': this adds a Deadline task.
3. 'event TASK_NAME /at YYYY-MM-DD': this adds an Event task.
4. 'list': this lists all the tasks you have now.
5. 'done INDEX': this marks the task at the specified index as done. The index refers to 
the index number on the list.
6. 'delete INDEX': this deletes the task at the specified index. The index refers to the index number on 
the list.
7. 'find KEYWORD': this finds the tasks in the list that matches the keyword.
8. 'bye': exits the program.
9. 'help': this shows you the help message that lists the valid formats of the commands.
```


  
## 4. Command summary

Index | Action | Format, examples |
| ------------ | ------------ | ------------- |
1 | Add a Todo task | `todo TASK_NAME`, e.g.,`todo do readings` |
2 | Add a Deadline task | `deadline TASK_NAME /by YYYY-MM-DD`, e.g.,  `deadline finish report /by 2020-09-21` |
3 | Add an Event task | `event TASK_NAME /at YYYY-MM-DD`, e.g., `event workshop /at 2020-09-23` |                 
4 | List all tasks | `list` |
5 | Mark a task as done | `done INDEX`, e.g., `done 3` |
6 | Delete a task | `delete INDEX`, e.g., `delete 6` |
7 | Find tasks matching the keyword | `find KEYWORD`, e.g., `find project` |
8 | Exit | `bye`

## 5. Acknowledgements
1. [Gradle Tutorial](https://se-education.org/guides/tutorials/gradle.html)
2. [CheckStyle Tutorial](https://se-education.org/guides/tutorials/checkstyle.html)
3. [JavaFX Tutorial](https://se-education.org/guides/tutorials/javaFx.html)
4. [Text UI Testing Tutorial](https://se-education.org/guides/tutorials/textUiTesting.html)
5. [Working with Jar files Tutorial](https://se-education.org/guides/tutorials/jar.html)

                    
                      
                  
                  
                
               