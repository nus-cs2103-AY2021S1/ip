# User Guide

## Features 

* **Tasks**

    There are 3 types of tasks you can add:
    * Todo 
    * Deadline
    * Event

* **Manage your tasks**
    * Mark task as complete
    * Delete tasks
    * Search
    
* **See your whole list of tasks easily**

* **Filter tasks that are due soon**
    * Find items due by a certain date
    * Find items due before a certain date and/or time

* **Storage of list**
    
    Your list of items is stored locally on your disk, so you do not 
    have to worry about losing your data!

## Usage

### `list` - shows the user the whole list of task(s)

Example of usage: 

`list`

Expected outcome (if there are tasks in the list):

`Here are the task(s) in your list:...`

Expected outcome (if there are NO tasks in the list):

`List is empty, you have free time (for now)! YAY :D`

### `bye` - ends the chat with Bob.

Example of usage: 

`bye`

Expected outcome:

`Bye. Hope to see you again soon! :)`

### `help` - shows all the valid inputs for Bob.

Example of usage:

`help`

Expected outcome:

`List of commands:...`

### *To add a task:*

### `todo <task description>` - adds a Todo item

Adds a todo item to the list.

Example of usage: 

`todo homework`

Expected outcome:

```
Got it. I've added this task:
    [T][✘] homework
Now you have 1 task(s) in the list.
```

### `deadline <task description> /<connector> <DD/MM/YYYY> <HHmm>` - adds a Deadline item

Adds a deadline item to the list.
The addition of a time is optional. Time input should be in 24hrs format.

Example of usage: 

`deadline project /by 14/9/2020 2359`

Expected outcome:

```
Got it. I've added this task:
    [D][✘] project (by 14 Sep 2020, 11:59PM)
Now you have 2 task(s) in the list.
```

### `event <task description> /<connector> <DD/MM/YYYY> <HHmm>-<HHmm>` - adds an event item

Adds an event item to the list.
The addition of a time is optional. Time input should be in 24hrs format and has a start + end timing.

Example of usage: 

`event party /from 15/9/2020 1700-2300`

Expected outcome:

```
Got it. I've added this task:
    [E][✘] party (from 15 Sep 2020, 5:00PM - 11:00PM)
Now you have 3 task(s) in the list.
```

### `done <item number>` - mark a particular task done

Example of usage:

`done 1`

Expected outcome:

```
Good job! I've marked this task as done:
    [T][✓] homework
```

### `delete <item number>` - deletes a particular task

Example of usage:

`delete 1`

Expected outcome:

```
Noted. I've removed this task:
    [T][✓] homework
Now you have 2 tasks in the list.
```

### `find <keyword>` - search tasks with keyword

Example of usage:

`find project`

Expected outcome:

```
Here are the matching tasks in your list:
1.[D][✘] project (by 14 Sep 2020, 11:59PM)
```

### *Filter*

### `items due by <DD/MM/YYYY>` - search tasks due by inputted date

Example of usage:

`items due by 14/9/2020`

Expected outcome:

```
Task(s) due by 14 Sep 2020:
[D][✘] project (by 14 Sep 2020, 11:59PM)
```

### `items due before <DD/MM/YYYY> <HHmm>` - search tasks due before inputted date and/or time

Time input is optional.

Example of usage:

`items due before 15/9/2020 2359`

Expected outcome:

```
Task(s) due before 15 Sep 2020, 11:59PM:
[D][✘] project (by 14 Sep 2020, 11:59PM)
[E][✘] party (from 15 Sep 2020, 5:00PM - 11:00PM)
```

