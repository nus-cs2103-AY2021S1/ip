# User Guide
Duke is a desktop app for managing tasks, optimized for use via a Command Line Interface (CLI) while still having the 
benefits of a Graphical User Interface (GUI). If you can type fast, Duke can get your tasks management done faster than 
traditional GUI apps.

## Features 

### Feature 1 
Description of feature.

## Usage

### `todo` - Adding todo task

Adds a todo task to the list.

Example of usage: 

`todo [description]`

Expected outcome:

`Got it. I've added this task: `<br/>
`[T][✘] todo 1`<br/>
`Now you have 9 tasks in the list.`

### `deadline` - Adding deadline task

Adds a deadline task with a Date to the list.

Example of usage: 

`deadline [description] /by [yyyy-MM-dd]`

Expected outcome:

`Got it. I've added this task: `<br/>
`[D][✘] deadline 1 (by: Sep 15 2020)`<br/>
`Now you have 8 tasks in the list.`

### `event` - Adding event task

Adds an event task with a DateTime to the list.

Example of usage: 

`event [description] /at [yyyy-MM-dd h:mm a]`

Expected outcome:

`Got it. I've added this task: `<br/>
`[E][✘] event 1 (at: Sep 15 2020 10:32 am)`<br/>
`Now you have 7 tasks in the list.`

### `done` - Marking a task as done

Marks a task in the list as done.

Example of usage: 

`done [index]`

Expected outcome:

`Nice! I've marked this task as done: `<br/>
`[T][✓] todo 1`

### `find` - Finding tasks

Finds tasks that contain any of the given keywords.

Example of usage: 

`find [keyword]`

Expected outcome:

`Here are the matching tasks in your list: `<br/>
`1. [T][✘] todo 1`<br/>
`2. [T][✘] todo 2`

### `delete` - Deleting a task

Remove a task from the list.

Example of usage: 

`delete [index]`

Expected outcome:

`Noted. I've removed this task: `<br/>
`[T][✘] todo 1`<br/>
`Now you have 4 tasks in the list.`

### `list` - Listing all tasks

Shows a list of all tasks in Duke.

Example of usage: 

`list`

Expected outcome:

`Here are the tasks in your list: `<br/>
`1. [T][✘] todo 1`<br/>
`2. [D][✘] deadline 1 (by: Sep 20 2020)`<br/>
`3. [E][✘] event 1 (at: Sep 21 2020 7:00 pm)`<br/>
`4. [T][✓] todo done`



### `schedule` - Listing all tasks by Date

Shows a list of all tasks that has the specific date in Duke.

Example of usage: 

`schedule [yyyy-MM-dd]`

Expected outcome:

`Here are the schedule on Sep 20 2020: `<br/>
`1. [D][✘] exam (by: Sep 20 2020)`

### `bye` - Exiting the program

Exits the program

Example of usage: 

`bye`
