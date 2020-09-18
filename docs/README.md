# User Guide

## Features 
- Adding tasks: todo, deadline, event
- View tasks added: list
- Find tasks: find
- Finish tasks: done
- Remove tasks: delete
- Exit application: bye

Notes about the command format:
- words in `UPPER_CASE` are the parameters supplied by the user
- items in square brackets `[]` are optional

##Usage

### Feature 1 
Adding tasks to the task list

#### `todo` - Adds a todo task to the task list

Format: `todo TASK_DESCRIPTION`

Examples of correct usage: 

- todo return book

Examples of wrong usage: 

- todo

#### `deadline` - Adds a deadline task to the task list

Format: `deadline TASK_DESCRIPTION /by YYYY-MM-DD`

Examples of correct usage: 

- deadline return book /by 2020-01-01

Examples of wrong usage: 

- deadline return book by 2020-01-01

- deadline return book /by 2020/01/01

- deadline return book

- deadline

#### `event` - Adds a event task to the task list

Format: `event TASK_DESCRIPTION /at YYYY-MM-DD`

Examples of correct usage: 

- event read book /at 2020-01-01

Examples of wrong usage: 

- event read book at 2020-01-01

- event read book /at 2020/01/01

- event read book

- event

### Feature 2
View tasks in the list

#### `list` - Displays all the added tasks in a list


### Feature 3
Find tasks from the list

#### `find` - Finds all the tasks that contains a keyword

Format: `find KEYWORD`

Examples of correct usage: 

- find book

Examples of wrong usage: 

- find

### Feature 4
Finish tasks in the list

#### `done` - Marks tasks as done

Format: `done TASK_INDEX [TASK_INDEX] ...` (multiple task indexes should be separated by a single space)

Examples of correct usage: 

- done 1
- done 1 2 3

Examples of wrong usage: 

- done

### Feature 5
Remove tasks from the list

#### `delete` - Deletes tasks from the list

Format: `delete TASK_INDEX [TASK_INDEX] ...` (multiple task indexes should be separated by a single space)

Examples of correct usage: 

- delete 1
- delete 1 2 3

Examples of wrong usage: 

- delete

### Feature 6
Exit the application

#### `bye` - Closes the application