# User Guide

##### Like cats? Like anime? Need someone to help you manage your busy life?
##### Neko-chan is the perfect companion for you.

![Screenshot](./Ui.png)

## Features 

- [Add Tasks - Todos, Deadlines & Events](#add-tasks)
- [Delete Tasks](#delete-tasks)
- [View Tasks](#view-tasks)
- [Mark Task As Completed](#mark-task-as-completed)
- [Search](#search)
- [Undo/Redo](#undoredo)

### Add Tasks
Neko-chan can help you to manage 3 different types of tasks:
* Todos
* Deadlines
* Events

#### Todos
Todos are the most basic form of tasks with only a description and a completion status.

How to use: `todo [description]`

Example: `todo Pack school bag` - Creates a todo called Pack school bag.

Expected outcome:
```
Hai hai~ Neko-chan has added this task:
[T][✘] Pack school bag
Neko-chan now knows 1 thing.
```

#### Deadlines
Deadlines are tasks with a due date in addition to the basic properties.

How to use: `deadline [description] by [due date]` 

_View the section on date and time formats for more info on accepted date and time formats._

Example: `deadline Submit homework by 1 Jun 2020`

Expected outcome:
```
Hai hai~ Neko-chan has added this task:
[D][✘] Submit homework (by: 1 Jun 2020)
Neko-chan now knows 2 things.
```

#### Events
Events are tasks with a starting and ending date in addition to the basic properties.

How to use: `event [description] from [start date] to [end date]` 
or `event [description] from [start date] for [duration]` 
or `event [description] from [start date] for all day`

Example: `event Lunch with friends from 23 Jun 2020 12:00 to 23 Jun 2020 13:00` - Creates an event called 
Lunch with friends that starts on 23 Jun 2020 at 12pm to 1pm.

Expected outcome:
```
Hai hai~ Neko-chan has added this task:
[E][✘] Lunch with friends (from 23 Jun 2020 12:00 to 23 Jun 2020 13:00)
Neko-chan now knows 3 things.
```

Example: `event Neko-chan meet and greet from 23 Jun 2020 18:00 for 1h` - Creates an event called 
Neko-chan meet and greet that starts on 23 Jun 2020 at 12pm and lasts for 1 hour.

_View the section on duration formats for more info on accepted duration formats._

Expected outcome:
```
Hai hai~ Neko-chan has added this task:
[E][✘] Neko-chan meet and greet (from 23 Jun 2020 18:00 to 23 Jun 2020 19:00)
Neko-chan now knows 4 things.
```

Example: `event Visit USS from 10 Jun 2020 for all day` - Creates an event called 
Visit USS that occurs on 23 Jun 2020 and is an all day event.

Expected outcome:
```
Hai hai~ Neko-chan has added this task:
[E][✘] Visit USS (on 10 Jun 2020 for all day)
Neko-chan now knows 5 things.
```

### Delete Tasks
Neko-chan can help you to delete one or all of your tasks if the list is getting too long.

How to use: `delete [task number]` or `delete all`

Example: `delete 3` - Deletes your third task.

Expected outcome:
```
Hai! Neko-chan has removed this task:
[E][✘] Lunch with friends (from 23 Jun 2020 12:00 to 23 Jun 2020 13:00)
Neko-chan now knows 4 things.
```

Example: `delete all` - Deletes all your tasks.

Expected outcome:
```
Neko-chan has cleared all your tasks.
You sure are efficient!
```

### View Tasks
Neko-chan can help you view all your tasks at once. 

How to use: `list`

Example: `list` - Lists all your tasks.

Expected outcome:
```
Here's everything Neko-chan knows:
1. [T][✘] Pack school bag
2. [D][✘] Submit homework (by: 1 Jun 2020)
3. [E][✘] Lunch with friends (from 23 Jun 2020 12:00 to 23 Jun 2020 13:00)
4. [E][✘] Neko-chan meet and greet (from 23 Jun 2020 18:00 to 23 Jun 2020 19:00)
5. [E][✘] Visit USS (on 10 Jun 2020 for all day)
```

### Mark Task As Completed
Neko-chan can help you keep track of which tasks you have completed or not.

How to use: `complete [task number]`

Example: `complete 1` - Marks the first task in your list as complete.

Expected outcome: 
```
Yay! Neko-chan has marked this task as complete:
[T][✘] Pack school bag
```

### Search
Need to find a task by a particular keyword or date? Neko-chan can help you with that as well!

How to use: `search [keyword]` or `search [date]`

Example: `search homework` - Searches for tasks in your list with the word homework.

Expected outcome:
```
Here's everything Neko-chan found:
[D][✘] Submit homework (by: 1 Jun 2020)
```

Example: `search 23 Jun 2020` - Searches for deadlines or events that occur on 1 Jun 2020.

Expected outcome:
```
Here's everything Neko-chan found:
[E][✘] Lunch with friends (from 23 Jun 2020 12:00 to 23 Jun 2020 13:00)
[E][✘] Neko-chan meet and greet (from 23 Jun 2020 18:00 to 23 Jun 2020 19:00)
```

### Undo/Redo
Neko-chan can help you to undo or redo your actions if you find yourself making a mistake.

How to use: `undo` or `redo`

Example: `undo` - Undoes the previous action

Expected outcome:
```
You're lucky Neko-chan can reverse the time.
```

Example: `redo` - Redoes the most recently undone action

Expected outcome:
```
So this is how the future looks like...
```

## Uninstallation

To uninstall Neko-chan, simply delete the `Neko.jar` file and remove the `nekochan` folder in your home directory.

## Accepted Date & Time Formats
* `DD MMM YYYY` (E.g 1 Sep 2020)
* `D/M/YYYY` (E.g 1/9/2020)
* `D-M-YYYY` (E.g 1-9-2020)
* `D MMM YYYY HH:mm` (E.g 1 Sep 2020 13:00)
* `D/M/YYYY HH:mm` (E.g 1/9/2020 13:00)
* `D-M-YYYY HH:mm` (E.g 1-9-2020 13:00)

## Accepted Duration Formats
* `X Days Y Hours Z Minutes` (E.g 1 Days 2 Hours 1 Minutes)
* `Xd Yh Zm` (E.g 1d 2h 1m)
* `Xday Yhr Xmin` (E.g. 1day 2hr 1min)

_Where X is the number of days, Y is the number of hours and Z is the number of minutes. All values are optional, i.e
you may omit the number of days, hours or minutes, and they are assumed to be 0._

## Advanced Features

### Text UI Mode

If for any reason, you prefer to use Neko-chan in the command line environment, you can do so by executing the following
`java -jar Neko.jar -mode cli`.

_Neko-chan might be a little sad you don't want to see her though._
