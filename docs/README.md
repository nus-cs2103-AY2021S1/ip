# User Guide

Duke is an app for that helps manage a checklist of items via command line Interface 
(CLI) with the benefits of a GUI(Graphical User Interface).

## Features 

### List Tasks
To list all your events in the checklist.

command: list

### Add Task
To add a task to your checklist.

command: todo `description of task`

Example of usage: 

`todo get milk from store`

Expected outcome:

Task "get milk from store" gets added to the list.

### Add Event 
To add an event to your checklist. Events occur at a specific time.

command: event `description of event` /at `specified time`

Example of usage: 

`event attend meeting /at 3pm wednesday`

Expected outcome:

Task "attend meeting" gets added to the list with its own unique timing.

### Add deadline 
To add a deadline to your checklist. Deadlines have to be completed by a specific time.

command: deadline `description of deadline` /by `YYYY-MM-DD` `HH:HH`

Example of usage:
`deadline complete homework /by 2020-12-11 18:00`

Expected outcome:
The deadline gets added to the list.

`[D][x] complete homework (by DEC 11 2020 18:00)`

### Delete
Removes a task from the list.

command: delete `task number`

Example of usage:
`delete 1`

Expected outcome:
First task gets deleted from list.

### Done
Marks a task as done.

command: done `task number`

Example of usage: 
`done 1`

Expected outcome:
First task gets marked as done

### Tag 

Give a task a tag.

command: tag `task number` `description of tag`

Example of usage:
`tag 1 important`

Expected outcome:
Task 1 gets tagged as important

