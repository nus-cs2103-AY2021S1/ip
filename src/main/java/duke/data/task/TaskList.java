package duke.data.task;

import java.util.ArrayList;
import java.util.List;

import duke.data.exception.DukeException;

/**
 * Data class containing the list of tasks
 * with operations to add or delete tasks.
 */
public class TaskList {
    private List<Task> tasks;

    /**
     * Overloaded constructor initialising the task list by taking in
     * and existing list.
     * @param tasks Task list loaded by Storage class.
     */
    public TaskList(List<Task> tasks) throws DukeException {
        this.tasks = tasks;
    }

    /**
     * Constructor initialising a task list.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Getter returning the task list.
     * @return tasks.
     */
    public List<Task> getTaskList() {
        assert tasks != null;
        return tasks;
    }

    /**
     * Gets a specific task in the list by index.
     * @param index Index of task to be retrieved.
     * @return Task at the given index in the list.
     */
    public Task getTask(int index) {
        assert tasks != null;
        assert index >= 0 & index < tasks.size();
        return tasks.get(index);
    }

    /**
     * Adds a task to the end of the task list.
     * @param task Task to be added.
     */
    public void addTask(Task task) {
        assert tasks != null;
        tasks.add(task);
    }

    /**
     * Deletes a task at a given index in the task list.
     * @param index Index of task to be deleted.
     */
    public void deleteTask(int index) {
        assert tasks != null;
        assert index >= 0 & index < tasks.size();
        tasks.remove(index);
    }

}
