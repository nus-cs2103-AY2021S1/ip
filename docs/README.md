# User Guide

## Features 

### Todo Feature
Add a todo task to your list.

## Usage

### `todo <task name>`

Adds the todo to your list of tasks

Example of usage: 

`todo buy new shoes`

Expected outcome:

`[T][x] <task name>`

### Event Feature
Add an event to your list.

## Usage

### `event <event name> /at <DD-MM-YYYY>`

Adds the event to your list of tasks

Example of usage: 

`event party/at9-08-2020 23:59`

Expected outcome:

`[E][x] <event name> (at: 09 Sep 2020 11.59pm)`

### Deadline Feature
Add a deadline to your list.

## Usage

### `deadline <deadline name> /by <DD-MM-YYYY>` 

Adds the deadline to your list of tasks

Example of usage: 

`deadline homework/at9-08-2020 23:59`

Expected outcome:

`[D][x] <event name> (by: 09 Sep 2020 11.59pm)`

### Find Feature
Finds tasks with a specific keyword within your list.

## Usage

### `find <keyword>` - 

Displays a list of tasks containing the keyword.

Example of usage: 

`find meeting`

Expected outcome:

`1. [E][x] meeting for CS2103 (at: 14 Sep 2020 10.00pm)`
`2. [E][x] CCAmeeting (at: 20 Sep 2020 7.00am)`

### Date Feature
Finds tasks with a specific date within your list.

## Usage

### `date <DD-MM-YYYY>` - 

Displays a list of tasks containing the keyword.

Example of usage: 

`find meeting`

Expected outcome:

`1. [E][x] meeting for CS2103 (at: 14 Sep 2020 10.00pm)`
`2. [T][x] Group Project Task (at: 14 Sep 2020 7.00am)`

### Tag Feature
Tags your task with a specific tag.

## Usage

### `tag <index> <tag>` - 

Adds the tag to your task. You need to use the list feature to view the task indices.

Example of usage: 

`tag 2 urgent`

Expected outcome:

`1. [E][x] meeting for CS2103 (at: 14 Sep 2020 10.00pm)`
`2. [T][x] Group Project Task (at: 14 Sep 2020 7.00am) #urgent`

### Find Tag Feature
Display a list of tasks with the specified tag.

## Usage

### `findtag <tag>` - 

Display a list of tasks with the specified tag.

Example of usage: 

`findtag urgent`

Expected outcome:

`1. [E][x] meeting for CS2103 (at: 14 Sep 2020 10.00pm) #urgent #important`
`2. [T][x] Group Project Task (at: 14 Sep 2020 7.00am) #abc #urgent`

### Untag Feature
Untags all tasks (or a specified index of task) by removing the specified tag.

## Usage

### `untag <tag>` - 

Removes the tag from all tasks

Example of usage: 

`untag urgent`

Expected outcome:

`1. [E][x] meeting for CS2103 (at: 14 Sep 2020 10.00pm) #important`
`2. [T][x] Group Project Task (at: 14 Sep 2020 7.00am) #abc`

### `untag <index> <tag>` - 

Removes the tag from tasks at the specified index number. You need to use the list feature to view the task indices. 

Example of usage: 

`untag 2 urgent`

Expected outcome:

`1. [E][x] meeting for CS2103 (at: 14 Sep 2020 10.00pm) #urgent #important`
`2. [T][x] Group Project Task (at: 14 Sep 2020 7.00am) #abc`

### Delete Feature
Delete a specific task number.

## Usage

### `delete <index>` - 

Deletes the task at the specified index. You need to use the list feature to view the task indices.

Example of usage: 

`delete 1`

Expected outcome:

`1. [T][x] Group Project Task (at: 14 Sep 2020 7.00am) #abc #urgent`

### Done Feature
Marks a task as done.

## Usage

### `done <index>` - 

Marks the task at specific index as done. You need to use the list feature to view the task indices.

Example of usage: 

`done 1`

Expected outcome:

`1. [T][✔] Group Project Task (at: 14 Sep 2020 7.00am) #abc #urgent`
