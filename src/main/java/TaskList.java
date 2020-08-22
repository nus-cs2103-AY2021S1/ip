import java.util.ArrayList;
import java.util.List;

public class TaskList {

    private List<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<>();
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    public void deleteTask(int index) {
        taskList.remove(index);
    }

    public List<Task> getList() {
        return taskList;
    }

    public boolean isEmpty() {
        return taskList.isEmpty();
    }

    public int size() {
        return taskList.size();
    }

    public Task get(int index) {
        return taskList.get(index);
    }

    public String taskSizeString() {
        return "Now you have " + taskList.size() + " task(s) in the list.";
    }

    public void markTaskDone(int taskNum) {
        Task task = taskList.get(taskNum);
        task.markDone();
    }
}
