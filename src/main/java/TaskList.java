import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> tasks;
    
    TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;    
    }
    
    public void add(Task task) {
        tasks.add(task);
    }
    
    public void remove(int index) {
        tasks.remove(index);
    }
    
    public Task get(int index) {
        return tasks.get(index);
    }
    
    public void markAsDone(int index) {
        tasks.set(index, tasks.get(index).markAsDone());
    }

    @Override
    public String toString() {
        String str = "";
        for (int i = 0; i < tasks.size(); i++) {
            str += (i + 1) + ". " + tasks.get(i).toString() + "\n";
        }
        return str;
    }
    
    
}
