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

## Features implemented

1. Add a task from user input to a list of tasks. Example:
    
2. Mark a task in the list as done.

3. Add support for tracking three types of tasks:

    * **ToDos**: tasks without any date/time attached to it e.g., visit new theme park
    * **Deadlines**: tasks that need to be done before a specific date/time e.g., submit report by 11/10/2019 5pm
    * **Events**: tasks that start at a specific time and ends at a specific time e.g., team project meeting on 2/10/2019 2-4pm

4. Add error handling features:

    * Command that is not `todo`, `deadline`, `event`, `list`, `done` or `bye` cannot be recognised.
    * For commands that require a description, such as `todo`, `deadline`, `event`, if the response does not include a description, Duke will not accept.
    * For command `done` that takes an argument of particular format (i.e. integer), Duke will not receive illegal arguments.
    * For commands that takes in a time after some trigger words, such as `deadline` and `event`, Duke will not accept responses without a time.
## Demo
   ```
       ____________________________________________________________
        Hello! I'm Duke 
        What can I do for you?
       ____________________________________________________________
   hi!
       ____________________________________________________________
        ☹ OOPS!!! I cannot recognise the command "hi!" :-(
       ____________________________________________________________
   todo 
       ____________________________________________________________
        ☹ OOPS!!! The description of a todo cannot be empty.
       ____________________________________________________________
   todo this task
       ____________________________________________________________
        Got it. I've added this task:
          [T][✘] this task
        Now you have 1 tasks in the list.
       ____________________________________________________________
   deadline   
       ____________________________________________________________
        ☹ OOPS!!! The description of a deadline cannot be empty.
       ____________________________________________________________
   deadline this
       ____________________________________________________________
        ☹ OOPS!!! The time of a deadline cannot be empty.
       ____________________________________________________________
   deadline this /by
       ____________________________________________________________
        ☹ OOPS!!! The time of a deadline cannot be empty.
       ____________________________________________________________
   deadline this /by that
       ____________________________________________________________
        Got it. I've added this task:
          [D][✘] this (by: that)
        Now you have 2 tasks in the list.
       ____________________________________________________________
   event     
       ____________________________________________________________
        ☹ OOPS!!! The description of a event cannot be empty.
       ____________________________________________________________
   event this /by that
       ____________________________________________________________
        ☹ OOPS!!! The time of a event cannot be empty.
       ____________________________________________________________
   event this /at
       ____________________________________________________________
        ☹ OOPS!!! The time of a event cannot be empty.
       ____________________________________________________________
   event this /at that
       ____________________________________________________________
        Got it. I've added this task:
          [E][✘] this (at: that)
        Now you have 3 tasks in the list.
       ____________________________________________________________
   list
       ____________________________________________________________
        Here are the tasks in your list:
        1.[T][✘] this task
        2.[D][✘] this (by: that)
        3.[E][✘] this (at: that)
       ____________________________________________________________
   done  
        ☹ OOPS!!! The description of a done cannot be empty.
       ____________________________________________________________
   done x
        ☹ OOPS!!! The argument for a done command must be an integer within the range!!!
       ____________________________________________________________
   done the first task
        ☹ OOPS!!! The argument for a done command must be an integer within the range!!!
       ____________________________________________________________
   done 4
        ☹ OOPS!!! The argument for a done command must be an integer within the range!!!
       ____________________________________________________________
   done 1
       ____________________________________________________________
        Nice! I've marked this task as done:
          [T][✓] this task
       ____________________________________________________________
   list
       ____________________________________________________________
        Here are the tasks in your list:
        1.[T][✓] this task
        2.[D][✘] this (by: that)
        3.[E][✘] this (at: that)
       ____________________________________________________________
   bye
       ____________________________________________________________
        Bye. Hope to see you again soon!
       ____________________________________________________________


   ```
## Automated UI Testing
1. Assume you are running a *nix operating system, e.g. macOS.
1. Make sure you have `dos2unix` installed.
1. Direct to directory `text-ui-text`.
1. Run `chmod +x runtest.sh` to make the `.sh` file executable.
1. Run command
    ```
    ./runtest.sh
   ```
1. If the actual output matches the `EXPECTED.TXT`, the test passes.

   If the actual output differs from the `EXPECTED.TXT`, the script will report a failure.