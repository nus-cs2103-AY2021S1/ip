# TheMatrix User Guide
![alt text](matrix.png)

The matrix is a desktop app which allows you to keep track of tasks with a Graphical User Interface(GUI). The app has a chat interface which make the monitoring of tasks more human like. 
- Quick start
- Features
	1. [Add tasks](#addtask): `todo` `event` `deadline` 
	2. Display list: `list`
	3. Marking task as done: `done`
	4. Deleting tasks: `delete`
	5. Searching tasks: `find`
	6. Tagging a task: 'tag'
	7. Finding a task with tags: `findtag`
	8. Help: `help`
	9. Exiting the program: `exit`
- Quick list of command examples

## QuickStart
Please ensure that you have installed the latest version of the Jar file as well as java `11` installed to run the program.

Here are some example commands you can try:

- `todo swim`: Adds a todo `swim` to the list of tasks
- `list`: Lists all the tasks that are present
- `done 1`: Marks the first task as done.
- `delete 1`: Deletes the first task on the list.
- `bye`: Exits the app

## Features 


### Add tasks <div id ="addtask"></div>
There are three types of tasks that could be added into the list: ToDo, Event and Deadline. The various formats have various types of inputs and outputs as shown below:

#### Adding a ToDo: `todo`

Adds a todo to the list of tasks.
Format: `todo <DESCRIPTION>`

Example of usage: 
`todo swim`

Expected outcome:
```
Got it. I've added this task:
[T][✘] swim
Now you have 1 tasks in the list.
 ```
 
#### Adding a Deadline: `deadline`

Adds a deadline to the list of tasks.
Format: `deadline <DESCRIPTION> /by <DD-MM-YYYY HH:MM>`
> :stop_sign: The date that has been input has to be strictly of the following format: `DD-MM-YYYY HH:MM'

Example of usage: 
`deadline return book /by 23-02-2020 00:00`

Expected outcome:
```
Got it. I've added this task:
[D][✘] return book (by: 23 Feb 2020, 12:00 AM)
Now you have 1 tasks in the list.
 ```
 
#### Adding an Event: `event`

Adds an event to the list of tasks.
Format: `event <DESCRIPTION> /at <DD-MM-YYYY HH:MM>`
> :stop_sign: The date that has been input has to be strictly of the following format: `DD-MM-YYYY HH:MM'

Example of usage: 
`event project meeting /at 01-01-2020 12:30`

Expected outcome:
```
Got it. I've added this task:
[E][✘] project meeting (by: 1 Jan 2020, 12:30 PM)
Now you have 2 tasks in the list.
```
 
### Display list: `list`
To display the list fo tasks that has already been made.
Format: `list`
 
Example of Usage:
 `list`
 
 Expected outcome:
```
Here are the tasks in your list:
1. [T][✘] swim
2. [E][✘] project meeting (by: 1 Jan 2020, 12:30 PM)
3. [D][✘] return book (by: 23 Feb 2020, 12:00 AM)
 ```
 
 > :stop_sign: If the list is empty a warning message will be shown.
 
### Done: `done`
Marks the task as done 
Format: `done <Task Number in list>`
 
Example of Usage:
 `done 1`
 
 Example of outcome:
```
Nice! I've marked this task as done:
[T][✓] swim
```

### Delete: `delete`
Deletes the selected task from the list 
Format: `delete <Task Number in list>`
  
Example of Usage:
`delete 1`
 
Example of outcome:
```
Noted. I've removed this task:
[T][✓] swim
Now you have 2 tasks in the list.
```
 
### Find: `find`
Finds tasks with the keyword provided.
 
 Format: `find <keyword>`
 
Example of usage:
`find book`
 
Example of outcome:
```
Here are the matching tasks in your list:
1. [T][✘] read book
2. [E][✘] book discussion (by: 1 Jan 2020, 12:30 PM)
3. [D][✘] return book (by: 23 Feb 2020, 12:00 AM)
```
 > :stop_sign: You could enter partial words and it still works. For example, instead of `book` you could type `bo` and it would generate search results based on that.
 
### Tag: 'tag'
You can tag tasks with a certain tag to make it easier to group certain tasks together.

Format: `tag <task number> <tag name>`

Example of usage:
`tag 1 today'

Example of outcome:
```
Got it. I've tagged this task with: #today
[E][✘] project meeting (by: 1 Jan 2020, 12:30 PM)
Now you have 2 tasks in the list.
```
 > :stop_sign: Use tags to group multiple tasks together for easier search.
 
### FIndTag: `findtag'
This will help you search for tags with a certain tag name.

Example of usage: 'findtag today'

Example of outcome:
```
Here are the matching tasks in your list with the tag:
1. [E][✘] project meeting (by: 1 Jan 2020, 12:30 PM)
```

### Help: `help`
Calls out the help message which shows all the commands that can be entered.

Example of usage: `help`

### Bye: 'bye'
This will exit the program.

Example of usage: 'bye'

## Quicklist of commands
`todo borrow book` `deadline return book /by 23-02-2020 00:00` `event project meeting /at 01-01-2020 12:30` `done 2` `delete 2` `list` `bye` `tag 1 lol` `findtag lol` `help`





 
 
 

 
 





