# Duke project 

This is a personalised task manager that will keep track of tasks, events and deadlines.
It is designed with a Command-Line Interface implementation in mind.

## Setting up in Intellij

Prerequisites: JDK 11, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project dialog first)
1. Set up the correct JDK version, as follows:
   1. Click `Configure` > `Structure for New Projects` and then `Project Settings` > `Project` > `Project SDK`
   1. If JDK 11 is listed in the drop down, select it. If it is not, click `New...` and select the directory where you installed JDK 11
   1. Click `OK`
1. Import the project into Intellij as follows:
   1. Click `Open or Import`.
   1. ~~Select the project directory~~ When you import the project into IDEA, at the step where you normally select the project root folder, choose the `build.gradle` file inside the root folder instead., and click `OK`. 
   1. If there are any further prompts, accept the defaults (Open as project).
1. After the importing is complete, run `gradle build` to install required dependencies and setup the project.
2. Locate the ~~`src/main/java/Duke.java`~~  `src/main/java/Launcher.java`file, right-click it, and choose ~~`Run Duke.main()`~~ `Run Launcher.main()`. If the setup is correct, you should see something like the below:
![Capture](https://user-images.githubusercontent.com/43946966/92689417-91220000-f371-11ea-9eed-bc34b24a3a78.PNG)

## References
1. https://se-education.org/guides/
