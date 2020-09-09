# User Guide
Duke is a desktop application for tasks management that is best suited for fast-typists, who prefer to do tasks management entirely using Command Line Interface (CLI). 
This application also allows user to use Graphical User Interface (GUI).

## Features 
#### There are 3 different types of task that you can put into the list, namely are: ```todo```, ```deadline``` and ```event```.
0. Load existing tasks when application launches, if any.
1. Add todo: ```todo```
2. Add deadline: ```deadline```
3. Add event: ```event```
4. Mark task as done: ```done```
5. List all tasks: ```list```
6. Delete task: ```delete```
7. Search keyword in list: ```find```
8. Show statistics: ```stats```
9. Exit the application: ```bye```

### Feature 0: Load existing tasks
Duke application will automatically saves your tasks after each command and loads up when everytime you enter the application.
An example would be as shown in the image below, where the user has existing tasks that is stored in the list previously.

### Feature 1: Add todo
Adds a todo task to be stored in the list.
Type ```todo <task description>``` in the textbox as provided, and click Send or press Enter button.
Example usage: ```todo go to the gym```
<1.0>

You application should register the new todo list and should look like this:
<1.1>

### Feature 2: Add deadline
Adds a deadline task to be stored in the list.
Example usage:
```
deadline <description> /by <date in yyyy-mm-dd format>
Eg: deadline submit google project PR /by 2020-11-11
```

Expected outcome:
<2>

### Feature 3: 
