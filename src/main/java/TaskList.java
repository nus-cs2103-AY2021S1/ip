import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private final List<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList addToList(Task newTask) {
        tasks.add(newTask);
        return new TaskList(this.tasks);
    }

    public String getAllTasks() {
        String result = "";
        for (int i = 0; i < tasks.size(); i++) {
            result += "\n";
            Task currentTask = tasks.get(i);
            int taskId = i + 1;
            result += taskId + ". " + currentTask;
        }
        return result;
    }
}
