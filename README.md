# Duke

* This is a greenfield Java project for a software engineering module.
* Duke is a to-do list manager in the form of a chatbot.

## Quick Start

Prerequisites: Java 11
1. Download `duke.jar` from [releases](https://github.com/ysr25/ip/releases).
1. Double click or use the `java -jar duke.jar` command to run. You should see something like:
   ```
   Hello from
    ____        _        
   |  _ \ _   _| | _____ 
   | | | | | | | |/ / _ \
   | |_| | |_| |   <  __/
   |____/ \__,_|_|\_\___|
   ```
1. Refer to the [user guide](https://ysr25.github.io/ip/) on how to use Duke.

## Setting up in IntelliJ

Prerequisites: JDK 11, update IntelliJ to the most recent version

1. Open IntelliJ (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project dialog first).
1. Set up the correct JDK version, as follows:
   1. Click `Configure` > `Structure for New Projects` and then `Project Settings` > `Project` > `Project SDK`.
   1. If JDK 11 is listed in the drop down, select it. If it is not, click `New...` and select the directory where you installed JDK 11.
   1. Click `OK`.
1. Import the project into IntelliJ as follows:
   1. Click `Open or Import`.
   1. Select the `build.gradle` file in the project directory, and click `OK`.
   1. If there are any further prompts, accept the defaults.
1. After the importing is complete, run the `ip[run]` task in the Gradle toolbar. If the setup is correct, you should see something like:
   ```
   Hello from
    ____        _        
   |  _ \ _   _| | _____ 
   | | | | | | | |/ / _ \
   | |_| | |_| |   <  __/
   |____/ \__,_|_|\_\___|
   ```

## Acknowledgements

* Based on the [Duke project template](https://github.com/nus-cs2103-AY2021S1/ip).
* Followed the guides from [SE-EDU](https://se-education.org/guides/index.html).