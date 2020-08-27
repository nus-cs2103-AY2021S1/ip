package duke;

import java.util.ArrayList;

public class TaskList {
    protected ArrayList<Task> list;
    public TaskList() {
        this.list = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    public int size() {
        return list.size();
    }

    public Task get(int index) {
        return list.get(index);
    }

    public ArrayList<Task> getTasks() {
        return list;
    }

    public void addTask(Task task) {
        list.add(task);
    }

    public void removeTask(int index) {
        list.remove(index);
    }
}