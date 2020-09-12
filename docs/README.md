# User Guide
Have you ever wanted the Supreme Leader of North Korea to be your personal secretary?
Now you can with your very own Kim Jong Duke personal assistant chatbot! In the chatbot,
you will play the role of Donald Trump and have your BFF Kim Jong Duke manage your tasks 
for you! :D

> "Nobody knows more about how fantastic this chatbot is. Believe me. I am the best." - Donald Trump

> "Can this chatbot be eaten?" - Kim Jong Un

> "A real masterpiece!" - My Mum

## Features 
1. Add a task - Todo, Event, Deadline
2. Delete a task
3. Find a task 
4. Mark task as completed
5. Show all tasks
6. Support for natural date formats

## Feature 1 - Add a task - Todo, Event, Deadline

Users are able to add tasks into Kim Jong Duke. Specifically,
they are able to add 3 types of tasks - Todo, Event, Deadline.

**Todo** - Task without any date/time attached to it.

**Event** - Task that starts and/or ends at a specific time.

**Deadline** - Task that needs to be done before a specific date/time.

### Usage

### `todo`

Action - `todo`

Outcome - Creates a task without any date/time attached to it.

Tags - [T] _Indicates that the task is a Todo._

Example of usage: 

`todo nuke north korea`

Expected outcome:

`Got it. I've added this task:`

`[T][N] nuke north korea`



### `event`

Action - `event`

Outcome - Creates a task that starts and/or ends at a specific time.

Tags - [E] _Indicates that the task is an Event._

Example of usage: 

`event meet Kim Jong Un /at 5pm`

Expected outcome:

`Got it. I've added this task:`

`[E][N] meet Kim Jong Un (at:5pm)`



### `deadline`

Action - `deadline`

Outcome - Creates a task that needs to be done before a specific date/time.

Tags - [D] _Indicates that the task is a Deadline._

Example of usage: 

`deadline tweet bad things about Joe Biden /by tomorrow`

Expected outcome:

`Got it. I've added this task:`

`[D][N] tweet bad things about Joe Biden (by: tomorrow)`



## Feature 2 - Delete a task

Users are able to delete a task by declaring the serial number
tagged to the task.

### Usage

### `delete`

Action - `delete`

Outcome - Deletes a task.

Example of usage: 

`delete 1`

Expected outcome:

Assumption - [T][N] nuke north korea is the first task (ie has S/N 1.) in 
the list of tasks.

`Noted. I've removed this task:`

`[T][N] nuke north korea`



## Feature 3 - Find a task

Users are able to find tasks in their task list containing specific keywords.

### Usage

### `find`

Action - `find`

Outcome - Find tasks that match the given keyword.

Example of usage: 

`find north korea`

Expected outcome:

`Here are the matching tasks in your list:`

`1. [T][N] nuke north korea`



## Feature 4 - Mark task as completed

Upon completing a task, users are able to mark their task as 
completed by declaring the serial number tagged to the task.

### Usage

### `done`

Action - `done`

Outcome - Mark a task as completed. _[N] changes to [Y] 
to indicate that the task has been completed._

Example of usage: 

`done 1`

Expected outcome:

`Nice! I've marked this task as done:`

`1. [T][Y] nuke north korea`



## Feature 5 - Show all tasks

Displays all tasks in users' task list.
(This includes both completed [Y] and non-completed [N] tasks)

### Usage

### `list`

Action - `list`

Outcome - Displays all tasks in users' task list.

Example of usage: 

`list`

Expected outcome:

`1. [T][Y] nuke north korea`

`2. [E][N] meet Kim Jong Un (at:5pm)`

`3. [D][N] tweet bad things about Joe Biden (by:tomorrow)`



## Feature 6 - Support for natural date formats

Kim Jong Duke can accept dates in certain formats and will convert it to English date format.
If a day of the week (e.g Monday) is accepted as input, Kim Jong Duke will convert the input to
the earliest possible Monday in GMT +8. (Ie This Monday if the Monday of the week has yet to pass,
otherwise the Monday of the next week.)
 
 
Format Accepted | Example Input | Example Output 
------------ | ------------- | -------------
dd/MM/yyyy HHmm | 07/09/2020 1100 | Monday, September 07, 2020, 11:00 AM
d/MM/yyyy HHmm | 7/09/2020 1100 | Monday, September 07, 2020, 11:00 AM
dd/M/yyyy HHmm | 07/9/2020 1100 | Monday, September 07, 2020, 11:00 AM
d/M/yyyy HHmm | 7/9/2020 1100 | Monday, September 07, 2020, 11:00 AM
dd-MM-yyyy HHmm | 07-09-2020 1100  | Monday, September 07, 2020, 11:00 AM
d-MM-yyyy HHmm | 7-09-2020 1100 | Monday, September 07, 2020, 11:00 AM
dd-M-yyyy HHmm | 07-9-2020 1100 | Monday, September 07, 2020, 11:00 AM
d-M-yyyy HHmm | 7-9-2020 1100 | Monday, September 07, 2020, 11:00 AM
Monday | Monday | Monday, September 07, 2020
monday | monday | Monday, September 07, 2020
Mon | Mon | Monday, September 07, 2020
mon |mon | Monday, September 07, 2020


### Usage

### `NaturalDates`

Action - `deadline nuke north korea /by Sunday`

Outcome - Sunday is converted to Sunday, September 13, 2020.

Example of usage: 

`deadline nuke north korea /by Sunday`

Expected outcome:

`Got it. I've added this task:`

`[D][N] nuke north korea (by: Sunday, September 13, 2020)`