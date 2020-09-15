# User Guide

Duke- the all-in-one friendly task manager :bowtie:

## Main Features
### [Add todo task: ](#add-todo) `todo`
### [Add deadline task: ](#add-deadline) `deadline`
### [Add event task: ](#add-event) `event`
### [List all tasks: ](#list) `list`
### [Mark task as done: ](#done) `done`
### [Delete task: ](#delete) `delete`
### [Find task: ](#find) `find`
### [Help for all commands: ](#help) `help`
### [Bye to exit: ](#bye) `bye`

### Add todo task: `todo` <a href="add-todo"></a>
Add a todo task with user input as the task description. 

Example of usage: <br/>
`todo assignment 7`

Expected outcome: <br />
Got it. I've added this task: <br />
[T][✘] assignment 7 <br />
Now you have 1 task in the list.

### Add deadline task: `deadline` <a href="add-deadlne"></a>
Add a todo task based on user input as the task description. Requires deadline after /by preferably in
the proper format: 
- yyyy/mm/dd or yyyy-mm-dd 
- dd/mm/yyyy or dd-mm-yyyy 
- HHmm or HH.mm a.

Example of usage: <br/>
`deadline buy maple syrup /by 15/9/2020 tonight at 1900`

Expected outcome: <br />
Got it. I've added this task: <br />
[D][✘] buy maple syrup (by: 15/9/2020 tonight at 1900) (Tue, Sep 15 2020 7.00pm) <br />
Now you have 2 tasks in the list.

### Add event task: `event` <a href="add-event"></a>
Add a todo task based on user input as the task description. Requires event time after /at preferably in
the proper format: 
- yyyy/mm/dd or yyyy-mm-dd 
- dd/mm/yyyy or dd-mm-yyyy 
- HHmm or HH.mm a.

Example of usage: <br/>
`event wedding /at 23/9/2020 Fullerton at 8pm`

Expected outcome: <br />
Got it. I've added this task: <br />
[E][✘] wedding (at: 23/9/2020 Fullerton at 8pm) (Wed, Sep 23 2020 8.00pm) <br />
Now you have 3 tasks in the list.

### List all tasks: `list` <a href="list"></a>
Display all tasks and their done status.

Example of usage: <br />
`list`

Expected outcome: <br />
Here are the tasks in your list: <br />
1.[T][✘] assignment 7 <br />
2.[D][✘] buy maple syrup (by: 15/9/2020 tonight at 1900) (Tue, Sep 15 2020 7.00pm) <br />
3.[E][✘] wedding (at: 23/9/2020 Fullerton at 8pm) (Wed, Sep 23 2020 8.00pm) 

### Mark task as done: `done` <a href="done"></a>
Mark the task specified by the input task number as done. 

Example of usage: <br />
`done 1`

Expected outcone: <br />
Nice! I've marked this task as done: <br />
[T][✓] assignment 7 <br />

### Delete task: `delete` <a href="delete"></a>
Delete the task specified by the input task number.

Example of usage: <br />
`delete 1`

Expected outcome: <br />
Noted, I've removed this task: <br />
[T][✓] assignment 7 <br />
Now you have 2 tasks in the list.

### Find task: `find` <a href="find"></a>
Find a task that fully and partially matches the input word.

Example of usage: <br />
`find wed`

Expected outcome: <br />
Here are the matching tasks in your list: <br />
1.[E][✘] wedding (at: 23/9/2020 Fullerton at 8pm) (Wed, Sep 23 2020 8.00pm) 

### Help for all commands: `help` <a href="help"></a>
Display all valid commands.

Example of usage: <br />
`help`

Expected outcome: <br />
Here are all your commands: <br />
list- list all tasks, <br />
todo <description> - add task, <br />
deadline <description> /by <due date> -add task with deadline, <br />
event <description> /at <event date> -add event with date , <br />
date <one filter date> - finds task on specified date, <br />
&nbsp;&nbsp;&nbsp;date can be formatted as : yyyy-mm-dd or dd/mm/yyyy, <br />
&nbsp;&nbsp;&nbsp;time can be formatted as : HHmm or HH.mm a <br />
done <task number> - marks task as done, <br />
find <one filter word> - finds task with specified word, <br />
bye - goodbye! <br />

### Bye to exit: `bye` <a href="bye"></a>
Exit Duke.

Example of usage: <br />
`bye`

Expected outcome: <br />
Bye. Hope to see you again soon!

Back to [Main Features](#features)
