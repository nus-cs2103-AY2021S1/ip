package tasks;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    List<Task> result;

    public TaskList(List<Task> list) {
        this.result = list;
    }

    public TaskList() {
        this.result = new ArrayList<>();
    }

    public void add(Task task) {
        result.add(task);
    }
    public void set(Integer index, Task task) {
        result.set(index, task);
    }
    public Task get(Integer index) {
        return result.get(index);
    }
    public int getSize() {
        return result.size();
    }
    public void remove(int index) {
        result.remove(index);
    }
}
