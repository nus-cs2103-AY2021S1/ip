# User Interface
<p align="center"><img width="100%" src="docs/Ui.png"/></p>

# About duke
The duke bot is a desktop app for managing task, optimized for use via a Command Line Interface (CLI). If you can type fast, duke bot can track your tasks faster than traditional GUI apps.  
A more comprehensive user guide can be found [here](https://github.com/Ashley-Lau/ip/tree/master/docs).

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
1. After the importing is complete, locate the `src/main/java/Launcher.java` file, right-click it, and choose `Run Launcher.main()`. If the setup is correct, the application window will appear:

