import java.util.ArrayList;
import java.util.List;

public class Ui {

    public static String line = "---------------------------------------------------";

    public Ui() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String welcome = line + "\nHello! I'm Duke!\n" +
                "What can I do for you?\n";
        System.out.println(welcome);
    }

    public void displayList(List<Task> list) {
        System.out.println(line);
        for (Task task : list) {
            System.out.println(task.getStatusWithIndex());
        }
    }

    public void addTask(Task task, List<Task> list) {
        System.out.println(task == null
                ? "Failed!"
                : line + "\nGot it. I've added this task:\n   " + task.toString()
                    + "\nNow you have " + list.size() + " tasks in the list." );
    }

    public void addOtherTask(String task) {
        System.out.println(line + "\nadded: " + task);
    }

    public void completeTask(Task task) {
        System.out.println(line + "\nNice! I have marked this task as done: \n  "
                + task.toString());
    }

    public void deleteTask(Task task, List<Task> list) {
        System.out.println(line + "\nNoted. I've removed this task:\n  " + task.toString()
                + "\nNow you have " + list.size() + " tasks in the list.");
    }

    public void endDuke() {
        System.out.println(line + "\nBye. Hope to see you again soon!\n" + line);
    }

    public void errorLoading() {
        System.out.println("FAILURE: Unable to load data from local drive.");
    }

    public void printError(String error) {
        System.out.println(error);
    }

}
