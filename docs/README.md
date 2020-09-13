# User Guide 

## Introduction
A simple guide to get you kick started on how to easily use this CLI app to create and manage your personal TaskList
## Features 

<details>
<summary>
View Function
</summary>

## `list`
<br>

**Command to view tasks**
<br>

* Type in `list`
* List of all tasks currently contained in tasklist will be shown
<br>
<br>

![list](./Images/list.png)
<hr>
</details>
<details>
<summary>
Add Functions
</summary>
<br>

**Here are the commands to add different types of tasks into your tasklist**  
<br>
<details>
<summary>Add Todo</summary>

## `todo`
<br>

* Type `todo` followed by a space and then type in the 'todo' you wish to add into your list
<br>
<br>

![Todo](./Images/todo.png)
</details>

<details>
<summary>Add deadline</summary>

## `deadline`
<br>

* Type `deadline` followed by description of deadline, then followed by  `/by` followed by a space and then type the rest of the description
* Input date via `YYYY-MM-DD` format and time in `HH:MM` format if you wish to add date and time
<br>
<br>

![Deadline](./Images/deadline.png)
</details>
<details>
<summary>Add event</summary>

## `event`
<br>

* Type `event` followed by description of event, then followed by `/at` followed by a space and then type in the rest of the description
<br>
* Input date via `YYYY-MM-DD` format and time in `HH:MM` format if you wish to add date and time
<br>
<br>

![Event](./Images/event.png)
</details>
<hr>
</details>

<details>
<summary>
Search Functions
</summary>
<br>

**Here are the commands to search for tasks via description, time or date**  
<br>
<details>
<summary>
Search Via Description
</summary>

## `find`
<br>

* Type in `find` and then the description that you want to search for in the tasklist
* Return you tasks which contains the description you searched for
<br>
<br> 

![find](./Images/find.png)
</details>
<details>
<summary>
Search Via Time
</summary>

## `time`
<br>

* Type in `time` followed by the time that you wish to search for in `HH:MM` format
* Returns you the tasks which take place at the time you searched for
<br>
<br>

![time](./Images/time.png)
</details>
<details>
<summary>
Search Via Date
</summary>

## `schedule`
<br>

* Type in `schedule` followed by date that you wish to search for in `YYYY-MM-DD` format
* Returns you the tasks which take place on the date you searched for
<br>
<br> 

![schedule](./Images/schedule.png)
</details>
<hr>
</details>

<details>
<summary>
Update functions
</summary>
<br>

**Commands to edit tasklist**
<br>
<br>
<details>
<summary>
Change Task Status
</summary>

## `done`
<br>

* Type in `done` followed by task number which you wish to mark as done
* Status icon of task will be changed
<br>
<br>

![done](./Images/done.png)
</details>
<details>
<summary>
Delete Task
</summary>

## `delete`
<br>

* Type in `delete` followed by task number of task you wish to remove from tasklist
* Task with corresponding number entered will be removed
<br>
<br>

![delete](./Images/delete.png)
</details>
</details>

## Usage

### `Keyword` - Describe action

Describe action and its outcome.

Example of usage: 

`keyword (optional arguments)`

Expected outcome:

`outcome`
