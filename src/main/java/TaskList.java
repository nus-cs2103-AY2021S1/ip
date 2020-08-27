package main.java;

import java.util.ArrayList;
import java.util.List;

/**
 * The TaskList contains the list of tasks and has operations to add/delete/etc. tasks in the list
 *
 * @author Lio
 */
public class TaskList {
    List<Task> list = new ArrayList<>();

    /**
     * Constructor of empty list
     */
    public TaskList() {
    }

    /**
     * Constructor
     *
     * @param data List of data from of tasks
     */
    public TaskList(List<String> data) throws Exception {
        for (String line : data) {
            list.add(Task.fromData(line));
        }
    }

    /**
     * Converts the TaskList to data form
     */
    public List<String> toData() {
        List<String> data = new ArrayList<>();
        for (Task t : list) {
            data.add(t.toData());
        }
        return data;
    }

    /**
     * Adds a task to the TaskList
     *
     * @param task Task to be added
     */
    public void add(Task task) {
        list.add(task);
    }

    /**
     * Returns the size of the TaskList
     *
     * @return Size of TaskList
     */
    public int size() {
        return list.size();
    }

    /**
     * Deletes a task from the TaskList
     *
     * @param id ID of the task to be deleted
     * @return Task that was deleted
     */
    public Task delete(int id) {
        return list.remove(id - 1);
    }

    /**
     * Sets a task as done
     *
     * @param id ID of the task to be set as done
     * @return Task that was set as done
     */
    public Task setDone(int id) {
        Task task = list.get(id - 1);
        task.done = true;
        return task;
    }

    @Override
    public String toString() {
        String str = "";
        for (int i = 0; i < list.size(); i++) {
            str += (i+1) + "." + list.get(i) + "\n";
        }
        return str;
    }

}
