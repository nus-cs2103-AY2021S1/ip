# Duke the Singlish Dude

## Features

  - Task manager
  - Add and delete tasks
  - Filter through tasks by description
  - Filter through tasks by tagging
  - Comes with an additional Singlish accent!

## Usage

### list - Lists all the tasks in Duke the Singlish Dude
Format: `list`

### todo - Adds a todo task to the task list
Format: `todo {description} #{tag}`  
*#{tag} is an optional input*

### deadline - Adds a deadline task to the task list
Format: `deadline {description} /by YYYY-MM-DD #{tag}`  
*#{tag} is an optional input*

### event - Adds a event task to the task list
Format: `event {description} /at YYYY-MM-DD #{tag}`  
*#{tag} is an optional input*

### delete - Delete a task stored in task list
Format: `delete {task number}`  
*Ensure that task number is within range*

### list - Reveals all tasks stored in task list
Format: `list`

### find - Filters the task list by keyword (if any)
Format: `find {keyword}`

### findtag - Filters the task list by tag provided (if any)
Format: `findtag #{tag}`

### bye - Ends the session
Format:`bye`

## Examples

Input: `list`  
Example output:  
```
    ____________________________________
    Here are the things you need to do lor: 
    1. [E][✗] Exam (at: 23 Jun 2020)
    2. [D][✗] Individual Project (by: 17 Sep 2020) [#CS2103T]
    ____________________________________
```
    
Input: `todo Math Homework #school`  
Example output:  
```
    ____________________________________
    Okok. I add for you: 
    [T][✗] Math Homework [#school]
    You got 3 tasks in the list.
    ____________________________________
```
    
Input: `deadline Project Work /by 2020-10-10 #school`  
Example output:  
```
    ____________________________________
    Okok. I help you add this task: 
    [D][✗] Project Work (by: 10 Oct 2020) [#school]
    You got 5 tasks in the list.
    ____________________________________
```

Input: `findtag #school`  
Example output:  
```
    ____________________________________
    Here are the things you want lor: 
    1. [T][✗] Math Homework [#school]
    2. [D][✗] Project Work (by: 10 Oct 2020) [#school]
    3. [D][✗] Project Work (by: 10 Oct 2020) [#school]
    ____________________________________
```
    
Input: `done 3`  
Example output:  
```
    ____________________________________
    Swee! Now I will mark this as done: 
    [T][✓] Math Homework [#school]
    ____________________________________
```
    
Input: `delete 4`  
Example output:  
```
    ____________________________________
    Task deleted liao: 
    [D][✗] Project Work (by: 10 Oct 2020) [#school]
    You got 4 tasks left. 
    ____________________________________
```

## Product Snapshot
![Duke the Singlish Dude](https://i.imgur.com/C7dLXhQ.png)

## Contact
***For any enquiries feel free to contact me at e0406711@u.nus.edu***