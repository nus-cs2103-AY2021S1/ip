# User Guide:

### Setting up and Running the program
Requires Java JDK 11 or later installed.

Double click on duke.jar to start program.

### How to start
1. Start by typing anything in the text box at the bottom of the window.
1. Instruction/error messages will guide you along.
1. The following are the commands and how to use them.
1. Formats have to be followed strictly.

### Commands and instructions

Note: 
* <INFO\> denotes information to be filled in by the user
* Date formats used are of the form date/month/year
* Time formats used below are of a 24hr format (e.g. 2359)

#### View all the commands

* _**Format**_ `!commands`

* Displays the following list of text commands in the window.

1) !commands | returns a list of text commands
1) list | lists out all the current tasks
1) bye | saves the current tasks into a file and exits program
1) done | format: "done <task number>", marks a task in the list as done
1) todo | format: "todo <description>", creates a todo task
1) deadline | format: "deadline <description> /by <dd/MM/yyyy HHmm>", creates a deadline task
1) event | format: "event <description> /at <dd/MM/yyyy HHmm>", creates an event task
1) delete | format: "delete <task number>", deletes a task in the list
1) find | format: "find <string>", finds tasks with matching string

    
#### View all the tasks in the list

* _**Format**_ | `list`

* Displays the list of tasks currently in the task list.

#### Quit the program

* _**Format**_ `bye`

* Saves the current tasks into a file and exits program.
* File saved is a .txt file.
* File location will be displayed in the window as well.
* After which programs closes in 5 seconds.


#### Mark a task as done

* _**Format:**_ `done <interger task number>`

* _**Example:**_ `done 1`, marks task 1 as done.

* Marks a designated task in the task list as done

#### Add a todo task
 
* _**Format:**_ `todo <description>`

* _**Example:**_ `todo have a break`, adds a todo task with the description: "have a break".

* Adds a todo task to the task list.

#### Add a deadline task

* _**Format:**_ `deadline <description> /by <dd/MM/yyyy HHmm>`

* _**Example:**_ `deadline assignment /by 16/9/2020 2359`, 
adds a deadline task with the description: "assignment", and the time by 16/9/2020, 2359hrs.
 
* Adds a deadline task to the task list.

#### Add an event task

* _**Format:**_ `event <description> /at <dd/MM/yyyy HHmm>`

* _**Example:**_ `event birthday /at 16/9/2020 1300`, 
adds an event with the description: "birthday", and the time at 16/9/2020, 1300hrs.
 
* Adds an event task to the task list.

#### Delete a task 

* _**Format**_ `delete <task number>`

* _**Example:**_ `delete 1`, deletes task 1 from the list.

* Deletes a designated task from the task list.

#### Find a task

* _**Format**_ `find <string>`

* _**Example:**_ `find test`, 
displays all tasks with descriptions containing "test" partially or as a whole.

* Display tasks with matching string.   