package duke.ui;

import duke.task.Task;
import duke.task.TaskList;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Encapsulates the user interface for Duke.
 */
public class Ui {
    private Scanner scanner;
    
    static String ADD_TASK_LINE = ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>";
    static String DONE_TASK_LINE = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
    static String LIST_TASK_LINE = "________________________________________________________";
    static String BYE_LINE = "========================================================";
    static String INDENT = "    ";
    
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Prints out the welcome message for the user when Duke is booted up.
     */
    public void displayWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello my name\n" + logo + "\nHow may I help?");
    }

    /**
     * Prints out the list of the user's tasks. 
     * 
     * @param tasks TaskList of user's tasks.
     */
    public void displayTaskList(TaskList tasks) {
        ArrayList<Task> list = tasks.getList();
        System.out.println(INDENT + LIST_TASK_LINE);
        for (Task task : list) {
            System.out.println(INDENT + (list.indexOf(task) + 1) + "." + task.toString());
        }
        if (list.size() == 0) {
            System.out.println(INDENT + "None");
        }
        System.out.println(INDENT + LIST_TASK_LINE);
    }

    /**
     * Prints out a message to confirm that the user has marked a task as done.
     * 
     * @param task Task marked as done by user.
     */
    public void displayDoneMessage(Task task) {
        System.out.println(
            INDENT + DONE_TASK_LINE + "\n"
            + INDENT + "The following task has been marked as done:\n"
            + INDENT + task.toString()
            + "\n" + INDENT + DONE_TASK_LINE
        );
    }

    /**
     * Prints out a message to confirm that the user has deleted a task.
     * 
     * @param task Task deleted by user.
     * @param taskCount Updated number of tasks in the user's task list.
     */
    public void displayDeletedTaskMessage(Task task, int taskCount) {
        System.out.println(
            INDENT + DONE_TASK_LINE + "\n"
            + INDENT + "The following task has been removed:\n"
            + INDENT + INDENT + task.toString() + "\n"
            + INDENT + "You now have " + taskCount + " task(s) in the list.\n"
            + INDENT + DONE_TASK_LINE
        );
    }

    /**
     * Prints out a message to confirm that the user has added a task to the list.
     * 
     * @param task Task added by user.
     * @param taskCount Updated number of tasks in the user's task list.
     */
    public void displayAddTaskSuccess(Task task, int taskCount) {
        System.out.println(
            INDENT + ADD_TASK_LINE + "\n"
            + INDENT + "Added task:" +"\n"
            + INDENT + INDENT + task.toString() + "\n"
            + INDENT + "You now have " + taskCount + " task(s) in the list.\n"
            + INDENT + ADD_TASK_LINE
        );
    }

    /**
     * Prints out an error message when an Duke encounters an error.
     * 
     * @param errorMessage Error message.
     */
    public void displayError(String errorMessage) {
        System.out.println(INDENT + errorMessage);
    }

    /**
     * Prints out a goodbye message when the user exits Duke.
     */
    public void displayGoodbye() {
        System.out.println(
            INDENT + BYE_LINE + "\n"
            + INDENT + "Goodbye\n"
            + INDENT + BYE_LINE
        );
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
