package duke.task;
import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> tasks;
    
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }
    
    public TaskList() {
        this.tasks = new ArrayList<>();
    }
    
    public void add(Task task) {
        tasks.add(task);
    }
    
    public Task get(int index) {
        return tasks.get(index);
    }
    
    public int getSize() {
        return tasks.size();
    }
    
    public void remove(int index) {
        tasks.remove(index);
    }
    public void printTaskList() {
        for (int i = 0; i < tasks.size(); i++) {
            String num = (i + 1) + ". ";
            Task current = tasks.get(i);
            System.out.println(num + current);
        }
    }
}

