# User Guide
NotDuke is a **desktop chatbot designed for managing your personal tasks** 
through a graphical user interface (GUI). 

## Getting started
1. Ensure you have Java 11 or above installed on your computer.
1. Download the latest version from [here](https://github.com/StopTakingAllTheNames/ip/releases).
1. Copy the file to your desired local directory.
1. Run the application via `java -jar {PATH_TO_JAR}` in the terminal.
Duke will search for a saved file in `data/tasks.txt` and create one if it does 
not exist.
1. Type the command in the text field and click the send button to execute it.
1. Refer to the list of features below for all available commands.

## Features 
Items in curly braces (e.g. `{TASK_DESCRIPTION}`) are mandatory fields 
to be supplied by the user.

### Viewing commands: `help`
Shows all available commands.

Format: `help`

### Listing all tasks: `list`
Shows a list of all tasks.

Format: `list`

### Adding a new to-do task: `todo`
Adds a new to-do to the list of tasks.

Format: `todo {TASK_DESCRIPTION}`

Example: `todo buy eggs`

### Adding a new event task: `event`
Adds a new event to the list of tasks.
Accepted date formats are:
* dd-MM-yyyy
* dd/MM/yyyy

Format: `event {TASK_DESCRIPTION} /at {DATE}`

Example: `event summer festival /at 11/9/20`

### Adding a new deadline task: `deadline`
Adds a new deadline to the list of tasks.
Accepted date formats are:
* dd-MM-yyyy
* dd/MM/yyyy

Format: `deadline {TASK_DESCRIPTION} /by {DEADLINE}`

Example: `deadline submit report /by 12/8/20`

### Finding a task: `find`
Finds all tasks that contain a given keyword in the description.

Format: `find {KEYWORD}` - lists all tasks containing the keyword.

### Saving all tasks locally: `save`
Saves all tasks to `data/tasks.txt`. 
**All changes to the task list have to be manually saved before 
exiting the application.**

Format: `save`

### Marking a task as complete `done`
Marks a task in the given index of the list as complete. 
The index must be a **positive integer**. 
This command labels the task with a tick.

Format: `done {INDEX}`

Example: `done 2` - Marks the second item on the list of tasks as complete.

### Deleting a task: `delete`
Deletes a task in the given index from the list.
The index must be a **positive integer**.
Users must remember to save all changes after using this command.

Format: `delete {INDEX}`

Example: `delete 1` - Deletes the first item from the list of tasks.

## Command Summary

Action | Format, Example(s)
---|---
Help | `help`
List | `list`
Add to-do | `todo {TASK_DESCRIPTION}`<br> e.g. `todo buy eggs`
Add event | `event {TASK_DESCRIPTION} /at {DATE}`<br> e.g. `event festival /at 11/9/20`
Add deadline | `deadline {TASK_DESCRIPTION} /by {DEADLINE}`<br> e.g. `deadline submit report /by 13/8/20`
Save | `save`
Mark task complete | `done {INDEX}`<br> e.g. `done 2`
Delete task | `delete {INDEX}`<br> e.g. `delete 1`
Find task | `find {KEYWORD}`<br> e.g. `find shop`