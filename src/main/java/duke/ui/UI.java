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

    private static final String HORIZONTAL_BREAK = "____________________________________________________________";
    private static final String OFFSET = "     ";
    private static final String ITEM_OFFSET = "       ";
    private static final String BLANK_LINE = "";

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
        System.out.println(UI.OFFSET + UI.HORIZONTAL_BREAK);
    }

    /**
     * Prints the display greeting to the screen.
     */
    public String displayGreeting() {
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
        return "I am duke, keeper of all tasks. How may I help you?";
    }

    /**
     * Prints the goodbye message to the screen.
     */
    public String displayGoodbye() {
        return "Bye! Hope to see you again :)";
    }

    /**
     * Prints a loading error to the screen.
     * @param s Error message to be printed.
     */
    public String showLoadingError(String s) {
        return "Looks like there was an error retrieving your data\n"
                       + s;
    }

    /**
     * Prints an error to the screen.
     * @param s Error message to be printed.
     */
    public String showError(String s) {
        return s;
    }

    /**
     * Prints the added task to the screen.
     *
     * @param task Task that was added.
     * @param listSize Size of the list of tasks.
     */
    public String displayAddedTask(Task task, int listSize) {
        return "Got it. I've added this task:\n"
                       + task + "\n" + "Now you have \n"
                       + listSize + " tasks in the list.";

    }

    /**
     * Prints the deleted task to the screen.
     *
     * @param task Task that was deleted.
     * @param listSize Size of the list of tasks.
     */
    public String displayDeletedTask(Task task, int listSize) {
        return "Noted. I've removed this task:\n" + task + "\n" + "Now you have " + listSize + " tasks in the list.";
    }

    /**
     * Prints the done task to the screen.
     *
     * @param task Task that was set as done.
     */
    public String displayDoneTask(Task task) {
        return "Nice! I've marked this task as done:\n" + task;
    }

    /**
     * Prints all tasks that are due on the given date.
     *
     * @param tasks List of current tasks.
     * @param localDate Due date.
     */
    public String displayEventsOnDate(ArrayList<Task> tasks, LocalDate localDate) {
        String answer = "Here are your events on "
                + localDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ":\n";
        for (Task task : tasks) {
            answer += task + "\n";
        }
        return answer;
    }

    /**
     * Prints all items to the screen.
     *
     * @param tasks List of current tasks.
     */
    public String displayAllItems(ArrayList<Task> tasks) {
        String result = "Here are the tasks in your list:\n";
        int taskCount = 1;
        for (Task task : tasks) {
            result += (taskCount) + ". " + task + "\n";
            taskCount++;
        }
        if (tasks.size() == 0) {
            return "This is a very empty list... UwU";
        }
        return result;
    }

    /**
     * Displays blank line on screen.
     */
    public void displayBlankLine() {
        System.out.println(UI.BLANK_LINE);
    }

    /**
     * Prints help information to the screen.
     */
    public String showHelp() {
        return "I can't help you either ._.";
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
