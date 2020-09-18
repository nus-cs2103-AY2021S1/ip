# Duke User Guide
Duke is a personal task manager that helps to add, delete, find and sort tasks for you!

## Duke GUI
![Duke GUI](/docs/Ui.png)

## Setting Up
Download the latest relase of Duke.jar to start using Duke.

## Features
There are a total of 9 features on Duke for users to use.

Feature | Description
------------ | -------------
`bye` | Exits Duke application
`deadline NAMEOFDEADLINE /YYYY-MM-DD` | Creates a deadline and add it to list of tasks
`delete TASKID` | Delete the particular task
`done TASKID` | Marks the particular task as done
`event NAMEOFEVENT /YYYY-MM-DD` | Creates an event and add it to list of tasks
`find KEYWORD` | Search for any tasks based on the keyword given
`list` | Shows the current list of tasks
`sort` | Shows the current list of tasks sorted by task type
`todo NAMEOFTODO` | Creates a todo and add it to list of tasks

## Usage of Features
Below is a list of examples showing the expected outcomes of each features on Duke

### 1. When duke is launched
Expected output:
```
Hi I'm Duke! I help create and store tasks for you! How can I help you today?
```

### 2. `bye`
Sample input:
```
bye
```

Expected output:
```
Bye! Hope to see you again soon.
```

### 3. `deadline NAMEOFDEADLINE /YYYY-MM-DD`
Sample input:
```
deadline homework /2020-09-12
```

Expected output:
```
Got it. I have added this tasks:
[D][X] homework (by: Sep 12 2020)
Now you have 1 task in the list
```

### 4. `delete TASKID`
Sample input:
```
delete 1
```

Expected output:
```
Noted. I have removed this task:
[D][X] homework (by: Sep 12 2020)
Now you have 0 tasks in the list
```

### 5. `done TASKID`
Sample input:
```
done 1
```

Expected output:
```
Nice! I have marked this task as done:
[D][O] homework (by: Sep 12 2020)
```

### 6. `event NAMEOFEVENT /YYYY-MM-DD`
Sample input:
```
event birthday /2020-10-08
```

Expected output:
```
Got it. I have added this task:
[E][X] birthday (at: Oct 8 2020)
Now you have 2 tasks in the list
```

### 7. `find KEYWORD`
Sample input:
```
find birthday
```

Expected output:
```
Here are the matching tasks in your list:
[D][O] birthday (by: Sep 12 2020)
```

### 8. `list`
Sample input:
```
list
```

Expected output:
```
Here are the tasks in your list:
1. [T][O] run
2. [D][O] homework (by: Sep 12 2020)
3. [E][X] birthday (at: Oct 8 2020)
```

### 9. `sort`
Sample input:
```
sort
```

Expected output:
```
SORTED BY TASK TYPE
1. [D][O] homework (by: Sep 12 2020)
2. [E][X] birthday (at: Oct 8 2020)
3. [T][O] run
```

### 10. `todo NAMEOFTODO`
Sample input:
```
todo drink water
```

Expected output:
```
Got it. I have added this task:
[T][X] run
Now you have 3 tasks in the list
```




