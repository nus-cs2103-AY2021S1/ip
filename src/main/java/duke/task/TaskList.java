package duke.task;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Represents the list of <code>Tasks</code> that Duke stores in.
 */
public class TaskList implements Iterable<Task> {
    private final ArrayList<Task> taskList;

    /**
     * Constructor method.
     * Initialise a new <code>ArrayList</code> of <code>Task</code>.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Implements the <code>Iterable</code> interface in order for <code>TaskList</code> to be iterable.
     * @return the iterator form of <code>TaskList</code>.
     */
    @Override
    public Iterator<Task> iterator() {
        return this.taskList.iterator();
    }

    /**
     * Gets the number of <code>Tasks</code> that are being stored currently.
     * @return the number of <code>Tasks</code>.
     */
    public int getCurrCapacity() {
        return this.taskList.size();
    }

    /**
     * Checks if the given <code>index</code> is within the capacity of <code>TaskList</code>.
     * @param index the value to be checked on.
     * @return <code>true</code> if <code>index</code> is within the capacity.
     */
    public boolean isValidIndex(int index) {
        return index <= this.taskList.size() && index > 0;
    }

    /**
     * Adds the given <code>Task</code> into <code>TaskList</code>.
     * @param task the <code>Task</code> that is to be added.
     * @return the <code>Task</code> that has been added.
     */
    public Task addTask(Task task) {
        this.taskList.add(task);
        return task;
    }

    /**
     * Changes the specified <code>Task</code> in <code>TaskList</code> to completed.
     * @param index the index of the <code>Task</code> that is to be changed.
     * @return the <code>Task</code> that has been completed.
     */
    public Task completeTask(int index) {
        Task task = this.taskList.get(index - 1);
        task.completeTask();
        return task;
    }

    /**
     * Removes a specified <code>Task</code> from <code>TaskList</code>.
     * @param index the index of the <code>Task</code> that is to be deleted.
     * @return the <code>Task</code> that has been removed.
     */
    public Task removeTask(int index) {
        return this.taskList.remove(index - 1);
    }

    /**
     * Converts the <code>TaskList</code> to a representative <code>String</code>.
     * This <code>String</code> contains all details of <code>Tasks</code> stored in <code>TaskList</code>.
     * @return the <code>String</code> representing <code>TaskList</code>.
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
     * Finds all <code>Tasks</code> containing the specified search word.
     * @param searchWord the <code>String</code> that is to be search with.
     * @return a <code>String</code> containing all <code>Tasks</code> that are found.
     */
    public String findString(String searchWord) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.getCurrCapacity(); i++) {
            Task task = this.taskList.get(i);
            if (task.getTaskDescription().contains(searchWord)) {
                String taskString = String.format("%d. %s", i + 1, task.toString());
                sb.append(taskString);
                sb.append("\n");
            }
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }
}
