package duke.task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> list;

    public TaskList() {
        list = new ArrayList<>();
    }

    public void add(Task task) {
        list.add(task);
    }

    public Task delete(int index) {
        return list.remove(index);
    }

    public Task get(int index) {
        return list.get(index);
    }

    public int size() {
        return list.size();
    }
}
