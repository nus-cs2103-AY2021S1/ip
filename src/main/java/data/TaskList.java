package data;

import data.task.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private final List<Task> taskList = new ArrayList<>();

    public TaskList() {}

    public TaskList(List<Task> list) {
        this.taskList.addAll(list);
    }

    public List<Task> getList() {
        return this.taskList;
    }

    public Task get(int index) throws IndexOutOfBoundsException {
        return taskList.get(index);
    }

    public int size() {
        return this.taskList.size();
    }

    public void add(Task task) {
        this.taskList.add(task);
    }

    public void remove(Task targetTask) {
        this.taskList.remove(targetTask);
    }

}
