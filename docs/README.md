# User Guide for Duke



### Features 



### Add Task 
Add tasks to Duke. There are todo, deadline and event tasks.

## Usage

`todo [String]` - Add Todo Task

Adds a new Todo task with name String.



Example of usage: 

`todo read book`

Expected outcome:

```
 Added Task:
 [T][X] read book
```



`deadline [String] /by [dateTimeString]` - Add Deadline Task

 Adds a new Deadline task with name String, and deadline dateTimeString.



 Example of usage: 

 `deadline finish 2103 /by 12/09/2020 1500`

 Expected outcome:

```
 Added Task:
 [D][x] finish 2103 (by: Sep 13 2020 1500)
  
```



`event [String] /at [dateTimeString]` - Add Event Task

   Adds a new Event task with name String, and time dateTimeString.



   Example of usage: 

   `event attend meeting /at 12/09/2020 1500`

   Expected outcome:

```
   Added Task:
   [E][x] attend meeting (at: Sep 13 2020 1500)
```



## Complete Task
Completes a task that has already been created.

### Usage

`done [int]` - Complete task at int position of user's task

Marks the int task as done.



Example of usage: 

`done 1` - Marks the first task in the list as done.

Expected outcome:

```
Nice! I've marked this task as done: 
 [D][✓] finish 2103 (by: Sep 13 2020 1500)
```



### List
 Displays list of tasks.

 ## Usage

`list`

 Expected outcome:

```
 Here are your current tasks:
  1.[T][✓] eat dinner
  2.[T][x] wash clothes
 
```



### Bye
Saves list of tasks and exits bot.

## Usage

`bye`

Expected outcome:

`Bye! Message me anytime!!`