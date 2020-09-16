# Alfred User Guide

Alfred is a personal assistant chat bot that lets you manage tasks, events and deadlines.

* [Features](#features)
    * [Manage tasks](#manage-tasks)
    * [Mark tasks as done](#mark-tasks-as-done)
    * [Task search](#task-search)
* [Usage](#usage)
    * [`todo` - Create a new ToDo task](#todo---create-a-new-todo-task)
    * [`event` - Create a new Event task](#event---create-a-new-event-task)
    * [`deadline` - Create a new Deadline task](#deadline---create-a-new-deadline-task)
    * [`list` - List all tasks](#list---list-all-tasks)
    * [`done` - Mark a task as done](#done---mark-a-task-as-done)
    * [`delete` - Delete a task](#delete---delete-a-task)
    * [`find` - Find tasks based on a keyword](#find---find-tasks-based-on-a-keyword)
    * [`date` - Find tasks based on a date](#date---find-tasks-based-on-a-date)
    * [`help` - Get help on the different commands available](#help---get-help-on-the-different-commands-available)
    * [`bye` - Quits the application](#bye---quits-the-application)
 * [Credits](#credits)
    
   
## Features 

### Manage tasks
Keep track of different tasks, events and deadlines that you might have.

### Mark tasks as done
Mark tasks as done once you have completed them.

### Task Search
Search for tasks based on keywords or their deadline.

## Usage

### `todo` - Create a new ToDo task.

This function creates and saves a new ToDo task.

Format:

`todo task_name`

Example of usage: 

`todo buy milk`

Expected outcome:

`Got it. I've added this task [T][✘] buy milk`

### `event` - Create a new Event task.

This function creates and saves a new Event task.

Format:

`event event_name /at event_time`

Example of usage: 

* `event concert /at 2020-12-12`
* `event concert /at 2020-12-30 2359`
* `event concert /at 2020-09-20 14:30`

Expected outcome:

* `[E][✘] concert (at: 12 December 2020)`
* `[E][✘] concert (at: 30 December 2020, 11:59 PM)`
* `[E][✘] concert (at: 12 December 2020, 2:30 PM)`

### `deadline` - Create a new Deadline task.

This function creates and saves a new Deadline task.

Format:

`deadline deadline_name /by deadline_time`

Example of usage: 

* `deadline project /by 2020-12-12`
* `deadline project /by 2020-12-30 2359`
* `deadline project /by 2020-09-20 14:30`

Expected outcome:

* `[D][✘] project (by: 12 December 2020)`
* `[D][✘] project (by: 30 December 2020, 11:59 PM)`
* `[D][✘] project (by: 12 December 2020, 2:30 PM)`

### `list` - List all tasks.

This function returns a list of all tasks, regardless of status.

Example of usage: 

`list`

Expected outcome:

 `1. [T][✘] buy milk`
 
 `2. [E][✘] concert (at: 12 December 2020)`
 
 `3. [D][✘] project (by: 30 December 2020, 11:59 PM)`

### `done` - Mark a task as done.

This function marks a task as done.

Format:

`done task_id`

Example of usage: 

`done 1`

Expected outcome:

`Nice! I've marked this task as done.`

`[T][✓] Alfred user guide`

### `delete` - Delete a task.

This function deletes a task.

Format:

`delete task_id`

Example of usage: 

`delete 1`

Expected outcome:

`Nice! I've deleted this task.`

`[T][✓] Alfred user guide`

`Now you have 5 tasks left in your list.`

### `find` - Find tasks based on a keyword.

This function finds tasks in the list based on a keyword.

Format:

`find keyword`

Example of usage: 

* `find type=todo`
* `find type=event`
* `find tyoe=deadline`
* `find status=done`
* `find status=undone`
* `find project`

Expected outcome:

* All todo tasks
* All event tasks
* All deadline tasks
* All done tasks
* All undone tasks
* All tasks with a task name that contains the word project

### `date` - Find tasks based on a date.

This function finds tasks in the list based on a date.

Format:

`date date_of_task`

Example of usage: 

`date 2020-12-30`

Expected outcome:

`1. [D][✘] project (by: 30 December 2020, 11:59 PM)`

### `help` - Get help on the different commands available.

This function links the user to the user guide.

Example of usage: 

`help`

Expected outcome:

`Please check (user_guide_link) for more details`

### `bye` - Quits the application.

This function quits the application.

Example of usage: 

`bye`

Expected outcome:

`Goodbye! The application will close shortly...`

## Credits

* The GUI was created with help from a JavaFX tutorial [here](https://se-education.org/guides/tutorials/javaFx.html).
* The Batman image was retrieved from [here](https://www.pngfind.com/mpng/hwwTTi_free-png-download-lego-batman-movie-clipart-png/).
* The Alfred image was retrieved from [here](https://www.pngfind.com/mpng/TRwRibh_alfred-lego-batman-movie-lego-batman-alfred-png/).
* The background image was retrieved from [here](https://www.deviantart.com/urlogicfails/art/1966-Batman-and-Robin-Phone-Background-554954684).
