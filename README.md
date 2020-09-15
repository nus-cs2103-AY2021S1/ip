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

## Features

1. Create a to-do task:
   Description: Creates a to-do task with the given description and adds it to the task list.
   Command: todo DESCRIPTION
   Example: todo Assignment
   Note: A to-do task is marked with a 'T'.
   
2. Create a deadline:
   Description: Creates a deadline task with the given description and due date, and adds it to the task list.
   Command: deadline DESCRIPTION /by DATE
   Example: deadline Homework /by 2020-09-16 1000
   Note: DATE has to be in YYYY-MM-DD HHmm format. A deadline is marked with a 'D'.

3. Create an event:
   Description: Creates an event task with the given description and due date, and adds it to the task list.
   Command: event DESCRIPTION /at DATE
   Example: event Party /at 2021-09-16 2000
   Note: DATE has to be in YYYY-MM-DD HHmm format. An event is marked with an 'E'.
   
4. List all tasks:
   Description: Displays a list of all created tasks with their assigned task number, task type, and done status.
   Command: list
   
5. Mark a task as done:
   Description: Marks the specified task as done.
   Command: done TASK_NUMBER
   Example: done 1
   Note: TASK_NUMBER can be obtained from list command. A task is marked with an 'X' if not done, and an 'O' if done.
   
6. Delete a task:
   Description: Deletes the specified task.
   Command: delete TASK_NUMBER
   Example: delete 1
   Note: TASK_NUMBER can be obtained from list command.
   
7. Sort the task list:
   Description: Sorts the task list in chronological order.
   Command: sort
   
8. Find tasks by keyword:
   Description: Finds and displays all tasks with the given keyword.
   Command: find KEYWORD
   Example: find party
   
9. Find tasks that are soon to be due:
   Description: Finds all tasks that are due in the given time frame in either hours or days.
   Command: due in NUMBER hours | due in NUMBER days
   Example 1: due in 2 hours
   Example 2: due in 1 days

10. Save the current task list:
   Description: Saves the current task list, which will be loaded the next time Bob is opened.
   Command: save
   Note: The task list is saved in /data/tasks.txt of the home directory of Bob.
   
11. List all available commands:
   Description: Displays a list of all the available commands Bob understands.
   Command: help
