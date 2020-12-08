# User Guide

## Features 

### Notes about the command format :
- Words in `UPPER CASE` are the parameters to be supplied by the user.
  e.g. in `find KEYWORD>`, `KEYWORD` is a parameter which can be used as `find homework`.
  
- Items in square brackets are optional.
  e.g. `deadline TASK_NAME /by TASK_DATE [TASK_TIME]` can be used as `deadline maths homework /by 2020-09-09` or `deadline maths homework /by 2020-09-09 23:59`

- Items with `...` after them can received more than one parameter(s) separated by commas.
  e.g. `done LIST_NUMBER1, LIST_NUMBER2, ...` can be used as `done 1, 2, 3`.

#### Adding Task : `todo` / `deadline` / `event`
Adds input a todo/deadline/event task and its details to the list.
Format : 
- `todo TASK_NAME` 
- `deadline TASK_NAME /by TASK_DATE [TASK_TIME]` 
- `event TASK_NAME /at TASK_DATE [TASK_START_TIME]-[TASK_END_TIME]`

###### :bulb: **Tip** : parameter TASK_DATE should be in the form of `YYYY-MM-DD` and (TASK/TASK_START/TASK_END)_TIME is written as `HH:MM`

Examples :
- todo read book
- deadline CS2103 Quiz 6 /by 2020-09-14 23:59
- event family video call /at 2020-09-13 11:00-13:00

#### Deleting Task : `delete`
Deletes the task in the specified task number from the list.
Format : `delete TASK_NUMBER`

Examples :
- delete 1 (*will delete task number 1*)
- delete 3 (*will delete task number 3*)

#### Marking Task(s) As Done : `done`
Marks the task(s) in the specified task number(s) from the list.
Format : `done TASK_NUMBER1, [TASK_NUMBER2], [TASK_NUMBER3], ...`

Examples :
- done 1 (*will mark task number 1 as done*)
- done 1, 2, 3 (*will mark task number 1, 2, 3 as done*)


#### Finding Task(s) by Keyword(s) : `find`
Shows all the task containing the keyword(s).
Format : `find KEYWORD1, [KEYWORD2], ...`

Examples :
- find read (*will show all task(s) containing `read` keyword if they exist.*) 
- find CS2103, CS2103 (*will show all task(s) containing `CS2102` and `CS2103` keywords if they exist.*)


#### Finding Task(s) by Date : `show`
Shows all the task on the specified input date.
Format : `show DATE`

###### :bulb: **Tip** : DATE format should be in the form of `YYYY-MM-DD`.

Examples :
- show 2020-09-01 (*will show all task(s) on 1st September 2020*)
- show 2020-10-21 (*will show all task(s) on 21st October 2020*)


#### Sorting Task(s) : `sort`
Shows the sorted task list (Sorted by _date_, _time_, and _lexicographic order_).
Format : `sort`


#### Listing Task(s) : `list`
Lists all the task(s) in the task list.
Format : `list`


#### Detecting Duplicate(s)
Detects duplicate when adding task. Will not add the task if it already exists in the list.


#### Saving Data 
Saves data to hard disk automatically after any command that changes the data. There is no need to save manually.



## Command Summary

Action | Format
------ | ------
add a **todo** | `todo TASK_NAME`
add a **deadline** | `deadline TASK_NAME /by TASK_DATE [TASK_TIME]` 
add an **event** | `event TASK_NAME /at TASK_DATE [TASK_START_TIME]-[TASK_END_TIME]`
delete task | `delete TASK_NUMBER`
mark task(s) as done | `done TASK_NUMBER1, [TASK_NUMBER2], [TASK_NUMBER3], ...`
find by keyword(s) | `find KEYWORD1, [KEYWORD2], ...`
find by date | `show DATE`
sort list | `sort`
list | `list`



## Task Lists
- [x] Auto-exit after command `bye`. Entering command `bye` will show goodbye message but does not exit the bot automatically.
