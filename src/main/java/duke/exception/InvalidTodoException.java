package duke.exception;

public class InvalidTodoException extends InvalidTaskException {
    private static final String ERROR_MESSAGE = "Please input the correct details for the event task.\n"
            + "todo *description*";

    /**
     * Signals that the todo task provided by the user is formatted incorrectly.
     */
    public InvalidTodoException() {
        super(ERROR_MESSAGE);
    }
}
