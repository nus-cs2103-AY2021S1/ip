package luoyi.duke.data.task;

import java.util.ArrayList;
import java.util.List;

/**
 * TaskList class to encapsulate a list of tasks.
 */
public class TaskList {
    private final List<ITask> taskList;

    /**
     * Returns a TaskList using a list of tasks.
     *
     * @param taskList A list of tasks.
     */
    public TaskList(List<ITask> taskList) {
        this.taskList = new ArrayList<>(taskList);
    }

    /**
     * Adds a task to the task list.
     *
     * @param task Task to be added.
     */
    public void add(ITask task) {
        taskList.add(task);
    }

    /**
     * Removes a task in the task list.
     *
     * @param index Task index to be removed.
     */
    public void remove(int index) {
        taskList.remove(index);
    }

    /**
     * Replaces a task in the task list.
     *
     * @param index Task index to be removed.
     * @param newTask Task used to replace the old task.
     */
    public void replace(int index, ITask newTask) {
        taskList.set(index, newTask);
    }

    /**
     * Return the task at index {@code index}.
     *
     * @param index Index of the task to be returned.
     * @return The task at index {@code index}.
     */
    public ITask get(int index) {
        return taskList.get(index);
    }

    /**
     * Returns the size of the task list.
     *
     * @return Size of the task list.
     */
    public int size() {
        return taskList.size();
    }

    /**
     * Returns the list stored in the task list.
     *
     * @return List stored in the task list.
     */
    public List<ITask> getList() {
        return taskList;
    }
}
