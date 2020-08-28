import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> tasks = new ArrayList<>();

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void doneTask(int taskNumber) {
        tasks.get(taskNumber - 1).markDone();
    }

    public Task getTask(int taskNumber) {
        return tasks.get(taskNumber - 1);
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void deleteTask(int taskNumber) {
        tasks.remove(taskNumber - 1);
    }

    public int size() {
        return tasks.size();
    }
}
