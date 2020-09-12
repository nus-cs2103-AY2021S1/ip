# User Guide

## Features 

### Dynamic operations
Ability to add, delete, update as well as find tasks

## Usage

### `LIST` - LISTS ALL TASKS

Lists all the tasks inputted by the user.

Example of usage: 

`list`

Expected outcome:

`1. [T][Not Done] help me out`

`2. [D][Donezo] buy groceries (by: Jul 20 2020, 3:00pm)`

`3. [E][Not Done] party hard (at: Jul 20 2020, 3:00pm)` 



### `DONE [INT]` - MARKS A TASK AS DONE

Marks a task as done based on the number provided by the user

Example of usage:

`done 1`

Expected outcome:

`Nice! I've marked this task as done: 
[D][Donezo]update todos (by: 23/02/2020 21:22).
Now you have 7 tasks in the list` 


### `DELETE [INT]` - DELETE A TASK FROM THE LIST

Deletes the task found at the number provided by the user

Example of usage:

`delete 1`

Expected outcome:

`The event has been removed as per your request:
[D][Donezo]update todos (by: 23/02/2020 21:22).
Now you have 6 tasks to the list`


### `DEADLINE [STRING] /BY DD-MM-YYYY HH:MM `- CREATE A DEADLINE TASK

Creates and adds a deadline task at the given date and time

Example of usage:

`deadline get out in front /by  22/02/2020 23:45`

Expected outcome:

`[D][Not Done] get out in front (by: 22/02/2020 23:45) has been added to the list. 
You have 7 tasks left to do.`


### `EVENT [STRING] /AT DD-MM-YYYY HH:MM `- CREATE AN EVENT TASK

Creates and adds an event task at the given date and time

Example of usage:

`event get out in front /at  22/02/2020 23:45`

Expected outcome:

`[E][Not Done] get out in front (at: 22/02/2020 23:45) has been added to the list. 
You have 8 tasks left to do` 


### `TODO [STRING]`- CREATE A TODO TASK

Creates and adds a todo task with given description

Example of usage:

`todo get groceries`

Expected outcome:

`[T][Not Done] get groceries has been added to the list. 
You have 9 tasks left to do`


### `FIND[STRING] `- FIND TASKS BASED ON KEYWORD

Finds and displays all the tasks which contain an user-inputted keyword

Example of usage:

`find portfolio`

Expected outcome:

`[D][Not Done] portfolio now (by: 23/02/2020 12:12) contain portolio`


### `UPDATE [INT][STRING] `- UPDATE TASKS

Updates task at the integer provided by the within the user input

Example of usage:

`update 2 date 24/2/2020 23:23`

Expected outcome:

`[E][Donezo] portfolio now (at: 23/02/2020 12:12) has been updated to
[E][Donezo] portfolio now (at: 24/02/2020 23:23)`


### `NUMBER`- NUMBER OF TASKS IN THE LIST

Displays the number of tasks in the list

Example of usage:

`number`

Expected outcome:

`Number of tasks to do is 7`
