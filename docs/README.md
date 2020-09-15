# User Guide

Duke Chatbot is a **desktop app for managing task schedules, optimised for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). 

* Quick Start
* Features
	* Adding a task : 
		* Todo : ``` todo```
		* Deadline : ```deadline```
		* Event : ```event```
	*	Viewing all tasks : ``` list```
	*	Marking a task as done : ``` done```
	*	Finding a task by name : ``` find```
	*	Viewing all tasks on a particular date : ```view```
	*	Deleting a task : ``` delete```
	*	Exiting the program: ``` bye```
* Command Summary

## Quick Start

1.  Ensure you have Java  `11`  or above installed in your Computer.
    
2.  Download the latest  `duke.jar`  from  [here](https://github.com/yejiadong/ip/releases).
    
3.  Copy the file to the folder you want to use as the  _home folder_  for your Duke chatbot.
    
4.  Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note that the app does not contain any sample task data preloaded.

![Image of Duke Chatbot](https://yejiadong.github.io/ip/Ui.png)

5.  Type the command in the command box and press Enter to execute it. 
    Some example commands you can try:
    
    -   **`list`**  : Lists all tasks scheduled
        
    -   **`todo`**`return National Library book`  : Adds a task with the description  `return National Library book`  to the task list.
        
    -   **`delete`**`1`  : Deletes the 1st task shown in the task list.
        
    -   **`done`**`1` : Marks the 1st task shown in the task list as done.
        
    -   **`bye`**  : Exits the app.
        
6.  Refer to the  [Features](https://se-education.org/addressbook-level3/UserGuide.html#features)  below for details of each command.


## Features

### **Notes about the command format:**  

-   Words in  `UPPER_CASE`  are the parameters to be supplied by the user.   
e.g. in  `todo DESCRIPTION`,  `DESCRIPTION`  is a parameter which can be used as  `todo return library book`.

- Items in square brackets are optional.  
e.g  `deadline DESCRIPTION /by DATE [TIME]`  can be used as  `deadline return book /by 2022-08-26 1800`  or as  `deadline return book /by 2022-08-26`.

## Adding a task 

### `Todo`

Adds a **todo** task to the task list. A **todo** task contains a description only.

Format: `todo DESCRIPTION`

Examples:

-   `todo Watch CS2103 Web Lecture`
-   `todo Meet Friend at JCube`

### `Deadline`

Adds a **deadline** task to the task list. A **deadline** task contains a description, a date to indicate the deadline and an optional [TIME] parameter.

Format: `deadline DESCRIPTION /by DATE [TIME]`

 >**Note :** 
 > - The delimiter /by must be indicated to separate the `DESCRIPTION` and `DATE`
 >- The date specified must be set in the future and must follow the format **yyyy-mm-dd**.
 (i.e. 2021-08-26 to denote 26th August 2021)
 >- Any time specified must be in the **HHmm** format. 
 (i.e. 1800 to denote 6 p.m)
>- Ensure there is a space to separate all parameters.

Examples:

-   `deadline Finish 2103 Quiz /by 2021-08-26 1800`
-   `deadline watch Tenet /by 2021-09-30`

![Image of Duke Chatbot](https://yejiadong.github.io/ip/Deadline.png)

### `Event`

Adds an **event** task to the task list. An **event** task contains a description, a date to indicate the event's date and an optional [TIME] parameter.

Format: `event DESCRIPTION /at DATE [TIME]`

 >**Note :** 
> - The delimiter /at must be indicated to separate the `DESCRIPTION` and `DATE`
> - The date specified must be set in the future and must follow the format **yyyy-mm-dd**.
 (i.e. 2021-08-26 to denote 26th August 2021)
> - Any time specified must be in the **HHmm** format. 
 (i.e. 1800 to denote 6 p.m)
 >- Ensure there is a space to separate all parameters.

Examples:

-   `event Go for Yiruma's Concert /at 2026-08-25`
-   `event Go for Tom's Birthday Party /at 2024-07-28 2000`

## Viewing all tasks : `list`

Shows a list of all tasks in the task list.

Format:  `list`

![Image of List Command](https://raw.githubusercontent.com/yejiadong/ip/master/docs/List.png)

## Marking a task as done : `done`

Mark a task as having been done.

Format:  **`done`**`TASK_INDEX`

-   Marks the task at the specified  `TASK_INDEX` as done.
-   The index refers to the index number shown in the displayed task list using `list`.
-   The index  **must be a positive integer**  1, 2, 3, …​

> Take note that the task index must reference a task that exists inside the task list.

![Image of Done Command](https://yejiadong.github.io/ip/Done.png)

## Finding a task by name : `find`

Finds tasks with descriptions which contain the given keyword(s).

Format:  `find KEYWORD [MORE_KEYWORDS]`

-   The search is case-sensitive. e.g  `library`  will match  `library` and not `Library`
-   The order of the keywords matters. e.g.  `birthday party`  will not match  `party birthday`
-   Only the description is searched.
-   Partial words will also be matched e.g.  `book`  will match  `books`
-   Only tasks matching all keywords specified will be returned 
e.g.  `return book`  will not match with  `return pen`,   `return` or `book`

Examples:
>Assuming task list contains the following tasks:
> 1) todo return library book
> 2) todo borrow library book
> 3) deadline visit Charlie /by 2025-08-26
-   `find library book`  returns  tasks **1 and 2**
-   `find charlie`  returns  no tasks

![Image of Find Command](https://yejiadong.github.io/ip/Find.png)

## Viewing all tasks on a particular date : `view`

Finds tasks with dates which match the specified date.

Format:  `view DATE`

-   The date specified must follow the format **yyyy-mm-dd**.
 (i.e. 2021-08-26 to denote 26th August 2021)
-   Only the date is searched.
-   Only one date should be specified

Examples:
>Assuming task list contains the following tasks:
> 1) deadline return library book /by 2021-08-26 1800
> 2) event go for graduation ceremony /at 2021-08-26 
> 3) deadline visit Charlie /by 2025-08-26
-   `find 2021-08-26`  returns  tasks **1 and 2**
-   `find 2025-08-26`  returns  task **3**


## Deleting a task : `delete`

Deletes the specified task from the task list.

Format:  **`delete`**`TASK_INDEX`

-   Deletes the task at the specified  `TASK_INDEX`.
-   The index refers to the index number shown in the displayed task list using `list`.
-   The index  **must be a positive integer**  1, 2, 3, …​

> Take note that the task index must reference a task that exists inside the task list.

## Exiting the program: `bye`

Exits the program.

Format:  `bye`

# Command summary

Action | Example
-------- | -----
todo | `todo DESCRIPTION` e.g. `todo Watch CS2103 Web Lecture`
deadline | `deadline DESCRIPTION /by DATE [TIME]` e.g.`deadline Finish 2103 Quiz /by 2021-08-26 1800`
event | `event DESCRIPTION /at DATE [TIME]` e.g. `event Go for Yiruma's Concert /at 2026-08-25`
list | `list`
done | `done TASK_INDEX` e.g. `done 2`
delete | `delete TASK_INDEX` e.g. `delete 2`
find | `find KEYWORD [MORE_KEYWORDS]` e.g. `find library`
view | `view DATE` e.g. `view 2021-08-26`
bye | `bye`

