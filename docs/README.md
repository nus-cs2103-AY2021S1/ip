# User Guide
Duke is a task tracker that can be used on your computer to keep track of tasks for you.


## Features
Duke is able to:
* create tasks
* list tasks
* mark tasks
* delete tasks


## Usage


### `todo` - create a todo

Creates a new task of type "todo" in the task list.

Syntax:

        todo <Description_Of_ToDo>

Example of usage: 

       todo dishes

Expected outcome:

        Got it. I've added the task:
        [T][✘] dishes
        Now you have 1 tasks in the list.
    
    
### `deadline` | `event` - create a deadline or an event

Creates a new task of type "deadline" or "event" in the task list with an attached time. The time attached in 
`deadline` and `event` commands should be indicated following `/by` and `/at` keyword respectively.

Syntax (Event):

        Event <Description of Event> /by <DD/MM/YYYY> <HHMM>

Syntax (Deadline):

        Deadline <Description of deadline> /by <DD/MM/YYYY> <HHMM>

Example of usage: 

        event abc /at 12/12/2020 2100

        deadline homework /by 1/12/2020 2359

Expected outcome:

        Got it. I've added the task:
        [E][✘]  (At: 12 december 2020 2100 hrs)
        Now you have 2 tasks in the list.

        Got it. I've added the task:
        [D][✘] homework (By: 1 december 2020 2359 hrs)
        Now you have 3 tasks in the list.


### `list` - view all tasks

Lists out all tasks that are added in the task list.

Example of usage: 

        list

Expected outcome (if you have no tasks):

        Currently, you have no tasks!

Expected outcome (if you have tasks):

        These are your tasks:
        1.   [T][✘] dishes
        2.   [E][✘]  (At: 12 december 2020 2100 hrs)
        3.   [D][✘] homework (By: 1 december 2020 2359 hrs)

### `done` - mark a task as done

Marks a task from the list as "done" using its index from the `list` command.

Example of usage: 

        done 2

Expected outcome:

        Nice! I've marked this task as done: 
        2.   [E][✓]  (At: 12 december 2020 2100 hrs)

### `delete` - delete a task

Deletes a task from the list using its index from the `list` command.

Example of usage: 

        delete 3

Expected outcome:

        Noted. I've removed this task: 
        3.   [D][✘] homework (By: 1 december 2020 2359 hrs)

### `find` - find tasks with keyword

Finds all tasks that contain a given keyword. The keyword should appear in the description of the task. 

Example of usage: 

        find dishes

Expected outcome:

        Here's what I found: 
        [T][✘] dishes
