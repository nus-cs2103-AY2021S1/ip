import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TaskList {
    List<Task> taskArr;

    public TaskList() {
        taskArr = new ArrayList<>();
    }

    public TaskList(List<Task> taskArr) {
        this.taskArr = taskArr;
    }

    public static TaskList generateTaskList(Storage storage) {
        try {
            return new TaskList(storage.load());
        } catch(IOException e) {
            System.out.println("Could not load file. Generating blank Task List.");
            return new TaskList();
        }
    }

    public void addTask(Task task) {
        taskArr.add(task);
    }

    public void removeTask(int i) {
        taskArr.remove(i);
    }

    public Task getTask(int i) {
        return taskArr.get(i);
    }

    public String getMostRecentTask() {
        return taskArr.get(taskArr.size() - 1).toString();
    }

    public int getTaskListSize() {
        return taskArr.size();
    }

    public boolean isEmpty() {
        return taskArr.isEmpty();
    }
}
