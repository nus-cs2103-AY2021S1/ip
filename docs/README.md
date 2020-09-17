# User Guide

## Features 

### Add tasks 
Add tasks such as todo task, task with a deadline, an event, or a task with a fixed duration.

### List all task
List all task with its status

### Find tasks with keywords

### Delete specified task

### Mark specified task as done

## Usage

### `clear` - Clears screen

Example of usage: 

`clear`

Expected outcome:

`Previous commands and outputs are cleared`

### `todo` - Creates a todo task

Example of usage: 

`todo take out the trash`

Expected outcome:

`Task of type todo created with description: take out the trash`

### `event` - Creates an event task

Example of usage: 

`event take out the trash /at 12`

Expected outcome:

`Task of type event created with description: take out the trash at 12 o'clock`

### `deadline` - Creates a todo task

Example of usage: 

`deadline take out the trash /by 12`

Expected outcome:

`Task of type deadline created with description: take out the trash by 12 o'clock`

### `fixedtask` - Creates a fixed duration task

Example of usage: 

`fixedtask take out the trash /for 2`

Expected outcome:

`Task of type fixed duration created with description: take out the trash for 2 hours`

### `list` - List all tasks

Example of usage: 

`list`

Expected outcome:

`List all previously created tasks`

### `done` - Marks task as done

Example of usage:

`done 1`

Expected outcome:

`The first task in the list is marked as done`

### `delete` - Delete task

Example of usage: 

`delete 1`

Expected outcome:

`Deletes the first task`

### `find` - Delete task

Example of usage: 

`find trash`

Expected outcome:

`List all task with keyword: trash in the description`

### `hello` - Display hello message

Example of usage: 

`hello`

Expected outcome:

`Welcome the user`

### `bye` - End the program

Example of usage: 

`bye`

Expected outcome:

`The program ends`