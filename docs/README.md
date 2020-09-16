# User Guide
## Description
Chubs is a customised chat bot that keeps track of your tasks under the guise of eating them. Chubs operates through
 Command Line Interface (CLI) while having a Graphical User Interface (GUI).

## Installation
1. Make sure you have java versions 11 or above installed.
2. Download the jar file.
3. Run it.

## Usage

### `list` - lists all current tasks

Lists all task in current task list.

Example of usage: 

`list`

Expected outcome:

`1. [(type of task)][(task completeness)] (task description)`

`2. [(type of task)][(task completeness)] (task description)`

### `stats` - displays statistics

Displays statistics concerning the number of tasks added, number of tasks deleted and number of tasks done from when
 the App is installed.

Example of usage: 

`stats`

Expected outcome:

`Number of things I've eaten: (number)`

`Number of things I've thrown up: (number)`

`Number of things I've digested: (number)`

### `todo {task number}` - adds a todo task

Adds a todo task to current task list

Example of usage: 

`todo chicken nuggets`

Expected outcome:

`the following has been eated:`

`[T][✗] chicken nuggets`

### `deadline {task description} \by {date}` - adds a deadline task

Adds a deadline task to current task list, to be done by the specified date.
Date input takes the format yyyy/mm/dd or yyyy-mm-dd.

Example of usage: 

`deadline strawberry yoghurt \by 2020-09-21`

Expected outcome:

`the following has been eated:`

`[D][✗] strawberry yoghurt to be digested by: Sep 21 2020`

### `event {task description} \a {date}` - adds an event task

Adds an event task to current task list, to be done at the specified date.
Date input takes the format yyyy/mm/dd or yyyy-mm-dd.

Example of usage: 

`event crocodile meat \at 2020-09-18`

Expected outcome:

`the following has been eated:`

`[E][✗] crocodile meat to be digested at: Sep 18 2020`

### `done {number}` - done task

Marks a task in current task list as done if task number supplied is in valid range.

Example of usage: 

`done 1`

Expected outcome:

`I've digested the following: `

`[T][✓] chicken nuggets`

### `delete {number}` - removes task

Deletes a task in current task list if task number supplied is in valid range.

Example of usage: 

`delete 1`

Expected outcome:

`The following has been removed:`

`[T][✓] chicken nuggets`

### `find {keyword}` - search for tasks

Finds tasks in current task list containing the input keyword.

Example of usage: 

`find chicken`

Expected outcome:

`[T][✓] chicken nuggets`

`[T][✓] kentucky fried chicken crispy drumlet with extra spice`


### `owo` - fun extra interaction with Chubs

Example of usage: 

`owo`

Expected outcome:

`uwu`

### `uwu` - fun extra interaction with Chubs

Example of usage: 

`uwu`

Expected outcome:

`owo`

### `exit` - exits the application

Example of usage: 

`exit`