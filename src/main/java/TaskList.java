import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a task list.
 */
public class TaskList {

    private List<Task> list;

    /**
     * Creates a task list.
     */
    TaskList() {
        list = new ArrayList<>();
    }

    /**
     * Creates a task list.
     *
     * @param list task list
     */
    TaskList(List<Task> list) {
        this.list = list;
    }

    /**
     * Adds a task to list.
     *
     * @param task task to be added
     * @throws IOException exception to add a task
     */
    public void addTask(Task task) throws IOException {
        list.add(task);
    }

    /**
     * Gets the task list.
     *
     * @return a list of tasks
     */
    public List<Task> getList() {
        return this.list;
    }

}
