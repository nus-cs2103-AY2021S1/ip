package duke;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TaskList {

    private List<Task> tasks;
    private static String BORDER = "-----------------------------------------------------------";
    private static String INDENTATION = "    ";

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public static void printBorder() {
        System.out.println(INDENTATION + BORDER);
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void add(Task task) throws IOException {
        printBorder();
        tasks.add(task);
        String textToAppend = task.getSymbol() + " @ " + task.getStatusIcon() + " @ "
                + task.getDescription() + " @ " + task.getDate() + "\n";
        Storage.appendToFile(Storage.getFilePath(), textToAppend);

        System.out.println(INDENTATION + "Got it. I've added this task:");
        System.out.println(INDENTATION + INDENTATION + task);
        System.out.println(INDENTATION + "Now you have " + (tasks.size() != 1
                ? tasks.size() + " tasks in the list."
                : tasks.size() + " task in the list."));
        printBorder();
    }

    public void delete(int index) throws IOException {
        Task task = tasks.get(index);
        tasks.remove(task);
        Storage.updateFile(Storage.getFilePath(), this);

        printBorder();
        System.out.println(INDENTATION + "Noted. I've removed this task:");
        System.out.println(INDENTATION + INDENTATION + task);
        System.out.println(INDENTATION + "Now you have " + (tasks.size() != 1
                ? tasks.size() + " tasks in the list."
                : tasks.size() + " task in the list."));
        printBorder();
    }

    public void replace(Task oldTask, Task newTask) {
        int index = tasks.indexOf(oldTask);
        tasks.set(index, newTask);
    }
}
