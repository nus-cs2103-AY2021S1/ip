import java.util.ArrayList;

public class TaskList {

    static String LINE = "    ____________________________________________________________";
    static String INDENT = "    ";

    ArrayList<Task> tasks;

    TaskList() {
        this.tasks = new ArrayList<>();
    }

    TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    void updateList(Storage storage) {
        storage.save(tasks);
    }

    void addTask(Task task, Storage storage) {
        tasks.add(task);
        updateList(storage);
    }

    Task deleteTask(int index) {
        Task removed = tasks.remove(index);
        return removed;
    }

    public int numTask() {
        return tasks.size();
    }
}
