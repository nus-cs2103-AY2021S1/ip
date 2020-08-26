package rogue.model.task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public List<Task> getTaskList() {
        return tasks;
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public Task get(int index) throws IndexOutOfBoundsException {
        return tasks.get(index);
    }

    public Task delete(int index) throws IndexOutOfBoundsException {
        return tasks.remove(index);
    }

    public int count() {
        return tasks.size();
    }
}
