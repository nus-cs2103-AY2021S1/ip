import java.util.ArrayList;
import java.util.List;

public class TaskList {
    List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public Task getTask(int taskNumber) {
        return tasks.get(taskNumber - 1);
    }

    public List<Task> getTaskList() {
        return this.tasks;
    }

    public int getNumTasks() {
        return tasks.size();
    }

    public void deleteTask(int taskNumber) {
        tasks.remove(taskNumber - 1);
    }

    public void addTask(Task task) {
        tasks.add(task); // try replacing without an index, should work also
    }

    public void doneTask(int taskNumber) {
        tasks.set(taskNumber - 1, tasks.get(taskNumber - 1).markAsDone());
    }

}
