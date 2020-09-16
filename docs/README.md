# User Guide
### Everyone needs a friend! How about one that would help you take down all your important tasks? Yuki is a desktop app for managing tasks via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI).

## Quick Start
1. Ensure you have java `11` or above installed in your computer.
2. Download the latest `Yuki.jar` from [here](https://github.com/samlsm/ip/releases).
3. Copy the file to the folder you want to use as the home folder for your taskManager.
4. Double-click the file to start the app.
5. Type commands in the command box at the bottom of the page and Enter to execute. For new users, typing `help` would give you the list of available commands.


## Features 
   * [Adding a task](#adding-a-task)
   * [Deleting a task](#deleting-a-task)
   * [Listing all tasks](#listing-all-task)
   * [Mark a task as completed](#mark-a-task-as-completed)
   * [Search for task(s) using specified keywords](#search-for-tasks-using-specified-keywords)
   * [Search for task(s) on specified date](#search-for-tasks-on-specified-date)
   * [Undo a command](#undo-a-command)
   * [Help](#help)

### Adding a task
#### Yuki accepts mainly 3 types of task, including `todo`, `deadline`, `event`.

1. Add a `todo` task:
`todo` refers to a task with no date/time involved. To add such a task, make use of command `todo {descriptionOfTask
}`, press `Enter` to execute.
                                                                                                                              
    Example of usage: `todo pay mobile bill`                 
    
    Expected outcome: `[T][✘] pay mobile bill`

2. Add a `deadline` task:
`deadline` refers to a task with date involved, specifying time is optional. To add such a task, make use of command  `deadline {descriptionOfTask} /by YYYY/MM/DD` or  `deadline {descriptionOfTask} /by YYYY/MM/DD HH:MM`, press `Enter` to execute. 

    Example of usage: `deadline CS2103T IP Submission /by 2020/09/18 12:00`
    
    Expected outcome: `[D][✘] CS2103T IP Submission (FINISH by: 18 September 2020 12:00)`

3. Add an `event` task:
`event` refers to task with date, start time and end time. To add such a task, make use of command `event {descriptionOfTask} /by YYYY/MM/DD HH:MM-HH:MM`, press `Enter` to execute.

    Example of usage: `Event CS2103T Finals /at 2020/12/01 12:00-14:00`

    Expected outcome: `[E][✘] CS2103T Finals (APPEAR at: 01 December 2020 12:00-14:00)`

### Deleting a task
To delete a task permanently from storage, make use of command `delete {taskNumber}`. The taskNumber of a specific task can be found using the `list` command, press `Enter` to execute.

Example of usage: `delete 5`

Expected outcome:<br />
`*Woof* I have removed:`<br />
`[T][✘] pay mobile bill`<br />
`Now you have 4 tasks in the list. Keep going!!`

### Listing all task
To display all tasks in storage, make use of command  `list`, press `Enter` to execute.

Example of usuage:
`list`

Expected outcome:<br />
`Here are the tasks in your list *Woof*:`<br />
`1. [D][✓] CS2103T IP Submission (FINISH by: 18 September 2020 12:00)`<br />
`2. ... `

### Mark a task as completed 
To mark a specific task as completed, make use of command `done {taskNumber}`. The taskNumber of a specific task can be found using the `list` command, press `Enter` to execute.

Example of usage:
`done 4`

Expected outcome: <br />
`Good Job!!! You cleared this task:`<br />
`[D][✓] CS2103T IP Submission (FINISH by: 18 September 2020 12:00)`

### Search for task(s) using specified keywords
To search for task(s) containing specific keyword(s), make use of the command `find {keyword1} ... {keywordN}`, press `Enter` to execute.

Example of usage:
`find finals submission`

Expected outcome: <br />
`Here is the list of matching tasks in your storage:`<br />
`1. [D][✓] CS2103T IP Submission (FINISH by: 18 September 2020 12:00)`<br />
`2. [E][✘] CS2103T Finals (APPEAR at: 01 December 2020 12:00-14:00)`.

### Search for task(s) on specified date
To check for task(s) occuring on a particular day, make use of the command `check YYYY/MM/DD`, press `Enter` to execute.

Example of usage:
`check 2020/09/18`

Expected outcome: <br />
`Here is the list of ongoing tasks on 20 September 2020:`<br />
`1. [D][✓] CS2103T IP Submission (FINISH by: 18 September 2020 12:00)`<br />
...

### Undo a command
To undo any of the commands that can be undone (eg. add, delete, done), make use of the command `undo`, press `enter` to execute.

### Help
At any point in time you require assistance in using the bot, make use of the command `help` to access the list of commands available and its format. 
