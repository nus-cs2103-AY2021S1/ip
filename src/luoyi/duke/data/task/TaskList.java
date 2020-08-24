package luoyi.duke.data.task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private final List<ITask> list;

    public TaskList(List<ITask> list) {
        this.list = new ArrayList<>(list);
    }

    public void add(ITask task) {
        list.add(task);
    }

    public void remove(int index) {
        list.remove(index);
    }

    public void replace(int index, ITask newTask) {
        list.set(index, newTask);
    }

    public ITask get(int index) {
        return list.get(index);
    }

    public int size() {
        return list.size();
    }

    public List<ITask> getList() {
        return list;
    }
}
