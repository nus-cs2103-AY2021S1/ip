# User Guide
## Features
* A *Task* can be a *ToDo* or *Event* or *Deadline* 
* The txt file stored on hard disk will be updated after any change to the collection of all *Task*
* Although the program has taken care of some format issue such as preceding white space, following the format specification is necessary to make the command useful. 
* Not understandable command will result in customized error message being shown. 

#### Add 
Fundamental command used for adding new *Task* into the collection.
#### Exit
Fundamental command used for closing the application. Alternatively, user can also close the app by clicking the red cross located at the top right corner. 
#### Delete
Fundamental command used for deleting specific *Task* from the list.
#### List
Fundamental command used for listing down all the *Task*. 
#### Check 
Command used for checking all *Event* or *Deadline* taking place at a specific day.
#### Done
Command used for marking specific *Task* from the list as done.
#### Find
Command used for finding all *Task* that have a particular word inside the name.
#### Remind 
Advanced Command used for listing down all the *Task* due in certain hours.
#### Sort
Advanced Command used for sorting all the *Task* according to the date and time. *ToDo* which does not have a date and time will be displayed lastly. 
#### Update
Advanced Command used for updating a *Deadline* or *Event* with a new date and time. 

## Usage
- **day** should be expressed from the range of 1 to last date of the month, note that no 0 is needed for signle digit day
- **month** should be expressed from the range of 01(January) to 12(December). note that **0** is needed for single digit month
- **year** can be any four digit number, preceding 0s are also counted, however it doesn't make much sense in this case
- **content** can be any text regardless of the white space in between
- **num** can be any numeric value
- note the white space between text 
- note the **num** for Delete, Done and Update represents the index of that specific task in the list
### `Add` 
Example of usage: 
```
todo <content>
deadline <content> /by <day>/<month>/<year> <time>
event <content> /at <day>/<month>/<year> <time>
```
Expected outcome:
```
Understood! I've added this task:
[T][✘] read book
You have 5 tasks in your list now!
```

### `Exit` 
Example of usage: 
```
bye
```
Expected outcome:
```
Welcome back!
```

### `Delete` 
Example of usage: 
```
delete <num>
```
Expected outcome:
```
Undetstood! I've deleted this task:
[T][✘] read book
You have 5 tasks in your list now!
```

### `List` 
Example of usage: 
```
list
```

Expected outcome:
```
Here are the tasks in your list:
 1. [D][✘] submit assignment (by: 21 09 2019 2359)
 2. [E][✘] project meeting (at: 19 09 2019 1300)
 3. [E][✘] training (at: 23 09 2019 1900)
```

### `Check` 
Example of usage: 
```
check <day>/<month>/<year>
```
Expected outcome:
```
Hey! I have printed out the tasks that match the date:
1. [E][✘] mlbb tournament (at: Dec 2 2019 18:00)
```

### `Done` 
```
done <num>
```
Expected outcome:
```
Undetstood! I've marked this task as done:
[T][✓] read book
```
### `Find` 
Example of usage: 
```
find <content>
```
Expected outcome:
```
Hey! I have printed out the tasks that contain the name:
1. [E][✘] mlbb tournament (at: Dec 2 2019 18:00)
```

### `Remind` 
Example of usage: 
```
remind <num>
```
Expected outcome:
```
Please take a look at the tasks that will occcur soon:
1. [E][✘] mlbb tournament (at: Dec 2 2019 18:00)
```

### `Sort` 
Example of usage: 
```
sort
```
Expected outcome:
```
I have sorted the list of tasks! Todo tasks will be display lastly.

Please take a look at the tasks:

1. [E][✘] mlbb tournament (at: Dec 2 2019 18:00)
2. [D][✘] buy books (at: Dec 9 2019 18:00)
3. [T][✘] read book
```

### `Update` 
Example of usage: 
```
update <num> <day>/<month>/<year> <time>
```
Expected outcome:
```
Understood, I've updated this task for you:
[D][✘] buy books (at: Dec 9 2019 18:00)
```

### Reference Chart
**Sample Task** | **Sample Command**
------------ | -------------
Add a *ToDo* | todo read book
Add an *Event* | event return book /at 2/12/2019 1800
Add a *Deadline* | deadline return book /by 2/12/2019 1800
Close the application | bye
Check all *Event* or *Deadline* taking place at a specific day | check 2/12/2019
Delete specific *Task* from the list | delete 1
Mark specific *Task* from the list as done | done 1
Find all *Task* that have a particular word inside the name | find book
List down all the *Task* | list
List down all the *Task* due in certain hours | remind 72
Sort all the *Task* according to the date and time | sort
Update a *Deadline* or *Event* with a new date and time | update 2 2/12/2019 1800


