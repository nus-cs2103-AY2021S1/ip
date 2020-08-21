import java.util.List;
import java.util.ArrayList;

public class TaskList {
    private List<Task> tasks;

    TaskList() {
        this.tasks = new ArrayList<>();
    }

    TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public Task getTask(int taskId) {
        return this.tasks.get(taskId - 1);
    }

    public boolean addTask(Task task) {
        return this.tasks.add(task);
    }

    public Task deleteTask(int taskId) {
        return this.tasks.remove(taskId - 1);
    }

    public int size() {
        return this.tasks.size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < this.tasks.size(); i++) {
            sb.append(String.format("%d. %s\n", i + 1, tasks.get(i)));
        }

        return sb.toString().trim();
    }

}
