package duke.tasks;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents the list of Tasks.
 */
public class TaskList implements Serializable {

    private List<Task> tasks;

    /**
     * Construct an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * @param index Index of the Task in the TaskList.
     * @return Task at the corresponding index.
     */
    public Task getTaskAtIndex(int index) {
        assert (index >= 0 && index < this.listSize());
        return this.tasks.get(index);
    }

    /**
     * @param task Task to be added to the TaskList.
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * @param index Item index in the TaskList.
     */
    public void deleteTask(int index) {
        assert (index >= 0 && index < this.listSize());
        this.tasks.remove(index);
    }

    /**
     * @return Size of the TaskList.
     */
    public int listSize() {
        return tasks.size();
    }
}
