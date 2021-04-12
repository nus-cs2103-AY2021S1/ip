# User Guide

Duke is (yet another) task management application. It is based off a project template.

## Features 

### Create
Create various types of tasks, based on what you need to track.

##### Todo
Record a basic task. It represents a task you need to complete, with no details on time.

##### Event
Record events with this. It represents an event you need to attend, with a start date and an end date.

##### Deadline
Record pressing deadlines with this. It represents a todo, but you can give it a deadline for tracking purposes.

### Read

Retrieve what you have previously recorded.

### Check

Mark those tasks that you have finished.

### Delete

Remove tasks that have cluttered your list.

### Find

Locate tasks based on their description.

## Usage

### `todo [description]` - Create a todo task

Create a todo application.

Aliases: `t`

Example of usage:

`todo Use Duke!`

### `event [description] /from [DD/MM/YYYY HH:mm] /till [DD/MM/YYYY HH:mm]` - Create an event

Create an event. You have to follow the given format.

Aliases: `e`

Example of usage:

`deadline Use Duke! /from 01/01/2020 08:00 /till 01/01/2020 10:00`

### `deadline [description] /by [DD/MM/YYYY HH:mm]` - Create a deadline

Create a deadline. You have to follow the given format.

Aliases: `d`

Example of usage:

`deadline Use Duke! /by 01/01/2020 10:00`

### `list` - List all tasks

List all the tasks you currently have.

Aliases: `ls` or `l`

Example of usage:

`list`

### `check [index] [index]` - Check off one or more tasks

Indicate that one or more tasks have been done. 

The indexes are based off the list command. You have to provide at least one valid index. 

Aliases: `ck`

Example of usage:

`check 1 2`

Expected outcome:

![Check outcome](images/check-outcome.JPG)

### `remove [index] [index]` - Delete one or more tasks

Delete one or more tasks.

The indexes are based off the list command. You have to provide at least one valid index. 

Aliases: `rm`

Example of usage:

`remove 1 2`

Expected outcome:

![Remove outcome](images/remove-outcome.JPG)

### `find [search-term]` - Look for tasks with matching descriptions

Find tasks that contain the given search term.

Aliases: `f`

Example of usage:

`find Duke`

### `bye` - End Duke

Exit from duke. This disables the input.

Aliases: `exit` or `q`

Example of usage:

`bye`