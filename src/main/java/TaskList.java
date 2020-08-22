import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> listTasks;
    
    TaskList() {
        listTasks = new ArrayList<>();
    }
    
    public int size() {
        return listTasks.size();
    }
    
    public Task get(int n) {
        return listTasks.get(n);
    }
    
    public void add(Task t) {
        listTasks.add(t);
    }
    
    public void remove(int n) {
        listTasks.remove(n);
    }
}
