
<h1>Duke user guide</h1>

<h3>UI of Duke</h3>
<h4>Duke is a command line task manager.</h4>

Features include:
1. Add tasks
2. List tasks
3. Delete tasks
4. Marking as done
5. Sort tasks by date
6. Find tasks
7. Exit programm

## Usage
<h2>1. Add tasks:</h2>
<h5> Add tasks like ToDo, Event and Deadline</h5>

* todo Description
Input:
    
```
todo cool todo
```

Expected outcome:
    
```
Adding Too to the list:
[T][✘] cool todo 
You have 1 tasks in the list.
```
* event Description/Date(yyyy-mm-dd)
Input:    
```
event cool event/2020-10-09
```

Expected outcome:
```
Adding Event to the list:
[E][✘] cool event(At: Oct 9 2020)
You have 2 tasks in the list.
```
    
* deadline Description/Date
Input:
    
```
deadline cool deadline/2020-10-09
```

Expected outcome:    
```
Adding Deadline to the list:
[D][✘] cool deadline(By: Oct 9 2020)
You have 3 tasks in the list.
```

<h2>2. List tasks</h2>
<h5>List all the tasks stored in Duke</h5>
Input:
    
 ```
list
 ```

Expected outcome:
    
```
You have 3 tasks in the list.
Here are the tasks in your list:
1. [T][✘] cool todo 
2. [E][✘] cool event(At: Oct 9 2020)
3. [D][✘] cool deadline(By: Dec 15 2020)
```

<h2>3. Deleting tasks</h2>
<h5>Delete a task to clear space</h5>
Input:

```
delete 3
```

Expected outcome:
```
Task removed: 
[T][✘] cool todo
You have 1 task in the list.
``` 

<h2>4. Marking as done</h2>
<h5>Marks the tasks as done by changing the cross to a tick</h5>
Input:

```
done 1
```

Expected outcome:

```
Nice! I've marked this task as done: 
[E][✓] cool event(At: Oct 9 2020)
```

<h2>5. Sort tasks by date</h2>
<h5>Sorts the tasks into 1) ToDo 2) Deadline 3) Event. Deadline and Events would also be sorted by date</h5>    
Input:

```
sort
```

Expected outcome:
```
Tasks sorted
```

Sample list before sort:

```
Here are the tasks in your list:
1. [E][✘]  Jan event(At: Jan 1 2020)
2. [T][✘]  stuff
3. [D][✘]  Dec deadline(By: Dec 1 2020)
4. [E][✘]  June event(At: Jun 6 2020)
5. [T][✘]  stuff 2
6. [E][✘]  Dec event (At: Dec 1 2020)
7. [T][✘]  stuff 3
8. [D][✘]  2019 deadline(By: Jan 1 2019)
```

Sample list after sort:
```
You have 8 tasks in the list.
Here are the tasks in your list:
1. [T][✘]  stuff
2. [T][✘]  stuff 2
3. [T][✘]  stuff 3
4. [D][✘]  2019 deadline(By: Jan 1 2019)
5. [D][✘]  Dec deadline(By: Dec 1 2020)
6. [E][✘]  Jan event(At: Jan 1 2020)
7. [E][✘]  June event(At: Jun 6 2020)
8. [E][✘]  Dec event (At: Dec 1 2020)
```
<h2>6. Find tasks</h2>
<h5>Search for tasks that match a certain key phrase</h5>

Input:
```
find Dec
```

Expected outcome
```
Here are the items that match
1) [D][✘]  Dec deadline(By: Dec 1 2020)
2) [E][✘]  Dec event (At: Dec 1 2020)
```

<h2>7. Exit programm</h2>
<h5>Quit the programm when finish. Programm will give the closing messgae and exit automatically</h5>

Input:
```
bye
```

Expected outcome:
```
Bye. Hope to see you again soon
```
