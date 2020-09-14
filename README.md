# DoLah User Guide [![Status](https://travis-ci.org/BobbyZhouZijian/ip.svg?branch=master)](https://travis-ci.org/BobbyZhouZijian/ip)

![App Image](./docs/Ui.png)
## Features

DoLah is a task managing application that helps you too keep track of what you list of tasks.

## Usage

### Build and Run

To use the the Do Lah, simply compile and run the Duke.java file at `src/main/java/duke/Duke.java`.

You can also build the application and run it using gradle.

A packaged fat jar file has been attached in the latest stable release. Feel free to download it 
and enjoy DoLah by type the command `java -jar ip.jar`

### 1. `list` - List out all current tasks

DoLah will return you a list of the tasks currently inside the application.

Format: `list`

Example: 
```$xslt
list
```

Expected Outcome:
```
Here are the tasks in your list:
1. [T][✓] do work~
2. [D][✗] submit ip (by: Sep 16 2020)
```

### 2. `todo` - Add a todo task

Add a todo task with the specified content to DoLah.

Format: `todo CONTENT`

Example: 
```$xslt
todo submit individual project
```

Expected Outcome:
```
Got it. I've added this task:
    [T][✗] submit individual project
Now you have 3 tasks in the list.
```

### 3. `deadline` - Add a Deadline Task

Add a deadline task with the specified content and deadline to DoLah.

Format: `deadline CONTENT /by YYYY-MM-DD`

Example: 
```$xslt
deadline revise for midterm /by 2020-09-25
```

Expected Outcome:
```$xslt
Got it. I've added this task:
    [D][✗] revise for midterm (by: Sep 25 2020)
Now you have 4 tasks in the list.
```

### 4. `event` - Add an event

Add an event with the specified content and event date to DoLah.

Format: `event CONTENT /at YYYY-MM-DD`

Example: 
```$xslt
event go for CS2103 lecture /at 2020-09-15
```

Expected Outcome:
```$xslt
Got it. I've added this task:
    [E][✗] go for CS2103 lecture (on: Sep 15 2020)
Now you have 5 tasks in the list.
```


### 5. `done` - Mark task as done

Mark a given task (todo, deadline, event) as completed.

Format: `done INDEX` where index is the index of the task displayed by DoLah.

Example:
```
done 2
```

Expected Outcome:
```$xslt
Nice! I've marked this task as done:
    [D][✓] submit ip (by: Sep 16 2020)
```

### 6. `delete` - Delete a task

Delete a given task by its index.

Format: `delete INDEX` where index is the index of the task displayed by DoLah.

Example:
```$xslt
delete 2
```

Expected Outcome:
```$xslt
Noted. I've removed this task:
    [D][✓] submit ip (by: Sep 16 2020)
Now you have 4 tasks in the list.
```

### 7. `find` - Find a task

Find a task according to a search keyword. The find function supports fuzzy search.

Format: `find SEARCH_KEYWORD`

Example:
```$xslt
find lexture
```

Expected Outcome:
```$xslt
Here are the matching tasks in your list:
1. [E][✗] go for CS2103 lecture (on: Sep 15 2020)
```

### 8. `bye` - Exit the application

Exit the application. A popup will be shown to ask if you want to 
quit with the progress saved.

Format: `bye`

Example:
```$xslt
bye
```

Expected Outcome: Application exits.


## Author

Zhou Zijian

