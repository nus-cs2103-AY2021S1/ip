# Duckie User Guide
Duckie is your task manager to manage all your tasks. 

## Features 

### Add new tasks
Add tasks so that Duckie can manage them for you!
You can add the following types of tasks:
1. **Todo** - A simple task with only a description
1. **Deadline** - A task containing a deadline
1. **Event** - A task containing the date of event

### List all the tasks
List all the input tasks in the current task list and check their status

### Delete tasks
Delete unwanted tasks in the current task list. You can choose to delete all tasks at one go as well.

### Find tasks
Search for tasks based on keyword you have input.

### Mark tasks as done
Each task has its own status and can be marked done when required.

### Sort events or deadline based on their dates
Sort the event tasks, or the deadline tasks based on their dates in ascending order.

### Exit the platform
Input bye command and exit the platform to say goodbye to Duckie.

## Usage

### `todo` - A simple task with only a description

Format: `todo DESCRIPTION`

Example of usage: '

`todo borrow books`

Expected outcome:

`"Quack! Added: `
`[T][✘] borrow books`
`Now you have " + tasks.getTaskList().size() + " task(s) in the list.`
