package main.java;

import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> list;

    public TaskList() {
        list = new ArrayList<Task>();
    }

    public void delete(int index) {
        list.remove(index-1);
    }

    public void add(Task task) {
        list.add(task);
    }

    public Task get(int index) {
        return list.get(index-1);
    }

    public int size() {return list.size(); }

    public boolean isEmpty() {return list.isEmpty();}
}
