package luoyi.duke.data.task;

import java.util.ArrayList;
import java.util.List;

/**
 * TaskList class to encapsulate a list of tasks.
 */
public class TaskList {
    private final List<ITask> list;

    /**
     * Returns a TaskList using a list of tasks.
     *
     * @param list A list of tasks.
     */
    public TaskList(List<ITask> list) {
        this.list = new ArrayList<>(list);
    }

    /**
     * Adds a task to the task list.
     *
     * @param task Task to be added.
     */
    public void add(ITask task) {
        list.add(task);
    }

    /**
     * Removes a task in the task list.
     *
     * @param index Task index to be removed.
     */
    public void remove(int index) {
        list.remove(index);
    }

    /**
     * Replaces a task in the task list.
     *
     * @param index Task index to be removed.
     * @param newTask Task used to replace the old task.
     */
    public void replace(int index, ITask newTask) {
        list.set(index, newTask);
    }

    /**
     * Return the task at index {@code index}.
     *
     * @param index Index of the task to be returned.
     * @return The task at index {@code index}.
     */
    public ITask get(int index) {
        return list.get(index);
    }

    /**
     * Returns the size of the task list.
     *
     * @return Size of the task list.
     */
    public int size() {
        return list.size();
    }

    /**
     * Returns the list stored in the task list.
     *
     * @return List stored in the task list.
     */
    public List<ITask> getList() {
        return list;
    }
}
