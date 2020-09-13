# User Guide

## Features 

### Feature 1: Help
Sends instructions on how to use bot.

### Feature 2: Add a task
Tasks can be in the form of a todo [T], deadline [D], or event [E].

### Feature 3: View all tasks
Shows all tasks with status and date if any.

### Feature 4: Find a task
Shows all tasks with the specified letters in it.

### Feature 5: Mark as done
Marks specified task as done.

### Feature 6: Delete a task
Deletes a specific task.

### Feature 7: Exit the program
Saves all data and closes the window for you.

## Usage

### `help` - Shows a message with all the instructions

Format: `help`

Example of usage: 

`help`

Expected outcome:

    Hello! I'm Duke
    Send me a task in one of the following formats and I'll store it for you.
        Todo: todo <description>
        Deadline: deadline <description> /by <YYYY-MM-DD>
        Event: event <description> /at <YYYY-MM-DD>
    Send list to see all tasks.
    Send find <string of choice> to see all related tasks.
    Send done <item number> to mark an item as done
    Send delete <item number> to delete and item from the list
    Send bye to end our conversation.
    Send help to end see the commands again.

### `todo` - Adds a todo item to the list

Format: `todo DESCRIPTION`

Example of usage: 

`todo eat my breakfast`

Expected outcome:

    Got it. I've added this task:
        [T][✘] eat my breakfast
    You now have x tasks in the list.

### `deadline` - Adds a deadline item to the list, with a specified date

Format: `deadline DESCRIPTION /by YYYY-MM-DD`

Example of usage: 

`deadline do CS2103T quiz /by 2020-09-15`

Expected outcome:

    Got it. I've added this task:
        [D][✘] do CS2103T quiz (by: Sep 15 2020)
    You now have x tasks in the list.

### `event` - Adds an event item to the list, with a specified date

Format: `event DESCRIPTION /at YYYY-MM-DD`

Example of usage: 

`event marathon /at 2020-10-11`

Expected outcome:

    Got it. I've added this task:
        [E][✘] marathon (at: Oct 11 2020)
    You now have x tasks in the list.
    
### `list` - Shows all current tasks

Format: `list`

Example of usage: 

`list`

Expected outcome:

If there are no items in the list:

    Theres currently nothing in your list.
    
If there are existing items in the list, it may look something like:

    1. [T][✓] eat my breakfast
    2. [D][✘] do CS2103T quiz (by: Sep 15 2020)
    3. [E][✓] marathon (at: Oct 11 2020)
    
### `find` - Shows all current tasks

Format: `find DESCRIPTION`

Example of usage: 

`find mar`

Expected outcome:

If there are no items that matxhes your search:

    I'm sorry, there's nothing that matches your search.
    
If there are items that match your search, it may look something like:

    1. [T][✓] mark my peer's work
    2. [E][✓] marathon (at: Oct 11 2020)
    
### `done` - Marks a current task as done

Format: `done ITEM NUMBER`

Example of usage: 

`done 2`

Expected outcome:

    Nice, I've marked this item as done:
        [T][✓] make bed

### `delete` - Deletes an item from the list.

Format: `delete ITEM NUMBER`

Example of usage: 

`delete 2`

Expected outcome:

    Noted, I've removed this task:
        [T][✓] make bed
    You now have x items in the list.
    
### `bye` - Exits the program.

Format: `bye`

Example of usage: 

`bye`

Expected outcome:

The window closes.


