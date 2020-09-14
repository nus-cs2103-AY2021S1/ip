# User Guide

## Features 
* Adding tasks: todo, event, deadline
* Read tasks: list
* Deleting tasks: delete
* Finding tasks by keywords: find
* Update tasks: task, desc, time, date
* Exiting and save: bye  

### Adding simple task: `todo` 
Adds a simple task with a short description.

Format: `todo [task description]`
* Description must be stated

Examples: 
* `todo borrow book`
* `todo sleep`
  
### Adding an event: `event`
Adds an event. 

Format: `event [event description] /at [date] [time]` 
* Description, date and time of the event must be stated.
* Date format: **yyyy-mm-dd**
* Time format: **hh:mm** in 24-hour military time.

Examples: 
* `event go to book fair /at 2020-09-09 20:58`
* `event go go to an event /at 2020-09-09 20:00`

### Adding a task with a deadline: `deadline`
Adds a task with a deadline.

Format: `deadline [deadline description] /by [date] [time]`
* Description, date and time of the deadline must be included.
* Date format: **yyyy-mm-dd**
* Time format: **hh:mm** in 24-hour military time.

Example: 
* `deadline return book /by 2020-09-09 23:59`
* `deadline submit my ip /by 2020-09-18 23:59`

## Read tasks: `list`
Reads all the saved tasks in the list.

Format: `list`

## Deleting a task: `delete`
Deletes an existing task in the task list.

Format: `delete INDEX`
* Deletes the task at the specified `INDEX`. The index refers to the index number shown in the displayed task list.
The index must be a **positive integer**.

Example: 
* `delete 1` - deletes the task at index 1 of the task list.
* `delete 4` - deletes the task at index 4 of the task list.

## Finding a task by key word: `find`
Find tasks which contains any of the keyword.

Format: `find KEYWORD`
* The search is case-insensitive.
* The full task will be searched 

Examples: 
* `find book` - will return all task that has the keyword `book`.
* `find borrow book` - will return all the task that has the keyword `borrow book`.
 
## Updating a task completely: `update task'
Replaces an existing task in the task list with another task. 

Format: `update task INDEX`
* Updates the task at the specified `INDEX`. The index refers to the index number shown in the displayed task list.
The index must be a **positive integer**.

Example: 
* `update task 1` - updates task at index 1.
* `update task 4` - updates task at index 4.

## Updating a task's description: `update desc`
Updates the description of an existing task in the task list.

Format: `update desc INDEX`
* Updates description of the task at the specified `INDEX`. The index refers to the index number shown in the displayed task list.
The index must be a **positive integer**.

Examples:
* `update desc 1` - updates the description of the task at index 1.
* `update desc 4` - updates the description of the task at index 4.

## Updating an event or a deadline's time: `update time`
Updates the time of an existing event or task with a deadline in the task list.

Format: `update time INDEX`
* Updates the time of the task at the specified `INDEX`. The index refers to the index number shown in the displayed task list.
The index must be a **positive integer**.*

Examples: 
* `update time 1` - updates the time of the task at index 1.
* `update time 4` - updates the time of the task at index 4.

## Updating an event or a deadline's date: `update date`
Updates the date of an existing event or task with a deadline in the task list.

Format: `update date INDEX`
* Updates the date of the task at the specified `INDEX`. The index refers to the index number shown in the displayed task list.
The index must be a **positive integer**.*

Examples: 
* `update date 1` - updates the date of the task at index 1.
* `update date 4` - updates the date of the task at index 4.

##Exiting the program and saving the program: `bye`
Exits the program and saves all the changes made. 

Format: `bye`
