package duke;
import java.util.ArrayList;

public class TaskList {
    protected ArrayList<Task> tasks;

    public TaskList (ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public Task get(int index) {
        return this.tasks.get(index);
    }

    public int size() {
        return this.tasks.size();
    }

    public void add(Task task) {
        this.tasks.add(task);
    }

    public void delete(int index) {
        this.tasks.remove(index);
        for (int i = index; i < tasks.size(); i++) {
            tasks.get(i).sequence = tasks.get(i).sequence - 1;
        }
    }

}
