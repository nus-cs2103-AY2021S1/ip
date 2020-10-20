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
&nbsp;&nbsp;&nbsp;&nbsp;`[T][X] homework` <br/>
`Now you have x tasks in your list.`

***

### Deadline
Adds a Deadline task to your list.

#### Usage

### `deadline [task description] /by [date]`

Creates a Deadline task with specified description and time 
and adds it to your list.

Example of usage: 

`deadline assignment /by 19/04/2020 2359`

Expected outcome:

`Got it! Task added to your list.`<br/>
&nbsp;&nbsp;&nbsp;&nbsp;`[D][X] assignment (by: 19 Apr 2020 11:59pm)` <br/>
`Now you have x tasks in your list.`

Note: <br/>
Valid date formats : `dd-mm-yyyy`, `dd-mm-yyyy hhhh`,
`dd/mm/yyyy`, `dd/mm/yyyy hhhh`.
Any other formats will be read as String.

***

### Event
Adds an Event task to your list.

#### Usage

### `event [task description] /at [date]`

Creates an Event task with specified description and time 
and adds it to your list.

Example of usage: 

`event concert /at 19/04/2020 1159`

Expected outcome:

`Got it! Task added to your list.`<br/>
&nbsp;&nbsp;&nbsp;&nbsp;`[E][X] concert (at: 19 Apr 2020 11:59am)` <br/>
`Now you have x tasks in your list.` <br/>

Note: <br/>
Valid date formats : `dd-mm-yyyy`, `dd-mm-yyyy hhhh`,
`dd/mm/yyyy`, `dd/mm/yyyy hhhh`.
Any other formats will be read as String.

***

### List
Shows a list of all your tasks.

#### Usage

### `list`

Lists all tasks.

Example of usage: 

`list`

Expected outcome:

`Here are your tasks:`<br/>
`1. [T][X] homework` <br/>
`2. [D][X] assignment (by: 19 Apr 2020 11:59pm)` <br/>
`3. [E][X] concert (at: 19 Apr 2020 11:59am)` <br/>

***

### Done
Marks task as completed with a [ / ].

#### Usage

### `done [index]`

Marks task at the specified index as done.

Example of usage: 

`done 1`

Expected outcome:

`Nice! I have marked this task as done:`<br/>
&nbsp;&nbsp;&nbsp;&nbsp;`[T][/] homework` <br/>

Note: <br/>
Index is as specified by `list` function.

***

### Delete
Removes specified task from list.

#### Usage

### `delete [index]`

Deletes task at the specified index.

Example of usage: 

`delete 1`

Expected outcome:

`Okay! I have removed this task:`<br/>
&nbsp;&nbsp;&nbsp;&nbsp;`[T] homework` <br/>
`Now you have x tasks in your list`  

Note: <br/>
Index is as specified by `list` function.

***

### Clear
Deletes task list.

#### Usage

### `clear`

Deletes entire task list.

Example of usage: 

`clear`

Expected outcome:

`Task list cleared!`<br/>

***

### Find
Finds task list using keyword/phrase.

#### Usage

### `find [keyword/phrase]`

Prints a sublist of tasks that contain the specified <br/>
keyword/phrase.

Example of usage: 

`find math`

Expected outcome:

`Here are your matching tasks:`<br/>
`1. [D][X] math homework (by: Sep 28 2020)` <br/>
`2. [D][X] math assignment (by: Sep 30 2020)` <br/>
`3. [E][X] math lecture (at: Sep 25 2020, 03:00pm)` <br/>

If unsuccessful find: <br/>
`Sorry! There are no tasks that match that description.`

***

### Edit
Edits a specified task with a new field.

#### Usage

### `edit 1 [/d or /t] [new field]`

Edits a task at the specified index with a new field. <br/>
Tag "/d" to edit description. <br/>
Tag "/t" to edit time.

Example of usage: 

`edit 1 /d math hw 1 section b`

Expected outcome:

`Okay! I have edited this task:`<br/>
&nbsp;&nbsp;&nbsp;&nbsp;`===> [D][X] math homework (by: Sep 28 2020)` <br/>
&nbsp;&nbsp;&nbsp;&nbsp;`<=== [D][X] math hw 1 section b (by: Sep 28 2020)` <br/>

Example of usage: 

`edit 1 /t 29/09/2020 2359`

Expected outcome:

`Okay! I have edited this task:`<br/>
&nbsp;&nbsp;&nbsp;&nbsp;`===> [D][X] math homework (by: Sep 28 2020)` <br/>
&nbsp;&nbsp;&nbsp;&nbsp;`<=== [D][X] math homework (by: Sep 29 2020, 11:59pm)` <br/>

***
