package duke.exception;

public class DukeEmptyDescriptionException extends DukeException {

    public DukeEmptyDescriptionException(String command) {
        super("Description for " + command + " cannot be empty!");
    }
}
