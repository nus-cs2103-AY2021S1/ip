# User Guide
Moco is a Personal Assistance Chatbot that helps you to 
keep track of various things. It was designed to learn how to build
small software incrementally.

## Features 

### `help` - shows a message explaining how to use the application

Shows commands with format to use Moco

Example of usage: 

`help`


### `Todo` - Creates task without any date/time attached

Creates task without any date/time attached to it and 
adds it to the task list

Example of usage: 

`todo (task description)`

Expected outcome:

`Got it. I've added this task:` \
`[T][✗] borrow book`\
`Now you have x tasks in the list.`


### `Deadline` - Creates task that needs to be done before a specific date/time

Creates task that needs to be done before a specific date/time and 
adds it to the task list

Example of usage: 

`deadline (task description) /by dd-mm-yyyy`

Expected outcome:

`Got it. I've added this task:`\
`[D][✗] return book (by: 12 Dec 2020)`\
`Now you have x tasks in the list.`


### `Event` - Creates task that start at a specific time and ends at a specific time

Creates task that start at a specific time and ends at a specific time 
and adds it to the task list

Example of usage: 

`event (task description) /at dd-mm-yyyy`

Expected outcome:

`Got it. I've added this task:`\
`[E][✗] project meeting (at: 20 May 2021)`\
`Now you have x tasks in the list.`


### `list` - returns list of tasks entered/created

Store tasks entered and display them back to
the user when requested (Shows list of tasks entered)

Example of usage: 

`list`

Expected outcome:

`Here are the tasks in your list` \
`1. [T][✗] borrow book` \
`2. [D][✗] return book (by: 12 Dec 2020)` \
`3. [E][✗] project meeting (at: 20 May 2021)`


### `done` - marks selected task as done

Shows that task has been marked done

Example of usage: 

`done 1`

Expected outcome:

`Nice! I've marked this task as done:` \
`[T][✓] borrow book` 


### `find` - finds tasks with specified keyword

Shows a list of the tasks within task listed
with the specified keyword

Example of usage: 

`find book`

Expected outcome:

`Here are the tasks in your list with your requested keyword:` \
`1. [T][✗] borrow book` \
`2. [D][✗] return book (by: 12 Dec 2020)`


### `delete` - delete tasks at specified index

Deletes task listed at specified index from
current task list created/saved

Example of usage: 

`delete 1`

Expected outcome:

`Noted. I've removed this task:` \
`1. [T][✗] borrow book`


###Saving the data

Moco Task Bot task list is saved in the hard disk automatically after any command that changes the data. 
There is no need to save manually.






