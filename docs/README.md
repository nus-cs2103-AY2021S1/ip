# User Guide - GuDukeTama

Welcome to the User Guide for the chatbot GuDukeTama! 
This is a greenfield project for CS2103 iP (AY20/21 Semester 1).

# Features

#### Help
Displays all instructions available to the user.

#### Creating Tasks 
Users can create tasks that are of types: todo, deadline or event.  
todo tasks only has a description, 
while deadline and events store description and date of the task.

#### Mark Tasks as done
After completing the task, user can mark them as done.

#### Deleting Tasks
User can delete unwanted Tasks.

#### Listing Tasks
Users can get a list of all their tasks.

#### Finding Tasks
Using a keyword, users can search their for tasks containing the keyword.

#### Sorting Tasks
Users can sort their tasks in chronological order.  
Todo Tasks without a date will be placed at the end of the list.

#### Data Management
Data is saved whenever the user exits the app with the command 'bye'
and automatically loaded when the user starts up the app.

# Usage

#### `help` - Displays all instructions
Displays all the available instructions to the user.

Example of usage: 
`help`

Expected Outcome:
>AVAILABLE INSTRUCTIONS:  
> help - Display Available Instructions  
  bye - Terminate Duke  
  list - Display current DukeTasks  
  done [Task Number] - Complete the specified task number  
  delete [Task Number] - Deletes the task number  
  find [keyword] - Finds related Tasks containing the keyword  
  sort [tag] - Sorts the tasks with the tag ("all", "deadline" or "event")     
  todo [Task Description] - Inputs a TODO DukeTask  
  deadline [Task Description] /by [Date]  
  event [Task Description] /at [Date]  
> 
> FORMAT FOR DATE: "DD/MM/YYYY hh/mm/ss"  
  DD, MM, YYYY are the date, month and year respectively  
  hh, mm, ss are the hour, minutes and seconds respectively   
>(24 HOUR NOTATION)  

<br>


#### `todo [description]` - Creates a Todo Task
Creates a Todo Task and adds it into the Task List

Example of usage: 
`todo homework`

Expected Outcome:
>Task Added:
>
>[T][✘] homework
>
>...*Yawns*... You have 11 tasks
>I'll go back to my nap...please finish them so-...zzz...  
  
<br>

#### `deadline [description] /by [date]` - Creates a Deadline Task
Creates a Deadline Task and adds it into the Task List

Example of usage: 
`deadline homework /by 10/09/2020 12/00/00`

Expected Outcome:
>Task Added:
>
>[D][✘] homework (by: 10 Sep 2020 12:00:00 PM)
>
>...*Yawns*... You have 12 tasks
>I'll go back to my nap...please finish them so-...zzz...


<br>

#### `event [description] /at [date]` - Creates an Event Task
Creates a Event Task and adds it into the Task List

Example of usage: 
`event lesson /at 10/09/2020 12/00/00`

Expected Outcome:
>Task Added:
>
>[E][✘] lesson (at: 10 Sep 2020 12:00:00 PM)
>
>...*Yawns*... You have 12 tasks
>I'll go back to my nap...please finish them so-...zzz...

<br>

#### `done [number]` - Marks task as done
Marks the task at [number] to be done

Example of usage: 
`done 2`

Expected Outcome:
>Oh...you're done? I'll mark it down for you...
>
>[E][✓] dinner (at: 12 Dec 2020 19:06:00 PM)
>
>...*Yawns*... You have 13 tasks  
>I'll go back to my nap...please finish them so-...zzz...


<br>

#### `delete [number]` - Deletes task
Deletes the task at [number]

Example of usage: 
`delete 2`

Expected Outcome:
>Fine...I'll get rid of that task for you...
>...but it's tiring so I'm not gonna bring it back...ever.
>
>[E][✘] lesson (at: 10 Sep 2020 12:00:00 PM)
>
>...*Yawns*... You have 12 tasks
>I'll go back to my nap...please finish them so-...zzz...

<br>

#### `list` - Lists all Tasks
Shows the user a list of all his/her tasks

Example of usage: 
`list`

Expected Outcome:
>1. [D][✓] CS2103 webcast (by: 12 Sep 2020 09:00:00 AM)
>2. [E][✘] dinner (at: 12 Sep 2020 18:00:00 PM)
>3. [D][✘] CS2103 Assignment (by: 12 Oct 2020 11:00:00 AM)  
>
>...*Yawns*... You have 3 tasks  
>I'll go back to my nap...please finish them so-...zzz...

<br>

#### `find [keyword]` - Searches for Tasks containing keyword
Shows the user a list of all his/her tasks containing keyword

Example of usage: 
`find CS2103`

Expected Outcome:
>...*yawns*...I found some tasks with the keyword: "CS2103"
>
>1. [D][✓] CS2103 webcast (by: 12 Oct 2020 06:06:00 AM)
>2. [D][✘] CS2103 Assignment (by: 13 Oct 2020 06:06:00 AM)

<br>

#### `sort [tag]` - Sorts the Tasks according to tag
Sorts the type of tasks determined by tag in chronological order

Example of usage: 
`sort all`

Expected Outcome:
>...*yawns*...I sorted some tasks that are of type: "all"
>
>1. [D][✓] homework (by: 10 Sep 2020 12:00:00 PM)
>2. [E][✓] lesson (at: 10 Sep 2020 12:00:00 PM)
>3. [D][✓] CS2103 webcast (by: 12 Oct 2020 06:00:00 AM)

<br>

#### `bye` - Exits the application
Saves the data of the user and exits the app.

Example of usage: 
`bye`

Expected Outcome: 
>None

<br>

# Summary of Instructions
| Command           | Format                              |
| ----------------- | ----------------------------------- |
|**help**           | `help`                              |
|**todo**           | `todo [description]`                |
|**deadline**       | `deadline [description] /by [date]` |
|**event**          | `event [description] /at [date]`    |
|**done**           | `done [number]`                     |
|**delete**         | `delete [number]`                   |
|**list**           | `list`                              |
|**find**           | `find [keyword]`                    |
|**sort**           | `sort [tag]`                        |
|**exit**           | `bye`                               |

## Project Github:
https://github.com/Vielheim/ip