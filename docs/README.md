# User Guide

## Features 

### Feature 1: View tasks
Shows all tasks with status and date.

### Feature 2: Add a task
Tasks can be in the form of a todo [T], deadline [D], or event [E].

### Feature 3: Mark as done
Marks specified task as done.

### Feature 4: Delete a task
Deletes a specific task.

### Feature 5: Sort tasks
Sorts tasks by dates.

### Feature 6: Exit the program
Saves all data and closes the window for you.

## Usage

### `todo` - Adds a todo item to the list

Format: `todo DESCRIPTION`

Example of usage: 

`todo fail CS2103T`

Expected outcome:

    Got it. I've added this task:
        [T][✘] fail CS2103T
    You now have ... tasks in the list.

### `deadline` - Adds a deadline item to the list, with a specified date

Format: `deadline DESCRIPTION /by YYYY-MM-DD`

Example of usage: 

`deadline fail CS2103T /by 2020-12-01`

Expected outcome:

    Got it. I've added this task:
        [D][✘] fail CS2103T (by: 2020-12-01)
    You now have ... tasks in the list.

### `event` - Adds an event item to the list, with a specified date

Format: `event DESCRIPTION /at YYYY-MM-DD`

Example of usage: 

`event fail CS2103T /at 2020-12-02`

Expected outcome:

    Got it. I've added this task:
        [E][✘] fail CS2103T (at: 2020-12-02)
    You now have ... tasks in the list.
    
### `list` - Shows all current tasks

Format: `list`

Example of usage: 

`list`

Expected outcome:

If there are no items in the list:

    Theres currently nothing in your list.
    
If there are existing items in the list, it may look something like:

    1. [T][✓] fail CS2103T
    2. [D][✓] fail CS2103T (by: 2020-12-01)
    3. [E][✓] fail CS2103T (at: 2020-12-02)
    
### `done` - Marks a current task as done

Format: `done ITEM NUMBER`

Example of usage: 

`done 1`

Expected outcome:

    Nice, I've marked this item as done:
        [T][✓] fail CS2103T

### `delete` - Deletes an item from the list.

Format: `delete ITEM NUMBER`

Example of usage: 

`delete 1`

Expected outcome:

    Noted, I've removed this task:
        [T][✓] fail CS2103T
    You now have ... items in the list.
        
### `sort` - Shows all current tasks

Format: `sort`

Example of usage: 

`sort`

Expected outcome:

    Tasks sorted by date
    1. [D][✓] fail CS2103T (by: 2020-12-01)
    2. [E][✓] fail CS2103T (at: 2020-12-02)
    3. [T][✓] fail CS2103T
    
### `bye` - Exits the program.

Format: `bye`

Example of usage: 

`bye`

Expected outcome:

The window closes.