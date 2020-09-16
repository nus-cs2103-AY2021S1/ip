# **User Guide for Duck**


## **Introduction**
Duck is a highly-intelligent bot that would be able to help to track your day-to-day tasks and record important events in your life. While these daily tasks might sometimes seem too trivial and become a hassle to note down, they are usually the ones that you tend to forget. After all, you never know when these tasks could snowball into something significant. A stitch in time saves nine!




## Features 
1. **Adding tasks**: 
    1. **`todo`**
    2. **`deadline`**
    3. **`event`**
2. **Other task list operations**
    1. **`list`**
    2. **`find`**
    3. **`done`**
    4. **`delete`**
    5. **`clear`**
3. **Dealing with duplicates**.
    1. **`duplicates`**
    2. **`remove duplicates`**
4. **Other operations**: 
    1. **`help`**
    2. **`bye`**




## **Details of features**



#### _Adding tasks_:
### 1a.`Todo`

###### Adds a todo task to the tasks list.

**Usage: "todo (task)"**

**_Example: "todo homework"_**

Expected result: 
!["print"](.\ss\todo.png)



### 1b.`Deadline`

###### Adds a task with a specified deadline to the tasks list.

**Usage: "deadline (task) by / (deadline in MM-DD-YYYY hhmm format) "**

**_Example: "deadline homework /by 2014-12-25 1630"_**

Expected result: 
!["print"](.\ss\deadline.png)



### 1c.`Event`

###### Adds a task with a specified event time to the tasks list.

**Usage: "event  (task)  at / (deadline in MM-DD-YYYY hhmm format) "**

**_Example: "event hackathon /at 2014-12-25 1630"_**

Expected result: 
!["print"](.\ss\event.png)



#### _Other task list operations_:
### 2a.`List`

###### Prints out the list of tasks you told Duck.

**Usage: "list"**

**_Example: "list"_**

Expected result: 
!["print"](.\ss\list.png)



### 2b.`Find`

###### Finds all tasks with the keyword provided by user.

**Usage: "find"**

**_Example: "find homework"_**

Expected result: 
!["print"](.\ss\find.png)



### 2c.`Done`

###### Marks a task as done in the tasks list.

**Usage: "done (task number)"**

**_Example: "done 2"_**

Expected result: 
!["print"](.\ss\done.png)



### 2d.`Delete`

###### Deletes a task from the tasks list.

**Usage: "delete (task number)"**

**_Example: "delete 2"_**

Expected result: 
!["print"](.\ss\delete.png)



### 2e.`Clear`

###### Clears the task list.

**Usage: "clear please"**

**_Example: "clear please"_**

Expected result: 
!["print"](.\ss\clear.png)



#### _Dealing with duplicates_:
### 3a.`Duplicates`

###### Fetches all duplicated tasks in the list.

**Usage: "duplicates"**

**_Example: "duplicates"_**

Expected result: 
!["print"](.\ss\duplicate.png)



### 3b.`Remove duplicates`

###### Removes all duplicated tasks except the first copy.

**Usage: "remove duplicates"**

**_Example: "remove duplicates"_**

Expected result: 
!["print"](.\ss\removeDuplicates.png)



#### _Other Duck operations_:
### 4a.`Help`

###### Prints out a list of commands Duck understand.

**Usage: "help"**

**_Example: "help"_**

Expected result: 
!["help"](.\ss\help.png)



### 4b.`Bye`

###### Exits duck application.

**Usage: "bye"**

**_Example: "bye"_**

Expected result: Duck application closed.

