package dude.util;

import java.util.ArrayList;
import java.util.List;

import dude.task.Task;

/**
 * The class that handles all of the tasks while the program is running.
 */


public class TaskList {
    private List<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public int getCount() {
        return tasks.size();
    }

    public void deleteTask(int index) {
        tasks.remove(index);
    }
}
