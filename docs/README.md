# Bob

Bob is a personal task handling chatbot! Tell him all your deadlines, events and tasks to do and he will keep track of them for you.

## Quick Start

Prerequisites: JDK 11

1. Download the latest Bob.jar.
2. Copy the file to the home directory for Bob.
3. Open the Bob.jar file and a window should open.
4. Try typing in the textbox and hit 'Send'.

![Bob UI](/docs/Ui.png)

5. If you see something like this, everything is working!
6. Type a command in the command box and press 'Send' to execute it.
      Some example commands you can try:
      help: Displays all the available commands.
      list : Lists all tasks Bob knows about.
      todo DESCRIPTION: Creates a To-Do task.
      deadline DESCRIPTION /by DATE: Creates a Deadline task.
      event DESCRIPTION /at DATE : Creates an Event task.
      bye: Saves the task list.
7. Refer to the Features below for details of each command.

# User Guide

## Features 

### Creating tasks
Users can create tasks such as deadlines, events and to-do tasks. Once the task is created, Bob will add it to the task list. Each task can also be marked as done.

For deadlines and events, dates must be provided.

### Viewing all task
Users can view all the tasks they have created. Bob will display a list of all the tasks, where each task is tagged with their task number, task type (D for deadline, E for event, and T for to-do), and done status (O for done, X for not done).

### Marking tasks as done
Users can mark tasks as done to keep track of which tasks have not been completed. Tasks are marked with an 'X' if they are not done and 'O' if they are done.

### Deleting tasks
Users can delete tasks from the task list.

### Sorting the task list
Users can sort the task list according to time.

### Finding tasks
Users can either find tasks by keyword or by time.

1. By keyword: Users can specify a keyword and Bob will find and display all tasks that contain that keyword.

2. By time: Users can specify a time period (in hours or in days) and Bob will find and display all tasks that are due or within that time period.

### Saving the task list
Users can save their current task list. Doing so will allow Bob to remember what tasks there are when he is opened again next time.

## Usage

### todo - Create a to-do task

Creates a to-do task with the given description and adds it to the task list.

Format:

`todo DESCRIPTION`

Example of usage: 

`todo Assignment`

Expected outcome:

`The task list contains a to-do task with description Assignment`

### deadline - Create a deadline

Creates a deadline task with the given description and due date, and adds it to the task list.

Format:

`deadline DESCRIPTION /by DATE`

Example of usage: 

`deadline Homework /by 2020-09-16 1000`

Expected outcome:

`The task list contains a deadline task with description Homework and due date 2020-09-16 10:00`

Note: DATE has to be in YYYY-MM-DD HHmm format.

### event - Create an event

Creates an event task with the given description and due date, and adds it to the task list.

Format:

`event DESCRIPTION /at DATE`

Example of usage: 

`event Party /at 2021-09-16 2000`

Expected outcome:

`The task list contains an event task with description Party and date 2021-09-16 20:00`

Note: DATE has to be in YYYY-MM-DD HHmm format.

### list - Lists all tasks

Displays a list of all created tasks with their assigned task number, task type, and done status.

Example of usage: 

`list`

Expected outcome:

`The task list is displayed.`
   
### done - Mark a task as done

Marks the specified task as done.

Format:

`done TASK_NUMBER`

Example of usage: 

`done 1`

Expected outcome:

`Task 1 is marked as done.`

Note: TASK_NUMBER can be obtained from list command. A task is marked with an 'X' if not done, and an 'O' if done.

### delete - Delete a task

Deletes the specified task.

Format:

`delete TASK_NUMBER`

Example of usage: 

`delete 1`

Expected outcome:

`Task 1 is deleted.`

Note: TASK_NUMBER can be obtained from list command.

### sort - Sort the task list

Sorts the task list in chronological order.

Example of usage: 

`sort`

Expected outcome:

`The task list is sorted by date.`

### find - Find tasks by keyword

Finds and displays all tasks with the given keyword.

Format:

`find KEYWORD`

Example of usage: 

`find party`

Expected outcome:

`All tasks with the keyword are displayed.`

### due in - Find tasks by keyword

Finds all tasks that are due in the given time frame in either hours or days.

Format:

`due in NUMBER hours | due in NUMBER days`

Example of usage: 

`due in 24 hours`

`due in 1 days`

Expected outcome:

`All tasks due in 24 hours or 1 day are displayed.`

### save - Save the current task list

Saves the current task list, which will be loaded the next time Bob is opened.

Example of usage: 

`save`

Expected outcome:

`The task list is saved in /data/tasks.txt of the home directory of Bob.`

### help - Show all available commands

Displays a list of all the available commands Bob understands.

Example of usage: 

`list`

Expected outcome:

`The list of all available commands are displayed.`
