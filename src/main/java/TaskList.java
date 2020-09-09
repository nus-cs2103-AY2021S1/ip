package main.java;

import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> tasks;

    TaskList() {
        this.tasks = new ArrayList<>();
    }

    TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    protected int size() {
        return tasks.size();
    }

    protected Task get(int i) {
        return tasks.get(i);
    }

    protected void add(Task task) {
        tasks.add(task);
    }

    protected void remove(int i) {
        tasks.remove(i);
    }
}
