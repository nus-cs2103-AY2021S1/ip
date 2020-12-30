# User Guide

## Features <a name="Features"></a>

### Feature 1 Add To Do tasks
Add a todo task into the task manager.

#### Usage
### `todo` - Add to do tasks

Type `todo` and add a description of the todo task to add a todo task into the task manager. 

Example of usage: 

`todo read books`

Expected outcome:

`[T][✘] read books`

### Feature 2 Add Event tasks
Add a event task with a starting date and time into the task manager.

#### Usage
### `event` - Add event tasks

Type `event` and add a description of the event task to add a event task with a starting date and time into the task manager.

Example of usage: 

`event work hard /at 2020-12-12 1221`

Expected outcome:

`[E][✘] work hard (at: 12 12 2020 12 : 21)`

### Feature 3 Add Deadline tasks
Add a deadline task with a deadline date and time into the task manager.

#### Usage
### `deadline` - Add deadline tasks
Type `deadline` and add a description of the deadline task to add a deadline task with a starting date and time into the task manager.

Example of usage: 

`deadline work hard /by 2020-12-12 1221`

Expected outcome:

`[D][✘] work hard (by: 12 12 2020 12 : 21)`

### Feature 4 Mark a task as Done
Finish a task and change the status icon to a check.

#### Usage
### `done` - Mark tasks as done

Type `done` and the index of the task to mark it as done.

Example of usage: 

`done 1`

Expected outcome:

` Nice! I've marked this task as done:`  
         `[T][✓] read books`
         
### Feature 5 Delete a task
Delete a certain task from the list.

#### Usage

### `delete` - Delete tasks from the list

Type `delete` and the index of the task to delete a certain task from the list.

Example of usage: 

`delete 1`

Expected outcome:

` Nice! I've removed this task:`  
         `[T][✓] read books`  
  `Now you have 2 tasks in the list.`
  
  
### Feature 6 Check tasks by date
Find the tasks that matches the date.

#### Usage

### `check` - find tasks that match the date
 
Type `check` and the date to find the tasks that matches the date.
 
 Example of usage: 
 
 `check 2020-12-12`
 
 Expected outcome:
 
` Here are the matching tasks in your list:`  
`1. [E][✘] work hard (at: 12 12 2020 12 : 21)`


### Feature 7 Find tasks by keyword
Find the tasks that match the input keyword.

#### Usage

### `find` - find tasks that match the keyword
 
Find the tasks that matches the keyword.
 
 Example of usage: 
 
 `find work hard`
 
 Expected outcome:
 
` Here are all the tasks in your list:`  
`1. [E][✘] work hard (at: 12 12 2020 12 : 21)`

### Feature 8 Update task information
Update the date and time of a event/deadline task according to index.

#### Usage

### `update` - Update the details of a task
 
Type `update` and the new datetime to update the details of a specific task according to index.
 
 Example of usage: 
 
 `update 2 /to 1111-11-11 1111`
 
 Expected outcome:  
 
`Noted. I have updated the task.`  
`[E][✘] work hard (at: 11 11 1111 11 : 11)`  
`Now you have 2 tasks in the list.`

### Feature 9 List down all the tasks
List down all the tasks from storage.

#### Usage

### `list` - List down all the tasks
 
Type `list` to list down all the tasks from storage.
 
 Example of usage: 
 
 `list`
 
 Expected outcome:  
 
`Here are all the tasks in your list.`  
`1. [T][✓] read books`  
`2. [E][✘] work hard (at: 11 11 1111 11 : 11)`  


### Feature 10 Exit the program
Exit the application and save the changes into the task list.

#### Usage

### `bye` - Exit the application
 
Exit the application and save the changes into the task list.
 
 Example of usage: 
 
 `bye`
 
 Expected outcome:
 
`Bye. Hope to see you again soon! This will`  
`automatically close in 3.3 seconds...`








  





   




