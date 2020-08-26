package duke.task;

import java.util.ArrayList;

/**
 * Represents the container that stores all of the tasks at every point in time.
 */
public class TaskList {

    /** Stores the description of the Task. */
    private ArrayList<Task> taskList;

    /**
     * Initialises the task list with an empty ArrayList.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Adds a task into the task list.
     *
     * @param task Task to be added into the task list.
     */
    public void addTask(Task task) {
        getTaskList().add(task);
    }

    /**
     * Deletes the task at a specified index of the ArrayList.
     *
     * @param index The task index that identifies the task to be deleted.
     */
    public void deleteTask(int index) {
        getTaskList().remove(index);
    }

    /**
     * Returns the task at a specified index of the ArrayList.
     *
     * @param index The task index that identifies the task to be returned.
     * @return The task at a specified index of the ArrayList.
     */
    public Task getTask(int index) {
        return getTaskList().get(index);
    }

    /**
     * Returns whether a task at a particular specified index is present.
     *
     * @param index The task index that identifies the task to be checked.
     * @return True if the task is present in the task list.
     */
    public boolean isTaskPresent(int index) {
        return index <= (totalTask() - 1);
    }

    /**
     * Returns the total number of tasks stored in the task list.
     *
     * @return An Integer representing the total number of tasks stored in the task list.
     */
    public int totalTask() {
        return getTaskList().size();
    }

    /**
     * Returns the ArrayList used to contain the tasks.
     *
     * @return The ArrayList used to contain the tasks.
     */
    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

}

