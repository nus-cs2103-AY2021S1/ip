package Duke.Helpers;
import Duke.Duke;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    List<String> allTasks;
    public TaskList(List<String> tasks){
        this.allTasks = new ArrayList<>(tasks);
    }
    public TaskList(){
        allTasks = new ArrayList<>();
    }
    public List<String> getAllTasks(){
        return allTasks;
    }
}
