# User Guide

Duke (Also known as Dwayne The Rock Johnson) is your friendly **productivity assistant chatbot**. Simply **type into the message box** and send your inputs and Duke's got your back!

* Quick Start
* Features
  * Adding a todo: `todo`
  * Adding a deadline: `deadline`
  * Adding an event: `event`
  * Marking task as done: `done`
  * Listing all tasks: `list`
  * Deleting a task: `delete`
  * Finding a task: `find`
  * Exiting the program: `bye`
* FAQ
* Command Summary


## Quick Start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest duke.jar from [here](https://github.com/nicholas-gcc/ip/releases/download/A-Release/duke.jar)

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Take note this image reflects the state of the GUI after adding some sample commands so yours may look empty initially.<br>
<img src="/docs/Ui.png" width="374.5" height="588"><br>

1. Type the command in the message box and press Enter to execute it. e.g. typing list and pressing Enter will give you a list of tasks.
Some example commands you can try:

* **`list`**: Lists all current tasks in order of dates
* **`deadline`**`Do homework /by 2020-08-08`: Adds an unfinished deadline titled `Do homework` with the corresponding date `by: Aug 08 2020`
* **`todo`**`Read book`: Adds an unfinished todo titled `Read book` with no corresponding date
* **`delete`**`2`: Deletes the second task on the task list and lists remaining tasks
* **`done`**`1`: Marks first task on the task list as done
* **`find`**`homework`: Finds tasks containing the keywords `homework`

## Features 

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Fields after deadline/event descriptions should be followed by a `/` and a preposition such as `by` or `at` with no whitespace in between. The subsequent field takes in a date in the `YYYY-MM-DD` format with a whitespace before the field. Any other date format is not able to be accepted.<br> 
e.g. `deadline Homework /by 2020-08-08` creates a deadline for homework at `2020-08-28`, but using `2020-28-08` will not be accepted.  

* The commands `done` and `delete` need to be followed by a `whitespace` and an `integer` greater than 0 and less than or equals to the size of the task list.<br>
  e.g `done 1` will mark the first task as done, `delete 1` will mark the first task as deleted from the list but `done 0` or `delete` will both neither be accepted inputs.

* The task-identifying commands `todo`, `deadline` and `event` must have text input after these fields.<br>
e.g. `todo Read a book` is an accepted input, but `todo` is not

* The input and commands are case sensitive.<br>
e.g. `todo Read a book` is an accepted input, but `Todo read a book` is not

</div>

### Adding a todo: `todo`

Adds a todo task to the task list.

Format: `todo TASK_DESCRIPTION`

Examples:
* `todo Exercise and get some abs`

### Adding a deadline: `deadline`

Adds a deadline to the task list.

Format: `deadline TASK_DESCRIPTION /PREPOSITION YYYY-MM-DD`

Examples:
* `deadline Exercise and get some abs /by 2020-08-28`
* `deadline Do homework /due 2017-09-22`

### Adding an event: `event`

Adds an event to the task list.

Format: `event TASK_DESCRIPTION /PREPOSITION YYYY-MM-DD`

Examples:
* `event Science conference /on 2019-06-11`
* `event Roy's party /at 2018-09-14`

### Listing all tasks : `list`

Shows a list of all persons in the task list, sorted by todos at the top and by dates of deadlines/events in chronological order (from soonest to latest).<br>
Each list element has a marker `[T]`, `[E]` or `[D]` to indicate whether the task is of type `todo`, `event` or `deadline` respectively.<br>
Each list element will show a tick symbol `[✔]` if the task is marked done, or a cross `[x]` if undone.

Format: `list`

### Locating task by keywords: `find`

Finds tasks whose descriptions contain any of the given keywords.

Format: `find KEYWORD [MORE_KEYWORDS]`

* The search is case-sensitive. e.g `book` will not match task description `read Book`
* The order of the keywords matter. e.g. `read book` will match `book read`
* Only the task description is searched, and `find` returns all types of tasks `todo`, `deadline`, `event`.
* Partial words will be matched e.g. `boo` will match task description `read books`

Examples:
* `find books` will match the task descriptions `Read book` and `Return books` (if both tasks exist)<br>
<img src="/docs/FindExample.PNG" width="317.5" height="249.5">

### Deleting a person : `delete`

Deletes the specified task from the task list.

Format: `delete INDEX`

* Deletes the task at the specified `INDEX`, and reprints the new list.
* The index refers to the index number shown in the displayed task list.
* The index **must be a positive integer** 1, 2, 3, …​ and be less than or equals to the size of the list.

Examples:
* `delete 2` deletes the 2nd task in the task list.<br>
<img src="/docs/DeleteExample.PNG" width="374.5" height="588">


### Marking a task as done : `done`

Marks a task as done and changes done symbol ([✔] or [x]) of the specified task on the task list.

Format: `done INDEX`
* Marks the task at the specified `INDEX` as done.
* The index refers to the index number shown in the displayed task list.
* The index **must be a positive integer** 1, 2, 3, …​ and be less than or equals to the size of the list.

Examples:
* `done 2` marks the 2nd task in the task list as done.<br>

### Exiting the program : `bye`

Exits the program.

Format: `bye`
