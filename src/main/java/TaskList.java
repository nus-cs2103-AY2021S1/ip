import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class TaskList {
    private final List<Task> tasks = new ArrayList<>();

    public TaskList() {
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void deleteTask(int index) {
        tasks.remove(index);
    }

    public void addAllTasks(Collection<Task> newTasks) {
        tasks.addAll(newTasks);
    }

    public int tasksCount() {
        return tasks.size();
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public List<Task> searchTasks(String term) {
        return tasks.stream()
                .filter(t -> t.taskName.contains(term))
                .collect(Collectors.toList());
    }
}
