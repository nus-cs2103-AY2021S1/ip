# User Guide
Welcome to Duke! Duke is a chat bot that helps you keep track of tasks, including deadlines and events!

## Quick Start
To get started, download the duke.jar file to your computer and place it in a folder of your choosing. To launch Duke, 
run the .jar file, and the Duke window should open shortly.

As a first command, create a new example ToDo task by typing in "todo play around with Duke" 
without the quotation marks. This will create the task "play around with Duke" and place it in your task list. To check
that it has been added correctly, type in the "list" command to display the task list.

Next, try using the "done 1" and "undone 1" commands to mark the task done and not done respectively. Like before, you 
can verify that the commands worked by using "list" to view the task list. The number you enter represents the number 
of the task, so if you want to mark the third task in the list as done, use type in "done 3" instead of 1.

Finally, to close off this brief tutorial, use the command "delete 1" to delete the example task from your list. You can 
"list" once again to display your task list, which by now should be empty.

If you ever forget which command to use, you can type "help" to view the entire list of commands that you can use in
interacting with Duke, including the more advanced "find" and "upcoming" commands!

Have fun using Duke!

## Features 
1. Add a new task to your list
1. Delete a task from your list
1. Mark a task as done or not done
1. Search for a task using keywords
1. Display your current list of tasks
1. Display all tasks occurring within the next X days
1. Store your list of tasks in the hard disk

### Adding a new task to your list
Adds a new task (a ToDo, a deadline or an event) to your current list of tasks.

### Deleting a task from your list
Deletes a task from your current list of tasks.

### Marking a task as done or not done
Marks a task in your list as done, or marks a done task as not done.

### Searching for tasks using keywords
Searches for all tasks containing the exact keywords you entered, similar to Ctrl-F on the browser or text editors.

### Display your current list of tasks
Shows you your entire list of tasks.

### Display all tasks occurring in the next X days
Shows you all your deadlines and events which are occurring in the next X days.

### Storing your list of tasks in the hard disk
Duke will auto-save your list of tasks, so you can see the same list the next time you launch Duke. 
To close Duke without saving, close the window without using the "exit" keyword, such as by using the shortcut Alt-F4 or
using End Task in Windows Task Manager.


If you wish to transfer your data to another computer, follow these simple steps:

1. Go to the folder where your duke.jar file is
1. Locate the text file named "data"
1. Create a new folder and copy the duke.jar and data.txt files inside
1. Transfer this folder to your new computer
1. Ensure that the folder on the new computer still contains both the duke.jar and data.txt files
1. You're done!

Ensure that your data.txt and duke.jar are in the **same folder**. If Duke cannot find the data.txt file in the folder,
it will simply create a fresh data file from scratch. Additionally, make sure to not lose your original data.txt, or 
your task list data will be **lost**!

## Usage

### `todo <task description>` - Add a new ToDo

Adds a new "ToDo" task with the given description to your list.

Example of usage: 

`todo Buy new clothes`

Expected outcome:

`Added new task: Buy new clothes
You now have 1 task in your list.`

The ToDo task will be added to your task list.

### `deadline <task description> /by <date of task in DD/MM/YYYY format> <time of task in hh:mm format>` - Add a new Deadline

Adds a new "Deadline" task with the given description, date and time to your list.

Example of usage: 

`deadline Math Assignment /by 20/10/2020 23:59`

Expected outcome:

`Added new task: Math Assignment
You now have 2 tasks in your list.`

The deadline "Math Assignment" will be added to your task list.

_NOTE: it is important that you follow the given format exactly. Duke will prompt you if you use an incorrect format._

### `event <task description> /at <date of task in DD/MM/YYYY format> <time of task in hh:mm format>` - Add a new Event

Adds a new "Event" task with the given description, date and time to your list.

Example of usage:

`event Mom's birthday /at 05/11/2020 00:00`

Expected outcome:

`Added new task: Mom's birthday 
You now have 3 tasks in your list.`

The event "Mom's birthday" will be added to your task list.

### `delete <number of the task you want to delete>` - Deletes a task

Deletes a task from your task list. The number you enter refers to the number of the task in the list. 
Be careful, this action is permanent!

Example of usage:

`delete 2`

Expected outcome:

`The task [D][X] Math Assignment (by Oct 20 2020 23:59) has been deleted from your list.`

The second task in your list (Math Assignment) will be deleted from your list.

### `done <number of task you want to mark as done>` - Marks a task as done

Marks a task in your list as done. The number you enter refers to the number of the task in the list. 
The opposite of this action is the "undone" command.

Example of usage:

`done 2`

Expected outcome:

`Congrats, I've marked this task as done!`

The second task in your list (now Mom's Birthday) will be marked as done.

### `undone <number of task you want to mark as done>` - Marks a task as not done

Marks a task in your list as not done. The number you enter refers to the number of the task in the list. 
The opposite of this action is the "done" command.

Example of usage:

`undone 2`

Expected outcome:

`I've marked that task as unfinished.`

The second task in your list (Mom's Birthday) will be marked as not done.

### `find <keywords>` - Searches your tasks containing the keywords

Searches your task list for all tasks containing the exact keywords. It works similar to Ctrl-F on your browser or text editors.

Example usage:

```
todo Buy new chair
todo Buy new table
find Buy new
```

Expected outcome:

```
I found 3 tasks matching your keywords:
1.[T][X] Buy new clothes
2.[T][X] Buy new chair
3.[T][X] Buy new table
```

### `list` - Shows you your entire list of tasks

Displays your entire list of tasks for you to read.

Example usage:

list

Expected outcome:

```
Here are your tasks:
---------------------------------------------------------------
1.[T][X] Buy new clothes
2.[T][X] Mom's Birthday (at Nov 05 2020 14:00)
3.[T][X] Buy new chair
4.[T][X] Buy new table
---------------------------------------------------------------
```

### `upcoming <X number of days>` - Shows you all upcoming tasks

Duke will display all upcoming deadlines or events occurring within the number of days you enter. 
Since ToDos have no date or time associated with them, they will not be displayed.

Example usage:

`deadline Math Assignment /by 19/09/2020 23:59
upcoming 7`

Expected outcome:

```
I found 1 task occurring in the next 7 days:

1.[D][X] Math Assignment (by: Sep 19 2020 23:59)
```

### `exit` - Quits and saves
Shuts down Duke after auto-saving your task list. If you shut down Duke without using this keyword, such as
by using Alt-F4 or Windows Task Manager, Duke will not save your task list.

Example usage:

`exit`

Expected outcome:

`Goodbye!`

Duke will then exit and the window will close.

### `help` - Help message

Displays a help message that tells you what each command does, so you don't have to refer to this guide.

Example usage:

`help`

Expected outcome:

```
Welcome to Duke! Here is a list of commands you can use:

todo <name> - adds a Todo task to your list
deadline <name> /by <time> - adds a Deadline task to your list
event <name> /at <time> - adds an Event task to your list
list - displays the current list of your tasks
done <number> - marks a task as done
undone <number> - marks a task as not done
delete <number> - deletes a task from your list
find <keyword> - displays all tasks containing the exact keyword
upcoming <number> - displays all tasks occurring within the given number of days
help - displays this helpful message
exit - shuts down the bot
```
