package duke;

import duke.task.Task;

import java.util.ArrayList;

/**
 * The TaskList object contains the task list, and contains operations to modify or get information about the list.
 */
public class TaskList {
    private static ArrayList<Task> tasks;

    /**
     * Initialize the TaskList object with 0 tasks.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Initialize the TaskList object with the list of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        TaskList.tasks = tasks;
    }

    /**
     * Add a task to the task list.
     *
     * @param task Task to be added to the list.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Remove a task from the task list.
     *
     * @param task Task to be removed from the list.
     */
    public void removeTask(Task task) {
        tasks.remove(task);
    }

    /**
     * Returns the number of tasks in the task list.
     *
     * @return Number of tasks in the task list.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Returns the task in the specified index.
     *
     * @param i Index of the desired task.
     * @return Desired task.
     */
    public Task getTask(int i) {
        return tasks.get(i);
    }

    /**
     * Returns the ArrayList of tasks.
     *
     * @return ArrayList of tasks.
     */
    public ArrayList<Task> getTaskList() {
        return tasks;
    }
}
