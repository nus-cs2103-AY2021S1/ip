package main.java;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TaskList {
    List<Task> list = new ArrayList<>();

    public TaskList() {
    }

    public TaskList(List<Task> list) {
        this.list = list;
    }

    public static TaskList fromData(List<String> data) throws Exception {
        List<Task> list = new ArrayList<>();

        for (String line : data) {
            list.add(Task.fromData(line));
        }

        return new TaskList(list);
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

    public TaskList filterByKeyword(String keyword) throws Exception {
        List<Task> filteredList = list.stream()
                .filter(t -> t.name.contains(keyword)).collect(Collectors.toList());
        return new TaskList(filteredList);
    }
}
