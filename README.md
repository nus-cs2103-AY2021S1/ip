# Bob, the personal assistant

This project was created using Java Object Oriented Programming from a greenfield Java projects project template for a software engineering module (CS2103T) 
at the National University of Singapore. 

It is named Bob, the personal assistant, and functions primarily to track pending tasks of the user. 

Users may interact with Bob by providing commands via a Command Line Interface (CLI) or a simple chat box Graphical User Interface (GUI). 

![Image of BobUi](https://github.com/Caleblyx/ip/blob/master/docs/Ui.png)

## User Guide
Take a look at the [User Guide](https://github.com/Caleblyx/ip/blob/master/docs/README.md) for more details.
####Quick start
1. Ensure you have Java 11 or above installed.
2. Download the latest version of `Bob.jar` from [releases](https://github.com/Caleblyx/ip/releases).
3. Find a home for Bob! Move `Bob.jar` to your preferred home directory for the program. Note that data 
used by Bob will be stored in this directory.
4. Execute Bob.jar by double-clicking it (or your preferred method).

After following the steps above, Bob's chat box should appear. Input commands in the field bar to interact 
with Bob.

More details can be found in the [User Guide](https://github.com/Caleblyx/ip/blob/master/docs/README.md)





## Instructions for setting up in Intellij for Developers

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
1. After the importing is complete, locate the `src/main/bob/Bob.java` file, right-click it, and choose `Run Bob.main()`. If the setup is correct, the Command Line Interface of Bob
should initialise.
  
## Acknowledgements
* Bob's profile picture sourced from [PNGGuru](https://www.pngguru.com/free-transparent-background-png-clipart-kvkde)
* Third party libraries used: [JavaFX](https://openjfx.io/) and [JUnit](https://junit.org/junit5/)

## Contributors
Take a look at the [contributors](https://github.com/Caleblyx/ip/blob/master/CONTRIBUTORS.md) page.