# User Guide
Duke is a desktop app for managing Tasks, with functionality to add, delete, save, update, & mark Tasks as done.

* Table of Contents
{:toc}
  
##Quick start
1. Ensure you have Java 11 or above installed in your Computer.

2. Download the latest Duke.jar from [here](https://ethan-l-m-e.github.io/ip/).

3. Copy the file to the folder you want to use as the home folder for your AddressBook.

4. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.
  
    * **`list`** : Lists all Tasks.
  
    * **`todo`**`example` : Adds a Task with description `example` to the Task List.
    
    * **`dealine`**`Deadline1 /by 2020-09-19` : Adds a Deadline Task with description `Deadline1` and due date `Sep 19th 2020` to the Task List.
    
    * **`event`**`EventA /at 2020-09-19` : Adds an Event Task with description `EventA` and event time `Sep 19th 2020` to the Task List.
  
    * **`delete`**`3` : Deletes the 3rd Task shown in the Task List.
  
    * **`update 1`** : Edits the first task in the Task List.
  
    * **`exit`** : Exits the app.
  
## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Items in `UPPER_CASE` brackets are parameters to be entered by the user.<br>
  e.g. `todo DESCRIPTION`, where `DESCRIPTION` is a parameter to be supplied, such as `todo Assignment 1`
* Items in square brackets `[]` are optional fields.<br>
  e.g. `DESCRIPTION [/at YYYY-MM-DD]`, can be used as `eventA /at 2021-01-01` or also as just `eventA`
  
</div>

### Adding a Todo: `todo`
*A Todo is a Task that contains only a description, and nothing else.*

**Format:** `add DESCRIPTION` 

___

### Adding a Deadline: `deadline`
*A Task that contains a description, as well as the date for which it is due.*

**Format:** `deadline DESCRIPTION​ /by YYYY-MM-DD`

* `/by` must be followed by a date.
* Supplied date has to follow `YYYY-MM-DD` format.

**Examples:**
*  `deadline Quiz1 /by 2020-09-20` Sets new Deadline called `Quiz1` that is due on `Sep 20th 2020`.
*  `deadline complete task 1, 2, 3 /by 2021-01-01` 

___

### Adding an Event: `event`
*A Task that contains a description, as well as the date when the event occurs.*

**Format:** `event DESCRIPTION /at YYYY-MM-DD`

* `/at` must be followed by a date.
* Supplied date has to follow `YYYY-MM-DD` format.

**Examples:**
*  `event meeting /at 2020-09-14`
*  `event New Year Countdown /at 2020-12-31` 

___

## Usage
### `delete` - Deleting a Task:

*Deletes the Task with the specified Task number.*

**Example of usage:** 

`delete 2` Deletes the second Task in the `list`

___

### `done` - Marking a Task as Done:

*Marks the Task with the specified Task number as done.*

**Example of usage:** 

`done 1` Marks the first Task in the `list` as done.

> Done tasks are displayed in the `list` by being marked off with an `[x]`:
>
> e.g. `1.[T][x] Return Book`

> Task not yet marked done are shown with `[ ]`:
>
> e.g. `1.[T][ ] Return Book`

___

### `list` - Listing all Tasks:

*Displays all Tasks to the user.*

**Examples:** 

`list`

___

### `update` - Updating an existing Task:

*Requests to update a Task with the specified Task number.*

**Format:**
1. `update TASKNUMBER`
2. `[DESCRIPTION] [/by DEADLINE] [/at EVENTTIME]` 
3. `y` or `n` 

> Specify Task number of the target. Task number can be seen with `list`.

> In a single line, you may enter a new `DESCRIPTION`, as well as:

> If Task is a Deadline, you can provide a new `/by YYYY-MM-DD` to change the deadline.

> If Task is an Event, you can provide a new `/at YYYY-MM-DD` to change the event date.

> Cannot edit date for Todo Tasks.

> Changes to dates can be done without giving a new `DESCRIPTION`.

> After entering the changes, you will be prompted to confirm with `y` or `n`.

**Examples:**
* `update 1` -> `new description`
* `update 1` -> `new description /by 2020-01-01`
* `update 1` -> `new description /at 2020-01-01`
* `update 1` -> `/by 2020-01-01` 
* `update 1` -> `/at 2020-01-01` 

___

### `find` - Searching for a Task:

*Displays all Tasks with descriptions that match keywords.*

**Format:** `find KEYWORDS`

**Examples:** 
`find meeting` Returns Tasks that have the word `meeting` in their description.

___

## Saving the data :

Data is saved in the hard disk automatically after any command that changes the Task list. Saving is not required.

## Exiting the program : 

Closing the window by clicking the `X` at the top right corner exits the program.

