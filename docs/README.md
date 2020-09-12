# User Guide
Duke is a chat bot to help you manage your deadlines and task. It serves as an all in one to-do app! It is optimized for use via a Command Line Interface.

## Quick Start
	1. Download the latest Duke.jar
	2. Double click the file to start the app
    3. Use the text field to type in command
## Features 
Some commands available:
-   `help` opens a help window
-   `list` lists out all current tasks
-   `done 3` marks the 3rd task in the list as done
-   `delete 3` deletes the 3rd task in the list

More commands are available in the help window
### Feature 1 
Adding of tasks

#### Usage

### `todo`, `deadline`, `event` - Adds the specific task type

Adds a todo, deadline or event task. 

Note that for deadline, a `/by` followed by the date in the format `YYYY-MM-DD` is required

Note that for event, a `/at` followed by the date and time in the format `YYYY-MM-DD TT:TT-TT:TT` is required

Example of usage: 

`todo cs2103 quiz`

`deadline cs2103 quiz /by 2020-08-23`

`event cs2103 lecture /at 2020-08-23 16:00-18:00`

Expected outcome:

`Got it. I've added the task: [T][x] cs2103 quiz`

`Got it. I've added the task: [T][x] cs2103 quiz (by: Aug 23 2020)`

`Got it. I've added the task: [T][x] cs2103 quiz (at: Aug 23 2020 16:00-18:00)`
