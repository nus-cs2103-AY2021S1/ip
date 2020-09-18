# Viscount
![](https://i.imgur.com/dt9NWoj.png)

Viscount is a **desktop app for managing tasks, optimised for use via a Command Line Interface (CLI)** while still having the benefits of a Graphical User Interface (GUI). If you are someone who can type fast, Viscount can help you track your tasks faster than traditional GUI apps.

## Quick Start for Users
1. Ensure you have Java 11 or later installed.
1. Download the jar file from the project website.
1. Copy the file to the folder you want to use as the home folder for the application.
1. Double-click the file to start the app. A GUI should appear, with the field bar to input commands.

Check out the [user guide](https://sc-arecrow.github.io/ip/) to get started!

## Setting up in Intellij for Developers

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
1. After the importing is complete, locate the `src/main/java/viscount.Launcher.java` file, right-click it, and choose `Run Launcher.main()`. If the setup is correct, the Viscount application should open.
