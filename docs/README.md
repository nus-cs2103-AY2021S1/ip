# User Guide
Duke bot is an individual project done for 
CS2103T Software Engineering module. 

Duke Bot is an interactive 
chat-bot that helps users to keep track of their tasks. 

Main types of tasks that can be recorded are:
1. Todos (no deadline/ timing stated)
2. Deadlines (e.g. by 10th September 2020 08:00AM)
3. Events (e.g. at 18th September 2020 08:00AM)
4. Fixed Duration Tasks (e.g. 2 hours)

With reference to the screenshot:
* User: SpongeBob
* Bot: Patrick 

## Features 
The current features available are:

Feature | Purpose
------------ | -------------
`todo <task name>` | adds a todo task  
`deadline <task name> /by <deadline>` | adds a deadline task
`event <task name> /at <event time>` | adds an event task
`fixed <task name> /for <duration>` | adds a fixed-duration task  
`list`| shows the current tasks in the list
`done <task number>` | marks a specific task as done
`delete <task number>` | removes a specific task
`find <keyword>` | shows a task that contains a given keyword
`bye` | terminates the bot

-----------------------------

### Feature Details

#### 1. `todo`
This command adds a todo task into the list.
#### Usage
#### `todo <task name>`
A todo task with the given task name will be added.
The number of tasks will be updated, and indicated as a summary.

Example of usage: 

`todo read book`

Expected outcome:

```
Got it. I've added this task:

[T][✘] read book

Now you have 1 task in the list.
```
#### 2. `deadline`
This command adds a deadline task into the list
#### Usage
#### `deadline <task name> /by <deadline>`
A deadline task with the given task name and deadline will be added.

`/by` acts as an indicator for deadline task. 
Hence, users need to include the keyword `by` for the bot to detect that it is a deadline task.

The number of tasks will be updated, and indicated as a summary.


Example of usage: 

`deadline finish project task /by 18/10/2020 23:59`

Expected outcome:

```
Got it. I've added this task:

[D][✘] finish project task (by: 18-Oct-2020 11:59PM)

Now you have 2 tasks in the list.
```

#### 3. `event`
This command adds an event task into the list
#### Usage
#### `event <task name> /at <event time>`
An event task with the given task name and event time will be added.

`/at` acts as an indicator for event task. 
Hence, users need to include the keyword `at` for the bot to detect that it is an event task.


The number of tasks will be updated, and indicated as a summary.

Example of usage: 

`event attend meeting /at 18/10/2020 20:00`

Expected outcome:

```
Got it. I've added this task:

[E][✘] attend meeting (at: 18-Oct-2020 08:00PM)

Now you have 3 tasks in the list.
```
#### 4. `fixed`
This command adds a fixed duration task into the list
#### Usage
#### `fixed <task name> /for <duration>`
A fixed duration task with the given task name and duration will be added.

`/for` acts as an indicator for fixed duration task. 
Hence, users need to include the keyword `for` for the bot to detect that it is a fixed duration task.


The number of tasks will be updated, and indicated as a summary.

Example of usage: 

`fixed do homework /for 2 hours`

Expected outcome:

```
Got it. I've added this task:

[F][✘] do homework (needs: 2 hours)

Now you have 4 tasks in the list.
```

#### 5. `list`
This command shows the tasks in the tasklist.
#### Usage
#### `list`

Example of usage: 

`list`


Expected outcome:

```
Here are the tasks in your list:
1.[T][✘] read book
2.[D][✘] finish project task (by: 18-Oct-2020 11:59PM)
3.[E][✘] attend meeting (at: 18-Oct-2020 08:00PM)
4.[F][✘] do homework (needs: 2 hours)

Now you have 4 tasks in the list.
```
#### 6. `done`
This command marks a particular task as done.
#### Usage
#### `done <task number>`
The task with a particular task number will be marked as done.

The icon of the task will change from ✘ to ✓.

Example of usage: 

`done 1`


Expected outcome:

```
Nice! I've marked this task as done:
[T][✓] read book
```

#### 7. `delete`
This command deletes a particular task.
#### Usage
#### `delete <task number>`
The task with a particular task number will be deleted.

Example of usage: 

`delete 1`


Expected outcome:

```
Noted. I've removed this task:
[T][✓] read book
```
#### 8. `find`
This command finds tasks in the tasklist that contains a particular keyword.
#### Usage
#### `find <keyword>`
Example of usage: 

`find do`

Expected outcome:

```
Here are the matching tasks in your list:
1.[F][✘] do homework (needs: 2 hours)
2.[D][✘] do project task (by: 18-Oct-2020 11:59PM)
```

#### 9. `bye`
This command terminates the bot.
#### Usage
#### `bye`
Example of usage: 

`bye`

Expected outcome:

*System exits*