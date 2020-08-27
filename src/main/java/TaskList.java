/**
 * A class representing the tasks in a list.
 */

import java.util.ArrayList;

public class TaskList {

    public ArrayList<Task> tasks;

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
