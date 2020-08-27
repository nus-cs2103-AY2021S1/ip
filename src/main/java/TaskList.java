import java.util.*;

public class TaskList {

    ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public ArrayList<Task> getArrayList() {
        return tasks;
    }

    public int getSize() {
        return tasks.size();
    }

    public Task get(int i) {
        return tasks.get(i);
    }

    public void remove(int i) {
        tasks.remove(i);
    }

    public void add(Task tmp) {
        tasks.add(tmp);
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }
}
