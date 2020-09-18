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
    public static String printLogo() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        return logo;
    }

    /**
     * Greets to the user.
     */
    public static String greet() {
        return "Hello! I'm Duke\n What can I do for you?";
    }

    /**
     * Farewell to the user.
     */
    public static String exit() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Prints the message of adding a task for the user.
     * @param task the task to be added.
     * @param tasks the list where the task to be added in.
     */
    public static String add(Task task, ArrayList<Task> tasks) {
        return "Got it. I've added this task:\n" + task.toString()
                + "\n Now you have " + tasks.size() + " tasks in the list.";
    }

    /**
     * Prints the message of marking a task as done.
     * @param n the index of the task to be marked as done.
     * @param tasks the list where the task is stored in.
     */
    public static String done(int n, ArrayList<Task> tasks) {
        return "Nice! I've marked this task as done: \n"
                + tasks.get(n-1).toString();
    }

    /**
     * Prints the message of deleting a task.
     * @param n the index of the task to be deleted.
     * @param tasks the list where the task to be deleted from.
     */
    public static String delete(int n, ArrayList<Task> tasks) {
        return "Noted. I've removed this task:\n"
                + tasks.get(n-1).toString();
    }

    /**
     * Prints the message of counting the number of total tasks.
     * @param tasks the list of tasks to be counted.
     */
    public static String count(ArrayList<Task> tasks) {
        return "Now you have " + tasks.size() + " tasks in the list.";
    }

    /**
     * Prints the list of tasks.
     * @param tasks The list of tasks to be printed.
     */
    public static String printList(ArrayList<Task> tasks) {
        String result = "";
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i) != null) {
                result += (i + 1) + ". " + tasks.get(i).toString() + "\n";
            }
        }
        return result;
    }

    /**
     * Prints the result of tasks that matches the given keyword for the user.
     * @param results The list of matching tasks.
     */
    public String findKeyword(ArrayList<Task> results) {
        String result = "Here are the matching tasks in your list:";
        for (int i = 0; i < results.size(); i++) {
            if (results.get(i) != null) {
                result += (i + 1) + ". " + results.get(i).toString();
            }
        }
        return result;
    }

    /**
     * Shows command error for the user while catching duke exceptions.
     * @param e the duke exception caught.
     */
    public static String showCommandError(DukeException e) {
        assert e.getType() != null;
        String result = "";
        if (e.getType().equals("EmptyToDo")) {
            result = "OOPS!!! The description of a todo cannot be empty.";
        }
        if (e.getType().equals("EmptyDeadline")) {
            result = "OOPS!!! The description of a deadline cannot be empty.";
        }
        if (e.getType().equals("EmptyEvent")) {
            result = "OOPS!!! The description of a event cannot be empty.";
        }
        if (e.getType().equals("invalid")) {
            result = "OOPS!!! I'm sorry, but I don't know what that means :-(";
        }
        return result;
    }

    /**
     * Shows loading error for the user while files are not found.
     */
    public static String showLoadingError() {
        return "File not found";
    }

    /**
     * Shows error for the user while catching IOExceptions.
     * @param e the io exception caught.
     */
    public static String showIOException(IOException e) {
        return "Oops! " + e.getMessage();
    }
}
