# Duke User Guide

## Features
Duke is a Personal Assistant Chatbot that helps you keep track of tasks and gives you reminders, optimised for use via a Command Line Interface (CLI).

## Usage

### 1. Adding a todo: `todo`
Adds a todo to your list of tasks.

Format: `todo <DESCRIPTION>`

Example of usage: 

`todo return book`

Expected outcome:

`Added this task to your list:`

`[T][✘] return book`

`You now have 1 task(s) in the list.`

### 2. Adding a deadline task: `deadline`
Adds a deadline to your list of tasks.

Format: `deadline <DESCRIPTION> /by <YYYY-MM-DD>`

Example of usage: 

`deadline finish assignment /by 2020-10-20`

Expected outcome:

`Added this task to your list:`

`[D][✘] finish assignment (by: Oct 20 2020)`

`You now have 1 task(s) in the list.`

### 3. Adding an event task: `event`
Adds an event to your list of tasks.

Format: `event <DESCRIPTION> /at <YYYY-MM-DD>`

Example of usage: 

`event attend wedding /at 2020-10-19`

Expected outcome:

`Added this task to your list:`

`[E][✘] attend wedding (at: Oct 19 2020)`

`You now have 1 task(s) in the list.`

### 4. Listing all tasks: `list`
Shows your list of tasks.

Format: `list`

Example of usage: 

`list`

Expected outcome:

`1: [T][✘] return book`

`2. [D][✘] finish assignment (by: Oct 20 2020)`

`3. [E][✘] attend wedding (at: Oct 19 2020)`

### 5. Marking a task as done: `done`
Marks a task as done.

Format: `done <INDEX>`

Example of usage: 

`done 2`

Expected outcome:

`Nice! I've marked this task as done`

`[D][✓] finish assignment (by: Oct 20 2020)`

### 6. Finding tasks by keyword: `find`
Finds tasks whose description contain the keyword.

Format: `find <KEYWORD>`

Example of usage: 

`find book`

Expected outcome:

`Here are the matching tasks in your list:`

`1: [T][✘] return book`

### 8. Deleting a task: `delete`
Deletes the specified task.

Format: `delete <INDEX>`

Example of usage: 

`delete 2`

Expected outcome:

`Noted. Removed task:`

`[D][✓] finish assignment (by: Oct 20 2020)`

`You now have 2 task(s) in the list.`

### 9. Exiting the program: `bye`
Exits the program.

Format: `bye`

### Command Summary

Command | Format | Examples
------------ | ------------- | -------------
todo | `todo <DESCRIPTION>` | `todo return book`
deadline | `deadline <DESCRIPTION> /by <YYYY-MM-DD>` | `deadline finish assignment /by 2020-10-20`
event | `event <DESCRIPTION> /at <YYYY-MM-DD>` | `event attend wedding /at 2020-10-19`
list | `list` | 
done | `done <INDEX>` | `done 2`
find | `find <KEYWORD>` | `find book`
delete | `delete <INDEX>` | `delete 2`