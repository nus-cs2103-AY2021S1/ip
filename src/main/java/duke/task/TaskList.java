package duke.task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    List<Task> list;

    public TaskList() {
        list = new ArrayList<>();
    }

    public TaskList(List<Task> list) {
        this.list = list;
    }

    public void add(Task task) {
        list.add(task);
    }

    public void delete(int index) {
        list.remove(index);
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public Task get(int index) {
        return list.get(index);
    }

    public int size() {
        return list.size();
    }

    public List<Task> getList() {
        return list;
    }

    public TaskList filter(String keyword) {
        List<Task> filteredList = new ArrayList<>();
        for(Task task: list) {
            if(task.toString().contains(keyword)) {
                filteredList.add(task);
            }
        }
        return new TaskList(filteredList);
    }

    public List<String> itemize() {
        List<String> itemizedList = new ArrayList<>();
        for(int i = 0; i < list.size(); i++) {
            itemizedList.add((i + 1) + ". " + list.get(i));
        }
        return itemizedList;
    }

    public List<String> itemize(String introText) {
        List<String> itemizedList = new ArrayList<>();
        itemizedList.add(introText);
        for(int i = 0; i < list.size(); i++) {
            itemizedList.add((i + 1) + ". " + list.get(i));
        }
        return itemizedList;
    }
}
