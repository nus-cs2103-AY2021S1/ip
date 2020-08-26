import java.io.IOException;
import java.util.Scanner;

public class Ui {
    public Scanner sc;

    public static String horizontal = "________________________________" + "\n";

    public Ui() {
        sc = new Scanner(System.in);
    }

    public void welcome() {
        String hello = "Hello! I'm Duke" + "\n" + "What can I do for you?" + "\n";
        System.out.println(horizontal + hello + horizontal);
    }

    public void printBye() {
        String bye = "Bye. Hope to see you again soon!" + "\n";
        System.out.println(horizontal + bye + horizontal);
    }

    public void printDone(Task task) {
        System.out.println(horizontal + "Nice! I've marked this task as done:" + "\n" +
                task.toString() + "\n" + horizontal);
    }

    public void printDelete(TaskList taskList, Task task) {
        System.out.println(horizontal + "Noted. I've removed this task:" + "\n" +
                task.toString() + "\n" + "Now you have " + taskList.size() + " tasks in the list." +
                "\n" + horizontal);
    }

    public void printList(TaskList taskList) {
        System.out.println(horizontal + "Here are the tasks in your list:" + "\n");
        for (int i = 1; i <= taskList.size(); i++) {
            Task task = taskList.getTask(i);
            System.out.println(i + "." + task.toString());
        }
        System.out.println(horizontal);
    }

    public void printAdd(TaskList taskList, Task task) {
        System.out.println(horizontal + "Got it. I've added this task:" + "\n" + task.toString() + "\n" +
                "Now you have " + taskList.size() + " tasks in the list." + "\n" + horizontal);
    }

    public void printDukeError(DukeException e) {
        System.out.println(e.getMessage() + "\n");
    }

    public void printIOError(IOException e) {
        System.out.println(e.getMessage() + "\n");
    }

    public void printHasCreated(Boolean b) {
        if (b) {
            System.out.println("New file created" + "\n");
        } else {
            System.out.println("Failed to create file");
        }

    }

    public void printSaved(TaskList taskList) {
        if (taskList.size() > 0) {
            System.out.println("Here are your saved tasks: \n");
            for (int i = 1; i <= taskList.size(); i++) {
                Task task = taskList.getTask(i);
                System.out.println(i + ". " + task.toString());
            }
        } else {
            System.out.println("You have no tasks.");
        }
        System.out.println(horizontal);
    }
}
