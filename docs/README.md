# User Guide
Duke is a free program which act as a **personal chat bot to help you manage your tasks**.\
Duke is a **desktop application optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, Duke can allow you to manage and organise your tasks faster than traditional GUI apps.

- [Quick start](#quick-start)
- [Features](#features)
    1. [Viewing help: `help`](#viewing-help-help)
    2. [Adding a todo: `todo`](#adding-a-todo-todo)
    3. [Adding a deadline: `deadline`](#adding-a-deadline-deadline)
    4. [Adding an event: `event`](#adding-an-event-event)
    5. [Listing all tasks: `list`](#listing-all-tasks-list)
    6. [Deleting a task: `delete`](#deleting-a-task-delete)
    7. [Marking a task as done: `done`](#marking-a-task-as-done-done)
    8. [Retrieving tasks by date: `retrieve`](#retrieving-tasks-by-date-retrieve)
    9. [Finding tasks by keyword: `find`](#finding-tasks-by-keyword-find)
    10. [Sorting tasks: `sort`](#sorting-tasks-sort)
    11. [Exiting the program: `bye`](#exiting-the-program-bye)
    12. [Saving the data](#saving-the-data)
- [FAQ](#faq)
- [Command summary](#command-summary)


--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `duke.jar` from [here](https://github.com/nweiyue/ip/releases/tag/A-Release).

1. Copy the file to the folder you want to use as the _home folder_ for your Duke.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. <br>
   <img src="/docs/StartPage.png" alt="StartPage"/>

1. Type the command in the command box and press ENTER/click on the `SEND` button to execute it. e.g., typing **`help`** and pressing ENTER will send a short user-guide message from Duke.<br>\
   Some example commands you can try:

   * **`list`** : Lists all tasks in Duke.

   * **`todo`**`CS2100 Lab 3` : Adds a **todo** named `CS2100 Lab 3` to the task list.

   * **`delete`**`3` : Deletes the 3rd task shown in the current list of tasks.
   
   * **`done`**`1` : Marks the 1st task shown in the current list of tasks as done.

   * **`bye`** : Exits the application.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g., in `todo DESCRIPTION`, `DESCRIPTION` is a parameter which can be used as `todo meetup with Bob`.

* Date and Time in angle brackets have to follow a certain format.<br>
  e.g., `deadline CS2103T ip /by <dd/MM/yyyy HH:mm>` can be used as `deadline CS2103T ip /by 15/09/2020 23:59`.
  
* Leading and trailing whitespaces will be ignored.<br>
  e.g., `   delete 1   ` will be read as `delete 1`.
  
</div>

### Viewing help: `help`

Shows a message explaning the commands that Duke understand.

Format: `help`

Example of usage: 

`help`

Expected outcome:

```
Here is what you can do with me:
   * help: list of the commands that I understand
   * todo <description>: add a new TODO
   * deadline <description> /by <dd/MM/yyyy HH:mm>: add a new DEADLINE
   * event <description> /at <dd/MM/yyyy HH:mm>: add a new EVENT
   * list: list out the tasks you have currently
   * delete <task number>: delete the task from your list
   * done <task number>: mark the task as complete
   * retrieve <dd/MM/yyyy>: retrieves the tasks due on or happening on this date
   * find <keyword>: retrieves the tasks that contain the keyword
   * sort: sorts your list by their task type and then by their date and time if any
   * bye: exit the application
```


### Adding a todo: `todo`

Adds a **todo** to Duke.

Format: `todo DESCRIPTION`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
Words in `UPPER_CASE` are the parameters to be supplied by the user.
</div>

Example of usage: 

`todo play basketball`

Expected outcome:

```
Got it. I've added this task:
     [T][✘] play basketball
Now you have 1 task in the list.
```


### Adding a deadline: `deadline`

Adds a **deadline** to Duke.

Format: `deadline DESCRIPTION /by <dd/MM/yyyy HH:mm>`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
Date and Time in angle brackets have to follow a certain format.
</div>


Example of usage: 

`deadline CS2100 Assignment 1 /by 15/09/2020 12:00`

Expected outcome:

```
Got it. I've added this task: 
     [D][✘] CS2100 Assignment 1 (by: 15 September 2020, 12:00 PM)
Now you have 2 tasks in the list.
```


### Adding an event: `event`

Adds an **event** to Duke.

Format: `event DESCRIPTION /at <dd/MM/yyyy HH:mm>`

Example of usage: 

`event SOC Welcome Tea /at 02/11/2020 10:00`

Expected outcome:

```
Got it. I've added this task: 
     [E][✘] SOC Welcome Tea (at: 02 November 2020, 10:00 AM)
Now you have 3 tasks in the list.
```


### Listing all tasks: `list`

Shows a list of all tasks in Duke.

Format: `list`

Example of usage: 

`list`

Expected outcome:

```
Here are the tasks in your list:
   1.[T][✘] play basketball
   2.[D][✓] SEP application (by: 23 October 2020, 12:59 PM)
   3.[E][✘] SOC Welcome Tea (at: 02 November 2020, 10:00 AM)
```


###  Deleting a task: `delete`

Deletes an existing task in the current list of tasks.

Format: `delete INDEX`

* Deletes the task at the specified `INDEX`. The index refers to the index number shown in the displayed task list. The index **must be a positive integer** 1, 2, 3, …​

Example of usage: 

`delete 2`

Expected outcome:

```
Noted. I've removed this task:
     [D][✓] SEP application (by: 23 October 2020, 12:59 PM)
Now you have 2 tasks in the list.
```

###  Marking a task as done: `done`

Marks an existing task in the current list of tasks as done.

Format: `done INDEX`

* Marks the task at the specified `INDEX` as done. The index refers to the index number shown in the displayed task list. The index **must be a positive integer** 1, 2, 3, …​
* A task that is done is represented by a `[✓]` while a task that has yet to be done is represented by a `[✘]`

Example of usage: 

`done 1`

Expected outcome:

```
Nice! I've marked this task as done: 
     [T][✓] play basketball"
```


### Retrieving tasks by date: `retrieve`

Retrieves tasks from the exisiting list of tasks.

Format: `retrieve <dd/MM/yyyy>`

* Retrieves all deadlines and events that have the same date as specified by user.

Example of usage: 

`retrieve 23/10/2020`

Expected outcome:

```
Here are the deadlines and events happening on 23 October 2020:
   1.[D][✘] ST2334 Assignment 1 (by: 23 October 2020, 12:00 PM) 
   2.[E][✘] CS2100 Lab 4 (at: 23 October 2020, 06:00 PM)
```

### Finding tasks by keyword: `find`

Finds tasks which description contains the keyword.

Format: `find KEYWORD`

* The search is case-sensitive. e.g., `homework` will not match `Homework`
* Words that are longer than the keyword do not have to matched fully e.g., `home` will match `homework` but `homework` will not match `home`

Example of usage: 

`find book`

Expected outcome:

```
Here are the matching tasks in your list:
   1.[T][✘] borrow book
   2.[D][✘] readbook (by: 12 February 2020, 12:32 PM)
   3.[E][✘] return book (at: 12 April 2014, 14:09 PM)
```

### Sorting tasks: `sort`

Sorts tasks in the exisiting list of tasks.

Format: `sort`

* The sorting will result in **todos** at the top followed by **deadlines** then **events**
* For **deadlines** and **events**, they will be further sorted by their date and time from earliest to latest
* For **todos** and **deadlines** or **events** with the same date and time, original order will persist

Example of usage: 

`sort`

Expected outcome:

```
Got it! I've sorted your tasks:
   1.[T][✘] eat dinner
   2.[T][✘] play basketball
   3.[D][✘] CS2103T ip (by: 15 September 2020, 11:59 PM)
   4.[D][✘] ST2334 Assignment 1 (by: 20 October 2020, 06:00 PM)
   5.[E][✘] SEP application (at: 12 February 2020, 12:00 PM)
   6.[E][✘] Internship seminar (at: 12 December 2020, 13:15 PM)
```


### Exiting the program: `bye`

Exits the program.

Format: `bye`

Example of usage: 

`bye`

Expected outcome:

Application closes.


### Saving the data

Duke data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous Duke home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Help** | `help` 
**Todo** | `todo DESCRIPTION`<br> e.g., `todo play basketball`
**Deadline** | `deadline DESCRIPTION /by <dd/MM/yyyy HH:mm>`<br> e.g., `deadline CS2100 Assignment 1 /by 15/09/2020 12:00`
**Event** | `event DESCRIPTION /at <dd/MM/yyyy HH:mm>`<br> e.g., `event SOC Welcome Tea /at 02/11/2020 10:00`
**List** | `list`
**Delete** | `delete INDEX`<br> e.g.,`delete 2`
**Done** | `done INDEX`<br> e.g.,`done 3`
**Retrieve** | `retrieve <dd/MM/yyyy>`<br> e.g.,`retrieve 23/10/2020`
**Find** | `find KEYWORD`<br> e.g., `find book`
**Sort** | `sort`
**Bye** | `bye`
