# User Guide
Duke is a Personal Assistant Chatbot which stores and records your to-dos, deadlines and events. This User 
Guide will demonstrate how to use its features, such that users will have a smooth and productive experience with 
the application.


## Quick start   
    1. Ensure you have Java 11 or above installed in your Computer.   
    2. Download the latest jar file.   
    3. Copy the file to the folder you want to use as the home folder for the program.   
    4. Double-click the file to start the app. The GUI should appear in a few seconds.   
    5. Type the command in the command box and press Enter to execute it, Refer to the 
        Features below for details of each command.  
          
          
       
## Task Types
There are 3 types of Tasks that Duke can process. These include:
1. ToDo
2. Deadline
3. Event

## Features
* Add a Task
* List All Tasks
* Mark Tasks as Done
* Find Tasks by Keywords
* Delete Tasks
* Exit the Program
    

## Usage

### Feature 1: ToDo 
Add tasks without any date/time attached to it e.g., visit new theme park.  
Format: `todo DESCRIPTION`  
Example: `todo borrow book`

### Feature 2: Deadline
Add tasks that need to be done before a specific date/time e.g., submit report by 11/10/2019 5pm.  
Format: `deadline DESCRIPTION /by DATE TIME(optional)`  
Example:  
`deadline return book /by Sunday`

`deadline return book /by 2/12/2019 1800`

`deadline return book /by 2019-12-2`

* note: 
    * DATE has the following formats:`YYYY-M(M)-D(D)`,  `(D)D/(M)M/YYYY`
    * TIME can be any string.

### Feature 3: Event
Add tasks that start at a specific time and ends at a specific time e.g., team project meeting on 2/10/2019 2-4pm.  
Format: `event DESCRIPTION /at DATE TIME(optional)`  
Example:  
`event project meeting /at Mon 2-4pm`

`event return book /at 2/12/2019 1800`

`event return book /at 2019-12-2`

### Feature 4: List
Display all the current tasks to the user.  
Format: `list`  

### Feature 5: Mark as Done
Mark tasks as done.  
Format: `done INDEX`  
Example: `done 1`

### Feature 6: Find
find a task by searching for a keyword.  
Format: `find KEYWORD`  
Example: `find book`

### Feature 7: Delete
Delete tasks from the list.  
Format: `delete INDEX`  
Example: `delete 3`

### Feature 8: Exit
Exit the program via chat.  
Format: `bye`  
