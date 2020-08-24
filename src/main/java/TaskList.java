import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> list;

    public TaskList() {
        this.list = new ArrayList<>();
    }

    public Task get(int index) {
        return this.list.get(index);
    }

    public int size() {
        return this.list.size();
    }

    public void add(Task t) {
        this.list.add(t);
    }

    public void add(TaskList tasks) {
        ArrayList<Task> stored = tasks.list;
        tasks.list.addAll(stored);
    }

    public void delete(Task t) {
        this.list.remove(t);
    }

    public void delete(int index) {
        this.list.remove(index);
    }
}
