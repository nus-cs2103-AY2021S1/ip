# Duke User Guide

## <ins>Overview</ins>
Duke is an interactive chat-bot that helps to organize your tasks through a to-do list.

<img src="https://raw.githubusercontent.com/eugene3231/ip/master/docs/Ui.png" width="400">

## <ins>Quick Start Guide</ins>
Prerequisites:

* Java JDK 11
* Ensure that your `JAVA_HOME` is set to the correct JDK 11 location

Running Duke:
  1. [Download](https://github.com/eugene3231/ip/releases) *`Duke.jar`*
  2. Run *`Duke.jar`*
  3. The GUI should appear and you can start talking to Duke!


## <ins>Features</ins>

### Add tasks
* Creates a task of type listed below and add it to the task list.
  - Deadline
  - Event
  - Todo

### Delete tasks
* Removes a task.

### Show all tasks
* Displays all tasks.

### Clear all tasks
* Removes all tasks.

### Find a task
* Finds a task based on a given keyword.

### Mark task as completed
* Completes a task.

### Undo 
* Undos the previous user command.


## <ins>Usage</ins>

### 1. `todo` - Add new todo
Add a new todo task with description. </br>

Example of usage: 

`todo Walk the dog`

Expected outcome:

> Yes! I have successfully added:
</br>[T][✘] Walk the dog

### 2. `deadline` - Add new deadline
Add a new deadline task with description and time. </br>

Example of usage: 

`deadline Assignment /by 2020-10-10 1800`

Expected outcome:

> Yes! I have successfully added:
</br>[D][✘] Assignment (by: 10 Oct 2020, 06:00 PM)

### 3. `event` - Add new event
Add a new event task with description and time. </br>

Example of usage: 

`event Meeting /at 10pm`

Expected outcome:

> Yes! I have successfully added: 
</br>[E][✘] Meeting (at: 10pm)

### 4. `delete` - Delete a task
Delete a task by specifing its number in the task list.

Example of usage: 

`delete 3`

Expected outcome:

> Alright! I've removed this task: 
</br>[E][✔] Google Career Talk (at: U-Town)

### 5. `list` - List all tasks
Display all tasks currently in the list.

Example of usage: 

`list`

Expected outcome:

> Here are the tasks in your list:</br>
</br>1. [D][✔] Send Email (by: 13 Sept 2020, 06:00 PM)
</br>2. [E][✘] Software Engineering class (at: 3pm)
</br>3. [D][✘] Submission of Essay (by: 6pm on Thursday)
</br>4. [D][✘] Assignment (by: 10 Oct 2020, 06:00 PM)
</br></br>You have 4 task(s) in the list

### 6. `clear` - Clear all tasks
Delete all tasks in the list.

Example of usage: 

`clear`

Expected outcome:

> The list of tasks has successfully been cleared.

### 7. `find` - Find a task by keyword
Find all tasks matching the keyword either fully or partially.

Example of usage: 

`find Essay`

Expected outcome:

> I have found the matching tasks in your list:
</br>[D][✘] Submission of Essay (by: 6pm on Thursday)

### 8. `done` - Completes a task
Mark a task as done by specifing its number in the task list.

Example of usage: 

`done 4`

Expected outcome:

> Good job! You completed:
</br>[D][✔] Assignment (by: 10 Oct 2020, 06:00 PM)

### 9. `undo` - Undo the previous command
Example of usage: 

`undo`

Expected outcome:

> The previous command: "done 3" has been undone!

### 10. `help` - Displays help guide for commands
Examples of usage: 
* Display help for all commands

  `help`

* Display help for a specific command

  `help deadline`

Expected outcome:

> deadline: Adds a deadline task with time.
</br> Fields: [description] /by [time] 
</br> Example: deadline Assignment /by 2020-10-10 1800
