# Luigi Task Manager User Guide


![](https://media.giphy.com/media/Dfk5q64stLdRu/giphy.gif)

This Project is a Mario Themed Task Manager that allows you to schedules tasks to do!

___
### Features of Luigi Task Manager

![](https://media.giphy.com/media/tmMhRhvcytb53xPYXt/giphy.gif)

Here are the following features of the manager:

**1. Adding Tasks to a list**

**2. Display the list**

**3. Marking Tasks as completed**

**4. Deleting Tasks from a List**

**5. Search for certain tasks based on key words**

**6. Reschedule Tasks if edits are needed**

**7. Exiting the program**

**8. Storing of tasks**

___
### List of details about how to use the features of the Manager

The following list contains details on what each
command does as well as examples of how to use it:



1 Adding Tasks
======

Users can add 3 Types of Tasks to the List.

- ToDo

- Deadline

- Event

 # **Command Input**

  - **Todo:** todo \<detail of task\> *OR* T \<detail of task\>
  
  Adds a Todo task that only requires details of the task.
  
  *Examples of input:*
  
  ```
  todo feed the dog      ([T][X] feed the dog)
  
  T pet the cat          ([T][X] pet the cat)
  ```
  
  *Examples of output:*
  
  ```
  Got it. I've added this task:
  [T][X] feed the dog
  Now you have 1 task in your list
  ```
  
  - **Deadline:** deadline \<detail of task\> /by \<date & time\> *OR* DL \<detail of task\> /by \<date & time\>
  
  Adds a Deadline task that requires the details of the task and the date by which it needs to be completedby.
  
  *Examples of input:*
  
  ```
  deadline feed the dog /by 12/09/2020 1100      ([D][X] feed the dog (by: 12 Sep 2020, 11:00))
  
  DL pet the cat /by 12/09/2020 23:00            ([D][X] pet the cat (by: 12 Sep 2020, 23:00))
  ```
  
   *Examples of output:*
  
  ```
  Got it. I've added this task:
  [D][X] feed the dog (by: 12 Sep 2020, 11:00)
  Now you have 2 tasks in your list
  ```
  
  - **Event:** event \<detail of task\> /at \<date & time\> *OR* E \<detail of task\> /at \<date & time\>
  
  Adds a Event task that requires the details of the task and the date of when the event starts.
  
  *Examples of input:*
  
  ```
  event dog birthday party /at 12/09/2020 1100      ([E][X] dog birthday party (at: 12 Sep 2020, 11:00))
  
  E belly rubbing ceremony /at 12/09/2020 23:00     ([E][X] belly rubbing ceremony (at: 12 Sep 2020, 23:00))
  ```
  
*Examples of output:*
  
  ```
  Got it. I've added this task:
  [E][X] dog birthday party (at: 12 Sep 2020, 11:00)
  Now you have 3 tasks in your list
  ```

2 Display The List
======

# **Command Input**

  - **List:** list **OR** L
  
  Will display all tasks added in the list.
  
  
  *Examples of input:*
  
  ```
 list
 
 L
  ```
  
  *Example of output:*
  
  ```
 Here are the tasks in your list:
 1. [T][X] feed the dog
 2. [E][X] dog birthday party (at: 12 Sep 2020, 11:00)
 3. [D][X] pet the cat (by: 12 Sep 2020, 23:00)
  ```
  
  

3 Marking Completed Tasks
======

# **Command Input**

  - **Done:** done \<number of the task in the list to mark completed\> *OR* D \<number of the task in the list to mark completed\>
  
  Will mark a task as completed based on the number in the list spcified.
  
  *Example of your list:*
  
  ```
 Here are the tasks in your list:
 1. [T][X] feed the dog
 2. [E][X] dog birthday party (at: 12 Sep 2020, 11:00)
 3. [D][X] pet the cat (by: 12 Sep 2020, 23:00)
  ```
  
  *Examples of input:*
  
  ```
 done 2
 
 D 2
  ```
  
 *Examples of output:*
 
 ```
 Nice! I've marked this task as done:
 [E][✓] dog birthday party (at: 12 Sep 2020, 11:00)
 ```

4 Deleting Tasks from the List
======

# **Command Input**

  - **Delete:** delete \<number of the task in the list to mark completed\> *OR* DEL \<number of the task in the list to mark completed\>
  
  Will delete a task from the list based on the number in the list spcified.
  
  *Example of your list:*
  
  ```
 Here are the tasks in your list:
 1. [T][X] feed the dog
 2. [E][✓] dog birthday party (at: 12 Sep 2020, 11:00)
 3. [D][X] pet the cat (by: 12 Sep 2020, 23:00)
  ```
  
  *Examples of input:*
  
  ```
 delete 2
 
 DEL 2
  ```
  
 *Examples of output:*
 
 ```
 Noted. I've removed this task:
 [E][✓] dog birthday party (at: 12 Sep 2020, 11:00)
 Now you have 2 tasks in your list
 ```
 *When command List is called again, the list will look like this:*
 
  ```
 Here are the tasks in your list:
 1. [T][X] feed the dog
 2. [D][X] pet the cat (by: 12 Sep 2020, 23:00)
  ```
 5 Search for certain tasks based on key words
======

# **Command Input**

  - **Find:** find \<keyword\> *OR* F \<keyword\>
  
  Will display a separate list of all tasks that contain the keyword.
  
  *Example of your list:*
  
  ```
 Here are the tasks in your list:
 1. [T][X] feed the dog
 2. [E][X] dog birthday party (at: 12 Sep 2020, 11:00)
 3. [D][X] pet the cat (by: 12 Sep 2020, 23:00)
  ```
  
  *Examples of input:*
  
  ```
 find dog
 
 F dog
  ```
  
 *Examples of output:*
 
 ```
 Here are the matching tasks in your list:
 1. [T][X] feed the dog
 2. [E][X] dog birthday party (at: 12 Sep 2020, 23:00)
 ```
 
 6 Reschedule Tasks
======

# **Command Input**

  - **Reschedule:** reschedule \<number of the task in the list to change\> /to \<date to change to\> 
  
  *OR* R \<number of the task in the list to change\> /to \<date to change to\>
  
  Will change the date and time of Event and Deadline tasks to the date specified vased on the number order in the list.
  An error will show if user tries to reschedule a Todo Task.
  
  *Example of your list:*
  
  ```
 Here are the tasks in your list:
 1. [T][X] feed the dog
 2. [E][X] dog birthday party (at: 12 Sep 2020, 11:00)
 3. [D][X] pet the cat (by: 12 Sep 2020, 23:00)
  ```
  
  *Examples of input:*
  
  ```
 reschedule 2 /to 10/11/2023 1500 
 
 R 2 /to 10/11/2023 1500 
  ```
  
 *Example of output:*
 
 ```
 Noted. I have now reschedule [E][X] dog birthday party (at: 12 Sep 2020, 11:00) to:
 [E][X] dog birthday party (at: 10 Nov 2023, 15:00)
 ```
 
 7 Exiting the program
======

# **Command Input**

  - **Exit:** bye **OR** B.
  
  Will display a farewell message before exiting the program.
 
  
  *Examples of input:*
  
  ```
 bye
 
 B
  ```
  
 *Example of output:*
 
 ```
 Bye. Hope to see you again soon!
 ```
 
 8 Storing of Tasks
======
 
 Upon exiting the program, all tasks in the list will be saved onto a file called list.duke.
 If the file does not exist, anew file will be created in the hard drive of the pc.
 
 
GoodBye
======

 ![](https://media.giphy.com/media/l3V0csAeJ2Q9rvrkA/giphy.gif)
 
# Luigi Task Manager User Guide


![](https://media.giphy.com/media/Dfk5q64stLdRu/giphy.gif)

This Project is a Mario Themed Task Manager that allows you to schedules tasks to do!

___
### Features of Luigi Task Manager

Here are the following features of the manager:

**1. Adding Tasks to a list**

**2. Display the list**

**3. Marking Tasks as completed**

**4. Deleting Tasks from a List**

**5. Search for certain tasks based on key words**

**6. Reschedule Tasks if edits are needed**

**7. Exiting the program**

**8. Storing of tasks**

___
### List of details about how to use the features of the Manager

The following list contains details on what each
command does as well as examples of how to use it:



1 Adding Tasks
======

Users can add 3 Types of Tasks to the List.

- ToDo

- Deadline

- Event

 # **Command Input**

  - **Todo:** todo \<detail of task\> *OR* T \<detail of task\>
  
  Adds a Todo task that only requires details of the task.
  
  *Examples of input:*
  
  ```
  todo feed the dog      ([T][X] feed the dog)
  
  T pet the cat          ([T][X] pet the cat)
  ```
  
  *Examples of output:*
  
  ```
  Got it. I've added this task:
  [T][X] feed the dog
  Now you have 1 task in your list
  ```
  
  - **Deadline:** deadline \<detail of task\> /by \<date & time\> *OR* DL \<detail of task\> /by \<date & time\>
  
  Adds a Deadline task that requires the details of the task and the date by which it needs to be completedby.
  
  *Examples of input:*
  
  ```
  deadline feed the dog /by 12/09/2020 1100      ([D][X] feed the dog (by: 12 Sep 2020, 11:00))
  
  DL pet the cat /by 12/09/2020 23:00            ([D][X] pet the cat (by: 12 Sep 2020, 23:00))
  ```
  
   *Examples of output:*
  
  ```
  Got it. I've added this task:
  [D][X] feed the dog (by: 12 Sep 2020, 11:00)
  Now you have 2 tasks in your list
  ```
  
  - **Event:** event \<detail of task\> /at \<date & time\> *OR* E \<detail of task\> /at \<date & time\>
  
  Adds a Event task that requires the details of the task and the date of when the event starts.
  
  *Examples of input:*
  
  ```
  event dog birthday party /at 12/09/2020 1100      ([E][X] dog birthday party (at: 12 Sep 2020, 11:00))
  
  E belly rubbing ceremony /at 12/09/2020 23:00     ([E][X] belly rubbing ceremony (at: 12 Sep 2020, 23:00))
  ```
  
*Examples of output:*
  
  ```
  Got it. I've added this task:
  [E][X] dog birthday party (at: 12 Sep 2020, 11:00)
  Now you have 3 tasks in your list
  ```

2 Display The List
======

# **Command Input**

  - **List:** list **OR** L
  
  Will display all tasks added in the list.
  
  
  *Examples of input:*
  
  ```
 list
 
 L
  ```
  
  *Example of output:*
  
  ```
 Here are the tasks in your list:
 1. [T][X] feed the dog
 2. [E][X] dog birthday party (at: 12 Sep 2020, 11:00)
 3. [D][X] pet the cat (by: 12 Sep 2020, 23:00)
  ```
  
  

3 Marking Completed Tasks
======

# **Command Input**

  - **Done:** done \<number of the task in the list to mark completed\> *OR* D \<number of the task in the list to mark completed\>
  
  Will mark a task as completed based on the number in the list spcified.
  
  *Example of your list:*
  
  ```
 Here are the tasks in your list:
 1. [T][X] feed the dog
 2. [E][X] dog birthday party (at: 12 Sep 2020, 11:00)
 3. [D][X] pet the cat (by: 12 Sep 2020, 23:00)
  ```
  
  *Examples of input:*
  
  ```
 done 2
 
 D 2
  ```
  
 *Examples of output:*
 
 ```
 Nice! I've marked this task as done:
 [E][✓] dog birthday party (at: 12 Sep 2020, 11:00)
 ```

4 Deleting Tasks from the List
======

# **Command Input**

  - **Delete:** delete \<number of the task in the list to mark completed\> *OR* DEL \<number of the task in the list to mark completed\>
  
  Will delete a task from the list based on the number in the list spcified.
  
  *Example of your list:*
  
  ```
 Here are the tasks in your list:
 1. [T][X] feed the dog
 2. [E][✓] dog birthday party (at: 12 Sep 2020, 11:00)
 3. [D][X] pet the cat (by: 12 Sep 2020, 23:00)
  ```
  
  *Examples of input:*
  
  ```
 delete 2
 
 DEL 2
  ```
  
 *Examples of output:*
 
 ```
 Noted. I've removed this task:
 [E][✓] dog birthday party (at: 12 Sep 2020, 11:00)
 Now you have 2 tasks in your list
 ```
 *When command List is called again, the list will look like this:*
 
  ```
 Here are the tasks in your list:
 1. [T][X] feed the dog
 2. [D][X] pet the cat (by: 12 Sep 2020, 23:00)
  ```
 5 Search for certain tasks based on key words
======

# **Command Input**

  - **Find:** find \<keyword\> *OR* F \<keyword\>
  
  Will display a separate list of all tasks that contain the keyword.
  
  *Example of your list:*
  
  ```
 Here are the tasks in your list:
 1. [T][X] feed the dog
 2. [E][X] dog birthday party (at: 12 Sep 2020, 11:00)
 3. [D][X] pet the cat (by: 12 Sep 2020, 23:00)
  ```
  
  *Examples of input:*
  
  ```
 find dog
 
 F dog
  ```
  
 *Examples of output:*
 
 ```
 Here are the matching tasks in your list:
 1. [T][X] feed the dog
 2. [E][X] dog birthday party (at: 12 Sep 2020, 23:00)
 ```
 
 6 Reschedule Tasks
======

# **Command Input**

  - **Reschedule:** reschedule \<number of the task in the list to change\> /to \<date to change to\> 
  
  *OR* R \<number of the task in the list to change\> /to \<date to change to\>
  
  Will change the date and time of Event and Deadline tasks to the date specified vased on the number order in the list.
  An error will show if user tries to reschedule a Todo Task.
  
  *Example of your list:*
  
  ```
 Here are the tasks in your list:
 1. [T][X] feed the dog
 2. [E][X] dog birthday party (at: 12 Sep 2020, 11:00)
 3. [D][X] pet the cat (by: 12 Sep 2020, 23:00)
  ```
  
  *Examples of input:*
  
  ```
 reschedule 2 /to 10/11/2023 1500 
 
 R 2 /to 10/11/2023 1500 
  ```
  
 *Example of output:*
 
 ```
 Noted. I have now reschedule [E][X] dog birthday party (at: 12 Sep 2020, 11:00) to:
 [E][X] dog birthday party (at: 10 Nov 2023, 15:00)
 ```
 
 7 Exiting the program
======

# **Command Input**

  - **Exit:** bye **OR** B.
  
  Will display a farewell message before exiting the program.
 
  
  *Examples of input:*
  
  ```
 bye
 
 B
  ```
  
 *Example of output:*
 
 ```
 Bye. Hope to see you again soon!
 ```
 
 8 Storing of Tasks
======
 
 Upon exiting the program, all tasks in the list will be saved onto a file called list.duke.
 If the file does not exist, anew file will be created in the hard drive of the pc.
 
 
GoodBye
======

 ![](https://media.giphy.com/media/l3V0csAeJ2Q9rvrkA/giphy.gif)
 

