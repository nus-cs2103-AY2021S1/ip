import java.util.Scanner;
import java.util.ArrayList;

/**
 * Ui class handles any text or String representations to be viewed by the user.
 *
 * @author Hakiem Rasid
 */
public class Ui {

    private static final String HORIZONTAL_LINE =
            "_________________________________________________________________________________________";

    /**
     * Prints start-up message upon program execution.
     */
    public static void startUpMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        horizontalLine();
        System.out.println("Hello I'm Duke\nWhat can I do for you?");
        //horizontalLine();
    }

    /**
     * Prints a horizontal line.
     */
    public static void horizontalLine() {
        System.out.println(Ui.HORIZONTAL_LINE);
    }

    /**
     * Prints goodbye message upon exiting program.
     */
    public static void byeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Prints message upon clearing list of Task objects.
     */
    public static void clearedListMessage() {
        System.out.println("Task list cleared!");
    }

    /**
     * Prints message upon failing to confirm clearing of list.
     */
    public static void didNotClearListMessage() {
        System.out.println("Did NOT clear your task list! " +
                "Is there anything else?");
    }

    /**
     * Prints message upon successful marking of Task as done.
     * @param task String represetation of Task marked as done.
     */
    public static void markDoneMessage(String task) {
        System.out.println("Nice! I have marked this task as done:\n\t" + task);
    }

    /**
     * Prints message upon successful deletion of specified task and current size
     * of list of Task objects.
     *
     * @param task String representation of Task deleted.
     * @param size Size of list of Task objects after deletion.
     */
    public static void deleteTaskMessage(String task, int size) {
        System.out.println("Okay! I have removed this task:\n\t" + task);
        System.out.println("Now you have " + size + " tasks in your list");
    }

    /**
     * Prints message upon successful adding of new Task object to list and current
     * size of list of Task objects.
     *
     * @param task String representation of Task added to list.
     * @param size Size of list of Task objects after adding new Task.
     */
    public static void addTaskMessage(String task, int size) {
        System.out.println("Got it! Task added to list.");
        System.out.println("\t" + task);
        System.out.println("Now you have " + size + " tasks in your list.");
    }

    /**
     * Prints String representation of all Task objects in the input list.
     *
     * @param tasks List of Task objects.
     */
    public static void printList(ArrayList<Task> tasks) {
        System.out.println("Here are your tasks:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(i + 1 + ". " + tasks.get(i).printTask());
        }
    }

    /**
     * Prints message upon reading input of invalid format.
     */
    public static void invalidInputMessage() {
        System.out.println("Please enter valid input");
    }

    /**
     * Prints message upon reading DONE, DELETE input command
     * of invalid format.
     */
    public static void invalidIndexMessage() {
        System.out.println("Please enter valid index");
    }

    /**
     * Returns a boolean value after user has confirmed or denied a previous
     * instruction.
     *
     * @param sc Scanner object to read inputs.
     * @return  True if user confirms previous instruction. False if otherwise.
     */
    public static boolean promptConfirm(Scanner sc) {
        System.out.println("Are you sure? (Y/N)");
        horizontalLine();
        String input = sc.nextLine();
        horizontalLine();
        return input.toLowerCase().equals("y") ? true : false;
    }
}