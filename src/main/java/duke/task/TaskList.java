package duke.task;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Represents the list of Tasks that Duke stores in.
 */
public class TaskList implements Iterable<Task> {
    private final ArrayList<Task> taskList;

    /**
     * Constructor method.
     * Initialise a new ArrayList of Task.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Implements the Iterable interface in order for TaskList to be iterable.
     * @return the iterator form of TaskList.
     */
    @Override
    public Iterator<Task> iterator() {
        return this.taskList.iterator();
    }

    /**
     * Gets the number of Tasks that are being stored currently.
     * @return the number of Tasks.
     */
    public int getCurrCapacity() {
        return this.taskList.size();
    }

    /**
     * Checks if the given index is within the capacity of TaskList.
     * @param index the value to be checked on.
     * @return true if the index is within the capacity.
     */
    public boolean isValidIndex(int index) {
        return index <= this.taskList.size() && index > 0;
    }

    /**
     * Adds the given task into TaskList.
     * @param task the task that is to be added.
     * @return the task that has been added.
     */
    public Task addTask(Task task) {
        this.taskList.add(task);
        return task;
    }

    /**
     * Changes the specified Task in TaskList to completed.
     * @param index the index of the Task that is to be changed.
     * @return the Task that has been completed.
     */
    public Task completeTask(int index) {
        Task task = this.taskList.get(index - 1);
        task.completeTask();
        return task;
    }

    /**
     * Removes a specified Task from TaskList.
     * @param index the index of the Task that is to be deleted.
     * @return the Task that has been removed.
     */
    public Task removeTask(int index) {
        return this.taskList.remove(index - 1);
    }

    /**
     * Converts the TaskList to a representative String.
     * This string contains all details of Tasks stored in TaskList.
     * @return the string representing TaskList.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.taskList.size(); i++) {
            String taskLine = String.format("%d. %s", i + 1, this.taskList.get(i));
            sb.append(taskLine);
            sb.append("\n");
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }

    /**
     * Finds all Tasks containing the specified search word.
     * @param searchWord the String that is to be search with.
     * @return a TaskList containing all Tasks that are found.
     */
    public TaskList findString(String searchWord) {
        TaskList foundTasks = new TaskList();
        for (Task task : this.taskList) {
            if (task.getTaskDescription().contains(searchWord)) {
                foundTasks.addTask(task);
            }
        }
        return foundTasks;
    }
}
