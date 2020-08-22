package main.java;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void add(Task task) {
        this.tasks.add(task);
    }

    public void remove(int idx) {
        this.tasks.remove(idx);
    }

    public Task get(int idx) {
        return tasks.get(idx);
    }

    public int size() {
        return tasks.size();
    }
}
