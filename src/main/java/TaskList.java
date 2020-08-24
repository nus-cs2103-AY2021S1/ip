import java.util.ArrayList;
import java.util.List;

/**
 * Encapsulates the list of tasks as a class. The 'TaskList' class
 * supports initializing a new list of tasks and getting the list
 * of the tasks.
 */
public class TaskList {
    public List<Task> tasks;

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public List<Task> getTasks() {
        return tasks;
    }
}
