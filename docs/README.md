# User Guide

## Features 

### Feature 1 
Fantasia is a task and expense management application which the user can use to set reminders and deadlines for upcoming events and also track their own expenses. It uses a CLI to process instructions from the user, which can provide a faster and more efficient experience than a usual to-do list application.

## Create Task
There are 3 different categories of tasks which can be created in Fantasia- `todo`,`deadline` and `event`. `todo` contains a description of the task while `deadline` and `event` contains a description of the task as well as its time(in YYYY-MM-DD format).

## Completing a Task
You can mark a Task as completed(`[✓]`) by using the `done` command.

## List all the Tasks
You can `list` all the current tasks and all its details in a list format.

## Find Tasks
You can `find` all the tasks with similar descriptions.

## Delete Task
You can `delete` task from your task list by its numbering on the task list.

## Create an Expense
You can track your expense by adding an `expense` in Fantasia.

## List all Expenses
You can track all your expenses by in a list format as well as its cumulative total.

## Delete an Expense
You can delete an expense from the list.

## Commands for Tasks

### `list` - List out all the tasks stored

Format: `list`
Example of usage: 

`list`

Expected outcome:

```
Here are the tasks in your list:
1.[T][✗] Homework 
```


### `todo` - Create a todo Task

Format: `todo <todo description>`
Example of usage: 

`todo Assignment`

Expected outcome:

```
I have added this task:
[T][✗] Assignment
Now you have 2 task(s) in the list.
```
### `deadline` - Create a deadline Task

Format: `deadline <deadline description> /by <time in YYYY-MM-DD>`
Example of usage: 

`deadline Quiz /by 2020-09-20`

Expected outcome:

```
I have added this task:
[D][✗] Quiz (by:2020-09-20)
Now you have 3 task(s) in the list.
```
### `event` - Create a event Task

Format: `event <event description> /at <time in YYYY-MM-DD>`
Example of usage: 

`deadline Hackathon /by 2020-10-01`

Expected outcome:

```
I have added this task:
[E][✗] Hackathon (by:2020-10-01)
Now you have 4 task(s) in the list.
```
### `delete` - To delete a Task

Format: `delete <task number>`
Example of usage:

`delete 1`

Expected outcome:
```
I have removed the task:
[T][✗] Homework 
Now you have 3 tasks in the list.
```
### `done` - To mark a Task as done

Format: `done <task number>`
Example of usage:

`done 1`

Expected outcome:
```
Nice! I've marked this task as done:
[T][✓] Assignment
```
### `find` - To find similar Task

Format: `find <description>`
Example of usage:
`find Assignment`

Expected outcome:
```
Here are the matching tasks in your list:
1.[T][✓] Assignment
2.[T][✗] Assignment 2
```
## Commands for Expenses

### `expense` - To add an expense

Format: `expense <description> /amount <expense amount>`
Example of usage:
`expense dinner /amount 20`

Expected outcome:
```
I have added this expense:
dinner ,Cost:$20.00
Now your total expense is:
$20.00
```
### `listExpense` - To list all expenses

Format: `listExpense`
Example of usage:
`listExpense`

Expected outcome:
```
Here are the expenses:
1.Breakfast ,Cost:$10.00
2.Lunch ,Cost:$10.00
3.Dinner ,Cost:$20.00

Total expenses: $40.00
```
### `deleteExpense` - To delete an expense

Format: `deleteExpense <expense number>`
Example of usage:
`deleteExpense 2`

Expected outcome:
```
I have removed the expense:
Lunch ,Cost:$10.00
Now you have 2 expenses in the list.
```


