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
    public void showWelcome() {
        System.out.println("Hello! I'm Duke\n"
                + "What can I do for you?");
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
    public void showDoneMessage(Task task) {
        System.out.println("Nice! I've marked this task as done:\n"
                + task);
    }
    
    public void showFindMessage() {
        System.out.println("Here are the matching tasks in your list:");
    }

    /**
     * Prints a message to indicate that a task has been deleted.
     * @param task task which has been deleted.
     * @param totalTasks total number of tasks left after deletion.
     */
    public void showDeletedMessage(Task task, int totalTasks) {
        System.out.println("Noted. I've removed this task:\n"
                + task
                + "\nNow you have " + totalTasks + " tasks in the list.");
    }

    /**
     * Prints a message to indicate that a task has been added.
     * @param task task which has been added.
     * @param totalTasks total number of tasks left after addition.
     */
    public void showAddedMessage(Task task, int totalTasks) {
        System.out.println("Got it. I've added this task:\n"
                + task
                + "\nNow you have " + totalTasks + " tasks in the list.");
    }

    /**
     * Prints a message to indicate the list of all tasks.
     */
    public void showListMessage() {
        System.out.println("Here are the tasks in your list:");
    }

    /**
     * Prints a message to say goodbye to the user.
     */
    public void showGoodbyeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Prints a message to show the error encountered.
     * @param errorMessage error message to be printed.
     */
    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }

    /**
     * Prints a message to indicate an error in loading the file containing saved tasks.
     */
    public void showLoadingError() {
        System.out.println("File loading error!");
    }

    /**
     * Prints a message containing an uplifting quote.
     */
    public void showUpliftingQuote() {
        System.out.println("Those who are crazy enough to think that they can " +
                "change the world are the ones who usually do. Dream big!");
    }
}
