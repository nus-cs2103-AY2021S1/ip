# User Guide for Ultron

## Features 

### Add tasks
Add different types of tasks to your ultron

### Find tasks
Search for items in the todo list to find what you want

### Mark tasks as done
Mark the tasks which are inserted as done

### Look up how to use the command
Look up how to use any of the commands

## Usage

### `todo` - Add a todo to the list

This adds a todo task to the list of tasks

Example of usage: 

`todo return books`

Expected outcome:

`Can't keep track of '[T][✘] return books' yourself?`
<br>
`Now you have 3 burdens`

### `deadline` - Adds a deadline task to the list

This adds a deadline with a date or string into the list

Example of usage: 

`deadline return books /by sunday`

Expected outcome:

`Can't keep track of '[D][✘] return books (by:Sunday)' yourself?`
<br>
`Now you have 3 burdens`

### `event` - Describe action

Add an event with an event date to the list

Example of usage: 

`event school /at Sunday`

Expected outcome:

`Can't keep track of '[E][✘] school (at: Sunday)' yourself?`
<br>
`Now you have 3 burdens`

### `bye` - Describe action

Exits the bot

Example of usage: 

`bye`

Expected outcome:

The application will close

### `list` - Describe action

List all tasks added

Example of usage: 

`list`

Expected outcome:

`Heh, you cant even remember what you had`
<br>
`1. [T][✓] 1`
<br>
`2. [D][✘] return books (by: sunday)`

### `help` - Shows the list of commands

Shows all the commands that you can use

Example of usage:

`help`

Expected outcome:

`Heh I guess I could help an insect like you:`
<br>
`- help                      : Get help for the commands`
<br>
`- todo (name)               : Adds a todo to the list`
<br>
`- event (name) /at (date)   : Adds an event at date`
<br>
`- deadline (name) /by (date): Adds a deadline which expires by date`
<br>
`- delete (number)           : Removes a todo from the list`
<br>
`- find (keyword(s))           : Finds tasks with the keyword(s)`

### `delete` - Delete a task from the list

Deletes a task from the list

Example of usage:

`delete 1`

Expected outcome:

`What are you doing removing this?!?!`
<br>
`[T][✓] return books`
<br>
`Now you have 3 burdens`

### `find` - Find tasks containing a specific keyword

Find tasks with a specific key word

Example of usage:

`find books`

Expected outcome:

`Why do you always bothering me?`
<br>
`1. [T][✘] return books`


## Acknowledgements
### 3rd party Libraries
- Gson: For serializing java classes
- JUnits: For unit testing
- JavaFx: For graphics used for GUI