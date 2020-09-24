## <ins>Overview</ins>
Duke is an interactive chat-bot that helps to organize your tasks through a to-do list.

<img src="https://raw.githubusercontent.com/eugene3231/ip/master/docs/Ui.png" width="400">

## <ins>Quick Start Guide</ins>
Prerequisites:

* Java JDK 11
* Ensure that your `JAVA_HOME` is set to the correct JDK 11 location

Running Duke:
  1. [Download](https://github.com/eugene3231/ip/releases) *`Duke.jar`*
  2. Run *`Duke.jar`*
  3. The GUI should appear and you can start talking to Duke!


## <ins>Features</ins>

### Add tasks
* Creates a task of type listed below and add it to the task list.
  - Deadline
  - Event
  - Todo

### Delete tasks
* Removes a task.

### Show all tasks
* Displays all tasks.

### Clear all tasks
* Removes all tasks.

### Find a task
* Finds a task based on a given keyword.

### Mark task as completed
* Completes a task.

### Undo 
* Undos the previous user command.

</br>
______________________________________________________________________________________________________
</br>

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
