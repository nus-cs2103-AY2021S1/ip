# User Guide

Hyu's Drive-In is a desktop app for managing tasks, 
optimized for use via a Command Line Interface (CLI) while still having 
the benefits of a Graphical User Interface (GUI). 
If you can type fast, Hyu's Drive-In can get your daily tasks done faster than traditional GUI apps.


## Features 
Words in `UPPER_CASE` are the parameters to be supplied by the user.
e.g. in `todo DESCRIPTION`, `DESCRIPTION` is a parameter which can be used as `todo read book`. 



### `todo` - Adding a ToDo Item

Adds a Todo Item into the list of tasks. 

Format: `todo DESCRIPTION`

Example:

* `todo read book` will add an undone item `[T][x] read book` to the list. 

### `deadline` - Adding a Deadline 

Adds a Deadline into the list of tasks. 

Format: `deadline DESCRIPTION /by DEADLINE`
* `DEADLINE` needs to be in the format `YYYY/MM/DD`

Example:

* `deadline return book /by 2020/09/14` will add an undone deadline `[D][x] return book (by: Sep 14 2020)` 
   to the list. 

### `event` - Adding an Event 

Adds an Event into the list of tasks. 

Format: `event DESCRIPTION /at DATETIME`
* `DATETIME` needs to be in the format `YYYY/MM/DD HHMM`

Example:

* `event tP Meeting /at 2020/09/14 1400` will add an undone event `[E][x] tP Meeting (at: Sep 14 2020 2pm)`

### `list` - Listing all tasks

Lists all tasks in the list.

Format: `list`

### `find` - Locating tasks by keyword

Finds tasks which description contains the given word

Format: `find KEYWORD`
* The search is case-sensitive e.g. `Assignment` will not match `assignment`
* Only the description is searched. 
* Only full words will be matched. e.g. `Assignment` will not match `Assignments`
* Multiple `ToDo` Items, `Deadlines` and `Events` matching the keyword can be returned.  

Example: 
* `find Assignment` returns Deadline `CS2105 Assignment`, Deadline `CS2100 Assignment` and Event `Assignment Meeting`

![find Example](find.png)

### `save` - Saving current tasks

Saves the current list into a local file, which will be loaded into application every time it runs. 

Format: `save`

### `sort` - Sorting current tasks

Sorts the current list based

Format: `sort /type` OR `sort /date`
* Only the above two commands are valid. 

Examples: 
* `sort /type` will display a list that is sorted by type, with ToDo items first, followed by Deadlines, then Events. 
* `sort /date` will display a list that is sorted by date, with undated ToDo items first. 

### `done` - Marking a task as Done

Marks a specified `ToDo` Item, `Deadline` or `Event` as Done. 

Format: `done INDEX`
* Marks a specified task at the specified `INDEX`. 
* The index refers to the index number shown in the overall task list. 
* The index **must be a positive integer** 1, 2, 3, ...

Example:

* `list` followed by `done 1` marks the first task item in the list as Done. 
* `sort /type` followed by `done 1` marks the first item in the result of the `sort` command as done. 
* `find Assignment` followed by `done 1` marks the first item in the overall list as done, but not the first 
    item in the list returned by the `find` command. 

### `delete` - Deleting a task

Deletes a specified `ToDo` Item, `Deadline` or `Event`. 

Format: `delete INDEX`
* Marks a specified task at the specified `INDEX`. 
* The index refers to the index number shown in the overall task list. 
* The index **must be a positive integer** 1, 2, 3, ...

Example:

* `list` followed by `delete 1` deletes the first task item in the list. 
* `sort /type` followed by `delete 1` deletes the first item in the result of the `find` command. 
* `find Assignment` followed by `delete 1` deletes the first item in the overall list, but not the first 
    item in the list returned by the `find` command. 
 
 ### `clear` - Clears all saved data
 
 Clears all saved data.  
 
 Format: `clear`
 
### `bye` - Exiting the Program

Exits the program. 

Format: `exit`

## Feature Summary 


Command | Format/ Examples
--------|-----------------
todo | `todo DESCRIPTION` e.g. `todo read book`
deadline | `deadline DESCRIPTION /by DEADLINE` e.g. `deadline return book /by 2020/09/15`
event | `event DESCRIPTION /at DATETIME` e.g. `event tP Meeting /at 2020/09/14 1400`
list | `list`
find | `find KEYWORD` e.g. `find Assignment`
save | `save`
sort | `sort /type` or `sort /date`
done | `done INDEX`
delete | `delete INDEX` 
clear | `clear`
bye | `bye`
