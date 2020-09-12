# User Guide

![User Interface](Ui.png)

## Features 

### Create, Read, Update and Delete Tasks
Keep track of all your tasks, be it to-dos, deadlines, or events, all in this application.

### Mark tasks as completed
Once you have completed a task, you can mark them as complete.
If you don't need the task anymore, you can also delete it.

### Search for relevant tasks
Quickly filter through your lists of tasks by searching for tasks containing only a specified keyword.

## Usage

### `list` - Display all tasks

Displays a list of all added tasks.

Example of usage: 

`list`

Expected outcome:

```
Here are the tasks in your list:
1. [T][✓] Submit CS2103T IP User Guide
2. [E][✘] Birthday Celebration (at: Jan 1 2020)
3. [D][✘] Programming Assignment (by: Oct 5 2020)
4. [D][✘] Online Quiz (by: Oct 10 2020)
```

### `todo <description>` - Add a new To-do item

Adds a new to-do item to the list of tasks.

Example of usage: 

`todo Submit assignment`

Expected outcome:
```
Got it. I've added this task:
  [T][✘] Submit assigment
Now you have 5 tasks in the list.
```

### `deadline <description> /by <date in YYYY-MM-dd format>` - Add a new Deadline item

Adds a new deadline item to the list of tasks.

Example of usage: 

`deadline Submit assignment /by 2020-10-30`

Expected outcome:
```
Got it. I've added this task:
  [D][✘] Submit assignment (by: Oct 30 2020)
Now you have 6 tasks in the list.
```

### `event <description> /at <date in YYYY-MM-dd format>` - Add a new Event item

Adds a new event item to the list of tasks.

Example of usage: 

`event Submit assignment /at 2020-10-30`

Expected outcome:
```
Got it. I've added this task:
  [E][✘] Submit assignment (at: Oct 30 2020)
Now you have 7 tasks in the list.
```

### `done <task ID>` - Mark a task as complete

Marks the task with given task ID as complete. 
The task ID of a task is the number the accompanies it when you use the `list` command.

Example of usage: 

`done 2`

Expected outcome:
```
Nice! I've marked this task as done:
[E][✓] Birthday Celebration (at: Jan 1 2020)
```

### `update <task ID> <command to create a todo, deadline, or event>` - Updates a task

Update the tasks with given task ID. 
The task ID of a task is the number the accompanies it when you use the `list` command.

Example of usage: 

`update 2 todo Submit assignment`

Expected outcome:
```
Noted. I've updated the task.

Here's your updated task list:
1. [T][✓] Submit CS2103T IP User Guide
2. [T][✓] Submit assignment
3. [D][✘] Programming Assignment (by: Oct 5 2020)
4. [D][✘] Online Quiz (by: Oct 10 2020)
5. [T][✘] Submit assigment
6. [D][✘] Submit assignment (by: Oct 30 2020)
7. [E][✘] Submit assignment (at: Oct 30 2020)
```

### `find <keyword>` - Search for tasks with keyword

Search the list of tasks for tasks containing the specified keyword.

Example of usage: 

`find assignment`

Expected outcome:
```
Here are the matching tasks in your list:
1. [T][✓] Submit assignment
2. [D][✘] Programming Assignment (by: Oct 5 2020)
3. [D][✘] Submit assignment (by: Oct 30 2020)
4. [E][✘] Submit assignment (at: Oct 30 2020)
```

### `help` - Display the help manual

Display the help manual which shows the list of available commands.

Example of usage: 

`help`

Expected outcome:
```
This is a list of functionalities that I support:

• To list out all existing tasks, type 'list'.
• To exit the program, type 'bye'.
• To add a new Todo item, type 'todo <description>'.
• To add a new Deadline item, type 'deadline <description> /by <date in yyyy-MM-dd format>'.
• To add a new Event item, type 'event <description> /at <date in yyyy-MM-dd format>'.
• To mark any item as complete, type 'done <taskId>'.
• To update an item, type 'update <taskId> <command to create a todo, deadline, or event>'.
• To find all items containing a certain keyword, type 'find <keyword>'.


```

### `bye` - Exits the program

Exits the program.

Example of usage: 

`bye`

