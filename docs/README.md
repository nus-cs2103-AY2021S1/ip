# DUKE

## Quick Start
1. Ensure you have java 11 or above installed in your Computer.
2. Download the latest duke.jar from here
3. Copy the file to the folder you want to use as the _home folder_ 
    for your Duke bot.
4. Double-click the file to start the app. The GUI similar to the one
    shown below should appear in a few seconds. The example below contains
    some sample data.
    
    ![Image of Ui]
    (https://github.com/seantaysl/ip/blob/master/docs/Ui.png)
5. Type the command in the command box and press Enter to execute it.
<br/> Some example commands are listed below:

    * `list`: Lists all tasks.
    * `help`: Shows list of commands.
    * `delete 2`: Deletes the 2nd task shown in the list.
    * `bye`: Exits the app after 3 seconds
    
6. Refer to the Features below for details of each command.

## Features 

**Notes on the command format**
* Words in square brackets are parameters to be supplied by the user.
<br/> eg. `todo [taskname]`, `[taskname]` is a parameter which can be used as `todo borrow book`

* items in single inverted commas should be entered as it is.
<br/> eg. `update [index] ['name' or 'time'] [input]`, `['name' or 'time']` is a parameter which
which can be used as `update 1 name udpatedName`

## Usage

### `help` - Shows list of commands

Shows a list of commands that can be used.

Format: `help`

Example of usage: `help`

### `list` - Lists all tasks

Generates a list of current tasks.

Example of usage: `list`


### `find` - Searches through all tasks

Lists all tasks that contain input keyword.

Format: `find [keyword]` 

Example of usage: `find book`


### `todo` - Add new todo task

Creates a new todo task and adds it to the current list.

Format: `todo [taskname]` 

Example of usage: `todo borrow book`

### `deadline` - Add new deadline task

Creates a new deadline task and adds it to the current list.

Format: `deadline [taskname] /by [yyyy-MM-dd HHmm]` 

Example of usage: `deadline return book /by 2020-10-10 1800`

### `event` - Add new event task

Creates a new event task and adds it to the current list.

Format: `event [taskname] /at [yyyy-MM-dd HHmm]` 

Example of usage: `event borrow book /by 2020-10-01 1800`

### `delete` - Delete selected task from list

Deletes task at given index.

Format: `delete [index or 'all']` 

Example of usage: 
1. `delete 1`
2. `delete all`

### `update` - Update selected task in list

Updates name or time for task at given index.

Format: `update [index] ['name' or 'time'] [input]` 

Example of usage: 
1. `update 1 name udpatedName`
2. `update 1 time 2020-10-10 1500`

### `bye` - Shuts down Duke bot

Shuts down Duke bot and closes the window after 3 seconds.

Format: `bye`

Example of usage: `bye`
