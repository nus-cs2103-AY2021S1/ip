# User Guide

* Quick Start
* Features
    * Add a task with a tag
    * Delete a task
    * Mark a task as done
    * List all tasks
    * Find tasks with a keyword
    * View all tags
    * Find tasks with a tag
    * Exit the Program 

## Quick Start
1. Ensure you have Java 11 or above installed in your Computer.
2. Download the latest Duke.jar.
3. Copy the file to the folder you want to use as the home folder for Duke.
4. Double-click the file, and Duke will run. 

## Features 

### Add a task with a tag
Add 3 types of tasks, namely Todo, Deadline and Event.
#### Usage
1. `todo DESCRIPTION [@TAG]` - Add a Todo task with a tag

   Todo tasks can be used to record tasks with no Date/Time involved.   
   Tagging is optional.

   Example of usage: `todo homework @CS2030`

   Expected outcome:   
   `Got it. I've added this task:    `  
   `[T][X] homework @CS2030`   
   `Now you have 7 tasks in the list. `  

2. `deadline DESCRIPTION /by DD/MM/YYYY HHmm [@TAG]` - Add a Deadline task with a tag

   Deadline tasks can be used to record tasks with a Date/Time as deadline.   
   Tagging is optional.

   Example of usage: `deadline homework /by 20/06/2020 1900 @CS2030`

   Expected outcome:   
   `Got it. I've added this task:   `   
   `[D][X] homework (by: 20 Jun 2020, 1900) @CS2030 `  
   `Now you have 7 tasks in the list. `
   
3. `event DESCRIPTION /at DD/MM/YYYY HHmm [@TAG]` - Add an Event task with a tag

   Event tasks can be used to record tasks with a Date/Time of event.   
   Tagging is optional.

   Example of usage: `event graduation /at 20/06/2020 1900 @CS2030`

   Expected outcome:   
   `Got it. I've added this task:`   
   `[E][X] Graduation (at: 20 Jun 2020, 1900) @CS2030`  
   `Now you have 7 tasks in the list. `

### Delete a task
Delete a task that you no longer want on your task list.

`delete TASK_INDEX` - Delete the task with TASK_INDEX

Example of usage: `delete 3`

Expected outcome:   
`Noted. I've deleted this task:  `  
`[D][X] homework (by: 20 Jun 2020, 1900) @CS2030  `     
`Now you have 6 tasks in the list.`

### Mark a task as done
Mark a task that you have completed as done on your task list.

`done TASK_INDEX` - Mark the task with TASK_INDEX as done

Example of usage: `done 3`

Expected outcome:   
`Nice! I've marked this task as done:  `    
`[D][✓] homework (by: 20 Jun 2020, 1900) @CS2030`

### List all tasks
List out all tasks in your task list.

`list` - list out the task list

Example of usage: `list`

Expected outcome:   
`Here are the tasks in your list:  `    
`1. [D][X] homework (by: 20 Jun 2020, 1900) @CS2030`   
`2. [E][X] holiday (at: 20 Jun 2020, 1900) @hol`

### Find tasks with a keyword
Find the tasks which descriptions contain the given keyword.

`find KEYWORD` - Find the tasks that contain KEYWORD in description

Example of usage: `find book`

Expected outcome:   
`Here are the tasks that matched your search:`    
`1. [D][X] read storybook (by: 20 Jun 2020, 1900)`  
`2. [D][X] return book (by: 20 Jun 2020, 1900)`

### View all tags
List out all the tags in the task list.

`tags` - List the tags in the task list

Example of usage: `tags`

Expected outcome:   
`Here are your tags:`    
`1. CS2030`  
`2. hols`

### Find tasks with a tag
Find the tasks which tags match the given tag.

`tag TAG_NAME` - Find the tasks in the task list that match the tag

Example of usage: `tag CS2030`

Expected outcome:   
`Here are the tasks that matched your search:`    
`1. [D][X] tutorial (by: 20 Jun 2020, 1900) @CS2030`  
`2. [D][X] assignment (by: 20 Jun 2020, 1900) @CS2030`

### Exit the Program
Exits the Program.

Format: `bye` 

