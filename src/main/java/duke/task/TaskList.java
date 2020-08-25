package duke.task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> list;
    private SaveFunction saveFunction;

    public TaskList() {
        list = new ArrayList<>();
        saveFunction = (list) -> {};
    }

    public void connectStorage(SaveFunction function) {
        saveFunction = function;
    }

    public void add(Task task) {
        list.add(task);
        saveFunction.save(this);
    }

    public Task delete(int index) {
        Task removed = list.remove(index);
        saveFunction.save(this);
        return removed;
    }

    public Task get(int index) {
        return list.get(index);
    }

    public int size() {
        return list.size();
    }

    public void markAsDone(int index) {
        get(index).markAsDone();
        saveFunction.save(this);
    }
}
