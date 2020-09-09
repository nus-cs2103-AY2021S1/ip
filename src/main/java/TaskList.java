package main.java;

import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> tasks;

    TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public int size() {
        return tasks.size();
    }

    public Task get(int i) {
        return tasks.get(i);
    }

    protected void add(Task task) {
        tasks.add(task);
    }

    protected void remove(int i) {
        tasks.remove(i);
    }
}
