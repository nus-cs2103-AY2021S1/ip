package com.duke.tasklist;

import com.duke.tasks.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> list;

    public TaskList() {
        this.list = new ArrayList<>();
    }

    public TaskList(List<Task> taskArr) {
        this.list = taskArr;
    }

    /**
     * Adds entry into TaskList.
     *
     * @param item task to add into TaskList.
     */
    public void addItem(Task item) {
        this.list.add(item);
    }

    /**
     * Returns number of entries in TaskList.
     *
     * @return int returns number of entries in TaskList.
     */
    public int size() {
        return this.list.size();
    }

    /**
     * Marks specified entry in TaskList as done.
     *
     * @param index index of task in TaskList.
     */
    public void setDone(int index) {
        Task task = this.list.get(index);
        task.setDone(true);
    }

    /**
     * Returns specified task in TaskList.
     *
     * @param index index of task in TaskList.
     * @return List returns list with tasks saved as entries.
     */
    public Task getItem(int index) {
        return this.list.get(index);
    }

    /**
     * returns list under the TaskList object.
     *
     * @return List returns list under TaskList object.
     */
    public List getList() {
        return this.list;
    }

    /**
     * Removes entry from TaskList.
     *
     * @param index Index of entry to be removed.
     */
    public Task remove(int index) {
        return this.list.remove(index);
    }
}
