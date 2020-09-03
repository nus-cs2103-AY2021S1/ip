package duke.ui;

import java.util.ArrayList;
import java.util.Scanner;

import duke.task.Task;
import duke.task.TaskList;


/**
 * Encapsulates the user interface for Duke.
 */
public class Ui {
    private static final String LINE_ADD_TASK = ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>";
    private static final String LINE_BYE = "========================================================";
    private static final String LINE_DONE_TASK = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
    private static final String LINE_LIST_TASK = "________________________________________________________";
    private static final String INDENT = "    ";

    private Scanner scanner;

    /**
     * Creates a Duke UI instance and starts the scanner.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Prints out the welcome message for the user when Duke is booted up.
     */
    public String displayWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        return "Hello my name\n" + logo + "\nHow may I help?";
    }

    /**
     * Prints out the list of the user's tasks.
     *
     * @param tasks TaskList of user's tasks.
     */
    public String displayTaskList(TaskList tasks) {
        ArrayList<Task> list = tasks.getList();
        String output = "Here are your tasks:\n";
        for (Task task : list) {
            output += INDENT + (list.indexOf(task) + 1) + "." + task.toString() + "\n";
        }
        if (list.size() == 0) {
            output += INDENT + "None";
        }
        return output;
    }

    /**
     * Prints the list of matching tasks according to find keyword.
     *
     * @param tasks List of matching tasks.
     */
    public String displayMatchingTaskList(TaskList tasks) {
        ArrayList<Task> list = tasks.getList();
        String output = "Duke has found these tasks in your list:\n";
        for (Task task : list) {
            output += INDENT + (list.indexOf(task) + 1) + "." + task.toString() + "\n";
        }
        if (list.size() == 0) {
            output += INDENT + "None";
        }
        return output;
    }


    /**
     * Prints out a message to confirm that the user has marked a task as done.
     *
     * @param task Task marked as done by user.
     */
    public String displayDoneMessage(Task task) {
        return "The following task has been marked as done:\n"
            + INDENT + task.toString();
    }

    /**
     * Prints out a message to confirm that the user has deleted a task.
     *
     * @param task Task deleted by user.
     * @param taskCount Updated number of tasks in the user's task list.
     */
    public String displayDeletedTaskMessage(Task task, int taskCount) {
        return "The following task has been removed:\n"
            + INDENT + task.toString() + "\n"
            + "You now have " + taskCount + " task(s) in the list.";
    }

    /**
     * Prints out a message to confirm that the user has added a task to the list.
     *
     * @param task Task added by user.
     * @param taskCount Updated number of tasks in the user's task list.
     */
    public String displayAddTaskSuccess(Task task, int taskCount) {
        return "Added task:" + "\n"
            + INDENT + task.toString() + "\n"
            + "You now have " + taskCount + " task(s) in the list.";
    }

    /**
     * Prints out an error message when an Duke encounters an error.
     *
     * @param errorMessage Error message.
     */
    public String displayError(String errorMessage) {
        return errorMessage;
    }

    /**
     * Prints out a goodbye message when the user exits Duke.
     */
    public String displayGoodbye() {
        return "Goodbye";
    }

    /**
     * Reads user input and returns a String representing the input.
     *
     * @return String of user input
     */
    public String readInput() {
        return scanner.nextLine();
    }

    /**
     * Closes the scanner when the user exits Duke.
     */
    public void exit() {
        scanner.close();
    }
}
