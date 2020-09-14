# Duke User Guide
**Duke** is a Personal Assistant ChatBot that helps you to **keep track of various tasks** such as todo, deadline 
and event in daily life. This application allows users to manage their tasks via **Command Line Interface** (CLI) 
while still having the benefits of a Graphical User Interface (GUI).

## Quick Start
1. Ensure you have Java 11 or above installed in your computer.
2. Download the latest `duke.jar` from !here[url]
3. Copy the file to the folder you want to use as _home folder_ for Duke
4. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds.
<br>
![Image of Ui](images/Ui.png)
5. Type the command in the command box and press Enter to execute it. 
   e.g. typing `help` and pressing Enter will open the help window.
6. Refer to the *Features* below for details of each command.

## Features

### Viewing help: `help` 
Shows a list of commands that can be used.
<br>
Format: `help`
<br>
![Image of help](images/help.png)

### Listing all tasks: `list`
Shows a list of tasks that has been added and saved.
<br>
Format: `list`
<br>
![Image of list](images/list.png)

### Adding a Todo task: `todo`
Adds a Todo task to the list of tasks.
<br>
Format: `todo TASK`
<br>
Example: todo play piano
<br>
![Image of todo](images/todo.png)

### Adding a Deadline task: `deadline`
Adds a Deadline task to the list of tasks.
<br>
Format: `deadline TASK /by YYYY-MM-DD`
<br>
Example: deadline homework /by 2020-10-12
<br>
![Image of deadline](images/deadline.png)

### Adding an Event task: `event`
Adds an Event task to the list of tasks.
<br>
Format: `event TASK /at YYYY-MM-DD`
<br>
Example: event birthday party /at 2020-12-20
<br>
![Image of event](images/event.png)

### Deleting a task: `delete`
Deletes a task at a specific index from the list of tasks.
<br>
Format: `delete LIST_INDEX`
<br>
Example: delete 2
<br>
![Image of delete](images/delete.png)

### Marking a task as done: `done`
Marks a task at a specific index in the list of tasks as done.
<br>
Format: `done LIST_INDEX`
<br>
Example: done 5
<br>
![Image of done](images/done.png)

### Finding a task: `find`
Shows a list of tasks which contains the specific keyword in description.
<br>
Format: `find KEYWORD`
<br>
Example: find book
<br>
![Image of find](images/find.png)

### Closing the application: `bye`
Closes the application. Every previously added task is saved.
<br>
Format: `bye`
<br>
![Image of bye](images/bye.png)

### When Duke detects duplicates:
![Image of duplicate](images/duplicate.png)

### Saying yes: `yes`
Adds a duplicated task to the list of tasks.
<br>
Format: `yes`
<br>
![Image of yes](images/yes.png)

### Saying no: `no`
Does not add a duplicated task to the list of tasks.
<br>
Format: `no`
<br>
![Image of no](images/no.png)

### Command summary
Action | Format, Examples
------------ | -------------
Help | `help`
List | `list`
Todo | `todo TASK` <br> e.g., `todo play piano`
Deadline | `deadline TASK /by YYYY-MM-DD` <br> e.g., `deadline homework /by 2020-10-12`
Event | `event TASK /at YYYY-MM-DD` <br> e.g., `event birthday party /at 2020-12-20`
Delete | `delete LIST_INDEX` <br> e.g., `delete 2`
Done | `done LIST_INDEX` <br> e.g., `done 5`
Find | `find KEYWORD` <br> eg, `find book`
Bye | `bye`
Yes | `yes`
No | `no`
