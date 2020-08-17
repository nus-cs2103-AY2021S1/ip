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

## Demo
   ```
       ____________________________________________________________
        Hello! I'm Duke 
        What can I do for you?
       ____________________________________________________________
   todo read book
       ____________________________________________________________
        Got it. I've added this task: 
          [T][✘] read book
        Now you have 1 tasks in the list.
       ____________________________________________________________
   deadline return book /by June 6th
       ____________________________________________________________
        Got it. I've added this task: 
          [D][✘] return book (by: June 6th)
        Now you have 2 tasks in the list.
       ____________________________________________________________
   event project meeting /at Aug 6th 2-4pm
       ____________________________________________________________
        Got it. I've added this task: 
          [E][✘] project meeting (at: Aug 6th 2-4pm)
        Now you have 3 tasks in the list.
       ____________________________________________________________
   todo join sports club
       ____________________________________________________________
        Got it. I've added this task: 
          [T][✘] join sports club
        Now you have 4 tasks in the list.
       ____________________________________________________________
   list
       ____________________________________________________________
        Here are the tasks in your list:
        1.[T][✘] read book
        2.[D][✘] return book (by: June 6th)
        3.[E][✘] project meeting (at: Aug 6th 2-4pm)
        4.[T][✘] join sports club
       ____________________________________________________________
   done 1
       ____________________________________________________________
        Nice! I've marked this task as done: 
          [T][✓] read book
       ____________________________________________________________
   done 4
       ____________________________________________________________
        Nice! I've marked this task as done: 
          [T][✓] join sports club
       ____________________________________________________________
   todo borrow book
       ____________________________________________________________
        Got it. I've added this task: 
          [T][✘] borrow book
        Now you have 5 tasks in the list.
       ____________________________________________________________
   list
       ____________________________________________________________
        Here are the tasks in your list:
        1.[T][✓] read book
        2.[D][✘] return book (by: June 6th)
        3.[E][✘] project meeting (at: Aug 6th 2-4pm)
        4.[T][✓] join sports club
        5.[T][✘] borrow book
       ____________________________________________________________
   deadline return book /by Sunday
       ____________________________________________________________
        Got it. I've added this task: 
          [D][✘] return book (by: Sunday)
        Now you have 6 tasks in the list.
       ____________________________________________________________
   event project meeting /at Mon 2-4pm
       ____________________________________________________________
        Got it. I've added this task: 
          [E][✘] project meeting (at: Mon 2-4pm)
        Now you have 7 tasks in the list.
       ____________________________________________________________
   deadline do homework /by no idea :-p
       ____________________________________________________________
        Got it. I've added this task: 
          [D][✘] do homework (by: no idea :-p)
        Now you have 8 tasks in the list.
       ____________________________________________________________
   list
       ____________________________________________________________
        Here are the tasks in your list:
        1.[T][✓] read book
        2.[D][✘] return book (by: June 6th)
        3.[E][✘] project meeting (at: Aug 6th 2-4pm)
        4.[T][✓] join sports club
        5.[T][✘] borrow book
        6.[D][✘] return book (by: Sunday)
        7.[E][✘] project meeting (at: Mon 2-4pm)
        8.[D][✘] do homework (by: no idea :-p)
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