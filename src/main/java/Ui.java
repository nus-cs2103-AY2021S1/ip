package main.java;

import java.util.Scanner;

/**
 * The UI deals with interactions with the user
 *
 * @author Lio
 */
public class Ui {
    private Scanner s;

    public Ui() {
        this.s = new Scanner(System.in);
    }

    public void hi() {
        System.out.println("hi");
    }

    public void bye() {
        System.out.println("bye");
    }

    public String readCommand() {
        return s.nextLine();
    }

    public void showError(String message) {
        System.out.println("OOPS!!! " + message);
    }

    /**
     * ...
     */
    public void taskAdded(Task task, TaskList tasks) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    /**
     * ...
     */
    public void taskDeleted(Task task, TaskList tasks) {
        System.out.println("Noted. I've removed this task: ");
        System.out.println(task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    /**
     * ...
     */
    public void listTasks(TaskList tasks) {
        System.out.println("Here are the tasks in your list:");
        System.out.println(tasks);
    }

    /**
     * ...
     */
    public void taskDone(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
    }

    /**
     * ...
     */
    public void listFoundTasks(TaskList tasks) {
        System.out.println("Here are the matching tasks in your list:");
        System.out.println(tasks);
    }
}
