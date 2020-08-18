import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    private void addTask(Task task) {
        tasks.add(task);
    }

    public void addTask(String taskName) {
        Task task = new Task(taskName);
        addTask(task);
        PrintFunctions.printMessageBetweenLines("added: " + task.getName());
    }

    public void printTaskList() {
        String[] messages = new String[tasks.size()];
        for (int i = 0; i < tasks.size(); i++) {
            messages[i] = String.format("%d. %s", i + 1, tasks.get(i).getName());
        }
        PrintFunctions.printMessagesBetweenLines(messages);
    }
}
