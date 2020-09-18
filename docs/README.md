# User Guide

## Features 

### Feature 1: add tasks
There are 3 types of tasks that can be added.
1. a todo task: a task that has no deadline
2. a deadline task: a task that has reminder on deadline
3. a event task: a task that will happen at a timing specified
   
### Feature 2: list
The list of task will be recorded and shown when commanded.

### Feature 3: done tasks
When done with a task, users can indicate that they have done a particular task.

### Feature 4: delete tasks
Users can delete a task

### Feature 5: find tasks
Users can find all tasks that contain specified keyword.

### Feature 6: snooze tasks
Users can edit timing of a particular task without deleting it.

## Usage

### `todo (description of tasks)` - add a todo task

This adds a todo task to task list. If user did not state descriptions, there will be reminder suggesting that a todo task cannot be empty.

Example of usage: 

`todo borrow books`

Expected outcome:

`Got it. I've added this task:
  [T][✘] borrow books
 Now you have 1 tasks in the list`

### `deadline (description of tasks) /by (date in yyyy-MM-dd formatt)` - add a deadline task

This adds a deadline task to task list. If user did not state descriptions, there will be reminder suggesting that a deadline task cannot be empty.

Example of usage: 

`deadline return books /by 2020-09-18`

Expected outcome:

`Got it. I've added this task:
  [D][✘] return books (by: Sep 18 2020)
 Now you have 2 tasks in the list`
 
 ### `event (description of tasks) /at (date in yyyy-MM-dd formatt)` - add an event task
 
 This adds an event task to task list. If user did not state descriptions, there will be reminder suggesting that an event task cannot be empty.
 
 Example of usage: 
 
 `event read books /at 2020-09-15`
 
 Expected outcome:
 
 `Got it. I've added this task:
   [E][✘] read books (at: Sep 15 2020)
  Now you have 3 tasks in the list`
  
  ### `list` - list out all current tasks
  
  This lists out all tasks in the task list.
  
  Example of usage: 
  
  `list`
  
  Expected outcome:
  
  `Here are the tasks in your list:`
  
  `1.[T][✘] borrow books`
  `2.[D][✘] return books (by: Sep 18 2020)`
  `3.[E][✘] read books (at: Sep 15 2020)`
  
  ### `done (index)` - denote a task as done
    
  The task with specified index in the list will be marked as done.
    
  Example of usage: 
    
   `done 1`
    
  Expected outcome:
    
   `Congrats! you are done with task 1`
  and if you list again, it would look like this:
  `Here are the tasks in your list:`
    
   `1.[T][✓] borrow books`
   `2.[D][✘] return books (by: Sep 18 2020)`
   `3.[E][✘] read books (at: Sep 15 2020)`
    
  ### `delete (index)` - delete a specified task
      
   The task with specified index in the list will be deleted.
      
   Example of usage: 
      
   `delete 1`
      
   Expected outcome:
      
   `Noted. I've removed this tasks:`
   `[T][✓] borrow books`
   `Now you have 2 tasks in the list.`
   
  ### `find (keyword)` - finds a task with specified keyword
         
   The task(s) with specified keyword in the list will be listed.
         
   Example of usage: 
         
   `find return`
         
   Expected outcome:
         
   `1.[D][✘] return books (by: Sep 18 2020)`
  
  ### `snooze (index) (new timing)` - snooze a task of specifed index with a new timing
           
  The timing of the task with specified index in the list will be snoozed if it is a deadline or event task.
           
  Example of usage: 
           
  `snooze 1 2020-10-01`
           
  Expected outcome:
           
  `Okay! You have snoozed task 1 to Oct 01 2020`
  
  ### `bye` - exits the program
             
  The program will be exited
             
  Example of usage: 
             
  `exit`