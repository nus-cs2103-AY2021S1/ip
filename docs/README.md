# User Guide

## Features 

### Tasks
3 Different Task types exists, ToDo, Event and Deadline

* ### Deadline
  Use it to add a task involving a deadline(eg submission)
  
  ## User uses it to add a particular deadline
  
  Adds a task involving a deadline to the current list of tasks
  Gives a response saying that it has been added, and gives the current 
  Number of tasks present
  * Syntax: deadline (task name) /by (date and/or time)
  
  Examples of usage:
  
  deadline return book /by 23:59
     
       Got it. I've added this task: 
       [D][✗] return book (by: 23:59)
       Now you have 6 tasks in the list.
  
* ### Event
  Use it to add a task involving a period o time, 
  
  ## User uses it to add a particular deadline(eg competition)
  
  Adds a task that occurs over a period of time, where date and/or time has to be given in the format as shown below
  
  Gives a response saying that it has been added and gives the current 
  Number of tasks present.
  * Syntax: event (event name) /at (start date and/or time)-(end date and/or time)
  
  Examples of usage:
  
   event competition/at 12:00-14:00
  
      Got it. I've added this task: 
      [D][✗] return book (at: 23:59)
      Now you have 6 tasks in the list.
* ###ToDo
  This is to add a normal task without a need to be completed by a certain time frame
  
  ## User uses it to keep reminder of tasks without a specific time frame
  
  After this is added, a message saying that the todo task is added and the current number 
  Of tasks is printed
  
  * Syntax: todo (todo name)
  Example of Usage:
  todo read book
  
        Got it. I've added this task:
        [T][✗] read book
        Now you have 3 tasks in the list.


### Delete
Use it to delete a specific task

## User uses it to to delete a task

### ‘delete’ - deletes specific task based on the number given 

Deletes a task provided based on the number provided after delete, where the 
Number provided should coincide with the ordering given in the list
If delete is absent or number given is more than the number of items available 
Error is printed
* Syntax: delete (number)

Examples of usage:

delete 3 (if at least 3 tasks in the list)

        Noted. I've removed this task: 
        [E][✗] project meeting (at: Aug 6th 2-4pm)
        Now you have 4 tasks in the list.


### Find
Used to find tasks containing the words given by user
Allows user to search for tasks with specific words

##User uses it to search for tasks

Finds out tasks using their names and the tasks containing all the words given are given
* Syntax: find (words you wish to search for)

Examples of usage: 
 
find book

    Here are the matching tasks in your list:
      1.[T][✓] buy book

 ### Done
Use it to mark a task as done, once a task is completed

##User uses it to mark a task as done, to keep track of tasks done

Marks the task at the number(input by user) as done and returns a string representing it
* Syntax: done (ID as number)

Examples:

done 2

      Nice! I've marked this task as done:
      [E][✓] concert  (at: 12:00-14:00)


### List
Lists out all the current tasks that the user has

##User can use it to check and keep track of 
    current tasks she has, and how many she has completed and how many she has uncompleted

The tasks are given in order in which it has been added and tick represents completed tasks
Cross represents undone tasks

* Syntax: list

Examples:

     1. [T][✓] buy book
     2. [E][✓] concert (at: 12:00-14:00)
     3. [T][✗] read book
     4. [T][✗] read book

### Exit
Used to exit app

##After this one more input would lead to the app to close immediately

After this, a bye message would be printed, then another input causes the app to close immediately.

* Syntax: bye

Examples of usage:
Bye
 
      Bye. Hope to see you again soon!

###ShortCuts
This is to make app more user friendly so that users do not have to type much

## User can use existing short forms or define own short forms

ShortForms-Original
 * b - Bye
 * t - todo
 * e - event
 * s - short
 * l -list
 * de - deadline 
 * do - done
 * d - delete
 * f - find
You can also define your own short forms using syntax shown below:
short (original) (short form)
* Syntax: (short form) (same format as original excluding key word)

Example:
b

    Bye. Hope to see you again soon!

You can also define your own short cuts

##You can use a short cuts, that is user defined

Syntax: short (original form) (short form)

Example:
by
     
     short cut successfully added
     
Example of using user defined short cut:
by

     Bye. Hope to see you again soon!

### Random Command
If you insert none of the above commands a random command would be given

Example: blah

    '☹ OOPS!!! I'm sorry, but I don't know what that means :-(