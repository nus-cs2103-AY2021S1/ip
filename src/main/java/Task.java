import java.util.ArrayList;
import java.util.List;

public class Task {
    public static List<Task> taskList = new ArrayList<>();
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[\u2713]" : "[\u2718]");
    }

    public static void addTask(String description) {
        taskList.add(new Task(description));
        Print.formatPrint("Added: " + description);
    }

    public static void printList() {
        String output = "Here are the tasks in your list:\n";
        int count = taskList.size();
        for (int i = 0; i < count; i++) {
            output += (i + 1) + ". " + taskList.get(i) + "\n";
        }
        Print.formatPrint(output.strip());
    }

    public void markAsDone() {
        this.isDone = true;
        Print.formatPrint("Nice! I've marked this task as done: \n   " + this);
    }

    public static void markTaskAsDone(int idx) {
        taskList.get(idx - 1).markAsDone();
    }

    @Override
    public String toString() {
        return this.getStatusIcon() + " " + description;
    }
}
