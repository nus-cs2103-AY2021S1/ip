package Duke.Helpers;

import Duke.Tasks.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * contains the task list e.g., it has operations to add/delete tasks in the list
 */
public class TaskList {
    private List<Task> allTasks;
    /**
     * assigns allTasks a value
     * @param tasks assings the mem var a value of allTasks
     */
    public TaskList(List<Task> tasks){
        this.allTasks = new ArrayList<>(tasks);
    }
    /**
     * another constructor, where the allTasks variable is just empty
     */
    public TaskList(){
        allTasks = new ArrayList<>();
    }
    /**
     * returns the List<Task> value
     * @return the List<Task> value
     */
    public List<Task> getAllTasks(){
        return allTasks;
    }

}
