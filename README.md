# Cartona

Cartona is a todo-list manager with a simple GUI and CLI-like commands.

## Running the program

Prerequisites: Java 11 or above installed on your computer

1. Download the latest `cartona.jar` from [Releases](https://github.com/jayarengam/ip/releases).
1. Copy the file to the folder you want to use as the home folder for Cartona.
1. Double click the file to run it. 
    - Note that running the program will create a _tasklist.txt_ file in the same directory that will be used to save a running list of tasks.

- Refer to the [User Guide](https://jayarengam.github.io/ip/) to learn how to use the application.

## Setting up in IntelliJ (For Developers)

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project dialog first)
1. Set up the correct JDK version, as follows:
   1. Click `Configure` > `Structure for New Projects` and then `Project Settings` > `Project` > `Project SDK`
   1. If JDK 11 is listed in the drop down, select it. If it is not, click `New...` and select the directory where you installed JDK 11
   1. Click `OK`
1. Import the project into Intellij as follows:
   1. Click `Open or Import`.
   1. Select the project directory, and click `OK`
   1. If there are any further prompts, accept the defaults.
1. After the importing is complete, locate the `src/main/java/cartona/Main.java` file, right-click it, and choose `Run Main.main()`. If the setup is correct, a window like the one shown above should open.