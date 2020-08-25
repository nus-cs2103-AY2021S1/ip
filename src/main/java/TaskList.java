import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;
    TaskList() {
        this.tasks = new ArrayList<>();
    }
    TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }
    
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    public int size() {
        return tasks.size();
    }
    
    public Task get(int index) {
        return tasks.get(index);
    }

    public void remove(int index) {
        tasks.remove(index);
    }

    public void add(Task task) {
        tasks.add(task);
    }
}
