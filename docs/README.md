# Zelda Bot User Guide
This is simple chat bot application I created for my CS2103 Project. The aesthetics of the bot is inspired by my favourite childhood game: Legend of Zelda.


## Features 

### Feature 1 
Addition of Tasks
The user can add new tasks into ZeldaBot. The tasks can be a :
- deadline
- event
- todo
#### command:
- todo - todo <task details>

#### usage:
- todo deadlift

#### expected outcome:
- Got it. This task is now added.
    [T][X] deadlift
You have 1 tasks left in 
your list!

#### command:
- deadline <task details>/<Date & Time in yyyy-mm-dd format>

#### usage:
- deadline bench 2 plates/2020-12-25

#### expected outcome:
- Got it. This task is now added.
[D][X] bench 2 plates (by: 25 Dec
2020)
You have 2 tasks left in 
your list!

### Feature 2
Complete task
The user can tag a current stored as completed.

#### command:
- Done <task index>

#### usage:
- Done 1

#### expected outcome:
- Nice! I have completed 
this task! [T][X] deadlift

### Feature 3
List all tasks

#### command:
- list

#### usage:
- list

#### expected outcome:
- Here are the tasks in
your tasklist:
<All task displayed>

### Feature 4
Sort all the events and deadlines in chronological order

#### command:
- sort

#### usage:
- sort

#### expected outcome:
- Your Tasks has been sorted! :)
<displays tasks in chronological order>

### Feature 5
View the schedule for a particular month or data

#### command:
- schedule <a particular month>
- schedule <a particular date>

#### usage:
- schedule may
- schedule 2020-12-12

#### expected outcome:
- Here is your schedule for
this Month!
<Displays all tasks for this month> (schedule <month>)
- Here is your schedule for this date
<Displays all tasks for this date> (schedule <date>)

### Feature 6
Update a current task

#### command:
- update <index of task>
- update <desc or date of the selected task>

#### usage:
- update 1
- update desc squat 250kg

#### expected outcome:
- This is the current task:
    <Display current selected task>
    Ok tell me what to update  (update <task index>)
- This task has been
    updated!
    <Display updated task>

