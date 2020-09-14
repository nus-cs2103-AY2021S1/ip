# Duke project template

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
1. After the importing is complete, locate the `src/main/java/duke.Launcher.java` file, right-click it, and choose `Run Launcher.main()`. If the setup is correct, you should see something like the below:
   ```
    ____        _        
   |  _ \ _   _| | _____ 
   | | | | | | | |/ / _ \
   | |_| | |_| |   <  __/
   |____/ \__,_|_|\_\___|
   
   Hello! I'm Duke
   What can I do for you?
   1. list
   2. done...{item number}
   3. todo...{description}
   4. deadline...{description}.../by...{dd/mm/yyyy}...{hhmm}
       e.g. deadline return book /by 1/12/2020 1800
   5. event...{description}.../at...{dd/mm/yyyy}...{hhmm}-{hhmm}
       e.g. event meeting /at 1/12/2020 1800-1900
   6. delete...{item number}
   7. find...{item to find}
   8. update...{item number}.../{desc OR date}...{value}
   9. bye
   ```
 
## Features
In order for easy of use, the menu displayed to users was design to be **simple**.  
> Notes to reading menu: 
>  1. Replace `{replace_me}` with relevant data. The data required is defined in curly braces.   
>     1.1 `OR` inside the curly braces means either of the elements not both.
>           e.g. For command 8. ```$ update 2 /desc New description```
>  2. `...` displayed on the menu means it requires a space. **3 dots equals 1 space.**  

To exit programme just click the cross on the top right of the dialog box.

### Listing all Task: `list`
Shows a list of task (Deadline, Event, ToDo) that is recorded in Duke.  
Format: `list`

### Change task to complete: `done`
Change the status of not completed for a particular task to complete.   
Format: `done...{item_number}`   
  * Item number can be obtained by listing all the task and choosing the task index.  
 
Example: `done 2`

### Create a todo task: `todo`
Create a todo task with a description.   
Format: `todo...{description}`   
  * Description can be any text.
  * Description cannot be empty.   
 
Example: `todo iP Meeting

### Create a deadline task: `deadline`   
Create a deadline task with a description, deadline date and time.   
Format: `deadline...{description}...\by...{dd/mm/yyyy}...{hhmm}`   
  * Description can be any text.
  * Description cannot be empty.
  * Date can be in `dd/mm/yyyy` or `dd-mm-yyyy`.    
  * Time is in 24 hours format.
  
Example: 
  * `deadline iP meeting /by 14-9-2020 1130`
  * `deadline iP meeting /by 14/9/2020 1130`
  * `deadline iP meeting /by 14/09/2020 230`    
  
### Create an event task: `event`   
Create an event task with a description, start and end date and time.   
Format: `event...{description}...\at...{dd/mm/yyyy}...{hhmm}-{hhmm}`   
  * Description can be any text.
  * Description cannot be empty.
  * Date can be in `dd/mm/yyyy` or `dd-mm-yyyy`.    
  * Time is in 24 hours format.
  
Example: 
  * `event iP meeting /at 14-9-2020 1130-1800`
  * `event iP meeting /at 14/9/2020 1130-1800`
  * `event iP meeting /at 14/09/2020 230-1800`
 
### Delete a task: `delete`
Remove a task from Duke task list.
Format: `delete...{item_number}`   
  * Item number can be obtained by listing all the task and choosing the task index.  
 
Example: `delete 2`

### Find a task: `find`
Find item based on the description.  
Format: `find...{find_value}`
  * The find function only work with description.   
Example:
  * `find book` All description with book will appear.    
  * `find wa` E.g. thing that contain "wa" will appear like "water".
  
### Update task details `update`   
Update task description or date (if applicable).   
Format: `update...{item_number}.../{desc OR date}...{value}`
  * Item number can be obtained by listing all the task and choosing the task index.
  * `/{desc OR date}` will determine if the update will be for the task description, or the date.
  * `{value}` format will depend on the type of update.    
      * If update type is description than `{value}` can be any text.
      * If update type is date than follow date format of deadline task or event task, depending on the update task type. 
      
Example: 
  * `update 1 /desc iP Meeting Update`
  * `update 2 /date 14-9-2020 1130-1800`
  * `update 3 /date 14/9/2020 1130`
  
### Exiting application
To exit application click the cross at the top right-hand corner of the dialog box.

### Saving the data
Duke application saved the changes automatically after each action. There is no need to save manually.
   



