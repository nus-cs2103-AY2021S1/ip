# User Guide
Duke is a minimalistic task manager desktop app. Duke is able to add 3 different types of tasks as well as specify the date and time of deadlines and events. You will also be able to mark tasks as completed or delete them from the list. With Duke, you will never forget about what you have to do again.

## Features 

### `todo <description>`
Add a todo task to the list.

Example of usage: 

`todo borrow books`

Expected outcome:

```
Got it. I've added this task:
[T][✘] borrow books
Now you have 3 tasks in the list.
```

### `deadline <description> /by <date & time>`
Add a deadline task to the list.<br />
Note: `<date & time>` must be in the format `yyyy-mm-ddTHH:mm`

Example of usage:  

`deadline return books /by 2020-09-30T23:59`

Expected outcome:  

```
Got it. I've added this task:
[D][✘] return books (by: Sep 30 2020, 23:59)
Now you have 4 tasks in the list.
```

### `event <description> /at <date & time>`
Add an event task to the list.<br />
Note: `<date & time>` must be in the format `yyyy-mm-ddTHH:mm`

Example of usage:   

`event team meeting /at 2020-10-05T15:00`

Expected outcome:  

```
Got it. I've added this task:
[E][✘] team meeting (at: Oct 5 2020, 15:00)
Now you have 5 tasks in the list.
```

### `list`
Display all the tasks in the list.

Expected outcome:  

```
Here are the tasks in your list:
1. [T][✘] borrow books
2. [D][✘] return books (by: Sep 30 2020, 23:59)
3. [E][✘] team meeting (by: Oct 5 2020, 15:00)
```

### `done <task number>`
Mark a task as completed.<br />
Note: `<task number>` can be obtained from `list` command.

Example of usage:  

`done 2`

Expected outcome:  

```
This task has been marked as done:
[D][✓] return books (by: Sep 30 2020, 23:59)
```

### `delete <task number>`
Remove a task from the list.<br />
Note: `<task number>` can be obtained from `list` command.

Example of usage:  

`delete 2`

Expected outcome:  

```
This task has been removed:
[D][✓] return books (by: Sep 30 2020, 23:59)
Now you have 2 tasks in the list.
```

### `find <keyword>`
Filter the task list and display the tasks with the keyword.

Example of usage:  

`find meeting`

Expected outcome:  

```
Here are the matching tasks in your list:
1. [E][✘] team meeting (at: Oct 5 2020, 15:00)
2. [D][✘] email meeting notes (by: Sep 25 2020, 19:00)
```

### `sort`
Sort and display the tasks in ascending order according to their dates.

Expected outcome:  

```
Here are the tasks in your list:
1. [E][✘] project presentation (by: Sep 23 2020, 14:00)
2. [D][✘] return books (by: Sep 30 2020, 23:59)
3. [D][✘] submit book reviews (by: Oct 2 2020, 23:59)
4. [E][✘] team meeting (by: Oct 5 2020, 15:00)
5. [T][✘] borrow books
```

### `bye`
End and close the chat window after 3 seconds.

Expected outcome:  

```
Bye. Hope to see you again soon!
```