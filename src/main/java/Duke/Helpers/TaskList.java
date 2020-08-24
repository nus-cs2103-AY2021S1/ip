package Duke.Helpers;
import Duke.Duke;
import Duke.Tasks.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    List<Task> allTasks;
    public TaskList(List<Task> tasks){
        this.allTasks = new ArrayList<>(tasks);
    }
    public TaskList(){
        allTasks = new ArrayList<>();
    }
    public List<Task> getAllTasks(){
        return allTasks;
    }
}
