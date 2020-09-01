package duke.exception;

public class InvalidTodoException extends InvalidTaskException {
    /**
     * Signals that the todo task provided by the user is formatted incorrectly.
     */
    public InvalidTodoException() {
        super("Please input the correct details for the event task.\n"
                + "todo *description*");
    }
}
