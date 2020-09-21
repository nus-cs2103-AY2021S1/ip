# User Guide

## Features 

### 1. Greetings from Duke 
When user first starts the application, Duke will send out a greeting message. 
### 2. Add task
User can add tasks with the help of Duke. There are four kinds of tasks:
1. todo (without date) - tasks without specified dates
2. todo within a period - represents a task that must be completed within a given period
3. event - represents a one-off event that happens on a specific date
4. deadline - represents a task that must be completed before the given deadline
### 3. List existing tasks
User can view all their task entries with 'list' command. All the task entries will be numbered.
### 4. Delete task
User can delete a task entry by specifying its number.
### 5. Status of tasks
Each task entry has a status, indicating whether the task has been completed or not. User can complete a pending task and undo a completed task.
### 6. Find
User can look for a specific task by entering keywords. All related tasks will be displayed.


## Usage

### All dates must be given in the form of 'YYYY-MM-DD'

#### 1. `todo` - enter a todo without date
Duke will record a todo task without any dates. Text after 'todo' is the name of the task.
Example of usage: 

`todo read book`

Expected outcome:

`[T] [✘] read book`

#### 2. `todo /from ... /by ...` - enter a task that must be completed within certain period
Duke will record a todo task that must be completed within certain period. Text after 'todo' is the name of the task. Text after '/from' is the starting date and text after '/by' is the finishing date.
Example of usage: 

`todo assignment /from 2020-09-12 /by 2020-09-30`

Expected outcome:

`[T] [✘] assignment from: 2020-09-12 by: 2020-09-30`

#### 3. `deadline by ...` - enter a task that must be completed before a given date
Duke will record a todo task that must be completed before a given date. Text after 'deadline' is the name of the task. Text after '/by' is the deadline.
Example of usage: 

`deadline return book /by 2020-09-30`

Expected outcome:

`[D] [✘] return book by: 2020-09-30`

#### 4. `event /at...` - enter a task that occurs on a specific date
Duke will record a todo task that occurs on a specific date. Text after 'event' is the name of the task. Text after '/at' is the date of the event.
Example of usage: 

`event meeting /at 2020-09-22`

Expected outcome:

`[E] [✘] meeting  at: 2020-09-22`

#### 5. `list` - shows all past task entries
Duke will list out all the tasks that it recorded. Both finished and unfinished tasks will be shown. Tasks will be ordered by its creation time (i.e. tasks created earlier will be shown at the front). And all tasks will be given a number for users to refer to.
Example of usage: 

`list`

Expected outcome:
```
1. [T] [✘] read book
2. [T] [✘] assignment from: 2020-09-12 by: 2020-09-30
3. [D] [✘] return book by: 2020-09-30
4. [E] [✘] meeting  at: 2020-09-22
```
#### 6. `find` - search for related entries
Duke will show related task entries.
Example of usage:
`find book`
Expected outcome:
```
Here are the matching tasks in your list:
1. [T] [✘] read book
2. [D] [✘] return book by: 2020-09-30
```
#### 7. `delete (task number)` - delete a task entry
Duke will delete the specified task entry
Example of usage:
`delete 1`
Expected outcome:
```
I have deleted the following task for you:
[T] [✘] read book
You now have 3 tasks in your list!
```
#### 8. `done (task number)` - mark a task entry as done
Duke will mark the task entry as done
Example of usage:
`done 1`
Expected outcome:
```
Good job! You have finished this job!
[T] [✓] assignment from: 2020-09-12 by: 2020-09-30
```
