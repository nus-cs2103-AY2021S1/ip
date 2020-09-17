# User Guide for ChatboxDuke

## Features

### Feature 1 - deadline record
Users can enter deadline tasks. Users need to enter task name, /by and date in the format of YYYY-MM-DD.


### `deadline` - Command to record deadline tasks. 

Example of usage: 

`deadline ip /by 2020-09-18`

Expected outcome:
    
    _____________________________________________
    Got it. I've added this task:
    [D][✘] deadline ip (by Sep 18 2020)
    Now you have 1 tasks in the list.
    _____________________________________________

### Feature 2 - event record
Users can enter event name. Users need to enter event name, /at and date in the format of YYYY-MM-DD.

## Usage

### `event` - Command to record event.

Example of usage: 

`event winter holiday /at 2020-11-30`

Expected outcome:

`String`
 #####_____________________________________________
Got it. I've added this task:
 #####[E][✘] winter holiday (at Nov 30 2020)
 ##### Now you have 2 tasks in the list.
 #####_____________________________________________
 
 ### Feature 3 - todo record
 Users can enter todo tasks. Users only need to enter task name.
 
 ## Usage
 
 ### `todo` - Command to record todo tasks. 
 
 Example of usage: 
 
 `todo night jog`
 
 Expected outcome:
 
 `String`
  #####_____________________________________________
 Got it. I've added this task:
  #####[T][✘] night jog
  ##### Now you have 3 tasks in the list.
  #####_____________________________________________
  
  ### Feature 4 - list
  Users can see all tasks in his/her list.
  
  ## Usage
  
  ### `list` - provides the list of tasks
  
  Example of usage: 
  
  `deadline ip /by 2020-09-18`
  
  Expected outcome:
  
  `String`
  #####_____________________________________________
  Here are the tasks in your list:
  #####1. [D][✘] deadline ip (by Sep 18 2020)
  #####2. [E][✘] winter holiday (at Nov 30 2020)
  #####3. [T][✘] night jog
  ##### Now you have 3 tasks in the list.
  #####_____________________________________________
  
  ### Feature 5 - delete
   Users can delete a task in list with the index of the task.
    
   ## Usage
    
   ### `delete` - Delete a task
    
   Example of usage: 
    
   `delete 3`
    
   Expected outcome:
   `String`
   #####_____________________________________________
   Noted. I've removed this task:
   #####3. [T][✘] night jog
   ##### Now you have 2 tasks in the list.
   #####_____________________________________________
   
   ### Feature 6 - find
   Users can use keyword to search for matching tasks in the list. The keyword can be incomplete.
    
   ## Usage
    
   ### `find` - find a task
    
   Example of usage: 
    
   `find wint`
    
   Expected outcome:
   `String`
   #####_____________________________________________
   Here are the matching tasks in your list:
   #####2. [E][✘] winter holiday (at Nov 30 2020)
   #####_____________________________________________
   
   ### Feature 7 - done
   Users can mark a task as done.
       
   ## Usage
       
   ### `done` - mark a task as done.
       
   Example of usage: 
   `done 1`
   
    Expected outcome: String
     
   #####_____________________________________________
   #####Nice! I've marked this task as done:
   #####1. [D][✓] ip (by Sep 18 2020)
   #####_____________________________________________

   ### Feature 8 - Exit the program
   Users can exit the program and the task list will be recorded in duke.txt for future usages.
       
   ## Usage
       
   ### `bye` - exit the program
       
   Example of usage: 
   `bye`
   
    Expected outcome: String
     
   #####_____________________________________________
   #####Bye. Hope to see you again soon!
   #####_____________________________________________   
   
   
   
   
   
    
  