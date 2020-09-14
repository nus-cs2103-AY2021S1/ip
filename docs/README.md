# User Guide

## 1. Introduction
Duke is a personal assistant that can help remember important tasks
and deadlines for you!

## 2. Features 

### 2.1 Adding a Todo
Adds a todo item to the list.
The item can be a string containing spaces.

Format: `todo ITEM`

Shortcut: `t ITEM`

Examples:
 - `todo make user guide`
 - `t submit homework`

### 2.2 Adding an Event
Adds an event with a date to the list.
The item can be a string containing spaces.
The format of the date is `YYYY-MM-DD`.

Format: `event ITEM -at DATE`

Shortcut: `e ITEM -at DATE`

Examples:
 - `event amazing product guide reveal -at 2020-09-13`
 - `e sleep -at 2020-10-11`

### 2.3 Adding a Deadline
Adds a deadline with a date to the list.
The item can be a string containing spaces.
The format of the date is `YYYY-MM-DD`.

Format: `deadline ITEM -by DATE`

Shortcut: `d ITEM -by DATE`

Examples:
 - `deadline homework -by 2020-09-13`
 - `d finish project -by 2020-10-11`

### 2.4 Listing items
Lists all items that are recorded.

Format: `list`

Shortcut: `l`

### 2.5 Find an item
Searches for an item in the list using the keyword provided.

Format: `find KEYWORD`

Shortcut: `f KEYWORD`

Examples:
 - `find homework`
 - `f project`

### 2.6 Mark an item as done
Mark an item on the list as completed.
The index is the number of the item in the list command.

Format: `done INDEX`

Shortcut: `c INDEX`

Examples:
 - `done 5`
 - `c 12`

### 2.7 Delete an item
Removes an item permanently from the list.
The index is the number of the item in the list command.
The indexes of the subsequent items will be updated accordingly.

Format: `delete INDEX`

Shortcut: `x INDEX`

Examples:
 - `delete 1`
 - `x 3`

### 2.8 Add a new macro
**Warning: this is an advanced feature!**

Adds a macro to run several commands in succession.

NOTE: allows you to use one of the arguments as a command, allowing
you to build higher order commands but also potentially infinite loops.

**USE WITH CAUTION**.

#### How it works

Macros accept *arguments* that can be substituted as variables into a series
of commands defined by a user.

Arguments are strings with associated option names added after the macro
name.
Arguments with no associated option name are called *Unnamed arguments*.

E.g. in the macro `sample abc -t test -v test2`, the macro's name is
 `sample`. `test` and `test2` are
arguments associated to the options `t` and `v` respectively, and
`abc` is an unnamed argument.

Arguments to the macro can be substituted in the commands using the syntax:
`\ARGUMENT_NAME`.

Unnamed arguments can be substituted using the syntax: `\$`

Format: `macro MACRO_NAME ARGUMENT_1 ARGUMENT_2 ... ; 
COMMAND_NAME_1 ARGUMENTS_TO_COMMAND \REUSED_ARGUMENT; COMMAND_NAME_2...`

#### Example 1
`macro spEvent -at ; event special \$ -at \at`
 
 Example usage of this macro: `spEvent test -at 2020-09-13`
 
 The following command will be executed by the macro: `event special test -at 2020-09-13`
 
 i.e. in `event special \$ -at \at`, `\$` and `\at` will be substituted with
  `test` and `2020-09-13` respectively.

#### Example 2
`macro doubleTodo ; todo \$ ; todo second \$`
 
 Example usage of this macro: `doubleTodo hello`
 
 The following commands will be executed by the macro: `todo hello` and `todo second hello`. 