# User Guide

## Features 

### `todo`
Adds todo task to your tasks

## Usage

### `todo {description}` - Adding todo task

Adds todo task

Example of usage: 

`todo read book`

Expected outcome:

`outcome`
  
### `deadline`
Adds deadline task

## Usage

### `deadline {description} /by {YYYY-MM-DD}` - Adding deadline task

Adds deadline task into the list.

Example of usage: 

`deadline essay /by 2020-01-01`

Expected outcome:

`outcome`

### `event`
Adds event tasks

## Usage

### `event {description} /at {YYYY-MM-DD}` - Adding event task

Adds event task into the list.

Example of usage: 

`event conference /at 2019-12-30`

Expected outcome:

`outcome`

### Deleting task: `delete`
delete the task

## Usage

### `delete {index}` - listing all tasks

Lists out all tasks in the list

Example of usage: 

`delete 1`

Expected outcome:

`outcome`

### `done`
Marks the task done

## Usage

### `done {index}` - Marking task done

Marks the task at the given index done

Example of usage: 

`done 1`

Expected outcome:

`outcome`

### `list`
Shows all saved taskd

## Usage

### `list` - Listing all tasks

Lists out all tasks in the list

Example of usage: 

`list`

Expected outcome:

`outcome`

### `undo`
Undo for command(**todo**, **deadline**, **event**, **delete**, **done**)

## Usage

### `undo` - Undo

undo the previous command

Example of usage: 

`undo`

Expected outcome:

`outcome`

### : `find`
Finds tasks from the list

## Usage

### `find {keyword}` - Searching tasks

List out all the tasks contain the keyword given.

Example of usage: 

`find read`

Expected outcome:

`outcome`

### : `Exit`
Close the application

## Usage

### `bye` - 

Disable user input.

Example of usage: 

`bye`

Expected outcome:

`outcome`

## Command Summary
Action|Format
------|--------------
todo| `todo {description}`
deadline| `deadline {description} /by {YYYY-MM-DD}`
event| `event {description} /at {YYYY-MM-DD}`
list| `list`
delete| `delete {index}`
done| `done {index}`
undo| `undo`
find| `find {keyword}`

