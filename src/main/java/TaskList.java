import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TaskList {
    List<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public TaskList(List<Task> taskArr) {
        this.tasks = taskArr;
    }

    public static TaskList generateTaskList(Storage storage) {
        try {
            return new TaskList(storage.load());
        } catch (IOException e) {
            System.out.println("Could not load file. Generating blank Task List.");
            return new TaskList();
        }
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void removeTask(int i) {
        tasks.remove(i);
    }

    public Task getTask(int i) {
        return tasks.get(i);
    }

    public Task getMostRecentTask() {
        return tasks.get(tasks.size() - 1);
    }

    public int getTaskListSize() {
        return tasks.size();
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }
}
