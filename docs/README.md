# User Guide
![Duke UI](Ui.png)
This is a user guide for the usage of Duke Application, made by 
[Stephen Tan](https://www.linkedin.com/in/stephen-tan-hin-khai/) under 
the module CS2103T 2020/21 Sem 1.

Duke Chatbot is a project template for a greenfield Java project. It's named after the Java mascot _Duke_. 
Given below are instructions on how to use it.

## Pre-Requisites for use
Install Java 11
## Getting Started
To execute the program, or start the Duke Chatbot: 
Double click the jar file, or enter by command prompt 
```
java -jar duke.jar
```
If you prefer a Command Line Interface, type: 
```
java -jar duke.jar -cli 
```
Then, type the help command to get help on what Duke can do.
```
help
```

## Features supported
Scope of features supported.
1. Create new tasks
2. View a list of tasks
3. Use regex to find tasks by matching substring
4. Get help about commands from Duke
5. Update a task as done
6. Delete tasks by given index
7. GUI or CLI interface
8. User Feedback for bad input
9. Saves and loads tasks upon execution of program

## Command Usage

### Adding and Scheduling Tasks:
You can schedule tasks on the chatbot.
There are up to 3 kinds of tasks supported.
#### 1. Todo tasks
##### command:
```
todo <task description>
```
Todo tasks have no deadline and thus have no requirements on completion
##### Expected outcome:
In the system will register a `todo task`
##### Upon wrong input:
Duke will indicate a bad command input was given and return a specific 
error relating to the bad input
#### 2. Deadline Tasks
##### command:
```
deadline <task description> -by dd-MM-YYYY
```
Deadline tasks have a deadline and will indicate how close the given date is 
from the deadline using the current time. For deadline tasks, there is limited 
level of autocorrection Hence you can just specify a day > the current day's date
Eg: if date is 12 September 2020
```
deadline example task -by 13
```
##### Expected outcome:
In the system will register a `deadline due by 14-9-2020`
##### Upon bad input
Duke will indicate that datetime is invalid, or an invalid input was given 
to the description field
#### 3. Event Tasks
##### command:
```
event <task description> -at dd-MM-YYYY
```
Event tasks have a date of event and will indicate how close the given date is from the
event using the current time. For event tasks, there is limited level of autocorrection
Hence you can just specify a day > the current day's date
Eg: if date is 12 September 2020
```
event example task -by 13
```
##### Expected outcome:
In the system will register a `event due by 14-9-2020`
##### Upon bad input
Duke will indicate that datetime is invalid, or an invalid input was given 
to the description field
### Listing Tasks:
You can list all tasks registered in the system with the command
```
list
``` 
### Search for Task:
You can find all tasks registered in the system by description using a
 substring or a regex expression.
```
find <regex or substring>
```
### Mark task done:
You can mark a task done by using the index of the task referenced from before.
```
done <index>
```
##### Expected outcome:
Duke will mark the task as done
##### Upon bad input
Duke will indicate that the input is either out-of-index or not a number.
### Delete Task
You can delete a task by using the index of the task referenced from before.
```
delete <index>
```
##### Expected outcome:
Duke will delete the task
##### Upon bad input
Duke will indicate that the input is either out-of-index or not a number. 
### Help
You can get help about Duke by issuing the command 
```
help
```
You can also get specific help about certain commands through adding the 
specific command itself.
```
help <command>
```  
##### Expected outcome:
Duke will respond with a help message.
##### Upon wrong input:
Duke will respond with the all help Duke message.
### End Application
You can end the execution of the application by issuing the command `bye`
```
bye
```