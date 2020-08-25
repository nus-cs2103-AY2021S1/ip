/**
 * Ui of the application.
 */
public class Ui {
    /**
     * Prints the welcome message on application start.
     */
    public void showWelcomeMessage() {
        String message = "Hello! I'm Duke\n"
                + "What can I do for you?\n";
        System.out.println(message);
    }

    /**
     * Prints the exit message on application termination.
     */
    public void showExitMessage() {
        String message = "Bye. Hope to see you again soon!";
        System.out.println(message);
    }

    /**
     * Prints the result of a command execution.
     * @param result contains a String message after a command has been executed
     */
    public void showResponse(CommandResult result) {
        System.out.println(result.responseToUser);
    }
}
