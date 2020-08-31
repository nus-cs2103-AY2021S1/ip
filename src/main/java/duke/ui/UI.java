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
     * Returns the display greeting.
     * @return Display greeting as a String.
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
     * Returns the goodbye message.
     * @return Goodbye message as a String.
     */
    public String displayGoodbye() {
        return "Bye! Hope to see you again :)";
    }

    /**
     * Returns a loading error.
     * @param s Error message to be printed.
     * @return Error message as a String.
     */
    public String showLoadingError(String s) {
        return "Looks like there was an error retrieving your data\n"
                       + s;
    }

    /**
     * Returns an error message.
     * @param s Error message to be printed.
     * @return Error message as a String.
     */
    public String showError(String s) {
        return s;
    }

    /**
     * Returns the added task.
     *
     * @param task Task that was added.
     * @param listSize Size of the list of tasks.
     * @return Task added message as a String.
     */
    public String displayAddedTask(Task task, int listSize) {
        return "Got it. I've added this task:\n"
                       + task + "\n" + "Now you have \n"
                       + listSize + " tasks in the list.";

    }

    /**
     * Returns the deleted task.
     *
     * @param task Task that was deleted.
     * @param listSize Size of the list of tasks.
     * @return Task deleted message as a String.
     */
    public String displayDeletedTask(Task task, int listSize) {
        return "Noted. I've removed this task:\n" + task + "\n" + "Now you have " + listSize + " tasks in the list.";
    }

    /**
     * Returns the done task.
     *
     * @param task Task that was set as done.
     * @return Task done message as a String.
     */
    public String displayDoneTask(Task task) {
        return "Nice! I've marked this task as done:\n" + task;
    }

    /**
     * Returns all tasks that are due on the given date.
     *
     * @param tasks List of current tasks.
     * @param localDate Due date.
     * @return Tasks with corresponding due date as a String.
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
     * Returns all items currently in the list.
     *
     * @param tasks List of current tasks.
     * @return All tasks in the list as a String.
     * */
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
     * Returns help information.
     * @return Help information as a String.
     */
    public String showHelp() {
        return "Welcome to Duke! these are the commands you can use: \n"
                       + "--help : Displays help information\n"
                       + "\n"
                       + "todo <task description> : Adds task as Todo item\n"
                       + "\n"
                       + "deadline <task description> /by <due date> : Adds task as Deadline\n"
                       + "\n"
                       + "event <task description> /at <event date> : Adds task as Event\n"
                       + "\n"
                       + "delete <number> : Deletes item with index <number>\n"
                       + "\n"
                       + "done <number> : Marks item at index <number> as done\n"
                       + "\n"
                       + "list : Lists all items currently being tracked\n"
                       + "\n"
                       + "getEvents <date> : Retrieves all tasks with the corresponding date\n"
                       + "\n"
                       + "search <keyword1 keyword2....> : Searches for all tasks matching the keywords\n"
                       + "\n"
                       + "bye : Exits the program";
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
