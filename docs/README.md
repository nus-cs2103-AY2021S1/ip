## User Guide
# Individual Project DUKE

## Overview 
DUKE is an interactive chat bot that not only has the function of user interaction, 
but also the function of helping users keep a list of different types of tasks 
(namely todos, events, deadlines). 

- Users are able to create tasks `todo, event, deadline`.
- Users are able to filter task list `find, reminder`.
- Users are also able to manipulate the tasks `done, delete`.

## Feature 1: Greetings
Upon initiating this program, the chat bot prints its unique introductory greeting to
welcome the user.

### Usage 1: Upon running the program
> Prints a greeting message whenever the Duke program starts.

Usage example:
- Start the program.

Expected outcome:

     _______________________________________________________________________
      ***Welcome to Project DUKE***
      CS2103T Individual Project
      
      Hello! I'm Duke
      What can I do for you?
     _______________________________________________________________________

## Feature 2: Task Creation
The task creation commands is followed by a space character and then the 
description to the task. This adds a task to the list of tasks.

### Usage 1: Creates a todo task
> The todo task only requires `todo` command and description.
A not yet done todo task is created.

Usage example:
`todo return book`

Expected outcome:

    _______________________________________________________________________
     Got it. I've added this task:
       [T][✘] return book
     Now you have 1 task(s) in the list.
    _______________________________________________________________________

### Usage 2: Creates a event task
> The event task only requires `event` command, `/at` command, description and
a date formatted to `YYYY-MM-DD`. A not yet done event task is created.

Usage example:
`event return storybook /at 2020-06-16`

Expected outcome:

    _______________________________________________________________________
     Got it. I've added this task:
       [E][✘] return storybook (at: Jun 6 2020)
     Now you have 2 task(s) in the list.
    _______________________________________________________________________

### Usage 3: Creates a deadline task
> The deadline task only requires `deadline` command, `/by` command, description 
and a date formatted to `YYYY-MM-DD`. A not yet done deadline task is created.

Usage example:
`deadline finish CS2103T project /by 2020-07-07`

Expected outcome:

    _______________________________________________________________________
     Got it. I've added this task:
       [D][✘] finish CS2103T project (by: Jul 7 2020)
     Now you have 3 task(s) in the list.
    _______________________________________________________________________

## Feature 3: List Manipulation
The list manipulation command changes the list of tasks displayed to the 
user.

### Usage 1: Displays all current tasks
> This function only requires `list` command. Displays all tasks read currently.

Usage example:
`list`

Expected outcome:

    _______________________________________________________________________
     Here are the task(s) in your list:
     1.[T][✘] return book
     2.[E][✓] return storybook (at: Jun 16 2020)
    _______________________________________________________________________
    
### Usage 2: Find all current tasks with matching word
> This function requires `find` command and a keyword. 
It is also caps sensitive.

Usage example:
`find Assignment`

Expected outcome:

    _______________________________________________________________________
     Here are the matching task(s) in your list:
     1.[E][✘] CS2100 Assignment 1 (at: Sep 16 2020)
    _______________________________________________________________________
    
### Usage 3: Reminders to upcoming tasks to complete
> Displays a list of incomplete tasks that are required to be completed in 3 
days including the day today. Incomplete Todo tasks are all displayed.

Usage example:
- Start the program.
- On press "My Reminder" button at the top of the Duke program.

Expected outcome:

**IF** *There are tasks to be completed:*

    _______________________________________________________________________
     ***Reminder:
     Here are the tasks to be completed within 3 days:
     1.[E][✘] CS2100 Assignment 1 (at: Sep 16 2020)
     2.[D][✘] CS2103T Week 6 ip (by: Sep 17 2020)
    _______________________________________________________________________

**ELSE** *There are no tasks to be completed:*

    _______________________________________________________________________
     ***Reminder:
     There are no urgent tasks to be completed.
     You can take a break! :)
    _______________________________________________________________________

## Feature 4: Task Manipulation
The task manipulation command changes the tasks' states within the list of 
data.

### Usage 1: Marks the specific task as done
> This function only requires `done` command and task index. Finds the task
with the index number specified by user and marks the task as done.

Usage example:
`done 2`

Expected outcome:

    _______________________________________________________________________
     Nice! I've marked this task as done:
       [E][✓] return storybook (at: Jun 6 2020)
    _______________________________________________________________________

### Usage 2: Deletes the specific task
> This function only requires `delete` command and task index.

Usage example:
`delete 3`

Expected outcome:

    _______________________________________________________________________
     Noted. I've removed this task:
       [D][✘] finish CS2103T project (by: Jul 7 2020)
     Now you have 2 task(s) in the list.
    _______________________________________________________________________
   
## Feature 5: System Automation
There are inbuilt functions which allows users to load, save and exit their 
program with more convenience.

### Usage 1: Closing of Program
> This function only requires `bye` command. Duke prints a goodbye message and
then closes the Duke program for the user in 2 seconds.

Usage example:
`bye`

Expected outcome:

    _______________________________________________________________________
     See you again!
    _______________________________________________________________________

*The Duke program then proceeds to close in 2 seconds*.
   
### Usage 2: Loading of data
> Loads the data from a data file in `duke.txt` format upon starting the Duke
program. If the file does not exists in the user directory, a new `duke.txt` 
file will be created.

### Usage 3: Saving of data
> Data will automatically be saved to the `duke.txt` data file every time user 
inputs a new command.
