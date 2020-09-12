# User Guide
Duke is a desktop chatbot application that allows users to track their daily tasks and financial expenses. It is optimised for users who are good at typing.

### Table of Contents
* [Setting up Duke](#setting-up-duke)
* [Features](#features)

## Setting up Duke
1. Ensure either Java __11__ or __13__ is installed on your computer.
1. Download the latest version of Duke [here](https://github.com/ruixuantan/ip/releases/download/v0.2/ip.jar).
1. Move the jar file downloaded into any folder of your choice.
1. Run the command: `java -jar ip.jar` to start Duke:
1. When Duke has started, enter the ?command?: `todo started Duke` \
You will see that Duke has created a new Todo item:

## Features 
### Notes regarding the format used in this guide:
1. Formats with `{input description}` are meant to be input as `input description` only, without the curly brackets.
1. Formats with `(task)OR(expense)` mean that you would only need to enter either `task` or `expense`, __not both__.

## Adding Todos: `todo`
Adds todos to Duke. Todos are basic forms of tasks.

Format: `todo {todo description}`

Example: `todo read book`

## Adding Events: `event`
Adds events to Duke. Events are tasks that are tagged with a time.

Format: `event {event description} /at {time}`

Example: `event CS2103T meeting /at 0800`

:bulb: Tip: The `{time}` must be of a 24h format, without any symbols separating the hour and minutes.

## Adding Deadlines: `deadline`
Adds deadlines to Duke. Deadlines are tasks that are tagged with a date and time.

Format: `deadline {deadline description} /by {date} {time}`

Example: `deadline return book to library /by 01-12-2020 1200`

:bulb: Tip: The `{date}` has to be of the following format: `dd-mm-yyyy`, with hyphens separating the day, month and year. The `{time}` must be of a 24h format, without any symbols separating the hour and minutes.

## Adding Payables: `pay`
Adds payables to Duke. Payables refer to expenses that you have spent.

Format: `pay {payable description} ${amount} /on {date}`

Example: `pay lunch $5 /on 02-12-2020`

:bulb: Tip:
The `{amount}` can be of the following forms: `5` or `5.00` (0 or 2 decimal places). The `{date}` has to be of the following format: `dd-mm-yyyy`, with hyphens separating the day, month and year.

## Adding Receivables: `receive`
Adds receivables to Duke. Receivables refer to money that is passed to you.

Format: `receive {receivable description} ${amount} /on {date}`

Example: `receive from Duke $100.10 /on 03-12-2020`

:bulb: Tip: The `{amount}` can be of the following forms: `100` or `100.00` (0 or 2 decimal places). The `{date}` has to be of the following format: `dd-mm-yyyy`, with hyphens separating the day, month and year.

## Listing Tasks or Expenses: `list`
Lists either all tasks or expenses in Duke.

Format: `list (task)OR(expense)`

Examples:
* `list task`
* `list expense`

## Deleting Tasks of Expenses: `delete`
Deletes the task or expense, as specified by the index passed in.

Format: `delete (task)OR(expense) {index}`

Examples: 
* `delete task 3`
* `delete expense 1`

## Completing Tasks: `done`
Marks a task as complete.

Format: `done {index}`

Example: `done 2`

:bulb: Tip: Only tasks can be marked as done!

## Searching for Tasks or Expenses: `find`
Lists all tasks or expenses with descriptions matching your input.

Format: `find (task)OR(expense) {input description}`

Examples: 
* `find task read book`
* `find expense lunch`

## Exiting Duke: `bye`
Exits Duke when you enter this command. Alternatively, you can click on the close button of the application. Both methods will save the tasks and expenses you have input. However, it is recommended to use the `bye` command instead as it informs you if the tasks and expenses you have input have been saved.

Format: `bye`

Example: `bye`

:bulb: Tip: In the event where Duke is not able to save the tasks and/or expenses, do the following: Within the folder you have moved Duke into, search for a folder named `data`. If it does not exist, create a new folder named `data`. Next, within this `data` folder, if the following text files do not exist: `task_storage.txt` and `expenses_storage.txt`, create them.
