package main.java;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    List<Task> list = new ArrayList<>();

    public TaskList() {
    }

    public TaskList(List<String> data) throws Exception {
        for (String line : data) {
            list.add(Task.fromData(line));
        }
    }

    public List<String> toData() {
        List<String> data = new ArrayList<>();
        for (Task t : list) {
            data.add(t.toData());
        }
        return data;
    }

    public void add(Task task) {
        list.add(task);
    }

    public int size() {
        return list.size();
    }

    public Task delete(int id) {
        return list.remove(id - 1);
    }
    
    @Override
    public String toString() {
        String str = "";
        for (int i = 0; i < list.size(); i++) {
            str += (i+1) + "." + list.get(i) + "\n";
        }
        return str;
    }

    public Task setDone(int id) {
        Task task = list.get(id - 1);
        task.done = true;
        return task;
    }
}
