# Rogue

**Rogue is a task manager that takes the form of a chatbot.**

![Screenshot of Rogue](Ui.png)

It is a desktop application that provides an interactive way to organize your tasks through its command-line interface.
If you enjoy typing, Rogue may give you a productivity boost.

## Features

### Keep track of different types of tasks!

Rogue can store 3 types of tasks: To-dos, Deadlines, and Events.
Use them to your advantage and systematize your workflow.

### Search for upcoming tasks

Events and Deadlines have a date component Rogue can easily filter to see all your tasks that are due soon.
Never forget your important tasks again.

### Persisting, exportable data

Rogue saves tasks to a text file automatically whenever there are new changes.
You don't have to worry about losing data.
Also, transferring your data is as simple as moving the text file.

### Be enamored with Rogue's charming personality

Rogue may be a sassy chatbot, but you may grow to like how adorable he is.

## Requirements

* Java 11 or later

## Getting Started

1. Download the latest version of Rogue [here](https://github.com/w-yeehong/ip/releases).
1. Double click on `rogue.jar` to run.

## Usage

An option surrounded by [] indicates that it is optional.

### `help` - view instructions

Displays all possible actions and their options.

_Syntax_

```
help
```

### `todo` - create Todo

Adds a task of type Todo.

_Syntax_

```
todo /d description
```

_Example_

```
// Adds a Todo with description "Call Betty"

> todo /d Call Betty
```

### `deadline` - create Deadline

Adds a task of type Deadline.
A Deadline takes a date in the format `dd-MM-yyyy`.

_Syntax_

```
deadline /d description /by date
```

_Example_

```
// Adds a Deadline with description "Submit homework"
// and due on 4 May 2021

> deadline /d Submit homework /by 04-05-2021
```

### `event` - create Event

Adds a task of type Event.
An Event takes a date in the format `dd-MM-yyyy`.

_Syntax_

```
event /d description /at date
```

_Example_

```
// Adds an Event with description "Attend music festival"
// and due on 1 January 2021.

> event /d Attend music festival /at 01-01-2021
```

### `list` - view tasks

Shows all tasks that have been added.

_Syntax_

```
list
```

### `done` - mark tasks as complete

Marks a task with the specified index as complete.
The index is an integer corresponding to the order shown by a `list` action.

_Syntax_

```
done /i index
```

_Example_

```
> list

Do you really need me to name them out for you?

1. [T][&#2717;] Pat a chinchilla
2. [D][&#2717;] Submit team report (by: Sep 30 2020)

// Marks entry with index 2 as complete
// i.e. [D][&#2713;] Submit team report (by: Sep 30 2020)

> done /i 2

```

### `delete` - delete tasks

Deletes the task with the specified index.
The index is an integer corresponding to the order shown by a `list` action.

_Syntax_

```
delete /i index
```

_Example_

```
> list

Do you really need me to name them out for you?

1. [T][&#2717;] Pat a chinchilla
2. [D][&#2717;] Submit team report (by: Sep 30 2020)

// Deletes entry with index 2
// i.e. [D][&#2717;] Submit team report (by: Sep 30 2020)

> delete /i 2

```

### `find` - filter tasks

Searches for all tasks that meet the search criteria.
At least one criterion must be provided:

* A `/d description` search filters for tasks that match the description (case-sensitive).
* A `/days numOfDays` search filters for tasks that are due in a certain number of days, relative to system date.
  Todos do not have an explicit date component and do not work with this filter.
  The start date (system date) and end date (start date + number of days) are included in the search.
  
_Syntax_

```
find [/d description] [/days numOfDays]
```

_Example_

```
// Finds tasks with description containing "Submit"

> find /d Submit

// Finds tasks due today

> find /days 0

// Finds tasks with description containing "Submit"
// and due today and tomorrow

> find /d Submit /days 1

```