import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task newTask) {
        tasks.add(newTask);
    }

    public void removeTask(int position) {
        tasks.remove(position);
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    public int size() {
        return this.tasks.size();
    }

    public Task get(int position) {
        return this.tasks.get(position);
    }
}
