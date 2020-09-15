package duke.tasklist;

import java.util.ArrayList;

import duke.tasks.Task;

/** Represents the dynamic list of tasks. */
public class TaskList {

    /** The number of tasks completed in the session. */
    private static int numCompletedTasks = 0;

    /** The list of tasks. */
    private ArrayList<Task> tasks;

    /** Constructs a new TaskList object with the specified ArrayList of tasks.
     *
     * @param tasks The list of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /** Gets the number of tasks completed in the session.
     *
     * @return The number of tasks completed in the session.
     */
    public static int getNumCompletedTasks() {
        return numCompletedTasks;
    }

    /** Increments the number of completed tasks. */
    private static void incrementNumCompletedTasks() {
        TaskList.numCompletedTasks++;
    }

    /** Gets the list of tasks.
     *
      * @return The list of tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /** Marks a task that is stored in the list as done.
     *
     * @param index The index of the task to be marked as done.
     */
    public void markTaskAsDone(int index) {
        if (!tasks.get(index).isDone()) {
            tasks.get(index).markAsDone();
            incrementNumCompletedTasks();
        }
    }

    /** Adds a task into the list.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /** Deletes a task from the list.
     *
     * @param index The index of the task to be deleted.
     */
    public void deleteTask(int index) {
        tasks.remove(index);
    }
}
