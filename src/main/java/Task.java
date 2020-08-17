import java.util.ArrayList;

public class Task {
    private String taskTitle;
    private boolean isDone;

    public Task(String taskTitle) {
        this.taskTitle = taskTitle;
        this.isDone = false;
    }

    public static void createTask(String taskTitle, ArrayList<Task> tasks) {
        Task newTask = new Task(taskTitle);
        tasks.add(newTask);
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String getStatus() {
        return (isDone ? "✓" : "✗");
    }

    @Override
    public String toString() {
        return "[" + getStatus() + "] " + taskTitle;
    }
}
