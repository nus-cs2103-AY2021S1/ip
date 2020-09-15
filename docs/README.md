# User Guide for Duke
_Lum Jian Yang Sean | A0199758Y_

<p align="center"><img width="100%" src="Ui.png"/></p>

Welcome to Duke, a task manager app to track your todos, deadlines and events.

## Table Of Contents
* [Introduction](#introduction)
    * [About](#about) 
    * [Getting Started](#getting-started)
* [Features](#features)
* [Usage](#usage)
    * [Todo](#todo)
    * [Deadline](#deadline)
    * [Event](#event)
    * [List](#list)
    * [Done](#done)
    * [Delete](#delete)
    * [Find](#find)
    * [Remind](#remind)
    * [Help](#help)
    * [Bye](#bye)
* [FAQ]()
* [Credits](#credits)

## Introduction

A JavaFX-Powered Task Manager App

### About

This is a java project created for CS2103T Software Engineering for AY2020/2021 Semester 1.

Author: Lum Jian Yang Sean <br/>
Version: 0.1.4 <br/>
Release Date: 14 Sepetember 2020 <br/>
Github: <a href="http://github.com/seanjyjy">https://github.com/seanjyjy</a> <br />

### Getting Started

* Java JDK 11 <br />
Download and install jdk 11 from: <a href="https://www.oracle.com/technetwork/java/javase/downloads/jdk11-downloads-5066655.html">
https://www.oracle.com/technetwork/java/javase/downloads/jdk11-downloads-5066655.html
</a>

* Mac / Linux / Windows <br/>
    Double click the jar file to run or `java -jar Duke-0.1.4.jar` in the path where you have the jar file.

## Features 
* Add different types of task _e.g._ `todo`, `deadline`, `event`
* Display the list of tasks _e.g._ `list`
* Marks a task as done _e.g._ `done`
* Delete tasks _e.g._ `delete`
* Find tasks using a keyword _e.g._ `find`
* Set a reminder on a task _e.g._ `remind`
* Exit and save the data _e.g._ `bye`
* Case-insensitive commands allowed _e.g._ `TODO`, `EveNT`
* Platform compatibility with Windows, MacOS, Linux

## Usage

### Notes about the command command format:
   * Words in `UPPER_CASE` are the parameters to be supplied by the user.
     e.g. `todo DESCRIPTION`, `DESCRIPTION` is a parameter which can be used as `todo task1`.
   
   
### `deadline`
Deadline is a task with description, a completion status, a reminder status and a date. Note that the date format must follow either `yyyy/mm/dd HH:mm` or `yyyy/mm/dd`. By default reminder status is set to false and could only be activated/deactivated using the `remind` command.

Format:
`deadline DESCRIPTION /by DATE`

Example of usage: 

`deadline Project Duke submission /by 2020-12-11 2312`

Expected outcome:
```
Got it. I've added this task:
[D][✘] Project Duke submission (by: Friday, 11 Dec 2020, 11:12pm)
Now you have <Int> tasks in the list.
```

### `event`
Event is a task with description, a completion status, a reminder status and a date. Note that the date format must follow either `yyyy/mm/dd HH:mm` or `yyyy/mm/dd`. By default reminder status is set to false and could only be activated/deactivated using the `remind` command.

Format:
`event DESCRIPTION /at DATE`

Example of usage: 

`event Project Duke meeting /at 2020-12-11 2312`

Expected outcome:
```
Got it. I've added this task:
[E][✘] Project Duke meeting (at: Friday, 11 Dec 2020, 11:12pm)
Now you have <Int> tasks in the list.
```
 
### `todo`
Todo is a task with a description, a completion status and a reminder status. By default reminder status is set to false and could only be activated/deactivated using the `remind` command.

Format:
`todo DESCRIPTION`

Example of usage: 

`todo Project Duke`

Expected outcome:
```
Got it. I've added this task:
[T][✘] Project Duke
Now you have <Int> tasks in the list.
```
### `list`
Your tasks will be displayed out for you.

Example of usage: 

`list`

Expected outcome:
```
Here are the tasks in your list:
1.  [D][✘] Project Duke submission (by: Friday, 11 Dec 2020, 11:12pm)
2.  [E][✘] Project Duke meeting (at: Friday, 11 Dec 2020, 11:12pm)
3.  [T][✘] Project Duke

```

### `done`
The task to be marked is indicated by the index after the `done` command, you can view the index of the task by typing `list`.

Format:
`done INTEGER`

Example of usage: 

`done 1`

Expected outcome:
```
Nice! I've marked this task as done:
[D][✓] Project Duke submission (by: Friday, 11 Dec 2020, 11:12pm)
```

### `delete`
The task to be deleted is indicated by the index after the `done` command, you can view the index of the task by typing `list`.

Format:
`delete INTEGER`

Example of usage: 

`delete 1`

Expected outcome:
```
Noted. I've removed this task:
[D][✓] Project Duke submission (by: Friday, 11 Dec 2020, 11:12pm)
Now you have <Int> tasks in the list.
```

### `find`
Find tasks thats contains the keyword, note that only 1 keyword is allowed currently.

Format:
`find KEYWORD`

Example of usage: 

`find duke`

Expected outcome:
```
Here are the matching tasks in your list:
[E][✘] Project Duke meeting (at: Friday, 11 Dec 2020, 11:12pm)
[T][✘] Project Duke
```

### `remind`
The task to be reminded is indicated by the index after the `remind` command, you can view the index of the task by typing `list`. Also, state whether to activate or deactivate the reminder using `y` for yes, `n` for no after the `remind` command.

Format:
`remind INTEGER Y/N`

Example of usage: 

`remind 1 y`

Expected outcome:
```
The reminder of this task Project Duke, has been activated
```

### `help`
The list of commands available in duke will be displayed as well as its usage.

Example of usage: 

`help`

Expected outcome:
```
Here are the list of commands available:
1. list
2. bye
3. todo 'task name' (e.g. todo task 1)
4. delete ___ (e.g. delete 1) *Note that it should be a value more than 0*
5. done ___ (e.g. done 1) *Note that it should be a value more than 0*
6. find ___ (e.g. find book) *Note that only 1 keyword is allowed*
7. remind _ _ (e.g.remind 1 y)
   *Sets reminder on task 1 in task list, y or n represents yes or no respectively*
8. deadline 'task name' /by 'any date format'
   (e.g. deadline project /by YYYY-MM-DD HHMM or YYYY-MM-DD)
9. event 'event name' /at 'any date format'
   (e.g. event project /at YYYY-MM-DD HHMM or YYYY-MM-DD)   
```

### `bye`
A message will be displayed and after a 1.5s delay, the program will close.

Example of usage: 

`help`

Expected outcome:
```
Bye ^.^, Hope to see you again soon!!!
```

## FAQ
**Where does duke store its task?** <br/>
Duke stores its tasks in a csv file. The path for the text file can be found in `./duke.Duke/todoList.csv`

**What happens if I do not have `./duke.Duke` directory in my computer?** <br/>
Duke will automatically generate this directory for you.

**What happens if I accidentally deleted the `.csv`file?** <br/>
Duke will automatically generate this todoList.csv for you. However, once deleted, the data is not recoverable

**Duke windows too small** <br/>
Duke allows interactive resizing!

**Am I able to customized the colors of Duke?** <br/>
Unfortunately we did not include any customized feature.

## Credits

### External Packages Used
* [JUnit by JUnit Team](https://github.com/junit-team/junit5/) - For testing Duke.
* [JavaFX by OpenJDK](https://github.com/openjdk/jfx) - For creating the graphical user interface.

### Images Used

* [User image](https://www.freepik.com/premium-vector/cute-cartoon-character-doctor-style_4424223.htm)
* [Duke image](https://www.pngitem.com/middle/hmJbxbb_love-stitch-cute-hearts-disney-stitch-cute-whit/)
* [Welcome image](https://www.hotpng.com/free-png-clipart-gnkqq)
