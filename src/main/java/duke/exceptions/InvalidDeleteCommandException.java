package duke.exceptions;

public class InvalidDeleteCommandException extends DukeException {

    public InvalidDeleteCommandException() {
        super("\u2639 OOPS!!! Please mark a valid item for deletion");
    }
}
