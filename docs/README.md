# Duke User Guide

## Features 

### ToDo
Adds a ToDo task to your list.

#### Usage

### `todo [task description]`

Creates a ToDo task with specified description and adds 
it to your list.

Example of usage: 

`todo homework`

Expected outcome:

`Got it! Task added to your list.`<br/>
&nbsp;&nbsp;&nbsp;&nbsp;`[T] homework` <br/>
`Now you have x tasks in your list.`

### Deadline
Adds a Deadline task to your list.

#### Usage

### `deadline [task description] /by [date]`

Creates a Deadline task with specified description and time 
and adds it to your list.

Example of usage: 

`deadline assignment /by 12/12/2020 1200`

Expected outcome:

`Got it! Task added to your list.`<br/>
&nbsp;&nbsp;&nbsp;&nbsp;`[D] assignment (by: 12 Dec 2020 12:00pm)` <br/>
`Now you have x tasks in your list.`

Note:
Valid date formats : `dd-mm-yyyy`, `dd-mm-yyyy hhhh`,
`dd/mm/yyyy`, `dd/mm/yyyy hhhh`.
Any other formats will be read as String.