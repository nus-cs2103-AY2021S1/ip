# Duke User Guide
This is a regular Duke (Todo list app) with only 1 feature (C-MassOps).
Basic Ui only.
## Features 

### Mass operations
Able to remove multiple tasks at once, and set multiple tasks to done.

## Usage

### `list` - list all tasks

Shows a list of all tasks.

Example of usage: 

`list`

Expected outcome:<br />
`1. [D] [✓] Buy eggs (by: 7pm)` <br />
`2. [T] [✗] Sell eggs`

### `todo [description]` - Add a basic task
Add a task.

Example of usage: 

`todo buy some eggs`

Expected outcome:<br />
`Got it, I've added this task:` <br />
`   [T] [✗] buy some eggs`

### `event [description] /at [YYYY/MM/DD]` - Add a task with a time
Add a event.

Example of usage: 

`event buy some bananas /at 2020/01/01`

Expected outcome:<br />
`Got it, I've added this task:` <br />
`   [E] [✗] buy some bananas (at: Jan 1 2020)`


### `deadline [description] /at [YYYY/MM/DD]` - Add a task with a deadline
Add a deadline.

Example of usage: 

`deadline buy some ham /by 2020/02/02`

Expected outcome:<br />
`Got it, I've added this task:` <br />
`   [D] [✗] buy some ham (at: Feb 2 2020)`



### `remove [index]` - Remove task
Remove task at a particular index.

Example of usage: 

`remove 3`

Expected outcome:<br />
`Noted. I've removed the tasks:` <br />
`   [T] [✗] buy some eggs`<br />
`Now you have 2 tasks in the list` <br />


### `remove [index1] [index2]...` - Remove multiple tasks
Remove tasks at the indexes

Example of usage: 

`remove 3 4`

Expected outcome:<br />
`Noted. I've removed the tasks:` <br />
`   [E] [✗] buy some bananas (at: Jan 1 2020)`<br />
`   [D] [✗] buy some ham (by: Feb 2 2020)`<br />
`Now you have 2 tasks in the list` <br />


### `done [index]` - Set task as done
Set task at [index] as done

Example of usage: 

`done 1`

Expected outcome:<br />
`Nice! I've marked these tasks as done:` <br />
`   [T] [✓] buy some eggs`<br />


### `done [index1] [index2]` - Set multiple tasks as done
Set task at [index1],[index2]... ,as done

Example of usage: 

`done 1 2`

Expected outcome:<br />
`Nice! I've marked these tasks as done:` <br />
`   [T] [✓] buy some eggs`<br />
`   [E] [✓] buy some ham (at: Jan 1 2020)`<br />

### `find [search string]` - find all tasks 

Shows a list of all tasks with the substring [search string]

Example of usage: 

`find eggs e`

Expected outcome:<br />
`1. [D] [✓] Buy eggs extract (by: 7pm)` <br />
`2. [T] [✗] Sell eggs egger`

### `bye` - close the program
Exit the program.

Example of usage: 

`bye`

Expected outcome:<br />
Program exits.



