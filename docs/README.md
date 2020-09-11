# User Guide for TaskBot, your virtual task-keeping assistant

## Features 

## Getting Help 

### Type 'help' in the search bar to get instructions on how to use TaskBot. 
### You may add the feature's name to find out more about that feature.

Example usage: | Expected outcome:
-------------- | -----------------

`help todo`    | `"Adds a todo task to the tasks list
               | __Format: todo [desc]__
               | __desc: The description of the todo task__


## Adding Tasks

### TaskBot supports three types of tasks, `todo`, `deadline`and `event` tasks.
### Tasks are automatically saved for future viewing once they are inserted. 

### Todo
A todo task is the most basic of tasks. 	

### `todo (desc)` - TaskBot takes in the task description as a mandatory argument.

Example usage: | Expected outcome:
-------------- | -----------------

`todo go running`    | Adds a todo task with the description of "go running"

### Deadline
A deadline task supports the inclusion of a deadline. 	

### `deadline (desc) /by (time)` - The additional time parameter follows the format YYYY-MM-dd HHmm in 24 hour format.

Example usage: | Expected outcome:
-------------- | -----------------

`deadline return book /by 2020-09-15 2200`    | Adds a deadline to return the book by the time specified

### Event
An event task is a task that occurs by the specified time. 	

### `event (desc) /at (time)` - The additional time parameter follows the format YYYY-MM-dd HHmm in 24 hour format.

Example usage: | Expected outcome:
-------------- | -----------------

`event meet friends /at 2020-09-15 1000`    | Adds an event to meet friends by the time specified

## Searching for tasks

### TaskBot supports viewing of the tasks added so far and also searching by keywords.

### List
Lists all the tasks that have been added, in order of when they were added. 	

### `list` - No additional arguments needed

Example usage: | Expected outcome:
-------------- | -----------------

`list`    | A numbered list of tasks. If the list is empty, TaskBot will notify you via a message.

### Find
Allows you to find tasks which description matches the any of the given keywords	

### `find (keywords)` - Multiple keyword entries are supported, just seperate them using whitespaces.

Example usage: | Expected outcome:
-------------- | -----------------

`find run book`    | Shows a list of tasks with either "run", "book" or both in their descriptions.

### Upcoming
Allows you to find tasks which happens in the next `n` days. Todo tasks are regarded to always happen and are always listed.	

### `upcoming (days)` - the number of days ahead to search for tasks, integer values only.

Example usage: | Expected outcome:
-------------- | -----------------

`upcoming 2`    | Shows a list of tasks happening within the next 2 days.

## Editing tasks

### You may edit the tasks in various ways.

### Done
Completes the task at the index in the list provided by the `list` command.	

### `done (index)` - Completes a task at the index, if valid.

Example usage: | Expected outcome:
-------------- | -----------------

`done 2`    | Marks the task at index 2 as complete, as viewed when the `list` command is called.

### Delete
You may also delete any tasks that you no longer require.	

### `delete (index)` - Deletes a task at the index, if valid.

Example usage: | Expected outcome:
-------------- | -----------------

`delete 2`    | Deletes the task at index 2 as complete, as viewed when the `list` command is called.

## Exiting the program

### You may simply exit the program by typing the `exit` command.
### A goodbye message will appear from TaskBot, and the application will close after a few seconds.

 




