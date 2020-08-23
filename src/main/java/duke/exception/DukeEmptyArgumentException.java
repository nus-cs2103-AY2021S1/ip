package duke.exception;

public class DukeEmptyArgumentException extends DukeException {

    public DukeEmptyArgumentException(String command) {
        super("Argument for " + command + " cannot be empty!");
    }
}
