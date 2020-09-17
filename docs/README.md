# User Guide
Overwhelmed by the amount of tasks to do, deadlines to remember and events to attend? Fret not, Willy is here to your rescue!
Willy is a desktop app that helps you to **manage your activities** in the form of a list.

## Features 
* Adding tasks:
    - Normal Tasks: `todo`
    - Tasks with deadline: `deadline`
    - Tasks with duration: `event`
* Update task: `done`
* Remove task: `delete`
* Find a task: `find`
* Edit a task in the list: `edit`
* Show list of tasks: `list`

---------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `willy.jar` from [here](https://github.com/se-edu/addressbook-level3/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your WillyBot.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds.
 The image below is just an example containing some sample data which will not be present in your application.
<br><br>
   ![Ui](Ui.png)
   
## Usage

### Adding Normal Tasks: `todo`
Keep track of a new task by adding the task to your list of current activities.

Format: `todo [TASK]`

Example of usage: `todo do Homework`

### Adding Tasks with deadline: `deadline`
Keep track of a new task with deadline by adding the task to your list of current activities.

Format: `deadline [TASK] /by [DATE] [TIME]`

Example of usage: `deadline project /by 20/20/2020 18:00`

### Adding Tasks with duration: `event`
Keep track of a new event by adding the event to your list of current activities.

Format: `event [TASK] /at [DATE] [TIME]`

Example of usage: `event project meeting /at 20/20/2020 18:00`

### Update task: `done`
Update the task you have done in your list of activities.

Format: `done [TASK NUMBER]`

Example of usage: `done 1`

### Remove task: `delete`
Remove the task from your list of activities.

Format: `delete [TASK NUMBER]`

Example of usage: `delete 1`

### Find a task: `find`
Find tasks in your list of activities that match with the keyword you input.

Format: `find [KEYWORD(s)]`

Example of usage: `find read`

### Edit a task in the list: `edit`
Helps you replace a certain task with a new task in your list of activities.

Format: `edit [TASK NUMBER] > [TASK DETAILS]`

Example of usage: `edit 1 > todo sleep`

### Show list of tasks: `list`
Displays all the activities in your list of activities

Format: `list`

Example of usage: `list`

## Command summary

Action | Format, Examples
--------|------------------
**Add task** | Add todo task: `todo [TASK]` <br> e.g. `todo homework` <br> Add deadline task: `deadline [TASK] /by [DATE] [TIME]` <br> e.g. `deadline project /by 20/20/2020 18:00` <br> Add event task: `event [TASK] /at [DATE] [TIME]` <br> e.g. `event project meeting /at 20/20/2020 18:00`
**Update Task** | `done [TASK NUMBER]` <br> e.g. `done 3`
**Delete Task** | `delete [TASK NUMBER]`<br> e.g. `delete 3`
**Edit** | `edit [TASK NUMBER]`<br> e.g. `edit 3`
**Find** | `find [KEYWORD(s)]`<br> e.g. `find read book`
**List** | `list`
 ***View List of Commands** | `help`
 
 ## Acknowledgement
 - Willy profile picture:  Taken with permission from cute ghost sticker pack designed by vibs. 