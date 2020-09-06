package duke.tasklist;

import java.util.ArrayList;

import duke.task.Task;

/**
 * Represents a list to store and manipulate tasks.
 * A <code>TaskList</code> object is represented by an ArrayList.
 */
public class TaskList {

    /** ArrayList of tasks */
    private final ArrayList<Task> tasks;

    /**
     * Constructs a <code>TaskList</code> object.
     */
    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    /**
     * Constructs a <code>TaskList</code> object filled with tasks
     * contained in an ArrayList.
     *
     * @param tasks ArrayList of tasks
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds task to the list.
     *
     * @param task Task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Marks task as complete based on its index in the list.
     *
     * @param index Index of task to be mark as complete.
     */
    public void doTask(int index) {
        assert index >= 0 : "Task index should not be less than 0";
        tasks.get(index).completeTask();
    }

    /**
     * Removes <code>Task</code> from the list based on in its index in the list.
     *
     * @param index Index of task to be removed.
     */
    public void removeTask(int index) {
        assert index >= 0 : "Task index should not be less than 0";
        tasks.remove(index);
    }

    /**
     * Returns a task based on in its index in the list.
     *
     * @param index Index of task.
     * @return Task
     */
    public Task getTask(int index) {
        assert index >= 0 : "Task index should not be less than 0";
        return tasks.get(index);
    }

    /**
     * Returns the list of tasks.
     *
     * @return A list of tasks.
     */
    public ArrayList<Task> getList() {
        return tasks;
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The size of the list.
     */
    public int getNumberOfTask() {
        return tasks.size();
    }

    /**
     * Returns a String description of the number of Task.
     *
     * @return String description of the number of Task.
     */
    public String getNumberOfTaskDescription() {
        int noOfTask = getNumberOfTask();
        String taskDescription = "";
        if (noOfTask > 1) {
            taskDescription = noOfTask + " tasks";
        } else {
            taskDescription = noOfTask + " task";
        }
        return taskDescription;
    }

    /**
     * Prints out the tasks in the list in an orderly manner.
     *
     * @return The description of the contents of the list.
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
