# User Guide
# Table of Contents

1. [Features](#Features)
2. [Usage](#Usage)

## Features <a name="Features"></a>

### Feature 1 Add todo tasks
Add a todo task into the tasklist.


### Feature 2 Add event tasks
Add a event task with due date into the tasklist.


### Feature 3 Add deadline tasks
Add a deadline task with deadline into the tasklist.

### Feature 4 Delete tasks
Delete a task from the tasklist.

### Feature 5 Mark a task as 'Done'
Change the status icon of a task from a cross to a check.


### Feature 6 Search tasks using keywords
Find tasks matching the keywords.


### Feature 9 Exit the program
Exit the process and save the changes into the tasklist.



## Usage <a name="Usage"></a>

### `todo` - Add todo tasks

Add a todo task into the tasklist.

Example of usage: 

`todo sleep`

Expected outcome:

`added:sleep`
`Now you have 1 task(s) in the list`

### `event` - Add event tasks

Add a event task with due date into the tasklist.

Example of usage: 

`event sleep /by 2020-09-15`

Expected outcome:

`added:sleep`
`Now you have 1 task(s) in the list`

### `deadline` - Add deadline tasks

Add a deadline task with deadline into the tasklist.

Example of usage: 

`deadline sleep /by 2020-09-15`

Expected outcome:

`added:sleep`
`Now you have 1 task(s) in the list`


### `delete` - Delete tasks

Delete a task from the tasklist.

Example of usage: 

`delete 1`

Expected outcome:

`I have removed this task: `
`[T][✘] sleep`
  
### `done` - Mark as done

Change the status icon of a task from a cross to a check.

Example of usage: 

`done 1`

Expected outcome:

`Great! I have marked this task as done:
[T][✘] sleep`
  
### `find` - find tasks
 
Find tasks matching the keywords.
 
 Example of usage: 
 
 `find sleep`
 
 Expected outcome:
 
`Here are the tasks I find:`
`1. [T][✘] sleep`

### `bye` - exit process
 
Exit the process and save the changes into the tasklist.
 
 Example of usage: 
 
 `bye`
 
 Expected outcome:
 
`Bye-bye, see you next time!`


