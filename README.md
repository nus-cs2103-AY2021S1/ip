# DUKE User Guide
## Features 

1. [list](#list---returns-the-list-of-tasks-in-the-task-list)

1. [todo](#todo---adds-a-todo-into-the-task-list)

1. [deadline](#deadline---adds-a-deadline-into-the-task-list)

1. [event](#event---adds-an-event-into-the-task-list)

1. [done](#done---completes-the-task-at-the-specified-position)

1. [delete](#delete---deletes-the-task-at-the-specified-position)

1. [find](#find---finds-tasks-in-the-task-list-that-contains-the-keyword)

1. [help](#help---returns-a-list-of-commands-and-their-uses)

1. [bye](#bye---saves-the-task-list-into-a-txt-file-and-terminates-the-program)

## Usage

### Features
#### `list` - Returns the list of tasks in the Task List

Example of usage: 

    `list`

Expected outcome:

    Here are the tasks in your list:
     1. [E][✔] birthday (at: 09 Oct 2020 1800)`
     2. [T][✘] finish iP
     3. [E][✘] birthday (at: 14 Sep 2020 0900)
     4. [T][✘] eat dinner!
 
 
 
 
#### `todo` - Adds a TODO into the Task List
 
Example of usage: 
 
    `todo read a good book`
 
Expected outcome:
 
    Now you have a new task! :
    [T][✘] read a good book
    Type 'list' to check your Tasklist
 
#### `deadline` - Adds a deadline into the Task List
 
Example of usage: 
 
    `deadline finish iP /by 2020-09-18 0000`
 
Expected outcome:
 
    Now you have a new task! :
    [D][✘] finish iP (by: 18 Sep 2020 0000)
    Type 'list' to check your Tasklist
    
#### `event` - Adds an event into the Task List
 
Example of usage: 
 
    `event Mom's Birthday /at 2020-10-10 1800`
 
Expected outcome:
 
    Now you have a new task! :
    [E][✘] Mom's Birthday (at: 10 Oct 2020 1800)
    Type 'list' to check your Tasklist
    
#### `done` - Completes the task at the specified position
 
Example of usage: 
 
    `done 6`
 
Expected outcome:

If task was not done before:

    Nice! This task is getting done!!
    [✔] finish iP

If task was done before:

    This task is already done!
    [✔] finish iP (by: 18 Sep 2020 0000)
    
#### `delete` - Deletes the task at the specified position
 
Example of usage: 
 
    `delete 6`
 
Expected outcome:

If index in range:

    The following Task is removed from the TaskList!!
    [✔] finish iP

If index not in range:

    You do not have 6 tasks!
    Choose a number less than equals to (size of tasklist)!
    
#### `find` - Finds tasks in the Task List that contains the keyword
 
Example of usage: 
 
    `find birthday`
 
Expected outcome:

If task found with keyword:

    There were 2 tasks found
    1.[E][✔] birthday (at: 09 Oct 2020 1800)
    2.[E][✘] birthday (at: 14 Sep 2020 0900)
    3.[E][✘] Mom's Birthday (at: 10 Oct 2020 1800)

If no tasks found with keyword:

    There are no tasks found in this find!
    
#### `help` - Returns a list of commands and their uses
 
Example of usage: 
 
    `help` OR
    `help todo`
 
Expected outcome:

If help without command:

    Enter "help" followed by the command returns the specific instructions for the command
    e.g help OR help todo
    -bye
    -deadline
    -delete
    -done
    -event
    -find
    -help
    -list
    -todo

If help with a command:

    todo - Adds a TODO into the Task List
    Enter "todo" followed by a spacing before entering the task
    e.g todo read a book
    
    
#### `bye` - Saves the Task List into a txt file and terminates the program
 
Example of usage: 
 
    `bye`
 
Expected outcome:

    Bye! Hope to see you soon again?!
    
    
#Thank you for reading!