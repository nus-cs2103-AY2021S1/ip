# User Guide for Project Duke

Duke is a desktop application that is designed for keeping track of the various tasks at hand. It is optimized for 
use via a Command Line Interface (CLI) while still having Graphical User Interface (GUI). As a skilled typer, you should
be able to carry out various actions such as adding new tasks and marking a task as done much faster than the 
traditional GUI apps.

This is **an implementation of [Project Duke](https://nus-cs2103-ay1920s2.github.io/website/se-book-adapted/projectDuke/index.html)**
, an educational software project designed to take the new software developer through the steps of building
a small software incrementally, while applying as many Java and SE techniques as possible along the way.
![Example of Execution](https://github.com/Nahoyhp/ip/blob/master/docs/Ui.png)


## Setting up in Intellij
1. Ensure you have Java 11 or above installed in your Computer.
1. Download the latest ip.jar from [here](https://github.com/Nahoyhp/ip).
1. Copy the file to the folder you want to use as the home folder.
   [] Make sure you have the priviledge to create a new directory and edit files within the directory.
1. Double-click the file to start the app. The GUI should appear in a few seconds (as shown below).
1. Type the command in the command box and press Enter to execute it.  
1. For the details of each command, refer to the [Features](##Features) below.

![Image of GUI showing Welcome Message](https://github.com/Nahoyhp/ip/blob/master/docs/Welcome.png)

## Summary of Command  
| Action | Format & Example |  
|---|---|
|Add a ToDo task | `todo EVENT_TITLE ` <br/> <br/> e.g: `todo Assigment 1`|  
|Add a Deadline task | `deadline EVENT_TITLE /by DATE TIME` <br/> <br/> e.g: `deadline Assignment 1 /by 2020-09-14 18:00`|  
|Add an Event task | `event EVENT_TITLE /at DATE TIME - TIME` <br/> <br/> e.g: `event Birthday Party /at 2020-09-14 18:00 20:00`|  
|List | `list` |
|Mark a Task as Done | `done INDEX` <br/> <br/> e.g: `done 2`|  
|Delete a Task | `delete INDEX` <br/> <br/> e.g: `delete 2`  |
|Find tasks with keyword | `find KEYWORD` <br/> <br/> e.g: `find homework`|  
|Archive data into a separate file | `archive LOCATION_TO_SAVE` <br/> <br/> e.g: `archive data\dukeII.txt`|  
|Load Data | `load FILE_LOCATION` <br/> <br/> e.g: `load data\dukeII.txt`  |
|Save | `save`  |
|End the session | `bye`|  

## Features
<blockquote>
<h5>Notes</h5>
<font>
Words in UPPER_CASE are the parameters to be supplied by the user.
e.g. in todo TITLE, TITLE is a parameter which can be used as todo homework.  
<br/>
<br/>
In the Graphic Display, dialogue in Red Box indicates a Warning or error.
</font>
</blockquote>

##### Add a ToDo : `todo`   
Add a todo list.
Format: `todo EVENT_TITLE`  
E.g:
* `todo homework`

##### Add a Deadline : `deadline`  
Add a deadline list. The DATE should be in YYYY-MM-DD format and TIME should be HH:MM format.  
Format: `deadline EVENT_TITLE /at DATE TIME - TIME`  
E.g:
* `deadline Assignment 1 /by 2020-09-14 18:00`

##### Adding an Event : `event`  
Add a deadline list. The DATE should be in YYYY-MM-DD format and TIME should be HH:MM format.  
Format: `event EVENT_TITLE /at DATE TIME - TIME`  
E.g:
* `event Birthday Party /at 2020-09-14 18:00 20:00`

##### Listing out all Tasks : `list`  
List out all the tasks added to the application in order of the entry.   
Format: `list`

##### Marking a Task as done : `done`  
Mark the task at INDEX as done.  
INDEX should be a positive integer, e.g. 1, 2, 3, 4... and the order is in entry order.  
If you are not sure about the order, check with `list` command.
Format: `done INDEX`  
E.g.
* 'done 5'

##### Loading archived data : `delete`  
Delete the task at INDEX as done.  
INDEX should be a positive integer, e.g. 1, 2, 3, 4... and the order is in entry order.  
If you are not sure about the order, check with `list` command.
Once the delete is completed, the index of all subsequent tasks will move down by 1. 
Format: `delete INDEX`  
E.g.
* 'delete 5'  

##### Saving progress : `save`  
Save all the file into the default folder.
The default folder is `data\duke.txt` which will be located in the same folder as the application `.jar` file.
Format: `save`

##### Archiving data : `archive`  
Saves all the data of the current progress into the DESTINATION.
DESTINATION should point to a `.txt` file and takes relative position from the folder that contains the application .jar.
Format: `archive DESTINATION`
E.g.
* `archive data\dukeII.txt`  

##### Loading data : `load`  
Load the archived file at the DESTINATION.
DESTINATION should point to a `.txt` file and takes relative position from the folder that contains the application .jar.
It will not override the current data until `save` or `bye` command is entered.  
Format: `archive DESTINATION`
E.g.
* `archive data\dukeII.txt`

##### Ending a session : `bye`  
Close the application window after 2 seconds.  
The application will automatically save the file to the default file path.  
Format: `bye`

## Credit  
Credit goes to
1. [NUS CS2103T AY20201 Semester 1 Teaching Team](https://github.com/nus-cs2103-AY2021S1/ip) for the starter code and guidance
1. Refers to [SE-Education Adress Book Level 3](https://se-education.org/addressbook-level3/UserGuide.html) for the layout and content of
this User Guide. 
1. Thanks to Jame_D's answer in [stack**overflow**](https://stackoverflow.com/questions/27334455/how-to-close-a-stage-after-a-certain-amount-of-time-javafx.)
implement the function that close windows after a few seconds.


