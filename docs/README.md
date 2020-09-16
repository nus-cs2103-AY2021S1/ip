# User Guide

Are you tired of forgetting what you are supposed to do? Need someone to help keep track of your work progress?\
Say hi to Bearimy, your personal assistant that will help you keep track of all your tasks! Read on to find out what he is capable of.

## **Features** 

### Feature 1: Add tasks
This is the fundamental feature of Bearimy. You may specify a `todo`, `deadline` or `event` type task, whichever best suits a particular task.

### Feature 2: Delete tasks
List of tasks getting too long? Don't worry, the `delete` feature helps to get rid of unwanted tasks to free up space.

### Feature 3: Mark tasks
Indicates a completed task with 'O'.

### Feature 4: Listing
Displays a list of all existing tasks that were stored by Bearimy.

### Feature 5: Finding tasks
Displays a list of all existing tasks that contain the keyword that you have specified.

### Feature 6: Handling tasks with the same name
Allows you to decide whether to keep a task that is already stored by Bearimy.

## Usage

### `todo` - Creates a todo task
Format: `todo <*name of task*>`\
Example of usage: `todo read a book`\
Expected outcome: `todo added to list`

### `deadline` - Creates a deadline task
Format: `deadline <*name of task*> /by <*YYYY-MM-DD HHmm*>`\
Example of usage: `deadline submit proposal /by 2020-09-13 2300`\
Expected outcome: `deadline added to list`

### `event` - Creates an event task
Format: `event <*name of task*> /at <*description*>`\
Example of usage: `event attend workshop /at 12.30pm`\
Expected outcome: `event added to list`

### `delete` - Removes a specified task
Format: `delete <*task number*>`\
Example of usage: `delete 2`\
Expected outcome: `task 2 deleted from list`

### `done` - Marks a specified task
Format: `done <*task number*>`\
Example of usage: `done 5`\
Expected outcome: `task 5 marked on list (with 'O')`

### `list` - Displays list of existing tasks
Format: `list`\
Example of usage: `list`\
Expected outcome: `list of tasks displayed`

### `find` - Finds all tasks that contain the specified keyword
Format: `find <*keyword*>`\
Example of usage: `find run`\
Expected outcome: `list of tasks with "run" keyword displayed`

### `yes` - Creates a task (only when prompted by Bearimy)
Format: `yes`\
Example of usage: `yes`\
Expected outcome: `duplicate task added to list as per normal`

### `no` - Ignores input task (only when prompted by Bearimy)
Format: `no`\
Example of usage: `no`\
Expected outcome: `duplicate task will not be added to list`

### `bye` - Exits the application
Format: `bye`\
Example of usage: `bye`\
Expected outcome: `application terminates`