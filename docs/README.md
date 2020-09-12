# User Guide
Duke is a desktop app for managing tasks such as todo, deadlines and events, 
optimized for use via a Command Line Interface (CLI) while having the benefits of a Graphical User Interface (GUI).
With minimal typing, Duke can manage your task faster than traditional GUI apps.

## Features 
1. Add todos : `todo`
2. Add deadlines : `deadline`
3. Add events : `event`
4. Mark tasks as done : `done`
5. List all tasks : `list`
6. Delete task : `delete`
7. Locating keywords in the task list : `find`
8. List all tags : `tags`
9. Exit the application : `bye`

## Usage

### `todo` - Adds a todo task

Adds a todo task to the task list & prints the added task

Example of usage: 

`todo read book`

Expected outcome:

`Got it. I've added this task:
   [T][?] borrow book
 Now you have 1 tasks in the list.`

### `deadline` - Adds a deadline task

Adds a deadline task to the task list & prints the added task

Example of usage: 

`deadline return book /by 2019-10-15`

Expected outcome:

`Got it. I've added this task:
    [D][?] return book (by: Oct 15 2019)
Now you have 2 tasks in the list.`

### `event` - Adds an event task

Adds an event task to the task list & prints the added task

Example of usage: 

`event project meeting /at 2010-08-17`

Expected outcome:

`Got it. I've added this task:
   [E][?] project meeting (at: Aug 17 2010)
 Now you have 3 tasks in the list.`
 
### `done` - Marks a task as done
 
Marks a task as done

Example of usage: 

`done 1`

Expected outcome:

`Nice! I've marked this task as done:
    [?] return book
Here are the tasks in your list:`
  
### `list` - List all tasks
   
List all tasks in the task list

Example of usage: 

`list`

Expected outcome:

`Here are the tasks in your list:
1.[T][?] borrow book
2.[D][?] return book (by: Oct 15 2019)
3.[E][?] project meeting (at: Aug 17 2010)`
    
### `delete` - Deletes a task
 
Deletes a task from the task list

Example of usage: 

`delete 1`

Expected outcome:
 
`Noted. I've removed this task:
[D][?] return book (by: Oct 15 2019)
Now you have 2 tasks in the list.`

### `find` - Search for keywords

Find tasks that matches the keywords being search and prints all tasks with the specified keyword

Example of usage: 

`find book`

Expected outcome:

`Noted. I've removed this task:
  [D][?] return book (by: Oct 15 2019)
Now you have 2 tasks in the list.`
    
 ### `tags` - List down the tags of each task

Finds the tags of each task and list them down

Example of usage: 

`tags 1`

Expected outcome:
    
Here are the tags in your list:
 1. `#cool`
 2. `#lovebooks`
 
  ### `bye` - Exits the application
 
 Greets the user goodbye and leaves the application
 
 Example of usage: 
 
 `bye`
 
 Expected outcome:
     
 `Bye. Hope to see you again soon!`
 