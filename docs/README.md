# User Guide

## Features 

### Create deadline

Creates a deadline and adds it to the task list.

Format: `deadline <DESCRIPTION> /by <yyyy-mm-dd>`

Example: `deadline cs2103 ip /by 2020-09-18` creates a deadline with description `cs2103 ip` and date `18 Sep 2020`

### Create event

Creates an event and adds it to the task list.

Format: `event <DESCRIPTION> /at <yyyy-mm-dd>`

Example: `event cs2100 midterm /at 2020-10-10` creates an event with description `cs2100 midterm` and date `10 Oct 2020`

### Create todo

Creates a todo and adds it to the task list.

Format: `todo <DESCRIPTION>`

Example: `todo cs2103 tp` creates a todo with description `cs2103 tp`

### Delete tasks

Deletes a task, specified by its index, from the task list.

Format: `delete <INDEX>`

Example: `delete 3` deletes the 3rd task in the task list.

### Find tasks

Find tasks in the task list that contain the specified keyword.

Format: `find <KEYWORD>`

Example: `find cs` finds all tasks that contain the keyword `cs`

### List tasks

Lists all tasks in the task list.

Format: `list`

### Mark tasks as done

Marks a task, specified by its index, in the task list as done.

Format: `done <INDEX>`

Example: `done 5` marks the 5th task in the task list as done. 

### Archive

Archives a task, specified by its index, in the task list.

Format: `archive <INDEX>`

Example: `archive 4` archives the 4th task in the task list.

Archives all tasks

Format: `archive all`