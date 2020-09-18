# User Guide

### Feature 
#### Duke
A robot named Duke who helps you to manage your tasks.
You can simply type in your command, ask Duke to add a task, set a deadline or record a event timing. 

## Usage

### `list` - list all the tasks in your todo list
Duke will list all the tasks in your todo list.

Example of usage: 

`list`

Expected outcome:

`Here are the tasks in your list:`
`1.[T][✓] read book`
`2.[D][✓] return book (by: Oct 10 2020)`
`3.[E][✘] project meeting (at: Aug 6th 2-4pm)`


### `done` - mark your corresponding tasks as done
Duke will mark your corresponding tasks as done

Example of usage: 

`done 3`

Expected outcome:

`Nice! I've marked this task as done:`
`[E][✓] project meeting (at: Aug 6th 2-4pm)`

### `delete` - delete a task from your todo list
Duke will delete a task from your todo list

Example of usage: 

`delete 3`

Expected outcome:

`Noted! I've removed this task:`
`[E][✘] project meeting (at: Aug 6th 2-4pm)`
`Now you have 3 tasks in the list.`
 
### `todo` - simply add a task 
Duke will simply add a task 

Example of usage: 

`todo read book`

Expected outcome:

`Got it. I've added this task:`
   `[T][✘] read book`
`Now you have 1 tasks in the list.`

### `deadline` - add a task with a specific deadline
Duke will add a task with a specific deadline

Example of usage: 

`deadline return book /by 2020-10-10`

Expected outcome:

`Got it. I've added this task:`
`[D][✘] return book (by: Oct 10 2020)`
`Now you have 2 tasks in the list.`
 
 
### `event` - add a task that happens at the time you give
Duke will add a task that happens at the time you give

Example of usage: 

`event return book /at tonight`

Expected outcome:

`Got it. I've added this task:`
`[E][✘] return book (at: tonight)`
`Now you have 2 tasks in the list.`

### `FDTask` - add a task with a fixed duration
Duke will add a task with a fixed duration

Example of usage: 

`FDTask read book /for 2`

Expected outcome:

`Got it. I've added this task:`
`[F][✘] read book (needs 2.00 hours)`
`Now you have 2 tasks in the list.`

### `find` - find the task with the given keyword
Duke will find the task with the given keyword

Example of usage: 

`find book`

Expected outcome:

`Here are the matching tasks in your list:`
`1.[F][✘] read book (needs 2.00 hours)`

### `bye` - say goodbye to Duke.
Duke will update you background memory with your current todo list

Example of usage: 

`bye`

Expected outcome:

`Bye. Hope to see you again soon!`
