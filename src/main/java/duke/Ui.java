package duke;

import duke.task.Task;

import java.util.Scanner;

/**
 * The Ui object deals with interactions with the user, including on screen messages and taking in user input.
 */
public class Ui {
    private Scanner scanner;

    /**
     * Initializes the Ui object with a new Scanner object.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Gets the user input.
     *
     * @return User input.
     */
    public String getUserInput() {
        return scanner.nextLine();
    }

    /**
     * Shows the greeting message.
     */
    public void showGreeting() {
        String message = "Eh what's up\n"
                + "What do you want?";
        System.out.println(wrapMessage(message));
    }

    /**
     * Shows the goodbye message.
     */
    public void showGoodbye() {
        String message = "Alright I'll see you around!";
        System.out.println(wrapMessage(message));
    }

    /**
     * Shows the loading file error.
     */
    public void showLoadingError() {
        System.out.println(wrapMessage("Error loading file!"));
    }

    /**
     * Shows the list of tasks in the list.
     *
     * @param tasks Tasks currently in the list.
     */
    public void showList(TaskList tasks) {
        String message = "Here are the tasks in your list:\n";
        for (int i = 0; i < tasks.getSize(); i++) {
            Task task = tasks.getTask(i);
            message += (i + 1)
                    + ". "
                    + task
                    +"\n";
        }
        System.out.println(wrapMessage(message));
    }

    /**
     * Shows the add message when the user adds a task.
     *
     * @param task The added task.
     * @param tasks The current list of tasks.
     */
    public void showAdd(Task task, TaskList tasks) {
        String message = "Got it. I've added this task:\n"
                + task
                + "\n"
                + "Now you have "
                + tasks.getSize()
                + " tasks in the list.";
        System.out.println(wrapMessage(message));
    }

    /**
     * Shows the delete message when the user deletes a task.
     *
     * @param task The deleted task.
     * @param tasks The current list of tasks.
     */
    public void showDelete(Task task, TaskList tasks) {
        String message = "Noted! I've removed this task:\n"
                + task
                + "\n"
                + "Now you have "
                + tasks.getSize()
                + " tasks in the list.";
        System.out.println(wrapMessage(message));
    }

    /**
     * Shows the message when the user marks a task as done.
     *
     * @param task The task that has been marked as done.
     */
    public void showDoneTask(Task task) {
        String message = "Nice! I've marked this task as done:\n"
                + task;
        System.out.println(wrapMessage(message));
    }

    /**
     * Shows the message when the user finds a list of tasks with a keyword.
     *
     * @param tasks The list of tasks containing the keyword.
     */
    public void showFind(TaskList tasks) {
        String message = "Here are the matching tasks in your list:\n";
        for (int i = 0; i < tasks.getSize(); i++) {
            Task task = tasks.getTask(i);
            message += (i + 1)
                    + ". "
                    + task
                    +"\n";
        }
        System.out.println(wrapMessage(message));
    }

    /**
     * Shows the error message.
     *
     * @param message The error message to be shown.
     */
    public void showError(String message) {
        System.out.println(wrapMessage(message));
    }

    /**
     * Wraps the message between top and bottom lines.
     *
     * @param message The message to be wrapped.
     * @return The wrapped message.
     */
    public String wrapMessage(String message) {
        if (message.endsWith("\n")) {
            // If the message ends with a newline, remove the newline
            message = message.substring(0, message.length() - 1);
        }

        String line = "____________________________________________________________\n";
        return line
                + message
                + "\n"
                + line;
    }
}
