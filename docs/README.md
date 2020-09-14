# Guide to HAL9000

## Features

### Record and remember your ToDo list
HAL9000 categorizes tasks into 3 types:
1. **To do**: General task.

1. **Deadline**: Task with a specific deadline.
    
1. **Event**: Task with a time or place.
    
### Other features
1. **list**: view all your tasks.

1. **done**: mark a task as done.

1. **delete**: delete a task from your list permanently.

1. **find**: find a task with a keyword.

## Usage
### `todo` - Save your task as a To Do task.
To save task as todo task, specify task name:
 
`todo [task name]`
    
Example of usage:

`todo visit the moon`
    
Expected outcome:

    Got it. I've added this task:
      [T][✗] visit the moon
    Now you have 1 tasks in the list.

### `deadline` - Save your task as a Deadline task

To save task as a Deadline task, specify task name followed by its deadline. 

`deadline [task name] /by [YYYY-MM-DD] [HH:MM]`

Example of usage: 

`deadline collect moon rock samples /by 2020-09-21 17:00`

Expected outcome:

    Got it. I've added this task:
       [D][✗] collect moon rock samples (by: 2020-09-21 17:00)
     Now you have 3 tasks in the list.

### `event` - Save your task as an Event task

To save task as a Deadline task, specify task name followed by \
details about the event, like time or location. 

`event [task name] /at [details]`

Example of usage: 

`event NASA rocket launch /at 15:00 in USA`

Expected outcome:

    Got it. I've added this task:
      [E][✗] NASA rocket launch (at: 15:00 in USA)
    Now you have 4 tasks in the list.

### `list` - Displays all your tasks

Input `list` to see every task you have in your list \
along with their status and index number, excluding those you have deleted.

Example of usage: 

`list`

Expected outcome:

    Here are the tasks in your list:
    1.[D][✗] buy stuff (by: 2019-02-19 19:00)
    2.[T][✗] visit the moon
    3.[D][✗] collect moon rock samples (by: 2020-09-21 17:00)
    4.[E][✗] NASA rocket launch (at: 15:00 in USA)

### `done` - Mark a task as done

`done` is used when you are done with a specific task. \
Status of the task will change from `[✗]` to `[✓]`

Example of usage: 

`done 1`

Expected outcome:

    Nice! I've marked this task as done:
    [✓] buy stuff

### `delete` - Delete a task from your list

`delete` will remove the specified task from your list permanently. This cannot be undone.

Example of usage: 

`delete 2`

Expected outcome:

    Noted. I've removed this task:
      [T][✗] visit the moon
    Now you have 3 tasks in the list.


### `find` - Find task using keyword

`find` is used when you want to search a task by a specific keyword.\
HAL9000 will display all tasks containing the keyword.

Example of usage: 

`find NASA`

Expected outcome:

    Here are the matching tasks in your list:
    1.[E][✗] NASA rocket launch (at: 15:00 in USA)
    
Thank you for using

       __ _____   __  ___  ___  ___  ___
      / // / _ | / / / _ \/ _ \/ _ \/ _ \
     / _  / __ |/ /__\_, / // / // / // /
    /_//_/_/ |_/____/___/\___/\___/\___/
    
Hope to see you again soon!
