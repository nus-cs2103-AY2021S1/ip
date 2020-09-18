# User Guide

## Features 
The DukeBot can help you manage your tasks conveniently. There are 3 tasks for you to choose from:
* Deadline (by a time)
* Event (at a time or can happen in a period of time)
* Todo

You can also list all the task you are having, mark them as done or delete them. 

All of your data will also be stored and load again next time you start the app!

## Usage
**Note** All time can be in any of the following format :
```
"yyyy-MM-dd"
"dd-MM-yyyy"
"yyyy/MM/dd"
"dd/MM/yyyy"
"MMM d yyyy"
"d MMM yyyy"
```

### `event <description> /at <time>` 
Add an event that will happen at a specified time

Example of usage: 
`event Roland Garros finals /at 09/06/2019`

Outcome example:
```
Got it. I've added this task:
[E][✗] Roland Garros finals (at: Sep 6 2019)
Now you have 2 tasks in the list.
```

### `event <description> /between <time> and <time>` 
Add an event that will happen at in a period of time

Example of usage: 
`event Roland Garros /between 09/06/2019 and 09/08/2019`

Outcome example:
```
Got it. I've added this task:
[E][✗] Roland Garros (between: Jun 9 2019 and Aug 9 2019)
Now you have 2 tasks in the list.
```

### `deadline <description> /by <time>` 
Add a deadline that should be finished by a specified time

Example of usage: 
`deadline return book /by 2019-10-15`

Outcome example:
```
Got it. I've added this task:
[D][✗] return book (by: Oct 15 2019)
Now you have 3 tasks in the list.
```

### `todo <description>` 
Add a todo

Example of usage: 
`todo return book`

Outcome example:
```
Got it. I've added this task:
[T][✗] return book
Now you have 3 tasks in the list.
```

### `list` 
List all tasks you are having

Example of usage: 
`list`

Outcome example: 
```
Here are the tasks in your list:
1.[D][✓] sample (by: Oct 15 2019)
2.[E][✓] sample (between: Oct 15 2019 and Oct 16 2019)
3.[D][✗] sample (by: Oct 15 2019)
```

### `done <task_id>` 
Mark a task with the specified task_id as done

Example of usage: 
`done 1`

Example outcome:
```
Nice! I've marked this task as done:
[D][✓] sample (by: Oct 19 2020)
```

### `delete <task_id>` 
Delete the task that has the specified task_id

Example of usage: 
`delete 1`

Outcome example:
```
Noted. I've removed this task:
[D][✗] sample (by: Oct 15 2019)
```

### `bye` 
To quit the program

Example of usage: 
`bye`


