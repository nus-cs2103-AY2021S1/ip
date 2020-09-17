# User Guide

![Duke Logo](./DukeImage.png)

## Features 

### Keep Track of Your Tasks
Duke is here to help keep track of the many tasks that you have!

### Save Your Tasks
Duke will automatically save your current tasks and load them in the next start up.

### Pen Down Your Notes!
Other than just keeping track of tasks, Duke has an added functionality to add notes to each task!

## Usage

Keyword | Action | Usage
--------|--------|-------
`hello` | Duke says hello!| `hello`
`list` | Show current task list | `list`
`bye` | Saves the current list and exits the program | `bye`
`todo <description>` | Create a todo Task | `todo Bring out the trash`
`event <description> /at <dd/MM/yyyy>` | Create an event Task (date is optional) | `event Mom's Birthday /at 22/02/2020`
`deadline <description> /by <dd/MM/yyyy>` | Create a deadline Task (date is optional) | `deadline Math Assignment 1 /at 02/02/2020`
`done <task number>` | Mark the specified task as done | `done 2`
`undo <task number>` | Mark the specified task as not done | `undo 2`
`delete <task number>` | Deletes the specified task from the list | `delete 1`
`find <keyword>` | Provides a list of the tasks containing the keyword in its description. | `find assignment`
`view <task number>` | Views the task and showing the note attached to the task. | `view 1`
`note <task number> <note content>` | Creates a note for the specified task number. | `note 1 This is my note`

#### Note: Use the `bye` command for exiting the application! If not your changes will not be saved.
## Example 

#####Starting with an empty task list:

Example execution | Expected outcome
------------|-----------------
`todo Bring out the trash` | `[Todo] [✘] Bring out the trash`
`event Mom's Birthday /at 22/02/2020` | `[Event] [✘] Mom's Birthday (at: 22 Feb 2020)`
`deadline Math assignment 1 /at 02/02/2020` | `[Deadline] [✘] Math assignment 1 (by: 2 Feb 2020)`
`find assignment` | `[Deadline] [✘] Math assignment 1 (by: 2 Feb 2020)`

#####Following the previous example
Example execution | Expected outcome
------------|-----------------
`done 2` | `[Event] [✓] Mom's Birthday (at: 22 Feb 2020)`
`undo 2` | `[Event] [✘] Mom's Birthday (at: 22 Feb 2020)`
`delete 1` | `Noted, deleted the following task: [Todo] [✘] Bring out the trash`
`view 1` | `[Event] [✘] Mom's Birthday (at: 22 Feb 2020) Note: No written note`
`note 1 This is my note` | `[Event] [✘] Mom's Birthday (at: 22 Feb 2020) Note: This is my note`

