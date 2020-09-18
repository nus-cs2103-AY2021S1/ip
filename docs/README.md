# User Guide
A simple task manager to help you manage different types of tasks.

## Features 

### Manage different types of tasks 
CRUD operations for Todos, Deadlines and Events

### Search for tasks
Search tasks by keywords or dates

### Task sorting
Sort tasks by name in alphabetical order or date in chronological order

## Getting Started
Download the latest release from <https://github.com/shadowezz/ip/releases>. Go to the directory of your downloaded
jar file and run `java -jar duke.jar` to open the application.

## Usage

### `todo` - Add todo

Adds a todo to your task list.

Example of usage: 

`todo read book`

Expected outcome:

```
Got it. I have added this task:
   [T] [✘] read book
Now you have 1 task in the list.
```
 

### `deadline` - Add deadline

Adds a deadline to your task list.

Example of usage: 

`deadline read book /by 2/2/18 1700`

Expected outcome:

```
Got it. I have added this task:
  [D] [✘] read book (by: 2/2/2018 1700)
Now you have 2 tasks in the list.
```

### `event` - Add event

Adds an event to your task list.

Example of usage: 

`event buy food /at 2/2/2018 1900`

Expected outcome:

```
Got it. I have added this task:
  [E] [✘] buy food (at: 2/2/2018 1900)
Now you have 3 tasks in the list.
```

### `list` - Show list

Displays your current task list.

Example of usage: 

`list`

Expected outcome:

```
Here are the tasks in your list:
      1. [T] [✘] read book
      2. [D] [✘] read book (by: 2/2/2018 1700)
      3. [E] [✘] buy food (at: 2/2/2018 1900)
```

### `delete` - Delete task

Deletes a task from your task list.

Example of usage: 

`delete 1`

Expected outcome:

```
Noted. I've removed this task:
  [T] [✘] read book
Now you have 2 tasks in the list.
```

### `done` - Complete task

Completes a task in your task list.

Example of usage: 

`done 1`

Expected outcome:

```
Nice! I've marked this task as done:
  [D] [✓] read book (by: 2/2/2018 1700)
```

### `find` - Search by keywords

Displays tasks that contain a certain keyword or phrase.

Example of usage: 

`find book`

Expected outcome:

```
Here are the matching tasks in your list:
  1. [D] [✓] read book (by: 2/2/2018 1700)
```

### `get` - Search by date

Displays tasks that occur on a particular date.

Example of usage: 

`get 2/2/2018 1700`

Expected outcome:

```
Here are the matching tasks in your list:
  1. [D] [✓] read book (by: 2/2/2018 1700)
```

### `sort` - Sort existing tasks

Sort your tasks by name in alphabetical order or date in chronological order

Example of usage: 

`sort name`

Expected outcome:

```
Here are the tasks in your list:
  1. [E] [✘] buy food (at: 2/2/2018 1900)
  2. [D] [✓] read book (by: 2/2/2018 1700)
```

### `bye` - Exits the program

Closes the application and saves all data on disk

Example of usage: 

`bye`

Expected outcome:

`bye!`
      

