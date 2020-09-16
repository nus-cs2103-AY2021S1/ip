# User Guide
Welcome to Duke, your loyal personal assistant chatbot!  
Duke provides a fast and convenient way to keep track of all your tasks, events and deadlines.

## Features 

#### Viewing help: `assist` 

#### Adding a ToDo: `todo` 

#### Adding a Deadline: `deadline` 

#### Adding an Event: `event` 

#### Marking tasks as done: `conquer` 

#### Deleting a task: `delete` 

#### Finding tasks with a search term: `find` 

#### Viewing the schedule for a day: `schedule` 

#### Exiting: `dismiss` 

## Usage

#### Viewing help: `assist`
Shows a list of available commands. 

Format: `assist`

Example of usage: `assist`

Expected outcome:
>Greetings, Your Majesty.
>Use any of these commands to access my quality services:
>1. todo [TASK]: Adds a todo to your scroll
>2. deadline [TASK] /by [DATE AND/OR TIME]: Adds a deadline to your scroll
>3. event [TASK] /on [DATE AND/OR TIME]: Adds an event to your scroll
>4. scroll: Displays your scroll - your list of tasks
>5. conquer [NUMBER]: Marks the particular item on your scroll as DONE
>6. delete [NUMBER]: Deletes the particular item from your scroll
>7. find [KEYWORD]: Returns a list of relevant items on your scroll
>8. schedule [DATE]: Returns a list of items on this date
>9. dismiss: This will be my cue to leave.  
>
>Now, how may I serve you?

#### Adding a ToDo: `todo`
Adds a todo task to your list.

Format: `todo TASK`

Example usage: `todo read book`

Expected outcome:
>Your Majesty, I've added the writing:  
>[T] [✘] read book  
>You have 1 writing(s) on your scroll as of now.

#### Adding a Deadline: `deadline`
Adds a deadline task to your list.  

Format:`deadline TASK /by DATE [TIME]`  
* `DATE` specified must be in YYYY-MM-DD format.
* `TIME` input field is optional, but if specified must be done in HH:MM format.  

Example usage: `deadline project /by 2020-09-18 23:59`  

Expected outcome:
>Your Majesty, I've added the writing:  
>[D] [✘] project (by: Sep 18 2020, 11.59pm)  
>You have 2 writing(s) on your scroll as of now.  

#### Adding an Event: `event`  
Adds an event task to your list.  

Format:`event TASK /on DATE [TIME]`  
* `DATE` specified must be in YYYY-MM-DD format.
* `TIME` input field is optional, but if specified must be done in HH:MM format.  

Example usage: `event workshop /on 2020-09-23 19:30`  

Expected outcome:
>Your Majesty, I've added the writing:  
>[E] [✘] workshop (on: Sep 23 2020, 7.30pm)  
>You have 3 writing(s) on your scroll as of now.   

#### Marking tasks as done: `conquer`
Marks a desired task as done.  

Format: `conquer INDEX`  
* `INDEX` refers to the index of the task on the task list. The index must be a positive integer 1, 2, 3... 

Example usage: `conquer 1` 

Expected outcome:
>As you wish, Your Majesty. I have marked this as conquered.  
>[T] [✓] read book 

#### Deleting tasks: `delete`
Deletes a desired task from your list.    

Format: `delete INDEX`  
* `INDEX` refers to the index of the task on the task list. The index must be a positive integer 1, 2, 3... 

Example usage: `delete 1`

Expected outcome:
>As you wish, Your Majesty. I have removed this writing.  
>[T] [✓] read book   
>You have 2 writing(s) on your scroll as of now.  

#### Finding tasks with a search term: `find`  
Returns a list of tasks related to the desired search term.   

Format: `find SEARCH_TERM`  
* If `SEARCH_TERM` given is empty, the full list of tasks will be returned.

Example usage: `find food`

Expected outcome: 
>These are the relevant writings, Your Majesty:  
>1. [T] [✘] buy cat food  
>2. [E] [✘] foodie gathering (on: Sep 30 2020, 6.00pm)  

#### Viewing the schedule for a day: `schedule`  
Returns an unordered list of tasks on the desired day.   
Format: `schedule DATE`  
* `DATE` specified must be in YYYY-MM-DD format.

Example usage: `schedule 2020-09-18`

Expected outcome: 
>Here are the writings you requested, Your Majesty:  
>1. [D] [✘] iP (by: Sept 18 2020, 11:59pm)  
>2. [D] [✘] assignment 1 (by: Sep 18 2020, 11:59pm)  


#### Exiting: `dismiss`
Exits the application.  

Format: `dismiss`

Example usage: `dismiss`

Expected outcome: 
> Your wish is my command, Your Majesty. Till I see you again.




