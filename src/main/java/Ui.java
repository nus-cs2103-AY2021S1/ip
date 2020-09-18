import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents a user interface (UI) to deal with interactions with the user.
 */
public class Ui {

    /**
     * Shows a welcome message.
     * @return welcome message.
     */
    public static String showWelcome() {
        return "Hello! I'm Duke\n"
                + "What can I do for you?";
    }

    /**
     * Shows a message to indicate that a task has been marked as completed.
     * @param task task which has been marked as completed.
     * @return completed message with an uplifting quote.
     */
    public String showDoneMessage(Task task) {
        return "Nice! I've marked this task as done:\n"
                + task
                + "\nThose who are crazy enough to think that they can "
                + "change the world are the ones who usually do. Dream big!";
    }

    /**
     * Shows a message with all the filtered tasks.
     * @param tasks filtered tasks.
     * @return filtered tasks message.
     */
    public String showFindMessage(ArrayList<Task> tasks) {
        String findMessage = "Here are the matching tasks in your list:\n";
        for (int i = 1; i <= tasks.size(); i++) {
            findMessage = findMessage + i + ". " + tasks.get(i - 1) + "\n";
        }
        return findMessage;
    }

    /**
     * Shows a message to indicate that a task has been deleted.
     * @param task task which has been deleted.
     * @param totalTasks total number of tasks left after deletion.
     * @return deleted message.
     */
    public String showDeletedMessage(Task task, int totalTasks) {
        return "Noted. I've removed this task:\n"
                + task
                + "\nNow you have " + totalTasks + " tasks in the list.";
    }

    /**
     * Shows a message to indicate that a task has been added.
     * @param task task which has been added.
     * @param totalTasks total number of tasks left after addition.
     * @return
     */
    public String showAddedMessage(Task task, int totalTasks) {
        return "Got it. I've added this task:\n"
                + task
                + "\nNow you have " + totalTasks + " tasks in the list.";
    }

    /**
     * Shows a message to indicate the list of all tasks.
     * @param tasks list of all tasks.
     * @return list message.
     */
    public String showListMessage(ArrayList<Task> tasks) {
        String listMessage = "Here are the tasks in your list:\n";
        for (int i = 1; i <= tasks.size(); i++) {
            listMessage = listMessage + i + ". " + tasks.get(i - 1) + "\n";
        }
        return listMessage;
    }

    /**
     * Shows a message to indicate that the task being added is a duplicate.
     * @return duplicate task message.
     */
    public String showDuplicateMessage() {
        return "This task has already been added to your list!";
    }

    /**
     * Shows  message to say goodbye to the user with an uplifting quote.
     * @return goodbye message.
     */
    public String showGoodbyeMessage() {
        return "Bye. Hope to see you again soon!\n"
                + "Those who are crazy enough to think that they can "
                + "change the world are the ones who usually do. Dream big!";
    }

    /**
     * Shows a message to show the error encountered.
     * @param errorMessage error message to be printed.
     * @return error message.
     */
    public String showError(String errorMessage) {
        return errorMessage;
    }

    /**
     * Shows a message to indicate an error in loading the file containing saved tasks.
     * @return loading error message.
     */
    public String showLoadingError() {
        return "File loading error!";
    }
}
