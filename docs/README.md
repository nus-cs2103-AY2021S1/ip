# User Guide
This user guide will provide an extensive overview of the features available to users of Bob, the personal assistant.
Since Bob is interacted with primarily through the input of commands, this guide will consist mainly of the available
commands and respective formats.
* Table of Contents
{:toc}

## Quick Start
1. Ensure you have Java 11 or above installed.
2. Download the latest version of `Bob.jar` from [releases](https://github.com/Caleblyx/ip/releases).
3. Find a home for Bob! Move `Bob.jar` to your preferred home directory for the program. Note that data 
used by Bob will be stored in this directory.
4. Execute Bob.jar by double-clicking it (or your preferred method).

After following the steps above, Bob's chat box should appear. Input commands in the field bar to interact 
with Bob.
## Features 
### Notes about the command syntax
Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

### Adding tasks
Bob's primary function is to track pending tasks of user in a `Task List`.
There are three types of tasks that may be added to Bob's `Task List`:
   * To do's
   * Deadlines
   * Events
   
#### Adding To Do's : `todo`
Adds a `To do` to Bob's `Task List`.

Format: `todo DESCRIPTION`

* `DESCRIPTION`may be a brief description of the to do.

Examples:
* `todo mop the floor`
* `todo tidy backpack`

#### Adding Deadlines: `deadline`
Adds a task with a `deadline` to Bob's `Task list`.

Format: `deadline DESCRIPTION /by DATE TIME`  
* `DESCRIPTION`may be a brief description of the deadline.
* `DATE` must be inputted using the following format: `YYYY-MM-dd`.
* `TIME` must be inputted using 24 hour time and the following format: `HHmm`.

Examples:
* `deadline finish mathematics assignment /by 2020-05-19 2359`
* `deadline email project plan to co-ordinator /by 2020-08-12 1000`

#### Adding Events: `event`
Adds an `event` (i.e a task that occurs over a period of time) to Bob's `Task List`.

Format: `event DESCRIPTION /at SDATE STIME to EDATE ETIME`
* `DESCRIPTION`may be a brief description of the deadline.
* `SDATE` is the starting date of the event and must be inputted using the following format: `YYYY-MM-dd`.
* `STIME` is the starting time of the event and must be inputted using 24 hour time and the following format: `HHmm`.
* `EDATE` is the ending date of the event and must be inputted using the following format: `YYYY-MM-dd`.
* `ETIME` is the ending time of the event and must be inputted using 24 hour time and the following format: `HHmm`.

Examples:
* `event meet friends for lunch /at 2020-06-18 1100 to 2020-06-18 1200`
* `event project meeting /at 2020-08-22 1300 to 2020-08-22 1400`


### Listing all tasks on the Task List: `list`
Lists all the tasks on Bob's `Task List`. Shows the `index` assigned to each task.

Format: `list`

### Delete tasks from the Task List: `delete`

Deletes a task at a provided index from the `Task List`.

Format: `delete INDEX`

* `INDEX` must be an index of a task on the `Task List` for this feature to function successfully.

Example:
* `delete 1`

Alternatively, the following deletes all tasks from the `Task List`.

Format: `delete all`

### Marking tasks on the Task List as done: `done`

Marks a task at a provided index on the `Task List` as done.

Format: `done INDEX`
* `INDEX` must be an index of a task on the `Task List` for this feature to function successfully.
  
Example:
  * `done 1`
  
Alternatively, the following marks all on the `Task List` as done.

Format: `done all`

### Finding tasks with keywords: `find`
Finds a list of tasks that contain provided keywords or phrases.

Format: `find KEYWORDS`

* `KEYWORDS` is the keywords or phrases to be searched for in the `Task List`.

Example:
* `find homework`
* `find Mar`

### Snoozing deadlines: `snooze`
Snoozes the deadline of a task at an index to a provided deadline.

Format: `snooze INDEX /to DATE TIME`

* `INDEX` must be an index of a task with a deadline on the `Task List` for this feature to function successfully.
* `DATE` must be inputted using the following format: `YYYY-MM-dd`.
* `TIME` must be inputted using 24 hour time and the following format: `HHmm`.

Example:
* `snooze 1 /to 2020-12-20 0800`

### Reschedule events: `reschedule`
Reschedules the period of an event at an index to a provided period.

Format: `reschedule INDEX /to SDATE STIME to EDATE ETIME`

* `INDEX` must be an index of a task with a deadline on the `Task List` for this feature to function successfully.
* `SDATE` is the starting date of the event and must be inputted using the following format: `YYYY-MM-dd`.
* `STIME` is the starting time of the event and must be inputted using 24 hour time and the following format: `HHmm`.
* `EDATE` is the ending date of the event and must be inputted using the following format: `YYYY-MM-dd`.
* `ETIME` is the ending time of the event and must be inputted using 24 hour time and the following format: `HHmm`.

Example:
* `reschedule 3 /to 2020-04-20 0900 to 2020-04-20 1000`

### Exiting the command: `bye`
Exits the program.

Format: `bye`

### Loading and saving data
Commands do not have be provided to load or save data from or to the hard disk respectively, since Bob automatically saves any changes regarding data
and loads saved data automatically.


## Command summary

Action | Format
--------|------------------
**Add** |  `todo DESCRIPTION`<br> `deadline DESCRIPTION /by DATE TIME`  <br> `event DESCRIPTION /at SDATE STIME to EDATE ETIME`
**List** | `list`
**Delete** | `delete INDEX`<br> -or- <br> `delete all`
**Done** | `done INDEX` <br> -or- <br> `done all`
**Find** | `find KEYWORD`
**Snooze** | `snooze INDEX /to DATE TIME`
**Reschedule** | `reschedule INDEX /to SDATE STIME to EDATE ETIME`
**Bye** | `bye`

## Acknowledgements
* The format of this user guide was adapted from [*SE Education Address Book level 3's User Guide*](https://github.com/se-edu/addressbook-level3/blob/master/docs/UserGuide.md)