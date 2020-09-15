# duke.Duke project template

This is a project template for a greenfield Java project. It's named after the Java mascot _Duke_. Given below are instructions on how to use it.

## Setting up in Intellij

Prerequisites: JDK 11, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project dialog first)
1. Set up the correct JDK version, as follows:
   1. Click `Configure` > `Structure for New Projects` and then `Project Settings` > `Project` > `Project SDK`
   1. If JDK 11 is listed in the drop down, select it. If it is not, click `New...` and select the directory where you installed JDK 11
   1. Click `OK`
1. Import the project into Intellij as follows:
   1. Click `Open or Import`.
   1. Select the project directory, and click `OK`
   1. If there are any further prompts, accept the defaults.
1. After the importing is complete, locate the `src/main/java/duke.Duke.java` file, right-click it, and choose `Run duke.Duke.main()`. If the setup is correct, you should see something like the below:
   ```
   Hello from
    ____        _        
   |  _ \ _   _| | _____ 
   | | | | | | | |/ / _ \
   | |_| | |_| |   <  __/
   |____/ \__,_|_|\_\___|
   ```
# User Guide:

### Setting up and Running the program
This program when run (running Duke.main()), will display a window that represents a chat window with a bot.

### How to start
1. Start by typing anything in the text box at the bottom of the window.
1. Instruction/error messages will guide you along.
1. The following are the commands and how to use them.
1. Formats have to be followed strictly.

### Commands and instructions

#### View all the commands

* _**Format**_ | `!commands`

* Displays a list of text commands in the window. 
* A shorter version of the following below.
    
#### View all the tasks in the list

* _**Format**_ | `list`

* Displays the list of tasks currently in the task list.

#### Quit the program

* _**Format**_ | `bye`

* Saves the current tasks into a file and exits program.
* File saved is a .txt file.
* File location will be displayed in the window as well.
* After which programs closes in 5 seconds.


#### Mark a task as done

* _**Format:**_ `done <interger task number>`

* Marks a designated task in the task list as done

#### Add a todo task
 
* _**Format:**_ `todo <description>`

* Adds a todo task to the task list.
* Populates the description of the task.

#### Add a deadline task

* _**Format:**_ `deadline <description> /by <dd/MM/yyyy HHmm>`
 
* Adds a deadline task to the task list.
* Populates the description and date time of the task.

#### Add an event task

* _**Format:**_ `event <description> /at <dd/MM/yyyy HHmm>`

* Adds an event task to the task list.
* Populates the description and date time of the task.

#### Delete a task 

* _**Format**_ | `delete <task number>`

* Deletes a designated task from the task list.
#### Find a task

* _**Format**_ | `find <string>`

* Display tasks with matching string.