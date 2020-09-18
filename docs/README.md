# Yan's Greg User Guide

## Features 

###  View your list of tasks
Greg allows you to view your current list of tasks

### Mark tasks as done
Greg allows you to mark any item on the list as done

### Add a Todo Task
Greg allows you to add Todo tasks, so you won't forget it!

### Add a Deadline Task
Have an upcoming deadline? Greg allows you to add the deadline
task, so you won't forget it!

### Add an Event Task
How about an event? Greg allows you to add any Event tasks
, so that you won't forget about it!

### Delete current tasks
Don't require the tasks anymore? Greg allows you to delete
the tasks!

### Searching for tasks
Having trouble sieving through your long list of tasks?
Greg allows you to search for a particular tasks!

### Undo your previous command
Oops! Added/deleted something wrong? Fret not! Greg
allows you to undo your most recent command.

## Usage

### `todo` - Create a Todo Task

Adds a new Todo Task to the list.

Format: `todo {todo description}`

Example of usage: 

`todo eat lunch`

Expected outcome:

![Image of Todo Task](images\todo.png)

### `deadline` - Create a Deadline Task

Adds a new Deadline Task to the list.

Format: `deadline {deadline description} /by {deadline time}`

Example of usage: 

`deadline quiz1 /by 2020-09-20 2359`

Expected outcome:

![Image of Deadline Task](images\deadline.png)

### `event` - Create as event Task

Adds a new Deadline Task to the list.

Format: `event {event description} /at {event time}`

Example of usage: 

`event training /at 2020-09-20 1000`

Expected outcome:

![Image of Event Task](images\event.png)

### `done` - Mark a task as done

Marks a task of your choice as done

Format: `done {number position of task}`

Example of usage: 

`done 4`

Expected outcome:

![Image of Done](images\done.png)

### `delete` - Deletes tasks from the list

Delete tasks of your choice

Format: `delete {number} or delete {number-number}`

Example of usage: 

`delete 3`

Expected outcome:

![Image of singleDelete](images\singleDelete.png)

Examples of usage: 

`delete 1-2`

Expected outcome:

![Image of singleDelete](images\massDelete.png)

### `list` - show your Current list of Tasks

View all your added Task items

Format: `list`

Example of usage: 

`list`

Expected outcome:

![Image of Done](images\list.png)

### `find` - search for a specific Task

Finds all relevant Tasks that correspond to a keyword of choice

Format: `find {keyword}`

Example of usage: 

`find event`

Expected outcome:

![Image of Done](images\find.png)

### `undo` - undo your previous command

Undo your most recent command

Format: `undo`

Example of usage: 

`undo`

Expected outcome:

![Image of Done](images\undo.png)

### `bye` - exits the GregBot

Undo your most recent command

Format: `bye`

Example of usage: 

`bye`

Expected outcome:

Exits the GregBot.











