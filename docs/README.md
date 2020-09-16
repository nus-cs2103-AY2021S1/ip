---
layout: page
title: User Guide
---

Duke is a chatbot for managing tasks and events.

* Table of Contents
{:toc}

## Features 

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `todo DESCRIPTION`, `DESCRIPTION` is a parameter which can be used as `todo do my homework`.

* Items in square brackets are optional.<br>
  e.g `DATE [TIME]` can be used as `15/01/2020 17:00` or as `15/01/2020`.

</div>

### Adding a to do: `todo`
Adds a task to be done to the list.

Format: `todo DESCRIPTION`

- `DESCRIPTION` may contain spaces. Any tabs will be converted to 4 spaces. This is true for description of all list items.

### Adding a deadline: `deadline`
Adds task with a deadline to the list.

Format: `deadline DESCRIPTION /by DATE [TIME]`

- `DATE` should be given in the **day/month/year** format . Eg. `15/01/20` or `15/1/2020` for 15th January 2020
- `TIME` should be given in the **HH:MM** 24-hour format. Eg. `17:00` for 5pm
- If `TIME` is not specified, it is assumed that the deadline is at midnight (00:00)

### Adding an event: `event`
Adds an event with a start and end time to the list.

Format: `event DESCRIPTION /at START_DATE [START_TIME]-END_DATE [END_TIME]`

- Format of `START_DATE`, `END_DATE`, `START_TIME`, `END_TIME` are the same as date and time used for deadlines. See the previous section for details.

### Displaying all items in list: `list`
Displays all items currently in the list.

Format: `list`

### Marking an item as done: `done`
Marks an item in the list as done.

Format: `done NUMBER`

- `NUMBER` refers to the number of the item in the list to mark as done

### Deleting an item: `delete`
Removes an item from the list.

Format: `delete NUMBER`

- `NUMBER` refers to the number of the item in the list to remove

### Editing an item: `edit`
Edits an item in the list.

Format: `edit NUMBER FIELD NEW_VALUE`

- `NUMBER` refers to the number of the item in the list to edit
- `FIELD` refers to the field to edit and `NEW_VALUE` refers to the new value for the field. The following fields can be edited:
  - `/description` refers to the description of an item
  - `/date` refers to the date/time for a deadline. `NEW_VALUE` should have the format `DATE [TIME]`. See "Adding a deadline" for more details.
  - `/start` and `/end` refers to the start and end date/time (respectively) of an event. `NEW_VALUE` should have the format `DATE [TIME]`. See "Adding an event" for more details.

### Finding an item: `find`
Finds and displays items containing the given phrase.

Format: `find KEYWORD`

### Saving the list
Duke automatically saves the task list after any changes, so there is no need to save manually. The task list is saved in the file **data/tasks.txt**, which is relative to the directory that the JAR file for Duke is in.
