package main.java;

import java.util.ArrayList;

public class TaskList {

    // ArrayList to store task
    protected ArrayList<Task> taskList;

    // Constructor for TaskList
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> data) {
        this.taskList = data;
    }

    public void add(Task t) {
        taskList.add(t);
    }

    public void delete(int i) {
        taskList.remove(i);
    }

    public Task get(int i) {
        return taskList.get(i);
    }

    public int size() {
        return taskList.size();
    }
}
