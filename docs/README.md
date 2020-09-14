# Duke User Guide
Duke is a task management tool that helps users to keep track of various tasks.
To interact with Duke, users can type in commands to perform various functions Duke has to offer.

![Image of Ui](https://raw.githubusercontent.com/junhui-phoon/ip/master/docs/Ui.png)
## Features

### Add Tasks 
You can add the following tasks: `todo` `deadline` `event`.

### Show Task List
Shows you the tasks you've entered, in a `list` format.

### Delete Tasks
You can `delete` task(s) from the Task List.

### Mark Tasks as Done
You can mark task(s) as `done` in the Task List.

### Find Tasks
You can `find` task(s) matching with a keyword.

### View Schedules
You can view task(s) that occurs on a specific `date`.

## Usage

### `todo <description>`

Adds a `todo` task with description to the task list.

Example of usage: 

`todo return books`

### `event <description> /at <date> <time>`

Adds an `event` task with a description, date (dd/MM/yy) and optional time (HH:mm) to the task list.

Examples of usage: 

`event The Weeknd Concert /at 14/2/20 20:00`  

`event John's Birthday /at 11/07/20`  

### `deadline <description> /by <date> <time>`

Adds a `deadline` task with a description, date (dd/MM/yy) and optional time (HH:mm) to the task list.

Example of usage: 

`deadline CS2103T iP /by 15/09/20 23:59`  

`deadline CS3230 Assignment /by 19/09/20`

### `list`

Shows you the Task List.

Expected outcome:  

![Image of list_Outcome](https://raw.githubusercontent.com/junhui-phoon/ip/master/docs/list_outcome.png)

### `delete <task number(s)>`

Delete task(s) from the Task List.

Examples of usage: 

`delete 1`

`delete 1 2 3`

### `done <task number(s)>`

Mark task(s) as Done from the Task List.

Examples of usage: 

`done 1`

`done 1 2 3`

### `find <keyword>`

Find task(s) that match the keyword.

Example of usage: 

`find CS2103T`

### `date <date>`

View task(s) that occurs on this date (dd/MM/yy).

Example of usage: 

`date 14/02/20`

### `help`

Shows the help page which includes the available commands.

### `bye`

Exits Duke Application

## Advanced Features
Duke has a feature that automatically load and save the Task List as `duke.txt` in your Desktop.

When Duke starts up, it will search for `duke.txt` in your Desktop, if the file does not exist,
Duke will automatically create `duke.txt` in your Desktop when you run these commands: `todo`
`event` `deadline` `done` `delete`.

If `duke.txt` exists in your Desktop, it will attempt to load into the application and existing
tasks will be shown in your Task List when you run the `list` command.

New users are recommended not to modify `duke.txt` as it may cause Duke to have loading error due to
syntax errors.

### Advanced Features Usage

You can modify `duke.txt` to add, delete or mark `todo` `event` `deadline` tasks as `done`.

#### `<Task Type> | <Task Status> | <Description> | <Date> <Time>`

* Task Type:
    * T - `todo`
    * D - `deadline`
    * E - `event`
* Task Status:
    * 0 - Task not done.
    * 1 - Task done.
* Date:
    * `dd/MM/yy`
* Time:
    * `HH:mm`
    
Example of Usage:

`T | 1 | return books`  

`D | 0 | cs2103t | 15/09/20 23:59`  

`E | 0 | cs2103t project meeting | 20/09/20 14:00`  

`D | 0 | cs3230 assignment | 18/09/20 23:59`



