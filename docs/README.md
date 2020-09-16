# User Guide
Duke is bored, and will assist you in organising your tasks!

Duke is a desktop task managing app, and is used via a Command Line Interface (CLI).

## Features 

### Add a new ToDo - `todo`
Format: `todo DESCRIPTION`

Adds a new ToDo task to the list.

Example of usage:

`todo wash clothes`

Expected outcome:

`Alright! I've added this task:`  
`[T][✘] wash clothes`  
`Now you have 1 task(s) in your list.`

### Add a new Deadline - `deadline`
Format: `deadline DESCRIPTION /by DATE [TIME]`

Adds a new Deadline task to the list. This task requires minimally a date, and optionally
a time. Check the appendix for the accepted date and time formats.

Example of usage:

`deadline homework /by 20-09-2020 6pm`

Expected outcome:

`Alright! I've added this task:`  
`[D][✘] homework (by: 20 Sep 2020, 18:00)`  
`Now you have 2 task(s) in your list.`

### Add a new Event - `event`
Format: `event DESCRIPTION /at DATE [TIME]`

Adds a new Event task to the list. This task requires minimally a date, and optionally
a time. Check the appendix for the accepted date and time formats.

Example of usage:

`event company retreat /at 31/Sep/2020`

Expected outcome:

`Alright! I've added this task:`  
`[E][✘] company retreat (at: 30 Sep 2020)`  
`Now you have 3 task(s) in your list.`

### List tasks - `list`
Format: `list`

Prints out all tasks in the list, including the tags associated to the tasks.

Example of usage:

`list`

Expected outcome:

`1. [T][✘] wash clothes`  
`   Tags: No tags yet`  
`2. [D][✘] homework (by: 20 Sep 2020, 18:00)`  
`   Tags: No tags yet`  
`3. [E][✘] company retreat (at: 30 Sep 2020)`  
`   Tags: No tags yet`

### Tag a task - `tag`
Format: `tag TASK_INDEX TAGS...`

Adds provided tags (minimally 1) to a task at the specified `TASK_INDEX`. The index refers to the index
number shown in the displayed task list. 

Example of usage:

`tag 2 school work difficult`

Expected outcome:

`Alright, I have added the tags!`

### Remove tags - `rmtag`
Format: `rmtag TASK_INDEX TAGS_TO_REMOVE...`

Removes provided tags (minimally 1) from a task at the specified `TASK_INDEX`, if they exist. The index refers to the index
number shown in the displayed task list. 

Example of usage:

`rmtag 2 difficult fun`

Expected outcome:

`Done!`  
`One or more tags provided do not exist in the task, so I skipped them.`

### Mark task as done - `done`
Format: `done TASK_INDEX`

Marks the task at the specified `TASK_INDEX` as done. The index refers to the index
number shown in the displayed task list. 

Example of usage:

`done 1`

Expected outcome:

`Nice! I've marked this task as done:`  
`[T][✓] wash clothes`

### Delete a task - `delete`
Format: `delete TASK_INDEX`

Deletes the task at the specified `TASK_INDEX` as done. The index refers to the index
number shown in the displayed task list. 

Example of usage:

`delete 3`

Expected outcome:

`Noted. I've removed this task:`  
`[E][✘] company retreat (at: 30 Sep 2020)`

### Find task(s) using keyword- `find`
Format: `find KEYWORD`

Finds and prints tasks that contain the provided keyword in their description.

Example of usage:

`find clothes`

Expected outcome:

`Here's what I found:`  
`1. [T][✓] wash clothes`

### Find task(s) using keyword - `find`

Format: `find KEYWORD`

Finds and prints tasks that contain the provided keyword in their description.

Example of usage:

`find clothes`

Expected outcome:

`1. [T][✓] wash clothes`

### Exiting the program - `bye`
Format: `bye`

Exits the program. The program will close after a short delay.

### Saving the data
List data are saved in the hard disk automatically after any command that makes
any changes in the task list.

## Appendix
### Date and time formats
The accepted date and time formats in this app are listed below:

#### Date:

| Format | Example |
| ------- | -------- |
| yyyy/M/d | 2020/3/21 |
| d/M/yyyy | 3/12/2020 |
| yyyy/MMM/d | 2020/Mar/21 |
| d/MMM/yyyy | 21/Mar/2020 |

The slashes ( / ) can be replaced with hyphens ( - ).

#### Time:

| Format | Example |
| ------ | ------- |
| HH[:]mm | 1800, 18:00 |
| h:mma | 7:20pm |
| h.mma | 7.20am |
| ha | 10pm |
