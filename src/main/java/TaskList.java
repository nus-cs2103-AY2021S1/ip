package main.java;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public List<Task> getTasks() {
        return this.tasks;
    }

    public Task deleteTask(int taskNum) {
        return this.tasks.remove(taskNum);
    }

    public int size() {
        return this.tasks.size();
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public Task getTask(int taskNum) {
        return this.tasks.get(taskNum);
    }

    public boolean isEmpty() {
        return this.tasks.isEmpty();
    }

}
