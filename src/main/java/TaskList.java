import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> listOfTasks;

    public TaskList(ArrayList<Task> listOfTasks) {
        this.listOfTasks = listOfTasks;
    }

    public TaskList() {
        this.listOfTasks = null;
    }

    public ArrayList<Task> getListOfTasks() {
        return this.listOfTasks;
    }

    public Task get(int index) {
        return this.listOfTasks.get(index);
    }

    public Task remove(int index) {
        return this.listOfTasks.remove(index);
    }

    public int size() {
        return this.listOfTasks.size();
    }

    public void add(Task task) {
        this.listOfTasks.add(task);
    }
}
