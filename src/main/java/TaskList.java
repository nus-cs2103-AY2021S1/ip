import java.util.ArrayList;

/**
 * Contains the task list e.g. it has operations to add/delete tasks in the list
 */
public class TaskList {
    private ArrayList<Task> tasks;
    
    public TaskList() {
        this.tasks = new ArrayList<>();
    }
    
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }
    
    public void addTask(Task task) {
        tasks.add(task);
    }
    
    public int getSize() {
        return this.tasks.size();
    }

    public Task get(int taskNumber) {
        return tasks.get(taskNumber);
    }
    
    public ArrayList<Task> getTaskList() {
        return this.tasks;
    }
    
    public void deleteTask(int taskNumber) {
        tasks.remove(taskNumber);
    }
    
}
