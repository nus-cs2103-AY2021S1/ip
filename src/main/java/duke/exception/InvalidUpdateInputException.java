package duke.exception;

public class InvalidUpdateInputException extends DukeException {

    public InvalidUpdateInputException() {
        super("OOPS!!! Invalid input after delete command. Keep index within list range. (Example format: update INDEX [d/DESCRIPTION])");
    }
}
