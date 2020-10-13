# User Guide

## Usage

### Misc

##### exit program
1. `bye`
2. `b`

### Viewing & querying tasks

##### List task details: id, description, time stamp etc...
1. `list`
2. `l`

##### Search for matches in tasks, return the id
1. `find,<expression>`
2. `f,<expression>`

### Updating tasks

##### Indicate a task is complete
1. `done,<task id>`
2. `do,<task id>`

Examples
```
done,1
do,1
```

##### Delete a task
1. `delete,<task id>`
2. `del,<task id>`

Examples
```
delete,1
del,1
```

### Creating tasks

##### Create todo
1. `todo,<task name>`
2. `t,<task name>`

Examples
```
todo,CS2100 lab
t,CS2100 Lab 
```

##### Add a task with a deadline
1. `deadline,<task name> <dd-MM-yyyy>`
2. `de,<task name>,<dd-MM-yyyy>`

Examples
```
deadline,CS2100 Lab,12-04-2020
de,CS2100 Lab,12-04-2020
```
 
##### Add an event
1. `event,<task name>,<dd-MM-yyyy>`
2. `e,<task name>,<dd-MM-yyyy>`

Examples
```
event,CS2100 Lab,12-04-2020
e,CS2100 Lab,12-04-2020
```