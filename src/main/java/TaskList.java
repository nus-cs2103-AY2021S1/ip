import java.util.List;
import java.util.ArrayList;

public class TaskList {
    protected List<Task> list;

    public TaskList() {
        this.list = new ArrayList<>();
    }


    public List<Task> getList() {
        return this.list;
    }

    public void add(Task task) {
        this.list.add(task);
    }

    public void delete(Task task) {
        this.list.remove(task);
    }

    public int getSize() {
        return this.list.size();
    }

    public void empty() {
        this.list.clear();
    }
}
