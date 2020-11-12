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

#### `todo` - adds a todo task to the list
Todos: tasks without any date/time attached to it e.g., visit new theme park 

<i> Format: </i> `todo description (#tag)` 

<i> Example of usage: </i>

`todo visit new theme park`

<i> Expected outcome: </i>

`Wow, another task. Added. You sure you can finish them all?`

  `[T] [✘] borrow book`
  
  `Now you have a grand total of 1 tasks!`
  

#### `deadline` - adds a deadline task to the list
Deadlines: tasks that need to be done before a specific date e.g., submit CS2030 homework by 22/12/2020

<i> Format: </i> `deadline description /by YYYY-MM-DD (#tag)` 

<i> Example of usage: </i> 

`deadline CS2030 homework /by 2020-12-22`

<i> Expected outcome: </i>

`Wow, another task. Added. You sure you can finish them all?`

  `[D] [✘] CS2030 homework (by: Dec 22 2020)`
  
  `Now you have a grand total of 1 tasks!`
  
#### `event` - adds an event task to the list
Events: tasks that has a specific time e.g., team project meeting on 25/12/2020 7pm

<i> Format: </i> `event description /at HH:mm YYYY-MM-DD (#tag)`

<i>Example of usage: </i> 

`event project meeting /at 19:00 2020-12-25`

<i>Expected outcome: </i>

`Wow, another task. Added. You sure you can finish them all?`

  `[E] [✘] project meeting (at: 19:00, Dec 25 2020)`
  
  `Now you have a grand total of 1 tasks!`  


### `delete` - deletes task
Deletes an existing task with specified index in the list. The index must be a positive number within range.

<i> Format: </i> `delete task_index`

<i>Example of usage: </i> 

`delete 2`

<i>Expected outcome: </i>

`Good good... Okay removed! Looks more apt for a lazy ass like you.`

  `[E] [✘] project meeting (at: 19:00, Dec 25 2020)` 

### `find` - finds tasks
Find a list of tasks that have a given keyword. The search is case sensitive. 

<i> Format: </i> `find keyword`

<i>Example of usage: </i> 

`find homework`

<i>Expected outcome: </i>

`Found them. But at what cost...`

  `1. [D] [✘] CS2030 homework (at: Dec 22 2020)` 
  
### `#` - finds tasks with specific tag
Find a list of tasks that are tagged with a given keyword. The search is case sensitive. 

<i> Format: </i> `#keyword`

<i>Example of usage: </i> 

`#school`

<i>Expected outcome: </i>

`These are the tasks with the tag:`

  `1. [D] [✘] CS2030 homework (at: Dec 22 2020)`   
  
### `done` - marks a task as done
Marks an existing task with specified index in the list as done. The index must be a positive number within range.

<i> Format: </i> `done (task index)`

<i>Example of usage: </i> 

`done 2`

<i>Expected outcome: </i>

`Wah finally. Wondering how long more I need to wait...`

  `[E] [✓] project meeting (at: 19:00, Dec 25 2020)`   
  
### `list` - views all tasks
Views all tasks added.

<i> Format: </i> `list`

<i>Example of usage: </i> 

`list`

<i>Expected outcome: </i>

`Checking the whole list doesn't make you finish anything faster...`

  `1. [E] [✓] project meeting (at: 19:00, Dec 25 2020)`
  
  `2. [T] [✘] visit new theme park`
  
  `3. [D] [✓] CS2030 homework (by: Dec 22 2020)`
  
### `help` - views all commands
Views all possible commands.

<i> Format: </i> `help`

<i>Example of usage: </i> 

`help`

<i>Expected outcome: </i>

`todo [description] - add a todo task`

`deadline [description] /by [YYYY-MM-DD] - add a deadline task`

`event [description] /at [YYYY-MM-DD HH:mm] - add an event task`

`list - view all current tasks`

`done [task number] - mark chosen task as done`

`find [keyword] - find all tasks with specified keyword`

`#[task number] - find all tasks with specified tag`

  
