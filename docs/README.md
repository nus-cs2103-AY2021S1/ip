# Duke User Guide

## Introduction
Duke (a.k.a JonasBot) is a personalised chat bot that functions as a task manager, allowing
users to perform a variety of task related functions.
Duke maintains some features of a Graphical User Interface (GUI) but is ideal for users
who prefer a Command Line Interface (CLI).

## Getting Started
1. Ensure that you have Java `11` or above installed on your device.
2. You can find the latest version of Duke [here](https://github.com/jonasngs/ip/releases/tag/v0.2).
3. Download the `duke.jar` file and open the jar file to start the application.
4. You should be able to see the following application:
![DukeStart Image](/DukeStart.png)
5. Enter commands into the textbox located at the bottom of the application and click `Send`.

## Duke Functions and Commands

### 1. Create a `todo` task 
**Command**: `todo <todo description>` <br/>
**Function**: Creates a new todo task and adds it to the user's list of tasks. <br/>
**Example of usage**: 
```
todo eat 
```
**Expected outcome**: 
```
Success! This task has been added:
[T][x] eat
You have 1 tasks in your list now. 
```

### 1. Create a `todo` task 
**Command**: `todo <todo description>` <br/>
**Function**: Creates a new todo task and adds it to the user's list of tasks. <br/>
**Example of usage**: 
```
todo eat 
```
**Expected outcome**: 
```
Success! This task has been added:
[T][✗] eat
You have 1 tasks in your list now.

