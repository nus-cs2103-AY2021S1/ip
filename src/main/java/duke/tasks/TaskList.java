package duke.tasks;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the list of tasks.
 */
public class TaskList {

    List<Task> taskList;
    public TaskList(List<Task> taskList){
        this.taskList = taskList;
    }

    public TaskList(){
        this.taskList = new ArrayList<>();
    }

    public List<Task> getTaskList() {
        return taskList;
    }
}
