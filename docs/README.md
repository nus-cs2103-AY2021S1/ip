Duke project - User Guide
=========

By theodoreleebrant
Last Updated 22 September 2020

# 1. Introduction
Duke is a productivity application that helps you manage your tasks in an orderly fashion. 

Duke has the following main features:
1. Manage tasks of various nature, including:
    a. Simple tasks (To-Dos)
    b. Tasks which has a deadline
    c. Events that occur at a particular time
2. Mark tasks as complete
3. Delete tasks
4. Find your tasks based on the description

Duke is optimized for users who are fast typists and prefer typing over other means of input. It comes with:
* A Command Line Interface (CLI) which allows you to accesss the features in Duke by typing
* A Graphical User Interface (GUI) which displays the information requested

![Duke UI](https://github.com/theodoreleebrant/ip/blob/master/docs/Ui.png)

# 2. About this Guide
## 2.1. Basic Information
This User Guide explains how you can use Duke to help you manage your tasks.

You may want to refer to [Section 3 (Quick start)](#3-quick-start) for a short tutorial on how to run Duke on your device and use Duke's main features. For a full walkthrough of Duke, please refer to [Section 4 (Features)](#4-features). If you are an experienced CLI user, you can find all the commands available under [Section 5 (Command Summary)](#5-command-summary)

## 2.2 Command Format
This section explains the forma of commands in the User Guide:
> Command format:
> - Words in `UPPER_CASE` are parameters supplied by the user
> - Parameters starting with `DATE` must either be `today` or supplied in this format: `dd-MM-yyyy hhmm` where:
>   - `dd` is the date as a valid integer (e.g. `28`, `03`)
>   - `MM` is the month as a valid integer (e.g. `01`, `12`)
>   - `yyyy` is the year as a valid integer (e.g. `2020`)
>   - (optional) `hhmm` is the time in 24 hour format (e.g. `1500` for 3:00pm)
> - Parameters in square brackets are optional

# 3. Quick Start
1. Ensure you have Java 11 or later installed.
2. Download the jar file from the project website. The file can be downloaded [here](https://github.com/theodoreleebrant/ip/releases).
3. Copy the file to the folder you want to use as the home folder for the application.
4. Double-click the file to start the app. A window should appear, with the field bar below to input commands.
5. Type the command in the command box and press `Enter` to execute it. 

# 4. Features 
## 4.1 Overview
Duke supports three types of tasks:
* Todos - these are simple tasks with a description.
* Deadlines - these are tasks that needs to be completed within before a certain time period.
* Events - these are things that has a time at which they happen.

In Duke, you can:
* Add tasks
* Mark tasks as done
* Delete tasks
* List your tasks
* Search for tasks

Other commands are to:
* Exit Duke
* Show the help message

## 4.2 Details
### Adding tasks - `todo`, `deadline`, `event`
Adds a task into the list.

There are three types of tasks: `Todo`, `Deadline` and `Event`.

Format:
```
// add a todo
todo DESCRIPTION

// add a deadline
deadline DESCRIPTION /by DATE_DUE

// add an event
event DESCRIPTION /at DATE_OF_EVENT
```

The date format is `dd/MM/yyyy HHmm`, with the default hour being 0000 if omitted.

Example usage:
```
todo read book
deadline return book /by 28/09/2020
event go to library /at 28/09/2020 1000
```

Expected outcome:
```
Got it. I've added this task:
[T][✘] read book
Now you have 1 tasks in the list.

Got it. I've added this task:
[D][✘] return book (by: 28/09/2020 0000)
Now you have 2 tasks in the list.

Got it. I've added this task:
[E][✘] go to library (at: 28/09/2020 1000)
Now you have 3 tasks in the list.
```

### Mark task as done - `done`
Marks a task as done.

Format:
```
// mark the task with INDEX as done
done INDEX

// mark all tasks as done
done all
```

Example usage:
```
done 1
```

Expected outcome:
```
Nice! I've marked this task as done:
[T][✓] read book
```

### Deleting task - `delete`
Deletes a task.

Format:
```
// delete the task with INDEX
delete INDEX
```

Example usage:
```
delete 1
```

Expected outcome:
```
Noted. I've removed this task:
[T][✓] read book
Now you have 2 tasks in the list.
```

### Listing tasks - `list`
Shows a list of all tasks.

Format:
```
list
```

Example usage:
```
// list all tasks
list
```

Expected outcome:
```
Here are the tasks in your list:
1.[T][✘] read book
2.[D][✘] return book (by: Sep 28 2020 00:00)
3.[E][✘] go to library (at: Sep 28 2020 10:00)
```

### Exiting Duke -`bye`
Closes the program. The task data will be automatically saved.

Format:
```
bye
```

### Show help message - `help`
Shows the help message

Format:
```
help
```

# 5. Command Summary
**Utility commands**
* Show help message: `help`
* Exit the application: `bye`

**Adding tasks**
* Add a todo task: `todo <details>`
* Add a deadline task: `deadline <detail> /by <date>`
* Add an event task: `event <detail> /at <date>`
The date format is `dd/MM/yyyy HHmm`, with the default hour being 0000 if omitted.

**Task processing**
* Mark tasks as done: `done <index of task>`
* Delete tasks: `delete <index of task>`
* List your tasks: `list`
* Search for tasks: `find <name of task>
