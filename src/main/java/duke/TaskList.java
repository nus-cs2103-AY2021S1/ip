package duke;

import java.util.ArrayList;
import duke.task.Task;

/**
 * contains the task list
 */
public class TaskList {
    private final ArrayList<Task> listOfTasks;

    /**
     * Constructor for creating TaskList object
     *
     * @param listOfTasks list of input tasks
     */
    public TaskList(ArrayList<Task> listOfTasks) {
        this.listOfTasks = listOfTasks;
    }

    /**
     * Alternative constructor for empty list
     */
    public TaskList() {
        this.listOfTasks = null;
    }

    /**
     * Getter for retrieving list of tasks
     *
     * @return list of tasks
     */
    public ArrayList<Task> getListOfTasks() {
        return this.listOfTasks;
    }

    /**
     * Retrieves task at a specified index
     *
     * @param index position of task in list
     * @return corresponding task
     */
    public Task get(int index) {
        return this.listOfTasks.get(index);
    }

    /**
     * Removes task at a specified index
     *
     * @param index position of task in list
     * @return corresponding task
     */
    public Task remove(int index) {
        return this.listOfTasks.remove(index);
    }

    /**
     * Retrieves number of tasks in the list
     *
     * @return number of tasks
     */
    public int size() {
        return this.listOfTasks.size();
    }

    /**
     * Adds a task to the list
     *
     * @param task task to be added
     */
    public void add(Task task) {
        this.listOfTasks.add(task);
    }
}
