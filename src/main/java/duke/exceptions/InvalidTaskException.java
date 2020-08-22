package duke.exceptions;

public class InvalidTaskException extends DukeException {

    public InvalidTaskException() {
        super("OOPS! Invalid task found.");
    }
}
