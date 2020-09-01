/**
 * Ui of the application.
 */
public class Ui {
    /**
     * Returns the welcome message on application start.
     */
    public String showWelcomeMessage() {
        String message = "Hello! I'm Duke\n"
                + "What can I do for you?\n";
        return message;
    }

    /**
     * Returns the exit message on application termination.
     */
    public String showExitMessage() {
        String message = "Bye. Hope to see you again soon!";
        return message;
    }

    /**
     * Returns the result of a command execution.
     * @param result contains a String message after a command has been executed
     */
    public String showResponse(CommandResult result) {
        return result.responseToUser;
    }
}
