import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> list;

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

    public void delete(int index) {
        this.list.remove(index);
    }

    public ArrayList<Task> getList() {
        return this.list;
    }
}
