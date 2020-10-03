# User Guide
## quick start
1. Install Java 11 on your computer.
2. Download Duke.jar file.
3. Run the file with java -jar Duke.jar.
## Features 
#### `help`
Displays all the available instructions in Duke.

<br/>

#### `list`
Displays all the tasks added in Duke.

<br/>

###`todo DESCRIPTION`
Adds a simple task.

Eg. `todo read book`

<br/>

#### `deadline DESCRIPTION /by DATE(mm-dd-yyyy)`
Adds a task to be done by a date.

Eg. `deadline return book /by 09-30-2020`

<br/>

#### `event DESCRIPTION /at DATE(mm-dd-yyyy)`
Adds a task to be done at a date.

Eg. `event borrow book /at 09-30-2020`

<br/>

#### `done INDEX`
Marks the task at a specific index as done.

Eg. `done 2` marks the second task in the list as done.

<br/>

#### `delete INDEX`
Delete the task at a specific index.

Eg. `delete 2` deletes the second task in the list.

<br/>

#### `find KEYWORD`
Displays all the tasks that contains the key word.

Eg. `find book` displays all the tasks that contains `book`.

<br/>

#### `bye`
Says bye to Duke and exits the app.
## Acknowledgement
JavaFX tutorial @SE-EDU/guides

URL: https://se-education.org/guides/tutorials/javaFx.html
