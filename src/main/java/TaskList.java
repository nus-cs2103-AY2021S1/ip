import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> list;

    TaskList(List<Task> tasks) {
        this.list = tasks;
    }

    TaskList() {
        this.list = new ArrayList<>();
    }

    public void add(Task task) {
        this.list.add(task);
    }

    public void delete(int index) {
        this.list.remove(index);
    }

    public Task get(int index) {
        return this.list.get(index);
    }

    public int getSize() {
        return this.list.size();
    }

    public List<Task> getList() {
        return this.list;
    }
}
