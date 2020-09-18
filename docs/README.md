# User Guide

## Features 

### 1. Add a task 
Adds a task to task list. Types of tasks are **Todo, Event** and **Task.**

### 2. Delete a task
Deletes a task from task list.

### 3. Mark done
Marks a task as done.

### 4. Find a task
Finds all tasks containing given keyword.

### 5. Prioritize a task
Moves a task to the top of task list and label it as **HIGH priority**.

### 6. View all tasks
Displays all tasks in the task list.

###7. Exit
Exits the bot.



## Usage

### `todo <task>` - Add a todo task to task list.

Example of usage: 

`todo wash clothes`

Expected outcome:

```
 Got it. I've added this task:
 [T][✘] wash clothes
 Now you have 1 task in the list.
```

### `event <task> /at <YYYY-MM-DD>` - Add a event task to task list.

Example of usage: 

`event meeting /at 2020-09-20`  

Expected outcome:

```
 Got it. I've added this task:
 [E][✘] meeting (at: Sep 20 2020) 
 Now you have 2 tasks in the list.
```

### `deadline <task> /by <YYYY-MM-DD>` - Add a deadline task to task list.

Example of usage: 

`deadline quiz /by 2020-09-20`

Expected outcome:

```
 Got it. I've added this task:
 [D][✘] deadline quiz (by: Sep 20 2020)
 Now you have 3 task in the list.
```

### `delete <task number>` - Delete a task

Example of usage: 

`delete 1`  

Expected outcome:

```
 Noted. I've removed this task: 
 [T][✘] wash clothes 
 Now you have 2 tasks in the list.
```

### `done <task number>` - Mark a task as done.

Example of usage: 

`done 1`  

Expected outcome:

```
 Nice! I've marked this task as done:
 [E][✓] meeting (at: Sep 20 2020)
 ```

### `find <keyword>` - Finds all tasks containing given keyword.

Example of usage: 

`find quiz`  

Expected outcome:

```
 Here are the matching task(s) in your list: 
 1.[D][✘] deadline quiz (by: Sep 20 2020)
```

### `prioritize <task number>` - Move a task to the top of task list and label it as **HIGH priority**.

Example of usage: 

`prioritize 1`  

Expected outcome:

```
 Noted. I've prioritized this task: 
 [D][✘] deadline quiz (by: Sep 20 2020)
```

### `list` - Display all tasks in the task list.

Example of usage: 

`list`  

Expected outcome:

```
 Here are the tasks in your list:
 1.[D][✘] deadline quiz (by: Sep 20 2020) !!!HIGH priority!!!
 2.[E][✓] meeting (at: Sep 20 2020)
```

### `bye` - Exit the bot.

Example of usage: 

`bye`  

Expected outcome:

```
 Bye. Hope to see you again soon!
```