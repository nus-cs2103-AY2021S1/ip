package duke;

import java.util.List;
import java.util.Scanner;

import duke.task.Task;

/**
 * Represents the class that deals with interactions with the user.
 */
public class Ui {

    /**
     * A scanner object to take in user input.
     */
    private final Scanner sc;

    /**
     * Initializes a Ui object with the scanner ready to take in inputs.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Returns the next line of user input.
     *
     * @return The next line of user input.
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Displays the welcome message to the user.
     */
    public void showWelcomeMessage() {
        System.out.println("Hello! I'm Duke.");
        System.out.println("What can I do for you?\n");
    }

    /**
     * Displays the exit message to the user.
     */
    public void showExitMessage() {
        System.out.println("Goodbye! Hope to see you again soon!");
    }

    /**
     * Displays the message when a task is being added.
     *
     * @param task The task to be added.
     * @param numTasks The total number of tasks after adding.
     */
    public void showAddMessage(Task task, int numTasks) {
        System.out.println("Okay! Task added for you!");
        System.out.println(task);
        System.out.println("Now you have " + numTasks + " task(s) in the list." + "\n");
    }

    /**
     * Displays the message when a task is being deleted.
     *
     * @param task The task to be deleted.
     * @param numTasks The total number of tasks after deleting.
     */
    public void showDeleteMessage(Task task, int numTasks) {
        System.out.println("Noted. The following task is removed:");
        System.out.println(task);
        System.out.println("Now you have " + numTasks + " task(s) in the list." + "\n");
    }

    /**
     * Displays the message when a task is being marked as done.
     *
     * @param task The task to be marked as done.
     */
    public void showDoneMessage(Task task) {
        System.out.println("Good job! I've marked this task as done:");
        System.out.println(task + "\n");
    }

    /**
     * Displays the error message when an error occurred.
     *
     * @param message The error message to be displayed.
     */
    public void showErrorMessage(String message) {
        System.out.println(message);
    }

    /**
     * Displays the list of tasks to the user.
     *
     * @param tasks The list of tasks to be displayed.
     */
    public void displayTasks(List<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("No tasks added to your list yet!\n");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println(i + 1 + ". " + tasks.get(i));
            }
            System.out.println();
        }
    }

    public void displayMatchingTasks(List<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("No matching tasks found in your list!\n");
        } else {
            System.out.println("Here are the matching tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println(i + 1 + ". " + tasks.get(i));
            }
            System.out.println();
        }
    }
}