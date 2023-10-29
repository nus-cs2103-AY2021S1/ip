# Duke User Guide

Duke (nicknamed ALIEN) is a schedule tracker that remembers and manages your tasks for you so you do not have to.

## Features 

1. **`todo`**: Adds a To-Do task into your task list

1. **`deadline`**: Adds a Deadline task into your task list

1. **`event`**: Adds an Event task into your task list

1. **`done`**: Marks a task as done

1. **`delete`**: Deletes a task

1. **`list`**: Lists all tasks in your task list

1. **`find`**: Lists all tasks in your task list that matches the keyword

1. **`snooze`**: Snoozes a task

1. **`bye`**: Terminates Duke

## Usage of Features

#### 1. **`todo`**: Adds a To-Do task ino your task list

- This command adds a task of type Todo into your task list
- A Todo task consists of a description

Format: `todo <description>`

Example: `todo CS2103T homework`

Expected outcome:
```
ALIEN: I have added this task!
[T][✘] CS2103T homework
Current list size: 1
```

#### 2. **`deadline`**: Adds a Deadline task ino your task list

- This command adds a task of type Deadline into your task list
- A Deadline task consists of a description and date

Format: `deadline <description> /by <yyyy-mm-dd>`

Example: `deadline CS2103T submission /by 2020-09-18`

Expected outcome:
```
ALIEN: I have added this task!
[D][✘] CS2103T submission - 2020-09-18
Current list size: 2
```

#### 3. **`event`**: Adds an Event task ino your task list

- This command adds a task of type Event into your task list
- An Event task consists of a description and date

Format: `event <description> /at <yyyy-mm-dd>`

Example: `event ALIEN's birthday /at 2021-09-16`

Expected outcome:
```
ALIEN: I have added this task!
[E][✘] ALIEN's birthday - 2021-09-16
Current list size: 3
```

#### 4. **`done`**: Marks a task as done

- This command marks your task as done, indicated by changing [✘] to [✓]
- The index of your task is required

Format: `done <index>`

Example: `done 1`

Expected outcome:
```
ALIEN: I have marked it as done!
[T][✓] CS2103T homework
```

#### 5. **`delete`**: Deletes a task

- This command deletes your task
- The index of your task is required

Format: `delete <index>`

Example: `delete 1`

Expected outcome:
```
ALIEN: I have deleted this task!
[T][✓] CS2103T homework
Current list size: 2
```

#### 6. **`list`**: Lists all tasks in your task list

- This command lists all your task in your task list, if any

Format: `list`

Example: `list`

Expected outcome:
```
ALIEN: Here are your tasks:
1. [D][✘] CS2103T submission - 2020-09-18
2. [E][✘] ALIEN's birthday - 2021-09-16
```

#### 7. **`find`**: Lists all tasks in your task list that matches the keyword

- This command lists all your task in your task list, if any, that matches the keyword
- A keyword is required
- Duke will search for any task that contains your keyword parsed as a String without any formatting

Format: `find <keyword>`

Example: `find day`

Expected outcome:
```
ALIEN: Here are the matching tasks found:
[E][✘] ALIEN's birthday - 2021-09-16
```

#### 8. **`snooze`**: Snoozes a task

- This command snoozes your task by one day
- The index of your task is required

Format: `snooze <index>`

Example: `snooze 1`

Expected outcome:
```
ALIEN: I have snoozes this task!
[D][✘] CS2103T submission - 2020-09-19
```

#### 9. **`bye`**: Terminates Duke

- This command terminates the Duke program

Format: `bye`
