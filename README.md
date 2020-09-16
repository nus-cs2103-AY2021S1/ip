# User Guide for King Chat Bot
This is a project under the module CS2103t taken in the National University of Singapore.  


<img>![Java CI](https://github.com/todoge/ip/workflows/Java%20CI/badge.svg)</img>

<strong><u>Instructions to run the app:</u></strong>  
Download and run the `king.jar` in `release v1.2`.\
On running the application successfully, you should see the pop-up window.  
## Features 
<ul>
    <li>todo</li>
    <li>deadline</li>
    <li>event</li>
    <li>delete</li>
    <li>find</li>
    <li>list</li>
    <li>clear list</li>
    <li>bye</li>
</ul>

## Usage
#### `todo <task>` - Adds a new task 
Adds a new task to list.  

Example of usage: 
`todo running`


#### `event <task> /at <time>` - Adds a new event
Adds a new event to list.  

Example of usage: 
`event marathon /at 2pm`

#### `deadline <task> /by <date> <time>` - Adds a new deadline
Adds a new deadline to list.  

Example of usage: 
`deadline homework /by 23/10/2020 1400`

#### `delete [list of task numbers]` - Deletes tasks
Deletes all the tasks with the task numbers.  

Example of usage: 
`delete 1 2 3`  
Outcome: deletes task 1, task 2 and task 3.

#### `find [list of keywords]` - Find tasks with given keywords
Find all the tasks with at least 1 of the matching keyword.  

Example of usage: 
`find run sing jump`  
Outcome: finds tasks that contains at least 1 of the keywords provided.

#### `list` - Returns all the tasks in the list
Shows all the tasks currently stored in the list.

Example of usage: 
`list`  

#### `clear list` - Removes all the tasks in the list
Remove all the tasks in the list.

Example of usage: 
`clear list`  
## Releases
<u><strong> Updated UI v1.2 on 14/09/2020: </strong></u>
![GUI v1.2](docs/Ui.png)
<u><strong> Updated UI v1.1 on 05/09/2020: </strong></u>
![GUI v1.1](data/images/ui%20v1.1.PNG)
![GUI v1.1](data/images/ui%20v1.1_2%20PNG.PNG)


<br><u><strong>New GUI on JavaFXML UI 01/09/2020: v1.0</strong></u>\
![GUI v0.1](data/images/king_gui_v0.1.PNG)


<strong><u>UPDATE: Deprecated method 01/09/2020:</u> King now runs on javaFXML GUI.</strong>\
Simply download JAR file in `release v0.1` and run on your local system.\
The program requires minimum `Java SDK 11`. To run the JAR file simply run `java -jar ip.jar`. \
Refer to the fork for the project template.

   ```
   ____  __.__
  |    |/ _|__| ____    ____
  |      < |  |/    \  / ___\
  |    |  \|  |   |  \/ /_/  >
  |____|__ \__|___|  /\___  /
          \/       \//_____/
  
  Hello! I'm King
  What can I do for you?
   ```