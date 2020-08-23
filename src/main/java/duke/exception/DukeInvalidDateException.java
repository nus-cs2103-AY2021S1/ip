package duke.exception;

public class DukeInvalidDateException extends DukeException {

    public DukeInvalidDateException(String command) {
        super("Please enter a valid date for " + command + "!");
    }
}
