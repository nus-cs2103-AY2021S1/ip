# User Guide
This is user guide of task manager desktop application, Duke.
**NOTE**: 
In duke, there are 3 types of task. Those are todo, event, and deadline.

## Features 

### 1. Add todo
-> Create todo and add it to Duke.

Format:

`todo [description]` 

Example of usage: 

`todo homework`

Expected outcome:

`Duke heard:
Got it. I've added this task: 
[T][✘] homework
Now you have 3 tasks in the list.`

### 2. Add event
-> Create event and add it to Duke. 

Format:

`event [description] /at [YYYY-MM-DD]` 

Example of usage: 

`event CS2103 /at 2020-10-15`

Expected outcome:

`Duke heard:
Got it. I've added this task: 
[E][✘] CS2103 (at: 10月 15 2020)
Now you have 2 tasks in the list.`

### 3. Add deadline
-> Create deadline and add it to Duke.

Format:

`deadline [description] /by [YYYY-MM-DD]` 

Example of usage: 

`deadline homework /by 2020-10-15`

Expected outcome:

`Duke heard:
Got it. I've added this task: 
[D][✘] homework (by: 10月 15 2020)
Now you have 5 tasks in the list.`

### 4. Mark task as done
-> Mark task indicated by index as done

Format:

`done [index]` 

Example of usage: 

`done 1`

Expected outcome:

`Duke heard:
Nice! I've marked this task as done: 
[T][✓] hello`

### 5. Delete task
-> Delete task indicated by index.

Format:

`delete [index]` 

Example of usage: 

`delete 1`

Expected outcome:

`Duke heard:
Noted. I've removed this task: 
[T][✓] hello
Now you have 4 tasks in the list.`

### 6. Find task
-> Find task by keyword.

Format:

`find [keyword]` 

Example of usage: 

`find homework`

Expected outcome:

```
Duke heard:
Here are the matching tasks in your list: 
1. [T][✘] homework 
2. [D][✘] math homework (by: 10月 22 2020) 
```

### 7. List tasks
-> Create todo and add it to Duke.

Format:

`todo [description]` 

Example of usage: 

`todo homework`

Expected outcome:

`Duke heard:
Got it. I've added this task: 
[T][✘] homework
Now you have 3 tasks in the list.`

### 8. Set priority to task
-> Create todo and add it to Duke.

Format:

`todo [description]` 

Example of usage: 

`todo homework`

Expected outcome:

`Duke heard:
Got it. I've added this task: 
[T][✘] homework
Now you have 3 tasks in the list.`

### 9. Exit Duke app
-> Create todo and add it to Duke.

Format:

`todo [description]` 

Example of usage: 

`todo homework`

Expected outcome:

`Duke heard:
Got it. I've added this task: 
[T][✘] homework
Now you have 3 tasks in the list.`








### Feature 2 
List all the tasks in Duke.

## Usage

### `list` 

List all the tasks in the Duke.

Example of usage: 

`list`

Expected outcome:

`Here are the tasks in your list: 
 1. [T][✘] [!!] dont do homework 
 2. [T][✘] homework 
 3. [T][✘] description `

### Feature 3 
Delete the task from Duke.

## Usage

### `delete [index]` 

Delete task according to its index.

Example of usage: 

`delete 2`

Expected outcome:

`Noted. I've removed this task: 
 [T][✘] [!!] dont do homework
 Now you have 1 tasks in the list.`

### Feature 4 
Close the Duke application.

## Usage

### `bye` 

Close the Duke application.

Example of usage: 

`bye`

Expected outcome:

`Bye. Hope to see you again soon!`