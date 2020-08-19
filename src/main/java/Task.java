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

    public static void addTask(Task task) {
        taskList.add(task);
        Print.formatPrint(String.format("Got it. I've added this task: \n   %s\nNow you have %d task%s in the list.",
                task, taskList.size(), taskList.size() > 1 ? "s": ""));
    }

    public static void deleteTask(int num) {
        Task deletedTask = taskList.get(num - 1);
        taskList.remove(num - 1);
        Print.formatPrint(String.format("Got it. I've deleted this task: \n   %s\nNow you have %d task%s in the list.",
                deletedTask, taskList.size(), taskList.size() > 1 ? "s": ""));
    }

    public static void printList() {
        String output = "Here are the tasks in your list:\n";
        int count = taskList.size();
        if (count <= 0) {
            Print.formatPrint("There is no task in your list currently. ");
        } else {
            for (int i = 0; i < count; i++) {
                output += (i + 1) + ". " + taskList.get(i) + "\n";
            }
            Print.formatPrint(output.strip());
        }
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
