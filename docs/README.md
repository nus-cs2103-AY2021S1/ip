# User Guide

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

## Features 

### Adding Todo Task: `todo`
Adds todo tasks into the list

Format: `todo {description}`

Example: todo read book
  
### Adding Deadline Task: `deadline`
Adds deadline tasks into the list

Format: `deadline {description} /by {YYYY-MM-DD}`

Example: deadline essay /by 2020-01-01

### Adding Event Task: `event`
Adds event tasks into the list

Format: `event {description} /at {YYYY-MM-DD}`

Example: event conference /at 2019-12-30

### Listing all tasks: `list`
Lists out all tasks

Format: `list`

### Deleting task: `delete`
delete the task

Format: `delete {index}`

Example: delete 1

### Marking task done: `done`
Marks the task done

Format: `done {index}`

Example: done 1

### Undo: `undo`
Undo for command(**todo**, **deadline**, **event**, **delete**, **done**) 

Format: `undo`

### Searching tasks: `find`
Finds tasks from the list

Format: `find {keyword}`

Example: find read

## Usage

### `Keyword` - Describe action

Describe action and its outcome.

Example of usage: 

`keyword (optional arguments)`

Expected outcome:

`outcome`
