import java.util.ArrayList;

/**
 * TaskList class stores the Tasks used in the application when it is running.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructor that creates a new TaskList object.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Adds a task to TaskList.
     * @param task the Task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a task in TaskList.
     * @param index the index of the Task to be deleted.
     */
    public void deleteTask(int index) {
        tasks.remove(index - 1);
    }

    /**
     * Marks a tasks in the TaskList as done.
     * @param index the index of the Task to be marked as done.
     */
    public void markDone(int index) {
        tasks.get(index - 1).markDone();
    }

    /**
     * Retrieves a Task from the TaskList.
     * @param index the index of the Task to be retrieved.
     * @return the Task retrieved from the TaskList.
     */
    public Task getTask(int index) {
        
        return tasks.get(index - 1);
    }

    /**
     * Returns the number of Tasks in the TaskList.
     * @return the number of Tasks in the TaskList.
     */
    public int size() {
        return tasks.size();
    }
}
