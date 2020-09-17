# User Guide
This is a project template for a greenfield Java project. It's named after the Java mascot Duke. Given below are instructions on how to use it.

## Setting up in IntelliJ
Prerequisites: JDK 11, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File`> `Close Project` to close the existing project dialog first).
2. Set up the correct JDK version, as follows:
    1. Click `Configure` > `Structure for New Projects` and then `Project Settings` > `Project` > `Project SDK`.
    2. If JDK 11 is listed in the drop down, select it. If it is not, click `New...` and select the directory where you installed JDK 11.
    3. Click `OK`.
3. Import the project into Intellij as follows:
    1. Click `Open` or `Import`.
    2. Select the project directory, and click `OK`.
    3. If there are any further prompts, accept the defaults.
4. After the importing is complete, locate the `src/main/java/Launcher.java` file, right-click it, and choose `Run Launcher.main()`. 
    * If errors arise due to missing packages or dependencies, please install them before running Duke.
5. If the setup is correct, a GUI window should appear and you should see something like the below:
<br/> ![ScreenShot](https://github.com/yeohhq/ip/blob/branch-A-UserGuide/src/main/resources/images/Default.png?raw=true)


## Types of Tasks
1. **Todo**: Simple Todo task.
2. **Deadline**:  Deadline task that needs to be completed by a specified date.
3. **Event**: Event task specified to be held on a certain date/location.

## Command List and Format
To view the full list of commands, enter any input that is not identified below.

| Command    | Format                                      |
|------------|---------------------------------------------|
| `list`     | `list`                                      |
| `todo`     | `todo` {description}                        |
| `event`    | `event` {description} `/at` {location/date} |
| `deadline` | `deadline` {description} `/by` {YYYY-MM-DD} |
| `done`     | `done` {integer task number}                |
| `delete`   | `delete` {integer task number}              |
| `find`     | `find` {keyword to find}                    |
| `tag`      | `tag` {integer task number} {tag word}      |

## Usage

### `list` - to show your current list of tasks

Upon input command `list`, Duke will list your current list of tasks as stored in the `duke.txt` file stored on your local repository.

Example of usage: 

`list`

Expected outcome:

>Here are the tasks in your list:
<br/>[D][✓] [HW] homework (by: Aug 24 2020)
<br/>[D][✓] [HW] homework 2 (by: Aug 12 2020)
<br/>[E][✓] birthday party (at: home)
<br/>[D][✓] [HW] homework 3 (by: Sep 01 2020)
<br/>[T][✓] [PROJECT] group project

### `todo` - to add a Todo task

Upon input command `todo`, Duke will add a new Todo task to your current list of tasks.

Example of usage: 

`todo` homework

Expected outcome:

>Got it. I've added this task:
<br/>[T][✗] homework
<br/>Now you have 1 tasks in the list.

### `event` - to add an Event task

Upon input command `event`, Duke will add a new Event task to your current list of tasks.

Example of usage: 

`event` birthday party `/at` home

Expected outcome:

>Got it. I've added this task:
<br/>[E][✗] birthday party (at: home)
<br/>Now you have 2 tasks in the list.

### `deadline` - to add a Deadline task

Upon input command `deadline`, Duke will add a new Deadline task to your current list of tasks.

Example of usage: 

`deadline` homework `/by` 2020-08-24
* Note: The date after `/by` has to be given in YYYY-MM-DD format.

Expected outcome:

>Got it. I've added this task:
<br/>[D][✗] homework (by: Aug 24 2020)
<br/>Now you have 3 tasks in the list.

### `done` - to mark a task as done

Upon input command `done`, Duke will mark the given task from your current list of tasks as done .

Example of usage: 

`done` 1
* Note: The integer value must be separated by a whitespace from the `done` command.

Expected outcome:

>Nice! I've marked this task as done:
<br/>[T][✓] homework

### `delete` - to delete a task

Upon input command `delete`, Duke will delete the given task from your current list of tasks.

Example of usage: 

`delete` 1
* Note: The integer value must be separated by a whitespace from the `delete` command.

Expected outcome:

>Noted. I've removed this task:
<br/>[T][✓] homework
<br/>Now you have 2 tasks in the list.

### `find` - to find a task with a given keyword

Upon input command `find`, Duke will find all tasks from your current list of tasks that matches the given keyword.

Example of usage: 

`find` homework
* Note: The keyword must be separated by a whitespace from the `find` command.

Expected outcome:

>Here are the matching tasks in your list:
<br/>[D][✗] homework (by: Aug 24 2020)

### `tag` - to tag a task with a given tag word

Upon input command `tag`, Duke will tag a given task from your current list of tasks with the given tag word.

Example of usage: 

`tag` 2 HW
* Note: 
    * The task number must be separated by a whitespace from the `tag` command.
    * The tag word (eg. 'HW') must also be separated by a whitespace from the task number.

Expected outcome:

>Got it, I've tagged this task for you:
<br/>[D][✗][HW] homework (by: Aug 24 2020)
