package tickbot.task;

import java.util.List;

import tickbot.storage.DataStorage;
import tickbot.storage.DataStorageFactory;

/**
 * The class to represent a task list.
 */
public class TaskList {
    private DataStorage storage = DataStorageFactory.getInstance();
    private List<Task> tasks = storage.read();

    /**
     * Get the size of the task list.
     * @return the size of the task list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Get a task in the list by the index.
     * @param index
     * @return the corresponding task.
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Add a task to the end of the task list.
     * @param task the task to add.
     * <p> The data storage would be updated. </p>
     */
    public void add(Task task) {
        tasks.add(task);
        storage.update(tasks);
    }

    /**
     * Remove a task from the task list.
     * @param index the index of the task to be removed.
     * <p> The data storage would be updated. </p>
     */
    public void remove(int index) {
        tasks.remove(index);
        storage.update(tasks);
    }

    /**
     * Mark a task in the task list as completed.
     * <p> The data storage would be updated. </p>
     * @param index the index of the task to be marked as completed.
     * @return {@code true} if the task is successfully marked;
     *         {@code false} if the task is already completed.
     */
    public boolean complete(int index) {
        Task task = tasks.get(index);
        if (task.isCompleted()) {
            return false;
        }
        task.setCompleted(true);
        storage.update(tasks);
        return true;
    }
}