# Duke project

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
1. After the importing is complete, locate the `src/main/java/main.java.duke.Duke.java` file, right-click it, and choose `Run main.java.duke.Duke.main()`. If the setup is correct, you should see something like the below:
   ```
   Hello, Welcome to Duke, your personal assistant!
   ```
1. Duke comes with a Graphical User Interface(GUI) as well, locate the `src/main/java/main.java.duke.Launcher.java` file, right-click it, and choose `Run main.java.duke.Launcher.main()`. If the setup is correct, you should see a GUI pop-up:

1. Alternatively, you may access the GUI by double clicking on the duke.jar file found as a release in this repo.

## Using Duke

Duke supports the following operations:

#### Saying hello!
You can say hello to Duke by typing the following command:
```
hello
```

#### Adding tasks

Duke supports the adding of 3 task types and the ability to specify its priority(optional).
1. todo
1. deadline
1. event

To add a task, enter a command in the following format:
"{task type} {task description} /priority {low/medium/high}"
Priority is optional.

Adding a todo task

Kindly note that the command is case sensitive.
Example command: 
```
todo CS2103T IP increments
todo CS2103T IP increments /priority high
```

Adding a deadline task

Kindly note that the command is case sensitive.
Date and Time deadlines can be specified in the task description using the /by flag. Date and Time has to be in the format "yyyy-mm-dd HHMM".
Example command: 
```
deadline CS2103T IP increments /by 2020-10-19 1900
deadline CS2103T IP increments /by 2020-10-19 1900 /priority medium
```

Adding an event task

Kindly note that the command is case sensitive.
Date and Time deadlines can be specified in the task description using the /by flag. Date and Time has to be in the format "yyyy-mm-dd HHMM".
Example command: 
```
event family gathering /by 2020-10-12 1800
event family gathering /by 2020-10-12 1800 /priority low
```

#### Listing all tasks

To list all tasks, enter the following command
```
list
```

#### Deleting tasks

To delete a task, enter a command in the following format:
"delete {task number}"
The task number is the number indicated on the left of the task in the list of tasks.

Example:
```
delete 2
```

#### Marking tasks as done

To mark a task as done, enter a command in the following format:
"done {task number}"
The task should appear with a tick as opposed to a cross.

Example:
```
done 2
```
#### Setting priority of tasks

To set the priority of a task, enter a command in the following format:
"set priority {task number} {low/medium/high}"

Example:
```
set 1 priority low
```
#### Exiting from the Application

To exit from the Command Line Interface type the following command:

```
bye
```

To exit from the Graphic User Interface, simply click the cross at the top right of the pop up window.

#### Error messages

Duke will notify you of an invalid command/input, this can be recognised by the red background in the GUI and the presence of the keyword "OOPS!!!" for the CLI.

```
OOPS!!! Sorry setting of this type of label is supported.
```
