package main.java;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The TaskList contains the list of tasks and has operations to add/delete/etc. tasks in the list
 *
 * @author Lio
 */
public class TaskList {
    private List<Task> list = new ArrayList<>();

    /**
     * Constructor of empty list
     */
    public TaskList() {
    }

    /**
     * Constructor
     *
     * @param list List of tasks
     */
    public TaskList(List<Task> list) {
        this.list = list;
    }

    /**
     * Converts the TaskList from data form
     *
     * @param data A list of Tasks in data form
     * @return The converted TaskList
     */
    public static TaskList fromData(List<String> data) throws Exception {
        List<Task> list = new ArrayList<>();

        for (String line : data) {
            list.add(Task.fromData(line));
        }

        return new TaskList(list);
    }

    /**
     * Converts the TaskList to data form
     *
     * @return A list of Tasks in data form
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
        task.setDone();
        return task;
    }

    @Override
    public String toString() {
        String str = "";
        for (int i = 0; i < list.size(); i++) {
            str += (i + 1) + "." + list.get(i) + "\n";
        }
        return str;
    }

    /**
     * Filters a TaskList for Tasks containing a keyword
     *
     * @param keyword the keyword that we filter with
     * @return A TaskList with only Tasks that contain the keyword
     */
    public TaskList filterByKeyword(String keyword) {
        List<Task> filteredList = list.stream()
                .filter(t -> t.contains(keyword)).collect(Collectors.toList());
        return new TaskList(filteredList);
    }
}
