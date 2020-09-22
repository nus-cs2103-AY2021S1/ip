package ui;

import task.Task;

import java.util.Scanner;
import java.util.ArrayList;

/**
 * Ui class handles any text or String representations to be viewed by the user.
 *
 * @author Hakiem Rasid
 */
public class Ui {

    private static final String HORIZONTAL_LINE =
            "______________________________________________________";


    /**
     * Returns and prints start-up message upon program execution.
     * @return Start-up message.
     */
    public static String startUpMessage() {
        StringBuilder sb = new StringBuilder();
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        sb.append("Hello from\n" + logo);
        sb.append(Ui.HORIZONTAL_LINE + "\n");
        sb.append("Hello I'm Duke\nWhat can I do for you?");
        System.out.println(sb.toString());
        return sb.toString();
    }

    public static String chatStartMessage() {
        return Ui.horizontalLine() + "\n" +
                "Hello I'm Duke\nWhat can I do for you?\n" + Ui.horizontalLine();
    }

    /**
     * Returns horizontal line
     *
     * @return Horizontal line.
     */
    public static String horizontalLine() {
        return Ui.HORIZONTAL_LINE;
    }

    /**
     *
     */
    /**
     * Returns goodbye message upon exiting program.
     *
     * @return Goodbye message as String;
     */
    public static String byeMessage() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Prints message upon clearing list of Task objects.
     */
    public static String clearedListMessage() {
        return "Task list cleared!";
    }

    /**
     * Prints message upon failing to confirm clearing of list.
     */
    public static void didNotClearListMessage() {
        System.out.println("Did NOT clear your task list! " +
                "Is there anything else?");
    }

    /**
     * Returns message upon successful marking of Task as done.
     *
     * @param task String representation of Task marked as done.
     * @return Message as String.
     */
    public static String markDoneMessage(String task) {
        return ("Nice! I have marked this task as done:\n\t" + task);
    }

    /**
     * Returns message upon successful deletion of specified task and current size
     * of list of Task objects.
     *
     * @param task String representation of Task deleted.
     * @param size Size of list of Task objects after deletion.
     * @return Message as String.
     */
    public static String deleteTaskMessage(String task, int size) {
        StringBuilder sb = new StringBuilder();
        sb.append("Okay! I have removed this task:\n\t" + task + "\n");
        sb.append("Now you have " + size + " tasks in your list");
        return sb.toString();
    }

    /**
     * Returns message upon successful adding of new Task object to list and current
     * size of list of Task objects.
     *
     * @param task String representation of Task added to list.
     * @param size Size of list of Task objects after adding new Task.
     * @return Message as String.
     */
    public static String addTaskMessage(String task, int size) {
        StringBuilder sb = new StringBuilder();
        sb.append("Got it! Task added to list.\n");
        sb.append("\t" + task + "\n");
        sb.append("Now you have " + size + " tasks in your list.");
        return sb.toString();
    }

    /**
     * Returns String representation of all Task objects in the input list.
     * @param tasks List of Task objects.
     * @param printOrFind Indicator if caller is a print or find method.
     * @return List of all Task objects as String.
     */
    public static String printList(ArrayList<Task> tasks, String printOrFind) {
        StringBuilder sb = new StringBuilder();
        if (printOrFind.equals("print")) {
            sb.append("Here are your tasks:\n");
        } else {
            sb.append("Here are your matching tasks:\n");
        }

        for (int i = 0; i < tasks.size(); i++) {
            sb.append(i + 1 + ". " + tasks.get(i).printTask());
            if (i != tasks.size() - 1) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    /**
     * Returns message if FIND command does not return any matching Task objects.
     *
     * @return Message as String.
     */
    public static String noMatchMessage() {
        return ("Sorry! There are not tasks " +
                "that match that description.\n");
    }

    /**
     * Prints message upon reading input of invalid format.
     */
    public static String invalidInputMessage() {
        return "Please enter valid input";
    }

    /**
     * Prints message upon reading DONE, DELETE input command
     * of invalid format.
     */
    public static String invalidIndexMessage() {
        return "Please enter valid index";
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
        System.out.println(horizontalLine());
        String input = sc.nextLine();
        System.out.println(horizontalLine());
        return input.toLowerCase().equals("y");
    }
}