import java.lang.reflect.Array;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;
    
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }
    
    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }
    
    public void add(Task task) {
        tasks.add(task);
    }
    
    public void delete(int idx) {
        tasks.remove(idx);
    }
    
    public Task get(int idx) {
        return tasks.get(idx);
    }
    
    public Task[] getArray() {
        return this.tasks.toArray(new Task[0]);
    }
    
    public ArrayList<Task> filter(String keyword) {
        ArrayList<Task> satisfiedTasks = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).description.contains(keyword)) {
                satisfiedTasks.add(tasks.get(i));
            }
        }
        return satisfiedTasks;
    }
    
    public String getList() {
        String list = "";
        for (int i = 0; i < tasks.size(); i++) {
            Task t = tasks.get(i);
            list += t.writeMessage() + "\n";
        }
        return list;
    }
    
    public int size() {
        return this.tasks.size();
    }
    
}
