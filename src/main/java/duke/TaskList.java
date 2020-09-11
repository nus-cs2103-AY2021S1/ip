package duke;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles manipulation of the list of Tasks.
 */

public class TaskList {
    private List<Task> tasks;

    /**
     * Constructor for duke.TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructor for duke.TaskList.
     * @param tasks Initial list of tasks.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Gets list of tasks.
     * @return List of tasks.
     */
    public List<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Gets size of the list of tasks.
     * @return Size of list of tasks.
     */
    public int getSize() {
        return this.tasks.size();
    }

    /**
     * Adds an input task to the list of tasks.
     * @param task duke.Task to be added.
     */
    public String addTask(Task task) {
        assert task != null : "Task to be added does not exist!";

        tasks.add(task);
        return ">> Added the task:\n>> " + task + "\n>> You now have " + tasks.size() + " tasks to do!";
    }

    /**
     * Deletes a task to the list of tasks.
     * @param idx Index of task to be deleted.
     */
    public String deleteTask(int idx) {
        assert idx < tasks.size() : "Task number provided is not valid!";

        Task task = tasks.get(idx);
        tasks.remove(idx);
        return ">> I've eradicated the task:\n>> " + task + "\n>> You now have "
                + tasks.size() + " tasks to do!";
    }


    /**
     * Marks a task in the list of tasks as completed.
     * @param idx Index of task to be completed.
     */
    public String completeTask(int idx) {
        assert idx < tasks.size() : "Task number provided is not valid!";

        tasks.get(idx).complete();
        return ">> Yay! The following task is marked as done:\n>> " + tasks.get(idx);
    }
}
