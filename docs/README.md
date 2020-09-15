# User Guide
# Table of Contents

1. [Features](#Features)
2. [Usage](#Usage)

## Features <a name="Features"></a>

### Feature 1 Add To Do tasks
Add a todo task into the task manager.


### Feature 2 Add Event tasks
Add a event task with a starting date and time into the task manager.


### Feature 3 Add Deadline tasks
Add a deadline task with a deadline date and time into the task manager.


### Feature 4 Mark a task as Done
Finish a task and change the status icon to a check.

### Feature 5 Delete a task
Delete a certain task from the list.


### Feature 6 Check tasks by time
Find the tasks that matches the date and time.


### Feature 7 Find tasks by keyword
Find the tasks that match the input keyword.


### Feature 8 Update task information
Update the details of a specific task according to index.


### Feature 9 Exit the program
Exit the application and save the changes into the task list.



## Usage <a name="Usage"></a>

### `todo` - Add to do tasks

Add a todo task into the task manager. 

Example of usage: 

`todo read books`

Expected outcome:

`[T][✘] read books`

### `event` - Add event tasks

Add a event task with a starting date and time into the task manager.

Example of usage: 

`event work hard /at 2020-12-12 1221`

Expected outcome:

`[E][✘] work hard (at: 12 12 2020 12 : 21)`

### `deadline` - Add deadline tasks

Add a deadline task with a starting date and time into the task manager.

Example of usage: 

`deadline work hard /by 2020-12-12 1221`

Expected outcome:

`[D][✘] work hard (by: 12 12 2020 12 : 21)`

### `done` - Mark tasks as done

Mark tasks as done.

Example of usage: 

`done 1`

Expected outcome:

` Nice! I've marked this task as done:
         [T][✓] read books`

### `delete` - Delete tasks from the list

Delete a certain task from the list.

Example of usage: 

`delete 1`

Expected outcome:

` Nice! I've removed this task:
         [T][✓] read books
  Now you have 2 tasks in the list.`
  
### `check` - find tasks that match the date and time
 
Find the tasks that matches the date and time.
 
 Example of usage: 
 
 `check 2020-12-12 1221`
 
 Expected outcome:
 
`[E][✘] work hard (at: 12 12 2020 12 : 21)`

### `find` - find tasks that match the keyword
 
Find the tasks that matches the keyword.
 
 Example of usage: 
 
 `find work hard`
 
 Expected outcome:
 
`[E][✘] work hard (at: 12 12 2020 12 : 21)`

### `update` - Update the details of a task
 
Update the details of a specific task according to index.
 
 Example of usage: 
 
 `update 2 todo work hard`
 
 Expected outcome:
 
`[T][✘] work hard`

   

### `bye` - Exit the application
 
Exit the application and save the changes into the task list.
 
 Example of usage: 
 
 `bye`
 
 Expected outcome:
 
`Bye. Hope to see you again soon!`


