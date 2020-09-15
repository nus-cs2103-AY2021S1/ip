# User Guide 

YURINA CHAN is a todo App that helps you to manage your daily tasks better.

![Image of YURINA CHAN](https://github.com/SherryWu178/ip/blob/master/docs/Ui.png)

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
1. After the importing is complete, locate the `src/main/java/duke/Launcher.java` file, right-click it, and choose `Run Launcher.main()`. 
## Features 

### Add Tasks 
You can add different types of tasks easily. We support the following types of tasks:
- todo (task without specific time)
- deadline (task to be complete before a specifc time)
- event (task on a specific date)
- period-task (task to be complete within a period)

### List Tasks

### Delete Tasks 

### Mark Tasks as complete

### Delete Tasks

### Find Tasks based on keywords



## Usage

### `todo name` - Add a task with given name.

Example of usage: 

`todo finish 2103 level 10`

Expected outcome:

`Got it.(^∇^) I've added this task:
[T][✗] finish 2103 level 10`


### `deadline name /by date` - Add a deadline due on given date.

Example of usage: 

`deadline return books /by 2020-09-11`

Expected outcome:

`Got it.(^∇^) I've added this task:
[D][✗] return books (by: 11 Sep 2020)`


### `event name /at date` - Add an event happening on given date.

Example of usage: 

`event Hazel's birthday /at 2020-09-19`

Expected outcome:

`Got it.(^∇^) I've added this task:
[E][✗] Hazel's birthday (at: 19 Sep 2020)`

### `period-task name /from date /to date` - Add a task should be completed within a specific time range.

Example of usage: 

`period-task collect ic /from 2020-08-14 /to 2020-09-14`

Expected outcome:

`Got it.(^∇^) I've added this task:
[P][✗] collect ic (from: 14 Aug 2020 to: 14 Sep 2020)`

### `done index` - Mark the task with given index as done.

Example of usage: 

`done 1`

Expected outcome:

`Nice!(^∇^) I've marked this task as done:
[P][✓] finish 2103 level 10`

### `delete index` - Delete the task with given index as done.

Example of usage: 

`delete 1`

Expected outcome:

`Got it.(^∇^) I've deleted this task::
[P][✗] finish 2103 level 10`

### `list (date)` - List all the tasks saved. If a date parameter is included, it returns tasks happening on that day.

This action delete task with given index as done.

Example of usage: 

`list`

Expected outcome:

`[D][✗] 1.return books (by: 11 Sep 2020)`

`[E][✓] 2.Hazel's birthday (at: 19 Sep 2020)`

`[P][✓] 3.collect ic (from: 14 Aug 2020 to: 14 Sep 2020)`


### `find` - Find tasks which contains the keyword

Example of usage: 

`find birthday`

Expected outcome:

`[E][✓] 2.Hazel's birthday (at: 19 Sep 2020)`

### `bye` - saves all the tasks locally.

Example of usage: 

`bye`

Expected outcome:

`Bye~ Hope to see you again soon! (❛‿❛✿̶̥̥)`
