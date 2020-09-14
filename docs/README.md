# DUKE
Duke bot is a **desktop app for managing tasks, optimized for use via a 
Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). 
If you can type fast, AB3 can get your contact management tasks done faster than traditional GUI apps.

* [Quick Start](https://github.com/seantaysl/ip#quick-start)
* [Features](https://github.com/seantaysl/ip#features)
    * [Viewing help: `help`](https://github.com/seantaysl/ip#help---shows-list-of-commands)
    * [Listing tasks: `list`](https://github.com/seantaysl/ip#list---lists-all-tasks)
    * [Finding tasks: `find`](https://github.com/seantaysl/ip#find---searches-through-all-tasks)
    * [Adding Todo task: `todo`](https://github.com/seantaysl/ip#todo---add-new-todo-task)
    * [Adding Deadline task: `deadline`](https://github.com/seantaysl/ip#deadline---add-new-deadline-task)
    * [Adding Event task: `event`](https://github.com/seantaysl/ip#event---add-new-event-task)
    * [Deleting task: `delete`](https://github.com/seantaysl/ip#delete---delete-selected-task-from-list)
    * [Updating task: `update`](https://github.com/seantaysl/ip#update---update-selected-task-in-list)
    * [Exiting bot: `bye`](https://github.com/seantaysl/ip#bye---shuts-down-duke-bot)

## Quick Start
1. Ensure you have java 11 or above installed in your Computer.
2. Download the latest duke.jar from [here](https://github.com/seantaysl/ip/releases/tag/A-Release)
3. Copy the file to the folder you want to use as the _home folder_ 
    for your Duke bot.
4. Double-click the file to start the app. The GUI similar to the one
    shown below should appear in a few seconds. The example below contains
    some sample data.
    
    ![Image of Ui](https://github.com/seantaysl/ip/blob/master/docs/Ui.png)

5. Type the command in the command box and press Enter to execute it.
<br/> Some example commands are listed below:

    * `list`: Lists all tasks.
    * `help`: Shows list of commands.
    * `delete 2`: Deletes the 2nd task shown in the list.
    * `bye`: Exits the app.
    
6. Refer to the [Features](https://github.com/seantaysl/ip#features) below for details of each command.

## Features 

**Notes on the command format**
* Words in square brackets are parameters to be supplied by the user.
<br/> eg. `todo [taskname]`, `[taskname]` is a parameter which can be used as `todo borrow book`

* items in single inverted commas should be entered as it is.
<br/> eg. `update [index] ['name' or 'time'] [input]`, `['name' or 'time']` is a parameter which
which can be used as `update 1 name udpatedName`


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

### `bye` - Exits Duke bot

Shuts down Duke bot and closes the window.

Format: `bye`

Example of usage: `bye`
