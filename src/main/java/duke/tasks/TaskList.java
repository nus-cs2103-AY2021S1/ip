package duke.tasks;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * List that contains all the task.
 */
public class TaskList {
    private List<Task> tasks;

    /**
     * Constructor to create TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Overloaded Constructor to create TaskList object when a list is already saved in local storage.
     *
     * @param tasks list saved in user's local storage.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a new task to TaskList.
     *
     * @param task task to be added into list of tasks.
     */
    public void add(Task task) {
        assert tasks != null : "Tasklist cannot be null.";
        tasks.add(task);
    }

    /**
     * Removes task from TaskList.
     *
     * @param index position of task being deleted in the TaskList.
     */
    public void delete(int index) {
        assert tasks != null : "Tasklist cannot be null.";
        List<Task> newList = this.tasks;
        tasks = newList.stream().filter((task) -> tasks.indexOf(task) != index)
                .collect(Collectors.toList());
    }

    /**
     * Get task from TaskList.
     *
     * @param index position of task needed in the TaskList.
     * @return task at the index position in TaskList.
     */
    public Task get(int index) {
        assert tasks != null : "Tasklist cannot be null.";
        return tasks.get(index);
    }

    /**
     * Gets the size of the TaskList.
     *
     * @return the number of tasks in the TaskList.
     */
    public int getSize() {
        assert tasks != null : "Tasklist cannot be null.";
        return tasks.size();
    }

    /**
     * Gets the entire list of tasks.
     *
     * @return the entire lists of tasks.
     */
    public List<Task> getList() {
        assert tasks != null : "Tasklist cannot be null.";
        return tasks;
    }
}
