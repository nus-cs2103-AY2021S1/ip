package duke.task;

import java.util.List;

/**
 * Encapsulates information and methods that relate to a list of tasks being tracked
 * for the user by Duke.
 */
public class TaskList {

    /** List of tasks belonging to the user. */
    private final List<Task> tasks;

    /**
     * Creates and initialises a new TaskList object.
     *
     * @param tasks List of tasks that has been created by the user.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return Number of tasks in the list.
     */
    public int getListSize() {
        return this.tasks.size();
    }

    /**
     * Retrieves the correct task in the list of tasks at the given index.
     *
     * @param index Zero based index of the desired task in the list.
     * @return Task at the given index in the list.
     */
    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    /**
     * Returns the list of tasks created by the user.
     *
     * @return List of tasks.
     */
    public List<Task> getTaskList() {
        return this.tasks;
    }

    /**
     * Adds a new task into the list of tasks.
     *
     * @param task Task to be added.
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Removes a task from the list of tasks at the given index.
     *
     * @param index Zero based index of the task to be deleted.
     */
    public void removeTask(int index) {
        this.tasks.remove(index);
    }

    /**
     * Marks the task at the given index in the list of tasks as completed.
     *
     * @param index Zero based index of the task to be marked as done.
     */
    public void completeTask(int index) {
        Task task = this.tasks.get(index);
        task.markAsDone();
    }

}
