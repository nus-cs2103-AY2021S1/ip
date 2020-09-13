# User Guide

## Quick start
1. Ensure that you have Java `11` or above installed in your Computer.

2. Donwload the latest duke.jar from [here](https://github.com/xnoobftw/ip/releases/tag/v0.2).

3. Create a folder and place duke.jar in it.

4. Open duke.jar. The GUI should appear in a few seconds. A data folder should be created upon 
opening duke.jar file (leave that untouched).

5. Type the command in the text box and press Enter / Send to execute it.

Examples include:

* `list`: Lists all Tasks.

* `todo eat`: Adds a ToDo Task called eat.

* `deadline homework /by 12-09-2020 1900`: Adds a Deadline Task called homework with the date time of 12 SEP 2020 7pm.

* `event dance /at 12-09-2020 1900`: Adds a Event Task called dance with the date time of 12 SEP 2020 7pm.

* `delete 1`: Deletes Task 1 as shown from `list`.

* `find eat`: Finds tasks with description that contains eat and lists them.

* `done 1`: Ticks Task 1 as done as shown from `list`.

* `bye`: exits the app and saves Tasks.

6. Refer to Features below for details of each command.

## Features 

### Listing all Tasks `list`
Shows a list of all task in tasklist.

Format: `list`

### Adding a To Do Task `todo`
Adds a To Do task with its description appended.

Format: `todo TASK_DESCRIPTION`

### Adding a Deadline Task `deadline`
Adds a Deadline task with its description and date time appended

Format: `deadline TASK_DESCRIPTION /by DD-MM-YYYY HHHH`

### Adding an Event Task `event`
Adds a Event task with its description and date time appended

Format: `event TASK_DESCRIPTION /at DD-MM-YYYY HHHH`

### Delete a Task `delete`
Deletes a Task depending on its position from `list`

Format: `delete ITEM_INDEX`

### Finding task based on description `find`
Finds and returns a list of task that contains description appended to find command.

Format: `find TASK_DESCRIPTION`

### Marking a task as done `done`
Marks a Task as done depending on its position from `list`

Format: `done ITEM_INDEX`

### Exit and save `bye`
Saves and exits from application

Format: `bye`
