import java.util.ArrayList;
import java.util.List;

public class TaskList {
    public List<Task> tasks;

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public List<Task> getTasks() {
        return tasks;
    }
}
