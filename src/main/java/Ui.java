import java.util.List;

/**
 * Deals with interactions with the user.
 */
public final class Ui {
    /**
     * Shows welcome message.
     */
    public void showWelcomeMessage() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    /**
     * Shows bye message.
     */
    public void showByeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void showListItems(List<Task> lst, String action) {
        System.out.println(String.format("Here are the %s tasks in your list:", action.equals("search") ? "matching" : ""));

        for (int i = 0; i < lst.size(); i++) {
            System.out.println(String.format("%d.%s", i + 1, lst.get(i)));
        }
    }

    public String formatListItems(List<Task> lst, String action) {
        String result = String.format("Here are the %s tasks in your list:\n", action.equals("search") ? "matching" : "");

        for (int i = 0; i < lst.size(); i++) {
            result += String.format("%d.%s\n", i + 1, lst.get(i));
        }

        return result;
    }

    /**
     * Shows add message.
     */
    public void showAddMessage() {
        System.out.println("Got it. I've added this task: ");
    }

    /**
     * Shows done message.
     */
    public void showDoneMessage() {
        System.out.println("Nice! I've marked this task as done:");
    }

    /**
     * Shows delete message.
     */
    public void showDeleteMessage() {
        System.out.println("Noted. I've removed this task:");
    }

    /**
     * Shows error message.
     */
    public void showErrorMessage() {
        System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
    }

    /**
     * Shows wrong input message.
     */
    public void showWrongInputMessage() {
        System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    /**
     * Shows file not found error message.
     */
    public void showDirErrorMessage() {
        System.out.println("Data directory or file not found, see error");
    }

    /**
     * Shows duplicate task message.
     */
    public static void showDuplicateTaskMessage(Task task) {
        System.out.println(String.format("Duplicate task detected: %s", task));
    }
}
