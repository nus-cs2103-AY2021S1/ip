/**
 * A class responsible for interactions with the user and showing success/failure messages.
 */

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {

    public Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Prints out the given message.
     * @param message
     */
    public void printMessage(String message) {
        System.out.println(message);
    }

    /**
     * Reads the next line from the scanner.
     * @return the next line
     */
    public String readInput() {
        return sc.nextLine();
    }

    /**
     * Prints the loading error message.
     */
    public void showLoadingError() {
        printMessage("Unable to load tasks");
    }

    /**
     * Prints the welcome message for the user.
     */
    public void welcome() {
        printMessage("Wazzup! I am Duke the Nuke \uD83D\uDE08\n"
                + "What do you want?");
    }

    /**
     * Prints the exit message for the user.
     */
    public void exit() {
        printMessage("Sayonara!");
    }

    /**
     * Prints the "invalid input" error message.
     */
    public void invalidInput() {
        printMessage("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    /**
     * Prints the addTask message.
     */
    public void addTask() {
        printMessage("Got it. I've added this task:");
    }

    /**
     * Prints the removeTask message.
     */
    public void removeTask() {
        printMessage("Noted. I've removed this task:");
    }

    /**
     * Prints the doneTask message.
     */
    public void doneTask() {
        printMessage("Nice! I've marked this task as done:");
    }

    /**
     * Prints the showList message.
     */
    public void showList() {
        printMessage("Here are the tasks in your list:");
    }

    /**
     * Prints the statement with the number of tasks.
     * @param tasks the list containing the tasks.
     */
    public void showNumberOfTasks(ArrayList<Task> tasks) {
        printMessage("Now you have " + tasks.size() + " tasks in the list.");
    }

    /**
     * Prints the matchingTasks message.
     */
    public void printMatchingTasks() {
        printMessage("Here are the matching tasks in your list:");
    }
}


