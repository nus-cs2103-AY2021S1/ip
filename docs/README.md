### User Guide
# Individual Project DUKE

## Overview 
DUKE is an interactive chat bot that not only has the function of user interaction, 
but also the function of helping users keep a list of different types of tasks 
(namely todos, events, deadlines). Users are also able to manipulate the tasks (namely
done, delete).

### Feature 1: Greetings
Upon initiating this program, the chat bot prints its unique introductory greeting to
welcome the user.

## Usage: Upon running the program

Expected outcome:

     _______________________________________________________________________
      ***Welcome to Project DUKE***
      CS2103T Individual Project 1
      
      Hello! I'm Duke
      What can I do for you?
     _______________________________________________________________________

### Feature 2: Task Creation
The task creation commands is followed by a space character and then the description to 
the task. This adds a todo task to the list of tasks.

## Usage 1: Creates a todo task
The todo task only requires `todo` keyword and description.

Usage example:
`todo return book`

Expected outcome:

    _______________________________________________________________________
     Got it. I've added this task:
       [T][✘] return book
     Now you have 1 task(s) in the list.
    _______________________________________________________________________

## Usage 2: Creates a event task
The event task only requires `event` keyword, `/at` keyword and description.

Usage example:
`event return storybook /at 6th June 2020`

Expected outcome:

    _______________________________________________________________________
     Got it. I've added this task:
       [E][✘] return storybook (at: 6th June 2020)
     Now you have 2 task(s) in the list.
    _______________________________________________________________________

## Usage 3: Creates a deadline task
The deadline task only requires `deadline` keyword, `/by` keyword and description.

Usage example:
`deadline finish CS2103T project /by 7th July 2020`

Expected outcome:

    _______________________________________________________________________
     Got it. I've added this task:
       [D][✘] finish CS2103T project (by: 7th July 2020)
     Now you have 3 task(s) in the list.
    _______________________________________________________________________

### Feature 3: List Manipulation
The list manipulation commands changes the tasks states in the list recorded

## Usage 1: Marks the specific task as done
This function only requires `done` keyword and task index.

Usage example:
`done 2`

Expected outcome:

    _______________________________________________________________________
     Nice! I've marked this task as done:
       [E][✓] return storybook (at: 6th June 2020)
    _______________________________________________________________________

## Usage 2: Deletes the specific task
This function only requires `delete` keyword and task index.

Usage example:
`delete 3`

Expected outcome:

    _______________________________________________________________________
     Noted. I've removed this task:
       [D][✘] finish CS2103T project (by: 7th July 2020)
     Now you have 2 task(s) in the list.
    _______________________________________________________________________
   
## Usage 3: Displays all current tasks
This function only requires `list ` keyword and a space character.

Usage example:
`list`

Expected outcome:

    _______________________________________________________________________
     Here are the tasks in your list:
     1.[T][✘] return book
     2.[E][✓] return storybook (at: 6th June 2020)
    _______________________________________________________________________
    
