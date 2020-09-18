# User Guide

Duke is a **desktop chat bot for managing and storing tasks**. Why try to remember all your tasks when Rose from Blackpink can help track all your tasks for you?

---
## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `Duke-0.2-all.jar` from [here](https://github.com/ChenXJ98/ip/releases/tag/A-Release).

1. Copy the file to the folder you want to use as the _home folder_ for your Duke.

1. Right-click the file and open the app. The GUI similar to the below should appear in a few seconds. <br>
   ![Ui](images/StartUi.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
      Some example commands you can try:

      * **`list`** : Lists all Tasks.

      * **`todo`**`Call my mom` : Adds a todo task `Call my mom` to the list.
      
      * **`deadline`**`Submit homework`**` /by`** `2020/05/04 0800`: Adds a deadline task `Submit homework` to the list to do by `Monday, 4 May 2020 08:00AM`.
      
      * **`event`**`Blackpink concert`**` /at`** `2020/05/04 0800`: Adds an event task `Blackpink concert` to the list to happen by `Monday, 4 May 2020 08:00AM`.

      * **`done`**`3` : Marks the 3rd Task shown in the list as done.
      
      * **`delete`**`3` : Deletes the 3rd Task shown in the list.
      
      * **`update`**`3 d/BTS concert dt/2020/06/04 0900`: Updates the 3rd Task shown in the list with the new description and datetime.

      * **`find`**`concert`: Finds all tasks containing `concert`.      
      
      * **`bye`** : Exits the app.

   1. Refer to the [Features](#features) below for details of each command.

---

## Features 

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `todo DESCRIPTION`, `DESCRIPTION` is a parameter which can be used as `todo Call my mom`.

* Items in square brackets are optional, but at least one needs to be used.<br>
  e.g `update INDEX [d/DESCRIPTION] [dt/DATETIME]` can be used as `update 3 d/sleep`, or as `update 3 dt/2020/10/16 1600`, or as `update 3 d/sleep dt/2020/10/16 1600`.

</div>

### Viewing help : `help`

Shows a message explaning how to use the app.

Format: `help`

### Listing all tasks : `list`

Shows a list of all tasks currently created and stored.

Format: `list`

### Adding a todo task: `todo`

Adds a todo task to the list.

Format: `todo DESCRIPTION`

### Adding a deadline task: `deadline`

Adds a deadline task to the list.

Format: `deadline DESCRIPTION /by YYYY/MM/DD HHMM`

### Adding an event task: `event`

Adds an event task to the list.

Format: `event DESCRIPTION /at YYYY/MM/DD HHMM`

### Complete a task : `done`

Completes the specified task from the list.

Format: `done INDEX`

* Deletes the task at the specified `INDEX`.
* The index refers to the index number shown in the displayed task list.
* The index **must be a positive integer** 1, 2, 3, …​

### Deleting a task : `delete`

Deletes the specified task from the list.

Format: `delete INDEX`

* Deletes the task at the specified `INDEX`.
* The index refers to the index number shown in the displayed task list.
* The index **must be a positive integer** 1, 2, 3, …​

### Updating a task : `update`

Updates a task in the list.

Format: `update INDEX [d/DESCRIPTION] [dt/YYYY/MM/DD HHMM]`

* Updates the task at the specified `INDEX`. The index refers to the index number shown in the displayed task list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* Note that todo tasks can only update description **d/**.

### Finding tasks : `find`

Finds tasks from the list containing matching keyword.

Format: `find KEYWORD`

* Finds all tasks containing `KEYWORD`.

### Exiting the program : `bye`

Exits the program.

Format: `bye`

### Saving the data

AddressBook data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

---

## Command summary

Action | Format, Examples
--------|------------------
**Todo** | `todo DESCRIPTION` <br> e.g., `todo Call my mom`
**Deadline** | `deadline DESCRIPTION /by YYYY/MM/DD HHMM` <br> e.g., `deadline Submit homework /by 2020/05/04 0800`
**Event** | `event DESCRIPTION /by YYYY/MM/DD HHMM` <br> e.g., `event Blackpink concert /at 2020/05/04 0800`
**Update** | `update INDEX [d/DESCRIPTION] [dt/YYYY/MM/DD HHMM]` <br> e.g., `update 3 d/BTS concert dt/2020/06/04 0900`
**Done** | `done INDEX`<br> e.g., `done 3`
**Delete** | `delete INDEX`<br> e.g., `delete 3`
**Find** | `find KEYWORD`<br> e.g., `find concert`
**List** | `list`
**Help** | `help`
**Exit** | `bye`
