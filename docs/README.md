# User Guide

![Duke screenshot](./Ui.png)

Duke is a chatbot for keeping track of tasks and events. Duke can run as either a graphical (default) or command line program.

## Quick start
1. Install Java 11 or later on your computer.
1. Download the latest JAR file for Duke (`duke.jar`) from the [releases page](https://github.com/chloelee767/ip/releases).
1. To start Duke as a graphical program, either double-click on the JAR file, or by run `java -jar duke.jar` in the terminal. 
To start Duke as a command line program instead, run `java -jar duke.jar cli` in the terminal.
1. Type a command into the input box and press <kbd>Enter</kbd> to start using Duke!

## Format of this guide

- User supplied parameters are given in `UPPER_CASE`.
- Items in square brackets eg. `[TIME]` are optional. The brackets are not to be entered as part of the command.

## Features 

### Adding a to do: `todo`
Adds a task to be done to the list.

Format: `todo DESCRIPTION`

- `DESCRIPTION` may contain spaces. Any tabs in the description will be converted to 4 spaces. This is true for description of all list items.

### Adding a deadline: `deadline`
Adds a task with a deadline to the list.

Format: `deadline DESCRIPTION /by DATE [TIME]`

- `DATE` should be given in the **day/month/year** format . Eg. `15/01/20` or `15/1/2020` for 15th January 2020.
- `TIME` should be given in the **HH:MM** 24-hour format. Eg. `17:00` for 5pm.
- If `TIME` is not specified, midnight (00:00) is assumed.

### Adding an event: `event`
Adds an event with a start and end time to the list.

Format: `event DESCRIPTION /at START_DATE [START_TIME]-END_DATE [END_TIME]`

- Format of `START_DATE`, `END_DATE`, `START_TIME`, `END_TIME` are the same as date and time used for deadlines. See the previous section for details.

### Displaying all items in list: `list`
Displays all tasks and events currently in the list.

Format: `list`

### Marking an item as done: `done`
Marks an item in the list as done.

Format: `done NUMBER`

- `NUMBER` refers to the number of the item in the list to mark as done.

### Deleting an item: `delete`
Removes an item from the list.

Format: `delete NUMBER`

- `NUMBER` refers to the number of the item in the list to remove.

### Editing an item: `edit`
Edits an item in the list.

Format: `edit NUMBER FIELD NEW_VALUE`

- `NUMBER` refers to the number of the item in the list to edit.
- `FIELD` refers to the field to edit and `NEW_VALUE` refers to the new value for the field. The following fields can be edited:
  - `/description` refers to the description of an item.
  - `/date` refers to the date/time for a deadline. `NEW_VALUE` should have the format `DATE [TIME]`. See "Adding a deadline" for more details.
  - `/start` and `/end` refers to the start and end date/time (respectively) of an event. `NEW_VALUE` should have the format `DATE [TIME]`. See "Adding an event" for more details.

### Finding an item: `find`
Finds and displays items containing the given phrase.

Format: `find KEYWORD`

### Exiting Duke: `bye`
Exits the application.

Format: `bye`

### Saving the list
Duke automatically saves the task list after any changes, so there is no need to save manually. The task list is saved in the file **data/tasks.txt**, which is relative to the directory that the JAR file for Duke is in. 
