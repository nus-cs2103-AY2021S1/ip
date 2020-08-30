package seedu.duke;

import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> ls;

    public TaskList() {
        this.ls = new ArrayList<Task>();
    }

    public TaskList(ArrayList<Task> ls) {
        this.ls = ls;
    }

    public void add(Task task) {
        this.ls.add(task);
    }

    public void remove(int i) {
        this.ls.remove(i);
    }

    public int size() {
        return this.ls.size();
    }

    public int indexOf(Task t) {
        return this.ls.indexOf(t);
    }

    public Task get(int i) {
        return this.ls.get(i);
    }

    public ArrayList<Task> getList() {
        return this.ls;
    }

}