# Duckie User Guide
Duckie is your task manager to manage all your tasks. 

## Table of contents
* [Features](#features)
    * [Add new tasks](#add-new-tasks)
    * [List all the tasks](#list-all-the-tasks)
    * [Delete tasks](#delete-tasks)
    * [Find tasks](#find-tasks)
    * [Mark tasks as done](#mark-tasks-as-done)
    * [Sort events or deadline](#sort-events-or-deadline-based-on-their-dates)
    * [Exit the platform](#exit-the-platform)
 * [Usage](#usage)
    * [todo](#todo---a-simple-task-with-only-a-description)
    * [deadline](#deadline---a-task-containing-a-deadline)
    * [event](#event---a-task-containing-a-date-of-event)
    * [list](#list---list-all-the-tasks-in-the-task-list)
    * [delete](#delete---delete-a-particular-task-in-the-list)
    * [delete all](#delete-all---clear-all-the-tasks-in-the-list)
    * [find](#find---search-for-tasks-based-on-a-keyword-you-have-input)
    * [done](#done---mark-task-based-on-the-index-in-the-list-to-be-done)
    * [sort deadline](#sort-deadline---sort-tasks-based-on-the-deadline-in-chronological-order)
    * [sort event](#sort-event---sort-tasks-based-on-the-event-date-in-chronological-order)
    * [bye](#bye---exit-the-platform)
 * [List of Commands](#list-of-commands)
    

## Features 

### Add new tasks
Add tasks so that Duckie can manage them for you!
You can add the following types of tasks:
1. **Todo** - A simple task with only a description
1. **Deadline** - A task containing a deadline
1. **Event** - A task containing the date of event

### List all the tasks
List all the input tasks in the current task list and check their status

### Delete tasks
Delete unwanted tasks in the current task list. You can choose to delete all tasks at one go as well.

### Find tasks
Search for tasks based on keyword you have input.

### Mark tasks as done
Each task has its own status and can be marked done when required.

### Sort events or deadline based on their dates
Sort the event tasks, or the deadline tasks based on their dates in chronological order.

### Exit the platform
Input bye command and exit the platform to say goodbye to Duckie.

## Usage

### `todo` - A simple task with only a description

Format: `todo DESCRIPTION`

Example of usage: `todo borrow books`

Expected outcome: 
```
"Quack! Added: 
[T][✘] borrow books
Now you have 1 task(s) in the list.
```

### `deadline` - A task containing a deadline

Format: `deadline DESCRIPTION /by d MMM YYYY`

Example of usage: `deadline Quiz /by 21 Aug 2020`

Expected outcome: 
```
"Quack! Added: 
[D][✘] Quiz (by: Fri, 21 Aug 2020)
Now you have 1 task(s) in the list.
```

### `event` - A task containing a date of event

Format: `event DESCRIPTION /at d MMM YYYY HH:MM a`

Example of usage: `event Dance Night /at 21 Aug 2020 07:30 PM`

Expected outcome: 
```
"Quack! Added: 
[E][✘] Dance Night (at: Fri, 21 Aug 2020 07:30 PM)
Now you have 1 task(s) in the list.
```

### `list` - List all the tasks in the task list

Example of usage: `list`

Expected outcome: 
```
"Quack! You have these in your list currently:
1. [T][✘] borrow books
2. [D][✘] Quiz (by: Fri, 21 Aug 2020)
3. [E][✘] Dance Night (at: Fri, 21 Aug 2020 07:30 PM)
```

### `delete` - Delete a particular task based on the index in the list

Format: `delete INDEX`

Example of usage: `delete 2`

Expected outcome: 
```
"Quack! I've removed this task: 
[D][✘] Quiz (by: Fri, 21 Aug 2020)
```

### `delete all` - Clear all the tasks in the list

Format: `delete all`

Expected outcome:
```
Quack! All tasks are cleared in the list!
```

### `find` - Search for tasks based on a keyword you have input

Format: `find KEYWORD`

Example of usage: `find quiz`

Expected outcome:
```
Quack! Duckie found these matching tasks:
1. [D][✘] Quiz (by: Fri, 21 Aug 2020)
```

### `done` - Mark task based on the index in the list to be done

Format: `done INDEX`

Example of usage: `done 2`

Expected outcome:
```
Quack! I've marked this task as done:
[E][✓] Dance Night (at: Fri, 21 Aug 2020 07:30 PM)
```

### `sort deadline` - Sort tasks based on the deadline in chronological order

Format: `sort deadline`

Expected outcome:
```
Quack! The following are your Deadline tasks from the earliest to the latest.
1. [D][✘] Quiz (by: Fri, 21 Aug 2020)
2. [D][✘] CS2101 Reflection (by: Sun, 23 Aug 2020)
```

### `sort event` - Sort tasks based on the event date in chronological order

Format: `sort event`

Expected outcome:
```
Quack! The following are your Event tasks from the earliest to the latest.
1. [E][✘] Homecoming (at: Wed, 19 Aug 2020 12:30 PM)
2. [E][✘] Birthday Party (at: Sun, 23 Aug 2020 4:30 PM)
```

### `bye` - Exit the platform

Format: `bye`

## List of Commands

Features | Command Inputs
------------ | -------------
todo | `todo DESCRIPTION`
deadline | `deadline DESCRIPTION /by d MMM YYYY`
event | `event DESCRIPTION /at d MMM YYYY HH:MM a`
list | `list`
delete | `delete INDEX`
delete all | `delete all`
find | `find INDEX`
done | `done INDEX`
sort deadline | `sort deadline`
sort event | `sort event`
bye | `bye`