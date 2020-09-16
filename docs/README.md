# User Guide

## Features 

### Feature 1: Add tasks
Add tasks(Todo [T], Deadline [D] or Event [E]) to the app.

### Feature 2: List all tasks
List all the tasks in the app and show their task type (Todo, Event or Deadline), task description, time for Deadline
and Event, date for Deadline and Event, and tags for each task if applicable.

### Feature 3: Mark a task as done
Mark the specified task as done.

### Feature 4: Delete a task
Delete the specified task from the app.

### Feature 5: Find tasks
Search through all the tasks in the app and list out the tasks that has matching keyword specified by the user

### Feature 6: Exit the app
Exit the app and close the window.

## Usage

### `todo` - Add a Todo to the app 

Add a Task of todo type to the app.  

(1) Add s single todo task:  

Syntax:  
  
 `todo <description> [#tag]...`

Example of usage: 

`todo do cs2103T quiz #homework`

Expected outcome:
```
Got it. I've added this task:\n   
[T][✘] do cs2103T quiz\n
  Tags: #homework\n
Now you have 1 tasks in the list. o(=*T*=)m
```

(2) Add multiple todo tasks (each Todo task is separated by a `,`):

Syntax:  

`todo <description> [#tag], <description> [#tag], <description> [#tag], ...`

Example of usage:  
`todo do cs2103T quiz #homework, review cs2103T ip #homework#project, watch batman`  

Expected outcome:  
``` 
Got it. I've added this task:\n
[T][✘] do cs2103T quiz\n
  Tags: #homework\n
[T][✘] review cs2103T ip\n
  Tags: #homework #project\n
[T][✘] watch batman\n
Now you have 1 tasks in the list. o(=*T*=)m
```

### `event` - Add an Event to the app  
Add a task of Event type to the app.  

Syntax:  
  
 `event <description> /at <data and time> [#tag...]`  
 Date and time format: `YYYY-MM-DD hh:mm` or `YYYY/MM/DD hh:mm`.

Example of usage: 

`event watch batman /at 2020-12-04 21:00 #movie`

Expected outcome:

```
Got it. I've added this task:
[E][✘] watch batman
  Tags: #movie\n
  (at: 2020-12-04 (Dec 4 2020) 21:00)
Now you have 1 tasks in the list. o(=*T*=)m
```


### `deadline` - Add a Deadline to the app  
Add a task of Deadline type to the app.  

Syntax:  
  
 `deadline <description> /by <data and time> [#tag...]`  
 Date and time format: `YYYY-MM-DD hh:mm` or `YYYY/MM/DD hh:mm`.

Example of usage: 

`deadline do cs2103T quiz /by 2020-12-04 21:00 #homework`

Expected outcome:

```
Got it. I've added this task:
[D][✘] do cs2103T quiz
  Tags: #homework
  (by 2020-12-04 (Dec 04 2020) 21:00)
Now you have 1 tasks in the list. o(=*T*=)m
```

### `list` - List out all tasks in the app  
List out all the tasks in the app.  

Syntax:  
  
 `list`  

Example of usage: 

`list`

Expected outcome:

``` 
o(=*T*=)m\n
Here are the tasks in your list:
1 [T][✘] do cs2103T quiz\n
  Tags: #homework\n
2 [E][✘] watch batman\n
  Tags: #movie\n
  (at: 2020-12-04 (Dec 4 2020) 21:00)\n
3 [D][✘] do cs2103T ip\n
    Tags: #homework #project\n
    (by 2020-12-04 (Dec 04 2020) 21:00)
```
    
### `done` - Mark specified task as done
Mark the specified task as done.  

Syntax: 
  
`done <taskNumber>`

Example of usage:

`done 1` 

Expected outcome:

```
Nice! I've marked this task as done:
[T][✓] do cs2103T quiz
  Tags: #homework
```
  
### `delete` - Delete the specified task
Delete the user specified task in the app.  

Syntax:

`delete <taskNumber>`

Example of usage:

`delete 1` 

Expected outcome:

```
Noted. I've removed this task:
[T][✓] do cs2103T quiz
  Tags: #homework
Now you have 0 tasks in the list. o(=*T*=)m

```
  
### `find` - Find the task with matching keyword
Search through all tasks in the app and list out all the tasks with matching keyword.  

Syntax:

`find <keyword>`

Example of usage:

`find cs` 

Expected outcome:
```
Here are the matching tasks in your list:
1 [T][✓] do cs2103T quiz
  Tags: #homework
2 [D][✘] do cs2103T ip
  Tags: #homework #project
  (by 2020-12-04 (Dec 04 2020) 21:00)
```

  
### `bye` - Exit the app
Exit the app and close the window.  

Syntax:

`bye`

Example of usage:

`bye` 

Expected outcome:  
Exit the app and window closed.