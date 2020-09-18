# User Guide

Duke is a revolutionary **chatbot desktop app that keeps track of your tasks for you.** **Optimised for use via a Command Line Interface** (CLI), Duke also supports a beautiful Graphical User Interface (GUI) to brighten up your day and help you get things done faster.

# Table Of Contents
* Quick Start
* Features
	1.  Adding a task : 
		1.  Todo : [``` todo```](https://avalionnet.github.io/ip/#Todo)
		2. Event : [```event```](https://avalionnet.github.io/ip/#Event)
		3. Deadline : [```deadline```](https://avalionnet.github.io/ip/#Deadline)
	2.	Viewing tasks :
			1.	View all saved tasks : ``` list```
			2.	View all tasks on a particular date : ```view```
			3.	Find a task by name/description : ``` find```
	3.	Mark a task as done : ``` done```
	4.  Delete a task : ``` delete```
	5.	Exit the program: ``` bye```
* Command Syntax Summary

# Quick Start

Installing Duke is fairly simple and 5 minutes is all you will need. Mark the list as you complete the steps below!

 -[x]  Ensure that you have Java `11` or above installed on your computer. 
 - To find out the Java version you have installed, click [here](https://www.java.com/en/download/help/version_manual.xml). 
 - If Java is not installed on your computer, click [here](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html) to download it. 
 -[x]  Download the latest `duke.jar` software from [here](https://github.com/avalionnet/ip/releases).
 
 -[x]  Save the file to the folder you want Duke to be installed in. 
 -[x]  Double-click the `duke.jar` file to start the app. 
 - **Note that Duke will automatically create an additional `data` folder in this 	directory to save your tasks upon starting the program for the first time.
 - You should observe the following GUI appearing :
 
![Intro Image of Duke Chatbot](https://github.com/Avalionnet/ip/blob/master/docs/Demo.png)

# Tutorial
Here are some features you can try upon your first launch of duke. Type the following commands in the input field at the bottom of the GUI and hit enter or click on the send button. Observe what happens.
    
- [x]  **`todo`**`Complete weekly programming quiz`  : 
		Adds a task with the description  `Complete weekly programming quiz`  to your task list.
- [x]  **`list`**  : Lists all tasks scheduled

- [x]  **`done`**`1`  : Marks the 1st task shown in the task list as done.

- [x]  **`delete`**`1`  : Deletes the 1st task shown in the task list.
        
- [x]  **`bye`**  : Exits the app.
- [x] Refer to the Features Section below for more commands that Duke supports.

### **User Guide Command Formatting :**  

-    `UPPER_CASE` values used in command formatting examples indicates the unique parameters the user needs to provide.   

> For example,  in  `done INDEX`,  `INDEX`  here refers to the index of the task that the user wants to mark as complete and hence can take on a varying value (1 - size of list).

- Items in square brackets are optional.  

> For instance,  `deadline DESCRIPTION /by DATE [TIME]`  can be used as  `deadline
> submit report /by 03.12.20 1800`  or as  `deadline submit report /by
> 03.12.20`.

## 1. Adding a task 

### `Todo`

Calling the **todo** command adds a todo task to the task list. A **todo** task contains only requires a description and will not accept an accompanying date or time. To add tasks with date and time, refer to the [deadline](https://avalionnet.github.io/ip/#Deadline) or [event](https://avalionnet.github.io/ip/#Event) commands.

Format: `todo DESCRIPTION`

> Examples:
> 
> -   `todo Celebrate Teacher's Day`
> -   `todo Complete GEQ1000 Quiz`
>
![Image of Todo Command](https://github.com/Avalionnet/ip/blob/master/docs/Todo.png)

### `Event`

Calling the **event** command adds an event task to the task list. An **event** task contains a description, date and time parameter to indicate the event's date and time.

Format: `event DESCRIPTION /at DATE TIME`

**Examples:**

-   `event CS2101 OP1 /at 15.09.20 1000`
-   `event Hackathon Finale /at 19.09.20 0900` 

![Event Image of Duke Chatbot](https://github.com/Avalionnet/ip/blob/master/docs/Event.png)

 >**Important Note :** 
> - The delimiter **/at must be added** between the `DESCRIPTION` and `DATE` of the event.
> - The date specified must be set in the future and must follow the format **dd.mm.yy**
> Note that a single digit day or month must also be accompanied by a 0 in front.
 (i.e. 03.02.21 to denote 3rd Feburary 2021)
> - Any time specified must be in the **HHmm** format. 
 (i.e. 2100 to denote 9 p.m)
 >- Please ensure that there is a space between all key parameters for accurate parsing by Duke.


### `Deadline`

Calling the **deadline** command adds a deadline task to the task list. A **deadline** task contains a description, date and time parameter to indicate the event's date and time.

Format: `deadline DESCRIPTION /by DATE TIME`

**Examples:**

-   `deadline CS2100 lab 2 /by 20.09.20 1000`
-   `deadline Art competition submission /by 30.10.20 2100` 

![Deadline Image of Duke Chatbot](https://github.com/Avalionnet/ip/blob/master/docs/Deadline.png)

 >**Important Note :** 
> - The delimiter **/by must be added** between the `DESCRIPTION` and `DATE` of the event.
> - The date specified must be set in the future and must follow the format **dd.mm.yy**
> Note that a single digit day or month must also be accompanied by a 0 in front.
 (i.e. 03.02.21 to denote 3rd Feburary 2021)
> - Any time specified must be in the **HHmm** format. 
 (i.e. 2100 to denote 9 p.m)
 >- Please ensure that there is a space between all key parameters for accurate parsing by Duke.

## 2. Viewing tasks 

### a. View all saved tasks : `list`

Displays and prints all tasks in the task list to the GUI.

Format:  `list`

> **Note:**
> Do not add any other parameters or descriptions behind the list word to trigger this command successfully.

![Image of List Command](https://github.com/Avalionnet/ip/blob/master/docs/List.png)

### b. View all tasks on a particular date : `view`

Finds tasks that match the specified date. Only works with tasks that support date and time values like `deadline` or `event` tasks.

Format:  `view DATE`

**Examples:**  For illustration purposes, let us assume that your current task list contains the following tasks:
 1. todo buy dinner 
 2. event attend housewarming party /at 29.10.20  1000
 3. deadline submit CS2103T user guide /by 18.09.20 2359
 4. event halo meetup /at 29.10.20 0900
-   `view 29.10.20`  returns  tasks **2 and 4**
-   `view 18.09.20`  returns  task **3**
-    task 1 will never be returned 

![Image of View Command](https://github.com/Avalionnet/ip/blob/master/docs/View.png)


> **Note:** 
> - The date specified must be set in the future and must follow the format **dd.mm.yy**
> Note that a single digit day or month must also be accompanied by a 0 in front.
 (i.e. 03.02.21 to denote 3rd Feburary 2021)
> -   The view command only supports a search by the input date. Searching by time is not yet supported.
> -   Please do not specify more than 1 date

### c. Find a task by name : `find`

Find tasks in the task list with descriptions that match or contains the given keyword(s).

Format:  `find KEYWORD [MORE_KEYWORDS]`

**Examples:** For illustration purposes, let us assume that your current task list contains the following tasks:  1) todo return Henry his iPhone cable   2) todo treat Henry to lunch  3) deadline pass Henry the completed source code /by 20.10.22
-   `find Henry`  returns  tasks **1 , 2 and 3**
-   `find iPhone cable`  returns task 1
-   `find dinner` returns no task 

![Image of Find Command](https://github.com/Avalionnet/ip/blob/master/docs/List.png)

> **Note:**
> -   The search only works on task descriptions.
> -   The search is case-sensitive. e.g  `Henry`  will match  `Henry` and not `henry`
> -   The order of the keywords matters. e.g.  `iPhone cable`  will not match  `cable iPhone`
> -   Partial words will also be matched e.g.  `iP`  will match  `iPhone`
> -   Only tasks matching all keywords specified will be returned  e.g.  `treat Henry`  will not match with  `treat Peter`,   `treat ***` or `*** Henry` where *** refers to an arbitrary value.


## 3. Marking a completed task : `done`

Mark a task as complete or done. This adds a tick symbol beside the targeted task.

Format:  **`done`**`TASK_INDEX`

-   Marks the task at the specified  `TASK_INDEX` as done.
-   First, use the `list` command to find the index of the task you want to mark as done. 
-   The `TASK_INDEX` here refers to the number shown beside the accompanying task in the task list after displaying it with the`list` command.
-   Please use a  **positive integer**  (eg. 1, 2, 3 …) for the index value to successfully mark a task as done.

> **Note:**
>  The task index must reference an existing task in the task list.

![Image of Done Command](https://github.com/Avalionnet/ip/blob/master/docs/Done.png)


## 4. Deleting a task : `delete`

Deletes the specified task from the task list.

Format:  **`delete`**`TASK_INDEX`

-   Deletes the task at the specified  `TASK_INDEX` from the task list.
-   First, use the `list` command to find the index of the task you want to delete. 
-   The `TASK_INDEX` here refers to the number shown beside the accompanying task in the task list after displaying it with the`list` command.
-   Please use a  **positive integer**  (eg. 1, 2, 3 …) for the index value to successfully mark a task as done.

![Image of Done Command](https://github.com/Avalionnet/ip/blob/master/docs/Delete.png)

> **Note:**
>  The task index must reference an existing task in the task list.

## 5. Exit the program: `bye`

Closes Duke and exits the program.

Command syntax:  `bye`

> **Note:**
> Do not add any other parameters or descriptions behind the list word to trigger this command successfully.


# Command syntax summary

Action | Command Syntax & Example Application
-------- | -----
todo | `todo DESCRIPTION` e.g. `todo Celebrate Teacher's Day`
event | `event DESCRIPTION /at DATE TIME` e.g. `event CS2101 OP1 /at 15.09.20 1000`
deadline | `deadline DESCRIPTION /by DATE TIME` e.g.`deadline CS2100 lab 2 /at 20.09.20 1000`
list | `list`
view | `view DATE` e.g. `view 12.12.20`
find | `find KEYWORD [MORE_KEYWORDS]` e.g. `find my coffee`
done | `done TASK_INDEX` e.g. `done 1`
delete | `delete TASK_INDEX` e.g. `delete 3`
bye | `bye`


