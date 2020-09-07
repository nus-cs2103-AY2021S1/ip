import java.util.ArrayList;
import java.io.IOException;

/**
 * Represents the user interface. This Ui class outputs messages and interacts with the user.
 */
public class Ui {

    /**
     * Creates a new Ui.
     */
    public Ui() { }

    /**
     * Prints the logo of Duke.
     */
    public static void printLogo() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
    }

    /**
     * Greets to the user.
     */
    public static void greet() {
        System.out.println("Hello! I'm Duke\n What can I do for you?");
    }

    /**
     * Farewell to the user.
     */
    public static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Prints the message of adding a task for the user.
     * @param task the task to be added.
     * @param tasks the list where the task to be added in.
     */
    public static void add(Task task, ArrayList<Task> tasks) {
        System.out.println("Got it. I've added this task:\n" + task.toString()
                + "\n Now you have " + tasks.size() + " tasks in the list.");
    }

    /**
     * Prints the message of marking a task as done.
     * @param n the index of the task to be marked as done.
     * @param tasks the list where the task is stored in.
     */
    public static void done(int n, ArrayList<Task> tasks) {
        System.out.println("Nice! I've marked this task as done: \n" + tasks.get(n-1).toString());
    }

    /**
     * Prints the message of deleting a task.
     * @param n the index of the task to be deleted.
     * @param tasks the list where the task to be deleted from.
     */
    public static void delete(int n, ArrayList<Task> tasks) {
        System.out.println("Noted. I've removed this task:\n" + tasks.get(n-1).toString());
    }

    /**
     * Prints the message of counting the number of total tasks.
     * @param tasks the list of tasks to be counted.
     */
    public static void count(ArrayList<Task> tasks) {
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    /**
     * Prints the list of tasks.
     * @param tasks The list of tasks to be printed.
     */
    public static void printList(ArrayList<Task> tasks) {
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i) != null) {
                System.out.println((i + 1) + ". " + tasks.get(i).toString());
            }
        }
    }

    /**
     * Prints the result of tasks that matches the given keyword for the user.
     * @param results The list of matching tasks.
     */
    public void findKeyword(ArrayList<Task> results) {
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < results.size(); i++) {
            if (results.get(i) != null) {
                System.out.println((i + 1) + ". " + results.get(i).toString());
            }
        }
    }

    /**
     * Shows command error for the user while catching duke exceptions.
     * @param e the duke exception caught.
     */
    public static void showCommandError(DukeException e) {
        if (e.getType().equals("EmptyToDo")) {
            System.out.println("OOPS!!! The description of a todo cannot be empty.");
        }
        if (e.getType().equals("EmptyDeadline")) {
            System.out.println("OOPS!!! The description of a deadline cannot be empty.");
        }
        if (e.getType().equals("EmptyEvent")) {
            System.out.println("OOPS!!! The description of a event cannot be empty.");
        }
        if (e.getType().equals("invalid")) {
            System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * Shows loading error for the user while files are not found.
     */
    public static void showLoadingError() {
        System.out.println("File not found");
    }

    /**
     * Shows error for the user while catching IOExceptions.
     * @param e the io exception caught.
     */
    public static void showIOException(IOException e) {
        System.out.println("Oops! " + e.getMessage());
    }
}
