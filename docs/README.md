# User Guide

Welcome to `CatBot` user guide. 

![Image of CatBot](https://github.com/luo-git/ip/raw/master/docs/catbot.gif)

CatBot is a chat bot that can help you to organise your tasks and schedule.

## Quick Start

1. Ensure that you have Java `11` or above installed.
2. Download the latest `catbot.jar` from [here](https://github.com/luo-git/ip/releases).
3. Copy the file to the folder you want to use as the home folder for your AddressBook.
4. Start the jar file from a terminal using `java -jar catbot.jar`. The GUI should appear in a few seconds.
5. Type in a command in the command box to execute it. Type `help` to see all the commands available.
6. Refer to features below for details of each command.

## Features

> Notes about the command format:
>
> Words in `UPPER_CASE` are the parameters to be supplied by the user.
> e.g. in add `/at TIME`, `TIME` is a parameter which can be used as `event abc /at 2020 11 11`.
>
> Items in square brackets are optional.
> e.g `help [KEYWORD]` can be used as `help event` or as `help`.
> 
> Time representation:
> Format of time can be `DD MM YYYY [HH MM]` or `YYYY MM DD [HH MM]` or a string. 
> Date segment is `DD MM YY` and time segment is `HH MM`.
> Delimiter between date can be `.`, `space`, `-` or `/`.
> Delimiter between time can be `.`, `space` or `:`.
> Delimiter between date and time can be `space` or `T`.

### Add Todo Task: `todo`
Create a new todo task.

Usage: `todo DESCRIPTION`

Example of usage: `todo homework`

Expected outcome: Create a new `todo` task with description `homework`.

### Add Deadline Task: `deadline`
Create a new deadline task.

Usage: `deadline DESCRIPTION /by TIME`

Example of usage: `deadline homework1 /by 2020-11-11`

Expected outcome: Create a new `deadline` task with description `homework1` and time `2020-11-11`.

### Add Event Task: `event`
Create a new event task.

Usage: `event DESCRIPTION /at TIME`

Example of usage: `event summer camp /at 2020-11-11 11:22`

Expected outcome: Create a new `event` task with description `summer camp` and time `2020-11-11 11:22`.

### List tasks: `list`
List the tasks stored. Optionally, list tasks on a specific date.

Usage: `list [TIME]`

Example of usage: `list 2020-11-11`

Expected outcome: List all task on `2020-11-11`.

### Mark Task As Done: `done`
Mark a task at some index as done.

Usage: `done INDEX`

Example of usage: `done 1`

Expected outcome: Mark task at index `1` as done.

### Delete Task: `delete`
Delete a task at some index.

Usage: `delete INDEX`

Example of usage: `delete 1`

Expected outcome: Delete task at index `1`.

### Find Task: `find`
Find a task based on description

Usage: `find DESCRIPTION`

Example of usage: `find homework`

Expected outcome: List all task with `homework` in description.

### Sort Tasks: `sort`
Sort tasks based on description or time.
Task without a time have higher precedence.

Usage: `sort description` or `sort time`

Example of usage: `sort time`

Expected outcome: Sort tasks stored by time.

### List tasks: `help`
Get help message. Command can be specified to show help for that command.

Usage: `help [KEYWORD]`

Example of usage: `help list`

Expected outcome: Show help message for command `list`.

### List tasks: `bye`
Exit program.

Usage: `bye`

Example of usage: `bye`

Expected outcome: Program will close in 5 seconds.

## Acknowledgement
Cat picture is from [Chi's Sweet Home](https://en.wikipedia.org/wiki/Chi%27s_Sweet_Home) by Kanata Konami.
All rights belong to the original creator.
No copyright infringement intended.