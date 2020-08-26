import java.io.IOException;

/**
 * A Ui wrapper to handle user inputs and outputs.
 */
public class Ui {
    /**
     * Reads user input from standard in.
     *
     * @param parser The Parser instance used to parse user input.
     * @throws IOException if an IO exception occurs while reading standard in.
     */
    static void readUserInput(Parser parser) throws IOException {
        parser.scan();
    }

    /**
     * Displays a welcome message when Duke starts.
     */
    static void showWelcomeMessage() {
        System.out.println("Hi I'm Duke, your personal task-tracker bot!");
        System.out.println("You can add todos, deadlines, or events to my " +
                                   "list.");
    }

    /**
     * Displays a goodbye message when the user quits Duke.
     */
    static void showGoodbyeMessage() {
        System.out.println("Bye. Hope to see you again");
    }

    /**
     * Displays a message when an existing save has been found and loaded.
     */
    static void showSuccessfulLoad() {
        System.out.println("Your existing task list has been retrieved from disk.");
    }

    /**
     * Displays a message when no existing save has been found.
     */
    static void showNoExistingSave() {
        System.out.println("You don't have an existing saved task list.");
    }

    /**
     * Displays a message when a task has been successfully added.
     *
     * @param task The Task instance that has been added.
     */
    static void showSuccessfulAdd(Task task) {
        System.out.println("added: " + task);
    }

    /**
     * Displays a message when the task list is successfully saved.
     */
    static void showSuccessfulSave() {
        System.out.println("Alright, your list has been saved!");
    }

    /**
     * Displays an error message when an exception occurs.
     *
     * @param exception The Exception instance that has been thrown.
     */
    static void showErrorMessage(Exception exception) {
        System.out.println(exception.getMessage());
    }

    /**
     * Displays an error message with a custom message prepended.
     *
     * @param customMessage The string to prepend.
     * @param exception The Exception instance that has been thrown.
     */
    static void showErrorMessage(String customMessage, Exception exception) {
        System.out.println(customMessage + exception.getMessage());
    }

    /**
     * Displays a confirmatory message when a task has been successfully removed.
     *
     * @param task The Task instance that has been removed from the task list.
     */
    static void showSuccessfulRemoval(Task task) {
        System.out.println("This task has been removed: " + task);
    }

    /**
     * Displays a message when a task has been successfully marked as done.
     *
     * @param task The Task instance that has been marked as done.
     */
    static void showMarkedAsDone(Task task) {
        System.out.println("I've marked this task as done:\n" + task);
    }
}
