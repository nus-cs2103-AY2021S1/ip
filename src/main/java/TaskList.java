import java.util.List;

public class TaskList {
    private final List<Task> tasks;

    protected TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    protected Task get(int index) {
        return tasks.get(index);
    }

    protected void delete(int index) {
        tasks.remove(index);
    }

    protected void add(Task task) {
        tasks.add(task);
    }

    protected List<Task> getTasks() {
        return tasks;
    }

    protected int size() {
        return tasks.size();
    }

    protected boolean checkIfValid(int digit) {
        return digit <= tasks.size() && digit > 0;
    }

    protected boolean isEmpty() {
        return tasks.isEmpty();
    }
}
