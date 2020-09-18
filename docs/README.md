# User Guide
DukeHub is a GUI (graphical user interface) chat bot based on desktop where you can use to keep track of your various tasks 
and their respective timings. 

##Types of Task
1. *Todo*
    * Task that does does not have a time requirement
2. *Deadline*
    * Task that has a deadline to meet
3. *Event*
    * Task that has a start time        

##Features Summary

Feature                 |  Command 
------------------------|---------
List out all commands   |help
Add Todo Task           |todo {task description}
Add Deadline Task       |deadline {task description} /by {YYYY-MM-DD}
Add Event Task          |event {task description} /at {YYYY-MM-DD}
List out all task       |list
Delete task             |delete {task index}
Find task               |find {task description}
Mark a task as completed|done {task index}
List all completed task in the past week|statistic
         
## Usage

### 1.`help`  
- list out all commands

*Format*  
`help`

*Example of usage:*  
`help`

*Expected outcome:*  
`********************************List of commands********************************`  
 `help`  
 `todo <task description>`  
 `list`  
 `deadline <task description> /by <YYYY-MM-DD> *Optional*<HH:MM>`  
 `event <task description> /at <YYYY-MM-DD> *Optional*<HH:MM>`  
 `find <task index>`  
 `done <task index`  
 `delete <task index>`    
 `statistic`  
 
 ### 2.`todo` 
 - add todo type task to list of task

*Format*  
`todo {task description}`
 
*Example of usage:*   
`todo read book`

*Expected outcome:*   
`Got it. I've added this duke:`  
`[T][X] read book`  
`Now you have 1 task in the list`

 ### 3.`deadline` 
 - add deadline type task to list of task

*Format*  
`deadline {task description} /by {YYYY-MM-DD}`  
`deadline {task description} /by {YYYY-MM-DD} {HH:MM}` *`optional`*
 
*Example of usage:*   
`deadline return book /by 2020-12-29`  
`deadline return book /by 2020-12-29 18:00`

*Expected outcome:*   
`Got it. I've added this duke:`  
`[D][X] return book (by: Dec 29 2020 18:00)`  
`Now you have 2 task in the list`
 
### 4.`event` 
- add deadline type task to list of task
 
*Format*  
`event {task description} /at {YYYY-MM-DD}`  
`event {task description} /at {YYYY-MM-DD} {HH:MM}` *`optional`*
  
*Example of usage:*   
`event book festival /at 2020-12-30`  
`event book festival /at 2020-12-30 19:00`
 
*Expected outcome:*    
`Got it. I've added this duke:`  
`[E][X] book festival (at: Dec 30 2020 19:00)`  
`Now you have 3 task in the list`

### 5.`list` 
- list out all of the tasks
 
*Format*  
`list`
  
*Example of usage:*   
`list`
 
*Expected outcome:*    
`1. [T][X] read book`   
`2. [D][X] return book (by: Dec 29 2020 18:00)`  
`3. [E][X] book festival (at: Dec 30 2020 19:00)`

### 6.`delete` 
- delete a certain task from the list
 
*Format*  
`delete {task index}`
  
*Example of usage:*   
`delete 2`
 
*Expected outcome:*    
`Noted. I've removed this duke:`  
`[E][X] return book (by: Dec 29 2020 18:00)`  

`now you have 2 tasks in the list`

### 7.`find` 
- find all the task that fits the find description
 
*Format*  
`find {task description}`
  
*Example of usage:*   
`find book festival`
`find book fest`
 
*Expected outcome:*    
`1. book festival (at: Dec 30 2020 19:00)`

### 8.`done` 
- mark a task as completed
 
*Format*  
`done {task index}`
  
*Example of usage:*   
`done 1`
 
*Expected outcome:*    
`Nice! I've marked this task as done:`  
`[T][tick] read book`

### 9.`statistic` 
- list out all completed task in the past week
 
*Format*  
`statistic`
  
*Example of usage:*   
`statistic`
 
*Expected outcome:*    
`task completed last week: 5`  
`read book chapter 1`  
`read book chapter 2`  
`read book chapter 3`  
`read book chapter 4`  
`read book chapter 5`  



  
 
 
