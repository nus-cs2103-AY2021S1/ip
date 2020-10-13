package duke.task;

import java.util.ArrayList;
import java.util.List;

/**
 * The class containing the list of tasks the user has specified.
 */
public class TaskList {
    private List<Task> list;

    /**
     * The constructor for task list.
     */
    public TaskList() {
        this.list = new ArrayList<>();
    }

    /**
     * The constructor for task list.
     */
    public TaskList(List<Task> list) {
        this.list = list;
    }

    /**
     * Adds a task to the list.
     * @param task the task to be added
     */
    public void add(Task task) {
        list.add(task);
    }

    /**
     * Removes a task from the list.
     * @param num the index of the task to be removed
     * @return the modified list
     */
    public Task delete(int num) {
        return list.remove(num);
    }

    /**
     * Returns the size of the current task list.
     * @return size
     */
    public int size() {
        return list.size();
    }

    /**
     * Retrieves a specific task from the list
     * @param index the index of the specific task
     * @return the task
     */
    public Task get(int index) {
        return list.get(index);
    }

    /**
     * Retrieves the entire list.
     * @return the task list
     */
    public List<Task> getList() {
        return list;
    }
}
