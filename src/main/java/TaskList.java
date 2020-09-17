import java.util.ArrayList;

/**
 * A class representing the current tasks in a list.
 */
public class TaskList {

    private final ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds the task to the arraylist.
     * @param task task to be added.
     */
    public void addTask(Task task) {

        this.tasks.add(task);
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    /**
     * Removes the task from the arraylist.
     * @param index the index of the task to be removed.
     * @return the task removed.
     */
    public Task removeTask(int index) {
        Task task = this.tasks.remove(index);
        return task;
    }

}
