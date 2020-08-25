package duke;

import java.util.List;
import java.util.Scanner;

import duke.task.Task;

public class Ui {
    
    private final Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public String readCommand() {
        return sc.nextLine();
    }

    public void showWelcomeMessage() {
        System.out.println("Hello! I'm Duke.");
        System.out.println("What can I do for you?\n");
    }

    public void showExitMessage() {
        System.out.println("Goodbye! Hope to see you again soon!");
    }

    public void showAddMessage(Task task, int numTasks) {
        System.out.println("Okay! Task added for you!");
        System.out.println(task);
        System.out.println("Now you have " + numTasks + " task(s) in the list." + "\n");
    }

    public void showDeleteMessage(Task task, int numTasks) {
        System.out.println("Noted. The following task is removed:");
        System.out.println(task);
        System.out.println("Now you have " + numTasks + " task(s) in the list." + "\n");
    }

    public void showDoneMessage(Task task) {
        System.out.println("Good job! I've marked this task as done:");
        System.out.println(task + "\n");
    }

    public void showErrorMessage(String message) {
        System.out.println(message);
    }

    public void displayTasks(List<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("No tasks added to your list yet!\n");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println(i + 1 + ". " + tasks.get(i));
            }
            System.out.println("");
        }
    }
}