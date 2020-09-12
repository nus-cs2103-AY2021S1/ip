# User Guide

## Features 

### Add task (e.g. Todo, deadline, event)

Asks the chatbot to add a new task to the list 
and save it to disk.
You are expected to specify a date for deadlines 
and events.

### List task
Asks the chatbot to list out your tasks.

### Delete task
Instructs the chatbot to delete the task which 
you do not want or have completed.

### Mark complete
Instructs the chatbot to mark a task as done.

### Find by date
Asks the chatbot to return you all tasks on 
a given date, to help you quickly find out what 
needs to be done on a particular day.

### Find by keyword
Asks the chatbot to return you a list of tasks
that contains certain keywords.

### Sort tasks
Asks the chatbot to return you a list of tasks
sorted by earliest or latest date.


## Usage

### Show All Tasks

Format: `list`

Shows a list of all tasks managed by the chatbot.

Examples:

- `list` will list out all tasks by the order they are added.


### Adds a Todo Task

Adds a todo task to the chatbot.

Format: `todo <description>` 

Examples:

- `todo Walk the dog` adds a todo task with the description `Walk the dog.`


### Adds a Deadline

Adds a deadline to the chatbot.

Format: `deadline <description> /by <date>`

Examples: 

- `deadline Submit assignment /by 2020-09-10` will add a deadline with a description
`Submit assignment` that is due on 10 September 2020.

### Adds an Event

Adds an event to the chatbot.

Format: `event <description> /at <date>`

Examples: 

- `event Attend sister's wedding /at 2020-10-10` will add an event with a description
`Attend sister's wedding` on 10 October 2020.

### Mark Complete

Marks a task as completed.

Format: `done <index>`

- Marks the task at the specified index as done.
- The index refers to the index number shown in the displayed tasks list.
- The index must be a positive integer i.e. 1, 2, 3, ...

Examples:

- `done 1` marks the first task in the task list as complete.

### Delete a Task

Deletes a task from the task list.

Format: `delete <index>`

- Deletes the task at the specified index.
- The index refers to the index number shown in the displayed tasks list.
- The index must be a positive integer i.e. 1, 2, 3, ...

Examples: 

- `delete 1` will delete the first task in the task list.

### Find by Date

Finds a task by the specified date.

Format:
`date <date>`

Examples: 

- `date 2020-10-10` will retrieve all tasks on 10 October 2020.


### Find by Keyword

Find tasks which the description contains the given keywords.

Format: `find <keyword>`

Examples:

- `find Submit` will retrieve all tasks containing the word `Submit`.


### Sort tasks

Sort tasks by `latest` or `earliest` date, and returns you a list of tasks in that order.
Tasks without a date will be placed at the bottom of the list.

Format: `sort /by <descriptor>`

Examples: 
```
1. Submit assignment (by 10 Oct 2020)
2. Attend sister's wedding (at 5 Nov 2020)
3. Submit internship application (by 8 Oct 2020)
```

- `sort /by latest` will return the following:
```
1. Attend sister's wedding (at 5 Nov 2020)
2. Submit assignment (by 10 Oct 2020)
3. Submit internship application (by 8 Oct 2020)
```
- `sort /by earliest` will return a list of tasks
```
1. Submit internship application (by 8 Oct 2020)
2. Submit assignment (by 10 Oct 2020)
3. Attend sister's wedding (at 5 Nov 2020)
```
### Exit

Exits the program.

Format: `bye`

