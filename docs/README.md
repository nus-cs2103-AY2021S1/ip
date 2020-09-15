# Yoo: User Guide
---

Yoo is a simple task-tracking chatbot that users can interact with via the command-line.
It currently supports three types of tasks:
- Todo (Task with no completion date)
- Deadline (Task that has a date to complete by)
- Event (Task that has an occurence date)

## Features
---

Some of the commands that Yoo supports:
- To add tasks
  - `todo` : adds a Todo task with description
  - `event` : adds an Event task with description and date
  - `deadline` : adds a Deadline task with description and date
- `bye` : ends the conversation with Yoo
- `delete` : deletes a task
- `done` : marks a task as completed
- `find` : finds tasks that match the given keyword  
- `help` : displays a list of commands
- `list` : displays the current list of tasks
- `update` : updates the description or date of a task

##### Notes about the command format:
- Words in `UPPER_CASE` are parameters to be supplied by the user.
  eg. in `todo DESCRIPTION`, `DESCRIPTION` is a parameter which can be used as `todo borrow book`.

## Feature Usage
---

#### 1. `todo`
Adds a Todo task to the list.
Format: `todo DESCRIPTION`
Examples: 
  - `todo read book`
  - `todo buy groceries`


#### 2. `deadline`
Adds a Deadline task to the list.
Format: `deadline DESCRIPTION /by YYYY-MM-DD`
Examples:
  - `deadline assignment /by 2020-05-05`
  - `deadline essay for XX mod /by 2020-06-06`


#### 3. `event`
Adds a Event task to the list.
Format: `event DESCRIPTION /at YYYY-MM-DD`
Examples:
  - `event party /at 2020-04-04`
  - `event lunch with mom /at 2020-03-03`


#### 4. `bye`
Ends the conversation with Yoo.
Format: `bye`


#### 5. `delete`
Deletes a task from the list.
Format: `delete INDEX`
  - Deletes the task at the specified `INDEX`.
  - The index refers to the index number showed in the displayed task list.
  - The index must be a **positive integer** 1,2,3,...

Example: `delete 1`


#### 6. `done`
Marks a task as completed.
Format: `done INDEX`
  - Marks the task at the specified `INDEX` as completed.
  - The index refers to the index number showed in the displayed task list.
  - The index must be a **positive integer** 1,2,3,...

#### 7. `find`
Finds tasks that match the given keyword.
Format: `find KEYWORD`
  - The search is case-sensitive. eg. `essay` will not match `Essay`
  - Only the description of the task is searched.
  - Only full words will be matched. eg. `essay` will not match `essays` 

#### 8. `help`
Displays a list of Yoo's commands.
Format: `help`


#### 9. `list`
Displays the list of tasks.
Format: `list`


#### 10. `update`
Updates a task description or time.
Format: `update INDEX /d DESCRIPTION` or `update INDEX /t YYYY-MM-DD`
  - Updates the task at `INDEX`.
  - The index refers to the index number showed in the displayed task list.
  - The index must be a **positive integer** 1,2,3,...
  - To update the description, use the `/d` command like so: `update INDEX /d UPDATED_DESCRIPTION`
  - To update the date, use the `/t` command like so: `update INDEX /t YYYY-MM-DD`

Examples:
  - `update 1 /d CS Assignment 1`
  - `update 4 /t 2020-02-02`
