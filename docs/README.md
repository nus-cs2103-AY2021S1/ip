# User Guide

## Features 

### Adding a normal task `todo `
Adds a normal task to your task list.

Format: `todo TASK`

Examples: `todo Run` Adds a task called 'Run' to your task list.

### Adding a task with a deadline `deadline `
Adds a task with a deadline to your task list.

Format: `deadline TASK /by DATE`
 - DATE must be in the form YYYY-DD-MM

Examples: `deadline Homework /by 2020-09-09` Adds a task called 'Homework by 9 Sep 2020' to your task list.

### Adding an event `event `
Adds a task that occurs at a specific date and time to your task list.

Format: `event TASK /at DATE TIME`
 - DATE must be in the form YYYY-DD-MM
 - Time can be in any format
 
Examples: `event Meeting /at 2020-09-09 1400hrs` Adds a task called 'Meeting at 9 Sep 2020 1400hrs' to your task list.

### Listing all tasks `list`
Shows a list of all tasks in the task list.

Format: `list`

### deleting a task `delete `
Deletes the specified task from your task list.

Format: `delete INDEX`
- Deletes the task at the specified INDEX.
- The index refers to the index number shown in the displayed task list.
- The index must be a positive integer 1, 2, 3, …​

Example: `delete 2` deletes the 2nd task in the task list.

### Locating tasks by keyword `find `
Finds tasks whose descriptions contain the given keyword.

Format: `find KEYWORD`

- The search is case-insensitive. e.g run will match Run.
- Only the description is searched.

Examples: `find read` returns the task 'todo reading'

### Adding a reminder to a task `remind `
Adds the specified task to the reminder list.

Format: `remind INDEX`
- Adds the task to the reminder list at the specified INDEX.
- The index refers to the index number shown in the displayed task list.
- The index must be a positive integer 1, 2, 3, …​

Examples: `remind 2` Adds the 2nd task in the task list to the reminder list.

### Listing all reminders `reminders`
Shows a list of all tasks in the reminder list which will be automatically shown when entering and exiting the program.

Format: `reminders`
