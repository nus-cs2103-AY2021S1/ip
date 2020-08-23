import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public int tasksSize() {
        return tasks.size();
    }

    public Task get(int taskIndex) {
        return tasks.get(taskIndex);
    }

    public List<Task> getTasks() {
        return this.tasks;
    }

    public void delete(int taskIndex) {
        tasks.remove(taskIndex);
    }

    public void markTaskCompleted(int taskIndex) {
        tasks.get(taskIndex).markAsCompleted();
    }
}
