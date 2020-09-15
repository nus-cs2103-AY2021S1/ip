# User Guide

## Types of Tasks
1. Todo: Tasks that do not have date/time associated with it.
2. Deadline: Tasks that have to be completed by a specific date/time.
3. Event: Tasks that occur at a specific date/time.
4. Weekly: Tasks that occur every week on a specific day/time.

## Features 
1. Add a todo task
2. Add a deadline task
3. Add an event task
4. Add a weekly recurring task
5. View list of tasks
6. Filter tasks by keyword
7. Filter tasks by date
8. Mark tasks as done
9. Delete tasks
10. View list of commands
11. Exit application

## Usage

### `todo` - Add a todo task

Adds a new todo task to current list of tasks.

Format: `todo <task>`

Example of usage: `todo task one`

Expected outcome:
```
Got it. I've added this task:
[T][x]task one
Now you have 1 tasks in your list.
```


### `deadline` - Add a deadline task

Adds a new deadline task to current list of tasks.

Format: 'deadline <task> /by <dd/mm/yyyy> <hhmm>'

Example of usage: `deadline task two /by 20/10/2020 1230`

Expected outcome:

'''
Got it. I've added this task: 
[D][x] task two (by: Oct 20 2020 12:30)
Now you have 2 tasks in the list.
'''


### `event` - Add an event task

Adds a new event task to current list of tasks.

Format: 'event <task> /at <dd/mm/yyyy> <hhmm>'

Example of usage: `event task three /at 30/09/2020 1825`

Expected outcome:

'''
Got it. I've added this task: 
[E][x] task three (at: Sep 30 2020 18:25)
Now you have 3 tasks in the list.
'''


### `weekly` - Add a weekly recurring task

Adds a new weekly task to current list of tasks. Only the next upcoming instance of the recurring task will be shown.

Format: 'weekly <task> /every <day> <hhmm>'

Example of usage: `weekly task four /every mon 1315`

Expected outcome:

'''
Got it. I've added this task: 
[Weekly] task four (at: Sep 21 2020 13:15)
Now you have 4 tasks in the list.
'''


### `list` - View list of tasks

Displays the current list of tasks.

Format: 'list'

Example of usage: `list`

Expected outcome:

'''
Here are the tasks in your list: 
1. [T][x] task one
2. [D][x] task two (by: Oct 20 2020 12:30)
3. [E][x] task three (at: Sep 30 2020 18:25)
4. [Weekly] task four (at: Sep 21 2020 13:15)
'''


### `find` - Filter tasks by keyword

Displays tasks that contain the filter keyword.

Format: 'find <keyword>'

Example of usage: `find one`

Expected outcome:

'''
Here are your tasks that matches your search: 
1. [T][x] task one
'''


### `filter` - Filter tasks by date

Displays tasks that are due by/occur on a specific date.

Format: 'filter <dd/mm/yyyy>'

Example of usage: `filter 30/09/2020`

Expected outcome:

'''
Here are your tasks due on Sep 30 2020: 
3. [E][x] task three (at: Sep 30 2020 18:25)
'''


### `done` - Mark tasks as done

Marks specific task as done. If task is already completed, nothing is changed.

Format: 'done <task id>'

Example of usage: `done 2`

Expected outcome:

'''
Congrats! I've marked this task as done: 
[D][✓] task two (by: Oct 20 2020 12:30)
'''


### `delete` - Delete tasks

Delete specific task from list.

Format: 'delete <task id>'

Example of usage: `done 3`

Expected outcome:

'''
Noted! I've deleted this duke.task: 
[E][x] task three (at: Sep 30 2020 18:25)
Now you have 3 tasks in the list.
'''



### `help` - View list of command

Displays list of all duke commands.

Format: 'help'

Example of usage: `help`



### `bye` - Exit application

Display goodbye message and closes the duke application.

Format: 'bye'

Example of usage: `bye`

Expected outcome:

'''
Goodbye! See you soon!
'''
