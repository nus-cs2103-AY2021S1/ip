# User Guide
Duke is a Personal Assistant Chatbox that can help you keep track of tasks 
and deadlines and manage your list of contacts. It uses a graphical user interface and
command line instructions to interact with the users.
## Features 
1. Add a new task to the task list
2. Mark an existing task as completed
3. Delete a task from your task list
4. Search for tasks using keywords
5. Save the tasks added to your hard disk
6. Display the existing list of tasks
7. Add a new friend to the friend list
8. Delete an existing friend from your friend list
9. Display the existing list of friends

### Feature 1
Add a new task to the current task list. A new task can be a ToDo, Deadline 
(with a fixed deadline) or an event. 

### Feature 2
Mark an existing task in the task list as completed

### Feature 3
Delete a task from the current task list

### Feature 4
Search through the task list to find tasks with matching keywords. All tasks which contain the 
keywords will be shown.

### Feature 5
Save the new tasks added to your hard disk. Everytime when you relaunch the application, you can 
see your tasks added previously.

### Feature 6
Display the whole list of tasks including those saved in your hard disk
### Feature 7
Add a new friend to the current friend list
### Feature 8
Delete a friend from the current friend list
### Feature 9
Display an entire list of friends
## Usage

### `todo <description of task>` - create a todo task

Add a todo task in the current task list

Example of usage: 

`todo shower`

Expected outcome:
```
Got it. I've added this task:
[T][✗] Learn Java`
Now you have 1 task on the list.
```
### `deadline <description of task> /by <deadline in the format of DD/MM/YYYY HHmm>` - Describe action

Add a deadline task with its deadline in the current task list.

Example of usage: 

`deadline project paper /by 12/12/2020 1234`

Expected outcome:
```
Got it. I've added this task:
[D][✗] project paper (by: 12/12/2020 12:34)`
Now you have 2 tasks on the list.
```
### `event <description of task> /at <date/time of the event>` - Describe action

Add an event task with its date or time into the current task list.

Example of usage: 

`event cello class /at Monday 2-4pm`

Expected outcome:

```
Got it. I've added this task:
[E][✗] cello class (at: Monday 2-4pm)`
Now you have 3 tasks on the list.
```
### `list` - list all existing tasks in the task list

List all the tasks in the task list

Expected outcome:
```
Here are the tasks in your list:
1. [T][✘] test 123
2. [D][✘] test 456 (by: Dec 12 2020 12:19)
3. [D][✘] test again (by: Dec 12 2020 19:12)
4. [E][✘] meeting (at: 2-3pm)
5. [T][✘] shower
```
### `done <index>` - Mark a task as completed

Identify the index of the task you wish to mark as completed using `list`
and then mark it as done

Example of usage: 

`done 2`

Expected outcome:

```
Nice! I've marked this task as done:
[D][✓] test 456 (by: Dec 12 2020 12:19)
```
### `delete <index>` - Delete the task from the task list

Delete the task from the task list according to its index within the list

Example of usage: 

`delete 5`

Expected outcome:
```
Noted. I've removed this task:
[T][✘] shower
```
### `find <keyword>` - Search for tasks in the task lists

Find all matching tasks in the task lists using the keyword

Example of usage: 

`find test`

Expected outcome:

```
Here are the tasks in your list:
1. [T][✘] test 123
2. [D][✘] test 456 (by: Dec 12 2020 12:19)
3. [D][✘] test again (by: Dec 12 2020 19:12)
```
### `friend /name <name> /number <phone number> /isClose <boolean>` - Add a friend in your friend list

Add a new friend with information of the name, phone number, closeness of your relationship to the friend
list

Example of usage: 

`friend /name Charles /number 91234567 /isClose true`

Expected outcome:

```
Added a new friend:
Charles (number: 91234567, isClose: true)
Now you have 1 friend in the list
```
### `fdelete <index>` - Delete a friend in the friend list

Delete a friend in the friend list based on its index in the list

Example of usage: 

`fdelete 1`

Expected outcome:

```
Noted. I've unfriend Charles (number: 91234567, isClose: true)
```
### `flist` - List out all friends in your friend list

List all your friends in your current friend list. Note that friend list is not saved to the hard disk.

Expected outcome:

```
Here are all your friends:
1. Charles (number: 91234567, isClose: true)
```