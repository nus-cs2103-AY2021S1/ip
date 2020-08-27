import java.util.ArrayList;

public class TaskList {
    
    ArrayList<Task> tasks;
    
    TaskList() {
        
    }
    
    TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }
    
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Retrieves the ArrayList<Task> attribute
     * @return returns the TaskList's ArrayList<Task>
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }
}
