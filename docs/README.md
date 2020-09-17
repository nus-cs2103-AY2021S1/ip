# User Guide
Mr. Meeseeks (yes, from Rick and Morty) is a Meeseeks-themed desktop task-management application you can use to keep track of your todos, deadlines and events. 
Its Command Line Input (CLI)-oriented features provide a fast and efficient experience for the productive keyboard-warrior.
## Features 
![Application Screenshot](https://raw.githubusercontent.com/bowei-yu/ip/master/docs/Ui.png)
### Create a task 
There are 3 types of tasks that Mr.Meeseeks would love to help you with - `todo`, `deadline` and `event`. 
`todo` contain a description of the task, while `deadline` and `event` contain a description of the task as well as its time (can be any text, or in either `yyyy-mm-dd hhmm` or `yyyy-mm-dd` format for the time to be recognised).
### List all tasks
You may `list` all current tasks in the format of a good ol' task list. 
### Completing a task
To mark a task as completed, you may use the `done` command. Mr. Meeseeks will, of course, congratulate you.
### Tag a task
A perfect situation to use hashtags, a task can be tagged by giving it a hashtag `#`.
### Find tasks
You may get Mr. Meeseeks to `find` tasks with a keyword. He will show you tasks with tags and descriptions that contain that keyword.
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

### `todo` - Create a todo task

Format: `todo <description>`

Example of usage: 

`todo fix the car`

Expected outcome:

```
Ooooohh yeahhhh cannnnn do, I'm Mr. Meeseeks! I've added this task:
[T][✗] fix the car
Now you have 5 tasks in the list.
```

### `deadline` - Create a deadline

Format: `deadline <description> /by <yyyy-mm-dd hhmm, yyyy-mm-dd or any text>`

Example of usage: 

`deadline finish science project /by 2020-12-20 1400`

Expected outcome:

```
Ooooohh yeahhhh cannnnn do, I'm Mr. Meeseeks! I've added this task:
[D][✗] finish science project (
Now you have 5 tasks in the list.
```

## Important note
The executable jar file may not display ticks and crosses correctly when directly opening it with double clicks, due to a difference in encoding.
As such, you may open it from the command line by using:
```
java -jar -Dfile.encoding=UTF-8 duke.jar
```