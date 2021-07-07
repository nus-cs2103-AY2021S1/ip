package duke.tasklist;

import java.util.ArrayList;

import duke.exception.InvalidTaskNumberException;
import duke.task.Task;

/**
 * Represents a list to store and manipulates Task.
 * A <code>TaskList</code> object is represented by an ArrayList.
 */
public class TaskList {

    /** ArrayList of Task */
    private ArrayList<Task> tasks;

    /**
     * Constructs a <code>TaskList</code> object.
     */
    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    /**
     * Constructs a <code>TaskList</code> object filled with Task
     * contained in an ArrayList.
     *
     * @param tasks ArrayList of Task
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds Task to the TaskList.
     *
     * @param task Task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Marks Task as complete based on its index in the TaskList.
     *
     * @param index Index of Task to be mark as complete.
     * @throws InvalidTaskNumberException If index is not in the range of the 0 to size of TaskList - 1.
     */
    public void doTask(int index) throws InvalidTaskNumberException {
        try {
            assert index >= 0 : "Task index should not be less than 0";
            tasks.get(index).completeTask();
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTaskNumberException("OOPS!!! No such task available.");
        }
    }

    /**
     * Removes Task from the list based on in its index in the TaskList.
     *
     * @param index Index of Task to be removed.
     * @throws InvalidTaskNumberException If index is not in the range of the 0 to size of TaskList - 1.
     */
    public void removeTask(int index) throws InvalidTaskNumberException {
        try {
            assert index >= 0 : "Task index should not be less than 0";
            tasks.remove(index);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTaskNumberException("OOPS!!! No such task available.");
        }
    }

    /**
     * Returns a Task based on in its index in the TaskList.
     *
     * @param index Index of Task to be retrieved.
     * @return Task to be retrieved.
     * @throws InvalidTaskNumberException If index is not in the range of the 0 to size of TaskList - 1.
     */
    public Task getTask(int index) throws InvalidTaskNumberException {
        try {
            assert index >= 0 : "Task index should not be less than 0";
            return tasks.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTaskNumberException("OOPS!!! No such task available.");
        }
    }

    /**
     * Returns the list of Task.
     *
     * @return A list of Task.
     */
    public ArrayList<Task> getList() {
        return tasks;
    }

    /**
     * Returns the number of Task in the TaskList.
     *
     * @return The size of the list.
     */
    public int getNumberOfTask() {
        return tasks.size();
    }

    /**
     * Returns a String description of the number of Task.
     * If there is more than 1 Task it will return "[number of Task] tasks".
     * If there is 0 or 1 Task it will return "[number of Task] task".
     *
     * @return String description of the number of Task.
     */
    public String getNumberOfTaskDescription() {
        int noOfTask = getNumberOfTask();
        assert noOfTask >= 0 : "Number of Task in TaskList cannot be negative";

        String taskDescription = "";
        if (noOfTask > 1) {
            taskDescription = noOfTask + " tasks";
        } else {
            taskDescription = noOfTask + " task";
        }
        return taskDescription;
    }

    /**
     * Prints out the Task in the TaskList in an orderly manner.
     *
     * @return The description of the Task contents in the TaskList.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the tasks in your list:\n\t ");

        int index = 1;
        for (Task task : tasks) {
            sb.append(index).append(".").append(task.toString()).append("\n\t ");
            index++;
        }
        return sb.delete(sb.length() - 3, sb.length() - 1).toString();
    }
}
