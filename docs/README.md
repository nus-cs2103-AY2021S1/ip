# User Guide

## Features 

1. `<todo>`       : Add Task
1. `<deadline>` : Add Deadline
1. `<event>`    : Add Event
1. `<delete>`   : Delete task
1. `<done>`     : Mark task as doone
1. `<list>`     : List all tasks
1. `<find>`     : Find task that matches the keyword

### `<todo>` -Used to add a new task

Example of usage:  
`todo borrow book`   
Expected outcome:  
`Added New Task [T][✘] borrow book`

### `<deadline>` -Used to add a new deadline

Example of usage:    
`deadline return book /by 29/08/2020 1500`   
Expected outcome:  
`Added New Task [D][✘] return book (by:29/08/2020 15:00)`

### `<event>` -Used to add a new event

Example of usage:  
`event study /by 29/08/2020 1500`   
Expected outcome:  
`Added New Task [E][✘] study (at:29/08/2020 15:00)`

### `<delete>` -Used to delete a task

Example of usage:    
`delete 1`   
Expected outcome:  
`This task has been deleted. [D][✘] return book (by:29/08/2020 15:00)`

### `<delete>` -Used to mark a task as done

Example of usage:    
`done 1`   
Expected outcome:  
`This task has been mark as done. [D][✘] return book (by:29/08/2020 15:00)`

### `<list>` -Used to view all tasks

Example of usage:    
`list`   
Expected outcome:  
`1. [T][✘] borrow book`  
`2. [D][✘] return book (by:29/08/2020 15:00)`  
`3. [E][✘] study (at:29/08/2020 15:00)`  

### `<find>` -Used to find a task that matches the keyword

Example of usage:    
`find book`   
Expected outcome:  
`1. [T][✘] borrow book`  
`2. [D][✘] return book (by:29/08/2020 15:00)`