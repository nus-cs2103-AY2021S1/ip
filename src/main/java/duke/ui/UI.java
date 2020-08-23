package duke.ui;

import duke.tasks.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class UI {

    private final String HORIZONTAL_BREAK = "____________________________________________________________";
    private final String OFFSET = "     ";
    private final String ITEM_OFFSET = "       ";
    private final String BLANK_LINE = "";

    private Scanner scanner;

    public UI() {
        this.scanner = new Scanner(System.in);
    }

    public void showLine() {
        System.out.println(OFFSET + HORIZONTAL_BREAK);
    }

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

    public void displayGoodbye() {
        showLine();
        System.out.println(OFFSET + "Bye! Hope to see you again :)");
        showLine();
    }

    public void showLoadingError(String s) {
        System.out.println(OFFSET + "Looks like there was an error retrieving your data");
        System.out.println(OFFSET + s);
    }

    public void showError(String s) {
        this.showLine();
        System.out.println(OFFSET + s);
        this.showLine();
    }

    public void displayAddedTask(Task task, int listSize) {
        this.showLine();
        System.out.println(OFFSET + "Got it. I've added this task:");
        System.out.println(ITEM_OFFSET + task);
        System.out.println(OFFSET + "Now you have " + listSize + " tasks in the list.");
        this.showLine();
    }

    public void displayDeletedTask(Task task, int listSize) {
        this.showLine();
        System.out.println(OFFSET + "Noted. I've removed this task:");
        System.out.println(ITEM_OFFSET + task);
        System.out.println(OFFSET + "Now you have " + listSize + " tasks in the list.");
        this.showLine();
    }

    public void displayDoneTask(Task task) {
        this.showLine();
        System.out.println(OFFSET + "Nice! I've marked this task as done:");
        System.out.println(ITEM_OFFSET + task);
        this.showLine();
    }

    public void displayEventsOnDate(ArrayList<Task> tasks, LocalDate localDate) {
        this.showLine();
        System.out.println(OFFSET + "Here are your events on " +
                localDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ":");
        for (Task task : tasks) {
            System.out.println(ITEM_OFFSET + task);
        }
        this.showLine();
    }

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

    public void displayBlankLine() {
        System.out.println(BLANK_LINE);
    }

    public void showHelp() {
        System.out.println(OFFSET + "I can't help you either ._.");
    }

    public String readCommand() {
        return scanner.nextLine();
    }



}
