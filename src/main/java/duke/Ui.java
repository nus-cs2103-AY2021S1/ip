package duke;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Encapsulates the user interactions.
 */
public class Ui {
    /**
     * Instantiates Ui.
     */
    Ui() {
    }

    /**
     * Reads the user input.
     *
     * @param sc Scanner object to read user input.
     * @return String of the user input.
     */
    public String getUserInput(Scanner sc) {
        return sc.nextLine();
    }

    /**
     * Prints welcome message for user.
     */
    public void showGreeting() {
        System.out.println("Hello! I'm Duke" + "\n" + "What can I do for you?");
    }

    /**
     * Prints loading error message.
     */
    public void showLoadingError() {
        System.out.println("Sorry, an error occurred while loading the data");
    }

    /**
     * Prints exit message.
     */
    public void showExitMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Prints the list of tasks.
     *
     * @param tasks TaskList to be printed.
     */
    public void showTasksList(TaskList tasks) {
        int index = 1;
        ArrayList<Task> tasksList = tasks.getTasksList();
        System.out.println("Here are the tasks in your list:");
        for (Task t : tasksList) {
            System.out.println(index + ". " + t);
            index++;
        }
    }

    /**
     * Prints the task added.
     *
     * @param task Task added.
     * @param size Size of the updated TaskList.
     */
    public void showAddTask(Task task, int size) {
        String taskText = "Got it. I've added this task:" + "\n" + task + "\n";
        String totalText = "Now you have " + size + " tasks in the list";
        System.out.println(taskText + totalText);
    }

    /**
     * Prints the task deleted.
     *
     * @param task Task deleted.
     * @param size Size of the updated TaskList.
     */
    public void showDeleteTask(Task task, int size) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.println("Now you have " + size + " tasks in the list");
    }

    /**
     * Prints the completed Task.
     *
     * @param task Task completed.
     */
    public void showTaskDone(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task.toString());
    }

    /**
     * Prints line break.
     */
    public void lineBreak() {
        System.out.println("---");
    }

    /**
     * Print error message.
     *
     * @param message Error message.
     */
    public void showError(String message) {
        System.out.println(message);
    }

    public void showTasksFound(TaskList tasks) {
        System.out.println("Here are the matching tasks in your list:");
        int index = 1;
        ArrayList<Task> tasksList = tasks.getTasksList();
        for (Task t : tasksList) {
            System.out.println(index + ". " + t);
            index++;
        }
    }
}
