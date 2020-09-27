# Timmi User Guide :clipboard:
![Timmi BigHead io avatar icon](../src/main/resources/images/BigheadDD.png)

Timmi the Task Manager is a chat bot personality that is created for the CS2103T 
individual project. It is a simple application to help users manage their tasks.

## :peach: Features :peach:
Overview of Features:
1. Add tasks
1. Sort tasks chronologically
1. View existing tasks
1. Keep track of done tasks
1. Delete existing tasks
1. Search tasks by date or keyword
1. Exit the application

### Add Tasks
Timmi supports the addition of tasks under **3 task types**, namely:
* `todo`
* `deadline`
* `event` 

See the respective usages for more information.

### Sort Tasks Chronologically
Timmi automatically sorts all tasks input for the user in the following sequence:
1. All Todo tasks, according to the sequence they are added.
1. All Event and Deadline tasks, in chronological order.

### View Existing Tasks
Timmi allows users to view their current task list. See usage of `list`.

### Keep Track of Done Tasks
Timmi allows users to mark their tasks as done through symbols ✘ and ✓. 
See usage of `done`.

### Delete Existing Tasks
Timmi allows users to remove tasks from their list. See usage of `delete`.

### Search Tasks by Date or Keyword
Timmi allows users to search for tasks related to a date, a query word or phrase.
See usage of `check` and `find`.

### Exit the Application
See usage of `bye`.

## :peach: Usage :peach:

### `todo` - Adds a todo task

A todo task will be taken in and added to the existing task list.

`todo <item description>`

Example of usage and outcome: 

`> todo prepare meeting materials`

`Ok, To-do added:`<br>
&nbsp;&nbsp;`[T][✘] prepare meeting materials`

---

### `deadline` - Adds a deadline task

A deadline task will be taken in and added to the existing task list.

`deadline <item description> /by <deadline date> <(OPTIONAL) time>`

Example of usages and outcomes: 

`> deadline lab assignment /by 31-12-2020`

`Ok, Deadline added:`<br>
&nbsp;&nbsp;`[D][✘] lab assignment (by: 31 Dec 2020)`

---

`> deadline CS2103T tutorial /by 31-12-2020 2359`

`Ok, Deadline added:`<br>
&nbsp;&nbsp;`[D][✘] CS2103T tutorial (by: 31 Dec 2020 11:59 PM)`

---

### `event` - Adds an event task

An event task will be taken in and added to the existing task list.

`event <item description> /at <event date> <(OPTIONAL) time>`

Example of usages and outcomes: 

`> event team meeting /at 31-12-2020`

`Ok, Event added:`<br>
&nbsp;&nbsp;`[E][✘] team meeting (at: 31 Dec 2020)`

---

`> event group presentation /at 31-12-2020 2359`

`Ok, Event added:`<br>
&nbsp;&nbsp;`[E][✘] group presentation (at: 31 Dec 2020 11:59 PM)`

---

### `list` - Shows user their current task list

Timmi will print the current task list for the user.

Example of usage and outcome: 

`> list`

`Here is your current list of task(s)!`<br>
`----------------------------------------`<br>
`1. [T][✘] prepare meeting materials`<br>
`2. [D][✘] lab assignment (by: 31 Dec 2020)`<br>
`3. [E][✘] group presentation (at: 31 Dec 2020 11:59 PM)`

---

### `done` - Marks tasks as completed

Tasks will be marked as done on the task list.

`done <task index>`

Example of usage and outcome: 

`> done 2`

`Wow!! Good job!!`<br>
&nbsp;&nbsp;`[D][✓] lab assignment (by: 31 Dec 2020)`

---

### `delete` - Removes tasks from task list

Tasks will be deleted from the task list.

`delete <task index>`

Example of usage and outcome: 

`> delete 2`

`Alright! I've deleted the task:`<br>
&nbsp;&nbsp;`[D][✓] lab assignment (by: 31 Dec 2020)`<br>
`You now have 2 task(s) in your list!`

---

### `check` - Checks for tasks on specified date

Tasks on the specified date will be listed out for the user.

`check <date>`

Example of usage and outcome: 

`> check 31-12-2020`

`Here is your list of task(s) on 31-12-2020`<br>
`----------------------------------------`<br>
`1. [E][✘] group presentation (at: 31 Dec 2020 11:59 PM)`

---

### `find` - Checks for tasks related to a keyword or phrase

Tasks related to the keyword or phrase will be listed out for the user.

`find <keyword or phrase>`

Example of usage and outcome: 

`> find meeting`

`Here is your list of task(s) related to meeting`<br>
`----------------------------------------`<br>
`1. [T][✘] prepare meeting materials`

---

### `bye` - Exits the application

An exit greeting will be shown, and the application will close.

Example of usage and outcome: 

`> bye`

`You're leaving? Bye :( Come back soon!`<br>
*Application close*