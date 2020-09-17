# User Guide

Boo Task Manager is a desktop app for managing tasks. This is a CS2103T individual project. 

* Features
    * [Adding a task: add](#adding-a-task-add)
    * [Listing all tasks: list](#listing-all-tasks-list)
    * [Marking a task complete: done](#marking-a-task-complete-done)
    * [Delete a task: delete](#delete-a-task-delete)
    * [Search for tasks: find](#search-for-tasks-find)
    * [Archive a task: archive](#archive-a-task-archive)
    * [Exiting the program: bye](#exiting-the-program-bye)
    * [Error handling](#error-handling)
    * [Saving the data](#saving-the-data)
* [FAQ](#faq)
* [Command Summary](#command-summary)
    
##Features 

###Adding a task: add
Users are able to add 3 types of tasks to the application.
1. Todo
1. Deadline
1. Event

Adding a todo task: `todo <todo description>`

Example of usage:

`todo Do laundry`

Expected outcome:

```
Got it. I've added this task:
[T][✘] Do laundry
Now you have 1 tasks in the list.
```
Adding a deadline task: `deadline <deadline description> /by dd/MM/yyyy HH:mm`

Example of usage:

`deadline Do assignment /by 23/09/2020 23:59`

Expected outcome:

```
Got it. I've added this task:
[D][✘] Do assignment (by: 23 Sep 2020 23:59)
Now you have 2 tasks in the list.
```

Adding an event task: `event <event description> /at dd/MM/yyyy HH:mm`

Example of usage:

`event Sam's birthday party /at 26/09/2020 19:00`

Example of outcome:

```
Got it. I've added this task:
[E][✘] Sam's birthday party (at: 26 Sep 2020 19:00)
Now you have 3 tasks in the list.
```

###Listing all tasks: list
Users are able to view all the tasks in the list.

Example of usage:

`list`

Example of outcome:
```
Here are the tasks in your list:
1.[T][✘] Do laundry
2.[D][✘] Do assignment (by: 23 Sep 2020 23:59)
3.[E][✘] Sam's birthday party (at: 26 Sep 2020 19:00)
```

###Marking a task complete: done
Users are able to mark a task as complete once they have finished the task.
The task number refers to the number in the list.

Marking a task as done: `done <task number>`

Example of usage:

`done 1`

Example of outcome:
```
Nice! I've marked this task as done: 
[T][✓] Do laundry
```

###Delete a task: delete
Users are able to delete a task from the list.
The task number refers to the number in the list.

Deleting a task: `delete <task number>`

Example of usage:

`delete 2`

Example of outcome:
```
Noted. I've removed this task:
[D][✘] Do assignment (by: 23 Sep 2020 23:59)
Now you have 2 tasks in the list.
```

###Search for tasks: find
Users are able to search for tasks in the list matching the keywords.

Finding a task: `find <keywords>`

Example of usage:

`find birthday`

Example of outcome:

```
Here are the tasks in your list:
1.[E][✘] Sam's birthday party (at: 26 Sep 2020 19:00)
```

###Archive a task: archive
Users are able to archive tasks. These archived tasks will be saved into an archive file.
The task number refers to the number in the list.

Archive a task: `archive <task number>`

Example of usage:

`archive 1`

Example of outcome:

```
Noted. I've archived this task:
[T][✓] Do laundry
Now you have 1 tasks in the list.
```

###Exiting the program: bye
Exits the program.

Example of usage:
`bye`

Example of outcome:
```
Bye. Hope to see you again soon!
```

###Error handling
Should users key in any command in the wrong format, the application will inform the user of the error.
An example below illustrates what will happen if the user does not key in the task description for a todo
task.

Example of usage:

`todo`

Example of outcome:

```
☹ OOPS!!! The description of a todo cannot be empty.
```

###Saving the data
The data is saved automatically after any command that changes the data. 
There is no need to manually save.

##FAQ
Q: How do i transfer my data from one computer to another?

A: Run the application on the computer and overwrite the empty data file with your data file from 
your previous computer.

##Command Summary
Action | Format, Examples
------------ | -------------
Add | eg. todo do laundry <br> eg. deadline assignment /by 23/09/2020 23:59 <br> eg. event birthday /at 23/09/2020 19:00
List | list
Done | done TASK NUMBER <br> eg. done 1
Delete | delete TASK NUMBER <br> eg. delete 2
Search | find KEYWORDS <br> eg. find do homework
Archive | archive TASK NUMBER <br> eg. archive 4
Exit | bye



