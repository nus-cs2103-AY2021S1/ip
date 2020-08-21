import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TaskList {
    private List<Task> tasks;

    TaskList() {
        this.tasks = new ArrayList<>();
    }

    TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    Task get(int i) {
        return tasks.get(i);
    }

    int size() {
        return tasks.size();
    }

    List<Task> getList() {
        return this.tasks;
    }

    void addTask(Task task) {
        tasks.add(task);
    }

    Task completeTask(int taskNo) {
        Task task = tasks.get(taskNo - 1);
        task.done();
        return task;
    }

    Task deleteTask(int taskNo) {
        Task task = tasks.get(taskNo - 1);
        tasks.remove(task);
        return task;
    }

    String taskSizeMessage() {
        return "Now you have " + tasks.size() + " tasks in the list.";
    }
}
