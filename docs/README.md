# User Guide
**Alpha** is a **desktop chatbot app for managing tasks**, 
optimized for use via a Command Line Interface. 
Use Alpha to quickly get your life on-task! Here's a 
quick breakdown of Alpha's features.
 
## Features

### Add a task
Alpha supports 3 types of tasks:
1. To-do (basic task)
2. Deadline (to-do with a deadline)
3. Event (has a start and end time) 

#### Add to-do: `todo`
Adds a to-do to the task list.

Format: `todo <Description>`

Examples: 
* `todo finish Sci-Fi readings for class`
* `todo buy eggs and chicken` 

#### Add deadline: `deadline`
Adds a deadline to the task list.

Format: `deadline <Description> /by <Time>`

_Note: Time format =_ `Date Time` = `YYYY-MM-dd hhmm`

Examples: 
* `deadline Philosophy essay /by 2020-09-18 2359`
* `deadilne Figma UI markup /by 2020-09-25 1800`

#### Add event: `event`
Adds an event to the task list.

Format: `event <Description> /at <Time>`

_Note: Time format =_ `Date startTime-endTime` = `YYYY-MM-dd hhmm-hhmm`

Examples: 
* `event Art Exhibition Opening /at 2020-09-20 1800-2200`
* `event Ultra Hackathon /at 2020-10-14 0800-2300`

### View task list: `list`
Lists out all tasks in the task list.

Example of usage: `list`

Expected outcome: 
```
Here are the tasks in your list:
    1. [T][✓] read book
    2. [D][✘] return book (by: June 6 2020)
    3. [E][✘] project meeting (at: Aug 6 2020, 1400hrs-1600hrs)
    4. [T][✓] join sports club
    5. [T][✘] borrow book
```

### Removing a task: `delete`
**Removes** a task from the task list.

Format: `delete <index>`
* Tasks are identified by index
* The index refers to the index number shown in the displayed task list.
* Index begins from 1. It needs to be a positive integer.

Examples:
* `delete 1` 
* `delete -1` -> `ERROR` 

### Mark task as complete: `done`
Marks a task as **completed**.

Format: `done <index>`
* Tasks are identified by index
* The index refers to the index number shown in the displayed task list.
* Index begins from 1. It needs to be a positive integer.

Examples:
* `done 1` 
* `done -1` -> `ERROR` 

### Find a task: `find`
Find a task whose description contains the given search term.

Format: `find <Search Term>`
* search term is a `String` searching for a match amongst the task descriptions
* search term is case-insensitive

Example of usage: 
`find ultra`

Expected outcome: 
```
Here are the matching tasks in your list:
    1. [E][X] Ultra hackathon (at: Oct 14 2020, 0800hrs - 2300hrs)
    2. [T][/] ultra difficult essay 
```

### Postpone a deadline/event: `postpone`
Postpone a deadline or event.

Format: `postpone <index> <amount> <time unit>`

Supported time units: 
* `year(s)`
* `month(s)`
* `day(s)`
* `hour(s)`
* `minute(s)`

Example of usage: 
```
>> list
Here are the tasks in your list:
    1. [T][✓] read book
    2. [D][✘] return book (by: June 6 2020)
    3. [E][✘] project meeting (at: Aug 6 2020, 1400hrs-1600hrs)
    4. [T][✓] join sports club
    5. [T][✘] borrow book

>> postpone 3 2 hours 
Pushing it back by 2 hours.
Okay, I've postponed it. Updated as follows:
    [E][✘] project meeting (at: Aug 6 2020, 1600hrs-1800hrs)
```

### Advance a deadline/event: `postpone`
Advance (brings forward) a deadline or event.

Format: `advance <index> <amount> <time unit>`

Supported time units: 
* `year(s)`
* `month(s)`
* `day(s)`
* `hour(s)`
* `minute(s)`

Example of usage: 
```
>> list
Here are the tasks in your list:
    1. [T][✓] read book
    2. [D][✘] return book (by: June 6 2020)
    3. [E][✘] project meeting (at: Aug 6 2020, 1400hrs-1600hrs)
    4. [T][✓] join sports club
    5. [T][✘] borrow book

>> advance 3 2 hours 
Bringing it forward by 2 hours.
Okay, I've postponed it. Updated as follows:
    [E][✘] project meeting (at: Aug 6 2020, 1200hrs-1400hrs)
```


## Command Summary

Action | Format, Examples
_________|_________
Add To-do |  `todo <Description>`<br/>E.g.: `todo finish Sci-Fi readings for class` 
Add Deadline | asd
Add Event |  asd
List | asd
Delete | asd
Done | asd
Find |  ads
Postpone | ads
Advance | ads
