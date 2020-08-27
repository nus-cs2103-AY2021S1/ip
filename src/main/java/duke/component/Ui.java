package duke.component;

import duke.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private static final String INDENT = "  ";
    Scanner scan;

    public Ui () {
        this.scan = new Scanner(System.in);
    }

    public void showWelcome() {
        System.out.println("Hello! I'm duke.Duke\n" + "What can I do for you?");
    }

    public void showBye() {
        System.out.println("Bye!!! Hope to see you again real soon.");
    }

    public void showDone() {
        System.out.println("The following task has been marked done: ");
    }

    public void showLine() {
        System.out.println("_____________________________________________________");
    }

    public void showError(String e) {
        System.out.println(e);
    }

    public void listAllTasks(ArrayList<Task> list) {
        int LENGTH_OF_LIST = list.size();
        if (LENGTH_OF_LIST > 0) {
            int counter = 1;
            for (Task task : list) {
                System.out.println("  " + counter + "." + task);
                counter++;
            }
        } else {
            System.out.println("No tasks found, add a task now!");
        }
    }

    public void addMessage(Task task, int tasksLeft) {
        System.out.println("Got it, the following task has been added:\n" + INDENT + INDENT + task +
                "\n" + INDENT + this.taskLeftMessage(tasksLeft));
    }

    public void deleteMessage(Task task, int tasksLeft) {
        System.out.println("Noted. I have removed this task:\n" + INDENT + INDENT + task + "\n" + this.taskLeftMessage(tasksLeft));
    }

    public String taskLeftMessage(int tasksLeft) {
        String end = "No tasks found. Add a task now!";
        if (tasksLeft > 0) {
            end = "Now you have " + tasksLeft + " tasks in the list.";
        }
        return end;
    }



    public void print(String s) {
        System.out.println(s);
    }

    public String getStatusIcon(Task task) {
        return ("[" + (task.getIsDone() ? "\u2713" : "\u2718") + "] ");
    }

    public String readCommand() {
        return scan.nextLine();
    }
}
