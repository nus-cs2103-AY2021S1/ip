import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;
    
    public TaskList() {
        this.tasks = new ArrayList<>();
    }
    
    public void addTask(Task task) {
        tasks.add(task);
    }

    public void deleteTask(int index) {
        tasks.remove(index - 1);
    }
    
    public void markDone(int index) {
        tasks.get(index - 1).markDone();
    }
    
    public Task getTask(int index) {
        
        return tasks.get(index - 1);
    }
    
    public int size() {
        return tasks.size();
    }
}
