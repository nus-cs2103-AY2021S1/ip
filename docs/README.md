# User Guide

## Features 

### Add task
User can add a todo, deadline or event task.

### Delete task
User can delelete a task in the task list.

### Find tasks with a given keyword
User can find all tasks that have a given keyword. 

### Find tasks with a tag
User can find all tasks that have a specified tag.

### Mark task as done
User can mark a specified task as done.

### View all tasks
User can view all added tasks.

### View help
User can view all possible commands.

## Usage

### `todo`/`deadline`/`event` - add task

#### `todo` - add a todo task to the list
Todos: tasks without any date/time attached to it e.g., visit new theme park 

Example of usage:

`todo visit new theme park`

Expected outcome:

`Wow, another task. Added. You sure you can finish them all?`

  `[T] [✘] borrow book`
  
  `Now you have a grand total of 1 tasks!`
  

#### `deadline` - add a todo task to the list
Deadlines: tasks that need to be done before a specific date e.g., submit CS2030 homework by 22/12/2020

Example of usage:

`deadline CS2030 homework /by 2020-12-22`

Expected outcome:

`Wow, another task. Added. You sure you can finish them all?`

  `[D] [✘] CS2030 homework (by: Dec 22 2020)`
  
  `Now you have a grand total of 1 tasks!`
  
#### `event` - add a todo task to the list
Events: tasks that has a specific time e.g., team project meeting on 25/12/2020 7pm

Example of usage:

`event project meeting /at 19:00 2020-12-25`

Expected outcome:

`Wow, another task. Added. You sure you can finish them all?`

  `[E] [✘] project meeting (at: 19:00, Dec 25 2020)`
  
  `Now you have a grand total of 1 tasks!`  



