import java.util.ArrayList;
import java.util.List;

/**
 * Represents a tasklist to store all the tasks.
 */
public class TaskList {
    private List<Task> tasks;

    /**
     * Initialise the tasklist with an arraylist.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Adds a task to the tasklist.
     * @param task task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Returns the size of the tasklist.
     * @return Size of tasklist.
     */
    public int tasksSize() {
        return tasks.size();
    }

    /**
     * Gets a specific task from the list based on the index.
     * @param taskIndex index of task in tasklist.
     * @return Specific task
     */
    public Task get(int taskIndex) {
        return tasks.get(taskIndex);
    }

    /**
     * Returns the tasks stored in the tasklist.
     * @return ArrayList inside tasklist.
     */
    public List<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Removes the task from the list based on the index.
     * @param taskIndex index of task in tasklist.
     */
    public void delete(int taskIndex) {
        tasks.remove(taskIndex);
    }

    /**
     * Indicate task as completed in the tasklist.
     * @param taskIndex index of task in tasklist.
     */
    public void markTaskCompleted(int taskIndex) {
        tasks.get(taskIndex).markAsCompleted();
    }
}
