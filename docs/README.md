# User Guide For Meimei Bot

 Meimei Bot is an interactive bot that offers commands to help the user keep track of a mutable list of tasks that can 
 be of 3 types: 
 * `Todo`,
 * `Deadline` or 
 * `Event`.
 
 These tasks can also be marked as done and will be saved in the hard disk.
 
 After the execution of any command, the bot will return a reply to help the user along or confirm their request.
 
## Getting Started
Type in the text box and press Enter or click the `Send` button to execute a command.

## Features 

### Adding a task: `todo` `deadline` `event`
Adds a task that is one of three types (`Todo`, `Deadline` or `Event`.) to the list of tasks stored by the bot.

#### Usage
Use one of the following commands:
##### `todo` - For simple tasks that need to be done.
##### `deadline` - For tasks that need to be completed by a specific deadline.
##### `event` - For events scheduled at a specific date and time.

Type the commands listed above followed by the description of the task you want to add.

Format (for each type of task):
* `todo {name/description of the task}`
* `deadline {name/description of the task} /by {date and time in this format: dd/MM/yyyy HH:mm}`
* `event {name/description of the task} /at {date and time in this format: dd/MM/yyyy HH:mm}`

Examples: 
* `todo homework`
* `deadline essay /by 21/09/2020 23:59`
* `event workshop /at 25/09/2020 17:00`

### Listing all tasks: `list`
Shows a list of all currently saved tasks.

#### Usage
Format: `list`

Example reply from bot: 

`Na, here is your list lah:`

`1.[T][✓] Spring cleaning`

`2.[D][✓] CCA video by: 2 Oct 2020, 11.59 PM`

`3.[E][✘] Dance workshop at: 21 Sep 2020, 7.00 PM`

### Deleting a task: `delete`
Deletes a task based on the position of the tasks in the whole list of current tasks.

#### Usage
Format: `delete {number corresponding to the task to be deleted}`

Example: 
* `delete 1` Deletes the first task in the list.

### Marking a task as done: `done`
Marks a task as done based on the position of the tasks in the whole list of current tasks.

#### Usage
Format: `done {number corresponding to the task to be marked}`

Example: 
* `done 1` Marks the first task in the list as done.

### Finding tasks: `find`
Asks the bot to find and return tasks that fully or partially match a user given string.

#### Usage
Format: `find {keyword(s) that the user is searching for}`

Examples: 
* `find home work` Asks the bot to look for tasks that include the strings "home" or "work" in their descriptions/names.

### Exiting the bot: `bye`
Terminates the bot and quits the application, closing the windows running the application.

#### Usage
Format: `bye`