import java.util.ArrayList;
import java.util.List;

public class TaskList {

    protected List<Task> storage = new ArrayList<>();

    public TaskList() {
    }

    public TaskList(List<Task> storage) {
        this.storage = storage;
    }

    public void updateList(List<Task> taskList) {
        storage = new ArrayList<>(taskList);
    }

    public void add(Task task) {
        storage.add(task);
    }

    public void remove(Task task) {
        storage.remove(task);
    }

    public int listSize() {
        return storage.size();
    }
}
