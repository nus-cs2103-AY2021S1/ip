# User Guide

This is a user guide for Duke.
Duke is a **chatbot for productivity**. All it takes is some
easy commands and Duke will handle the rest for you!

* Quick Start
* Features
* Commands List
    
## Quick Start

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest jar file from: https://github.com/jordanyoong/ip/releases/download/A-Release/duke.jar

3. Run the file by double clicking.

4. Type the command in the message box and press Enter to execute it. e.g. typing list and pressing Enter will give you a list of tasks.
Some example commands you can try:

* **`list`**: Shows user all current tasks
* **`deadline`**`Do Chores /by 2020-09-18`: Adds an unfinished deadline titled `Do Chores` with the corresponding date `by: September 19 2020`
* **`todo`**`Submit Assignment`: Adds an unfinished todo titled `Read book` with no corresponding date
* **`delete`**`3`: Deletes the tasks with the index provided, in this case the 3rd entry
* **`done`**`4`: Marks the corresponding indexed task on the task list as done, in this case the 4th one
* **`find`**`work`: Finds tasks containing the keyword `work`

--------------------------------------------------------------------------------------------------------------------

## Features 

### Command Formats

<div markdown="block" class="alert alert-info">

* Fields after deadline/event descriptions should be followed by a `/` and a preposition such as `by` or `at` with 
no whitespace in between. The subsequent field takes in a date in the `YYYY-MM-DD` format with a whitespace before the 
field. Any other date format is not able to be accepted.<br> e.g. `deadline Homework /by 2020-08-08` is valid, 
but using `2020-30-08` is not.

* The commands `done` and `delete` need to be followed by a `whitespace` and then an `integer` which is within the list's 
range.<br> e.g `done 2` will mark the second task as done, `delete 1` will remove the first task from the list
but `done 0` or `delete` will both neither be accepted inputs.

* The task-identifying commands `todo`, `deadline` and `event` must have text input after these fields.<br>
e.g. `todo Read a book` is an accepted input, but `todo` is not

* The task-identifying commands are case-sensitive.<br>
e.g. `todo read book` is an accepted input, but `Todo read book` is not

</div>

### Add a todo: `todo`

Adds a todo task to the task list.

Format: `todo TASK_DESCRIPTION`

Examples:
* `todo Exercise and get some abs`

### Add a deadline: `deadline`

Adds a deadline to the task list.

Format: `deadline TASK_DESCRIPTION /PREPOSITION YYYY-MM-DD`

Examples:
* `deadline Exercise and get some abs /by 2020-08-28`
* `deadline Do homework /due 2017-09-22`

### Add an event: `event`

Adds an event to the task list.

Format: `event TASK_DESCRIPTION /PREPOSITION YYYY-MM-DD`

Examples:
* `event Science conference /on 2019-06-11`
* `event Roy's party /at 2018-09-14`

### Listing all tasks : `list`

Shows all task elements that are on the list. Also provides details on the type, doneness and description of each task.

Format: `list`

### Locating task by keywords: `find`

Functions as a search function to find tasks containing the keywords provided.

Format: `find KEYWORD [MORE_KEYWORDS]`

* Keywords are all case-sensitive. e.g `cheese` will not match `cook Cheese`
* Keyword order matters. e.g. `read magazine` will match `magazine read`
* Only task descriptions are searched, and `find` returns all types of tasks `todo`, `deadline`, `event`.
* Subsets of words are valid e.g. `lod` will match task description `clear lodge`

Examples:
* `find food` will match the task descriptions `Cook food` and `Serve foods` <br>

### Deleting a task : `delete`

Deletes the specified task from the task list.

Format: `delete INDEX`

* Deletes the task at the provided `INDEX`, and outputs the remainder of the list.
* The index refers to the index number shown in the stipulated task list.
* The index **must be a positive integer** 1, 2, 3, …​ and be less than or equals to the size of the list.

Examples:
* `delete 3` deletes the 3rd entry in the task list.<br>


### Mark a task as done : `done`

Changes done symbol ([✔] or [x]) showing doneness of the specified task on the task list.

## Usage
Format: `done INDEX`
* Marks the task at the specified `INDEX` as done.
* The index refers to the index number shown in the stipulated task list.
* The index **must be a positive integer** 1, 2, 3, …​ and be less than or equals to the size of the list.

### `Keyword` - Describe action
Examples:
* `done 4` marks the 4th task in the task list as done.<br>

Describe action and its outcome.
### Exiting the program : `bye`

Example of usage: 
Exits the program.

`keyword (optional arguments)`
Format: `bye`

Expected outcome:
--------------------------------------------------------------------------------------------------------------------

`outcome`
Action | Format, Examples
--------|------------------
**todo** | `todo TASK_DESCRIPTION` <br> e.g., `todo Clean room`
**event** | `event TASK_DESCRIPTION /PREPOSITION YYYY-MM-DD` <br> e.g., `event Debate competition /at 2019-02-03`
**deadline** | `deadline TASK_DESCRIPTION /PREPOSITION YYYY-MM-DD` <br> e.g., `deadline Return books /by 2020-10-27`
**list** | `list`
**delete** | `delete INDEX`<br> e.g., `delete 7`
**done** | `edit INDEX`<br> e.g.,`done 1`
**find** | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find Household chores`
**bye** | `bye`


User Guide Acknowledgements: ChenXJ98, nicholas-gcc, Ziyangs98