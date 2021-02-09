# Moomin User Guide
Moomin is your personalized task manager. He can help you organize your tasks and make your life happier.
![Image of Moomin](./Ui.png)

## Quick Start
To use Moomin as your personal assistant, simply compile and run the Duke.java file at src/main/java/duke/Duke.java.

You can also build the application and run it using gradle.

A packaged jar file has been attached in the latest stable release. Feel free to download it and interact with Moomin by type the command java -jar ip.jar

## Features 

### 1.`list` - List out all current tasks.
Shows a list of all tasks in the task list.

Format: `list`

Example: `list`

Expected Outcome:
```
Here are the tasks in your list:
 1.[T][✘] borrow book
 2.[D][✘] return book (by: Sunday)
 3.[E][✘] project meeting (at: Mon 2-4pm)
```

### 2.`todo` - Add a todo task
Add a todo task with specified content to the task list. 

Format: `todo TASK_DESCRIPTION`

Example: `todo borrow book`

Expected Outcome:
```
Got it. I've added this task:
[T][✘] borrow book
Now you have 1 tasks in the list.
```

### 3.`deadline` - Add a deadline task
Add a deadline task with specified content and date by which the task needs to be completed to the task list. 

Format: `deadline TASK_DESCRIPTION /by YYYY-MM-DD`

Example: `deadline return book /by 2020-09-25`

Expected Outcome:
```
Got it. I've added this task:
[D][✘] return book (by: Sep 25 2020)
Now you have 2 tasks in the list.
```

### 4.`event` - Add an event task
Add an event task with specified content and date of the event to the task list. 

Format: `event TASK_DESCRIPTION /at YYYY-MM-DD`

Example: `event project meeting /at 2020-12-20`

Expected Outcome:
```
Got it. I've added this task:
[E][✘] project meeting (at: Dec 20 2020)
Now you have 3 tasks in the list.
```

### 5.`done` - Mark the task as done
Mark a specific task as done.

Format: `done TASK_NO`

Example: `done 1`

Expected Outcome:
```
Nice! I've marked this task as done:
  [✓] borrow book
```

### 6.`delete` - Delete a task
Delete a specific task.

Format: `delete TASK_NO`

Example: `delete 1`

Expected Outcome:
```
Noted. I've removed this task:
  [✓] return book
Now you have 2 tasks in the list.
```

### 7.`find` - Find a task
Find a task that contains a specific keyword.

Format: `find SEARCH_KEYWORD`

Example: `find friend`

Expected Outcome:
```
Here are the matching tasks in your list:
1.[D][✘] meet friend (by: Sep 24 2020)
```

### 8.`edit` - Edit a task
Edit the date, description of a task or undo a task.
edit 1 date 2020-12-15
edit 1 task borrow books
edit 1 undo

Format: `edit TASK_NO data YYYY-MM-DD`

Example: `edit 1 date 2020-12-15`

Expected Outcome:
```
Date is updated successfully:
  [D][✘] meet friend (by: Dec 25 2020)
```

Format: `edit TASK_NO task NEW_DESCRIPTION`

Example: `edit 1 task parents meeting`

Expected Outcome:
```
Description is updated successfully:
  [D][✘] parents meeting (by: Dec 25 2020)
```

Format: `edit TASK_NO undo`

Example: `edit 1 undo`

Expected Outcome:
```
The following task is marked as undone:
  [D][✘] parents meeting (by: Dec 25 2020)
```

### 9.`bye` - Exit the application

Exit the application. Data will be saved to storage.

Format: `bye`

Example: `bye`

### Author
Long Zeling
