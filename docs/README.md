# User Guide

## Features 

### Add Tasks 
You can add 3 types of tasks: Deadlines, Events and Todo.
1. Adding a **Deadline** Task:
    * Adds a task which has to be completed by a specific deadline date.
    * Usage: `deadline description /by dd/mm/yyyy (hhhh)`
    * Constraints:
      * The description cannot be empty
      * The deadline date must be in dd/mm/yyyy format, eg. 24/08/2020
      * The deadline time must be in 24 hour format, eg. for 11:59 pm input must be 2359
      * The deadline time field is optional. 
    * Example command: 
      * `deadline Submit Report /by 30/09/2020 2359`
    * Expected outcome:
      * `Great! I've added the following task:`<br>
         ` [D][✘] Submit Report (by: Sep 30 2020 11:59 pm)`<br>
         `Now you have 1 task in the list`

2. Adding an **Event** Task:
    * Adds a task for an event scheduled on a specific date.
    * Usage: `event description /at dd/mm/yyyy (hhhh)`
    * Constraints:
      * The description cannot be empty
      * The event date must be in dd/mm/yyyy format, eg. 24/08/2020
      * The event time must be in 24 hour format, eg. for 11:59 pm input must be 2359
      * The event time field is optional. 
    * Example command: 
      * `event Presentation meeting /at 30/09/2020 1500`
    * Expected outcome:
      * `Great! I've added the following task:`<br>
         ` [E][✘] Presentation meeting (at: Sep 30 2020 3:00 pm)`<br>
         `Now you have 2 tasks in the list`
         
3. Adding a **Todo** Task:
    * Adds a todo task which the you want to do.
    * Usage: `todo description`
    * Constraint:
      * The description cannot be empty
    * Example command:
      * `todo Prepare for vacation`
    * Expected outcome:
      * `Great! I've added the following task:`<br>
         ` [T][✘] Prepare for vacation`<br>
         `Now you have 3 tasks in the list`
      
### Listing Tasks
* Lists all the tasks currently stored in the application.
* Usage: `list`
* Example command:
  * `list`
* Expected outcome:
  * `1. [D][✘] Submit Report (by: Sep 30 2020 11:59 pm)`<br>
    `2. [E][✘] Presentation meeting (at: Sep 30 2020 3:00 pm)`<br>
    `3. [T][✘] Prepare for vacation`

### Marking tasks done
* Marks any task as done [✓].
* Usage: `done task_number`
* Constraints:
  * The `task_number` must be a valid task number, i.e. it should be within the range of the indices of the tasks returned by the list command.
* Example command:
  * `done 1`
* Expected outcome: (when `list` returns the above expected outcome)
  * `Great! i've marked this task as done:`<br>
    `  [D][✓] Submit Report (by: Sep 30 2020 11:59 pm)`
 
### Deleting Tasks
* Deletes a task from the ones currently stored in the application based on the index of the task in the list.
* Usage: `delete task_number`
* Constraints:
  * The `task_number` must be a valid task number, i.e. it should be within the range of the indices of the tasks returned by the list command.
* Example command:
  * `delete 1` 
* Expected outcome: (when `list` returns the above expected outcome)
  * `Noted. I've removed this task:`<br>
    ` [D][✘] Submit Report (by: Sep 30 2020 11:59 pm)`
  
### Find tasks
You can search for task in 3 ways: find, findtype and scheduled.
1. Searching for tasks based on **keyword**:
    * Returns tasks from the ones currently stored based on a keyword to search for.
    * Usage: `find keyword`
    * Constraints:
      * The keyword cannot be empty.
      * There can only be a single keyword.
      * The search on the basis of the keyword is case-sensitive, i.e. keyword "presentation" will not match "Presentation".
      * Tasks returned may also contain the keyword as a part of a word, i.e. searching with "sent" will return the task containing "Presentation".
    * Example command:
      * 'find meet`
    * Expected outcome:
      * `1. [E][✘] Presentation meeting (at: Sep 30 2020 3:00 pm)`
      
2. Searching for tasks based on the **task type**:
    * Returns tasks from the ones currently stored based on a task type to search for.
    * Usage: `findtype type`
    * Constraints:
      * The type cannot be empty.
      * Type can be of only 3 kinds - D or E or T (standing for Deadline, Even and Todo respectively).
      * The search on the basis of the type is not case-sensitive, i.e. type "d" will match "D".
    * Example command:
      * 'findtype T`
    * Expected outcome:
      * `1. [T][✘] Prepare for vacation`
      
3. Searching for tasks based on **scheduling details**:
    * Returns tasks from the ones currently stored based on a date of scheduling to search for.
    * Usage: `scheduled dd/mm/yyyy`
    * Constraints:
      * The date cannot be empty.
      * The date must be in dd/mm/yyyy format, eg. 24/08/2020
    * Example command:
      * 'scheduled 30/09/2020`
    * Expected outcome:
      * `1. [E][✘] Presentation meeting (at: Sep 30 2020 3:00 pm)`

### Updating tasks
* Updates the description and/or date and/or time (whichever ones are applicable).
* Usage: `update _task_number_ (desc: new_description) (date: new_date) (time: new_time)`
* Constraints:
  * The `task_number` must be a valid task number, i.e. it should be within the range of the indices of the tasks returned by the list command.
  * At least one of the three fields (description/date/time) must be specified.
  * For task of Todo type, only description must be specified.
  * You can perform upto three updates on any task. If you have more than one update to the same task, ensure the relative orders of the field is maintained, i.e. *desc* before *date* before *time*.
  * If the date is being updated, the new date must also be in dd/mm/yyyy format, eg. 24/08/2020.
  * If the time is being updated, the new time must also be in 24 hour format, eg. for 11:59 pm input must be 2359.
* Example command:
  * `update 1 desc: Presentation date: 29/09/2020 time: 1400`
* Expected outcome: (the example updates the event being used in the above examples)
  *  `Updated task number 1:`<br> `  [E][✘] Presentation (at: Sep 29 2020 2:00 pm)`

### Exiting the application
* Saves all the changes made and exits the application.
* Usage: `bye`

## FAQ

**Q**: How do I transfer my data to another computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous Dobby- Task Manager. The file would be named as "dobbylist.txt".

## Command Summary
* **Add deadline task**: `deadline description /by dd/mm/yyyy (hhhh)`
* **Add event task**: `event description /at dd/mm/yyyy (hhhh)`
* **Add todo task**: `todo description`
* **List tasks**: `list`
* **Mark tasks as done**: `done task_number`
* **Delete tasks**: `delete task_number`
* **Find with keyword**: `find keyword`
* **Find the type**: `findtype type`
* **Find with scheduling**: `scheduled dd/mm/yyyy`
* **Update task**: `update _task_number_ (desc: new_description) (date: new_date) (time: new_time)`
* **Exit application**: `bye`

