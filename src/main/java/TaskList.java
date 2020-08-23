import java.util.List;
import java.util.ArrayList;

public class TaskList {

    private final List<Task> taskList = new ArrayList<>();

    public void addTask(Task task) {
        taskList.add(task);
    }

    public void completeTask(int taskNumber) {
        Task completedTask = taskList.get(taskNumber - 1).markCompleted();
        taskList.set(taskNumber - 1, completedTask);
    }

    public void deleteTask(int taskNumber) {
        Task toRemove = taskList.get(taskNumber - 1);
        taskList.remove(taskNumber - 1);
    }

    public String tasksToString() {
        StringBuilder tasks = new StringBuilder("Here are the tasks in your list: \n");
        for (int i = 0; i < taskList.size(); i++) {
            tasks.append(String.format("%d. %s", i + 1, taskList.get(i)));
            if (i != taskList.size() - 1) {
                tasks.append('\n');
            }
        }
        return tasks.toString();
    }
}
