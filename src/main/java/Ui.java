import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents a user interface (UI) to deal with interactions with the user.
 */
public class Ui {

    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Prints a welcome message.
     */
    public static String showWelcome() {
        return "Hello! I'm Duke\n"
                + "What can I do for you?";
    }

    /**
     * Returns the user input in a String format upon scanning the system input.
     * @return user input in String format.
     */
    public String readCommand() {
        // scans for the next command (input from user)
        return this.scanner.nextLine();
    }

    /**
     * Prints a message to indicate that a task has been marked as completed.
     * @param task task which has been marked as completed.
     */
    public String showDoneMessage(Task task) {
        return "Nice! I've marked this task as done:\n"
                + task
                + "\nThose who are crazy enough to think that they can "
                + "change the world are the ones who usually do. Dream big!";
    }
    
    public String showFindMessage(ArrayList<Task> tasks) {
        String findMessage = "Here are the matching tasks in your list:\n";
        for (int i = 1; i <= tasks.size(); i++) {
            findMessage = findMessage + i + ". " + tasks.get(i - 1) + "\n";
        }
        return findMessage;
    }

    /**
     * Prints a message to indicate that a task has been deleted.
     * @param task task which has been deleted.
     * @param totalTasks total number of tasks left after deletion.
     */
    public String showDeletedMessage(Task task, int totalTasks) {
        return "Noted. I've removed this task:\n"
                + task
                + "\nNow you have " + totalTasks + " tasks in the list.";
    }

    /**
     * Prints a message to indicate that a task has been added.
     * @param task task which has been added.
     * @param totalTasks total number of tasks left after addition.
     */
    public String showAddedMessage(Task task, int totalTasks) {
        return "Got it. I've added this task:\n"
                + task
                + "\nNow you have " + totalTasks + " tasks in the list.";
    }

    /**
     * Prints a message to indicate the list of all tasks.
     */
    public String showListMessage(ArrayList<Task> tasks) {
        String listMessage = "Here are the tasks in your list:\n";
        for (int i = 1; i <= tasks.size(); i++) {
            listMessage = listMessage + i + ". " + tasks.get(i - 1) + "\n";
        }
        return listMessage;
    }

    public String showDuplicateMessage() {
        return "This task has already been added to your list!";
    }

    /**
     * Prints a message to say goodbye to the user with an uplifting quote.
     */
    public String showGoodbyeMessage() {
        return "Bye. Hope to see you again soon!\n"
                + "Those who are crazy enough to think that they can "
                + "change the world are the ones who usually do. Dream big!";
    }

    /**
     * Prints a message to show the error encountered.
     * @param errorMessage error message to be printed.
     */
    public String showError(String errorMessage) {
        return errorMessage;
    }

    /**
     * Prints a message to indicate an error in loading the file containing saved tasks.
     */
    public String showLoadingError() {
        return "File loading error!";
    }
}
