Duke is a **desktop app for managing tasks, optimized for use via a Command Line Interface** (CLI) while still having 
the benefits of a Graphical User Interface (GUI).

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `duke.jar` from [here](https://github.com/WeiJie96/ip/releases).

3. Copy the file to the folder you want to use as the _home folder_.

4. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds.<br>
   ![Ui](docs/images/Start.png)

5. Type the command in the command box and press Enter / Return on your keyboard to execute it. Alternatively, you may 
press the Enter button on the GUI. e.g. typing **`todo Defeat Gengar`** and pressing Enter will add a task 
named `Defeat Gengar` to your task list.<br>

   Some example commands you can try:

   * **`list`** : Lists all tasks.

   * **`todo`**`Defeat Gengar` : Adds a task named `Defeat Gengar` to your task list.

   * **`delete`**`3` : Deletes the 3rd task shown in the current list.

   * **`bye`** : Exits the app.

6. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `todo NAME`, `NAME` is a parameter which can be used as `todo Defeat Gengar`.

### Adding a task: `todo / event / deadline`

Adds a task to the address book.

Format: 

* Adding a todo: `todo NAME`
* Adding an event: `event NAME /at DATE`
* Adding a deadline: `deadline NAME /by DATE`

Examples:
* `todo Defeat Gengar`
* `event Attend Sobble's birthday /at 2020-12-12`
* `deadline Save Mudkip /by 2020-09-09`

### Listing all tasks : `list`

Shows a list of all tasks in the task list.

Format: `list`

### Locating tasks by name: `find`

Find tasks where names and / or dates contain any of the given keywords.

Format: `find KEYWORD`

* The search is case-sensitive. e.g `Save` will not match `save`
* A space-separated search is considered as one keyword e.g. `Save Mudkip` would match `Save Mudkip` only, 
and not `Save Treecko`
* Both the name and date can be searched.
* Partial words can be matched e.g. `Mud` will match `Mudkip`

Examples:
* `find Sav` returns `Save Mudkip /by 2020-09-09` and `Save Treecko`
* `find 2020` returns `Save Mudkip /by 2020-09-09` and `Attend Sobble's birthday /at 2020-12-12`<br>

### Sorting the task list : `sort`

Sorts the task list by task name.

Format: `sort SORT_ORDER`

* Sorts the entire task list by task name using the specified `SORT_ORDER`.
* `SORT_ORDER` **must be one of `asc` or `desc`**.
* `asc` sorts the task list in ascending order, while `desc` sorts the task list in descending order.

Examples:
* `sort asc` sorts the task list in ascending order.

### Marking a task as done: `done`

Marks the specified task from the task list as done.

Format: `done INDEX`

* Marks the task at the specified `INDEX` as done.
* The index refers to the index number shown in the entire task list.
* The index **must be a positive integer not more than the size of the task list** e.g. 1, 2, 3, ...

Examples:
* `done 2` marks the 2nd task in the task list as done.

### Deleting a task : `delete`

Deletes the specified task from the task list.

Format: `delete INDEX`

* Deletes the task at the specified `INDEX`.
* The index refers to the index number shown in the displayed task list.
* The index **must be a positive integer not more than the size of the task list** e.g. 1, 2, 3, ...

Examples:
* `delete 2` deletes the 2nd task in the task list.

### Exiting the program : `bye`

Exits the program.

Format: `bye`

### Saving the data

Task list data are saved in the hard disk automatically after any command that changes the data. There is no need to 
save manually.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Add deadline** | `deadline NAME /by DATE` <br> e.g., `deadline Save Mudkip /by 2020-09-09`
**Add event** | `event NAME /at DATE` <br> e.g., `event Attend Sobble's birthday /at 2020-12-12`
**Add todo** | `todo NAME` <br> e.g., `todo Defeat Gengar`
**Delete** | `delete INDEX` <br> e.g., `delete 3`
**Done** | `done INDEX` <br> e.g., `done 2`
**Exit** | `bye`
**Find** | `find KEYWORD`<br> e.g., `find Save`
**List** | `list`
**Sort** | `sort SORT_ORDER` <br> e.g., `sort asc`

--------------------------------------------------------------------------------------------------------------------

## Acknowledgements

* Pokemon Fandom for providing the following images:
    * [Piplup background](https://pokemon.fandom.com/wiki/Piplup?file=393Piplup_Pok%C3%A9mon_Super_Mystery_Dungeon.png)
    * [Piplup display picture](https://pokemon.fandom.com/wiki/Piplup?file=393Piplup.png)
    * [Oshawott display picture](https://pokemon.fandom.com/wiki/Oshawott?file=501Oshawott.png)
* Oracle documentation for providing [CSS code](https://docs.oracle.com/javafx/2/get_started/css.htm)
* SoC AB-3 team for providing reference for the [User Guide](https://se-education.org/addressbook-level3/UserGuide.html)
