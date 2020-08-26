package duke.task;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a list of Tasks. Tasks can only be modified through this class.
 */
public class TaskList {
    private List<Task> list;
    private SaveFunction saveFunction;

    public TaskList() {
        list = new ArrayList<>();
        saveFunction = (list) -> {};
    }

    /**
     * Set the save function for this list. The save function will be run whenver the list changes.
     *
     * @param function the save function
     */
    public void connectStorage(SaveFunction function) {
        saveFunction = function;
    }

    /**
     * Adds a task to this list.
     *
     * @param task the task to add
     */
    public void add(Task task) {
        list.add(task);
        saveFunction.save(this);
    }

    /**
     * Removes a task from this list.
     *
     * @param index the index of the task to be removed
     * @return the task removed
     */
    public Task delete(int index) {
        Task removed = list.remove(index);
        saveFunction.save(this);
        return removed;
    }

    /**
     * Retrieves a task from this list.
     *
     * @param index the index of the task to be retrieved
     * @return a task
     */
    public Task get(int index) {
        return list.get(index);
    }

    /**
     * Get the number of tasks in this list.
     *
     * @return the number of tasks
     */
    public int size() {
        return list.size();
    }

    /**
     * Marks a task as done.
     *
     * @param index the index of the task to mark as done
     */
    public void markAsDone(int index) {
        get(index).markAsDone();
        saveFunction.save(this);
    }
}
