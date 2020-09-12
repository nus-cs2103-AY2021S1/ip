# User Guide

## Features 

### `todo <description>`
Add a todo task to the list.

Example of usage: 

`todo borrow books`

Expected outcome:

<code>
Got it. I've added this task: <br />
[T][✘] borrow books <br />
Now you have 3 tasks in the list.
</code>

### `deadline <description> /by <date & time>`
Add a deadline task to the list. <br />
Note: `<date & time>` must be in the format `yyyy-mm-ddTHH:mm`

Example of usage: 

`deadline return books /by 2020-09-30T23:59`

Expected outcome:

<code>
Got it. I've added this task: <br />
[D][✘] return books (by: Sep 30 2020, 23:59) <br />
Now you have 4 tasks in the list.
</code>

### `event <description> /at <date & time>`
Add an event task to the list. <br />
Note: `<date & time>` must be in the format `yyyy-mm-ddTHH:mm`

Example of usage: 

`event team meeting /at 2020-10-05T15:00`

Expected outcome:

<code>
Got it. I've added this task: <br />
[E][✘] team meeting (at: Oct 5 2020, 15:00) <br />
Now you have 5 tasks in the list.
</code>

### `list`
Display all the tasks in the list.

Expected outcome:

<code>
Here are the tasks in your list: <br />
1. [T][✘] borrow books <br />
2. [D][✘] return books (by: Sep 30 2020, 23:59) <br />
3. [E][✘] team meeting (by: Oct 5 2020, 15:00)
</code>

### `done <task number>`
Mark a task as completed. <br />
Note: `<task number>` can be obtained from `list` command.

Example of usage: 

`done 2`

Expected outcome:

<code>
This task has been marked as done: <br />
[D][✓] return books (by: Sep 30 2020, 23:59)
</code>

### `delete <task number>`
Remove a task from the list. <br />
Note: `<task number>` can be obtained from `list` command.

Example of usage: 

`delete 2`

Expected outcome:

<code>
This task has been removed: <br />
[D][✓] return books (by: Sep 30 2020, 23:59) <br />
Now you have 2 tasks in the list.
</code>

### `find <keyword>`
Filter the task list and display the tasks with the keyword.

Example of usage: 

`find meeting`

Expected outcome:

<code>
Here are the matching tasks in your list: <br />
1. [E][✘] team meeting (at: Oct 5 2020, 15:00) <br />
2. [D][✘] email meeting notes (by: Sep 25 2020, 19:00)
</code>

### `sort`
Sort and display the tasks in ascending order according to their dates.

Expected outcome:

<code>
`Here are the tasks in your list: <br />
1. [E][✘] project presentation (by: Sep 23 2020, 14:00) <br />
2. [D][✘] return books (by: Sep 30 2020, 23:59) <br />
3. [D][✘] submit book reviews (by: Oct 2 2020, 23:59) <br />
4. [E][✘] team meeting (by: Oct 5 2020, 15:00) <br />
5. [T][✘] borrow books
</code>

### `bye`
End and close the chat window after 3 seconds.

Expected outcome:
`Bye. Hope to see you again soon!`