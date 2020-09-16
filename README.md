# JM's Duke

This is the user guide for JM's take on the CS2103T greenfield project _Duke_, a chatbot that uses the Command Line Interface (CLI).

## Setting up

**Duke is best run with Java 11.**

1. Download the jar file `duke.jar`.
1. Move the jar file to a directory of your choice.
1. Under the same directory, create a new folder and name it data.
1. Double click on the jar file to run Duke. If the program does not open run it from the terminal with the command `java -jar duke.jar`.

## Features
Duke handles your tasks and reminds you of upcoming events/deadlines.
1. **Adding tasks**
   1. `todo`  
      _Usage_: `todo [task description]`  
      Adds a simple todo task to Duke's list. 
   1. `deadline`  
      _Usage_: `deadline [task description] /by YYYY-MM-DD HH:MM`  
      Adds a task to Duke's list with a deadline. Both date and time are required.
   1. `event`  
      _Usage_: `event [task description] /at YYYY-MM-DD HH:MM`  
      Adds an event with the time and date of the event to Duke's list. Both date and time are required.  
1. **List your tasks:** `list`  
   _Usage_: `list`  
   Displays the list of all your tasks.  
1. **Setting tasks as done:** `done`  
   _Usage_: `done [position of task in the list as shown with the list command]`  
   Sets a task of your choice to be done. The status of a done task is denoted by the ✔ symbol.
1. **Delete a task:** `delete`  
   _Usage_: `delete [position of task in the list as shown with the list command]`  
   Deletes a task of your choice. **Be careful as this action is irreversible.**
1. **Reminder:** `reminder`  
   _Usage_: `reminder`  
   Lists upcoming tasks within the week.  
1. **Find task by keyword:** `find`  
   _Usage_: `find [keyword]`  
   Finds and lists tasks containing the keyword.
1. **Exit:** `bye`  
   _Usage_: `bye`  
   Exits Duke and saves your list to the storage file in the data folder. **Duke will save your tasks to the file only if you exit with this command.**
