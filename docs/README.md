# User Guide
![Benedict Screenshot](./Ui.png)

Benedict is a personal task manager in the form of a chatbot. He can help you track your To-dos, events, and deadlines. He comes in the form of a GUI, as well as a command line application.
## Quick Start
1. Ensure you have Java 11 or above installed on your Computer.
2. Download the latest `ip.jar` [here](https://github.com/JingYenLoh/ip/releases).
3. Double-click the file to start the app. The GUI should launch in a few seconds.
4. Type a command into the command box and press <kbd>Enter</kbd> to execute it.

### Format of this guide
- User input is prefixed with `$`.
- Required parts are surrounded by angle brackets `<>`.
- Optional parts are surrounded by square brackets `[]`.
- The brackets are not part of the actual typed commands.

### Features

## Usage
#### `help` &mdash; Get help
List all the commands Benedict supports, and their input format.
```
$ help
```

#### `list` &mdash; List Tasks
List all the tasks you've told Benedict to remind you about.
```
$ list
```

#### `find` &mdash; Find Tasks
Search for Tasks whose descriptions contain a given search string.
```
$ find <search_string>
```

#### `done` &mdash; Mark a Task as done
Marks a task as done. The task index is its position as shown in `list`.
```
$ done <task_index>
```

#### `delete` &mdash; Remove a Task
Tell Benedict you want it to stop tracking a Task for you. The task index is its position as shown in `list`.
```
$ delete <task_index>
```

#### `todo` &mdash; Add a Todo
Add a task you need to do.
```
$ todo <description>
```

#### `deadline` &mdash; Add a Deadline
Add a task with a deadline. The deadline should be of the format `yyyy-mm-dd`, e.g. `2020-09-18`.
```
$ deadline <description> /by <time: yyyy-mm-dd>
```

#### `event` &mdash; Add an Event
Add a task that occurs within a period.
```
$ event <description> /by <period>
```

#### `bye` &mdash; Exit app
Exits the app. Benedict will remember the tasks you've told him to remind you when you relaunch the application.
```
$ bye
```
