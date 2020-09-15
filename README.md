# Duke User Guide
Duke is task manager that helps you organise your to dos, deadline, and events all in one place. 

## Duke GUI
![Duke GUI](docs/Ui.png)

## Setting Up 
Download the latest release of Duke.jar to start using Duke.

## Features 
There are a total of 12 commands on Duke.

Feature | Description
------------ | -------------
todo NAMEOFTODO | Creates a todo and adds it to the list of tasks
deadline NAMEOFDEADLINE /by YYYY-MM-DD | Creates a deadline and adds it to the list of tasks
event NAMEOFEVENT /at YYYY-MM-DD | Creates an event and adds it to the list of tasks 
list | Shows the current list of tasks 
done TASKID | Marks the specified task as done 
delete TASKID | Deletes the specified task 
find KEYWORD | Searches for tasks that contain the specified keyword
tag TASKID TAGNAME | Tags the specified task with a specified tag 
findtag TAGNAME | Searches for tasks that contain the specified tag 
show tags | Shows the user's current list of tasks and their respective tags 
removetag TASKID TAGNAME | Removes the specified tag from the specified task
bye | Exits the duke application

## Usage of Commands

Example usages of the various commands are shown below:

**NOTE:** Currently, Duke only supports the following *tag names*: 
```
fun, boring, exciting, urgent, chill, sian, laze
```
### 1. When duke is launched
Expected output:
```
Hello I'm Duke! 
What can I do for you?
```

### 2. `todo NAMEOFTODO`
Sample input:
```
todo do homework
```

Expected output:
```
Got it. I've added this task: 
[T][X] do homework 
Now, you have 1 tasks in the list
```

### 3. `deadline NAMEOFDEADLINE /by YYYY-MM-DD`
Sample input:
```
deadline go to the gym /by 2020-09-20
```

Expected output:
```
Got it. I've added this task: 
[D][X] go to the gym (by: Sep 20 2020) 
Now, you have 2 tasks in the list
```

### 4. `event NAMEOFEVENT /at YYYY-MM-DD`
Sample input:
```
event meet friends /at 2020-10-20
```

Expected output:
```
Got it. I've added this task: 
[E][X] meet friends (at: Oct 20 2020) 
Now, you have 3 tasks in the list
```

### 5. `list`
Sample input:
```
list
```

Expected output:
```
Here are the tasks in your list: 
1. [T][X] do homework 
2. [D][X] go to the gym (by: Sep 20 2020)
3. [E][X] meet friends (at: Oct 20 2020)
```

### 6. `done TASKID`
Sample input:
```
done 1
```

Expected output:
```
Nice! I've marked this task as done: 
[T][O] do homework
```

### 7. `delete TASKID`
Sample input:
```
delete 1
```

Expected output:
```
Noted. I've removed this task: 
[T][O] do homework 
Now, you have 2 tasks in the list
```

### 8. `find KEYWORD`
Sample input:
```
find gym
```

Expected output:
```
Here are the matching tasks in your list: 
1. [D][X] go to the gym (by: Sep 20 2020)
```

### 9. `tag TASKID TAGNAME`
Sample input:
```
tag 2 chill
```

Expected output:
```
Nice! I've tagged this task with CHILL:
[E][X] meet friends (at: Oct 20 2020)
```

### 10. `findtag TAGNAME`
Sample input:
```
findtag CHILL
```

Expected output:
```
Here are the matching tasks in your list:
1. [E][X] meet friends (at: Oct 20 2020)
tags: #CHILL
```

### 11. `show tags`
Sample input:
```
show tags
```

Expected output:
```
Here are the tasks in your list: 
1. [D][X] go to the gym (at: Sep 20 2020)

2. [E][X] meet friends (at: Oct 20 2020)
tags: #CHILL
```

### 12. `removetag TASKID TAGNAME`
Sample input:
```
removetag 2 CHILL
```

Expected output:
```
Nice! I've removed the tag: chill
from [E][X] meet friends (at: Oct 20 2020)
```

### 13. `bye`
Sample input:
```
bye
```

Expected output:
```
Bye! Hope to see you again soon!
```
