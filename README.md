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
1. After the importing is complete, locate the `src/main/java/Duke.java` file, right-click it, and choose `Run Duke.main()`. If the setup is correct, you should see something like the below:
   ```
   Hello from
    ____        _        
   |  _ \ _   _| | _____ 
   | | | | | | | |/ / _ \
   | |_| | |_| |   <  __/
   |____/ \__,_|_|\_\___|
   ```

## Level 2. Add, List

Add the ability to store whatever text entered by the user and display them back to the user when requested.

Example:
```
    ____________________________________________________________
     Hello! I'm Duke
     What can I do for you?
    ____________________________________________________________

read book
    ____________________________________________________________
     added: read book
    ____________________________________________________________

return book
    ____________________________________________________________
     added: return book
    ____________________________________________________________

list
    ____________________________________________________________
     1. read book
     2. return book
    ____________________________________________________________
bye
    ____________________________________________________________
     Bye. Hope to see you again soon!
    ____________________________________________________________

```
* There is no need to save the data to the hard disk.
* Assume there will be no more than 100 tasks. If you wish, you may use a fixed size array (e.g., String[100]) to store the items.