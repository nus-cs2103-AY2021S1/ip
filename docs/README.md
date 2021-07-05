# User Guide

## Features 

### Task manager
Duke is a ChatBot with a task manager. It allows users to add and delete tasks, mark tasks as done and search for tasks.

## Usage

### 1. `list` - List out tasks

List out all the tasks in user's taskList.

Example of usage: 

`list`

Expected outcome:

`Here are the tasks in your list:`  
`1.[T][✘] read book`

### 2. `todo <description>` - Add todo

Add a todo to the user's taskList

Example of usage: 

`todo read book`

Expected outcome:

`Got it. I've added this task:`  
`[T][✘] read book`  
`Now you have 1 task in your list`

### 3. `deadline <description> /by <yyyy-mm-dd>` - Add deadline

Add a task with a deadline to the user's taskList

Example of usage: 

`deadline return book /by 2020-12-29`

Expected outcome:

`Got it. I've added this task:`  
`[D][✘] return book (by: Dec 29 2020)`  
`Now you have 1 task in your list`

### 4. `event <description> /at <yyyy-mm-dd>` - Add event

Add an event to the user's taskList

Example of usage: 

`event go bookstore /at 2020-12-30`

Expected outcome:

`Got it. I've added this task:`  
`[E][✘] go bookstore (at: Dec 30 2020)`  
`Now you have 1 task in your list`

### 5. `done <task ID>` - Mark tasks as done

Mark the task which corresponds to the ID as done.

Example of usage: 

`done 1`

Expected outcome:

`Nice! I've marked this task as done:`  
`[T][✓] read book`

### 6. `delete <task ID>` - Delete tasks

Delete the task which corresponds to the ID from user's taskList.

Example of usage: 

`delete 1`

Expected outcome:

`Noted. I've removed this task:`  
`[T][✓] read book`  
`Now you have 1 task in your list`

### 7. `find <keyword>` - Find tasks

Search for tasks which matches the keyword in user's taskList.

Example of usage: 

`find book`

Expected outcome:

`Here are the matching tasks in your list:`  
`[T][✓] read book`

### 8. `bye` - Exit Duke

Exits the program and closes the window.

Example of usage: 

`bye`
