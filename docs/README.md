# KING BOB User Guide

_KING BOB_ is a chat bot that helps you organise your daily tasks (bananas). Make his Highness happy by giving him more 
tasks (bananas)!
 
![Image of KING BOB](https://raw.githubusercontent.com/kkangs0226/ip/master/docs/Ui.png)
 
Below are instructions on how to use _KING BOB_.

- Features
  - todo 
  - event
  - deadline 
  - list
  - done
  - delete
  - date
  - find
  - bye  
  
 ## Features
  
### `todo` - Add a new Todo task
This command adds a new Todo task to the user's list of tasks.

Format: 
`todo TODO_DESCRIPTION`

Example of command:
- `todo read book` 

Expected result:
```
Banana! Banana has been added to your list!
      [T][✘] read book
   Now you have 1 banana(s) in your list! Nom nom..
```

### `event` - Add a new Event task
This command adds a new Event task to the user's list of tasks.

Format: 
`event EVENT_DESCRIPTION /at EVENT_DATE`

Example of command:
- `event graduation night /at 2020-02-19`

Expected result:
```
Banana! Banana has been added to your list!
      [E][✘] graduation night (at: Feb 19 2020)
   Now you have 2 banana(s) in your list! Nom nom..
```

### `deadline` - Add a new Deadline task
This command adds a new Deadline task to the user's list of tasks.


Format:
`deadline PROJECT_DESCRIPTION /by DEADLINE_DATE`

Example of command:
- `deadline project /by 2020-09-30`

Expected result:
```
Banana! Banana has been added to your list!
      [D][✘] project (by: Sep 30 2020)
   Now you have 3 banana(s) in your list! Nom nom..
```

### `list` - Display list of tasks 
This command displays the list of existing tasks to the user. 

Format:
`list`

Expected result:
```
Banana! So many tasks?
1. [T][✘] read book
2. [E][✘] graduation night (at: Feb 19 2020)
3. [D][✘] project (by: Sep 30 2020)
```

### `done` - Mark a task as done
This command marks the task at the specified index as done. 

Format:
`done TASK_INDEX`

Example of command:
- `done 1`

Expected result:
```
Banana! I’ve marked this task as done:
      [T][✘] read book
```

### `delete` - Delete a task 
This command deletes a task at the specified index. 

Format:
`delete TASK_INDEX`

Example of command:
- `delete 1`

Expected result:
```
Banana! banana has been eaten. Burp!
      [T][✘] read book
   Now you have 2 banana(s) in your list! Nom nom..
```

### `date` - Find a task by its date
This command finds tasks based on its event or deadline date. 

Format:
`date TASK_DATE`

Example of command:
- `date 2020-09-30`

Expected result: 
```
Bananas! Here are your bananas..
[D][✘] project (by: Sep 30 2020)
```

### `find` - Find a task by its keyword
This command finds tasks by keywords in the task description. 

Format: 
`find TASK_KEYWORD`

Example of command:
- `find project`

Expected result: 
```
Bananas! Here are your bananas..
[D][✘] project (by: Sep 30 2020)
```

Example of command: 
- `find book`  

Expected result: 
```
Bananas! Here are your bananas..
[T][✘] read book
```

### `bye` - Exits application
This command exits the application. 

Format:
`bye`

Expected result:
The app quits. 
  
  
## Credits

* Images retrieved from:
  * [Background](https://www.pinterest.co.kr/pin/661114420275950559/)
  * [User icon](https://www.pinterest.com/pin/484559241150248458/)
  * [KING BOB icon](https://despicableme.fandom.com/wiki/Bob)