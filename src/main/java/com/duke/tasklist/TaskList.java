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

    public void addItem(Task item) {
        this.list.add(item);
    }

    public int size() {
        return this.list.size();
    }

    public void setDone(int index) {
        Task task = this.list.get(index);
        task.setDone(true);
    }

    public Task getItem(int index) {
        return this.list.get(index);
    }

    public List getList() {
        return this.list;
    }

    public Task remove(int index) {
        return this.list.remove(index);
    };
}
