# User Guide for Doraemon Task Bot

## Features 
### Adding a todo task: `todo`
Adds a todo task to the task bot. A todo task only has a description of the task and optionally, notes for the task.

Format: `todo [task to be done]` 

Optional field: `/[any notes for this task]` 

Example of usage: *todo play games /need some rest yo* 


### Adding a deadline task: `deadline`
Adds a deadline task to the task bot. A deadline task has a description of the task, deadline date and time and optionally, notes for the task.</br></br>
Format: `deadline [task to be done] /YYYY-MM-DD HHMM`</br></br>
Optional field: `/[any notes for this task]` </br></br>
Example of usage: *deadline CS2103T IP /2020-12-12 1200 /help me pls* </br></br>

### Adding an event task: `event`
Adds an event task to the task bot. An event task has a description of the task, event date, start and end time and optionally, notes for the task.</br></br>
Format: `event [task to be done] /YYYY-MM-DD HHMM-HHMM`</br></br>
Optional field: `/[any notes for this task]` </br></br>
Example of usage: *event party /2020-10-10 2000-2300 /have fun yay* </br></br>

### Listing all tasks: `list`
Shows a list of tasks stored in the task bot.</br></br>
Format: `list`</br></br>

### Completing a task: `done`
Marks a task, specified by the task number in the updated task list, as done.</br></br>
Format: `done [task number]` (Task number refers to the task number shown in the displayed task list)</br></br>
Example of usage: *done 6* </br></br>

### Deleting a task: `delete`
Deletes a task that is specified by the task number in the updated task list. </br></br>
Format: `delete [task number]`  (Task number refers to the task number shown in the displayed task list) </br></br>
Example of usage: *delete 7*</br></br>

### Finding a task by description: `find`
Finds tasks with description that matches the keyword. </br></br>
Format: `find [keyword]`</br></br>
Example of usage: *find play* </br></br>

### Finding a task by date: `print`
Finds tasks with the same date keyed in by you.</br></br>
Format: `print YYYY-MM-DD`</br></br>
Example of usage: *print 2020-12-12* </br></br>

### Exiting the bot: `bye`
Exits the program. </br></br>
Format: `bye` and click on the `exit` button
