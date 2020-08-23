import java.io.Serializable;
import java.util.ArrayList;

public class TaskList implements Serializable {
    protected ArrayList<Task> list;

    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    public TaskList() {
        this(new ArrayList<>());
    }

    public void add(Task task) {
        list.add(task);
    }

    public Task markTaskAsDone(int index) {
        Task task = list.get(index);
        task.setDone(true);
        return task;
    }

    public Task get(int index) {
        return list.get(index);
    }

    public Task remove(int index) {
        return list.remove(index);
    }

    public int size() {
        return list.size();
    }

    public boolean isEmpty() {
        return size() == 0;
    }
}
