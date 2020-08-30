package duke.misc;

import java.util.List;
import java.util.Scanner;

public class Ui {
    private static Scanner sc;

    private static String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    /**
     * Wraps the message inside a box.
     *
     * @param printMessage an action that print something to the user interface
     */
    public static void wrap(Runnable printMessage) {
        System.out.println();
        System.out.println("++++++++++++++++++++++++++++++++++++++++++");
        printMessage.run();
        System.out.println("------------------------------------------");
        System.out.println();
    }


    /**
     * Greet the user and create a Scanner object.
     */
    public static void start() {
        wrap(() -> System.out.println("Hello from\n" + logo));
        sc = new Scanner(System.in);
    }

    /**
     * Get the next line of user input.
     *
     * @return next line of user input
     */
    public static String feed() {
        return sc.nextLine();
    }

    /**
     * Close the Scanner object and farewell the user.
     */
    public static String bye() {
        return ("See you again!\n");
    }

    public static void close() {
        sc.close();
    }

    /**
     * Prints the response of the LIST command
     *
     * @param tasks list of tasks to be printed
     */
    public static String list(List<String> tasks) {
        String response = "Here are the tasks in your list: \n";
        List<String> output = tasks;
        for (String s : output) {
            response += (s + "\n");
        }
        return response;
    }

    public static String find(List<String> tasks) {
        String response = "Here are the matching tasks in your list: \n";
        List<String> output = tasks;
        for (String s : output) {
            response += (s + "\n");
        }
        return response;
    }

    /**
     * Prints the response of the DONE command.
     *
     * @param task the task that is marked as finished
     */
    public static String done(String task) {
        return "Nice! I've marked this task as done:\n"
                + "    " + task + "\n";
    }

    /**
     * Prints the response of the DELETE command.
     *
     * @param task  the task deleted
     * @param count the number of tasks left in the list.
     */
    public static String delete(String task, int count) {
        return "Noted. I've removed this task: \n"
                + "    " + task + "\n"
                + "Now you have " + count + " tasks in the list. \n";
    }

    /**
     * Prints the response of the CLEAR command.
     */
    public static String clear() {
        return "All tasks cleared!\n";
    }

    /**
     * Prints the response of the commands that create a task.
     *
     * @param task  the task created
     * @param count the current number of tasks in the list
     */
    public static String task(String task, int count) {
        return "Got it. I've added this task: \n"
                + "    " + task + "\n"
                + "Now you have " + count + " tasks in the list. \n";
    }
}
