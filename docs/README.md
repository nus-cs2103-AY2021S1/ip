# User Guide
Duke is a desktop to-do list chat-bot that allows users to 
add tasks using plain texts through interaction with the Graphical 
User Interface (GUI).
* Quick start
* Features
    * Get help - `help`
    * Adding a todo task - `todo`
    * Adding a task with a deadline - `deadline`
    * Adding an event - `event` 
    * Retrieving list of tasks - `list`
    * Finding tasks with keyword - `find`
    * Exiting the program - `bye`
* Command Summary
## Quick start
1. Ensure java 11 is installed on your computer
2. Download the duke.jar file
3. Copy the file to desired folder for your Duke application
4. For Mac users, double-click to open the app.
5. For Windows user, if there's no default applications to open the file, <br>
start up command prompt and change directory to duke.jar's directory.
6. (Continue from step 5), run file with java -jar duke.jar
7. The application with a similar GUI to the one below should appear
8. Start typing commands to start! Type `help` to see list of commands.

## Features 
**NOTES** for command format:
> * Words in `UPPER_CASE` are parameters supplied by the user. 
> <br> e.g. `todo TASK`, where `TASK` is a parameter that can be added as 
<br>`todo homework` <br>
> * Items in square brackets are optional
> <br> e.g. `deadline TASK /by DATE [/repeat DAILY/WEEKLY/MONTHLY/YEARLY]` ,
> <br> where `/repeat` is optional such as <br> `deadline homework /by 2020-09-15 /repeat daily`
> <br> or `deadline homework /by 2020-09-15` <br>
> * For `DATE`, indicate in the format `YYYY-MM-DD` 
> e.g. `2020-09-15` <br>
> * For `/repeat FREQUENCY`, the following frequencies can be used: <br>
> `daily`, `weekly`, `monthly`, `yearly`, used as such: `/repeat daily` 

### Get help - `help` 
Provides the use a list of available commands, and the ways
to use the commands in the GUI

#### Usage
Format: `help` 
<br>
To view the list of commands, type: `help`

### Adding a todo task - `todo`
Adds a todo task to the task list
#### Usage
Format: `todo TASKNAME`
* Adds a task with `TASKNAME` to the task list.
<br>

### Adding a task with a deadline - `deadline`
Adds a task with a deadline to the task list
#### Usage
Format: `deadline TASKNAME /by DATE [/repeat FREQUENCY]`
* Adds a deadline with name as `TASKNAME`
* Date deadline is indicate by `DATE`
* Deadline repeats with frequency of `FREQUENCY`
<br>

### Adding an event - `event`
Adds an event to the task list. 
#### Usage
Format: `event EVENT_NAME /at EVENT_DETAILS|DATE [/repeat FREQUENCY]`
* Adds an event with name `EVENT_NAME`
* This event has `EVENT_DETAILS` or occurs on the specified `DATE`
* `FREQUENCY` has to be the options stated in the notes above 
<br>

### Retrieving list of tasks - `list`
Retrieves all task in the task list.
#### Usage
Format: `list`
<br>

### Finding tasks with keyword - `find`
Finds all task in the task list with specified keyword.
#### Usage
Format: `find KEYWORD`
* List of all tasks that contains the keyword will be shown.
<br>

### Exiting the program - `bye`
Ends the program
#### Usage
Format: `bye`
<br>

## Command Summary
* `help`
* `todo`
* `deadline`
* `event`
* `find`
* `list`
* `bye`