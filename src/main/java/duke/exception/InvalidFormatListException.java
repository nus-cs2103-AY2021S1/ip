package duke.exception;

public class InvalidFormatListException extends DukeException {
    public InvalidFormatListException() {
        super(" ☹ OOPS! Did you mean list? (Note: There should not be anything behind list)");
    }
}
