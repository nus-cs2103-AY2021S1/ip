package duke.ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import duke.tasks.Task;

/**
 * Represents the User Interface that the user interacts with.
 */
public class UI {

    private final String HORIZONTAL_BREAK = "____________________________________________________________";
    private final String OFFSET = "     ";
    private final String ITEM_OFFSET = "       ";
    private final String BLANK_LINE = "";

    private Scanner scanner;

    /**
     * Creates an instance of a User Interface.
     */
    public UI() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Prints a horizontal line to the screen.
     */
    public void showLine() {
        System.out.println(OFFSET + HORIZONTAL_BREAK);
    }

    /**
     * Prints the display greeting to the screen.
     */
    public void displayGreeting() {
        String logo = "        ___\n"
                + "    . -^   `--,\n"
                + "   /# =========`-_\n"
                + "  /# (--====___====\\\n"
                + " /#   .- --.  . --.|\n"
                + "/##   |  * ) (   * ),\n"
                + "|##   \\    /\\ \\   / |\n"
                + "|###   ---   \\ ---  |\n"
                + "|####      ___)    #|\n"
                + "|######           ##|\n"
                + " \\##### ---------- /\n"
                + "  \\####           (\n"
                + "   `\\###          |\n"
                + "     \\###         |\n"
                + "      \\##         |\n"
                + "       \\###.     .)\n"
                + "         `======/";
        System.out.println(logo);
        showLine();
        System.out.println(OFFSET + "I am duke, keeper of all tasks. How may I help you?");
        showLine();
        displayBlankLine();
    }

    /**
     * Prints the goodbye message to the screen.
     */
    public void displayGoodbye() {
        showLine();
        System.out.println(OFFSET + "Bye! Hope to see you again :)");
        showLine();
    }

    /**
     * Prints a loading error to the screen.
     * @param s Error message to be printed.
     */
    public void showLoadingError(String s) {
        System.out.println(OFFSET + "Looks like there was an error retrieving your data");
        System.out.println(OFFSET + s);
    }

    /**
     * Prints an error to the screen.
     * @param s Error message to be printed.
     */
    public void showError(String s) {
        this.showLine();
        System.out.println(OFFSET + s);
        this.showLine();
    }

    /**
     * Prints the added task to the screen.
     *
     * @param task Task that was added.
     * @param listSize Size of the list of tasks.
     */
    public void displayAddedTask(Task task, int listSize) {
        this.showLine();
        System.out.println(OFFSET + "Got it. I've added this task:");
        System.out.println(ITEM_OFFSET + task);
        System.out.println(OFFSET + "Now you have " + listSize + " tasks in the list.");
        this.showLine();
    }

    /**
     * Prints the deleted task to the screen.
     *
     * @param task Task that was deleted.
     * @param listSize Size of the list of tasks.
     */
    public void displayDeletedTask(Task task, int listSize) {
        this.showLine();
        System.out.println(OFFSET + "Noted. I've removed this task:");
        System.out.println(ITEM_OFFSET + task);
        System.out.println(OFFSET + "Now you have " + listSize + " tasks in the list.");
        this.showLine();
    }

    /**
     * Prints the done task to the screen.
     *
     * @param task Task that was set as done.
     */
    public void displayDoneTask(Task task) {
        this.showLine();
        System.out.println(OFFSET + "Nice! I've marked this task as done:");
        System.out.println(ITEM_OFFSET + task);
        this.showLine();
    }

    /**
     * Prints all tasks that are due on the given date.
     *
     * @param tasks List of current tasks.
     * @param localDate Due date.
     */
    public void displayEventsOnDate(ArrayList<Task> tasks, LocalDate localDate) {
        this.showLine();
        System.out.println(OFFSET + "Here are your events on " +
                localDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ":");
        for (Task task : tasks) {
            System.out.println(ITEM_OFFSET + task);
        }
        this.showLine();
    }

    /**
     * Prints all items to the screen.
     *
     * @param tasks List of current tasks.
     */
    public void displayAllItems(ArrayList<Task> tasks) {
        this.showLine();
        System.out.println(OFFSET + "Here are the tasks in your list:");
        int taskCount = 1;
        for (Task task : tasks) {
            System.out.println(OFFSET + (taskCount) + ". " + task);
            taskCount++;
        }
        if (tasks.size() == 0) {
            System.out.println(OFFSET + "This is a very empty list... UwU");
        }
        this.showLine();
    }

    /**
     * Displays blank line on screen.
     */
    public void displayBlankLine() {
        System.out.println(BLANK_LINE);
    }

    /**
     * Prints help information to the screen.
     */
    public void showHelp() {
        this.showLine();
        System.out.println(OFFSET + "I can't help you either ._.");
        this.showLine();
        System.out.println(this.BLANK_LINE);
    }

    /**
     * Reads the user input.
     *
     * @return User input.
     */
    public String readCommand() {
        return scanner.nextLine();
    }



}
