package duke.exceptions;

public class InvalidTaskNumberException extends DukeException {
    private static String errorMsg = "You ain't no task with that number!";

    public InvalidTaskNumberException() {
        super(errorMsg);
    }
}
