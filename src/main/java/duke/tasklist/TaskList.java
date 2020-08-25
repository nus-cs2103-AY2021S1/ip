package duke.tasklist;

import duke.tasks.Task;

import java.util.ArrayList;

/** Represents the dynamic list of tasks. */
public class TaskList {

    /** The list of tasks. */
    public ArrayList<Task> lst;

    /** Constructor.
     *
     * @param lst The list of tasks.
     */
    public TaskList(ArrayList<Task> lst) {
        this.lst = lst;
    }

    /** Marks a task that is stored in the list as done.
     *
     * @param num The index of the task to be marked as done.
     */
    public void markTaskAsDone(int num) {
        lst.get(num).markAsDone();
    }

    /** Adds a task into the list.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        lst.add(task);
    }

    /** Deletes a task from the list.
     *
     * @param num The index of the task to be deleted.
     */
    public void deleteTask(int num) {
        lst.remove(num);
    }
}
