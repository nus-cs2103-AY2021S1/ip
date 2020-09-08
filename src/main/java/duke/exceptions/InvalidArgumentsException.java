package duke.exceptions;

public class InvalidArgumentsException extends DukeException {

    public InvalidArgumentsException(String command) {
        super("Invalid arguments for " + command);
    }
}
