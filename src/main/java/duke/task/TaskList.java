package duke.task;

import java.time.LocalDate;
import java.util.ArrayList;

import duke.DateTime;

/**
 * Represents the container that stores all of the tasks at every point in time.
 */
public class TaskList {

    /** Stores the description of the Task. */
    private final ArrayList<Task> tasks;

    /**
     * Initialises the task list with an empty ArrayList.
     */
    public TaskList() {
        tasks = new ArrayList<>();
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
        assert index < totalTask() : "Task index is greater than available!";
        getTaskList().remove(index);
    }

    /**
     * Returns the task at a specified index of the ArrayList.
     *
     * @param index The task index that identifies the task to be returned.
     * @return The task at a specified index of the ArrayList.
     */
    public Task getTask(int index) {
        assert index < totalTask() : "Task index is greater than available!";
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
     * Finds all matching tasks from the task list.
     *
     * @param keyword Keyword to be matched with the task names in the task list.
     * @return ArrayList containing all matching tasks that contain the keyword.
     */
    public ArrayList<Task> findTask(String keyword) {
        ArrayList<Task> foundTasks = new ArrayList<>();
        for (int i = 0; i < getTaskList().size(); i++) {
            if (getTask(i).getName().contains(keyword)) {
                foundTasks.add(getTask(i));
            }
        }
        return foundTasks;
    }

    /**
     * Finds all matching tasks from the task list based on the date.
     *
     * @param date Date to match with task dates.
     * @return ArrayList containing all matching tasks that match the specified date.
     */
    public ArrayList<Task> findTaskByDate(LocalDate date) {
        ArrayList<Task> foundTasks = new ArrayList<>();
        for (int i = 0; i < getTaskList().size(); i++) {
            DateTime taskDateTime = getTask(i).getDateTime();
            if (taskDateTime != null && taskDateTime.checkDateEqual(date)) {
                foundTasks.add(getTask(i));
            }
        }
        return foundTasks;
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
        return tasks;
    }

}

