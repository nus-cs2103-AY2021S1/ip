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
     * Gets the size of the task list.
     * @return The size of the task list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Gets a task in the list by the index.
     * @param index The index of the task to get.
     * @return The corresponding task.
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Adds a task to the end of the task list.
     * <p> The data storage would be updated. </p>
     * @param task The task to add.
     */
    public void add(Task task) {
        assert task != null; // cannot insert null to the task list
        tasks.add(task);
        storage.update(tasks);
    }

    /**
     * Removes a task from the task list.
     * <p> The data storage would be updated. </p>
     * @param index The index of the task to be removed.
     */
    public void remove(int index) {
        tasks.remove(index);
        storage.update(tasks);
    }

    /**
     * Marks a task in the task list as completed.
     * <p> The data storage would be updated. </p>
     * @param index The index of the task to be marked as completed.
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

    public void addTags(int index, String[] tags) {
        Task task = tasks.get(index);
        for (String tag : tags) {
            task.addTag(tag);
        }
        storage.update(tasks);
    }
}
