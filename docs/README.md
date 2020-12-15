# User Guide

## Features 

### Adding a new todo task `todo`
Creates a new todo task.

Format: `todo TITLE`
* The TITLE could be any string including whitespaces.

Example:
* `todo Read book` creates a todo task called "Read book".
### Adding a new deadline `deadline`
Creates a new deadline with its title and date.

Format: `deadline TITLE /by TIME`
* The TITLE could be any string including whitespaces.
* TIME must be of the form DD/MM/YY HHMM.

Example:
* `deadline Homework /by 21/12/2012 1600` creates a deadline called 
"Homework" of which the deadline is 21/12/2012 at 4 p.m..
### Adding a new event `event`
Creates a new event with its title and date.

Format: `event TITLE /at TIME`
* The TITLE could be any string including whitespaces.
* TIME must be of the form DD/MM/YY HHMM.

Example:
* `Event Exam /at 21/12/2012 1600` creates an event called "Exam" of which the 
time is 21/12/2012 at 4 p.m..
### Marking a task as done `done`
Marks multiple todos/deadlines/events as finished, using using their indices.

Format: `done INDEX INDEX ...`
* INDEX must be an integer from 1 to the number of task in the list.
* Marks all tasks with the included indices.

Example:
* `done 2 3 5` marks the task 2, 3, 5 in the list as finished.
### Deleting a task `delete`
Deletes multiple todos/deadlines/events, using their indices.

Format: `delete INDEX INDEX ...`
* INDEX must be an integer from 1 to the number of task in the list.
* Deletes all tasks with the included indices.

Example:
* `delete 2 3 5` deletes the tasks 2, 3, 5 in the list

### Finding a task `find`
Finds a task of which the title contains the given string.

Format: `find QUERY_STRING`
* Returns all tasks of which the title contains QUERY_STRING as a substring.

Example:
* `find read` returns tasks with title such as "read books" or "read a novel".
  
### Listing all tasks `list`
Shows all of the tasks, both finished and not finished yet.

Format: `list`
* Lists all tasks in the order added by the user.

### Clearing all tasks `clear`
Clears all tasks, both finished and not finished yet.

Format: `clear`

### Saving all local data `save`
Saves local data to an external txt file.

Format: `save`

### Ending the program `bye`
Ends and exits the program

Format: `bye`

##Command summary
Action | Format, examples
-------|------------------
todo|`todo TITLE` 
deadline|`deadline TITLE /by DD/MM/YY HHMM` e.g. deadline homework 12/2/2012 1600
event|`event TITLE /at DD/MM/YY HHMM` e.g. event exam 12/2/2012 1600
done|`done INDEX INDEX ...` e.g. done 1 3 4
delete|`delete INDEX INDEX ...` e.g. delete 1 3 4
find|`find QUERY` e.g. find book
list|`list`
clear|`clear`
save|`save`
bye|`bye`