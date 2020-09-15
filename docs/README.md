# User Guide

## What is Duke?

Duke is an imperfect task manager. It's not the most elegant nor the most efficient, but it gets the job done, sometimes.

## How to use?

Duke understands the list of commands below. Duke doesn't understand anything else (Yes, it's not very smart).

### Commands

`list` - List all tasks

`date <date>` - List all tasks on a date

 `todo <task_name>` - Adds a todo task
 `event `
 
 `deadline <task_name> /by <date>` - Add a deadline task to be completed by `<date>`
 
`event <task_name> /at <date>` - Add an event task that is scheduled for `<date>`

`done <task_index>` - Marks the task at the task index as done

`delete <task_index>` - Deletes the task at the task index 

`find <keywords>` - Finds all the tasks in the task list that has the keywords 

`tag <task_index> <tag_name>` - Adds a tag to the task specified

`bye` - Shuts down the program

#### How to input the variables?

`<date>`
- Input `date` with this format: `yyyy-mm-dd`
- Make sure date is within valid range

`<task_index>`
- Run `list` command
- The number associated with the target task is the `task_index`

`<keywords>`
- Input a string (can have spaces in between)

`<task_name>`
- Input a string (can have spaces in between)

`<tag_name>`
- Input a string (can have spaces in between)