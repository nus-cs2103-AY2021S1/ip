# Patrick Star Bot - User Guide

Patrick Star Bot is a chat bot for keeping track of simple tasks. It is based on the characters of *Patrick Star* and *Man Ray* from the hit Nickelodeon television show, *SpongeBob SquarePants*.

![Image of chat bot](./Ui.png)

## Features 

### Types of tasks:
* `Todo` - Todo [T] tasks are tasks that are to be completed at some point in time but do not have a specific date or time associated to them.
* `Deadline` - Deadline [D] tasks are tasks that have to be completed by some specific date and optionally time.
* `Event` - Event [E] tasks are tasks that occur at a specific date and optionally time.

### Date and times:
* Deadlines and events require a date to be associated to them. Additionally, they can have a time associated to them.
* Dates can be in the format `day/month/year`, `day-month-year`, `day month year`, `yyyy/mm/dd`, `yyyy-mm-dd` or `yyyy mm dd`.
* For the formats where day comes first, i.e. `day/month/year`, `day-month-year` and `day month year`, day can be `03` or `3`, month is case-insensitive and can be `01` or `1` or `Jan` or `January`, and year can be `2011` or `11`.
* For the formats where year comes first, i.e. `yyyy/mm/dd`, `yyyy-mm-dd` or `yyyy mm dd`, the year, month and day must be in their full numerical representaions e.g. `2012-03-07`.
* Alternatively, dates can be days of the week: `Tuesday` or `Tues`, or the words `yesterday`, `today` and `tomorrow`. 
* Time must be in the 24h format `HH:mm`.

### List of commands:
* `todo` - adds a to do task with neither specific date nor time
* `deadline` - adds a deadline task with a specific date and optionally time to be completed by
* `event` - adds an event task with a specific date and optionally time at which it occurs
* `list` - lists out all the current tasks stored in the chat bot
* `done` = marks the specified task as done
* `delete` - deletes the specified task from the chat bot
* `view` - views all the tasks stored in the chat bot that occur on the specified date
* `find` - finds all the tasks stored in the chat bot that contains the specified keyword
* `bye` - exits the chat bot



## Usage
Note: 
* Words in `UPPER_CASE` are the parameters to be supplied by the user.
* Items in square brackets i.e. `[` and `]` are optional.

### `todo` - adds a to do task

Adds a to do task with the specified description. 

Format: `todo DESCRIPTION`

Example of usage: 

`todo maths homework`

Expected outcome:

![todo maths homework](./images/todoExample.png)


### `deadline` - adds a deadline task

Adds a deadline task with the specified description, date and optionally time to be completed by.

Format: `deadline DESCRIPTION /by DATE [TIME]`

Examples of usage: 

`deadline assignment 1 /by 11 September 2011`

`deadline assignment 2 /by 18 October 2011 23:50`

Expected outcome:

![deadline assignment 1 /by 11 September 2011 and deadline assignment 2 /by 18 October 2011 23:50](./images/deadlineExample.png)


### `event` - adds an event task

Adds an event task with the specified description, date and optionally time at which it occurs.

Format: `event DESCRIPTION /at DATE [TIME]`

Examples of usage: 

`event New Year's Day /at 1 Jan 2011`

`event birthday party /at 2/2/2011 12:00`

Expected outcome:

![event New Year's Day /at 1 Jan 2011 and event birthday party /at 2/2/2011 12:00](./images/eventExample.png)


### `list` - lists out all tasks

Lists out all the tasks the chat bot currently has stored. 

Format: `list`

Example of usage:

`list`

Expected outcome:

![list](./images/listExample.png)


### `done` - marks task as done

Marks the task with the specified task number as done.

Format: `done TASK_NUMBER`

Example of usage:

`done 1`

Expected outcome:

![done 1](./images/doneExample.png)


### `delete` - delete task

Deletes the task with the specified task number from the chat bot.

Format: `delete TASK_NUMBER`

Example of usage:

`delete 1`

Expected outcome:

![delete 1](./images/deleteExample.png)


### `view` - view tasks on a date

Shows all the tasks with the specified date. Note that view only works with dates and not times.

Format: `view DATE`

Example of usage: 

`view 01/01/2011`

Expected outcome:

![view 01/01/2011](./images/viewExample.png)


### `find` - find tasks with the keyword

Shows all the tasks with the specified keyword.

Format: `find KEYWORD`

Example of usage:

`find assignment`

Expected outcome:

![find assignment](./images/findExample.png)


### `bye` - exits the chat bot.

Exits the chat bot.

Format: `bye`

Example of usage:

`bye`

Expected outcome:

![bye](./images/byeExample.png)
