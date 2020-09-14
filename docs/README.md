# User Guide

Duke is a task manager with a Graphical User Interface(GUI). Given below are instructions on how to use it.
* Quick Start
* Features
    * Adding a ToDo task `todo`
    * Adding a Deadline task `deadline`
    * Adding an Event task `event`
    * Listing all tasks `list`
    * Marking a task as done `done`
    * Deleting a task `delete`
    * Finding tasks that match a keyword `find`
    * Saving the data
    * Exiting the program `bye`
* Command summary
* Acknowledgements
## Quick Start

P## Quick start
 1. Ensure that you have Java `11` or above installed in your computer.
 
 2. Download the latest `duke.jar` from [here](https://github.com/WM71811/ip/releases/tag/v0.3).
 
 3. Place `duke.jar` into an empty folder.
 
 4. Open duke.jar by running the command `java -jar duke.jar`. The Graphic User Interface(GUI) should appear in a few seconds. Note that a data folder would be created upon 
 opening duke.jar file.
 
 5. Type the command in the text box and press Send to execute it.
    Some example commands you can try:
    * `list`: Lists all Tasks.
    * `todo do assignment`: Adds a ToDo task named do assignment.
    * `event meeting /at 2020-09-20`: Adds an Event task named project with the time due on 10 Oct 2020.
    * `deadline project /by 2020-10-10`: Adds a Deadline task named project with the time due on 10 Oct 2020.
    * `done 2`: Marks the second task shown in the current list as done.
    * `delete 3`: Deletes the third task shown in the current list.
    * `find meeting`: Finds tasks that has a name that contains the word meeting and lists these tasks.
    * `bye`: exits the program.
6. Refer to Features below for details of each command.

## Features 
### Adding a ToDo Task `todo`
Adds a ToDo task with its task name appended.

Format: `todo TASK_NAME`

Examples:
* `todo do readings`
* `todo finish homework`

### Adding a Deadline Task `deadline`
Adds a Deadline task with its task name and time appended.

Format: `deadline TASK_NAME /by YYYY-MM-DD`

Examples:
* `deadline finish report /by 2020-09-21`
* `deadline write paper /by 2020-10-12`

### Adding an Event Task `event`
Adds an Event task with its task name and time appended.

Format: `event TASK_NAME /at YYYY-MM-DD`

Examples:
* `event workshop /at 2020-09-23`
* `event project meeting /by 2020-10-15`

### Listing all tasks `list`
Shows a list of all tasks.

Format: `list`

### Marking a task as done `done`
Marks the specified Task in the list as done.

Format: `done INDEX`
* Marks the task at the specified INDEX as done. 
*The index refers to the index number shown in the current list of tasks. 
*The index **must be a positive integer** 1, 2, 3, …

Examples:
* `done 1` marks the first task in the list as done.
* `done 3` marks the third task in the list as done.

### Deleting a task `delete`
Deletes the specified Task from the list.

Format: `delete INDEX`

* Deletes the task at the specified INDEX. 
* The index refers to the index number shown in the current list of tasks. 
* The index **must be a positive integer** 1, 2, 3, …

Examples:
* `delete 2` deletes the second task from the list.
* `delete 4` deletes the fourth task from the list.

### Finding tasks that match a keyword `find`
Finds the tasks with their names matching the keyword 
and returns these tasks as a list.

Format: `find KEYWORD`

Examples:
* `find homework` returns a list of tasks that has name 
matching the word homework.
* `find project` returns a list of tasks that has name 
matching the word project.

### Saving the data
Duke's data are saved in the data folder automatically 
after any user command that changes the data.

### Exit the program `bye`
Exits the program.

Format: `bye`

## Command summary
 Action | Format, examples
 ------------ | -------------
Add a Todo task | `todo TASK_NAME`, e.g.,`todo do readings`
Add a Deadline task | `deadline TASK_NAME /by YYYY-MM-DD`, e.g.,  `deadline finish report /by 2020-09-21`
Add an Event task | `event TASK_NAME /at YYYY-MM-DD`, e.g., `event workshop /at 2020-09-23`                 
List all tasks | `list`
Mark a task as done | `done INDEX`, e.g., `done 3`
Delete a task | `delete INDEX`, e.g., `delete 6`
Find tasks matching the keyword | `find KEYWORD`, e.g., `find project`
Exit | `bye`

## Acknowledgements
1. [Gradle Tutorial](https://se-education.org/guides/tutorials/gradle.html)
2. [CheckStyle Tutorial](https://se-education.org/guides/tutorials/checkstyle.html)
3. [JavaFX Tutorial](https://se-education.org/guides/tutorials/javaFx.html)
4. [Text UI Testing Tutorial](https://se-education.org/guides/tutorials/textUiTesting.html)
5. [Working with Jar files Tutorial](https://se-education.org/guides/tutorials/jar.html)

                    
                      
                  
                  
                
               