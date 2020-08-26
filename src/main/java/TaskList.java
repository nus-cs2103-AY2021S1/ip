import java.util.ArrayList;
import java.util.function.Consumer;

public class TaskList {
    private final ArrayList<Task> TASK_LIST = new ArrayList<>();

    public void add(Task t) {
        TASK_LIST.add(t);
    }

    public Task get(int i) {
        return TASK_LIST.get(i);
    }

    public Task remove(int i) {
        return TASK_LIST.remove(i);
    }

    public int size() {
        return TASK_LIST.size();
    }

    public void forEach(Consumer<Task> action) {
        TASK_LIST.forEach(action);
    }
}
