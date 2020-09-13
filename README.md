# Duke-it Task Manager

Duke-it is a desktop application for managing tasks by typing commands, while also having a Graphical User Interface (GUI)

* [Quick start](#quick-start)
* [Features](#features)

## Quick Start
1. Ensure you have [Java ```11```](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html) or above installed on your computer.
2. Download the latest [`duke.jar`](https://github.com/aizatazhar/ip/releases/tag/v1.0).
3. Copy the file to the folder you wish to use as the home folder.
4. Double-click the file to start the app
5. Type your command into the box and press enter to execute it.

## Features
Action | Description, Format & Examples
------------ | -------------
Bye | Exits the application <br/> ```bye```
Deadline | Creates a new deadline task <br/> ```deadline <TASK> /by <DATE>``` <br/> e.g. ```deadline Math homework /by 10-09-2020 2359``` 
Delete | Deletes a task <br/> ```delete <INDEX>``` <br/> e.g. ```delete 1```
Done | Marks a task as complete <br/> ```done <INDEX>``` <br/> e.g. ```done 1```
Event | Creates a new event task <br/> ```event <TASK> /by <DATE>``` <br/> e.g. ```event Christmas /at 25-12-2020 0000```
Find | Finds tasks containing given keywords separated by ~ <br/> ```find <KEYWORDS>``` <br/> e.g. ```find Math ~ English ~ Science```
List | Lists all tasks <br/> ```list```
Todo | Creates a new todo task <br/> ```todo <TASK>``` <br/> e.g. ```todo Clean room```
Sort | Sorts the task list by oldest date first <br/> ```sort```
