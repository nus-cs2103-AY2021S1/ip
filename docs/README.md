# User Guide
Duke is a desktop app for managing tasks, optimized for use via a Command Line Interface (CLI).

##### Tables of Contents
[Features](#features)
* [Adding a task](#add)
* [Listing all tasks](#list)
* [Locating task by description](#find)
* [Finishing a task](#done)
* [Deleting a task](#delete)
* [Archiving a task](#archive)

[FAQ](#faq) <br>
[Command Summary](#command_summary)

<a name="features"/>
## Features 
Note:

* Words in `UPPER_CASE` care the parameters to be supplied by the user. e.g. in `todo TASK`, `TASK` is a parameter which can be used as `todo read book`.

* Items in square brackets are optional. e.g. `deadline TASK /by DATE [TIME]` can be used as `deadline watch lecture /by 14/09/2020` or as `deadline watch lecture /by 14/09/2020 1600`

<br>
<a name="add"/>
### Adding a task: `todo`, `deadline`, `event` 
Adds a task into the list

Format:
* `todo DESCRIPTION`
* `deadline DESCRIPTION /by DATE [TIME]`
* `event DESCRIPTION /at DATE [TIME]`


Comments: 
* `DATE` must be in `dd/mm/yyyy` format.
* `TIME` must be in `hhmm` 24 hours format.

Examples:
* `todo read book`
* `deadline return book /by 15/09/2020`
* `event watch lecture /at 16/09/2020 1600`

<br>
<a name="list"/>
### Listing all tasks: `list`
Shows a list of all tasks.

Format: `list`

<br>
<a name="find"/>
### Locating task by description: `find`
Finds tasks whose description contain the given keyword

Format: `find KEYWORD [MORE_KEYWORDS]`

Comments:
* The search is case-sensitive
* The order of keywords matter
* Only the description will be search
* Only full words will be matched

Examples:
* `find book` will return `read book` and `return book`.


<br>
<a name="done"/>
### Finishing a task: `done`
Marks the specified task as completed.

Format: `done INDEX`

Comments:
* Marks the task at the specified index
* Index refers to task number
* The index must be a positive integer 1, 2, 3, ...

Examples:
* `done 1` will mark the first task as complete.


<br>
<a name="delete"/>
### Deleting a task: `delete`
Deletes the specified task from the list.

Format: `delete INDEX`

Comments:
* Deletes the task at the specified index
* Index refers to task number
* The index must be a positive integer 1, 2, 3, ...

Examples:
* `delete 1` deletes the first task in the list.


<br>
<a name="archive"/>
### Archiving a task: `archive`
Archives the specified task in the list.

Format: `archive INDEX`

Comments:
* Archives the task at the specified index
* Index refers to task number
* The index must be a positive integer 1, 2, 3, ...
* The task is deleted from the main list and added into a text file containing the archived tasks.
* The archive text file can be found under `/data/archive.txt`

<a name="faq"/>
## FAQ
**Q**: Do I need to save the data?  <br>
**A**: No, the tasks are saved automatically every time the list changes.

<a name="command_summary"/>
## Command Summary
| Action | Format, Examples |
| --- | --- |
| Todo | `todo DESCRIPTION` <br> e.g. `todo read book`|
| Deadline | `deadline DESCRIPTION /by DATE [TIME]`<br> e.g. `deadline return book /by 24/08/2020`|
| Event | `event DESCRIPTION /at DATE [TIME]` |
| List | `list` |
| Find | `find KEYWORD [MORE_KEYWORDS]` |
| Done | `done INDEX` |
| Delete | `delete INDEX`|
| Archive | `archive INDEX`|