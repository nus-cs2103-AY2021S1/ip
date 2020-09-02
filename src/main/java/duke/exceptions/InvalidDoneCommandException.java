package duke.exceptions;

public class InvalidDoneCommandException extends DukeException {
    public InvalidDoneCommandException() {
        super("\u2639 OOPS!!! Please mark a valid item as done");
    }
}
