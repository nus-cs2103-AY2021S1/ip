# User Guide

## Deuk task tracker v1.2.1

## Features 

### Feature 1: Add tasks to tasklist
Add new tasks to the tasklist.

### Feature 2: View tasks in tasklist
View the list of all tasks with name, completion status and date (if applicable).

### Feature 3: Delete tasks from tasklist
Delete old tasks from the tasklist.

### Feature 4: Mark tasks as done
Mark tasks as done after completing them.

### Feature 5: Duplicate tasks detection
If the task being added has the same name and date (if applicable) as another task in
the tasklist, the new task will not be added to the tasklist.

### Feature 6: Help command
A list of available commands will be shown to the user when the user inputs `help`.

### Feature 7: Case insensitive commands
The commands entered do not have to be case sensitive. `HELP`, `Help` and `help` are equivalent.

### Feature 8: Search for tasks in the tasklist
Search the tasklist for tasks that match the search term.

### Feature 9: Exit command
The program will terminate when the user enters `bye` and the user does not have to 
click on the close button of the GUI window.

## Usage

### `todo` - Adds a todo task to the tasklist

Format: `todo NAME`

Example of usage: 

`todo Visit Brandon's house`

Expected outcome:

    ____________________________________________________________
      Got it. I've added the following task: 
        [T][N] Visit Brandon's house
      Now you have 2 tasks in total.
    ____________________________________________________________


### `event` - Adds an Event task to the tasklist

Format: `event NAME /at YYYY-MM-DD`

**`/at` is case sensitive**

Example of usage: 

`event birthday party /at 2020-03-04`

Expected outcome:

    ____________________________________________________________
      Got it. I've added the following task: 
        [E][N] birthday party (at: Mar 4 2020)
      Now you have 3 tasks in total.
    ____________________________________________________________


### `deadline` - Adds a deadline task to the tasklist

Format: `deadline NAME /by YYYY-MM-DD`

**`/by` is case sensitive**

Example of usage: 

`deadline homework /by 2020-10-04`

Expected outcome:

    ____________________________________________________________
      Got it. I've added the following task: 
        [D][N] homework (by: Oct 4 2020)
      Now you have 4 tasks in total.
    ____________________________________________________________


### `find` - Search tasklist for tasks that match the search term

Format: `find SEARCH_TERM`

Example of usage: 

`find birthday`

Expected outcome:

    ____________________________________________________________
      Here are the matching tasks in your list:
      1. [E][N] birthday (at: Jan 16 2021)
      2. [E][N] birthday party (at: Mar 4 2020)
    ____________________________________________________________


### `done` - Mark task at specified index in tasklist as done

Format: `done INDEX`

Example of usage: 

`done 2`

Expected outcome:

    ____________________________________________________________
      Nice job! I'll mark that as done:
        [T][Y] Visit Brandon's house
    ____________________________________________________________


### `list` - View list of all tasks in tasklist

Example of usage: 

`list`

Expected outcome:

    ____________________________________________________________
      You have the following tasks in your list:
      1. [E][N] birthday (at: Jan 16 2021)
      2. [T][Y] Visit Brandon's house
      3. [E][N] birthday party (at: Mar 4 2020)
      4. [D][N] homework (by: Oct 4 2020)
    ____________________________________________________________


### `delete` - Delete task at specified index in tasklist

Format: `delete INDEX`

Example of usage: 

`delete 2`

Expected outcome:

    ____________________________________________________________
      Noted. The following task has been removed:
        [T][Y] Visit Brandon's house
      Now you have 3 tasks left.
    ____________________________________________________________


### `delete all` - Delete all tasks in the tasklist

Example of usage: 

`delete all`

Expected outcome:

    ____________________________________________________________
      Noted. All tasks have been removed.
    ____________________________________________________________


### `help` - Show the list of available commands

Example of usage: 

`help`

Expected outcome:

    ____________________________________________________________
      Here is the list of available commands:

      todo <name>: Add todo task
      event <name> /at <date>: Add event task. Date format: YYYY-MM-DD
      deadline <name> /by <date>: Add deadline task. Date format: YYYY-MM-DD
      find <search term>: Search the task list for matching tasks
      done <index>: Mark task at specified index as done
      list: View list of all tasks added
      delete <index>: Delete the task at specified index
      delete all: Delete all tasks from task list
      bye: Exit programme
      help: You do realise you're already on the help page, right?
    ____________________________________________________________


### `bye` - Exits the Deuk program

Example of usage: 

`bye`

Expected outcome:
Window closes after a second after printing the farewell message:

    ____________________________________________________________
      Deuk: Goodbye, hope to see you again!
    ____________________________________________________________
