package duke;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import duke.task.*;

/**
 * Contains a list of tasks.
 * Responsible for any task related operations.
 */
public class TaskList {

    /**
     * The list of tasks.
     */
    private List<Task> tasks;

    /**
     * Creates a new instance of a TaskList object.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Creates a new instance of a TaskList object with attributes defined
     * in the parameters.
     * @param tasks The list of tasks.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Retrieves the task according to the index given.
     * @param i Index of the task in the list.
     * @return Returns the task.
     */
    public Task get(int i) {
        return tasks.get(i);
    }

    /**
     * Retrieves the size of the task list.
     * @return Returns the size.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Retrieves the task list.
     * @return Returns the list of tasks.
     */
    public List<Task> getList() {
        return this.tasks;
    }

    /**
     * Adds a task to the task list.
     * @param task Task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Marks a task in the task list as complete.
     * @param taskNo Index of the task to be completed.
     * @return Returns the task.
     */
    public Task completeTask(int taskNo) {
        Task task = tasks.get(taskNo - 1);
        task.done();
        return task;
    }

    /**
     * Removes a task in the task list.
     * @param taskNo Index of the task to be removed.
     * @return Returns the removed task.
     */
    public Task deleteTask(int taskNo) {
        Task task = tasks.get(taskNo - 1);
        tasks.remove(task);
        return task;
    }

    /**
     * Retrieves a string describing the task size.
     * @return Returns the string.
     */
    public String taskSizeMessage() {
        return "Now you have " + tasks.size() + " tasks in the list.";
    }
}
