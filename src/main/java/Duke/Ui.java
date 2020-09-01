package Duke;

import java.util.Scanner;

public class Ui {
    Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public void showWelcome() {
        System.out.println("Hello! I'm Duke\n" +
                "What can I do for you?");
    }

    public String readLine() {
        return sc.nextLine();
    }

    public void close() {
        this.sc.close();
    }

    public void goodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void showAddTask(Task task) {
        System.out.println("Go it. I've added this task:\n" + task.toString());
    }

    public void showDoneTask(Task task) {
        System.out.println("Nice! I've marked this task as done:\n" + task);
    }

    public void showRemovedTask(Task task) {
        System.out.println("Noted. I've removed this task:\n" + task.toString());
    }

    public void showTotalTasks(int size) {
        String plural = size != 1 ? "tasks" : "task";
        System.out.println("Now you have " + size + " " + plural + " in the list.");
    }

    public void printMessage(String output) {
        System.out.println(output);
    }

    public void showError(String message) {
        System.out.println(message);
    }

}