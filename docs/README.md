# IrohBot User Guide

IrohBot is an application built using Java used to manage tasks, via a **Command Line Interface (CLI)**.

## How to Begin

1. Ensure Java 11 has been downloaded and installed on your computer.
2. Download the latest version of IrohBot.jar [here]()
3. Copy the file to the folder you want to use as the home folder for your IrohBot.
4. Double-click the file to start the app.
5. Commands that can be executed in the command box. Here are some example commands you can try:
   - `list` : Lists all tasks.
   - `todo Example` : Adds a task of type Todo with description "Example".
   - `delete 2`: Deletes the second Task in your task list.
   - `bye` : Exits the app.



## Command format:

* Words in upper case are parameters to be supplied by the user. For instance,  in `todo TODO_NAME`, TODO_NAME is the parameter in this command.
* For the field known as DATE_AND_TIME, the format of writing said date and time is: `DD-mm-YY HHMM` where D represents the day, m represents the month, Y represents the year, H represents the hour and M represents the minute.



## Features and Usage:

### Adding a todo: `todo` 

Adds a Todo task to your task list.

Format: `todo TODO_NAME`

Example:

* `todo Brush teeth` : Adds a task of type Todo with the description "Brush teeth".



### Adding a deadline: `deadline`

Adds a Deadline task to your task list.

Format: `deadline DEADLINE_NAME /by DATE_AND_TIME`

Example:

* `deadline Do homework /by 20-12-2018 1200` : Adds a Task of type Deadline with description "Do homework" due 3rd October 2018 at 1200hrs



### Adding an event: `event`

Adds an Event task to your task list.

Format:  `event EVENT_NAME /at DATE_AND_TIME`

Example:

* `event House party /on 20-12-2020 1200` : Adds a Task of type Event with description "House party" on the 20 April 2020 at 2000hrs



### Listing all tasks: `list`

Shows a list of all your tasks.

Format: `list`



### Marking a task as done: `done`

Marks a specified task from the task list as done.

Format: `done INDEX`

* The index refers to the position of the task in the task list, for instance the first task would have an index of 1, the second with an index of 2, and so on.
* The index must be a positive integer that falls within the length of the list, for instance `done` followed by an index of only 1 to 6 will work for a task list with 6 tasks.

Example:

* `done 1` : Marks the first task in the task list as done.



### Deleting a task: `delete`

Deletes a specified task from the task list.

Format: `delete INDEX`

* The index refers to the position of the task in the task list, for instance the first task would have an index of 1, the second with an index of 2, and so on.
* The index must be a positive integer that falls within the length of the list, for instance `delete` followed by an index of only 1 to 6 will work for a task list with 6 tasks.

Example:

* `delete 1` : Deletes the 1st task in the task list.



### Find a task with a specific keyword: `find`

Find tasks with descriptions containing KEYWORD in the Task list.

Format: `find KEYWORD`

* The search is case-sensitive, for instance, `defeat` will match `defeat Fire Lord` but not `Defeat Fire Lord`

Example:

* `find Avatar`: Returns a list of results of with that contain word "Avatar"



### Closing Application: `bye`

Exits IrohBot.

Format: `bye`



## Author

Ethan Noah Rozario