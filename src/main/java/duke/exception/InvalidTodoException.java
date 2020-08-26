package duke.exception;

public class InvalidTodoException extends InvalidTaskException {
    public InvalidTodoException() {
        super(
            "Please input the correct details for the event task.\n" +
            "todo *description*"
        );
    }
}
