# Duke User Guide
![Duke](Ui.png)

## Features summary

Feature | Description | Example
-------------|---------- | --------
todo|Adds a Todo task to your list|todo buy dinner
deadline|Adds a Deadline task to your list|deadline return book /by 25/9/2020 21:00
event|Adds an Event task to your list|event cca cohesion /at 30/9/2020 19:00
delete|Deletes a task in your list|delete 1
done|Marks a task in your list as done|done 2
list|Lists out all current tasks to the user|list
find|Finds all tasks matching the specified keyword(s)|find book birthday
sort|Sorts all current tasks in chronological order|sort
bye|Exits Duke and closes the program|bye

## Features

### Feature 1

<h4>Add Todo Task</h4>

Adds a Todo Task to your list.

### `todo DESCRIPTION`
Adds a Todo task where `DESCRIPTION` is the task name.

Example of usage: 

`todo buy dinner`

Expected outcome:

![Todo](./images/todo.png)

### Feature 2

<h4>Add Deadline Task</h4>

Adds a Deadline Task to your list.

### `deadline DESCRIPTION /by dd/MM/yyyy HH:mm`
Adds a Deadline task where `DESCRIPTION` is the task name and `dd/MM/yyyy HH:mm` is the date time.
If the time `HH:mm` is not specified, it will be set to the end of the day i.e. 23:59.

Example of usage: 

`deadline return book /by 25/9/2020 21:00`

Expected outcome:

![Deadline](./images/deadline.png)

### Feature 3

<h4>Add Event Task</h4>

Adds an Event Task to your list.

### `event DESCRIPTION /at dd/MM/yyyy HH:mm`
Adds an Event task where `DESCRIPTION` is the task name and `dd/MM/yyyy HH:mm` is the date time.
If the time `HH:mm` is not specified, it will be set to the end of the day i.e. 23:59.

Example of usage: 

`event cca cohesion /at 30/9/2020 19:00`

Expected outcome:

![Event](./images/event.png)

### Feature 4

<h4>Delete Task</h4>

Deletes a task in your list.

### `delete INDEX`
Deletes the task where `INDEX` represents the task number in the list.

Example of usage: 

`delete 1`

Expected outcome:

![Delete](./images/delete.png)

### Feature 5

<h4>Mark Task as done</h4>

Marks a task in your list as done.

### `done INDEX`
Marks the task as done where `INDEX` represents the task number in the list.

Example of usage: 

`done 2`

Expected outcome:

![Done](./images/done.png)

### Feature 6

<h4>List tasks</h4>

Lists out all current tasks to the user.

### `list`

Example of usage: 

`list`

Expected outcome:

![List](./images/list.png)

### Feature 7

<h4>Find tasks using keywords</h4>

Finds all tasks matching the specified keyword(s).

### `find KEYWORDS`
Finds all matching tasks to the specified `KEYWORDS`,
where `KEYWORDS` can contain 1 or more keywords.

Example of usage: 

`find book birthday`

Expected outcome:

![Find](./images/find.png)

### Feature 8

<h4>Sort tasks</h4>

Sorts all current tasks in chronological order.

### `sort`
Sorts all tasks according to their type in the following order: Todo, Deadline, Event.
Then, within each type of tasks, they are sorted in chronological order.

Example of usage: 

`sort`

Expected outcome:

![Sort](./images/sort.png)

### Feature 9

<h4>Exit programme</h4>

### `bye`
Exits the programme.

Example of usage: 

`bye`

Expected outcome:

Duke says goodbye and programme exits automatically after 2 seconds.