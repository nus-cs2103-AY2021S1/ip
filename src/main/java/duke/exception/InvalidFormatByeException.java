package duke.exception;

public class InvalidFormatByeException extends DukeException {
    public InvalidFormatByeException() {
        super(" ☹ OOPS! Did you mean bye? (Note: There should not be anything behind bye)");
    }
}
