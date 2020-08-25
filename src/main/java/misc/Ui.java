package main.java.misc;

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
     * @return next line of user input
     */
    public static String feed() {
        return sc.nextLine();
    }

    /**
     * Close the Scanner object and farewell the user.
     */
    public static void bye() {
        sc.close();
        wrap(() -> System.out.println("See you again!"));
    }

    /**
     * Prints the response of the LIST command
     * @param tasks list of tasks to be printed
     */
    public static void list(List<String> tasks) {
        wrap(() -> {
            System.out.println("Here are the tasks in your list: ");
            List<String> output = tasks;
            for (String s : output) {
                System.out.println(s);
            }
        });
    }

    public static void find(List<String> tasks) {
        wrap(() -> {
            System.out.println("Here are the matching tasks in your list: ");
            List<String> output = tasks;
            for (String s : output) {
                System.out.println(s);
            }
        });
    }

    /**
     * Prints the response of the DONE command.
     * @param task the task that is marked as finished
     */
    public static void done(String task) {
        wrap(() -> {
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("    " + task);
        });
    }

    /**
     * Prints the response of the DELETE command.
     * @param task the task deleted
     * @param count the number of tasks left in the list.
     */
    public static void delete(String task, int count) {
        wrap(() -> {
            System.out.println("Noted. I've removed this task: ");
            System.out.println("    " + task);
            System.out.printf("Now you have %d tasks in the list. \n", count);
        });
    }

    /**
     * Prints the response of the CLEAR command.
     */
    public static void clear() {
        wrap(() -> {
            System.out.println("All tasks cleared!");
        });
    }

    /**
     * Prints the response of the commands that create a task.
     * @param task the task created
     * @param count the current number of tasks in the list
     */
    public static void task(String task, int count) {
        wrap(() -> {
            System.out.println("Got it. I've added this task: ");
            System.out.println("    " + task);
            System.out.printf("Now you have %d tasks in the list. \n", count);
        });
    }
}
