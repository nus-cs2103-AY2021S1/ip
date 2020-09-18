#### Table Of Contents
<a href="#1">1. What is Duke?</a>
<br />
<a href="#2">2. Features</a>
<br />
<a href="#3">3. Getting Started</a>
<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#3.1">3.1 How to install Duke?</a>
<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#3.1">3.2 How to use Duke?</a>
<br />
<a href="#4">4. Contact Us</a>

## <a id="1">1. What is Duke?</a>

Duke is a task management tool which will help the users to manage their tasks. The users can use Duke with the least actions to do the most complicated task management. 

## <a id="#2">2. Features</a>

1. **Add a task**
    * Type of tasks:
    1. `todo`: a task to be done.
    1. `event`: a event that happens at the given time.
    1. `deadline`: a task to be done before the deadline.
    
1. **Check the list of tasks**
   * `list` out the tasks saved in the application.
  
1. **Find the tasks**
   * `find` the tasks that have same keyword in the their task description.
   
1. **Delete the task**
   * `delete` the unwanted task from the list of tasks.
   
1. **Mark the task as done**
   * mark the task as `done` after the user has complete the task.
   
1. **Undo the action**
   * `undo` the wrong action and set the list of tasks back to the previous list.
   
## <a id="#3">3. Getting Started</a>
 
### <a id="3.1"> 3.1 How to install Duke?</a>
  * Download the jar file [here] (https://github.com/TCQian/ip/releases/tag/v0.2)
  * Open the jar file and hava fun with it.
   
### <a id="3.2">3.2 How to use Duke?</a>
   Command | Purpose | Result
   ----------- | ----------- | ----------- 
   `todo` [Description]                                        | Add a task of todo type to the list of tasks                           | New task added onto the list with the tag of `[T]`.
   `event` [Description] + "/at" + [Time (dd/ mm/ yy hhmm)]    | Add a task of event type to the list of tasks                          | New task added onto the list with the tag of `[E]`.
   `deadline` [Description] + "/by" + [Time (dd/ mm/ yy hhmm)] | Add a task of deadline type to the list of tasks                       | New task added onto the list with the tag of `[D]`.
   `list`                                                      | List out the taks.                                                     | A list of tasks is shown.
   `done`                                                      | Mark the task as done.                                                 | The marked task is shown on the list with the tag of `[/]` to indicate its status of completion is done.
   `delete`                                                    | Delete the unwanted task.                                              | A new list of tasks is shown without the deleted task.
   `undo`                                                      | Undo the action.                                                       | The list of tasks from previous action will be shown.
   `find`                                                      | Find the tasks that the description of the tasks contains the keyword. | Only the tasks match the requirement will be shown.

## <a id="#4">4. Contact Us</a>
 For any issues, queries or suggestion, feel free to contact the developer with the github account name : [TCQian](https://github.com/TCQian)
