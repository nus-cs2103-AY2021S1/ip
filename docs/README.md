# User Guide

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
1. After the importing is complete, locate the `src/main/java/Duke.java` file, right-click it, and choose `Run Duke.main()`.
  

## Aim
Aqua is a task managing application that helps you keep track of your task list.

## Feature List

 - Show all of the tasks in the list
 - Add a task of a specific type(Todo/Deadline/Event) into the list
 - Delete a task from the list
 - Search for related tasks by keyword
 - Mark a task as done
 - Store the list in a local txt file
 - Load the list from the local txt file every time the application is opened

## Future Expansion Plan
- Tag function: give each task a tag
- Search by tag
- Priority function: give each a priority number
- List the tasks by priority
- List the tasks of a specific type(Deadline/Event/Todo)

## Usage

### 1. `hi` - Greet 
User input format: `hi`

Example: 
```$xslt
hi
```

Expected Outcome:
```
Hi!
```

### 2. `list` - List all of the tasks
User input format: `list`

Example: 
```$xslt
list
```

Expected Outcome:
```
Here are the tasks in your list:
1. [T][✓] homework
2. [T][✘] ip
3. [D][✘] quiz (by: 9月 18 2019)
```

### 3. `todo` - Add a Todo task to the list
User input format: `todo DESCRIPTION`

Example: 
```$xslt
todo CS2103S Quiz
```

Expected Outcome:
```
Got it. I've added this task:
[T][✘] CS2103 Quiz
Now you have 4 tasks in the list.
```

### 4. `deadline` - Add a Deadline task to the list
User input format: `deadline DESCRIPTION /by YYYY-MM-DD`

Example: 
```$xslt
Got it. I've added this task:
[D][✘] CS2103S ip (by: 2020-09-18)
Now you have 5 tasks in the list.
```

Expected Outcome:
```
Got it. I've added this task:
[E][✘] CS2101 OP meeting (at: Wednesday 9pm)
Now you have 6 tasks in the list.
```
### 5. `event` - Add an Event task to the list
User input format: `event DESCRIPTION /at TIME`

Example: 
```$xslt
event CS2101 OP meeting /at Wednesday 9pm
```

Expected Outcome:
```
Got it. I've added this task:
[E][✘] CS2101 OP meeting (at: Wednesday 9pm)
Now you have 6 tasks in the list.
```
### 5. `done` - Mark a task as done
User input format: `done INDEX`

Example: 
```$xslt
done 4
```

Expected Outcome:
```
Nice! I've marked this task as done:
[T][✓] CS2103 Quiz
```

### 6. `find` - Search for related tasks by keyword
User input format: `find KEYWORD`

Example: 
```$xslt
find 2103
```

Expected Outcome:
```
Here are the tasks found: 
[T][✘] 2103
[T][✓] CS2103 Quiz
[D][✘] CS2103S ip (by: 2020-09-18)
```

### 7. `delete` - Delete a task from the task list
User input format: `delete INDEX`

Example: 
```$xslt
delete 2
```

Expected Outcome:
```
Noted. I've removed this task:
[T][✘] 2103
Now you have 5 tasks in the list
```
### 8. `bye` - Exit from the Duke application
User input format: `bye`

Example: 
```$xslt
bye
```

Expected Outcome:
```
Bye!

The main window of the application closes.
The programme stops and you will exit from the application.
```
