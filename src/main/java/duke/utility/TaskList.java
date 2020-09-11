package duke.utility;

import java.util.ArrayList;
import java.util.List;

import duke.task.Task;

/**
 * Class to represents the Task's list.
 */
public class TaskList {
    private List<Task> tasks;

    /**
     * Constructs a new TaskList with no task.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a new TaskList with the specified list of tasks.
     * @param tasks List of tasks
     */
    public TaskList(List<Task> tasks) {
        assert tasks != null : "list to be assigned to the Task List can't be null!";
        this.tasks = tasks;
    }

    /**
     * Gets the list of tasks.
     * @return list of tasks
     */
    public List<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Deletes a specific task according to the task number. Note that the task count starts from 0.
     * @param taskNum task's number that wants to be deleted
     * @return the deleted task
     */
    public Task deleteTask(int taskNum) {
        return this.tasks.remove(taskNum);
    }

    /**
     * Gets the current size of the TaskList.
     * @return current size of the TaskList
     */
    public int size() {
        return this.tasks.size();
    }

    /**
     * Adds the specified task to the TaskList.
     * @param task task that wants to be added
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Gets a specific task according to the task number. Note that the task count starts from 0.
     * @param taskNum task's number of a task
     * @return task corresponding to the task's number
     */
    public Task getTask(int taskNum) {
        return this.tasks.get(taskNum);
    }

    /**
     * Returns true if the TaskList contains no elements.
     * @return list condition
     */
    public boolean isEmpty() {
        return this.tasks.isEmpty();
    }
}
