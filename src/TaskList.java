import java.util.ArrayList;
import java.util.List;

public class TaskList {
    public List<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public TaskList(List<Task> taskList) {
        this.taskList = new ArrayList<>(taskList);
    }

    public static TaskList parse(List<Task> tasks) {
        return new TaskList(tasks);
    }

    public void addTask(Task task) {
        this.taskList.add(task);
    }

    public void deleteTask(int num) {
        taskList.remove(num - 1);
    }

    public Task getTask(int num) {
        return this.taskList.get(num - 1);
    }

    public int size() {
        return this.taskList.size();
    }
}
