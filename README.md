# Pikachu Task Manager ChatBot

This is a project for a greenfield Java project, adapted from CS2103 Duke, named after the Java mascot _Duke_. Given below are instructions on how to use it. 

## Chatbot Commands:

- find [keyword]
- date [task date]
- todo [new todo list]
- event [new event] \at [DD-MM-YYYY HH:MM]
- deadline [new event] \by [DD-MM-YYYY HH:MM]
- tag [task number] [new tag]
- findtag [tag]
- untag [task number] [tag]
- untag [tag]
- delete [task number]
- done [task number]

## Sample App Preview

![image](other/app preview.gif)

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
