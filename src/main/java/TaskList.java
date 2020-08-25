import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    public Task remove(int index) {
        return tasks.remove(index);
    }

    public void add(Task t) {
        tasks.add(t);
    }

    public int size() {
        return tasks.size();
    }

    public Task get(int index) {
        return tasks.get(index);
    }

    public ArrayList<Task> clone() {
        ArrayList<Task> tasksClone = new ArrayList<>();
        for (Task task : tasks) {
            tasksClone.add(task);
        }
        return tasksClone;
    }
}
