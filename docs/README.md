# User Guide for Doraemon Task Bot
Doraemon Task Bot is a desktop app for managing your tasks.

## Quick Start
1. Ensure you have Java 11 or above installed in your Computer.

2. Download the latest doraemon.jar from [here](https://github.com/chiamyunqing/ip/releases/tag/A-Release).

3. Copy the file to the folder you want to use as the home folder for your Task Bot.

4. Double-click the file to start the app. 

5. Type the command in the command box and press Enter to execute it.  Refer to the Features below for details of each command.

## Features 
### Adding a todo task: `todo`
Adds a todo task to the task bot. A todo task only has a description of the task and optionally, notes for the task.

Format: `todo [task to be done]` 

Optional field: `/[any notes for this task]` 

Example of usage: *todo play games /need some rest yo* 


### Adding a deadline task: `deadline`
Adds a deadline task to the task bot. A deadline task has a description of the task, deadline date and time and optionally, notes for the task.

Format: `deadline [task to be done] /YYYY-MM-DD HHMM`

Optional field: `/[any notes for this task]` 

Example of usage: *deadline CS2103T IP /2020-12-12 1200 /help me pls* 


### Adding an event task: `event`
Adds an event task to the task bot. An event task has a description of the task, event date, start and end time and optionally, notes for the task.

Format: `event [task to be done] /YYYY-MM-DD HHMM-HHMM`

Optional field: `/[any notes for this task]` 

Example of usage: *event party /2020-10-10 2000-2300 /have fun yay* 


### Listing all tasks: `list`
Shows a list of tasks stored in the task bot.

Format: `list`


### Completing a task: `done`
Marks a task, specified by the task number in the updated task list, as done.

Format: `done [task number]` (Task number refers to the task number shown in the displayed task list)

Example of usage: *done 6*


### Deleting a task: `delete`
Deletes a task that is specified by the task number in the updated task list.

Format: `delete [task number]`  (Task number refers to the task number shown in the displayed task list)

Example of usage: *delete 7*


### Finding a task by description: `find`
Finds tasks with description that matches the keyword. 

Format: `find [keyword]`

Example of usage: *find play* 


### Finding a task by date: `print`
Finds tasks with the same date keyed in by you.

Format: `print YYYY-MM-DD`

Example of usage: *print 2020-12-12* 


### Exiting the bot: `bye`
Exits the program. 

Format: `bye` and click on the `exit` button

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Add Todo Task** | `todo [task to be done] /[any notes for this task]` <br> e.g. `todo play games /need some rest yo`
**Add Deadline Task** | `deadline [task to be done] /YYYY-MM-DD HHMM` <br> e.g. `deadline CS2103T IP /2020-12-12 1200 /help me pls`
**Add Event Task** | `event [task to be done] /YYYY-MM-DD HHMM-HHMM /[any notes for this task]`<br> e.g. `event party /2020-10-10 2000-2300 /have fun yay`
**List Tasks** | `list`
**Done Task** | `done [task number]` <br> e.g.`done 4`
**Delete Task** | `delete [task number]`<br> e.g. `delete 3`
**Find Task By Description** | `find [keyword]` <br> e.g.`find play`
**Find Task By Date** | `print YYYY-MM-DD` <br> e.g. `print 2020-12-12`
**Exit** | `bye`
