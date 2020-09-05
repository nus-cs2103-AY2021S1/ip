package duke;

import task.Task;

import java.util.ArrayList;

/**
 * duke.TaskList is a data structure to hold a list of Tasks.
 */
public class TaskList extends ArrayList<Task>{
    public TaskList find(String searchString){
        TaskList foundList = new TaskList();
        for(Task t : this){
            if(t.toString().contains(searchString)){
                foundList.add(t);
            }
        }
        return foundList;
    }
}
