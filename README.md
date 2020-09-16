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
1. After the importing is complete, locate the `src/main/java/Duke.java` file, right-click it, and choose `Run Duke.main()`. If the setup is correct, you should see something like the below:
   ```
   Hello from
    ____        _        
   |  _ \ _   _| | _____ 
   | | | | | | | |/ / _ \
   | |_| | |_| |   <  __/
   |____/ \__,_|_|\_\___|
   ```

## Testing

```shell script
cd text-ui-test
# chmod +x # Only needed first time
./runtest.sh
```

## Running Jar file

Simply recompile the program.

The run:

```shell script
java -jar out/artifacts/ip_jar/ip.jar
```

## Input commands

##### Misc
```
bye/b # exit program
```

##### Viewing & querying tasks
```
list/l # List task details: id, description, time stamp etc...
find/f,<keywords> # Search for matches in tasks, return the id
```

##### Updating tasks
```
done/do,<task id> # Indicate a task is complete
delete/del,<task id> # Delete a task
```

##### Creating tasks
```
todo/t,<task name> # Add a task to todos
deadline/de,<task name>,<time>
event/e,<task name>,<time>
```