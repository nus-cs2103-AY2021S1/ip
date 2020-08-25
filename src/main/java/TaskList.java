import java.util.ArrayList;

public class TaskList {

    protected ArrayList<Task> tasks;
    public TaskList() {
        tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }

    public int size() {
        return tasks.size();
    }

    public Task getTask(int pos) {
        return tasks.get(pos);
    }

    public void removeTask(int pos) {
        tasks.remove(pos);
    }

    public void printAddedTask(Task task) {
        System.out.println("Got it. I've added this task:\n"
                + task + "\nNow you have " + String.valueOf(tasks.size())
                + " tasks in the list.");
    }

    public void addTask(Task task) {
        tasks.add(task);
        printAddedTask(task);
    }
}
