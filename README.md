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
   
1. After the importing is complete, locate the `src/main/java/duke.main.Duke.java` file, right-click it, and choose `Run duke.main.Duke.main()`. If the setup is correct, you should see something like the below:
   
   ![Welcome image](/src/main/resources/images/hello.png)

5. Here are the following commands that Duke understands:
   * `help` - Displays the list of commands that Duke understands.
   * `list` - Displays the list of the user's tasks available.
   * `save` - Manually saves the current state of the list of the user's tasks.
   * `bye` - Saves the list of the user's tasks and displays the goodbye message. It is safe to exit the program after this command.
   * `todo 'TASK'` - Creates a new todo task with the description.
   * `deadline 'TASK' /by 'dd/MM/yyyy HH:mm'` - Creates a new deadline task with the description and the date due by.
   * `event 'TASK' /at 'dd/MM/yyyy HH:mm'` - Creates a new event task with the description and the date due at.
   * `done 'n'` - Marks the task at position 'n' of the list of the user's tasks as done.
   * `delete 'n'` - Deletes the task at position 'n' of the list of the user's tasks.