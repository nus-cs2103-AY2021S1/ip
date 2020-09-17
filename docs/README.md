# User Guide

## Features 

### Feature 1 
Create Todo task to your schedule.

## Usage

### `Todo <description>` 
1. A todo task will be created accordingly.
2. If your description is empty, Duke would warn and ask you to put.
3. The command is case insensitive, Todo, TODO, todo are all fine.
 
Example of usage: 
`Todo My CS2103t homework`

Expected outcome:

```
Got it. I have added this task:
    [T][⤫] My CS2103t homework
Now you have ? tasks in your list.
```

### Feature 2
Create Deadline task to your schedule.

## Usage

### `Deadline <description> /<DATE>` 
1. A deadline task will be created accordingly.
2. If your description is empty, Duke would warn and ask you to put.
3. If your deadline is not specified, Duke would warn and ask you to put.
4. Now Duke supports three DATE format, namely yyyy-mm-dd, yyyy-mm-dd HH:mm, E
5. The command is case insensitive, Deadline, DEADLINE, deadline are all fine.
 
Example of usage: 
`Deadline My CS2103t homework /Wed`

Expected outcome:

```
Got it. I have added this task:
    [D][⤫] My CS2103t homework (The next Wednesday from Now)
Now you have ? tasks in your list.
```

### Feature 3
Create Event task to your schedule.

## Usage

### `Event <description> /<DATE> ~ <DATE>` 
1. An event task will be created accordingly.
2. If your description is empty, Duke would warn and ask you to put.
3. If your duration is not specified, Duke would warn and ask you to put.
4. Now Duke supports three DATE format, namely yyyy-mm-dd, yyyy-mm-dd HH:mm, E
5. The command is case insensitive, Event, EVENT, event are all fine.
 
Example of usage: 
`Event My CS2103t team meeting /2020-09-25 10:00~12:00`

Expected outcome:

```
Got it. I have added this task:
    [E][⤫] My CS2103t team meeting (Sep 25 2020 10:00 ~ Sep 25 2020 12:00)
Now you have ? tasks in your list.
```

### Feature 4
List all the tasks.

## Usage

### `List` 
1. The command is case insensitive, List, LIST, list are all fine.
2. List and ls both work.
3. All the tasks will be listed with an ID in front.
 
Example of usage: 
`ls`

Expected outcome:

```
  1. [E][⤫] My CS2103t team meeting (Sep 25 2020 10:00 ~ Sep 25 2020 12:00)
  2. [D][⤫] My CS2103t team meeting (Sep 26 2020 10:00)
```

### Feature 5
Find specific tasks

## Usage

### `Find <queryKey>` 
1. The command is case insensitive, find, Find, FIND are all fine.
2. All the tasks that has the queryKey will be listed. (include Time)
 
Example of usage: 
`Find Sep 25`

Expected outcome:

```
     [E][⤫] My CS2103t team meeting (Sep 25 2020 10:00 ~ Sep 25 2020 12:00)
     [D][⤫] My CS2103t team meeting (Sep 25 2020 10:00)
```

### Feature 5
Done specific tasks

## Usage

### `Done <taskID>` 
1. The command is case insensitive, done, Done, DONE are all fine.
2. All tasks created will be marked as undone by default.
 
Example of usage: 
`Done 1`

Expected outcome:

```
Tres bien!! I have helped you mark task CS2103 homework1 as Done
  [T][✔️]CS2103 homework1

```

### Feature 6
Show all the available commands

## Usage

### Help 
1. The command is case insensitive, help, Help, HELP are all fine.
 
Example of usage: 
`Help`






