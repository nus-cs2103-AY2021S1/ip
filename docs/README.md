# Duke User Guide
![Ui](Ui.png)

## Features
1. `help` command
2. `todo` command
3. `deadline` command
4. `event` command
5. `delete` command
6. `find` command
7. `list` command
8. `bye` command

## Feature Breakdown
### `Help`
Brings up the list of commands available
and their respective descriptions.
> Example usage:
> 
>help
### `Todo`
Adds a task to be done into the task
list.

Format: **/todo [task]**
> Example usage:
>
> todo borrow book
### `Deadline`
Adds a task with a deadline to be done
into the task list.

Format: **/deadline [task] /by [dd/mm/yyyy] [24h-time]**
> Example usage:
>
> deadline assignment /by 08/09/2020 2359
### `Event`
Adds an event task into the task list.

Format: **/event [event] /at [dd/mm/yyyy] [24h-time]**
> Example usage:
>
> event attend wedding /at 01/02/2021 1800
### `Delete`
Deletes a specified task from the task list.

Format: **/delete [task number]**
> Example usage:
>
> delete 3
### `Find`
Finds a specific task from the task list
based on a keyword.

Format: **/find [keyword]**
> Example usage:
>
> find book
### `List`
Lists out the tasks currently in the task list.
> Example usage:
>
> list
### `Bye`
Saves the current task list to the hard disk
and exits the bot.
> Example usage:
>
> bye
