package main.task;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the list of tasks.
 * @author Joshua Liang XingYa
 * @author joshualiang.xy@gmail.com
 * @version v0.1
 * @since v0.1
 */
public class TaskList {
    private final List<Task> tasks;

    /**
     * Constructs an instance of TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Adds a task to the list.
     * @param task the task to be added.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Removes a task from the list
     * @param index the index of the task.
     * @return the task that is removed.
     */
    public Task remove(int index) {
        return tasks.remove(index);
    }

    /**
     * Gets the size of the task list.
     * @return the size of the task list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Gets the task from the task list that
     * corresponds to the index.
     * @param index the index of the task.
     * @return the task that corresponds to the index.
     */
    public Task get(int index) {
        return tasks.get(index);
    }
}
