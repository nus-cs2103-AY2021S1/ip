# User Guide

## Features 

### Manage your tasks easily
Navigate your way through a busy schedule with Batman

### Autosaving 
Any changes to tasks will automatically be updated and saved in data/tasks.txt

### CLI-first 
Increased efficiency for fast typists

### Portable 
Works without requiring an installer

## Usage

### `todo` - Creates a todo

A todo will be created. 

Format:

`todo <description>`

Example of usage: 

`todo get A+ for CS2103T`

Expected outcome:

`[T][0] get A+ for CS2103T`

### `event` - Creates an event at a specified date

An event will be created at the date specified by the user.

Format:

`event <description> /at <date>`

Example of usage: 

`event lunch with Damith /at tomorrow`

Expected outcome:

`[E][0] lunch with Damith (at: Sep 13 2020)`

### `deadline` - Creates a deadline at a specified date

A deadline will be created at the date specified by the user.

Format:

`deadline <description> /by <date>`

Example of usage: 

`deadline finish product website /by tomorrow`

Expected outcome:

`[D][0] finish product website (by: Sep 13 2020)`

### `done` - Marks a task as done

Marks a specified event/deadline/todo as done. This changes the task's status code from 0 to 1.

Format:

`done <index of task>`

Example of usage: 

`done 2`

Expected outcome:

`[D][1] finish product website (by: Sep 13 2020)`

### `list` - Describe action

Asks Batman to display all the tasks in a list to the user.

Example of usage: 

`list`

Expected outcome:

`Here are the tasks in your list:`\
`1. [E][0] lunch with Damith (at: Sep 13 2020)`\
`2. [D][1] finish product website (by: Sep 13 2020)`

### `delete` - Deletes the task at the specified index

Deletes the task at the specified index. 

Format:

`delete <index of task>`

Example of usage: 

`delete 1`

Expected outcome:

`Noted. I've removed this task:`\
`[E][0] lunch with Damith (at: Sep 13 2020)`

### `delete` - Deletes the task at the specified index

Deletes the task at the specified index. 

Format:

`delete <index of task>`

Example of usage: 

`delete 1`

Expected outcome:

`Noted. I've removed this task:`\
`[E][0] lunch with Damith (at: Sep 13 2020)`

### `find` - Finds all matching tasks

Finds and returns all tasks that match user input's search terms. 

Format:

`find <search term>`

Example of usage: 

`find lunch with Damith`

Expected outcome:

`Here are the matching tasks in your list:`\
`1.[E][0] lunch with Damith (at: Sep 13 2020)`\
`2.[E][0] lunch with Seth (at: Sep 14 2020)`

### `bye` - Closes program

Ends the conversation with Batman and closes the program after 1 second delay.

Example of usage: 

`bye`

Expected outcome:

`Farewell.`

## Acknowledgements
Natty - a third library used to parse date using natural language - http://natty.joestelmach.com/