# User Guide for Peanut

## About Peanut
Peanut is a free, reliable and delicious assistant that helps to keep track of
your tasks. He loves helping people stay organised and on task so don't 
hesitate to approach him for help! Read the **Features** section to see what
services he provides, and the **Usage** section to learn how to employ him. 

## Features 

### Add tasks 
Add various types of tasks that you wish to keep track of, including
*todo*, *event* and *deadline* tasks. More detailed instructions on how to 
do this can be seen in the **Usage** section.
### Task List
View the full list of the tasks you have added. You will be able to see
the task type(*todo*, *event*, *deadline*), status(done or not done), name, and date if any.
### Delete tasks
Delete tasks that you no longer want to keep track of.
### Mark tasks as done
Check tasks off your list as your finish doing them. 
### Detect duplicate tasks
Peanut will alert you if you have multiple tasks with the same name
just in case you accidentally added duplicates.
### Search for tasks
Find tasks that match keywords that you specify


## Usage

### `todo` - Add a *todo* task

Add a *todo* task, the simplest type of task. Peanut only needs
a task description from you to do this. He does not require a date/time for 
*todo* tasks although you can add it as part of the description.

Example of usage: 

`todo {Get a haircut}`

Expected reply from Peanut:

`Hurray! I have added: [T][✗] Get a haircut`

### `event` - Add an *event* task

Add an *event* task. Peanut requires a task description and a date/time
from you to do this. The date/time does not need to be in any
particular format. Peanut will just note down whatever format you use exactly.

Example of usage: 

`event {One Direction Concert} /at {Friday 6pm}`

Expected reply:

`Hurray! I have added: [E][✗] One Direction Concert (at: Friday 6pm)`

### `deadlide` - Add a *deadline* task

Add a *deadline* task. Peanut requires a task description and a date
from you to do this. The date needs to be in a dd-MM-yyyy format. 

Example of usage: 

`deadline {Online Quiz} /by {08-08-2025}`

Expected reply:

`Hurray! I have added: [D][✗] Online Quiz (by: 08 Aug 2020)`

### `list` - List tasks

Ask Peanut to show you the full list of tasks. 

Example of usage: 

`list`

Expected reply:

`1.[T][✗] Get a haircut` \
`2.[E][✗] One Direction Concert (at: Friday 6pm)`\
`3.[D][✗] Online Quiz (by: 08 Aug 2020)`

### `delete` - Delete tasks

Delete a task. Tasks need to be referenced by their index 
number. Use the *list* command to see the indexes of the tasks. 

Example of usage: 

`delete {3}`\
3 here indicates the task with index 3 on the list

Expected reply:

`Okay Online Quiz has been deleted.` \
`[D][✗] Online Quiz (by: 08 Aug 2020)`\
`You now have 2 tasks.`

### `done` - Mark tasks as done

Check a task off the list. Tasks need to be referenced by their index 
number. Use the *list* command to see the indexes of the tasks. 

Example of usage: 

`done {1}`\
1 here indicates the task with index 1 on the list

Expected reply:

`Hurray! Get a haircut is now done.` \
`[T][✓] Get a haircut`

### `find` - Find tasks

Find tasks that match a keyword that you specify.

Example of usage: 

`find {Concert}`

Expected reply:

`Here are the matching tasks I could find` \
`1.[E][✗] One Direction Concert (at: Friday 6pm)`

### `bye` - End session

Say bye to Peanut and close the session.

Example of usage: 

`bye`

Expected reply:

None. Chat window closes.