# User Guide
Mr. Meeseeks (yes, from Rick and Morty) is a Meeseeks-themed desktop task-management application you can use to keep track of your todos, deadlines and events. 
Its Command Line Input (CLI)-oriented features provide a fast and efficient experience for the productive keyboard-warrior.
## Quick start
1. Ensure you have Java 11 or above installed in your computer.
2. Download the latest duke.jar from [here](https://github.com/bowei-yu/ip/releases/tag/A-Release).
3. Create a new folder in your preferred directory.
4. Copy the file to that folder, which now acts as the cozy home folder for Mr. Meeseeks.
5. Double-click the file to start the app.
#### Important note: 
The executable jar file may not display ticks and crosses correctly when directly opening it through double clicks, due to a difference in encoding.
As such, you may open it from the command line by using:
```
cd <home folder for Mr. Meeseeks>
java -jar -Dfile.encoding=UTF-8 duke.jar
```
Refer to the Features and Command Usage below for details of each command.

## Features 
![Application Screenshot](https://raw.githubusercontent.com/bowei-yu/ip/master/docs/Ui.png)
### Create a task 
There are 3 types of tasks that Mr.Meeseeks would love to help you with - `todo`, `deadline` and `event`. 
`todo` contain a description of the task, while `deadline` and `event` contain a description of the task as well as its time (can be any text, or in either `yyyy-mm-dd hhmm` or `yyyy-mm-dd` format for the time to be recognised).
### Completing a task
To mark a task as completed [✓], you may use the `done` command. Mr. Meeseeks will, of course, congratulate you.
### List all tasks
You may `list` all current tasks in the format of a good ol' task list. 
### Tag a task
A perfect situation to use hashtags, a task can be tagged by giving it a hashtag `#`.
A task can be tagged several times with many tag names, including already existing ones. Pretty much Instagram style!
### Find tasks
You may get Mr. Meeseeks to `find` tasks with multiple keywords. He will show you tasks with tags and descriptions that contain any of those keywords specified.
### Get statistics
Curious to know how many tasks you've completed, are ongoing or worst, overdue?
Obtain statistics regarding your tasks by using the `stats` command.
### Delete a task
Unwanted task? Feel free to `delete` it from your task list by its numbering.
### Delete all tasks
Use the `delete all` command to delete all existing tasks from your task list. Look who's purging now? Hurray to freedom!
### Show all commands
Can't remember all these commands? Use some help by calling `help`.

## Command Usage

### `todo` - Create a todo

Format: `todo <description>`

Example of usage: 

`todo fix the car`

The expected outcome:

```
Ooooohh yeahhhh cannnnn do, I'm Mr. Meeseeks! I've added this task:
[T][✗] fix the car
Now you have 1 tasks in the list.
```

### `deadline` - Create a deadline

Format: `deadline <description> /by <yyyy-mm-dd hhmm, yyyy-mm-dd or any text>`

Examples of usage: 

- `deadline finish science project /by 2020-12-20 1400`
- `deadline finish science project /by 2020-12-20`
- `deadline finish science project /by not today :p`

Expected outcomes, respectively:

```
Ooooohh yeahhhh cannnnn do, I'm Mr. Meeseeks! I've added this task:
[D][✗] finish science project (by: Dec 20 2020, 0200PM)
Now you have 2 tasks in the list.
```
```
Ooooohh yeahhhh cannnnn do, I'm Mr. Meeseeks! I've added this task:
[D][✗] finish science project (by: Dec 20 2020)
Now you have 2 tasks in the list.
```
```
Ooooohh yeahhhh cannnnn do, I'm Mr. Meeseeks! I've added this task:
[D][✗] finish science project (by: not today :p)
Now you have 2 tasks in the list.
```

### `event` - Create an event

Format: `event <description> /at <yyyy-mm-dd hhmm, yyyy-mm-dd or any text>`

Examples of usage: 

- `event run in a stream /at 2020-11-17 1600`
- `event run in a stream /at 2020-11-17`
- `event run in a stream /at tentatively after I finished pizza`

Expected outcomes, respectively:

```
Ooooohh yeahhhh cannnnn do, I'm Mr. Meeseeks! I've added this task:
[E][✗] run in a stream (by: Nov 17 2020, 0400PM)
Now you have 3 tasks in the list.
```
```
Ooooohh yeahhhh cannnnn do, I'm Mr. Meeseeks! I've added this task:
[E][✗] run in a stream (by: Nov 17 2020)
Now you have 3 tasks in the list.
```
```
Ooooohh yeahhhh cannnnn do, I'm Mr. Meeseeks! I've added this task:
[E][✗] run in a stream (by: tentatively after I finished pizza)
Now you have 3 tasks in the list.
```

### `done` - Mark a task as done

Format: `done <task number>`

Example of usage: 

`done 2`

An expected outcome:

```
Oooh yeahhh, good job! I've marked this task as done:
[D][✓] finish science project (by: Dec 20 2020, 0200PM)
```

### `list` - List all tasks

Format: `list`

An expected outcome:

```
Yes sireee, look at me! Here are the tasks in your list:
1.[T][✗] fix the car
2.[D][✓] finish science project (by: Dec 20 2020, 0200PM)
3.[E][✗] run in a stream (by: Nov 17 2020, 0400PM)
```

### `#` - Tag a task

Format: `#<tag name (without spacing)> <task number>`

Example of usage:

- `#fun 3`
- `#relaxingtime 3`

Expected outcomes, cumulatively:

```
Yes siree! Your task is tagged:
[E][✗] run in a stream (#fun) (by: Nov 17 2020, 0400PM)
```
```
Yes siree! Your task is tagged:
[E][✗] run in a stream (#fun #relaxingtime) (by: Nov 17 2020, 0400PM)
```

### `find` - Find a task

Format: `find <keywords>`

Example of usage:

`find relax sci`

An expected outcome:

```
Oohhh look at me! Here are the matching tasks in your list:
1.[E][✗] run in a stream (#fun #relaxingtime) (by: Nov 17 2020, 0400PM)
2.[D][✓] finish science project (by: Dec 20 2020, 0200PM)
```

### `stats` - Get statistics on tasks

Format: `stats`

An expected outcome:

```
Oohhhhh cannnnn do! Here are your numbers:

Tasks done: 1
Tasks to be done: 1
Tasks that are overdue: 0
Tasks I have no clue yet to tell the time: 1
```

### `delete` - Delete a task

Format: `delete <task number>`

Example of usage: 

`delete 1`

An expected outcome:

```
I'm Mr. Meeseeks, look at me! I've removed this task:
[T][✗] fix the car
Now you have 2 tasks in the list.
```

### `delete all` - Delete all tasks

Format: `delete all`

The expected outcome:

```
All done! You have 0 tasks now.
```

### `help` - Show all commands

Format: `help`

### `exit` - Terminate the program

Format: `bye`

## Acknowledgements
- Picture of Meeseeks face is obtained from [https://dlpng.com/png/6359939](https://dlpng.com/png/6359939). 
- Picture of Morty is obtained from [https://pngimage.net/morty-head-png-4/](https://pngimage.net/morty-head-png-4/).
- Picture of Meeseeks bo obtained from [https://www.clipartmax.com/middle/m2H7d3N4Z5H7A0b1_rick-and-morty-clipart-mr-meeseeks-rick-and-morty-mr-meeseeks-box/](https://www.clipartmax.com/middle/m2H7d3N4Z5H7A0b1_rick-and-morty-clipart-mr-meeseeks-rick-and-morty-mr-meeseeks-box/)

All rights belong to the original creators. No copyright infringement intended.