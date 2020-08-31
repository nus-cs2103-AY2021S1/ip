import java.util.ArrayList;

public class TaskList {
    private static ArrayList<Task> tasks;
    private static Ui ui;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
        this.ui = new Ui();
    }

    public TaskList() {
        this.tasks = new ArrayList<Task>();
        this.ui = new Ui();
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    public static void add(Task task) {
        tasks.add(task);
        ui.add(task, tasks);
    }

    public static void done(int n) {
        tasks.get(n-1).setDone();
        ui.done(n, tasks);
    }

    public static void delete(int n) {
        ui.delete(n, tasks);
        tasks.remove(n-1);
        ui.count(tasks);
    }
}
