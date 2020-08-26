/**
 * Encapsulates the idea of a list of tasks, implemented
 * using an ArrayList
 */
package duke.task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {

    // instance variables
    private List<Task> tasks;

    // constructors
    public TaskList() {
        tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    // accessors for tasks
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    public int size() {
        return tasks.size();
    }

    /**
     * Get the task at the given zero-based index
     *
     * @param index zero-based index of the task to return
     * @return the task at specified index
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Return underlying list of tasks
     */
    public List<Task> getList() {
        return tasks;
    }

    // mutators
    /**
     * Appends a task to end of the list
     *
     * @param task the task to add
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Removes a task at the given zero-based index
     *
     * @param index zero-based index of the task to delete
     */
    public void remove(int index) {
        tasks.remove(index);
    }

    // string processing
    /**
     * Enumerates (starting from 1) tasks in list using their string representations,
     * and stores such enumerations in a list that is to be returned
     *
     * @return list of enumerations
     */
    public List<String> enumerate() {
        List<String> res = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            res.add((i + 1) + ". " + tasks.get(i));
        }
        return res;
    }
}
