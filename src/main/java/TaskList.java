import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TaskList {
    private static final List<Task> tasks = new ArrayList<>();

    public static void addTask(Task task) {
        tasks.add(task);
    }

    public static void deleteTask(int index) {
        tasks.remove(index);
    }

    public static void addAllTasks(Collection<Task> newTasks) {
        tasks.addAll(newTasks);
    }

    public static int tasksCount() {
        return tasks.size();
    }

    public static List<Task> getTasks() {
        return tasks;
    }

    public static Task getTask(int index) {
        return tasks.get(index);
    }
}
