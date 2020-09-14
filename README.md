# Duke User Guide

Duke is a chatbot that the user can use to track their tasks, deadlines and events.

## Quick start

1. Ensure you have Java 11 or above installed in your computer.
2. Download the latest duke.jar from [here](https://github.com/zhaojj2209/ip/releases/tag/A-Release).
3. Copy the file to the folder you want to use as the home folder for Duke.
4. Double-click the file to start the app. The GUI similar to the image below should appear in a few seconds.
5. Type the command in the command box and press Enter to execute it. e.g. typing help and pressing Enter will open the help window.

   ![Duke GUI](./docs/Ui.png)

## Features

### Creates a new todo task - `todo`

Adds a new todo task containing its description.

Usage: `todo <description>`

### Creates a new deadline task - `deadline`

Adds a new deadline task containing its description and deadline (in `yyyy-MM-dd` format).

Usage: `deadline <description> /by <deadline>`

### Creates a new event task - `event`

Adds a new event task containing its description and event time (in `yyyy-MM-dd` format).

Usage: `event <description> /at <event time>`

### Edit task - `edit`

Edits the details of the task.

Edit description: `edit <number of task in list> desc <description>`

Edit time (for deadline/event): `edit <number of task in list> time <new date>`

### Lists all tasks - `list`

Lists all tasks in order of creation.

Usage: `list`

### Completes a task - `done`

Marks a task as complete.

Usage: `done <number of task in list>`

### Searches for tasks - `find`

Returns tasks that contain the input keyword.

Usage: `find <keyword>`

### Exit program - `bye`

Exits the program.

Usage: `bye`
