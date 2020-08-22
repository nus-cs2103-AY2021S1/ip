package duke.exceptions;

public class InvalidTaskException extends IllegalArgumentException {

    public InvalidTaskException() {
        super("OOPS! Invalid task found.");
    }
}
