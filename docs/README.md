<br>

*Olivia* is an interactive chat bot geared towards helping you manage tasks that need to be done.

*Olivia* provides a compromise between efficiency and elegance, by employing both a
**Command Line Interface** (CLI) and **Graphical User Interface** (GUI); however, she is geared
towards faster typists, and does not provide means of input beyond the command line.

## Quick start

1. Ensure you have Java *11* or above installed in your Computer.

1. Download the latest `Olivia.jar` from [here](https://github.com/fyshhh/ip/releases) and save it to
the folder you want to use as the **home folder** for *Olivia*.

1. Double-click the file or run the command `java -jar Olivia.jar` to start the app. The GUI similar
to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](./Ui.png)

1. Type the command in the command box and press Enter to execute it.
   Some example commands you can try:
   * **`list`** : Lists all contacts.
   * **`event`**`meeting /at 12-12-2020 1212` : Adds an event named `meeting` to Olivia.
   * **`delete`**`3` : Deletes the 3rd task shown in the current list.
   * **`bye`** : Exits the app.

1. Type the command in the command box and press Enter to execute it. Refer to the [Features](#features)
below for details of each command.


## Features 

## Tasks

Tasks are grouped into three different types, all three having a compulsory description field. Tasks
also have a state, where they are either completed or not. The nomenclature chosen for the tasks are
completely arbitrary and thus have no bearing on the actual performance of *Olivia*. Thus, users need
not feel pressured into creating the "correct" task type.

### ToDo

ToDo represents a simple task that has no additional state.

### Deadline

Deadline represents a task with an additional time state, signifying when the deadline should be
completed by.

### Event

Event also represents a task with an additional time state, signifying when the event takes place.

## Commands

**Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `event DESCRIPTION /at DATE`, `DESCRIPTION` and `DATE` are parameters
  which can be used as `event meeting /at 12-12-2020 1212`.
 * Items in square brackets are optional.<br>
    e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.
* The date and time format is `DD-MM-YYYY HHmm`.

### Adding a ToDo task - `todo`

Creates a ToDo task and adds it to Olivia.

Format: `todo DESCRIPTION`

Examples: 
* `todo buy a birthday cake`

### Adding a Deadline task - `deadline`

Creates a Deadline task and adds it to Olivia.

Format: `deadline DESCRIPTION /by DATE`

Examples: 
* `deadline buy birthday present /by 12-12-2020 1212 `

### Adding an Event task - `event`

Creates an Event task and adds it to Olivia.

Format: `event n/DESCRIPTION /at EVENT`

Examples: 
* `event birthday party /at 12-12-2020 1212`

### Listing all tasks - `list`

Shows a list of all tasks currently stored in Olivia.

Format: `list`

### Deleting a task - `delete`

Deletes the specified task from Olivia.

Format: `delete INDEX`

* Deletes the task at the specified `INDEX`.
* The index refers to the index number shown in the displayed task list.
* The index must be a positive integer 1, 2, 3, …​

Examples: 
* `delete 2` deletes the second task in Olivia.

### Marking a task as done - `done`

Marks the specified task as done in Olivia.

Format: `done INDEX`

* Marks the task at the specified `INDEX` as done.
* The index refers to the index number shown in the displayed task list.
* The index must be a positive integer 1, 2, 3, …​

Examples:
* `done 2` marks the second task in Olivia as done.

### Searching for a task - `find`

Finds tasks whose name matches the given keyword.

Format: `find KEYWORD`

* The search is case-sensitive. e.g `meeting` will not match `Meeting`
* Only full words will be matched e.g. `meet` will not match `meeting`

Examples:
* `find meeting` returns `meeting` and `important meeting`.

### Updating a task - `update`

Updates the specified task in Olivia.

Format: `update INDEX [/description DESCRIPTION] [/date DATE]`

* Edits the task at the specified `INDEX`. The index refers to the index number shown
in the displayed task list. The index **must be a positive integer** 1, 2, 3, …​
* One of the optional fields must be provided.
* Existing values will be updated to the input values.

Examples:

* `update 2 /description meal` updates the second task in Olivia's description to `meal`.

### Exits the program - `bye`

Exits the program.

Format: `bye`