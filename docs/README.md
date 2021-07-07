# User Guide

## Features 

## General

### `clear` - clear all data

Example of usage: 

`clear`

Example outcome:

nothing

## Task List

### `tasks` - View list of all tasks

Example of usage: 

`tasks`

Example outcome:

`✰ 1. [T] [✓] read the bible ✰`
`✰ 2. [D] [✗] 2103T homework (by: 25 Aug 2020 11.59pm) ✰`

### `done` - Complete a task

Complete a certain task in the task list.

Example of usage: 

`done <index>`

Example outcome:

`✰ congrats on finishing your task :) it's marked as done:`
    `[D] [✓] 2103T homework (by: 25 Aug 2020 11.59pm) ✰`

### `delete` - Delete a task

Delete a certain task in the task list.

Example of usage: 

`delete <index>`

Example outcome:

`✰ i've removed the following task from the list:`
    `[D] [✓] 2103T homework (by: 25 Aug 2020 11.59pm)`
`now you have 2 items in your tasklist. ✰`

### `todo`,`deadline`,`event` - Add a new task

Example of usage: 

`todo <task name>`
`deadline <task name> <task due date>`
`event <task name> <task date>`

Expected outcome:

`✰ i've added this task for you: `
    `[D] [✗] 2103T homework (by: 25 Aug 2020 11.59pm)`
`now you have 2 items in your tasklist. ✰`

### `find` - Find tasks

Search for tasks in the list

Example of usage: 

`find <keyword>`

Expected outcome:

`✰ 1. [T] [✓] read the bible ✰`


## Notes

### `notes` - View list of all notes

Example of usage: 

`notes`

Example outcome:

`✰ 1. book ideas ✰`
`✰ 2. hmm ✰`

### `view` - View a note

View a certain task in the notes list.

Example of usage: 

`view <index>`

Example outcome:

`✰ a book about a little man ✰`

### `new` - Add a new note

Add a new note and starting writing to it.

Example of usage: 

`new <note name>`

Expected outcome:

`add another line or type 'complete' to save your note`

### `complete` - Finish writing note

Example of usage: 

`complete`

Expected outcome:

`your note has been saved!`
