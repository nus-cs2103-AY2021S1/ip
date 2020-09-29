# ip

iPbot is a personal assistant chatbot that allows the user to keep track of various tasks, deadlines, and events.

Although iPbot has a GUI, input and output are mostly CLI-based.

It is named after the [CS2103T individual project (iP)](https://nus-cs2103-ay2021s1.github.io/website/admin/ip-overview.html) and is based on the generic project [Duke](https://nus-cs2103-ay2021s1.github.io/website/se-book-adapted/projectDuke/index.html).

## Setting up in Intellij (for development)

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
1. After the importing is complete, locate the `src/main/java/core/Launcher.java` file, right-click it, and choose `Run Launcher.main()`. If the setup is correct, you should see the iPbot GUI:

![Screenshot](./docs/Ui.png)