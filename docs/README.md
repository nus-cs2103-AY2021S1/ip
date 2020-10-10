# User Guide
Welcome to **Duke**, a Personal Assistant Chatbot that helps you keep track of todos, deadlines and tasks.

- [Features](#Features)
- [Usage](#Usage)

## <a name="Features">Features</a> 

### [Listing all tasks](#list): `list`

### [Finding a task](#find): `find`

### [Adding a todo](#todo): `todo`

### [Adding a deadline](#deadline): `deadline` 

### [Adding an event](#event): `event` 

### [Marking a task as done](#done): `done` 

### [Deleting a task](#delete): `delete` 

### [Asking for help](#help): `help` 

### [Exiting the application](#bye): `bye`

## <a name="Usage">Usage</a>

### <a name="list">Listing all tasks</a>: `list`

Displays a list of all existing tasks.

Format: `list`

Expected outcome:

>Here are the tasks in your list:\
>1.[T][✓] ACC assignment\
>2.[E][✘] Formal Dinner (at: Sep 17 2020)\
>3.[T][✓] CS assignment 


### <a name="find">Finding a task</a>: `find`

Displays a list of tasks which contain the given keyword.

Format: `find [keyword]`

Example of usage: `find assignment`

Expected outcome:

>Here are the matching tasks in your list:\
>1.[T][✘] ACC assignment\
>2.[T][✘] CS assignment


### <a name="todo">Adding a todo</a>: `todo`

Adds a task with a description to the task list.

Format: `todo [description]`

Example of usage: `todo Essay`

Expected outcome:

>Got it. I've added this task:\
>[T][✘] Essay\
>Now you have 4 tasks in the list.


### <a name="deadline">Adding a deadline</a>: `deadline`

Adds a task with a description and a deadline to the task list.

Format: `deadline [description] /by [date]`

- deadline must have both a description and date
- date must be in yyyy-mm-dd format

Example of usage: `deadline School Fees /by 2020-11-01`

Expected outcome:

>Got it. I've added this task:\
>[D][✘] School Fees (by: Nov 1 2020)\
>Now you have 5 tasks in the list.

### <a name="event">Adding an event</a>: `event`

Adds a task with a description and an event date to the task list.

Format: `event [description] /at [date]`

- event must have both a description and date
- date must be in yyyy-mm-dd format

Example of usage: `event Birthday Celebration /at 2020-12-22`

Expected outcome:

>Got it. I've added this task:\
>[E][✘] Birthday Celebration (at: Dec 22 2020)\
>Now you have 6 tasks in the list.

### <a name="done">Marking a task as done</a>: `done` 

Marks the specified task as done in the task list.

Format: `done [index]`

- index refers to the task's index in the task list *(e.g. ACC assignment has index 1)*

Example of usage: `done 5`

Expected outcome:

>Nice! I've marked this task as done:\
>[✓] School Fees


### <a name="delete">Deleting a task</a>: `delete`

Removes the specified task from the task list.

Format: `delete [index]`

- index refers to the task's index in the task list *(e.g. ACC assignment has index 1)*

Example of usage: `delete 2`

Expected outcome:

>Noted. I've removed this task:\
>[E][✘] Formal Dinner (at: Sep 17 2020)

### <a name="help">Asking for help</a>: `help` 

Displays the help page.

Format: `help`

Expected outcome:

>I see that you need some help!
>
>Here are a list of Duke commands ([] indicates user input):
>
>1. list - shows a list of your existing tasks
>2. find [keyword] - shows a list of existing tasks which contain the given keyword
>3. todo [description] - adds a todo with a description
>4. deadline [description] /by [date in yyyy-mm-dd format] - adds a deadline with a description and a deadline
>5. event [description] /at [date in yyyy-mm-dd format] - adds an event with a description and an event date
>6. done [index] - marks the specified task as done
>7. delete [index] - deletes the specified task from the task list
>8. help - shows the help page
>9. bye - exits the application


### <a name="bye">Exiting the application</a>: `bye`

Exits the application.

Format: `bye`

Expected outcome:

>Bye. Hope to see you again soon!
