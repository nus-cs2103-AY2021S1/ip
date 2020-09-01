package duke.task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> list;

    /**
     * The constructor for task list.
     */
    public TaskList() {
        this.list = new ArrayList<>();
    }

    public TaskList(List<Task> list) {
        this.list = list;
    }


    public void add(Task task) {
        list.add(task);
    }

    public Task delete(int num) {
        return list.remove(num);
    }

    public int size() {
        return list.size();
    }

    public Task get(int i) {
        return list.get(i);
    }

    public List<Task> getList() {
        return list;
    }
}
