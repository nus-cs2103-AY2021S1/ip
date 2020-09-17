# Clippy User Guide
Clippy is a desktop app for managing tasks, optimized for use via a Command Line Interface (CLI). You can keep track of 
your day-to-day tasks, search for specific tasks by keywords and update details of tasks. If you can type fast, Clippy 
can help you keep track of your tasks faster than traditional GUI apps.

## Table of Content
1. About this document
2. Quick start
3. Features
4. FAQ
5. Command Summary

## About this document

## Quick start

## Features 


### Viewing help: `help`
Displays a message explaining how to access the user guide.

#### Format

`help`


### Adding a task: `todo`, `deadline`, `event`
Adds a task for Clippy to keep track of.

### Adding a todo task: `todo`
Adds a todo task for Clippy to keep track of. You should add a task as a todo when the task does not have a date/time attached to it.

#### Format

`todo <task description>`

#### Usage
Example of usage:
* `todo exercise at the gym`
* `todo visit the theme park`

Expected outcome:

`Got it. I've added this task:` <br>
`[T][X] exercise at the gym` <br>
`Now you have 1 task in the list` <br>

`Got it. I've added this task:` <br>
`[T][X] visit the theme park` <br>
`Now you have 2 tasks in the list` <br>

### Adding a deadline task: `deadline`
Adds a deadline task for Clippy to keep track of. You should add a task as a deadline when the task needs to be done 
before a specific date.
#### Format

`deadline <task description> /by <YYYY-MM-DD>`
NOTE: You have to provide the date in the exact `YYYY-MM-DD` format.

#### Usage
Example of usage:
* `deadline <sign up for hackathon> /by <2020-09-30>`

Expected outcome:

`Got it. I've added this task:` <br>
`[T][X] exercise at the gym` <br>
`Now you have 1 task in the list` <br>

### Adding an event task: `event`
Adds an event task for Clippy to keep track of. You should add a task as an event when the task occurs 
at a specific date/time period.

#### Format

`event <task description> /at <date/time period>`
NOTE: You do not have to follow any format for `<date/time period>`.
#### Usage
Example of usage:
* `event java workshop /at Tuesday 6-7pm`

Expected outcome:

`Got it. I've added this task:` <br>
`[T][X] exercise at the gym` <br>
`Now you have 1 task in the list` <br>

### Listing all tasks: `list`
Shows a list of all tasks being managed by Clippy.

#### Format

`list`

### Adding an event task: `event`
Adds an event task for Clippy to keep track of. You should add a task as an event when the task occurs 
at a specific date/time period.

#### Format

`event <task description> /at <date/time period>`
NOTE: You do not have to follow any format for `<date/time period>`.
#### Usage
Example of usage:
* `event java workshop /at Tuesday 6-7pm`

Expected outcome:

`Got it. I've added this task:` <br>
`[T][X] exercise at the gym` <br>
`Now you have 1 task in the list` <br>







## Command Summary
