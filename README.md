# Duke project template

This is a project template for a greenfield Java project. It's named after the Java mascot _Duke_. 
Given below are instructions on how to use it.

## Setting up in Intellij

Prerequisites: JDK 11, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` 
to close the existing project dialog first).
2. Set up the correct JDK version, as follows:
   1. Click `Configure` > `Structure for New Projects` and then `Project Settings` > `Project` > `Project SDK`.
   2. If JDK 11 is listed in the drop down, select it. If it is not, click `New...` and select the directory 
   where you installed JDK 11.
   3. Click `OK`.
3. Import the project into Intellij as follows:
   1. Click `Open or Import`.
   2. Select the project directory, and click `OK`.
   3. If there are any further prompts, accept the defaults.
4. After the importing is complete, locate the `src/main/java/focus.Launcher.java` file, 
right-click it, and choose Run `focus.Launcher.main()`. 
If the setup is correct, you should see something like this:
![Image of Starting Screen](./docs/images/startingScreen.png)

## Acknowledgements
Images used for making of Focus:  
[Image of Pocus](https://cdn.shopify.com/s/files/1/0231/6137/2752/files/bt21-rj_ebcc3442-da05-40c0-869a-e3befcf6f48e_1600x.jpg?v=1563920540)  
[Image of User](https://cdn.shopify.com/s/files/1/0231/6137/2752/files/bt21-koya_ab56b8c9-ca8a-40a7-a82a-689eaffa8d3d_1600x.jpg?v=1563920606)