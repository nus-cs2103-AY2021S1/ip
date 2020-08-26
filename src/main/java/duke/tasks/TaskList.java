package duke.tasks;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the list of tasks.
 */
public class TaskList {

    List<Task> tasks;
    public TaskList(List<Task> tasks){
        this.tasks = tasks;
    }

    public TaskList(){
        this.tasks = new ArrayList<>();
    }

    public List<Task> getTasks() {
        return tasks;
    }
}
