import java.util.List;

/**
 * Represents a TaskList object.
 * Stores the List of Tasks.
 */
public class TaskList {
    private List<Task> tasks;

    /**
     * Creates a TaskList object to store tasks
     *
     * @param tasks is the list of Task objects.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns the list of Tasks.
     *
     * @return current list of Tasks stored.
     */
    public List<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Returns the number of Tasks stored.
     *
     * @return current number of Tasks stored.
     */
    public int getLength() {
        return this.tasks.size();
    }

    /**
     * A command to store a Task object.
     *
     * @param newTask is the new Task to be stored.
     */
    public void store(Task newTask) {
        tasks.add(newTask);
    }

    /**
     * A command to remove a Task object from current list.
     * Returns a Task object that is removed.
     *
     * @param index specifies the position of Task in the list.
     */
    public Task remove(int index) {
        Task removedTask = tasks.get(index);
        tasks.remove(index);
        return removedTask;
    }

    /**
     * A command to mark a Task object from current list as done.
     *
     * @param index identifies the Task object to be marked as done.
     */
    public Task markDone(int index) {
        Task doneTask = tasks.get(index);
        doneTask.markAsDone();
        return doneTask;
    }
}
