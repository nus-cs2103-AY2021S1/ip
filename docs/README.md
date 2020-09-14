# User Guide for Jimmy

## Getting Started
1. Ensure you have Java 11 installed on your computer.
2. Download the [latest version of jimmy.jar](https://github.com/chewypiano/ip).
3. Copy the file to the folder you want to use as the home folder of your Jimmy program.

5. Type a command in the text box to execute it. For example, typing `help` and pressing `Send` will
provide useful information to navigate the app and use it optimally.
6. Refer to the Features section below for details of each command.

## Features 

#### Easily add tasks to your taskList
You can choose between 3 type of tasks to save to your taskList:
 - `todo` - tasks that are yet to be done 
 - `deadline` - tasks that have a set time constraint
 - `event` - tasks that signify an event
 
#### Flexible control over your taskList
You will be able to control and manage your taskList through easy-to-use functions:
 - `add` - add tasks to your taskList
 - `delete` - delete tasks from your taskList
 - `done` - mark tasks as complete
 - `list` - neatly lists out all tasks saved on your taskList
 - `find` - search for certain tasks that contain some keyword
 
#### User-friendly interface
Easily navigate the app with a simple Ui and helper functions if you get stuck:
- `help` - lets you have a run down of all available functions
- `bye` - assists you in closing the application
 


## Usage

#### `todo` - creates a todo task

Adds a todo task to your taskList.

Example of usage:

`todo borrow a book`

Expected outcome:
```
Got it. I've added this task:
  [T][✘] borrow a book
Now you have 5 tasks in the list.
```

#### `deadline` - creates a deadline task

Adds a deadline task to your taskList.

Example of usage:

`deadline return that one book /by 2020-09-20 Sunday`

Expected outcome:
```
Got it. I've added this task:
  [D][✘] return that one book (by: Sep 20 2020 Sunday)
Now you have 6 tasks in the list.
```

#### `event` - creates an event task

Adds an event task to your taskList.

Example of usage:

`event team meeting /at the library, Sunday`

Expected outcome:
```
Got it. I've added this task:
  [E][✘] team meeting (at: the library, Sunday)
Now you have 7 tasks in the list.
```

#### `delete` - deletes a task from the taskList

Deletes a task with the given task number from your taskList.

Example of usage:

`delete 5`

Expected outcome:
```
Noted. I've removed this task:
	  [T][✘] borrow a book
Now you have 6 tasks in the list.
```

#### `done` - marks task as complete

Marks a task with the given task number as complete.

Example of usage:

`done 4`

Expected outcome:
```
Nice! I've marked this task as done:
	  [T][✓] borrow a book
```

#### `find` - locates and returns tasks

Finds a task with the provided keywords.

Example of usage:

`find book`

Expected outcome:
```
Here are the matching tasks in your list:
4. [T][✓] borrow a book
5. [D][✘] return that one book (by: Sep 20 2020 Sunday)
```

#### `list` - returns all tasks in taskList

Returns all the tasks currently in the taskList.

Example of usage:

`list`

Expected outcome:
```
Here are your pending tasks:

1. [E][✓] tP team meeting (at: zoom, Sat 9pm)
2. [T][✘] 2103/T Quiz ASAP
3. [D][✘] complete iP tasks (by: Sep 18 2020 2359hrs)
4. [T][✓] borrow a book
5. [D][✘] return that one book (by: Sep 20 2020 Sunday)
6. [E][✘] team meeting (at: the library, Sunday)
```

#### `help` - Lists out all functions and their usage

Returns a list of all available functions and their descriptions

Example of usage: 

`help`

Expected outcome (snippet):
```
Here are a list of available commands:

todo <description>: Stores Todo task with given description into your TaskList.
.
.
.
help: Helps me help you! 
```

#### `bye` - Tool to close application

Bids goodbye and guides you on how to close the application.

Example of usage: 

`bye`

Expected outcome (snippet):
```
Bye. Hope to see you again soon!
Click the " X " button to close this window.
```

<hr />

That's it for the user guide. Send an email to __*gimikamil@gmail.com*__ for any queries.\
Bye. Hope to see you again soon!

<hr />