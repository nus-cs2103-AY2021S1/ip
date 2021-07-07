# User Guide
Pingu helper is a desktop app for managing tasks in a todo list format, optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI).
![Ui](https://user-images.githubusercontent.com/65865022/93659977-18abf500-fa7d-11ea-99e4-56b231b8e5ad.png)

## Usage
 
### Viewing help: `help` 
Shows a breakdown of how to use each command available.
![helpcommand png](https://user-images.githubusercontent.com/65865022/93021954-8f2d9a80-f618-11ea-8c27-2a4b1dd03fb5.PNG)

Format: `help`



### Listing all tasks: `list`
Shows a list of all tasks in the todo list.

Format: `list`




### Adding a task: `todo`
Adds a new task to the todo list.

Format: `todo [DESCRIPTION]`




### Adding a task with a deadline: `deadline`
Adds a new task with a deadline to the todo list.

Format: `deadline [DESCRIPTION] /by [DATE]`

*Date must be in YYYY-MM-DD format.
*Both fields must be provided.




### Adding an event: `event`
Adds a new upcoming event to the todo list.

Format: `event [DESCRIPTION] /at [DATE]`

*Date must be in YYYY-MM-DD format.
*Both fields must be provided.




### Marking a task as completed: `done`
Marks a task in the todo list as having been completed.

Format: `done [INDEX]`

- Marks the task at the specified [INDEX] as having been completed.
- The index refers to the index number shown in the displayed todo list.
- The index must be a positive integer 1, 2, 3, ...

Example: `done 2` marks the 2nd task in the todo list as done.




### Deleting a task: `delete`
Deletes a task from the todo list.

Format: `delete [INDEX]`

- Deletes the task at the specified [INDEX].
- The index refers to the index number shown in the displayed todo list.
- The index must be a positive integer 1, 2, 3, ...

Example: `delete 2` deletes the 2nd task in the todo list.




### Locating tasks by name: `find`
Finds tasks whose descriptions contain the given keyword.

Format: `find [KEYWORD]`

- The search is case-insensitive. e.g. book will match Book




### Exiting the program: `bye`
Exits the program.

Format: `bye`


