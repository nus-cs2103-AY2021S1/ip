package duke.ui;

import java.util.Scanner;

import duke.tasks.Task;

/**
 * Represents the ui that interacts with the user.
 * @version 1.0
 */
public class Ui {
    /**
     * Shows a section dividing line.
     */
    public void showLine() {
        System.out.println("----------------------------------------------------------------------------------------");
    }

    /**
     * Shows the welcome interface.
     */
    public void showWelcome() {
        System.out.println("Hello!\nI am Baymax, your personal idle time companion."
                + "\n\tHow may I help you?");
    }

    /**
     * Shows the farewell interface.
     */
    public void showFarewell() {
        System.out.println("List saved!\nIt was my pleasure assisting you.\nSee you next time!");
    }

    /**
     * Shows the list interface.
     */
    public void showList() {
        System.out.println("Here are the tasks in your list:");
    }

    /**
     * Shows the done interface with the specified task.
     *
     * @param task The task specified to be done
     */
    public void showDone(Task task) {
        System.out.println("You have finished " + task + "!\nMove on to the next one:");
    }

    /**
     * Shows add interface with the specified task.
     *
     * @param task The task specified to add.
     */
    public void showAdd(Task task) {
        System.out.println("You have added " + task + "!\nNow you have these tasks:");
    }

    /**
     * Shows delete interface with the specified task.
     *
     * @param task The task specified to delete.
     */
    public void showDelete(Task task) {
        System.out.println(task + " deleted. \nCheck out other tasks:");
    }

    /**
     * Shows date filtered list interface.
     */
    public void showDateFilterList() {
        System.out.println("You have these tasks on this date:");
    }

    /**
     * Shows key filtered list interface.
     *
     * @param key The key used to filter the list.
     */
    public void showFind(String key) {
        System.out.println("You have these tasks containing \"" + key + "\":");
    }

    /**
     * Reads the input from the user.
     *
     * @return The String representation of the input by the user.
     */
    public String readCommand() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    /**
     * Shows the error message.
     *
     * @param error The error message.
     */
    public void showError(String error) {
        System.out.println(error);
    }

    /**
     * Shows loading error.
     */
    public void showLoadingError() {
        System.out.println("Error loading file.\nNew Duke Todo list created!");
    }
}
