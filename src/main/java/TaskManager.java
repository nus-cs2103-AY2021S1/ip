import java.util.List;
import java.util.ArrayList;

public class TaskManager {
    private List<Task> tasks = new ArrayList<>();

    public TaskManager() {
    }

    public String addTask(Task newTask) {
        tasks.add(newTask);
        return "Added: " + newTask.getContent();
    }

    public String getAllTasks() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Here are all your tasks: \n");
        for (int i = 0; i < tasks.size(); i++) {
            StringBuilder task = stringBuilder.append(String.valueOf(i + 1) + ") " + tasks.get(i).getContent() + "\n");
        }
        return stringBuilder.toString();
    }
}
