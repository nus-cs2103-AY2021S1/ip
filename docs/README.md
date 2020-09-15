# Duke User Guide
Duke is a chat-bot application that helps you to manage your daily tasks through command line interface.

* [Quick Start](#quick-start)
* [Features](#features)
* [Feature Details](#features)

<img src="https://raw.githubusercontent.com/afroneth/ip/master/docs/Ui.png" width="400">

## <ins>Quick Start</ins>
1. Download  and execute `jar` file from release. GUI should appear in a few seconds.
2. Type in commands and press `enter` on the keyboard or click the "Send" button to execute commands.
3. Type `help` command to list commands available.

## <ins>Features</ins>

### Feature 1 - Task Management
Duke helps you to manage important tasks that you need to complete in your daily life. 
There are a total of 3 types of tasks: **Todo**, **Deadline** and **Event**. Tasks will be saved to a local file 
automatically, and you can re-open it anytime to perform further updates.

### Feature 2 - Task Update
Duke allows you to mark your tasks as 'Done' after you have completed them successfully.
You may also remove them from your list via the `delete` command.

### Feature 3 - Intuitive Command Line Interface
Duke allows you to record your tasks with simple command line commands.

## <ins>Feature Details</ins>

### `todo` - Adds a todo task to your task list

input `todo` followed by a description of the task.

Example of usage: 

`todo <description of todo>`

Expected outcome:

```
Thank you for your input. The following task has been added to the list:
    [T][x] <description of todo>
You have at total of 1 task in the list.
```

### `event` - Adds an event task to your task list

input `event` followed by the description of your event, `/at`, followed by  
dates in `<DD/MM/YYYY> <HHMM>` format.

Example usage:

`event my event /at 19/09/2019 2349 `

Expected outcome:

```
Thank you for your input. The following task has been added to the list:
     [E][x] my event (at: 19th of September 2019, 11:49pm)
You have at total of 2 tasks in the list.
```

### `deadline` - Adds a deadline task to your task list

input `deadline` followed by description of the deadline, `/by`, followed by 
dates in `<DD/MM/YYYY> <HHMM>` format.

Example usage:

`deadline my deadline /by 20/03/2020 1303`

Expected outcome:

```
Thank you for your input. The following task has been added to the list:
    [D][x] my deadline (by: 20th of March 2020, 1:03pm)
You have at total of 3 tasks in the list.
```

### `list` - Displays all tasks in your task list

input `list`

Example usage:
`list`

Expected outcome:
```
These are the tasks in your list:
1. [T][x] description of todo
2. [E][x] my event (at: 19th of September 2019, 11:49pm)
3. [D][x] my deadline (by: 20th of March 2020, 1:03pm)
```

### `find` - Searches for a matching task in your task list

input `find` followed by search term to search for.

Example usage:
`find my`

Expected outcome:
```
These are the matching tasks in your list
	[E][x] my event (at: 19th of September 2019, 11:49pm)
	[D][x] my deadline (by: 20th of March 2020, 1:03pm)
```

### `delete` - remove a task

input `delete` followed by index of task to delete

Example usage:
`delete 1`

Expected outcome:
```
The following task has been successfully removed:
	[T][x] description of todo
You have at total of 2 tasks in the list.
```

### `done` - Marks a task as done

input `done` followed by index of task that has been completed

Example usage:
`done 1`

Expected outcome:
```
Great job! The following task has been marked as done:
  [E][✓] my event (at: 19th of September 2019, 11:49pm)
```
### `sort` - Sorts your list alphabetically according to task description or task type

input `sort` followed by `description` or `type`

Example usage 1:
`sort description`

Expected outcome:
```
Your list has been sorted in alphabetical order with respect to task description.

These are the tasks in your list:
1. [D][✓] my deadline (by: 20th of March 2020, 1:03pm)
2. [E][x] my event (at: 19th of September 2019, 11:49pm)
```

Example usage 2:
`sort type`

Expected outcome:
```
Your list has been sorted in alphabetical order with respect to task type.

These are the tasks in your list:
1. [D][✓] my deadline (by: 20th of March 2020, 1:03pm)
2. [D][✓] new deadline added (by: 30th of March 2019, 11:03pm)
3. [E][x] my event (at: 19th of September 2019, 11:49pm)
```

### `help` - Displays all available commands in Duke application

input `help`

Example usage:
`help`

Expected outcome:
```
"List of commands are as follows:"
                1. [list]: Lists all tasks
                2. [delete <index>]: Removes task from list
                3. [done <index>]: Marks task as done
                4. [todo <task>]: Creates Todo Task
                5. [deadline <task> /by DD/MM/YY HHMM]: Creates Deadline task with desired date
                6. [event <task> /at DD/MM/YY HHMM]: Creates Event task with desired date
                7. [find <query>] Returns matching tasks in list
                8. [sort <description> or <type>]: Sorts list   according to input parameter
                9. [help]: Displays all available features in Duke   application"
                10. [bye]: Closes program
```

### `bye` - Saves any changes to task list and exits Duke application

input `bye` to exit the application

Example usage:
`bye`

Expected outcome (no changes made to task list):
```
Goodbye. Hope to see you again soon!
```
Expected outcome (changes made to task list):
```
Tasks have been successfully saved to duke.txt!

Bye. Hope to see you again soon!
```