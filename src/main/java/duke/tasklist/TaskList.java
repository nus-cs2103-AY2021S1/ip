package duke.tasklist;

import duke.exceptions.NoSuchTaskException;
import duke.tasks.Task;

import java.util.ArrayList;

/** Represents the list of tasks. */
public class TaskList {

    private ArrayList<Task> tasks;

    /** Constructs an empty taskList. */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /** Gets the ArrayList of tasks.
     *
     * @return the ArrayList of tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /** Adds a task to the list of tasks.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /** Marks a task as done.
     *
     * @param index The index of the task to be marked as done.
     * @return The task that was marked as done.
     * @throws NoSuchTaskException If there is no task corresponding the the index specified.
     */
    public Task markTaskAsDone(int index) throws NoSuchTaskException {
        try {
            Task task = tasks.get(index);
            task.markAsDone();
            return task;
        } catch (IndexOutOfBoundsException e) {
            throw new NoSuchTaskException();
        }
    }

    /** Deletes a task from the task list.
     *
     * @param index The index of the task to be deleted.
     * @return The task that was deleted.
     * @throws NoSuchTaskException If there is no task corresponding to the index specified.
     */
    public Task deleteTask(int index) throws NoSuchTaskException {
        try {
            return tasks.remove(index);
        } catch (IndexOutOfBoundsException e) {
            throw new NoSuchTaskException();
        }
    }

    /** Returns the total number of tasks in the task list.
     *
     * @return The total number of tasks in the task list.
     */
    public int numOfTasks() {
        return tasks.size();
    }

    /** Returns the message detailing the number of tasks in the list.
     *
     * @return The message detailing the number of tasks in the list.
     */
    public String tasksRemaining() {
        return String.format("Now you have %d tasks in the list", numOfTasks());
    }

}