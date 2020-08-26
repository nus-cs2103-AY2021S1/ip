package duke.task;
import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> taskList;
    
    public TaskList(List<Task> list) {
        this.taskList = list;
    }
    
    public TaskList() {
        this.taskList = new ArrayList<>();
    }
    
    public void add(Task task) {
        taskList.add(task);
    }
    
    public Task get(int index) {
        return taskList.get(index);
    }
    
    public int getSize() {
        return taskList.size();
    }
    
    public void remove(int index) {
        taskList.remove(index);
    }
}

