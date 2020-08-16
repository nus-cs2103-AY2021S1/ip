package task;

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
            StringBuilder task = stringBuilder.append(String.valueOf(i + 1) + ") " + tasks.get(i).toString() + "\n");
        }
        return stringBuilder.toString();
    }

    public String markTaskAsDone(int taskIndex) {
        Task updatedTask = tasks.get(taskIndex - 1).markTaskAsComplete();
        tasks.set(taskIndex - 1, updatedTask);
        String taskDoneMessage = "Good job on completing this task! I have marked this task as done: ";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(taskDoneMessage + "\n");
        stringBuilder.append(updatedTask.toString());
        return stringBuilder.toString();
    }
}
