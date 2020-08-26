package duke.exception;

// Signals that the todo task provided by the user is formatted incorrectly.
public class InvalidTodoException extends InvalidTaskException {
    public InvalidTodoException() {
        super(
            "Please input the correct details for the event task.\n" +
            "todo *description*"
        );
    }
}
