# User Guide

## Getting Started

1. Ensure that you have Java `11` or above installed on your device.
2. You can find the latest version of mattbot [here](provide link later).
3. Download the `mattbot.jar` file and open to start the application.
4. Run the jar file.
5. Enter commands into the textbox and click `Send`.

## Features

| Feature name               | Description  | syntax |
|:--------------------------| :----------- | :------ |
Add new todo task          | Add a new todo task with the given *[name]*                                                     | todo *[name]*
Add new deadline task      | Add a new deadline task with the given *[name]*, *[date and time]*                              | deadline *[name]* /by *[d-MM-uuuu HHmm]*
Add new Event task         | Add a new event task with the given *[name]*, *[date and time]*                                 | event *[name]* /at *[d-MM-uuuu HHmm]*
Mark as done               | Mark the specific task index, *[index]* as completed                                            | done *[index]*
Delete task                | Deletes the specified task *[index]* from the list                                              | delete *[index]*
Show all existing commands | Show all the commands the user can used                                                         | show
List all task              | List all currently tracked task and show their relevant information                             | list
View all tasks for a day   | View all the task on a specific date, *[date]*, and the tasks are arranged in order of the time | view *[d-MM-uuuu]*
Search for tasks           | Search of all tasks with this name *[name]*                                                     | find *[name]*
Filter the tasks by date   | Filter all the tasks and only show those that have the same *[date]*                            | filter *[d-MM-uuuu]*
Exits the program          | Terminates and closes the program | bye


### Feature 1
Add a new todo task with the given *[name]*

## Usage

### Keywords: `todo`

Example of usage:

`todo CS2103T`

Expected outcome:

```
Task has been successfully added!
    [T][X] CS2103T
Mattbot is tracking 1 number of tasks!
```

### Feature 2
Add a new deadline task with the given *[name]*, *[date and time]*

## Usage

### Keywords: `deadline`

Example of usage:

`deadline CS2103T IP /by 18-09-2020 2359`

Expected outcome:

```
Task has been successfully added!
    [D][X] CS2103T IP (by: 18 Sep 2020 2359)
Mattbot is tracking 2 number of tasks!
```

### Feature 3
Add a new event task with the given *[name]*, *[date and time]*

## Usage

### Keyword: `event`


Example of usage:

`event CS2103T IP /by 19-09-2020 2359`

Expected outcome:

```
Task has been successfully added!
    [E][X] CS2103T IP (by: 19 Sep 2020 2359)
Mattbot is tracking 3 number of tasks!
```

### Feature 4
Mark the specific task index, *[index]* as completed

## Usage

### Keywords: `done`

Example of usage:

`done 1`
`done 2`

Expected outcome:

```
    [T][O] CS2103T
The tracked task has been marked as completed! Congrats~~!
```
```
    [D][O] CS2103T IP (by: 18 Sep 2020 2359)
The tracked task has been marked as completed! Congrats~~!
```

### Feature 5
Deletes the specified task *[index]* from the list

## Usage

### Keywords: `delete`

Example of usage:

`delete 1`

Expected outcome:

```
    [T][O] CS2103T
The tracked task has been deleted!
```

### Feature 6
Show all the commands the user can used

## Usage

### keywords: `show`

Example of usage:

`show`

Expected outcome:

```
list: to show all existing tasks.
bye: to exit the todo bot.
delete [task index]: to delete the selected task from the todolist.
todo [task name]: to add the todo task into the list.
deadline [task name] /by [dd-MM-uuuu HHmm]: add a deadline task with the specific time and date.
event [task name] /at [dd-MM-uuuu HHmm]: add a event task with the specific period.
done [task index]: to mark the specific task as completed.
filter [d-MM-uuuu]: to show all the tasks with this date.
find [keyword]: to find all tasks with that has the keywords in its name.
view [d-MM-uuuu]: to view the tasks in order on that day
```

### Feature 7
List all currently tracked task and show their relevant information

## Usage

### Keywords: `list`

Example of usage:

`list`

Expected outcome:

```
These are your current tasks!
1. [D][O] CS2103T IP (by: 18 Sep 2020 2359)
2. [E][X] CS2103T IP (by: 19 Sep 2020 2359)
```

### Feature 8
View all the task on a specific date, *[date]*, and the tasks are arranged in order of the time

## Usage

### Keywords: `view`

Example of usage:

`view 18-09-2020`

Expected outcome:

```
Here are your tasks on this date!
    1. [D][X] CS2103T IP (by: 18 Sep 2020 1200)
    2. [D][X] CS2103T IP (by: 18 Sep 2020 2359)
```

### Feature 9
Search of all tasks with this name *[name]*

## Usage

### Keywords: `find`

Example of usage:

`find CS2103T`

Expected outcome:

```
Here are your tasks with this keywords!
    1. [T][X] CS2103T
    2. [D][X] CS2103T IP (by: 18 Sep 2020 2359)
```

### Feature 10
Filter all the tasks and only show those that have the same *[date]*

## Usage

### Keywords: `filter`

Example of usage:

`filter 18-09-2020`

Expected outcome:

```
Here are your tasks on this date!
    1. [D][X] CS2103T IP (by: 18 Sep 2020 2359)
```

### Feature 11
Terminates and closes the program

## Usage

### Keywords: `bye`

Example of usage:

`bye`

Expected outcome:

```
Awww, leaving so soon? Hope to see you again!
```
