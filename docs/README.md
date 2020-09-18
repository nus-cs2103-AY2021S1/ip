# User Guide

### Table of Contents
<a href="#1"> 1. What is Duke? </a>
<br/>
<a href="#2"> 2. Features </a>
<br/>
<a href="#3"> 3. Setting Up </a>
<br/>
<a href="#4"> 4. Commands </a>

## <a id="1">1. What is Duke?</a> 
Duke is a task management bot that can keep track of your daily tasks and manage them.

## <a id="2">2. Features</a> 
1. **Add a task**
<br/>
    * Types of tasks:
        1. `todo`: A task to be done later
        2. `event`: A task with the time to do it 
        3. `deadline`: A task with a deadline
    
2. **Delete a task**
<br/>
    * `delete` a task from the existing list of tasks.
   
3. **List the tasks**
<br/>
    * `list` the existing list of tasks.
    
4. **Mark a task as done**
<br/>
    * Mark a task as `done` after you have done it.
 
5. **Find tasks**
<br/>
    * `find` tasks that contain the keyword. 
    
6. **Add a note**
<br/>
    * Add a `note` to the existing list of notes.
    
7. **Delete a note**
<br/> 
    * `delnote`: deletes a note that you do not want to keep anymore.
    
8. **List the notes**
<br/>
    * `listnote`: lists the existing notes.
    
9. **Get the description of a note**
<br/>
    * `description`: the content of a certain note.

## <a id="#3">3. Setting Up</a>

    * Download the jar file.
    * Open the jar file downloaded and you can start using it.
    
## <a id="#4">4. Commands</a>
Command | Result
---------------- | ----------------
`todo` [Description] | New todo task added to the task list with the tag `[T]`
`event` [Description] /at [Time(YYYY/MM/DDTHH:MM)] | New event added to the task list with the tag `[E]`
`deadline` [Description] /by [Time(YYYY/MM/DDTHH:MM)] | New deadline added to the task list with the tag `[D]`
`delete` [Index] | Delete the task with the index
`list` | List the existing tasks on the task list
`done` [Index] | Mark the task with the index as done
`find` [Keyword] | Find the tasks which contains the keyword
`note` [Title] /d [Description] | Create a note with a title and description then add it to the note list.
`delnote` [Index] | Delete the note with the index.
`listnote` | List the notes on the note list.
`description` [Index] | Get the description of the note with the index.