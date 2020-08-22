package main.java;

import exception.NoSuchTaskException;
import task.Task;

import java.util.List;

public class TaskList {
    List<Task> tasks;
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public List<Task> all() {
        return tasks;
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public Task get(int zeroBasedIndex) throws NoSuchTaskException {
        try {
            return tasks.get(zeroBasedIndex);
        } catch (IndexOutOfBoundsException e) {
            throw new NoSuchTaskException();
        }
    }

    public Task remove(int zeroBasedIndex) throws NoSuchTaskException {
        try {
            return tasks.remove(zeroBasedIndex);
        } catch (IndexOutOfBoundsException e) {
            throw new NoSuchTaskException();
        }
    }

    public int size() {
        return tasks.size();
    }
}
