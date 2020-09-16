# User Guide

## Features 

### Feature 1: Help
Displays command guide for users, with all command formats listed down.

### Feature 2:  View Tasks
Displays all tasks stored in the current task list.

### Feature 3: Mark a Task as Done
Marks a task as done, updates the local storage file.

### Feature 4: Delete a Task
Deletes a Task from the current task list, updates the local storage file.

### Feature 5: Add a Task
Adds a Todo/Event/Deadline to the current task list, updates the local storage file.

### Feature 6: List Tasks Matching to Keyword Input
Displays all tasks that contain the keyword input by user.

### Feature 7: Archive the Current Tasks
Save the current task list as a local storage file.

### Feature 8: List the Archived Files
Displays details of all local archived files.

### Feature 9: Load Archived Task List
Loads the task list in a specific archived file into the current task list.

### Feature 10: Delete an Archived File
Deletes an unwanted archived file in order to save space.

### Feature 11: Enter and Exit Sleep Mode
Enters the sleep mode where the chatbot ceases to respond until the correct command is entered to exit the sleep mode.

## Usage

### `Keyword` - Describe action

Describe action and its outcome.

Example of usage: 

`keyword (optional arguments)`

Expected outcome:

`outcome`

### `help` - Requests for the Command Guide

Displays for the Command Guide requested.

Format: `help`

Example of usage: 

`help`

Expected outcome:

```
Please follow the formats below:

1. 'help'
2. 'list'
3. 'done TASK_NUMBER'
4. 'delete TASK_NUMBER'
5. 'todo MY_TASK'
6. 'deadline MY_TASK /by DATE_OR_TIME'
7. 'event MY_TASK /at DATE_OR_TIME'
8. 'find KEYWORD'
9. 'archive'
10. 'listArchive'
11. 'loadArchive ARCHIVE_FILE_NAME'
12. 'binArchive ARCHIVE_FILE_NAME'
13. 'bye'

Legend:
1 -> Get command guide
2 -> List all tasks in the current version
3 & 4 -> Modify the status of a task
5 & 6 & 7 -> Add a new task
8 -> Search for task(s) matching a certain keyword
9 -> Archive your current version of task list
10 -> List all existing archive files
11 -> Load a certain version of task list to your current work space
12 -> Delete a certain archive file
13 -> Let Duke enter the sleep mode
```

### `list` - List All Tasks in the Current Task List

Displays all tasks in the current task list.

Format: `list`

Example of usage: 

`list`

Expected outcome:

```
Here are the tasks in your list:

1. [D][✓] CS2103T quiz (by: Dec 20, 2020, 3:00 PM)
2. [T][✘] CCA
3. [E][✘] Hostel Briefing (at: Oct 10, 2020, 9:00 AM)
```

### `done` - Marks a Task as Done

Marks a specific task as done and updates the local storage file.

Format: `done TASKNUMBER`

Example of usage: 

`done 2`

Expected outcome:

```
Nice! I've marked this task as done:
 [✓] CCA
```

### `delete` - Deletes a Task

Deletes a specific task from the task list and updates the local storage file.

Format: `delete TASKNUMBER`

Example of usage: 

`done 2`

Expected outcome:

```
Noted. I've removed this task:
 [T][✓] CCA
Now you have 2 tasks in the list.
```

### `todo` - Adds a Todo Task

Adds a todo task to the current task list and updates the local storage file.

Format: `todo DESCRIPTION`

Example of usage: 

`todo join the telegram chat`

Expected outcome:

```
Got it. I've added this task:
 [T][✘] join the telegram chat
Now you have 3 tasks in the list.
```

### `event` - Adds an Event Task

Adds an event task to the current task list and updates the local storage file.

Format: `event DESCRIPTION /at TIME`

Example of usage: 

`event meet with friends /at 2020-12-12 1200`

Expected outcome:

```
Got it. I've added this task:
 [E][✘] meet with friends (at: Dec 12, 2020, 12:00 PM)
Now you have 4 tasks in the list.
```

### `deadline` - Adds a Deadline Task

Adds a deadline task to the current task list and updates the local storage file.

Format: `deadline DESCRIPTION /by TIME`

Example of usage: 

`deadline complete quiz /by 2020-09-07 2359`

Expected outcome:

```
Got it. I've added this task:
 [D][✘] complete quiz (by: Sep 7, 2020, 11:59 PM)
Now you have 5 tasks in the list.
```

### `find` - Searches for Tasks Matching the Keyword

Displays all tasks matching the keyword provided.

Format: `find KEYWORD`

Example of usage: 

`find quiz`

Expected outcome:

```
Here are the tasks that match your keyword:

1. [D][✓] CS2103T quiz (by: Dec 20, 2020, 3:00 PM)
2. [D][✘] complete quiz (by: Sep 7, 2020, 11:59 PM)
```

Example of usage: 

`find committee`

Expected outcome:

```
Sorry, there is no match for your keyword!
```

### `archive` - Archives the Current Task List

Archives the current task list by saving it to a local storage file.

Format: `archive`

Example of usage: 

`archive`

Expected outcome:

```
Tasks successfully archived! Enter 'listArchive' to observe a new file being added.
```

### `listArchive` - Lists all Archived Files

Lists details of all archived files in the local storage.

Format: `listArchive`

Example of usage: 

`listArchive`

Expected outcome:

```
* Please Note:
Your current list will be discarded once you switch to an archive file.
Key in 'archive' to save your work before continuing with 'loadArchive FILE_NAME'.

Use 'File name' of the archive file for any further operation.

File name: Archive-1600278374625.txt
Archived at: Wed Sep 16 03:02:57 SGT 2020
```

### `loadArchive` - Loads Archived Task List

Loads a specific archived task list into the current task list.

Format: `loadArchive FILENAME`

Example of usage: 

`loadArchive Archive-1600278374625.txt`

Expected outcome:

```
Task List successfully loaded from Archive file 'Archive-1600278374625.txt' created at Wed Sep 16 03:02:57 SGT 2020!
Enter 'list' to see the tasks!
```

### `binArchive` - Deletes an Archived File

Deletes an archived file from the local storage.

Format: `binArchive FILENAME`

Example of usage: 

`binArchive Archive-1600278374625.txt`

Expected outcome:

```
Successful deletion of archive file 'Archive-1600278374625.txt' created at Wed Sep 16 03:02:57 SGT 2020!
Enter 'listArchive' to see the current file list!
```

### `bye` - Enters Sleep Mode

Enters sleep mode where the chatbot will not respond properly until the corrent command is input.

Format: `bye`

Example of usage: 

`bye`

Expected outcome:

```
Bye. Hope to see you again soon!
```

### `hello` - Exits Sleep Mode

Exits Sleep Mode when the system is in sleep mode.

Format: `hello`

Example of usage: 

`hello`

Expected outcome:

```
Hello! This is J.A.R.V.I.S.
How may I help you?

Enter 'help' for command guide.
```

