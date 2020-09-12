# Shiro: User Guide

Shiro is a chat-bot cum task manager that the user can interact with via a command-line interface.

There are three types of tasks currently supported by Shiro:
* ToDos: tasks with no specific date
* Deadlines: tasks with a specified due date
* Events: tasks with a specified date of occurrence

I customized it with the theme of my favourite cartoon: Sumikko Gurashi!
![GitHub Logo](Ui.png)

## Features 
1. `help`: provides a list of possible commands
2. `list`: shows the list of tasks
3. Add tasks
	1. `todo`: creates a todo task with specific description
	2. `deadline`: creates a deadline task with specific description and date
	3. `event`: creates an event task with specific description and date
4. `done`: marks a task as complete
5. `delete`: deletes a task from the list
6. `clear`: deletes all tasks from the list
7. `find`: shows task(s) which match the query string
8. `view`: shows task(s) due on the given date
9. `bye`: ends the conversation with Shiro

### 1. `help`
This command shows a list of all possible commands that Shiro can recognize.

##### Usage
`help`

##### Example of usage: 
`help`

##### Expected outcome:
```
available commands are:
  1. list
  2. todo <description>
  3. deadline <description> /by <date>
  4. event <description> /at <date>
  5. done <task_number>
  6. delete <task_number>
  7. clear
  8. find <keyword>
  9. view <date>
 10. bye
```

### 2. `list`
This command shows the entire list of tasks.

##### Usage
`list`

##### Example of usage: 
`list`

##### Expected outcome:
```
okies! here are the tasks in your list:
1. [T][✘] read book
2. [D][✘] return book (by: Sep 14 2020)
3. [E][✘] project meeting (at: Sep 15 2020)
```

### 3.i. `todo`
This command adds a *todo* task to the list.

##### Usage
`todo <description>`

##### Example of usage: 
`todo read book`

##### Expected outcome:
```
sure thing! i have added the following task to your list:
    [T][✘] read book
you have [1] task(s) in your list
```

### 3.ii. `deadline`
This command adds a *deadline* task to the list.

##### Usage
`deadline <description> /by <date>`

`<date>` can either be in the format `<yyyy-mm-dd>` or `<day>`.

`<day>` should only consist the first three letters of the day of week.

For the usage of `<day>`, the date used will be the next specified day of week in the calendar.
If the day provided is the same as the current day of week, 
the date of the next specified day of week in the calendar will also be used, 
instead of the current date.

##### Example of usage: 
`deadline return book /by 2020-09-20`

##### Expected outcome:
```
sure thing! i have added the following task to your list:
    [D][✘] return book (by: Sep 20 2020)
you have [1] task(s) in your list
```

### 3.iii. `event`
This command adds an *event* task to the list.

##### Usage
`event <description> /by <date>`

`<date>` can either be in the format `<yyyy-mm-dd>` or `<day>`.

`<day>` should only consist the first three letters of the day of week.

For the usage of `<day>`, the date used will be the next specified day of week in the calendar.
If the day provided is the same as the current day of week, 
the date of the next specified day of week in the calendar will also be used, 
instead of the current date.

##### Example of usage: 
`event project meeting /at 2020-09-28`

##### Expected outcome:
```
sure thing! i have added the following task to your list:
    [E][✘] project meeting (at: Sep 28 2020)
you have [1] task(s) in your list
```

### 4. `done`
This command marks the specified task as done.

##### Usage
`done <task_number>`

If the task corresponding with the task number uncompleted [✘] before, it will now be marked as completed [✓].

##### Example of usage: 
`done 3`

##### Expected outcome:
```
yay! i have marked this task as done:
    [E][✓] project meeting (at: Sep 28 2020)
you have [1] task(s) in your list
```

### 5. `delete`
This command deletes the specified task from the list.

##### Usage
`delete <task_number>`

##### Example of usage: 
`delete 2`

##### Expected outcome:
```
of course! i have deleted this task from your list:
    [D][✘] return book (by: Sep 20 2020)
you have [3] task(s) in your list
```

### 6. `clear`
This command deletes all tasks from the list.

##### Usage
`clear`

##### Example of usage: 
`clear`

##### Expected outcome:
`okie! all the tasks in your list have been cleared :-)`

### 7. `find`
This command shows all the tasks which match the query string.

##### Usage
`find <keyword(s)>`

##### Example of usage: 
`find book`

##### Expected outcome:
```
got it! here are the tasks matching your search:
1. [T][✘] read book
2. [D][✘] return book (by: Sep 14 2020)
```

### 8. `view`
This command shows all the tasks due on the given date.

##### Usage
`view <date>`

`<date>` can either be in the format `<yyyy-mm-dd>` or `<day>`.

`<day>` should only consist the first three letters of the day of week.

For the usage of `<day>`, the date used will be the next specified day of week in the calendar.
If the day provided is the same as the current day of week, 
the date of the next specified day of week in the calendar will also be used, 
instead of the current date.

##### Example of usage: 
`view 2020-09-20`

##### Expected outcome:
```
got it! here are the tasks on your given date:
1. [E][✘] project meeting (at: Sep 20 2020)
2. [D][✘] return book (by: Sep 20 2020)
```

### 8. `bye`
This command causes the application to exit.

##### Usage
`bye`

##### Example of usage: 
`bye`

##### Expected outcome:
The application closes when this command is sent. 