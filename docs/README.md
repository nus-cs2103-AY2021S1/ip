# Duke User Guide

Duke is a desktop app that helps you keep track of your tasks so that you can have a peace of mind.
You can interact with Duke, the friendly chat bot and task manager, via a Command Line Interface (CLI).

## Quick start 
1. Ensure you have `Java 11` or above installed in your computer.
2. Copy the `duke.jar` file to the folder you want to use as the home folder for Duke.
3. Open your terminal and navigate to the home folder for Duke.
4. Run `java -jar duke.jar` to start the program.

Refer to the Features section below for details of the commands available.

## Features

### Listing all the tasks - `list`

Format: `list`

Lists all the tasks currently in your task list.
There are 3 types of tasks: `Todo`, `Deadline`, and `Event`

Expected outcome:<br/>
All the tasks  will be printed with a task number.

### Listing tasks on a date - `list`

Format: `list YYYY-MM-DD`

Lists all the deadlines and events occurring on a date stated in the format YYYY-MM-DD. 

Example of usage:<br/>
`list 2020-09-20` will list all the deadlines and events on 20th Sept 2020.

Expected outcome:<br/>
All the deadlines and events occurring on YYYY-MM-DD will be printed.

### Creating Todo task - `todo`

Format: `todo TODO_DESCRIPTION`

Creates a task of Todo type. The description of this todo task should be stated in `TODO_DESCRIPTION`.
It cannot be left empty.

Example of usage:<br/>
`todo borrow book` will create a todo task with description 'borrow book'

Expected outcome:<br/>
The successfully created todo task will be printed.

### Creating Deadline task - `deadline`

Format: `deadline DEADLINE_DESCRIPTION /by DEADLINE_BY`

Creates a task of Deadline type.<br/>
The description of this deadline task should be stated in `DEADLINE_DESCRIPTION`.
It cannot be left empty.<br/>
The date or datetime that this deadline should be completed by has to be given in `DEADLINE_BY`.
`DEADLINE_BY` can be in the format `YYYY-MM-DD` (date format) or `YYYY-MM-DDTHH:MM:SS` (datetime format).

Example of usage:<br/>
`deadline return book /by 2020-09-20`
will create a deadline task with description 'return book' due by 20th Sept 2020.<br/>
`deadline return book /by 2020-09-20T23:59:00`
will create a deadline task with description 'return book' due by 20th Sept 2020, 11.59pm.

Expected outcome:<br/>
The successfully created deadline task will be printed.

### Creating Event task - `event`

Format: `event EVENT_DESCRIPTION /at EVENT_AT`

Creates a task of Event type.<br/>
The description of this event task should be stated in `EVENT_DESCRIPTION`.
It cannot be left empty.<br/>
The date or datetime that this event is at has to be given in `EVENT_AT`.
`EVENT_AT` can be in the format `YYYY-MM-DD` (date format) or `YYYY-MM-DDTHH:MM:SS` (datetime format).

Example of usage:<br/>
`event midterms /at 2020-09-29`
will create an event task with description 'midterms' happening at 29th Sept 2020.<br/>
`event midterms /at 2020-09-29T16:30:00`
will create an event task with description 'midterms' happening at 29th Sept 2020, 4.30pm.

Expected outcome:<br/>
The successfully created event task will be printed.

### Tagging a task - `tag`

Format: `tag TASK_NUMBER TAG_DESCRIPTION`

Tags the task indicated by TASK_NUMBER (shown in `list`) with the TAG_DESCRIPTION.

Example of usage:<br/>
`tag 2 groceries` will tag the 2nd task in the list with the '#groceries' tag.

Expected outcome:<br/>
The successfully tagged task will be printed.

### Finding tasks - `find`

Format: `find KEYWORD`

Finds all the tasks that has descriptions containing KEYWORD.
If KEYWORD starts with a '#', this command will find all the tasks that has the tag KEYWORD.

Example of usage:<br/>
`find book` will find all the tasks that contains 'book' in its description.
`find #groceries` will find all the tasks with the 'groceries' tag.

Expected outcome:<br/>
All the tasks found will be printed.

### Marking tasks as done - `done`

Format: `done TASK_NUMBER`

Marks the task with TASK_NUMBER (shown in `list`) as completed.

Example of usage:<br/>
`done 2` will mark the 2nd task as done.

Expected outcome:<br/>
The task marked as done will be printed.

### Deleting tasks - `delete`

Format: `delete TASK_NUMBER`

Deletes the task with TASK_NUMBER (shown in `list`).

Example of usage:<br/>
`delete 2` will delete the 2nd task.

Expected outcome:<br/>
The task deleted will be printed.

### Exiting the program - `bye`

Format: `bye`

Exits the program.

### Saving the data

All data will be saved automatically after any command that changes the data.
The data will be saved in `/data/duke.txt`