package duke.task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> list;

    public TaskList(){
        this.list = new ArrayList<>();
    }

    public TaskList(List<Task> add) {
        this.list = add;
    }

    public Task get(int index) {
        return list.get(index);
    }

    public void remove(int index) {
        list.remove(index);
    }

    public int size() {
        return list.size();
    }

    public void add(Task task) {
        list.add(task);
    }

}
